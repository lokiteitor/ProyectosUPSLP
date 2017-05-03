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
package mx.edu.upslp.callserver.usuario.remote;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mx.edu.upslp.callserver.usuario.UsuarioEJB;
import java.util.Date;

/**
 *
 * @author David Delgado Hernandez 150205@upslp.edu.mx
 * SessionBean que funciona como Interfaz de acceso a los datos del usuario
 */
@Stateless
public class UsuarioSessionBean implements UsuarioSessionBeanRemote {
    
    // este contexto me permitira conectar mi instancia en memoria con la base de datos
    @PersistenceContext(unitName = "mx.edu.upslp_callserver_ejb_1.0PU")
    private EntityManager manager;

    /**
     * registra al usuario tomando los datos del hashmap del parametro
     * @param datos HashMap con los datos a registrar
     * @return Entity Bean del usuario registrado, null si registro fallo
     */
    @Override
    public UsuarioEJB registrarUsuario(HashMap<String,Object> datos) {
        UsuarioEJB usuario = new UsuarioEJB();
        java.util.Date now = new Date();
        boolean integrity = true;
        
        // estos son los datos que debe contener el hashmap
        String [] keys = new String[] {
            "nombre","apellido","nacimiento",
            "nacionalidad","turno","administrador","correo"
        };
        
        // revisar que se proporcionan todas las llaves
        
        for (String key : keys) {
            if (!datos.containsKey(key)) {
                integrity = false;                
            }
        }
        
        // revisar que el usuario no exista
        if (integrity) {
            String sql = "SELECT CORREO FROM USUARIO WHERE CORREO=?";
            // vincular los parametros y revisar si los resultados son vacios
            Query query = manager.createNativeQuery(sql,UsuarioEJB.class);
            query.setParameter(1, datos.get("correo").toString());

            if (!(query.getResultList().isEmpty())) {
                integrity = false;
            }            
        }
        
        // se reviso que se tienen todos los datos ahora se revisa la integridad
        // de los mismos datos

        if (integrity) {
            if (integridadDatos(datos)) {
                // vincular los datos del usuario
                usuario.setIdUsuario(datos.get("correo").toString());
                usuario.setNombre(datos.get("nombre").toString());
                usuario.setApellido(datos.get("apellido").toString());
                usuario.setNacionalidad(datos.get("nacionalidad").toString());
                usuario.setTurno(datos.get("turno").toString());
                usuario.setAdministrador(
                        Boolean.valueOf(datos.get("administrador").toString()));

                
                java.util.Date nacimiento = (Date) datos.get("nacimiento");
                usuario.setFechaNacimiento(nacimiento);

                // generar los password y usernames y guardarlos en la db
                usuario.setUsername(generarUsername(datos.get("nombre").toString()));
                usuario.setPassword(generarPassword(datos.get("nombre").toString(),
                        datos.get("nombre").toString()));

                usuario.setUpdatedAt(now);
                usuario.setCreatedAt(now);                
                // guardar el usuario
                manager.persist(usuario);
            }else{               
                usuario = null;
            }
        }else{
            usuario = null;
        }
                
        return usuario;
    }
    
    
    /**
     * Genera el username compuesto por un numero una letra y un caracter especial
     * @return username generado
     */
    private String generarUsername(String nombre){
        String username = "";
        Random random = new Random();
        boolean flag = true;
        String sql = "SELECT CORREO,USERNAME FROM USUARIO WHERE USERNAME=?";
        Query query;
        List<UsuarioEJB> resultados;
        
        
        do {
            username = String.valueOf(random.nextInt(10)) +
                    String.valueOf((char)(random.nextInt(25)+97 )) +
                    String.valueOf((char)(random.nextInt(15)+33 ));   
            
            // revisar que el usuario no exista
            query = manager.createNativeQuery(sql,UsuarioEJB.class);
            query.setParameter(1, username);
            

            resultados = query.getResultList();
            if (resultados.isEmpty()) {
               flag = false;
            }
                        
        } while (flag);
        
        System.out.println(username);
        return username;
    }
    
