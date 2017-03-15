/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.upslp.guarderia;

import java.util.Date;

/**
 *
 * @author lokiteitor
 */
public class Infante {
    
    private int id;
    private Date date; 
    private String salida;
    private String entrada;
    private final int idPadre;
    private final String grupo;
    private final String nombre;
    private boolean adentro;
    private boolean asistio;
    private String telefono;
    private final String nombrePadre;
    private int years;
    private int meses;
    private int sexo;
    

    public Infante(int id, int idPadre, String grupo,String nombre,String telefono,
        String nombrePadre,int years,int meses,int sexo) {
        // @todo revisar que los datos sean unicos
        this.id = id;
        this.idPadre = idPadre;
        this.grupo = grupo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.nombrePadre = nombrePadre;
        this.years = years;
        this.meses = meses;
        this.sexo = sexo;
        // iniciar el objecto de fechas
        date = new Date();                
    }
    
    public boolean registrarEntrada(){
        boolean response = false;
        // crear un registro de entrada        
        if (this.isAdentro()) {
            System.out.println("El alumno ya registro su entrada");
        }
        else{
            this.setAdentro(true);
            this.setAsistio(true);
            this.setEntrada(getDate().toLocaleString());
            response = true;
        }
        return response;
    }
    
    public boolean registrarSalida(){
        boolean response = false;
        // crear registro de salida
        if (!this.isAdentro()) {
            System.out.println("El alumno no ha registrado su entrada");
        }
        else{
            this.setSalida(getDate().toLocaleString());
            this.setAdentro(false);
            response = true;
        }
        
        return response;
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

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the adentro
     */
    public boolean isAdentro() {
        return adentro;
    }

    /**
     * @param adentro the adentro to set
     */
    public void setAdentro(boolean adentro) {
        this.adentro = adentro;
    }

    /**
     * @return the asistio
     */
    public boolean isAsistio() {
        return asistio;
    }

    /**
     * @param asistio the asistio to set
     */
    public void setAsistio(boolean asistio) {
        this.asistio = asistio;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the nombrePadre
     */
    public String getNombrePadre() {
        return nombrePadre;
    }

    /**
     * @return the years
     */
    public int getYears() {
        return years;
    }

    /**
     * @param years the years to set
     */
    public void setYears(int years) {
        this.years = years;
    }

    /**
     * @return the meses
     */
    public int getMeses() {
        return meses;
    }

    /**
     * @param meses the meses to set
     */
    public void setMeses(int meses) {
        this.meses = meses;
    }

    /**
     * @return the sexo
     */
    public int getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(int sexo) {
        this.sexo = sexo;
    }
       
}
