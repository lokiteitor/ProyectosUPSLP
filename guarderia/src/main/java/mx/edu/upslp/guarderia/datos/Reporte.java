
package mx.edu.upslp.guarderia.datos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import mx.edu.upslp.guarderia.Infante;

/**
 * Clase encargada de la generacion de  reportes
 * @author David Delgado Hernandez 150205@upslp.edu.mx
 */
public class Reporte {
    
    private ArrayList<Infante> lista;
    private ArrayList<String> incedencias = new ArrayList<String>();
    private FileWriter archivo;
    private BufferedWriter buffer;
    private PrintWriter salida;
    
    /**
     * Constructor , acepta una Arraylist de infantes para generar el reporte
     * @param lista lista de infantes sobre la que registrar los reportes
     */
    
    public Reporte(ArrayList<Infante> lista){        
        this.lista = lista;
    }
    
    /**
     * Constructor vacio
     */
    public Reporte(){        
    }
    
    
    /**
     * Escribe los reportes en la ruta marcada por path
     * @param path - ruta donde almacenar el reporte
     */
    public void generarReporte(String path){
        
        try{
            archivo = new FileWriter(path+".txt");
            buffer = new BufferedWriter(archivo);
            salida = new PrintWriter(buffer);
            // obtener los reportes y escribirlos en el archivo
            salida.println(getReporteAsistencias());
            salida.println(getReporteTotalAsistencia());
            salida.println(getIncedencias());
            salida.close();                        
        }
        catch (IOException e){
            System.out.println("Error al crear el archivo"); 
        }
    }
    /**
     * Obtener una cadena con el corte de infantes que asistieron
     * @return 
     */
    private String getReporteAsistencias(){
        String reporte = "";
        
        for (Infante infante : getLista()) {
            if (infante.isAsistio()) {
                // concatener el reporte a los anteriores
                reporte += 
                        "Alumno: " + infante.getNombre() +"Grupo: " + infante.getGrupo() 
                        + "\n" + "ID Infante" + infante.getId() +
                        "\n" + "ID Padre: " + infante.getIdPadre() + " Nombre: "
                        + infante.getNombrePadre() + "\nTelefono: " + infante.getTelefono()+
                        "\nAños: " + infante.getYears() + " Meses: " + infante.getMeses()
                        + "\nSexo: " + infante.getSexo() + "\nEntrada: " + infante.getEntrada();                        
                
                if (!infante.isAdentro()) {
                    reporte += "\nSalida: " + infante.getSalida();
                } 
                reporte += "\n";
            }
        }                                        
        return reporte;
    }
    
    /**
     * obtener una cadena con el corte de total de asistencias he inasistencias
     * @return 
     */
    private String getReporteTotalAsistencia(){
        String reporte = "";
        int asistencias = 0;
        int faltas = 0;
        
        for (Infante infante : lista) {
            if (infante.isAsistio()) {
                asistencias++;
            }
            else{
                faltas++;
            }
        }
        
        reporte = "Asistencias: " + asistencias + " Faltas: " + faltas + "\n";        
        return reporte;
    }
    
    /**
     * obtener una cadena con el corte de incidencias marcadas por hora y tipo 
     * de incidencia a forma de log
     * @return 
     */
    private String getReporteIncidencias(){
        String reporte = "";
        if (incedencias.size() != 0) {
            for (int i = 0; i < incedencias.size(); i++) {
                reporte += incedencias.get(i) + "\n";
            }            
        }        
        return reporte;
    }
    
    /**
     * Imprime en la salida estandar el reporte de asistencias
     */
    public void crearReporteAsistencia(){
        
        System.out.println(this.getReporteAsistencias());
        
    }
    
    /**
     * Imprime en la salida estandar el total de asistencias e inasistencias
     */
    public void crearReporteTotalAsistencia(){

        System.out.println(this.getReporteTotalAsistencia());
        
    }
    
    /**
     * imprime en la salida estandar el reporte de incidencias
     */
    public void crearReporteIncidencias(){
        System.out.println(this.getReporteIncidencias());
    }
    
    /**
     * registra una incidencia basandose en su fecha, infante involucrado y mensaje
     * @param fecha fecha de la incidencia
     * @param infante instancia del infante involucrado 
     * @param mensaje mensaje a reportar
     */
    public void registrarIncidencia(String fecha,Infante infante,String mensaje){
        String incidencia = fecha;
        if (infante != null) {
            incidencia += " " + infante.getNombre() + " " +mensaje;
        }
        
        incedencias.add(incidencia);
    }
    
    /**
     *  registra una incidencia basandose en la fecha y mensaje
     * @param fecha fecha de la incidencia
     * @param mensaje mensaje a reportar
     */
    public void registrarIncidencia(String fecha,String mensaje){
        String incedencia = fecha + " " +  mensaje;
        incedencias.add(incedencia);
    }    

    
    /**
     * @return the incedencias
     */
    public ArrayList<String> getIncedencias() {
        return incedencias;
    }

    /**
     * @param incedencias the incedencias to set
     */
    public void setIncedencias(ArrayList<String> incedencias) {
        this.incedencias = incedencias;
    }       

    /**
     * @return the lista
     */
    public ArrayList<Infante> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ArrayList<Infante> lista) {
        this.lista = lista;
    }   
}
