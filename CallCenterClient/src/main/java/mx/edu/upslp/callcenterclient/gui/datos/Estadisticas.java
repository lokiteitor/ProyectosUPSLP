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
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import mx.edu.upslp.callserver.estadistica.EstadisticaSessionBeanRemote;

/**
 *
 * @author David Delgado Hernandez 150205@upslp.edu.mx Programacion III Miercoles Horario: 2:00 - 4:00
 */
public class Estadisticas {
    private Properties props = new Properties();
    private InitialContext ctx;
    private EstadisticaSessionBeanRemote estRemote;
    
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
    
    public void numeroIncidencias(){
        estRemote.cantidadIncidencias();
    }
    
    
}
