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
package mx.edu.upslp.callserver.incidencia.remote;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mx.edu.upslp.callserver.cliente.ClienteEJB;
import mx.edu.upslp.callserver.incidencia.IncidenciaEJB;
import mx.edu.upslp.callserver.movimiento.MovimientoEJB;
import mx.edu.upslp.callserver.usuario.UsuarioEJB;

/**
 * Este Session Bean obtiene los datos relacionados con la incidencias
 * @author David Delgado Hernandez 150205@upslp.edu.mx
 */
@Stateless
public class IncidenciaSessionBean implements IncidenciaSessionBeanRemote {

    @PersistenceContext(unitName = "mx.edu.upslp_callserver_ejb_1.0PU")
    private EntityManager manager;    
    /**
     * este metodo se puede invocar para registrar una incidencia   
     * @param datos hashmap con los datos de la incidencia a registrar
     * @return boolean - retorna true si la incidencia se registro correctamente
     */
    @Override
    public boolean registrarIncidencia(HashMap<String,Object> datos) {
        boolean integrity = true;
        // revisar el diccionario y relacionar los datos
        IncidenciaEJB incidencia = new IncidenciaEJB();
        MovimientoEJB movimiento = new MovimientoEJB();
        ClienteEJB cliente = null;
        Date now = new Date();
        
        boolean success = false;
        
        // estas son las llaves que deben de existir en el hashmap
        String[] keys = new String[] {"tipo","importancia","descripcion","nombre",
            "direccion","edad","telefono","idusuario","fecha","correo","apellido"
        };
        
        for (String key : keys) {
            if (!datos.containsKey(key)) {
                integrity = false;
            }
        }

        // se reviso que se tienen todos los datos ahora se revisa la integridad
        // de los mismos datos
        if (integrity) {
            if (integridadDatos(datos)) {
                
                // revisar si el usuario existe de no ser asi crear su registro

                cliente = manager.find(ClienteEJB.class, datos.get("correo").toString());

                if (cliente == null) {
                    cliente = new ClienteEJB();
                    cliente.setCreatedAt(new Date());
                }       
                // guarda los datos del cliente o los actualiza
                Date edad = (Date)datos.get("edad");
                cliente.setEdad(edad);                    
                cliente.setCorreo(datos.get("correo").toString());
                cliente.setNombre(datos.get("nombre").toString());
                cliente.setApellido(datos.get("apellido").toString());
                cliente.setDireccion(datos.get("direccion").toString());                   
                cliente.setTelefono(datos.get("telefono").toString());                            
                cliente.setUpdatedAt(new Date());
                manager.persist(cliente);                    

                // registrar los datos de la incidencia
                incidencia.setTipo(datos.get("tipo").toString());
                incidencia.setImportancia(datos.get("importancia").toString());
                incidencia.setDescripcion(datos.get("descripcion").toString());    
                // guardar la relacion con el usuario
                UsuarioEJB usuario = manager.find(UsuarioEJB.class, datos.get("idusuario").toString());
                incidencia.setIdUsuario(usuario);
                incidencia.setCliente(cliente);
                Date fecha = (Date)datos.get("fecha");
                
                incidencia.setFecha(fecha);
                incidencia.setCreated_at(now);
                incidencia.setUpdated_at(now);
                
                manager.persist(incidencia);
                
                // guardar registro de movimiento
                
                movimiento.setCreated_at(now);
                movimiento.setIncidencia(incidencia);
                movimiento.setTipo("ALTA");
                movimiento.setUpdated_at(now);
                movimiento.setUsuario(usuario);
                
                manager.persist(movimiento);
                
                success = true;
                
            }else{
                incidencia = null;
                System.out.println("Error al validar los datos");
            }               
        }else{
            incidencia = null;
            System.out.println("Faltan datos para registrar incidencia");
        }
        
        return success;
    }
    
