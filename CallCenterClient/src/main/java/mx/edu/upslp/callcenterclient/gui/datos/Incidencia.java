/*
 * The MIT License
 *
 * Copyright 2017 David Delgado Hernandez 150205@upslp.edu.mx.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package mx.edu.upslp.callcenterclient.gui.datos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import mx.edu.upslp.callserver.cliente.ClienteEJB;
import mx.edu.upslp.callserver.incidencia.IncidenciaEJB;
import mx.edu.upslp.callserver.incidencia.remote.IncidenciaSessionBeanRemote;

/**
 * Esta clase se encarga de consultar los datos de las incidencias al servidor
 * @author David Delgado Hernandez 150205@upslp.edu.mx Programacion III Miercoles Horario: 2:00 - 4:00
 */
public class Incidencia {
    /**
     * tipo de la incidencia
     */
    private String tipo;
    /**
     * importancia de la incidencia
     */
    private String importancia;
    /**
     * descripcion de la incidencia
     */
    private String descripcion;
    /**
     * id del usuario que la registro
     */
    private String idUsuario;
    /**
     * nombre del cliente
     */
    private String nombreCliente;
    /**
     * domicilio del cliente
     */
    private String direccion;
    /**
     * edad del cliente
     */
    private Date edad;
    /**
     * telefono del cliente
     */
    private String telefono;
    /**
     * fecha de la incidencia
     */
    private Date fecha;
    /**
     * correo del cliente
     */
    private String correo;
    /**
     * apellido del cliente
     */
    private String apellido;
    /**
     * propiedades del JDNI
     */
    private Properties props = new Properties();
    /**
     * Contexto remoto para llamar a los EJB
     */
    private InitialContext ctx;
    private IncidenciaSessionBeanRemote remoteIncidencia;
    /**
     * Lista con las incidencias que devuelve
     */
    private List<IncidenciaEJB> allRegistros;
    /**
     * Lista con los clientes
     */
    private ArrayList<ClienteEJB> clientes = new ArrayList<ClienteEJB>();
    /**
     * lista con los ids de Incidencias
     */
    private ArrayList<Long> idIncidencias = new ArrayList<Long>(); 
    /**
     * lista con los niveles de incidencias
     */
    private ArrayList<String> niveles = new ArrayList<String>(); 
    /**
     * lista con los tipos
     */
    private ArrayList<String> tipos = new ArrayList<String>();
    /**
     * lista con los datos utilizados en los modelos
     */
    private Object[][] datos;

    /**
     * Atraves del constructor se conectar con el servidor
     */
    public Incidencia() {        
        // cargamos la configuracion del JNDI
        try{
            props.load(new FileInputStream("jndi.properties"));
            // se obtiene el contexto para futuras llamadas
            ctx = new InitialContext(props);
            // se obtiene la instancia del objeto remoto
            remoteIncidencia = (IncidenciaSessionBeanRemote) ctx.lookup("mx.edu.upslp.callserver.incidencia.remote.IncidenciaSessionBeanRemote");
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo");
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo");
        } catch (NamingException e) {
            System.out.println("Error al resolver JDNI");
        }       
    }    
    
