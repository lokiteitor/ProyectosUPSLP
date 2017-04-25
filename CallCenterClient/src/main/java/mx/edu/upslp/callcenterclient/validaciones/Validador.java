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

package mx.edu.upslp.callcenterclient.validaciones;

/**
 *
 * @author David Delgado Hernandez 150205@upslp.edu.mx Programacion III Miercoles Horario: 2:00 - 4:00
 */
public class Validador {
    
    /**
     * Devuelve la cadena si solo contiene caracteres alfabeticos
     * devuelve null en caso contrario
     * @param objetivo cadena a validar
     * @return  true si la cadena es valida
     */
    public boolean isAlpha(String objetivo){         
        boolean response =  false;
        if (objetivo.matches("^[a-zA-Z]*$")) {
            response = true;
        }        
        return response;
    }
    /**
     * Devuelve la cadena si solo contiene caracteres alfabeticos y espacios
     * devuelve null en caso contrario
     * @param objetivo cadena a validar
     * @return  true si la cadena es valida
     */
    public boolean isAlphaSpace(String objetivo){                
        boolean response =  false;
        if (objetivo.matches("^[a-zA-Z]*$")) {
            response = true;
        }        
        return response;
    }    
    
    
    /**
     * Devuelve un entero si la entrada es valida null en caso contrario
     * @param objetivo cadena a validar
     * @return cadena convertida a entero, -1 en caso contrario
     */
    public int isInteger(String objetivo){
        int response;
        
        try{
            response = Integer.parseInt(objetivo);
        }catch(NumberFormatException e){
            response = -1;
        }        
        return response;
        
    }
    
    /**
     * Valida que el objetivo pueda convertirse en flotante
     * @param objetivo cadena a validar
     * @return el valor de la cadena en flotante, -1 si es invalida
     */
    public float isFloat(String objetivo){
        float response;
        
        try{
            response = Float.parseFloat(objetivo);
        }catch(NumberFormatException e){
            response = -1;
        }
        return response;
    }
    
    public boolean isEmail(String objetivo){
        boolean response = true;
        if (!objetivo.matches("^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$")) {
            response = false;
        }
        
        return response;
    }
    
    
}
