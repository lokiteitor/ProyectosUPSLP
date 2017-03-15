/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guarderia;

import java.util.Date;

/**
 *
 * @author lokiteitor
 */
public class Infante {
    
    private final int id;
    private final Date date; 
    private String salida;
    private String entrada;
    private final int idPadre;
    private final String grupo;

    public Infante(int id, int idPadre, String grupo) {
        // @todo revisar que los datos sean unicos
        this.id = id;
        this.idPadre = idPadre;
        this.grupo = grupo;
        // iniciar el objecto de fechas
        date = new Date();                
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the salida
     */
    public String getSalida() {
        return salida;
    }

    /**
     * @param salida the salida to set
     */
    public void setSalida(String salida) {
        this.salida = salida;
    }

    /**
     * @return the entrada
     */
    public String getEntrada() {
        return entrada;
    }

    /**
     * @param entrada the entrada to set
     */
    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    /**
     * @return the idPadre
     */
    public int getIdPadre() {
        return idPadre;
    }

    /**
     * @return the grupo
     */
    public String getGrupo() {
        return grupo;
    }
       
}
