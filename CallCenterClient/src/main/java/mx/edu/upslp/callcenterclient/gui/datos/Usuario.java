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
import mx.edu.upslp.callserver.usuario.UsuarioEJB;
import mx.edu.upslp.callserver.usuario.remote.UsuarioSessionBeanRemote;

/**
 * Esta clase permite obtener los datos de un usuario atraves de los beans
 * @author David Delgado Hernandez 150205@upslp.edu.mx Programacion III Miercoles Horario: 2:00 - 4:00
 */
public class Usuario {   
    private Properties props = new Properties();
    private InitialContext ctx;
    private UsuarioSessionBeanRemote remoteUsuario;
    private List<UsuarioEJB> allRegistros;
    // los siguientes arreglos son necesarios para mantener el modelo
    private ArrayList<String> usernames = new ArrayList<String>();
    private ArrayList<String> nombres = new ArrayList<String>();
    private ArrayList<String> apellidos = new ArrayList<String>();
    private Object[][] datos;
    private UsuarioEJB usuario;
    
    // datos utilizados para crear al usuario
    private String correo;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String username;
    private String password;
    private String nacionalidad;
    private String turno;
    private Boolean administrador;

    /**
     * Atraves del constructor se conectar con el servidor
     */
    public Usuario() {        
        // cargamos la configuracion del JNDI
        try{
            props.load(new FileInputStream("jndi.properties"));
            // se obtiene el contexto para futuras llamadas
            ctx = new InitialContext(props);
            // se obtiene la instancia del objeto remoto
            remoteUsuario = (UsuarioSessionBeanRemote) ctx.lookup("mx.edu.upslp.callserver.usuario.remote.UsuarioSessionBeanRemote");
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo");
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo");
        } catch (NamingException e) {
            System.out.println("Error al resolver JDNI");
        }       
    }
      
    /**
     * Crea un nuevo usuario
     * @param nombre
     * @param apellido
     * @param fechaNacimiento
     * @param nacionalidad
     * @param turno
     * @param administrador 
     * @return 
     */
    public UsuarioEJB registro(){
        // utilizar el session bean para registra el usuario
        // crear el hashmap
        HashMap<String,Object> datos = new HashMap<String,Object>();
        // llenar los datos
        datos.put("correo",correo);
        datos.put("nombre", nombre);
        datos.put("apellido", apellido);
        datos.put("nacimiento", fechaNacimiento);
        datos.put("nacionalidad", nacionalidad);
        datos.put("turno", turno);
        datos.put("administrador", administrador);        
        
        
        try{
            this.usuario = remoteUsuario.registrarUsuario(datos);
            this.username = usuario.getUsername();
            this.password = usuario.getPassword();
        }catch(Exception e){
            System.out.println("Error al llamar al servidor");
            System.out.println(e.getMessage());
            usuario = null;
        }        
        return this.usuario;
    }      
    
    
    private void getUsuarioModelRaw(){

        try{
            // consultar la lista de usuarios
            allRegistros = remoteUsuario.getAllUsers();
            // con esta lista generar una matriz de objetos para crear el modelo
            
            for (UsuarioEJB usuario : allRegistros) {
                usernames.add(usuario.getNombre());
                apellidos.add(usuario.getApellido());
                nombres.add(usuario.getNombre());
            }
            
        }catch(Exception e){
            System.out.println("Error al consultar el servidor");
            System.out.println(e.getMessage());
        }
    }
    
    public Object[][] obtenerDatos(boolean regenerar){
        // llamar a la API si se quiere regenerar
        if (regenerar) {
            usernames.clear();
            apellidos.clear();
            nombres.clear();            
            getUsuarioModelRaw();            
        }
        generarDatos();
        
        return datos;
    }
    
    public UsuarioEJB obtenerDatosUsuario(int indx){
        UsuarioEJB response = null;
        if (allRegistros != null && allRegistros.size() >= indx ) {
            response = allRegistros.get(indx);
        }
        return response;
    }
    
    private Object[][] generarDatos(){
        datos = new Object[nombres.size()][4];
        for (int i = 0; i < nombres.size(); i++) {
            datos[i][0] = nombres.get(i);
            datos[i][1] = apellidos.get(i);
            datos[i][2] = usernames.get(i);
        }
        return datos;
    }
    
    public void actualizarUsuario(UsuarioEJB objetivo){
        try{
            remoteUsuario.actualizarDatosEntidad(objetivo);
            obtenerDatos(true);            
        }catch(Exception e){
            System.out.println("Error al consultar al servidor");
            System.out.println(e.getMessage());
        }
    }
    
    public boolean removerUsuario(String id){
        boolean response;
        try{
            response = remoteUsuario.removerUsuario(id);
        }catch(Exception e){
            System.out.println("Error al remover el usuario");
            System.out.println(e.getMessage());
            response = false;
        }
        return response;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the nacionalidad
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * @param nacionalidad the nacionalidad to set
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * @return the turno
     */
    public String getTurno() {
        return turno;
    }

    /**
     * @param turno the turno to set
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }

    /**
     * @return the administrador
     */
    public Boolean getAdministrador() {
        return administrador;
    }

    /**
     * @param administrador the administrador to set
     */
    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }
    
}