    /**
     * revisa la integridad de los datos, que sean validos
     * @param datos el hashmap con los datos a validars
     * @return boolean - true si los datos son integros
     */
    private boolean integridadDatos(HashMap<String,Object> datos){
        // revisa que los datos sean del tipo necesario
        boolean integrity = true;
        if (datos.get("tipo") instanceof String) {
            if (datos.get("tipo").toString().equals("QUEJAS")  && datos.get("tipo").toString().equals("SUGERENCIA")) {
                integrity = false;
            }
        }else{
            integrity = false;         
        }

        if (datos.get("importancia") instanceof String) {
            if (!datos.get("importancia").toString().equals("EN ESPERA") &&
                    !datos.get("importancia").toString().equals("URGENTE")) {
                integrity = false;
            }
            
        }else{
            integrity = false;
        }
        
        if (!(datos.get("descripcion") instanceof String)) {
            integrity = false;
        }
        
        if (!(datos.get("nombre") instanceof String)) {
            integrity = false;
        }
        
        if (!(datos.get("apellido") instanceof String)) {
            integrity = false;
        }
        
        if (!(datos.get("direccion") instanceof String)) {
            integrity = false;
        }
        
        if (!(datos.get("edad") instanceof Date)) {
            integrity = false;
        }
        
        
        if (!(datos.get("telefono") instanceof String)) {
            integrity = false;
        }
        
        if ((datos.get("idusuario") instanceof String)) {
            // revisar que el usuario realmente exista
            if (manager.find(UsuarioEJB.class, datos.get("idusuario").toString()) == null) {
                integrity = false;
            }
        }else{
            integrity = false;
        }
            
        if (!((datos.get("fecha")) instanceof Date)) {
            integrity = false;
        }
            
        if (!(datos.get("correo") instanceof String)) {
            integrity = false;
        }
        
        return integrity;
       }

    /**
     * Retorna una lista las incidencias paginadas de 10 en 10
     * @param page pagina a mostrar
     * @param idUsuario id del usuario cuyas incidencias a listar
     * @return List - colleccion Lista con los registros de incidencias
     */
    @Override
    public List<IncidenciaEJB> listarIncidencias(int page,String idUsuario) {
        
        List<IncidenciaEJB> resultados;
        String sql = "SELECT * FROM INCIDENCIA WHERE ID_USUARIO=? LIMIT ?,? ";
        // escapa los parametros de la consulta
        // devolver las consultas encapsuladas en IncidenciaEJB
        Query query = manager.createNativeQuery(sql, IncidenciaEJB.class);
        query.setParameter(2,(page-1)*10 );
        query.setParameter(3, (page)*10);
        
        query.setParameter(1, idUsuario);
        
        // obtener los resultados
        resultados = query.getResultList();
                       
        return resultados;     
    }
    
    /**
     * actualiza la entidad del parametro
     * @param objetivo instancia de la entidad IncidenciaEJB a actualizar
     */
    @Override
    public void actualizarIncidencia(IncidenciaEJB objetivo) {
        MovimientoEJB movimiento = new MovimientoEJB();
        // retomar la conexion para la entidad
        IncidenciaEJB entity = manager.merge(objetivo);
        UsuarioEJB usuario = manager.merge(objetivo.getIdUsuario());
        ClienteEJB cliente = manager.merge(objetivo.getCliente());
        //@todo validar si es necesario
        // guardar los datos
        manager.persist(usuario);
        manager.persist(cliente);
        manager.persist(entity);
        
        // crear registro de movimiento
        movimiento.setCreated_at(new Date());
        movimiento.setIncidencia(entity);
        movimiento.setTipo("MODIFICACION");
        movimiento.setUpdated_at(new Date());
        movimiento.setUsuario(usuario);
        manager.persist(movimiento);
    }

    /**
     * remover la incidencia en base al ID de la incidencia
     * @param id id de la incidencia
     * @return retorna true si se removio con exito
     */
    @Override
    public boolean removerIncidencia(Long id) {
        MovimientoEJB movimientoEJB = new MovimientoEJB();
        UsuarioEJB usuario;
        boolean response = true;
        // buscar el registro
        IncidenciaEJB objetivo = manager.find(IncidenciaEJB.class, id);
        // remover
        if (objetivo == null) {
            response = false;
        }else{
            
            // obtener todos los movimientos
            for (MovimientoEJB movimiento : objetivo.getMovimientos()) {
                movimiento.setIncidencia(null);
                manager.persist(movimiento);
            }
            // registrar movimiento
            usuario = manager.merge(objetivo.getIdUsuario());
            movimientoEJB.setCreated_at(new Date());
            movimientoEJB.setIncidencia(null);
            movimientoEJB.setTipo("BAJA");
            movimientoEJB.setUpdated_at(new Date());
            movimientoEJB.setUsuario(usuario);
            manager.persist(movimientoEJB);
            
            manager.remove(objetivo);
        }
        
        return response;
    }
    /**
     * Obtiene los datos de un cliente especifico
     * @param correo correo de identificacion del cliente
     * @return Instancia EJB con los datos del cliente
     */
    @Override
    public ClienteEJB getClienteData(String correo) {
        
        ClienteEJB cliente = manager.find(ClienteEJB.class, correo);
        
        return cliente;
    }

    
    
}
