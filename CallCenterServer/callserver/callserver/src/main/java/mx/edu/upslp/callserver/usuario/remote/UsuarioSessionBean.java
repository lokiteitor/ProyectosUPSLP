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

import java.sql.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.edu.upslp.callserver.usuario.UsuarioEJB;

/**
 *
 * @author David Delgado Hernandez 150205@upslp.edu.mx
 * SessionBean que funciona como Interfaz de acceso a los datos del usuario
 */
@Stateless
public class UsuarioSessionBean implements UsuarioSessionBeanRemote {
    
    // este contexto me permitira conectar mi instancia en memoria con la base de datos
    @PersistenceContext
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
    public UsuarioEJB RegistrarUsuario(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String turno, boolean administrador) {
        UsuarioEJB usuario = new UsuarioEJB();
        
        //@todo aqui van las validaciones llamando a otro bean
        
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setNacionalidad(nacionalidad);
        usuario.setTurno(turno);
        usuario.setAdministrador(administrador);
        
        // @ todo validar o crear Date de fecha        
        usuario.setFechaNacimiento(fechaNacimiento);
        
        //@todo crear y asignar password y username
        usuario.setUsername(turno);
        usuario.setPassword(turno);
        
        // guardar los datos en la base de datos
        manager.persist(usuario);
        
        return usuario;
    }
        
    /**
     * Genera el username compuesto por un numero una letra y un caracter especial
     * @return username generado
     */
    private String generarUsername(){
        String username = "david";
        
        return username;
    }
    
    private String generarPassword(String nombre,String apellido){
        // @todo encriptar contraseña
        String password = "pass";
        return password;
    }
    
}
