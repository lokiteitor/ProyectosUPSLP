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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import mx.edu.upslp.callserver.estadistica.EstadisticaSessionBeanRemote;
import mx.edu.upslp.callserver.incidencia.IncidenciaEJB;
import mx.edu.upslp.callserver.usuario.UsuarioEJB;

/**
 *
 * @author David Delgado Hernandez 150205@upslp.edu.mx Programacion III Miercoles Horario: 2:00 - 4:00
 */
public class Estadisticas {
    private Properties props = new Properties();
    private InitialContext ctx;
    private EstadisticaSessionBeanRemote estRemote;
    private List<UsuarioEJB> consulta;
    
    public Estadisticas() {
        // cargamos la configuracion del JNDI
        try{
            props.load(new FileInputStream("jndi.properties"));
            // se obtiene el contexto para futuras llamadas
            ctx = new InitialContext(props);
            // se obtiene la instancia del objeto remoto
            estRemote = (EstadisticaSessionBeanRemote) ctx.lookup("mx.edu.upslp.callserver.estadistica.EstadisticaSessionBeanRemote");
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo");
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo");
        } catch (NamingException e) {
            System.out.println("Error al resolver JDNI");
        }          
    }
    
    public int[] numeroIncidencias(){
        int[] datos;
        
        try{
            datos = estRemote.cantidadIncidencias();
        }catch(Exception e){
            datos =  null;
            System.err.println("Error al consultar estadisticas");
            System.err.println(e.getMessage());
        }        
        return datos;
        
    }
    
    public HashMap<String,Integer> listarMayores(){
        HashMap<String,Integer> result = new HashMap<String,Integer>();
        
        try{
            consulta = estRemote.IncidenciaPorUsuario();
            
            for (UsuarioEJB usuario : consulta) {
                result.put(usuario.getIdUsuario().toString(),Integer.valueOf(usuario.getIncidencias().size()));
            }
            
            
        }catch(Exception e){
            result = null;
            System.err.println("Error al consultar al servidor");
            System.err.println(e.getMessage());
        }                
        return result;
    }
    
    public HashMap<String,Object[]> incidenciasHoy(){
        List<IncidenciaEJB> resultado;
        HashMap<String,Object[]> datos = new HashMap<String,Object[]>();
        Long[] ids;
        String[] tipos;
        String[] importancias;
        String[] usuarios;
        String[] clientes;
        int cont = 0;
        
        try{
            resultado = estRemote.incidenciasHoy();
            ids = new Long[resultado.size()];
            tipos = new String[resultado.size()];
            importancias = new String[resultado.size()];
            usuarios = new String[resultado.size()];
            clientes = new String[resultado.size()];
            for (IncidenciaEJB incidencia : resultado) {                
                ids[cont] = incidencia.getIdIncidencia();
                tipos[cont] = incidencia.getTipo();
                importancias[cont] = incidencia.getImportancia();
                usuarios[cont] = incidencia.getIdUsuario().getIdUsuario();
                clientes[cont] = incidencia.getCliente().getCorreo();
                cont++;                        
            }
            
            datos.put("ids", ids);
            datos.put("tipos", tipos);
            datos.put("importancias", importancias);
            datos.put("usuarios", usuarios);
            datos.put("clientes", clientes);
            
        }catch(Exception e){
            System.err.println("Error al consultar el servidor");
            System.err.println(e.getMessage());
            datos = null;
        }
        
        return datos;        
    }
    
    public HashMap<String,Object[]> IncidenciaPorHora(LocalTime hora){
        List<IncidenciaEJB> resultado;
        HashMap<String,Object[]> datos = new HashMap<String,Object[]>();
        Long[] ids;
        String[] tipos;
        String[] importancias;
        String[] usuarios;
        String[] clientes;
        int cont = 0;
        
        try{
            resultado = estRemote.IncidenciasPorHora(hora);
            ids = new Long[resultado.size()];
            tipos = new String[resultado.size()];
            importancias = new String[resultado.size()];
            usuarios = new String[resultado.size()];
            clientes = new String[resultado.size()];
            for (IncidenciaEJB incidencia : resultado) {                
                ids[cont] = incidencia.getIdIncidencia();
                tipos[cont] = incidencia.getTipo();
                importancias[cont] = incidencia.getImportancia();
                usuarios[cont] = incidencia.getIdUsuario().getIdUsuario();
                clientes[cont] = incidencia.getCliente().getCorreo();
                cont++;                        
            }
            
            datos.put("ids", ids);
            datos.put("tipos", tipos);
            datos.put("importancias", importancias);
            datos.put("usuarios", usuarios);
            datos.put("clientes", clientes);
            
        }catch(Exception e){
            System.err.println("Error al consultar el servidor");
            System.err.println(e.getMessage());
            datos = null;
        }
        
        return datos;         
        
    }
    
}
