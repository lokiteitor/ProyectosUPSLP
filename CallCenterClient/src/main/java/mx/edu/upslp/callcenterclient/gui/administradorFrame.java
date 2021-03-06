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
package mx.edu.upslp.callcenterclient.gui;

import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.View;
import mx.edu.upslp.callcenterclient.gui.datos.Estadisticas;
import mx.edu.upslp.callcenterclient.gui.datos.Movimiento;
import mx.edu.upslp.callcenterclient.gui.datos.Usuario;
import mx.edu.upslp.callcenterclient.pdf.ReporteIncidenciasDiaPDF;
import mx.edu.upslp.callcenterclient.pdf.ReporteIncidenciasHoraPDF;
import mx.edu.upslp.callcenterclient.pdf.ReporteIncidenciasHoyPDF;
import mx.edu.upslp.callcenterclient.validaciones.MyException;
import mx.edu.upslp.callcenterclient.validaciones.Validador;
import mx.edu.upslp.callserver.usuario.UsuarioEJB;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;


/**
 * Este Frame muestra los controles el administrador
 * @author David Delgado Hernandez 150205@upslp.edu.mx
 */
public class administradorFrame extends javax.swing.JFrame {
    private int page = 1;
    private int movPage = 1;
    private UsuarioEJB miUsuario;
    private Validador validador = new Validador();
    private JDateChooser calendario = new JDateChooser();
    private String[] usuariosTitle = new String[] {"Username","Nombre","Apellido"};
    private String[] movUsuarioTitle = new String[] {"Username","Nombre","Apellido"};
    private String[] movMovimientosTitle = new String[] {"Folio Incidencia","Tipo","Fecha"};
    private Usuario usuarioManager = new Usuario();
    private Movimiento movManager = new Movimiento();
    private Estadisticas estadistica = new Estadisticas();
    private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatoAV = new SimpleDateFormat("dd/MM/yyyy hh:mm");    
    private Date fechaMaxima = new Date();
    private TimePicker  filtroHora = new  TimePicker();
    
    private JDateChooser filtroDia = new JDateChooser();
    
    // graficas
    private DefaultPieDataset totalIncidencias = new DefaultPieDataset();
    private DefaultCategoryDataset usuariosActivos = new  DefaultCategoryDataset();
    private JFreeChart grafico;
    private ChartPanel graficoChartPanel;
    
    // modificamos el modelo en su creacion
    private DefaultTableModel usuariosModel = new DefaultTableModel(){
        boolean[] editColum = {false,false,false};
        // hacemos que las columnas no sea editables
        public boolean isCellEditable(int indFila,int indCol){
            return editColum[indCol];
        }
    };

    private DefaultTableModel movimientosUsuario = new DefaultTableModel(){
        boolean[] editColum = {false,false,false};
        // hacemos que las columnas no sea editables
        public boolean isCellEditable(int indFila,int indCol){
            return editColum[indCol];
        }
    };

