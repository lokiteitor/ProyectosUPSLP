/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.upslp.guarderia.validaciones;

import java.util.ArrayList;
import mx.edu.upslp.guarderia.Infante;

/**
 *
 * @author lokiteitor
 */
public class ValidarLista {
    /**
     * @param lista lista sobre la que agregar
     * @param elemento elemento a comparar
     * @return 
     */
    public boolean isUnico(ArrayList<Infante> lista,Infante elemento){
        boolean response = true;
        
        for (Infante infante : lista) {
            if (infante.getId() == elemento.getId() ) {
                response = false;
                break;
            }
            if (infante.getIdPadre() == elemento.getIdPadre()) {
                response = false;
                break;
            }
        }               
        return response;
    }
    
    
}
