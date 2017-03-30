package mx.edu.upslp.guarderia.datos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import mx.edu.upslp.guarderia.Infante;

/**
 * clase encargada de llevar un registro de infantes
 * @author David Delgado Hernandez 150205@upslp.edu.mx
 */
public class Registro {
    // finales ya que son clases cuya referencia no va a cambiar
    // en un principio Gson se utilizaria para guardar los datos de los infantes
    // entre secciones
    private final Gson gson;
    private GsonBuilder builder = new GsonBuilder();
    private final String path;
    private ArrayList<Infante> lista = new ArrayList<Infante>();
    
    /**
     * Constructor, recive la ruta en la que se guardara el archivo con los datos 
     * del programa a fin de obtener persistencia
     * @param path  ruta donde guardar la persistencia de datos
     */      
    public Registro(String path) {
        
        this.gson = builder.setPrettyPrinting().create();
        this.path = path;
    }

    /**
     * @return the gson
     */
    public Gson getGson() {
        return gson;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
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
