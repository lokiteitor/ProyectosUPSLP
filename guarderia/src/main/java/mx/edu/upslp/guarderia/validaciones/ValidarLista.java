package mx.edu.upslp.guarderia.validaciones;

import java.util.ArrayList;
import mx.edu.upslp.guarderia.Infante;

/**
 *
 * @author David Delgado Hernandez 150205@upslp.edu.mx
 */

/**
 * Clase encargada de validar los datos dentro de el registro de infantes
 * 
 */
public class ValidarLista {
    /**
     * Devuelve el true si el id del infante es unico
     * @param lista lista sobre la que agregar
     * @param id id a comparar
     * @return boolean retorna true si el id es unico
     */
    public boolean isUnico(ArrayList<Infante> lista,int id){
        boolean response = true;
                
        for (Infante infante : lista) {
            if (infante.getId() == id) {
                response = false;
                break;
            }
        }                    
        return response;
    }
    
    /**
     * Devuelve true si el id del padre corresponde al registrado por el hijo
     * @param elemento Infante - hijo a validar
     * @param id int - id del padre a validar
     * @return boolean retorna true si el id del padre coincide 
     */
    public boolean samePadre(Infante elemento,int id){
        boolean response = false;
        if (elemento.getId() == id) {
            response = true;
        }        
        return response;
    }
    
}
