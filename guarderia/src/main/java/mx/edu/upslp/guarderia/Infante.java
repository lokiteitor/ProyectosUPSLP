package mx.edu.upslp.guarderia;

import java.util.Date;
import mx.edu.upslp.guarderia.datos.Grupo;

/**
 *
 * @author David Delgado Hernandez 150205@upslp.edu.mx
 */

/**
 * 
 * Clase que representa un infante, almacena sus datos y registra entrada y salida
 */
public class Infante {
    // la siguientes variables son privadas siguiendo el concepto de encapsulacion
    private int id;
    private Date date; 
    private String salida;
    private String entrada;
    private final int idPadre;
    private final Grupo grupo;
    private final String nombre;
    private boolean adentro;
    private boolean asistio;
    private int telefono;
    private final String nombrePadre;
    private int years;
    private int meses;
    private int sexo;
    
    /**
     * Constructor de un objeto infante
     * @param id id del infante
     * @param idPadre id del padre
     * @param grupo grupo al que pertenece
     * @param nombre nombre del infante
     * @param telefono telefono de su casa
     * @param nombrePadre nombre del padre
     * @param years edad en años 
     * @param meses edad en meses
     * @param sexo  sexo del infante
     */
    public Infante(int id, int idPadre, Grupo grupo,String nombre,int telefono,
        String nombrePadre,int years,int meses,int sexo) {
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
    
    /**
     * Registra la entrada de un infante
     * @return boolean - true si la entrada tuvo exito
     */
    public boolean registrarEntrada(){
        boolean response = false;
        // crear un registro de entrada        
        if (this.isAdentro()) {
            System.out.println("El alumno ya registro su entrada");
        }
        else{
            // cambiar el estado del alumno
            this.setAdentro(true);
            this.setAsistio(true);
            this.setEntrada(getDate().toLocaleString());
            response = true;
        }
        return response;
    }
    
    /**
     * Registra la salida del alumno
     * @return boolean - true si el registro tuvo exito
     */
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
     * Metodo estatico para poder asignar un infante a un grupo antes de crear el
     * objeto, devuelve null si el infante no se pudo ubicar
     * @param edad edad en años del infante
     * @return elemento de enumeracion Grupo 
     */
    public static Grupo ubicarGrupo(int edad){
        Grupo grp = null;
        
        for (Grupo grupo : Grupo.values()) {
            if (edad == grupo.getMin_edad()) {
                grp = grupo;
            }
        }
        
        return grp;
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
    public Grupo getGrupo() {
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
    public int getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(int telefono) {
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
