/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.upslp.guarderia;

import java.util.ArrayList;
import java.util.Date;

import mx.edu.upslp.guarderia.validaciones.ValidarCadena;
import mx.edu.upslp.guarderia.validaciones.ValidarLista;
import mx.edu.upslp.guarderia.datos.Reporte;
/**
 *
 * @author lokiteitor
 */
public class Menu {
    
    private final Date date;
    private String fecha;
    private String hora;    
    private final ValidarCadena validar;
    private final ValidarLista vlista;
    private Reporte reporte;
    
    public Menu(){
        this.vlista = new ValidarLista();
        date = new Date();
        validar = new ValidarCadena();
    }
    
    public void bienvenida(){
        
        System.out.println("Bienvenido al sistema");
        System.out.println("Fecha actual: " + date.toLocaleString() );        
    }    
    
    public int menuPrincipal(){
        int opc;
        System.out.println("Que accion desea realizar");
        System.out.print("1- Alta de niño\n"
            + "2- Registro entrada\n"
            + "3- Registro salida\n"
            + "4- Informe\n"
            + "5- Salir\n");
        
        opc = validar.opcionValida("", 5);                
        return opc;        
    }
    
    
    /**
     * Permite crear un objecto infante y agregarlo al array
     * @param lista Arraylist sobre el que agregar al infante
     *
     */
    public void altaInfante(ArrayList<Infante> lista){
        int id , idPadre;
        String grupo,nombre;
        Infante infante;
        // preguntar los datos del infante
        nombre = validar.isAlpha("Ingresa el nombre del niño");        
        id = validar.isDigit("Ingrese el ID del infante");
        idPadre = validar.isDigit("Ingrese el ID del Padre");
        grupo = validar.isAlpha("Ingrese el grupo al que pertenece");
        
        //@todo validar los datos
        // revisar que sea el unico                      
        // crear el objecto
        infante = new Infante(id, idPadre, grupo, nombre);
        if (vlista.isUnico(lista, infante)) {            
            // agregar al arraylist
            lista.add(infante);       
        }
        else{
            System.out.println("El id debe ser unico");
        }       
    }
    
    private Infante buscarInfante(ArrayList<Infante> lista){
        int id, idPadre;
        Infante busqueda;
        
        id = validar.isDigit("Ingrese el ID del alumno");
        idPadre = validar.isDigit("Ingrese el ID del padre");
        busqueda = null;
        // buscar el Infante
        for (Infante infante : lista) {
            if (infante.getId() == id && infante.getIdPadre() == idPadre) {
                busqueda = infante;
            }
        }         
        return busqueda;
        
    }
    
    public void registrarEntrada(ArrayList<Infante> lista){
        boolean exist = false;
                
        // buscar el Infante
        Infante infante = this.buscarInfante(lista);
        
        if (infante != null) {
            if (infante.registrarEntrada()) {
                System.out.println("Entrada Registrada");                
                System.out.println("Nombre: " + infante.getNombre());
                System.out.println("Grupo: " + infante.getGrupo());                
                System.out.println("Fecha: " + infante.getEntrada());                
            }           
            exist = true;

        }
        
        if (!exist) {
            System.out.println("El Infante no existe o lo ID no coinciden");
        }                
    }
    
    public void registrarSalida(ArrayList<Infante> lista){
        boolean exist = false;
        // buscar el infante
        Infante infante = this.buscarInfante(lista);
        
        if (infante != null) {
            if (infante.registrarSalida()) {
                System.out.println("Salida de " + infante.getNombre() + " registrada");
                System.out.println("Fecha: " + infante.getSalida());                
            }
            exist = true;
        }     

        if (!exist) {
            System.out.println("El Infante no existe o lo ID no coinciden");
        }               
    }
    
    public int mostrarCorte(){
        int opc;
        System.out.println("Que accion desea realizar");
        System.out.println("1- Mostrar listado de niños que asistieron");
        System.out.println("2- Mostrar el total de asistencias");
        System.out.println("3- Incidencias");
        opc = validar.opcionValida("", 3);
        return opc;        
    }
    
    public void crearCorte(int opc,ArrayList <Infante> lista){
        reporte = new Reporte(lista);
        switch(opc){
            case 1:
                reporte.crearReporteAsistencia();
                break;
            case 2:
                reporte.crearReporteTotalAsistencia();
                break;
            case 3:
                reporte.crearReporteIncidencias();
                break;
        }
    }
       
}
