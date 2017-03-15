/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guarderia;

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
        int opc;
        
        menu.bienvenida();
        
        do {
            opc = menu.menuPrincipal();

            switch(opc){
                case 1:
                    System.out.println("Alta de niño\nIngrese los datos del infante");
                    break;
                case 2:
                    System.out.println("Registro de entrada\nIngrese los datos de entrada");
                    break;
                case 3:
                    System.out.println("Registro de salida\nIngrese los datos de salida");
                    break;
                case 4:
                    System.out.println("Impresion de informe");
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
