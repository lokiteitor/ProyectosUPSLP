/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.upslp.guarderia;

import mx.edu.upslp.guarderia.datos.Registro;
import mx.edu.upslp.guarderia.datos.Reporte;

/**
 *
 * @author lokiteitor
 */
public class Guarderia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        boolean exit = false;
        Menu menu = new Menu();
        Registro registro = new Registro("dat");
            Reporte reporte = new Reporte(registro.getLista());
        int opc;
        // instancias de objetos propios

        
        menu.bienvenida();
        
        do {
            opc = menu.menuPrincipal();

            switch(opc){
                case 1:
                    System.out.println("Alta de niño\nIngrese los datos del infante");
                    menu.altaInfante(registro.getLista());
                    break;
                case 2:
                    System.out.println("Registro de entrada\nIngrese los datos de entrada");
                    menu.registrarEntrada(registro.getLista());
                    break;
                case 3:
                    System.out.println("Registro de salida\nIngrese los datos de salida");
                    menu.registrarSalida(registro.getLista());
                    break;
                case 4:
                    System.out.println("Informes");                    
                    break;
                case 5:
                    System.out.println("Bye");            
            }
            if (opc == 5) {
                exit = true;
            }                       
        } while (!exit);
        
        
    }
    
}
