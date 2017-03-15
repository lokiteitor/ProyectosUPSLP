/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guarderia.validaciones;
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
}
