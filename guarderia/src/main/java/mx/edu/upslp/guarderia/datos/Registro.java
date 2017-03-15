/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.upslp.guarderia.datos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import mx.edu.upslp.guarderia.Infante;

/**
 *
 * @author lokiteitor
 */
public class Registro {
    
    private final Gson gson;
    private GsonBuilder builder = new GsonBuilder();
    private final String path;
    private ArrayList<Infante> lista = new ArrayList<Infante>();

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