    private DefaultTableModel movimientosDetalles = new DefaultTableModel(){
        boolean[] editColum = {false,false,false,false};
        // hacemos que las columnas no sea editables
        public boolean isCellEditable(int indFila,int indCol){
            return editColum[indCol];
        }
    };    

    
    /**
     * Inicializa los componentes del frame
     */
    public administradorFrame(UsuarioEJB miUsuario) {
        this.miUsuario = miUsuario;
        fechaMaxima.setTime(fechaMaxima.getTime() - (long)(5.676E11));
        
        initComponents();
        // agregar el calendario a pantalla
        calendario.setEnabled(true);
        calendario.setVisible(true);
        calendarPanel.setLayout(new BoxLayout(calendarPanel, View.Y_AXIS));
        calendarPanel.add(calendario);
        
        // crear su manejador de eventos
        // restar 18 años
        Date fecha = new Date();
        calendario.setMaxSelectableDate(fechaMaxima);
        calendario.setDate(fechaMaxima);
        // filtro
        filtroHora.setTime(LocalTime.now());
        filtroHora.setEnabled(true);
        filtroHora.setVisible(true);
        panelFiltro.setLayout(new BoxLayout(panelFiltro, View.Y_AXIS));        
        panelFiltro.add(filtroHora);
        
        this.setResizable(false);
        this.setTitle("Asistencia de Quejas y sugerencias");
        this.setLocationRelativeTo(null);
        
        // ocultar letros de warning de la primera tab
        wnombreLabel.setVisible(false);
        wapellidoLabel.setVisible(false);
        wnacionalidadLabel.setVisible(false);
        wcorreoLabel.setVisible(false);
        // panel de graficos
        graficaPanel.setLayout(new BorderLayout());
        
        filtroDia.setMaxSelectableDate(new Date());
        filtroDia.setEnabled(true);
        filtroDia.setVisible(true);
        panelDiaFiltro.setLayout(new BoxLayout(panelDiaFiltro, View.Y_AXIS));
        panelDiaFiltro.add(filtroDia);
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField8 = new javax.swing.JTextField();
        tabs = new javax.swing.JTabbedPane();
        movimientoPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        reportesTable = new javax.swing.JTable();
        listaLabel = new javax.swing.JLabel();
        anteriorButton = new javax.swing.JButton();
        siguienteButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        movimientosTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        usuariosTab = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        usuariosTable = new javax.swing.JTable();
        usuariosLabel = new javax.swing.JLabel();
        usuariosAnteriorButton = new javax.swing.JButton();
        usuariosSiguienteButton = new javax.swing.JButton();
        usuariosNombreLabel = new javax.swing.JLabel();
        usuariosNombreField = new javax.swing.JTextField();
        usuariosApellidoLabel = new javax.swing.JLabel();
        usuariosApellidoField = new javax.swing.JTextField();
        usuariosFechaLabel = new javax.swing.JLabel();
        usuariosFechaField = new javax.swing.JTextField();
        usuariosNacionalidadLabel = new javax.swing.JLabel();
        usuariosNacionalidadField = new javax.swing.JTextField();
        usuariosTurnoLabel = new javax.swing.JLabel();
        usuariosAccesoLabel = new javax.swing.JLabel();
        usuariosAccesoField = new javax.swing.JTextField();
        usuariosCreacionLabel = new javax.swing.JLabel();
        usuariosCreacionField = new javax.swing.JTextField();
        usuariosActualizacionLabel = new javax.swing.JLabel();
        usuariosActualizacionField = new javax.swing.JTextField();
        seguridadPanel = new javax.swing.JPanel();
        usuariosPasswordLabel = new javax.swing.JLabel();
        usuariosPasswordField = new javax.swing.JPasswordField();
        usuariosDeleteLabel = new javax.swing.JLabel();
        usuariosDeleteButton = new javax.swing.JButton();
        actualizarPasswordButton = new javax.swing.JButton();
        usuariosTurnoField = new javax.swing.JTextField();
        seguridadLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        reporteTotalButton = new javax.swing.JButton();
        reporteUsuariosMButton = new javax.swing.JButton();
        reporteHoyButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        panelFiltro = new javax.swing.JPanel();
        filtroHoraButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        panelDiaFiltro = new javax.swing.JPanel();
        reporteDiaButton = new javax.swing.JButton();
        graficaPanel = new javax.swing.JPanel();
        altasPanel = new javax.swing.JPanel();
        nombreLabel = new javax.swing.JLabel();
        nombreField = new javax.swing.JTextField();
        apellidoLabel = new javax.swing.JLabel();
        apellidoField = new javax.swing.JTextField();
        fechaLabel = new javax.swing.JLabel();
        turnoLabel = new javax.swing.JLabel();
        turnoCombo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        nacionalidadLabel = new javax.swing.JLabel();
        accesoCombo = new javax.swing.JComboBox<>();
        wnombreLabel = new javax.swing.JLabel();
        wapellidoLabel = new javax.swing.JLabel();
        nacionalidadField = new javax.swing.JTextField();
        wnacionalidadLabel = new javax.swing.JLabel();
        calendarPanel = new javax.swing.JPanel();
        correoField = new javax.swing.JTextField();
        wcorreoLabel = new javax.swing.JLabel();
        enviarButton = new javax.swing.JButton();
        correoLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jTextField8.setText("jTextField8");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabs.setName("movimientos"); // NOI18N
        tabs.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabsStateChanged(evt);
            }
        });

        movimientoPanel.setName("reportes"); // NOI18N

        reportesTable.setModel(movimientosUsuario);
        reportesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportesTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(reportesTable);

        listaLabel.setText("Lista de Usuarios");

        anteriorButton.setText("Anterior");
        anteriorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anteriorButtonActionPerformed(evt);
            }
        });

        siguienteButton.setText("Siguiente");
        siguienteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteButtonActionPerformed(evt);
            }
        });

        movimientosTable.setModel(movimientosDetalles);
        jScrollPane3.setViewportView(movimientosTable);

        jLabel1.setText("Movimientos");

        javax.swing.GroupLayout movimientoPanelLayout = new javax.swing.GroupLayout(movimientoPanel);
        movimientoPanel.setLayout(movimientoPanelLayout);
        movimientoPanelLayout.setHorizontalGroup(
            movimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movimientoPanelLayout.createSequentialGroup()
                .addGroup(movimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(movimientoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(movimientoPanelLayout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(listaLabel)
                        .addGap(354, 354, 354)
                        .addComponent(jLabel1))
                    .addGroup(movimientoPanelLayout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(anteriorButton)
                        .addGap(18, 18, 18)
                        .addComponent(siguienteButton)))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        movimientoPanelLayout.setVerticalGroup(
            movimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movimientoPanelLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(movimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(listaLabel)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(movimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(movimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(anteriorButton)
                    .addComponent(siguienteButton))
                .addContainerGap(128, Short.MAX_VALUE))
        );

        tabs.addTab("Movimientos", movimientoPanel);

        usuariosTab.setName("listaUsuarios"); // NOI18N

        usuariosTable.setModel(usuariosModel);
        usuariosTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usuariosTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(usuariosTable);

        usuariosLabel.setText("Lista de Usuarios");

        usuariosAnteriorButton.setText("Anterior");
        usuariosAnteriorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuariosAnteriorButtonActionPerformed(evt);
            }
        });

        usuariosSiguienteButton.setText("Siguiente");
        usuariosSiguienteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuariosSiguienteButtonActionPerformed(evt);
            }
        });

        usuariosNombreLabel.setText("Nombre");

        usuariosNombreField.setEditable(false);

        usuariosApellidoLabel.setText("Apellido");

        usuariosApellidoField.setEditable(false);

        usuariosFechaLabel.setText("Fecha de Nacimiento");

        usuariosFechaField.setEditable(false);

        usuariosNacionalidadLabel.setText("Nacionalidad");

        usuariosNacionalidadField.setEditable(false);

        usuariosTurnoLabel.setText("Turno");

        usuariosAccesoLabel.setText("Nivel de acceso");

        usuariosAccesoField.setEditable(false);

        usuariosCreacionLabel.setText("Fecha de Creacion");

        usuariosCreacionField.setEditable(false);

        usuariosActualizacionLabel.setText("Ultima Actualizacion");

        usuariosActualizacionField.setEditable(false);

        seguridadPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));

        usuariosPasswordLabel.setText("Cambiar Contraseña");

        usuariosDeleteLabel.setText("Eliminar Usuario");

        usuariosDeleteButton.setText("Eliminar");
        usuariosDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuariosDeleteButtonActionPerformed(evt);
            }
        });

        actualizarPasswordButton.setText("Actualizar");
        actualizarPasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarPasswordButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout seguridadPanelLayout = new javax.swing.GroupLayout(seguridadPanel);
        seguridadPanel.setLayout(seguridadPanelLayout);
        seguridadPanelLayout.setHorizontalGroup(
            seguridadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seguridadPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(seguridadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(seguridadPanelLayout.createSequentialGroup()
                        .addComponent(usuariosPasswordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usuariosDeleteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(seguridadPanelLayout.createSequentialGroup()
                        .addGroup(seguridadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(actualizarPasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(seguridadPanelLayout.createSequentialGroup()
                                .addComponent(usuariosPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(usuariosDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        seguridadPanelLayout.setVerticalGroup(
            seguridadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seguridadPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(seguridadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usuariosPasswordLabel)
                    .addComponent(usuariosDeleteLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(seguridadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usuariosPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usuariosDeleteButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(actualizarPasswordButton)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        usuariosTurnoField.setEditable(false);

        seguridadLabel.setText("Area de Seguridad");

        javax.swing.GroupLayout usuariosTabLayout = new javax.swing.GroupLayout(usuariosTab);
        usuariosTab.setLayout(usuariosTabLayout);
        usuariosTabLayout.setHorizontalGroup(
            usuariosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usuariosTabLayout.createSequentialGroup()
                .addGroup(usuariosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(usuariosTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(usuariosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usuariosNombreLabel)
                            .addComponent(usuariosNombreField)
                            .addComponent(usuariosApellidoLabel)
                            .addComponent(usuariosApellidoField)
                            .addComponent(usuariosFechaLabel)
                            .addComponent(usuariosFechaField)
                            .addComponent(usuariosNacionalidadLabel)
                            .addComponent(usuariosNacionalidadField)
                            .addComponent(usuariosTurnoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(usuariosAccesoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(usuariosAccesoField)
                            .addComponent(usuariosTurnoField))
                        .addGroup(usuariosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(usuariosTabLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(usuariosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(usuariosTabLayout.createSequentialGroup()
                                        .addGroup(usuariosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(usuariosCreacionField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(usuariosCreacionLabel))
                                        .addGap(18, 18, 18)
                                        .addGroup(usuariosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(usuariosActualizacionLabel)
                                            .addComponent(usuariosActualizacionField, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(seguridadLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, usuariosTabLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(seguridadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(usuariosTabLayout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(usuariosLabel))
                    .addGroup(usuariosTabLayout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(usuariosAnteriorButton)
                        .addGap(34, 34, 34)
                        .addComponent(usuariosSiguienteButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        usuariosTabLayout.setVerticalGroup(
            usuariosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usuariosTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(usuariosLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(usuariosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(usuariosTabLayout.createSequentialGroup()
                        .addGroup(usuariosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usuariosNombreLabel)
                            .addComponent(usuariosCreacionLabel)
                            .addComponent(usuariosActualizacionLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(usuariosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usuariosNombreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usuariosCreacionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usuariosActualizacionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(usuariosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usuariosApellidoLabel)
                            .addComponent(seguridadLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(usuariosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(usuariosTabLayout.createSequentialGroup()
                                .addComponent(usuariosApellidoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(usuariosFechaLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(usuariosFechaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(usuariosNacionalidadLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(usuariosNacionalidadField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(usuariosTurnoLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(usuariosTurnoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(usuariosAccesoLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(usuariosAccesoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(seguridadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(usuariosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usuariosSiguienteButton)
                    .addComponent(usuariosAnteriorButton))
                .addContainerGap(123, Short.MAX_VALUE))
        );

        tabs.addTab("Lista de Usuarios", usuariosTab);

        jPanel3.setName("estadisticas"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        reporteTotalButton.setText("Reportes Totales");
        reporteTotalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteTotalButtonActionPerformed(evt);
            }
        });

        reporteUsuariosMButton.setText("Usuarios Mas Activos");
        reporteUsuariosMButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteUsuariosMButtonActionPerformed(evt);
            }
        });

        reporteHoyButton.setText("Reporte Diario");
        reporteHoyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteHoyButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Reportes por Hora");

        javax.swing.GroupLayout panelFiltroLayout = new javax.swing.GroupLayout(panelFiltro);
        panelFiltro.setLayout(panelFiltroLayout);
        panelFiltroLayout.setHorizontalGroup(
            panelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelFiltroLayout.setVerticalGroup(
            panelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        filtroHoraButton.setText("Generar");
        filtroHoraButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtroHoraButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Reporte por Dia");

        javax.swing.GroupLayout panelDiaFiltroLayout = new javax.swing.GroupLayout(panelDiaFiltro);
        panelDiaFiltro.setLayout(panelDiaFiltroLayout);
        panelDiaFiltroLayout.setHorizontalGroup(
            panelDiaFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDiaFiltroLayout.setVerticalGroup(
            panelDiaFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 108, Short.MAX_VALUE)
        );

        reporteDiaButton.setText("Generar");
        reporteDiaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteDiaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(reporteTotalButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(reporteUsuariosMButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(reporteHoyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelDiaFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(filtroHoraButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(reporteDiaButton)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reporteTotalButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reporteUsuariosMButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reporteHoyButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filtroHoraButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDiaFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(reporteDiaButton)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout graficaPanelLayout = new javax.swing.GroupLayout(graficaPanel);
        graficaPanel.setLayout(graficaPanelLayout);
        graficaPanelLayout.setHorizontalGroup(
            graficaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 845, Short.MAX_VALUE)
        );
        graficaPanelLayout.setVerticalGroup(
            graficaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graficaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(graficaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabs.addTab("Estadisticas", jPanel3);

        altasPanel.setName("altas"); // NOI18N

        nombreLabel.setText("Nombre");

        nombreField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreFieldFocusLost(evt);
            }
        });

        apellidoLabel.setText("Apellido");

        apellidoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                apellidoFieldFocusLost(evt);
            }
        });

        fechaLabel.setText("Fecha de Nacimiento");

        turnoLabel.setText("Turno");

        turnoCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"MATUTINO","VESPERTINO","NOCTURNO"}));

        jLabel5.setText("Nivel de Acceso");

        nacionalidadLabel.setText("Nacionalidad");

        accesoCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"GESTOR","ADMINISTRADOR"}));

        wnombreLabel.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        wnombreLabel.setForeground(new java.awt.Color(255, 0, 0));
        wnombreLabel.setText("*Nombre Invalido");

        wapellidoLabel.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        wapellidoLabel.setForeground(new java.awt.Color(255, 0, 0));
        wapellidoLabel.setText("* Apellido Invalido");

        nacionalidadField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nacionalidadFieldFocusLost(evt);
            }
        });

        wnacionalidadLabel.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        wnacionalidadLabel.setForeground(new java.awt.Color(255, 0, 0));
        wnacionalidadLabel.setText("* Nacionalidad Invalida");

        javax.swing.GroupLayout calendarPanelLayout = new javax.swing.GroupLayout(calendarPanel);
        calendarPanel.setLayout(calendarPanelLayout);
        calendarPanelLayout.setHorizontalGroup(
            calendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        calendarPanelLayout.setVerticalGroup(
            calendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        correoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                correoFieldFocusLost(evt);
            }
        });

        wcorreoLabel.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        wcorreoLabel.setForeground(new java.awt.Color(255, 0, 0));
        wcorreoLabel.setText("* Correo Invalido");

        enviarButton.setText("Enviar");
        enviarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarButtonActionPerformed(evt);
            }
        });

        correoLabel.setText("Correo");

        javax.swing.GroupLayout altasPanelLayout = new javax.swing.GroupLayout(altasPanel);
        altasPanel.setLayout(altasPanelLayout);
        altasPanelLayout.setHorizontalGroup(
            altasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(altasPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(altasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(wapellidoLabel)
                    .addGroup(altasPanelLayout.createSequentialGroup()
                        .addGroup(altasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(altasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(wnombreLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nombreLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nombreField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(apellidoLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(apellidoField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(fechaLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addComponent(calendarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(correoField))
                            .addComponent(wcorreoLabel)
                            .addComponent(correoLabel))
                        .addGap(18, 18, 18)
                        .addGroup(altasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(wnacionalidadLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(turnoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(turnoCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(nacionalidadLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(accesoCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nacionalidadField)
                            .addComponent(enviarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(388, Short.MAX_VALUE))
        );
        altasPanelLayout.setVerticalGroup(
            altasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(altasPanelLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(altasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreLabel)
                    .addComponent(turnoLabel))
                .addGap(19, 19, 19)
                .addGroup(altasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(turnoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(wnombreLabel)
                .addGap(2, 2, 2)
                .addGroup(altasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidoLabel)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(altasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accesoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(wapellidoLabel)
                .addGap(1, 1, 1)
                .addGroup(altasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fechaLabel)
                    .addComponent(nacionalidadLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(altasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(calendarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nacionalidadField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(altasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wnacionalidadLabel)
                    .addComponent(correoLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(altasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(correoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enviarButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wcorreoLabel)
                .addGap(269, 269, 269))
        );

        tabs.addTab("Altas", altasPanel);

        menu.setText("Menu");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menu.add(jMenuItem1);

        jMenuBar1.add(menu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Este evento se desata al cambiar entre cegillas de los paneles
     * @param evt evento desatado
     */
    private void tabsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabsStateChanged
        
        if (tabs.getSelectedComponent().getName().equals("listaUsuarios")) {
            // consultar la lista de usuarios
            usuariosModel.setDataVector(usuarioManager.obtenerDatos(true,page), usuariosTitle);            
        }
        else if (tabs.getSelectedComponent().getName().equals("reportes")) {
            movimientosUsuario.setDataVector(usuarioManager.obtenerDatos(true,movPage), movUsuarioTitle);
        }
        else if (tabs.getSelectedComponent().getName().equals("estadisticas")) {
            estadistica.numeroIncidencias();
        }
    }//GEN-LAST:event_tabsStateChanged
    /**
     * Este evento se desata cuando el usuario da click sobre un elemento de la 
     * tabla de usuarios
     * @param evt evento desatado
     */
    private void usuariosTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuariosTableMouseClicked
        // al hacer click sobre algun componente obtener la informacion del usuario
        UsuarioEJB usuarioSel = usuarioManager.obtenerDatosUsuario(usuariosTable.getSelectedRow());
        // el usuario existe llenar los campos
        if (usuarioSel != null) {
            usuariosNombreField.setText(usuarioSel.getNombre());
            usuariosApellidoField.setText(usuarioSel.getApellido());
            usuariosNacionalidadField.setText(usuarioSel.getNacionalidad());
            usuariosTurnoField.setText(usuarioSel.getTurno());
            if (usuarioSel.isAdministrador()) {
                usuariosAccesoField.setText("ADMINISTRADOR");
            }
            else{
                usuariosAccesoField.setText("GESTOR");
            }
            // fechas            
            usuariosFechaField.setText(formato.format(usuarioSel.getFechaNacimiento()));
            usuariosCreacionField.setText(formatoAV.format(usuarioSel.getCreatedAt()));
            usuariosActualizacionField.setText(formatoAV.format(usuarioSel.getUpdatedAt()));
        }               
    }//GEN-LAST:event_usuariosTableMouseClicked
    
    /**
     * Este evento se desata al presionar el boton de actualizar contraseña
     * @param evt evento
     */
    private void actualizarPasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarPasswordButtonActionPerformed
        // cambiar la contraseña                              
        if (usuariosTable.getSelectedRow() != -1 && usuariosPasswordField.getText().length() > 0) {
            // obtener el usuario
            UsuarioEJB objetivo = usuarioManager.obtenerDatosUsuario(usuariosTable.getSelectedRow());
            objetivo.setPassword(usuariosPasswordField.getText());
            usuarioManager.actualizarUsuario(objetivo,page);            
            
            try{
                throw new MyException("Contraseña actualizada correctamente");
            }
            catch(MyException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }                        
            usuariosPasswordField.setText("");
        }
        else{                       
            JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario para realizar esta accion");
        }
    }//GEN-LAST:event_actualizarPasswordButtonActionPerformed
    /**
     * Este evento desata la eliminacion de un usuario
     * @param evt evento
     */
    private void usuariosDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuariosDeleteButtonActionPerformed
        // eliminar el usuario
        if (usuariosTable.getSelectedRow() != -1 ) {
            // obtener el id del usuario 
            String id = usuarioManager.obtenerDatosUsuario(usuariosTable.getSelectedRow()).getIdUsuario();
            // remover        
            int resp = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar este usuario?");
            
            if (resp == 0) {
                if (usuarioManager.removerUsuario(id)) {
                    JOptionPane.showMessageDialog(null, "Usuario removido con exito");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Error al remover al usuario");
                }
                // regenerar la tabla
                usuariosModel.setDataVector(usuarioManager.obtenerDatos(true,page), usuariosTitle);
            }            
        }       
    }//GEN-LAST:event_usuariosDeleteButtonActionPerformed
    
    /**
     * Este evento se desata para paginar la lista de usuarios en bloques de 10 
     * retrocediendo en la lista
     * @param evt evento
     */
    private void usuariosAnteriorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuariosAnteriorButtonActionPerformed
        // TODO add your handling code here:
        if (page > 1) {
            page -= 1;
            usuariosModel.setDataVector(usuarioManager.obtenerDatos(true, page), usuariosTitle);
        }
    }//GEN-LAST:event_usuariosAnteriorButtonActionPerformed
    /**
     * Este evento se desata para paginar la lista de usuarios en bloques de 10
     * adelantando en la lista
     * @param evt 
     */
    private void usuariosSiguienteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuariosSiguienteButtonActionPerformed
        // TODO add your handling code here:
        page +=  1;
        usuariosModel.setDataVector(usuarioManager.obtenerDatos(true, page), usuariosTitle);
    }//GEN-LAST:event_usuariosSiguienteButtonActionPerformed
    /**
     * Este evento retrocede en bloques de 10 la lista de movimientos
     * @param evt evento
     */
    private void anteriorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anteriorButtonActionPerformed
        if (movPage > 1) {
            movPage -= 1;
            movimientosUsuario.setDataVector(usuarioManager.obtenerDatos(true, movPage), movUsuarioTitle);
        }
    }//GEN-LAST:event_anteriorButtonActionPerformed
    
    /**
     * Este evento avanza en bloques de 10 la lista de movimientos 
     * @param evt evento
     */
    private void siguienteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteButtonActionPerformed
        // TODO add your handling code here:
        movPage +=  1;
        movimientosUsuario.setDataVector(usuarioManager.obtenerDatos(true, movPage), movUsuarioTitle);        
    }//GEN-LAST:event_siguienteButtonActionPerformed
    /**
     * Este evento se desata al seleccionar un movimiento para mostrar sus detalles
     * @param evt evento
     */
    private void reportesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportesTableMouseClicked
        // TODO add your handling code here:
        // obtener el id del usuario
        UsuarioEJB usuarioSel = usuarioManager.obtenerDatosUsuario(reportesTable.getSelectedRow());
        
        if (usuarioSel != null) {
            movimientosDetalles.setDataVector(movManager.obtenerDatos(usuarioSel.getIdUsuario()), movMovimientosTitle);
        }
        
    }//GEN-LAST:event_reportesTableMouseClicked
    
    /**
     * Este evento se desata para crear el grafico con el numero de incidencias
     * por categoria
     * @param evt evento
     */
    private void reporteTotalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteTotalButtonActionPerformed
        // TODO add your handling code here:
        int[] datos = estadistica.numeroIncidencias();
        
        if (datos != null) {
            // fuente de datos
            totalIncidencias.setValue("QUEJAS", datos[0]);
            totalIncidencias.setValue("SUGERENCIAS", datos[1]);
            // creando el grafico
            grafico = ChartFactory.createPieChart("Incidencias por categoria",
                    totalIncidencias,
                    true,
                    true,
                    false);
            graficoChartPanel = new ChartPanel(grafico);
            graficaPanel.add(graficoChartPanel);
            graficaPanel.validate();
            
            
        }else{
            JOptionPane.showMessageDialog(this, "Error al leer datos desde el servidor");
        }
        
    }//GEN-LAST:event_reporteTotalButtonActionPerformed
    /**
     * Este evento se desata para generar los graficos con los 10 usuarios con mas 
     * registros
     * @param evt evento
     */
    private void reporteUsuariosMButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteUsuariosMButtonActionPerformed
        // TODO add your handling code here:
        HashMap<String,Integer> resultados = estadistica.listarMayores();
        
        if (resultados != null) {
            for (String key : resultados.keySet()) {
                usuariosActivos.setValue(resultados.get(key), key, key);
            }
            
            grafico = ChartFactory.createBarChart("Usuarios mas activos", "Usuario", "Incidencias", usuariosActivos);
            graficoChartPanel = new ChartPanel(grafico);
            graficaPanel.add(graficoChartPanel);
            graficaPanel.validate();
            
        }else{
            JOptionPane.showMessageDialog(this, "Error al leer los datos del servidor");
        }
    }//GEN-LAST:event_reporteUsuariosMButtonActionPerformed
    
    /**
     * Este evento se para salir del panel de administracion
     * @param evt evento
     */
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    /**
     * Este evento se desata para generar el reporte en PDF de las incidencias 
     * del dia de hoy
     * @param evt evento
     */
    private void reporteHoyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteHoyButtonActionPerformed
        // seleccionar la ruta
        JFileChooser ruta = new JFileChooser();
        ruta.showSaveDialog(this);
        File destino = ruta.getSelectedFile();
        
        
        HashMap<String,Object[]> datos = estadistica.incidenciasHoy();
        
        ReporteIncidenciasHoyPDF reporte = new ReporteIncidenciasHoyPDF(destino);
        reporte.setIds((Long[])datos.get("ids"));
        reporte.setClientes((String[])datos.get("clientes"));
        reporte.setImportancias((String[])datos.get("importancias"));
        reporte.setTipos((String[])datos.get("tipos"));
        reporte.setUsuarios((String[])datos.get("usuarios"));
        reporte.createPDF();
    }//GEN-LAST:event_reporteHoyButtonActionPerformed
    /**
     * este evento se desata para generar el reporte de las incidencias en PDF 
     * que se registraron el dia de hoy a determinada hora
     * @param evt evento
     */
    private void filtroHoraButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtroHoraButtonActionPerformed
        JFileChooser ruta = new JFileChooser();
        ruta.showSaveDialog(this);
        File destino = ruta.getSelectedFile();
        
        LocalTime tiempo = filtroHora.getTime();
        HashMap<String,Object[]> datos = estadistica.IncidenciaPorHora(tiempo);
        
        ReporteIncidenciasHoraPDF reporte = new ReporteIncidenciasHoraPDF(destino);
        reporte.setIds((Long[])datos.get("ids"));
        reporte.setClientes((String[])datos.get("clientes"));
        reporte.setImportancias((String[])datos.get("importancias"));
        reporte.setTipos((String[])datos.get("tipos"));
        reporte.setUsuarios((String[])datos.get("usuarios"));
        reporte.createPDF();        
        
    }//GEN-LAST:event_filtroHoraButtonActionPerformed
    
    /**
     * este evento se desata para registrar un usuario
     * @param evt evento
     */
    private void enviarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarButtonActionPerformed
        boolean valid = true;
        boolean administrador;
        Date fecha;
        Usuario usuario = new Usuario();
        // validar los datos
        if (!validador.isAlphaSpace(nombreField.getText()) || nombreField.getText().length() == 0) {
            valid = false;
            wnombreLabel.setVisible(true);
        }else{
            usuario.setNombre(nombreField.getText());
        }

        if (!validador.isAlphaSpace(apellidoField.getText()) || apellidoField.getText().length() == 0) {
            valid = false;
            wapellidoLabel.setVisible(true);
        }else{
            usuario.setApellido(apellidoField.getText());
        }

        if (!validador.isAlpha(nacionalidadField.getText()) || nacionalidadField.getText().length() == 0) {
            valid = false;
            wnacionalidadLabel.setVisible(true);
        }else{
            usuario.setNacionalidad(nacionalidadField.getText());
        }

        if (!validador.isEmail(correoField.getText())) {
            wcorreoLabel.setVisible(true);
            valid = false;
        }else{
            usuario.setCorreo(correoField.getText());
        }

        if (valid) {
            // las demas validaciones pasaron
            fecha = calendario.getDate();

            usuario.setFechaNacimiento(fecha);

            if (accesoCombo.getSelectedIndex() == 0) {
                administrador = false;
            }
            else{
                administrador = true;
            }
            usuario.setAdministrador(Boolean.valueOf(administrador));

            if (turnoCombo.getSelectedIndex() == 0) {
                usuario.setTurno("MATUTINO");
            }else if (turnoCombo.getSelectedIndex() == 1) {
                usuario.setTurno("VESPERTINO");
            }else{
                usuario.setTurno("NOCTURNO");
            }

            if (usuario.registro() == null) {
                JOptionPane.showMessageDialog(null, "Error al crear el usuario revisa los datos por favor");
            }
            else{
                JOptionPane.showMessageDialog(null, "Usuario creado con exito\nUsername: " +
                    usuario.getUsername() +
                    "\nPassword: " + usuario.getPassword());
            }
        }else{
            JOptionPane.showMessageDialog(this, "Los datos ingresados son invalidos");
        }
    }//GEN-LAST:event_enviarButtonActionPerformed
    /**
     * Este evento se desata al perder el foco para validar el correo
     * @param evt evento
     */
    private void correoFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_correoFieldFocusLost
        // validar el correo
        if (validador.isEmail(correoField.getText())) {
            wcorreoLabel.setVisible(false);
        }else{
            wcorreoLabel.setVisible(true);
        }
    }//GEN-LAST:event_correoFieldFocusLost
    /**
     * Este evento se desata para validar el campo de apellido
     * @param evt evento
     */
    private void apellidoFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_apellidoFieldFocusLost
        // TODO add your handling code here:
        if (validador.isAlphaSpace(apellidoField.getText())) {
            wapellidoLabel.setVisible(false);
        }else{
            wapellidoLabel.setVisible(true);
        }
    }//GEN-LAST:event_apellidoFieldFocusLost
    /**
     * Este evento se desata para validar el nombre en la alta de usuario
     * @param evt evento
     */
    private void nombreFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreFieldFocusLost
        // TODO add your handling code here:
        if (validador.isAlphaSpace(nombreField.getText())) {
            wnombreLabel.setVisible(false);
        }else{
            wnombreLabel.setVisible(true);
        }
    }//GEN-LAST:event_nombreFieldFocusLost
    /**
     * Este evento se genera para generar el reporte de incidencias del dia en PDF
     * @param evt evento
     */
    private void reporteDiaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteDiaButtonActionPerformed
        // obtener la fecha seleccionada        

        JFileChooser ruta = new JFileChooser();
        ruta.showSaveDialog(this);
        File destino = ruta.getSelectedFile();
               
        HashMap<String,Object[]> datos = estadistica.incidenciasDia(filtroDia.getDate());
        
        ReporteIncidenciasDiaPDF reporte = new ReporteIncidenciasDiaPDF(destino);
        reporte.setIds((Long[])datos.get("ids"));
        reporte.setClientes((String[])datos.get("clientes"));
        reporte.setImportancias((String[])datos.get("importancias"));
        reporte.setTipos((String[])datos.get("tipos"));
        reporte.setUsuarios((String[])datos.get("usuarios"));
        reporte.createPDF();         
        
    }//GEN-LAST:event_reporteDiaButtonActionPerformed

    private void nacionalidadFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nacionalidadFieldFocusLost
        // TODO add your handling code here:
        if (nacionalidadField.getText().length() > 0 && validador.isAlpha(nacionalidadField.getText())) {
            wnacionalidadLabel.setVisible(false);
        }
        else{
            wnacionalidadLabel.setVisible(true);
        }
    }//GEN-LAST:event_nacionalidadFieldFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> accesoCombo;
    private javax.swing.JButton actualizarPasswordButton;
    private javax.swing.JPanel altasPanel;
    private javax.swing.JButton anteriorButton;
    private javax.swing.JTextField apellidoField;
    private javax.swing.JLabel apellidoLabel;
    private javax.swing.JPanel calendarPanel;
    private javax.swing.JTextField correoField;
    private javax.swing.JLabel correoLabel;
    private javax.swing.JButton enviarButton;
    private javax.swing.JLabel fechaLabel;
    private javax.swing.JButton filtroHoraButton;
    private javax.swing.JPanel graficaPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel listaLabel;
    private javax.swing.JMenu menu;
    private javax.swing.JPanel movimientoPanel;
    private javax.swing.JTable movimientosTable;
    private javax.swing.JTextField nacionalidadField;
    private javax.swing.JLabel nacionalidadLabel;
    private javax.swing.JTextField nombreField;
    private javax.swing.JLabel nombreLabel;
    private javax.swing.JPanel panelDiaFiltro;
    private javax.swing.JPanel panelFiltro;
    private javax.swing.JButton reporteDiaButton;
    private javax.swing.JButton reporteHoyButton;
    private javax.swing.JButton reporteTotalButton;
    private javax.swing.JButton reporteUsuariosMButton;
    private javax.swing.JTable reportesTable;
    private javax.swing.JLabel seguridadLabel;
    private javax.swing.JPanel seguridadPanel;
    private javax.swing.JButton siguienteButton;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JComboBox<String> turnoCombo;
    private javax.swing.JLabel turnoLabel;
    private javax.swing.JTextField usuariosAccesoField;
    private javax.swing.JLabel usuariosAccesoLabel;
    private javax.swing.JTextField usuariosActualizacionField;
    private javax.swing.JLabel usuariosActualizacionLabel;
    private javax.swing.JButton usuariosAnteriorButton;
    private javax.swing.JTextField usuariosApellidoField;
    private javax.swing.JLabel usuariosApellidoLabel;
    private javax.swing.JTextField usuariosCreacionField;
    private javax.swing.JLabel usuariosCreacionLabel;
    private javax.swing.JButton usuariosDeleteButton;
    private javax.swing.JLabel usuariosDeleteLabel;
    private javax.swing.JTextField usuariosFechaField;
    private javax.swing.JLabel usuariosFechaLabel;
    private javax.swing.JLabel usuariosLabel;
    private javax.swing.JTextField usuariosNacionalidadField;
    private javax.swing.JLabel usuariosNacionalidadLabel;
    private javax.swing.JTextField usuariosNombreField;
    private javax.swing.JLabel usuariosNombreLabel;
    private javax.swing.JPasswordField usuariosPasswordField;
    private javax.swing.JLabel usuariosPasswordLabel;
    private javax.swing.JButton usuariosSiguienteButton;
    private javax.swing.JPanel usuariosTab;
    private javax.swing.JTable usuariosTable;
    private javax.swing.JTextField usuariosTurnoField;
    private javax.swing.JLabel usuariosTurnoLabel;
    private javax.swing.JLabel wapellidoLabel;
    private javax.swing.JLabel wcorreoLabel;
    private javax.swing.JLabel wnacionalidadLabel;
    private javax.swing.JLabel wnombreLabel;
    // End of variables declaration//GEN-END:variables
}
