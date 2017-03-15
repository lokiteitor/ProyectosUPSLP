/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guarderia;

import java.util.Date;

import guarderia.validaciones.ValidarCadena;
/**
 *
 * @author lokiteitor
 */
public class Menu {
    
    private Date date;
    private String fecha;
    private String hora;    
    private final ValidarCadena validar;
    
    public Menu(){
        date = new Date();
        validar = new ValidarCadena();
    }
    
    public void bienvenida(){
        
        System.out.println("Bienvenido al sistema");
        System.out.println("Fecha actual: " + date.toLocaleString() );        
    }    
    
    public int menuPrincipal(){
        int opc;
        System.out.println("Que accion desea realizar");
        System.out.print("1- Alta de niño\n"
            + "2- Registro entrada\n"
            + "3- Registro salida\n"
            + "4- Informe\n"
            + "5- Salir\n");
        
        opc = validar.opcionValida("", 5);                
        return opc;        
    }
    
}
