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
package mx.edu.upslp.callserver.estadistica;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mx.edu.upslp.callserver.incidencia.IncidenciaEJB;
import mx.edu.upslp.callserver.usuario.UsuarioEJB;

/**
 *
 * @author David Delgado Hernandez 150205@upslp.edu.mx
 */
@Stateless
public class EstadisticaSessionBean implements EstadisticaSessionBeanRemote {

    @PersistenceContext(unitName = "mx.edu.upslp_callserver_ejb_1.0PU")
    private EntityManager manager;           
    
    /**
     * obtiene la cantidad de incidencias por tipo
     * @return retorna un arreglo [QUEJAS,SUGERENCIAS]
     */
    @Override
    public int[] cantidadIncidencias() {
        int[] datos = new int[2];
        
        String sql = "SELECT COUNT(*) FROM INCIDENCIA WHERE TIPO=?";
        
        Query query = manager.createNativeQuery(sql);
        
        query.setParameter(1, "QUEJA");
                
        Query querySugerencia = manager.createNativeQuery(sql);
        querySugerencia.setParameter(1, "SUGERENCIA");
        
        System.out.println(incidenciasHoy());
        
        try{
            //quejas
            datos[0] = Integer.parseInt(query.getSingleResult().toString());
            // sugerencias
            datos[1] = Integer.parseInt(querySugerencia.getSingleResult().toString());
        }catch(NumberFormatException e){
            System.out.println("Error durante la conversion de datos");
            System.err.println(e.getMessage());
        }
        
        return datos;
    }
    /**
     * Muestra las incidencias que se registraron el dia de hoy 
     * @return retorna una List con las incidencias
     */
    @Override
    public List<IncidenciaEJB> incidenciasHoy() {
        String sql = "SELECT * FROM INCIDENCIA WHERE DATE(FECHA)=curdate()";
        Query query = manager.createNativeQuery(sql,IncidenciaEJB.class);
        
        List<IncidenciaEJB> resultados = query.getResultList();
        
        System.out.println(resultados);
        
        return resultados;
    }
    
    /**
     * Muestra las incidencias registradas en un dia en especifico
     * @param fecha fecha a buscar
     * @return List con las incidencias encontradas
     */
    @Override
    public List<IncidenciaEJB> incidenciasDia(Date fecha) {
        String sql = "SELECT * FROM INCIDENCIA WHERE DATE(FECHA)=?";
        Query query = manager.createNativeQuery(sql,IncidenciaEJB.class);
        query.setParameter(1, fecha);
        List<IncidenciaEJB> resultados = query.getResultList();
        
        System.out.println(resultados);
        
        return resultados;
    }    
    
    /**
     * devuelve las incidencias registradas por un un usuario
     * @return List con las incidencias encontradas
     */
    @Override
    public List<UsuarioEJB> IncidenciaPorUsuario() {
        // crear consulta que utiliza los resultados de otra para consultar solo una vez
        String sql = "SELECT * FROM USUARIO JOIN (SELECT ID_USUARIO FROM INCIDENCIA GROUP BY ID_USUARIO ORDER BY COUNT(ID_USUARIO) LIMIT 10) as sub ON USUARIO.CORREO IN (sub.ID_USUARIO)";
        
        Query query = manager.createNativeQuery(sql,UsuarioEJB.class);
        List<UsuarioEJB> mayores = query.getResultList();
        
        for (int i = 0; i < mayores.size(); i++) {
            System.out.println(mayores.get(i).getIncidencias());
        }
        
        
        mayores.get(0);
                        
        return mayores;
    }    
    
    /**
     * Muestra las incidencias de hoy registradas dentro de cierta hora
     * @param hora la hora a buscar
     * @return List de incidencias encontradas
     */
    @Override
    public List IncidenciasPorHora(LocalTime hora) {
        //hora.format(DateTimeFormatter.ISO_TIME);
        String sql = "SELECT * FROM INCIDENCIA WHERE FECHA=CURDATE() AND TIME(created_at) BETWEEN TIME(?) and  DATE_ADD(TIME(?),INTERVAL 1 HOUR)";
        Query query = manager.createNativeQuery(sql,IncidenciaEJB.class);
        
        query.setParameter(1,hora.toString()+":00");
        query.setParameter(2,hora.toString()+":00");
                
        List<IncidenciaEJB> resultados = query.getResultList();
        System.out.println(hora.toString()+":00");
        System.out.println(resultados);
        
        
        return resultados;
    }    
}
