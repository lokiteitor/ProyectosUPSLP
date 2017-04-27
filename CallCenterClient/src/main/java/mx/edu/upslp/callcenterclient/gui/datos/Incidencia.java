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
 *
 * @author David Delgado Hernandez 150205@upslp.edu.mx Programacion III Miercoles Horario: 2:00 - 4:00
 */
public class Incidencia {
    
    private String tipo;
    private String importancia;
    private String descripcion;
    private String idUsuario;
    private String nombreCliente;
    private String direccion;
    private Date edad;
    private String telefono;
    private Date fecha;
    private String correo;
    private String apellido;
    
    private Properties props = new Properties();
    private InitialContext ctx;
    private IncidenciaSessionBeanRemote remoteIncidencia;
    
    private List<IncidenciaEJB> allRegistros;
    private ArrayList<ClienteEJB> clientes = new ArrayList<ClienteEJB>();
    private ArrayList<Long> idIncidencias = new ArrayList<Long>(); 
    private ArrayList<String> niveles = new ArrayList<String>(); 
    private ArrayList<String> tipos = new ArrayList<String>();
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
    
    public Object[][] obtenerDatos(int page,String idUsuario){

        idIncidencias.clear();
        clientes.clear();
        niveles.clear();
        tipos.clear();
        getModelRaw(page,idUsuario);
        generarDatos();
        return datos;
    }
    
    public IncidenciaEJB obtenerDatosIncidencia(int indx){
        IncidenciaEJB response = null;
        
        if (allRegistros != null && allRegistros.size() >= indx) {
            response = allRegistros.get(indx);
        }        

        return response;
    }
    
    public void actualizarIncidencia(IncidenciaEJB objetivo,int page,String idUsuario){
        try{
            remoteIncidencia.actualizarIncidencia(objetivo);
            obtenerDatos(page,idUsuario);
        }catch(Exception e){
            System.out.println("Error al actualizar la incidencia");
            System.out.println(e.getMessage());
        }
    }
    
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
