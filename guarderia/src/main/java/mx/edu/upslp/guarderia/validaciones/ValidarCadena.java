/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.upslp.guarderia.validaciones;
import java.util.Scanner;
/**
 *
 * @author lokiteitor
 */
public class ValidarCadena {
    
    private final Scanner scan;
    
    public ValidarCadena(){
        scan = new Scanner(System.in);
    }
    
    public int opcionValida(String mensaje,int valorMaximo){
        boolean flag = true;
        String entrada;
        System.out.println(mensaje);
        do {
            entrada = scan.nextLine();
            
            if (entrada.matches("^[0-9]*$")) {
                if (Integer.parseInt(entrada) > 0 && Integer.parseInt(entrada) <= valorMaximo) {
                    flag = false;
                }
                else{
                    System.out.println("opcion Invalida");
                }
            }
            else{
                System.out.println("La opcion debe ser un numero de 0 a " + valorMaximo);
            }
        } while (flag);      
        return Integer.parseInt(entrada);
    }
    
    /**
     *  Este metodo valida que la entrada de usuario sea un numero
     * @param mensaje mensaje a imprimir
     * @return entero con el numero ingresado
     */
    public int isDigit(String mensaje){
        boolean flag = true;
        String entrada;
        System.out.println(mensaje);
        do {
            entrada = scan.nextLine();
            
            if (entrada.matches("^[0-9]*$")) {
                flag = false;
            }
            else{
                System.out.println("La entrada debe ser un numero");
            }
        } while (flag);      
        return Integer.parseInt(entrada);        
        
    }
    
    /**
     * Revisa que la entrada del usuario contenga solamente caracteres alphanumericos
     * @param mensaje mensaje a mostrar al usuario
     * @return String entrada del usuario
     */
    public String isAlpha(String mensaje){
        boolean flag = true;
        String entrada;
        System.out.println(mensaje);
        do {
            entrada = scan.nextLine();
            //@todo revisar que solo sean letras
            if (entrada.matches("^[A-Za-z]*$")) {
                flag = false;
            }
        } while (flag);      
        return entrada;
    }
    
}