    /**
     * registra una incidencia con los atributos de esta clase
     * @return true si la operacion fue correcta
     */
    public boolean registrarIncidencia(){
        // crear el mapa
        HashMap<String,Object> datos = new HashMap<String,Object>();
        // llenar los datos
        datos.put("tipo", tipo);
        datos.put("importancia", importancia);
        datos.put("descripcion", descripcion);
        datos.put("idusuario", idUsuario);
        datos.put("nombre", nombreCliente);
        datos.put("apellido",apellido);
        datos.put("direccion", direccion);
        datos.put("edad", edad);
        datos.put("telefono", telefono);
        datos.put("fecha", fecha);
        datos.put("correo", correo);
        
        // guardar los datos en el servidor

        return remoteIncidencia.registrarIncidencia(datos);
    }
    /**
     * obtiene los datos del servidor en crudo
     * @param page pagina de la base de datos a analizar
     * @param id id del usuario
     */
    private void getModelRaw(int page,String id){
            // pedir los datos al server
        allRegistros = remoteIncidencia.listarIncidencias(page, id);
        try{    
            // crea la matriz para el modelo
            for (IncidenciaEJB incidencia : allRegistros) {
                idIncidencias.add(incidencia.getIdIncidencia());
                clientes.add(incidencia.getCliente());
                niveles.add(incidencia.getImportancia());
                tipos.add(incidencia.getTipo());
            }            
        }catch(Error e){
            System.out.println(e.getMessage());
        }catch(Exception e){
            System.out.println("Error al consultar con el servidor");
            System.out.println(e.getMessage());
        }
    }
    /**
     * Genera los datosy los guarda en un arreglo de objetos
     * @return retorna los datos en forma de arreglo
     */
    private Object[][] generarDatos(){
        datos = new Object[idIncidencias.size()][4];
        for (int i = 0; i < idIncidencias.size(); i++) {
            datos[i][0] = idIncidencias.get(i);                                    
            datos[i][1] = clientes.get(i).getCorreo();
            datos[i][2] = niveles.get(i);
            datos[i][3] = tipos.get(i);
        }
        return datos;
    }
    
    /**
     * renueva los datos
     * @param page pagina a buscar
     * @param idUsuario id del usuario
     * @return Matriz con los datos listo para modelo de tablas
     */
    public Object[][] obtenerDatos(int page,String idUsuario){

        idIncidencias.clear();
        clientes.clear();
        niveles.clear();
        tipos.clear();
        getModelRaw(page,idUsuario);
        generarDatos();
        return datos;
    }
    /**
     * obtiene los datos de una determinada incidencia
     * @param indx indice dentro del arreglo de datos
     * @return instancia EJB con los datos de la incidenciaa
     */
    public IncidenciaEJB obtenerDatosIncidencia(int indx){
        IncidenciaEJB response = null;
        
        if (allRegistros != null && allRegistros.size() >= indx) {
            response = allRegistros.get(indx);
        }        

        return response;
    }
    
    /**
     * Actualiza los datos de la incidencia
     * @param objetivo incidencia a actualizar
     * @param page pagina a retornar
     * @param idUsuario  id del usuario
     */
    public void actualizarIncidencia(IncidenciaEJB objetivo,int page,String idUsuario){
        try{
            remoteIncidencia.actualizarIncidencia(objetivo);
            obtenerDatos(page,idUsuario);
        }catch(Exception e){
            System.out.println("Error al actualizar la incidencia");
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Remueve una incidencia de la base datos
     * @param id id de la incidencia
     * @return true si la operacion fue existosa
     */
    public boolean removerIncidencia(Long id){
        boolean response;
        
        try{
            response = remoteIncidencia.removerIncidencia(id);
        }catch(Exception e){
            System.out.println("Error al remover la incidencia");
            System.out.println(e.getMessage());
            response = false;
        }
        return response;
    }
    
    /**
     * Obtiene los datos de un cliente en especifico
     * @param correo correo del cliente a buscar
     * @return instancia EJB del cliente 
     */
    public ClienteEJB getDataCliente(String correo){
        ClienteEJB cliente = null;
        
        try{
            cliente = remoteIncidencia.getClienteData(correo);
        }catch(Exception e){
            System.err.println("Error al obtener los datos del servidor");
            System.err.println(e.getMessage());
        }
        return cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    
    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the importancia
     */
    public String getImportancia() {
        return importancia;
    }

    /**
     * @param importancia the importancia to set
     */
    public void setImportancia(String importancia) {
        this.importancia = importancia;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the idUsuario
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the nombreCliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * @param nombreCliente the nombreCliente to set
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the edad
     */
    public Date getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(Date edad) {
        this.edad = edad;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
