/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.edu.upslp.guarderia.datos;

/**
 * Enumeracion que lleva un registro de los grupos en la guarderia
 * @author David Delgado Hernandez 150205@upslp.edu.mx
 */
public enum Grupo {
    a("Grupo a",0,12,0,1),
    b("Grupo b",0,12,1,2),
    c("Grupo c",0,12,2,3),
    sinAsignacion("Sin asignacion",0,12,3,99);
    
    // identificador del grupo
    private String grupo;
    // rango de edades
    private int min_meses;
    private int max_meses;
    private int min_edad;
    private int max_edad;
    
    private Grupo(String grupo, int min_meses, int max_meses, int min_edad, int max_edad) {
        this.grupo = grupo;
        this.min_meses = min_meses;
        this.max_meses = max_meses;
        this.min_edad = min_edad;
        this.max_edad = max_edad;
    }

    /**
     * @return the grupo
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * @return the min_meses
     */
    public int getMin_meses() {
        return min_meses;
    }

    /**
     * @return the max_meses
     */
    public int getMax_meses() {
        return max_meses;
    }

    /**
     * @return the min_edad
     */
    public int getMin_edad() {
        return min_edad;
    }

    /**
     * @return the max_edad
     */
    public int getMax_edad() {
        return max_edad;
    }
    
    
    
}
