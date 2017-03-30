package mx.edu.upslp.guarderia;

import java.util.ArrayList;
import java.util.Date;
import mx.edu.upslp.guarderia.datos.Grupo;

import mx.edu.upslp.guarderia.validaciones.ValidarCadena;
import mx.edu.upslp.guarderia.validaciones.ValidarLista;
import mx.edu.upslp.guarderia.datos.Reporte;
/**
 * Clase que maneja las acciones del menu principal
 * @author David Delgado Hernandez 150205@upslp.edu.mx
 */
public class Menu {
    // finales por que los siguientes objetos no tiene que cambiar su 
    // referencia mas ademlante
    private Date date;
    private final ValidarCadena validar;
    private final ValidarLista vlista;
    private final Reporte reporte;
    
    /**
     * Constructo de la clase
     */
    public Menu(){
        this.vlista = new ValidarLista();
        date = new Date();
        validar = new ValidarCadena();
        reporte = new Reporte();
    }
    
    /**
     * imprime el mensaje de bienvenida
     */
    public void bienvenida(){
        date = new Date();
        System.out.println("Bienvenido al sistema");
        System.out.println("Fecha actual: " + date.toLocaleString() );        
    }    
    
    /**
     * Imprime en pantalla el menu principal y toma la opcion que elige el usuario
     * @return opcion ingresada por el usuario
     */
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
        date = new Date();
        boolean flag = true;
        // variables donde guardar temporalmente los datos
        int id , idPadre, telefono,years,meses,sexo;
        String nombre,nombrePadre;
        Grupo grupo;
        Infante infante;
        // preguntar los datos del infante y validarlos
        nombre = validar.isAlpha("Ingresa el nombre del niño");    

        // validar que el id del infante sea unico
        
        do {
            id = validar.isDigit("Ingrese el ID del infante");
            if (vlista.isUnico(lista, id)) {
                flag = false;
            }
        } while (flag);        
        
        idPadre = validar.isDigit("Ingrese el ID del Padre");        
        nombrePadre = validar.isAlpha("Ingrese el nombre del padre");
        telefono = validar.isDigit("Ingrese el telefono");        
        years = validar.isEdad("Ingrese la edad");
        meses = validar.isMeses("Ingrese los meses");
        sexo  = validar.isSexo("Ingrese el sexo");
        flag = true;
        // asignar un grupo
        grupo = Infante.ubicarGrupo(years);

        
        // crear el objecto
        infante = new Infante(id, idPadre, grupo, nombre,telefono,nombrePadre,
                    years,meses,sexo);
        if (vlista.isUnico(lista, id) && vlista.samePadre(infante, idPadre)) {            
            // agregar al arraylist
            lista.add(infante);       
        }
        else{
            System.out.println("El id debe ser unico");
            reporte.registrarIncidencia(date.toLocaleString(), infante, "ID Repetido");
        }       
    }
    /**
     * Busca el infante dentro del registro y evalua si los datos de ID son validos 
     * devuelve null si el infante no se encuentra o los datos osn invalidos
     * @param lista
     * @return instancia de Infante que se esta buscando
     */
    private Infante buscarInfante(ArrayList<Infante> lista){
        int id, idPadre;
        Infante busqueda;
        // validar datos
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
    
    
    /**
     * registrar la entrada de un niño validando los datos y cambiando su estado
     * @param lista lista sobre la que registrar la entrada
     */
    public void registrarEntrada(ArrayList<Infante> lista){
        date = new Date();
        boolean exist = false;
                
        // buscar el Infante
        Infante infante = this.buscarInfante(lista);
        
        // si el infante existe imprimir su informacion
        if (infante != null) {
            if (infante.registrarEntrada()) {
                System.out.println("Entrada Registrada");                
                System.out.println("Nombre: " + infante.getNombre());
                System.out.println("Grupo: " + infante.getGrupo());                
                System.out.println("Fecha: " + infante.getEntrada());                
            }
            else{
                // caso contrario registrar la incidencia
                reporte.registrarIncidencia(date.toLocaleString(), infante, "Error al registrar la entrada");
            }
            exist = true;

        }
        else{
            reporte.registrarIncidencia(date.toLocaleString() , "No se encontro al infante");
        }
        
        if (!exist) {
            System.out.println("El Infante no existe o lo ID no coinciden");
            reporte.registrarIncidencia(date.toLocaleString(), "El infante no existe o no coinciden los ID");
        }                
    }
    
    /**
     * registrar la salida del infante validando sus datos dentro de la lista 
     * @param lista registro de infantes
     */
    public void registrarSalida(ArrayList<Infante> lista){
        date = new Date();
        boolean exist = false;
        // buscar el infante
        Infante infante = this.buscarInfante(lista);
        
        if (infante != null) {
            if (infante.registrarSalida()) {
                // imprimir los datos de salida
                System.out.println("Salida de " + infante.getNombre() + " registrada");
                System.out.println("Fecha: " + infante.getSalida());                
            }
            else{
                // reportar la incidencia
                reporte.registrarIncidencia(date.toLocaleString(), "Error al registrar la salida");
            }
            exist = true;
        }     

        if (!exist) {
            System.out.println("El Infante no existe o lo ID no coinciden");
            reporte.registrarIncidencia(date.toLocaleString(), "El Infante no existe o lo ID no coinciden");
        }               
    }
    
    /**
     * Mostrar el menu de opciones de corte retornar la opcion elegida por el 
     * usuario
     * @return opcion ingresada por el usuario
     */
    public int mostrarCorte(){
        int opc;
        System.out.println("Que accion desea realizar");
        System.out.println("1- Mostrar listado de niños que asistieron");
        System.out.println("2- Mostrar el total de asistencias");
        System.out.println("3- Incidencias");
        opc = validar.opcionValida("", 3);
        return opc;        
    }
    
    /**
     * crear el reporte seleccionado en opc a partir del registro de infantes
     * @param opc opcion del menu ingresada por el infante
     * @param lista lista sobre la que generar los reportes
     */
    public void crearCorte(int opc,ArrayList <Infante> lista){
        // cargar la lista al reporte para generar datos
        reporte.setLista(lista);
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
    
    /**
     * Generar el reporte y escribirlo en un archivo
     * @param lista lista sobre la que escribir el reporte
     */
    public void escribirReporte(ArrayList <Infante> lista){
        date = new Date();
        // generar un reporte con los cortes en un archivo de texto
        reporte.setLista(lista);
        reporte.generarReporte(date.toLocaleString());
    }
       
}
