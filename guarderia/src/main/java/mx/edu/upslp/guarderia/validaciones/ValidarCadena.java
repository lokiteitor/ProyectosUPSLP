
package mx.edu.upslp.guarderia.validaciones;
import java.util.Scanner;
import mx.edu.upslp.guarderia.datos.Grupo;
/**
 *
 * @author David Delgado Hernandez 150205@upslp.edu.mx
 */
public class ValidarCadena {
    // instancia encargada de la entrada de usuario
    private final Scanner scan;
    
    /**
     * Constructor, toma como entrada de datos la entrada estandar
     */
    public ValidarCadena(){
        scan = new Scanner(System.in);
    }
    
    
    /**
     * Evalua si la opcion ingresada es valida y se encuentra dentro del rango 
     * establecida
     * @param mensaje mensaje a mostrar para requerir la entrada de usuario
     * @param valorMaximo rango maximo de opciones
     * @return retorna la opcion ingresa por el usuario
     */
    public int opcionValida(String mensaje,int valorMaximo){
        boolean flag = true;
        String entrada;
        // imprime el mensaje de solicitud
        System.out.println(mensaje);
        do {
            // repite el proceso hasta que el usuario ingrese los datos correctos
            entrada = scan.nextLine();
            // utilizando Regex valida que solo sea digitos
            if (entrada.matches("^[0-9]*$")) {
                // evalua el rango
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
        // retorna un entero representante del numero de opcion
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
        // imprime el mensaje de solicitud
        System.out.println(mensaje);
        do {
            // repite el ciclo hasta que la entrada sea valida
            entrada = scan.nextLine();
            // retira todos los espacios de la entrada de usuario
            entrada.replaceAll("\\s", "");
            // valida que sea un numero y que la cadena sea mas grande que 0
            if (entrada.matches("^[0-9]*$") && entrada.length() > 0) {
                flag = false;
            }
            else{
                System.out.println("La entrada debe ser un numero");
            }
        } while (flag);      
        // retorna el entero
        return Integer.parseInt(entrada);        
        
}
    
    /**
     * Revisa que la entrada del usuario contenga solamente caracteres alfabeticos
     * @param mensaje mensaje a mostrar al usuario
     * @return String entrada del usuario
     */
    public String isAlpha(String mensaje){
        boolean flag = true;
        String entrada;
        System.out.println(mensaje);
        do {
            entrada = scan.nextLine();
            entrada = entrada.trim();
            //@todo revisar que solo sean letras
            // utilizando Regex valida que solo sean letras o espacios
            if (entrada.matches("^[A-Za-z ]*$") && entrada.length() > 0) {
                flag = false;
            }
            else{
                System.out.println("La entrada deben ser solo caracteres");
            }
        } while (flag);      
        // retorna la entrada de usuario
        return entrada;
    }
    
    /**
     * Validar que la entrada este dentro de un rango valido
     * @param mensaje mensaje a mostrar para requerir la edad
     * @return retorna true si la edad es valida
     */
    public int isEdad(String mensaje){
        int edad;
        boolean flag = true;
        
        
        do {
            // primero valida que sea un numero
            edad = this.isDigit(mensaje);
            if (edad < 3 && edad >= 0 ) {
                flag = false;
            }            
            else{
                System.out.println("La edad debe estar en un rango de 0 a 2");
            }
        } while (flag);
        
        
        return edad;
    }
    
    /**
     * valida que el rango de meses este dentro del rango valido
     * @param mensaje mensaje mostrado para requerir la edad en meses
     * @return retorna true si la edad en meses es valida
     */
    public int isMeses(String mensaje){
        int meses;
        boolean flag = true;
        
        
        do {
            // valida que sea un numero
            meses = this.isDigit(mensaje);
            if (meses <= 12 && meses >= 0 ){
                flag = false;
            }            
            else{
                System.out.println("La edad debe estar en un rango de 0 a 12");
            }
        } while (flag);
        
        
        return meses;
    }
    
    /**
     * Valida que la opcion del sexo este dentro de 1 o 2
     * @param mensaje mensaje mostrado para requerir el sexo
     * @return retorna true si el sexo es valido
     */
    public int isSexo(String mensaje){
        int sexo;
        boolean flag = true;
        
        
        do {
            System.out.println("1- Masculino\n2- Femenino");
            sexo = this.isDigit(mensaje);            
            if (sexo == 1 || sexo == 2) {
                flag = false;
            }
            else{
                System.out.println("La edad debe de estar en un rango de 1 o 2");
            }
        } while (flag);
        
        
        return sexo;
    }
        
}