    /**
     * Generar el password del usuario
     * @param nombre nombre del usuario
     * @param apellido apellido del usuario
     * @return el password generado
     */
    private String generarPassword(String nombre,String apellido){
        // @todo encriptar contraseña
        Random random = new Random();
        String password;
        
        password = nombre.substring(0, 3) + String.valueOf(random.nextInt(99)) + apellido.charAt(0);                
        return password;
    }
    
    
    /**
     * Verifica si el usuario es valido(existe y coinciden sus credenciales)
     * @param username nombre del usuario
     * @param password password del usuario
     * @return  retorna true si el usuario existe en los registros y los datos coinciden
     */
    @Override
    public boolean login(String username, String password) {
        boolean isLogin = false;
        
        UsuarioEJB resultado;
        // utilizando ejbQL para consultas a la base de datos
        String ejbQL = "SELECT CORREO,USERNAME,PASSWORD FROM USUARIO WHERE USERNAME=?";                
        Query query = manager.createNativeQuery(ejbQL,UsuarioEJB.class);
        // escapamos los parametros para evitar SQL injection
        query.setParameter(1,username );
        
        try{
            resultado = (UsuarioEJB) query.getSingleResult();
            
            if (resultado.getPassword().equals(password)) {
                isLogin = true;
            }            
        }catch(NoResultException e){
            isLogin = false;
        }
        
        return isLogin;        
    }
    
    /**
     * Verifica si el usuario es administrador
     * @param username nombre del usuario
     * @return  retorna true si el usuario es administrador
     */
    @Override
    public boolean isAdmin(String username) {        
        boolean isAdmin = false;
        UsuarioEJB resultado;
        // busca al usuario
        String sql = "SELECT CORREO,ADMINISTRADOR FROM USUARIO WHERE USERNAME=?";
        Query query = manager.createNativeQuery(sql,UsuarioEJB.class);
        
        query.setParameter(1, username);
        // se espera que se obtenga solo un resultado
        resultado = (UsuarioEJB) query.getSingleResult();
        
        if (resultado.isAdministrador()) {
            isAdmin = true;
        }
        System.out.println(resultado.toString());

        return isAdmin;
    }

    
    /**
     * Devolver una lista con todos los usuarios registrados
     * @return Lista de usuarios registrados
     */
    @Override
    public List<UsuarioEJB> getAllUsers() {
        return manager.createNativeQuery("SELECT * FROM USUARIO",UsuarioEJB.class).getResultList();
    }
    /**
     * Actualiza los datos de la entidad 
     * @param usuario Entity Bean UsuarioEJB con los datos almacenados
     */
    @Override
    public void actualizarDatosEntidad(UsuarioEJB usuario) {
        // retomar la conexion
        UsuarioEJB entity = manager.merge(usuario);
        manager.persist(entity);               
    }

    @Override
    public boolean removerUsuario(String id) {
        boolean response = true;
        UsuarioEJB objetivo = manager.find(UsuarioEJB.class, id);
        
        if (objetivo == null) {
            response = false;
        }else{
            manager.remove(objetivo);
        }        
        return response;
    }

    @Override
    public UsuarioEJB obtenerUsuario(String username) {
        UsuarioEJB usuario;
        
        String sql = "SELECT * FROM USUARIO WHERE USERNAME=?";
        Query query = manager.createNativeQuery(sql,UsuarioEJB.class);
        
        query.setParameter(1, username);
        
        usuario = (UsuarioEJB) query.getSingleResult();
                
        return usuario;
    }

    @Override
    public boolean integridadDatos(HashMap<String,Object> datos) {
        boolean integridad = true;
        
        if (!(datos.get("nombre") instanceof String)) {
            integridad = false;
        }
        //@todo validar que se trate de un correo
        if (!(datos.get("correo") instanceof String)) {
            integridad = false;
        }
        
        if (!(datos.get("apellido") instanceof String)) {
            integridad = false;
        }
        
        if (!(datos.get("nacimiento") instanceof Date)) {
            integridad = false;
        }
 
        
        if (!(datos.get("nacionalidad") instanceof String)) {
            integridad = false;
        }
        if (datos.get("turno") instanceof String) {
            if (!datos.get("turno").equals("MATUTINO") && 
                    !datos.get("turno").equals("VESPERTINO") &&
                    !datos.get("turno").equals("NOCTURNO")) {
                integridad = false;
            }
        }
        
        if (!(datos.get("administrador") instanceof Boolean)) {
            integridad = false;
        }
        
        return integridad;
    }

    @Override
    public List<UsuarioEJB> listUsers(int page) {
        List<UsuarioEJB> resultados;
        String sql = "SELECT * FROM USUARIO LIMIT ?,? ";
        // escapa los parametros de la consulta
        // devolver las consultas encapsuladas en IncidenciaEJB
        Query query = manager.createNativeQuery(sql, UsuarioEJB.class);
        query.setParameter(1,(page-1)*10 );
        query.setParameter(2, (page)*10);
        
        
        // obtener los resultados
        resultados = query.getResultList();
                       
        return resultados;
    }

    
}
