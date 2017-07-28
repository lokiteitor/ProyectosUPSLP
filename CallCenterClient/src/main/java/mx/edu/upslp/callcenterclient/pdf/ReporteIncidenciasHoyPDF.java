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

package mx.edu.upslp.callcenterclient.pdf;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * Genera el reporte en PDF de las incidencias del dia de hoy
 * @author David Delgado Hernandez 150205@upslp.edu.mx Programacion III Miercoles Horario: 2:00 - 4:00
 */
public class ReporteIncidenciasHoyPDF {
    private String separador = System.getProperty("file.separator");    
    private static final Font parrafoFont = FontFactory.getFont(FontFactory.COURIER,8);
    private static final Font TituloFont = FontFactory.getFont(FontFactory.COURIER,16);
    private Document documento;
    private PdfPTable table = new PdfPTable(5);
    private PdfPCell columnHeader;
    private Chapter capTabla = new Chapter(1);    
    
    private Long[] ids;
    private String[] tipos;
    private String[] importancias;
    private String[] usuarios;
    private String[] clientes;

    /**
     * @param ids the ids to set
     */
    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    /**
     * @param tipos the tipos to set
     */
    public void setTipos(String[] tipos) {
        this.tipos = tipos;
    }

    /**
     * @param importancias the importancias to set
     */
    public void setImportancias(String[] importancias) {
        this.importancias = importancias;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(String[] usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * @param clientes the clientes to set
     */
    public void setClientes(String[] clientes) {
        this.clientes = clientes;
    }
    
    
    /**
     * Genera la instancia del PDF
     * @param salida ruta de salida
     */
    public ReporteIncidenciasHoyPDF(File salida){
        // creamos el documento
        try{
            documento = new Document();
            // obtenemos el objeto para escribirlo
            PdfWriter.getInstance(documento, new FileOutputStream(salida));
            documento.open();

            
        }catch(FileNotFoundException e){
            System.err.println("El archivo o directorio no existe");
            System.err.println(e.getMessage());
        }catch(DocumentException e){
            System.err.println("Error al crear el documento");
            System.err.println(e.getMessage());
        }catch(NullPointerException e){
            System.err.println("No se puede acceder al archivo");
            System.err.println(e.getMessage());
        }
    }
    
    /**
     * escribe los cambios en el PDF
     */
    private void write(){        
        try{
            // insertamos los metadatos
            addMetadata();            
            // con el archivo abierto insertamos los datos
            addTitle();
            addTable();
        }catch(DocumentException e){
            System.err.println("Error al escribir en el documento");
            System.err.println(e.getMessage());
        }
    }
    /**
     * metodo de entrada para la creacion del PDF
     */
    public void createPDF(){
        write();
        documento.close();
    }
    /**
     * Agrega los metadatos al PDF
     */
    private void addMetadata(){
        documento.addTitle("Reporte de incidencias diario");
        documento.addAuthor("CallCenter");
        documento.addCreationDate();
    }
    /**
     * Agrega la tabla al archivo PDF
     * @throws DocumentException 
     */
    private void addTable() throws DocumentException{
        Chapter capTabla = new Chapter(1);
        Section stabla = capTabla.addSection("");        
        headerTitle();
        addContent();
        stabla.add(table);
        documento.add(capTabla);
    }
    
    /**
     * Agrega las cabezeras a la tabla
     */
    private void headerTitle(){
        columnHeader = new PdfPCell(new Phrase("ID",parrafoFont));
        columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(columnHeader);
        
        columnHeader = new PdfPCell(new Phrase("TIPO",parrafoFont));
        columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(columnHeader);

        columnHeader = new PdfPCell(new Phrase("IMPORTANCIA",parrafoFont));
        columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(columnHeader);
        
        columnHeader = new PdfPCell(new Phrase("USUARIO",parrafoFont));
        columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(columnHeader);

        columnHeader = new PdfPCell(new Phrase("CLIENTE",parrafoFont));
        columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(columnHeader);
        //table.setHeaderRows(1);
        
    }
    
    /**
     * Agrega el contenido a la tabla
     */
    private void addContent(){
        PdfPCell cell;
        for (int i = 0; i < ids.length; i++) {
            cell = new PdfPCell(new Phrase(String.valueOf(ids[i]),parrafoFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);           

            cell = new PdfPCell(new Phrase(String.valueOf(tipos[i]),parrafoFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(importancias[i]),parrafoFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);            
            
            cell = new PdfPCell(new Phrase(String.valueOf(usuarios[i]),parrafoFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);            
            
            cell = new PdfPCell(new Phrase(String.valueOf(clientes[i]),parrafoFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);            
        }
    }
    
    /**
     * Agrega la cabezera del PDF
     */
        
    private void addTitle(){
        
        Image logo;
        try{
            logo = Image.getInstance("imagenes"+separador+"logo.png");           
            capTabla.add(logo);
            System.out.println("ok");
        }catch(BadElementException e){
            System.err.println("Error al abrir el logo");
            System.err.println(e.getMessage());
        }catch(IOException e){
            System.err.println("Error de entrada/salida");
            System.err.println(e.getMessage());
        }
        
        Chunk title = new Chunk("\n\n",TituloFont);
        capTabla.add(title);
        capTabla.add(new Paragraph(""));                
    }
    
    
    /**
     * @return the ids
     */
    public Long[] getIds() {
        return ids;
    }

    /**
     * @return the tipos
     */
    public String[] getTipos() {
        return tipos;
    }

    /**
     * @return the importancias
     */
    public String[] getImportancias() {
        return importancias;
    }
    /**
     * @return the usuarios
     */
    public String[] getUsuarios() {
        return usuarios;
    }

    /**
     * @return the clientes
     */
    public String[] getClientes() {
        return clientes;
    }    
}
