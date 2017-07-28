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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import mx.edu.upslp.callserver.movimiento.MovimientoEJB;
import mx.edu.upslp.callserver.movimiento.remote.MovimientoSessionBeanRemote;

/**
 * Esta clase se encarga de listar los movimientos y proporcionar informacion 
 * sobre los mismos
 * @author David Delgado Hernandez 150205@upslp.edu.mx Programacion III Miercoles Horario: 2:00 - 4:00
 */
public class Movimiento {
    private Properties props = new Properties();
    private InitialContext ctx;
    private MovimientoSessionBeanRemote remoteMovimiento;
    private List<MovimientoEJB> allMovimientos;
    /**
     * formatea las fechas
     */
    private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    /**
     * lista con los id de los movimientos
     */
    private ArrayList<Long> idMovimientos = new ArrayList<Long>();
    /**
     * lista los tipos de movimiento
     */
    private ArrayList<String> tipos = new ArrayList<String>();
    /**
     * lista con las fechas de las incidecias
     */
    private ArrayList<Date> fechas = new ArrayList<Date>();
    /**
     * datos de los modelos
     */
    private Object[][] datos;
    
    /**
     * El constructor se conecta con la base de datos
     */
    public Movimiento() {
        // cargamos la configuracion del JNDI
        try{
            props.load(new FileInputStream("jndi.properties"));
            // se obtiene el contexto para futuras llamadas
            ctx = new InitialContext(props);
            // se obtiene la instancia del objeto remoto
            remoteMovimiento = (MovimientoSessionBeanRemote) ctx.lookup("mx.edu.upslp.callserver.movimiento.remote.MovimientoSessionBeanRemote");
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo");
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo");
        } catch (NamingException e) {
            System.out.println("Error al resolver JDNI");
        }        
        
    }
    /**
     * Obtiene los datos del servidor J2EE
     * @param id id del movimiento
     */
    private void getModelRaw(String id){
            // pedir los datos al server        
        try{    
            allMovimientos = remoteMovimiento.listarMovimientos(id);
            System.out.println(allMovimientos);
            // crea la matriz para el modelo
            for (MovimientoEJB movimiento : allMovimientos) {
                if (movimiento.getIncidencia() != null) {
                    idMovimientos.add(movimiento.getIncidencia().getIdIncidencia());
                }else{
                    idMovimientos.add(0L);
                }
                tipos.add(movimiento.getTipo());
                fechas.add(movimiento.getCreated_at());
            }            
            
        }catch(Error e){
            System.out.println(e.getMessage());
        }catch(Exception e){
            System.out.println("Error al consultar con el servidor");
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * genera los datos para los modelos de tablas
     * @return Arreglo de objetos con los datos
     */
    private Object[][] generarDatos(){
        datos = new Object[idMovimientos.size()][4];
        for (int i = 0; i < idMovimientos.size(); i++) {
            datos[i][0] = idMovimientos.get(i);                                    
            datos[i][2] = formato.format(fechas.get(i));
            datos[i][1] = tipos.get(i);
        }
        return datos;
    }    

    /**
     * Obtiene los datos de los movimientos de  un usuario
     * @param idUsuario id del usuario
     * @return arreglo con los movimientos realizados
     */
    public Object[][] obtenerDatos(String idUsuario){                
        
        idMovimientos.clear();
        fechas.clear();
        tipos.clear();
        getModelRaw(idUsuario);
        generarDatos();
        return datos;
    }
    
}
