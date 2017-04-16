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

import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mx.edu.upslp.callserver.usuario.UsuarioEJB;

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
     * Crear un nuevo usuario
     * @param nombre - Nombre del usuario
     * @param apellido - Apellido del usuario
     * @param fechaNacimiento Fecha de nacimiento
     * @param nacionalidad nacionalidad del usuario
     * @param turno turno en el que trabaja
     * @param administrador true si el usuario es administrador
     * @return  Entity Bean con los datos del usuario
     */
    @Override
    public UsuarioEJB registrarUsuario(String nombre, String apellido, java.util.Date nacimiento, String nacionalidad, String turno, boolean administrador) {
        UsuarioEJB usuario = new UsuarioEJB();
        java.util.Date now = new java.util.Date();
        //@todo aqui van las validaciones llamando a otro bean
        
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setNacionalidad(nacionalidad);
        usuario.setTurno(turno);
        usuario.setAdministrador(administrador);
        
        // @ todo validar o crear Date de fecha        
        usuario.setFechaNacimiento(new java.sql.Date(nacimiento.getDate(), nacimiento.getMonth(), nacimiento.getYear()));
        
        //@todo crear y asignar password y username
        usuario.setUsername(generarUsername(nombre));
        usuario.setPassword(generarPassword(nombre, apellido));
        
        usuario.setUpdatedAt(new java.sql.Date(now.getYear(), now.getMonth(), now.getDay()));
        usuario.setCreatedAt(new java.sql.Date(now.getYear(), now.getMonth(), now.getDay()));
        // guardar los datos en la base de datos
        manager.persist(usuario);
        
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
        String sql = "SELECT ID_USUARIO,USERNAME FROM USUARIO WHERE USERNAME=?";
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
    
    private String generarPassword(String nombre,String apellido){
        // @todo encriptar contraseña
        Random random = new Random();
        String password;
        
        password = nombre.substring(0, 3) + String.valueOf(random.nextInt(99)) + apellido.charAt(0);                
        return password;
    }
    
    
    /**
     * Verifica si el usuario es valido
     * @param username nombre del usuario
     * @param password password del usuario
     * @return  retorna true si el usuario existe en los registros y los datos coinciden
     */
    @Override
    public boolean login(String username, String password) {
        boolean isLogin = false;
        
        UsuarioEJB resultado;
        // utilizando ejbQL para consultas a la base de datos
        String ejbQL = "SELECT ID_USUARIO,USERNAME,PASSWORD FROM USUARIO WHERE USERNAME=?";                
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
        
        String sql = "SELECT ID_USUARIO,ADMINISTRADOR FROM USUARIO WHERE USERNAME=?";
        Query query = manager.createNativeQuery(sql,UsuarioEJB.class);
        
        query.setParameter(1, username);
        
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

    @Override
    public void actualizarDatosEntidad(UsuarioEJB usuario) {
        UsuarioEJB entity = manager.merge(usuario);
        manager.persist(entity);               
    }

    @Override
    public boolean removerUsuario(Long id) {
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
    
}
