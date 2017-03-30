package mx.edu.upslp.guarderia;

import mx.edu.upslp.guarderia.datos.Registro;

/**
 *
 * @author David Delgado Hernandez 150205@upslp.edu.mx
 * Programacion III
 * Grupo C
 */
public class Guarderia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        boolean exit = false;
        Menu menu = new Menu();
        Registro registro = new Registro("dat");
        int opc;       

        // imprimir el mensaje de bienvenida
        menu.bienvenida();
        
        do {
            // optener la opcion indicada por el usuario
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
                    menu.crearCorte(menu.mostrarCorte(), registro.getLista());                    
                    break;
                case 5:
                    // generar el archivo de reporte
                    menu.escribirReporte(registro.getLista());
                    System.out.println("Bye");            
                    exit = true;
            }
            // repetir el menu hasta que el usuario decida    
        } while (!exit);
        
        
    }
    
}
