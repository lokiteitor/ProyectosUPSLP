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
package mx.edu.upslp.callserver.incidencencia.remote;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mx.edu.upslp.callserver.incidencencia.IncidenciaEJB;
import mx.edu.upslp.callserver.usuario.UsuarioEJB;

/**
 *
 * @author David Delgado Hernandez 150205@upslp.edu.mx
 */
@Stateless
public class IncidenciaSessionBean implements IncidenciaSessionBeanRemote {
    @PersistenceContext(unitName = "mx.edu.upslp_callserver_ejb_1.0PU")
    private EntityManager manager;    

    @Override
    public IncidenciaEJB registrarIncidencia(HashMap<String,Object> datos) {
        boolean integrity = true;
        // revisar el diccionario y relacionar los datos
        IncidenciaEJB incidencia = new IncidenciaEJB();
        Date now = new Date();
        
        String[] keys = new String[] {"tipo","importancia","descripcion","nombre",
            "direccion","edad","telefono","idusuario"
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

                // validados guardar los datos

                incidencia.setTipo(datos.get("tipo").toString());
                incidencia.setImportancia(datos.get("importancia").toString());
                incidencia.setDescripcion(datos.get("descripcion").toString());
                incidencia.setNombreCliente(datos.get("nombre").toString());
                incidencia.setDireccion(datos.get("direccion").toString());
                incidencia.setEdad((int)datos.get("edad"));
                incidencia.setTelefono(datos.get("telefono").toString());      
                incidencia.setIdUsuario(Long.valueOf(datos.get("idusuario").toString()));
                
                incidencia.setCreated_at(new java.sql.Date(now.getYear(),now.getMonth(),now.getYear()));
                incidencia.setUpdated_at(new java.sql.Date(now.getYear(),now.getMonth(),now.getYear()));
                
                manager.persist(incidencia);

            }else{
                incidencia = null;
                System.out.println("Error al validar los datos");
            }               
        }else{
            incidencia = null;
            System.out.println("Faltan datos para registrar incidencia");
        }
       return incidencia;
    }
    
    private boolean integridadDatos(HashMap<String,Object> datos){
        boolean integrity = true;
        if (datos.get("tipo") instanceof String) {
            if (datos.get("tipo").toString().equals("QUEJAS")  && datos.get("tipo").toString().equals("SUGERENCIA")) {
                integrity = false;
            }
        }else{
            integrity = false;         
        }

        if (datos.get("importancia") instanceof String) {
            if (datos.get("importancia").toString().equals("EN ESPERA") &&
                    datos.get("importancia").toString().equals("URGENTE")) {
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
        
        if (!(datos.get("direccion") instanceof String)) {
            integrity = false;
        }
        
        if (!(datos.get("edad") instanceof Integer)) {
            integrity = false;
        }
        
        if (!(datos.get("telefono") instanceof String)) {
            integrity = false;
        }
        
        if ((datos.get("idusuario") instanceof Long)) {
            // revisar que el usuario realmente exista
            if (manager.find(UsuarioEJB.class, datos.get("idusuario")) == null) {
                integrity = false;
            }
        }else{
            integrity = false;
        }
                       
        return integrity;
       }

    @Override
    public List listarIncidencias(int page,Long idUsuario) {
        List<IncidenciaEJB> resultados;
        String sql = "SELECT * FROM INCIDENCIA WHERE ID_USUARIO=? LIMIT ?,? ";
        
        Query query = manager.createNativeQuery(sql, IncidenciaEJB.class);
        query.setParameter(2,(page-1)*10 );
        query.setParameter(3, (page)*10);
        
        query.setParameter(1, idUsuario);
        
        // obtener los resultados
        
        resultados = query.getResultList();

        return resultados;     
    }

    @Override
    public void actualizarIncidencia(IncidenciaEJB objetivo) {
        
        IncidenciaEJB entity = manager.merge(objetivo);
        manager.persist(entity);
    }

    @Override
    public boolean removerIncidencia(Long id) {
        boolean response = true;
        IncidenciaEJB objetivo = manager.find(IncidenciaEJB.class, id);
        
        if (objetivo == null) {
            response = false;
        }else{
            manager.remove(objetivo);
        }
        
        return false;
    }
    
    
}