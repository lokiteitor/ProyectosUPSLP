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

import com.toedter.calendar.JDateChooser;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.View;
import mx.edu.upslp.callcenterclient.gui.datos.Incidencia;
import mx.edu.upslp.callcenterclient.validaciones.Validador;
import mx.edu.upslp.callserver.cliente.ClienteEJB;
import mx.edu.upslp.callserver.incidencia.IncidenciaEJB;
import mx.edu.upslp.callserver.usuario.UsuarioEJB;

/**
 * Este Frame muestra todos los controles a los que tiene acceso
 * @author David Delgado Hernandez 150205@upslp.edu.mx
 */
public class gestorFrame extends javax.swing.JFrame {
    private UsuarioEJB miUsuario;
    private JDateChooser calendario = new JDateChooser();
    private JDateChooser nacimiento = new JDateChooser();
    private Validador validador = new Validador();
    private Incidencia manager = new Incidencia();
    private int page = 1;
    private Date fechaMaxima = new Date();
    
    private String[] tIncidencias = new String[] {"ID","Cliente","Nivel","Tipo"};
    // modificamos el modelo en su creacion
    private DefaultTableModel incidenciasModel = new DefaultTableModel(){
        boolean[] editColum = {false,false,false,false};
        // hacemos que las columnas no sea editables
        public boolean isCellEditable(int indFila,int indCol){
            return editColum[indCol];
        }
    };    
    
    private JDateChooser listaCalendario = new JDateChooser();    
    private JDateChooser  edadChooser = new JDateChooser();
    
    
    /**
     * Crea la ventana del gestor e inicia los componentes
     */
    public gestorFrame(UsuarioEJB miUsuario) {
        this.miUsuario = miUsuario;
        fechaMaxima.setTime(fechaMaxima.getTime() - (long)(5.676E11));
        initComponents();        
        // agregar el calendario a pantalla
        calendario.setEnabled(true);
        calendario.setVisible(true);
        calendarPanel.setLayout(new BoxLayout(calendarPanel, View.Y_AXIS));
        calendarPanel.add(calendario);
        
        // crear su manejador de eventos        
        calendario.setMaxSelectableDate(new Date());        
        
        // calendario de lista
        
        listaCalendario.setEnabled(true);
        listaCalendario.setVisible(true);
        listaCalendarioPanel.setLayout(new BoxLayout(listaCalendarioPanel, View.Y_AXIS));
        listaCalendarioPanel.add(listaCalendario);
        
        listaCalendario.getJCalendar().setMaxSelectableDate(new Date());
        
        // calendario de nacimiento cliente
        nacimiento.setMaxSelectableDate(fechaMaxima);
        nacimiento.setDate(fechaMaxima);
        
        nacimiento.setEnabled(true);
        nacimiento.setVisible(true);
        nacimientoPanel.setLayout(new BoxLayout(nacimientoPanel, View.Y_AXIS));
        nacimientoPanel.add(nacimiento);
        
        
        // edad chooser
        edadChooser.setMaxSelectableDate(fechaMaxima);
        edadChooser.setDate(fechaMaxima);
        edadChooser.setEnabled(true);
        edadChooser.setVisible(true);
        edadChooserPanel.setLayout(new BoxLayout((edadChooserPanel), View.Y_AXIS));
        edadChooserPanel.add(edadChooser);
        
        this.setResizable(false);
        this.setTitle("Asistencia de Quejas y sugerencias");
        this.setLocationRelativeTo(null);      
        
        wnombreLabel.setVisible(false);
        wtelefonoLabel.setVisible(false);        
        quejaRadio.setSelected(true);
        wcorreoLabe.setVisible(false);
        wapellidoLabel.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoGrupo = new javax.swing.ButtonGroup();
        jSeparator1 = new javax.swing.JSeparator();
        listaTipoGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        tabPanel = new javax.swing.JTabbedPane();
        listaPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaTabla = new javax.swing.JTable();
        listaAnteriorButton = new javax.swing.JButton();
        listaSiguienteButton = new javax.swing.JButton();
        listaTituloLabel = new javax.swing.JLabel();
        listaNombreLabel = new javax.swing.JLabel();
        listaNombreField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        listaImportanciaLabel = new javax.swing.JLabel();
        listaTelefonoField = new javax.swing.JTextField();
        listaImportanciaCombo = new javax.swing.JComboBox<>();
        listaTelefonoLabel = new javax.swing.JLabel();
        listaEdadLabel = new javax.swing.JLabel();
        listaTipoLabel = new javax.swing.JLabel();
        listaQuejaRadio = new javax.swing.JRadioButton();
        listaSugerenciaRadio = new javax.swing.JRadioButton();
        listaDireccionLabel = new javax.swing.JLabel();
        listaDireccionField = new javax.swing.JTextField();
        listaFechaLabel = new javax.swing.JLabel();
        listaDescripcionLabel = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listaDescripcionArea = new javax.swing.JTextArea();
        listaModificarButton = new javax.swing.JButton();
        listaCalendarioPanel = new javax.swing.JPanel();
        wListaNombreLabel = new javax.swing.JLabel();
        wListaTelefonoLabel = new javax.swing.JLabel();
        wListaEdadLabel = new javax.swing.JLabel();
        listaSeguridadPanel = new javax.swing.JPanel();
        listaEliminarLabel = new javax.swing.JLabel();
        listaEliminarButton = new javax.swing.JButton();
        edadChooserPanel = new javax.swing.JPanel();
        registroPanel = new javax.swing.JPanel();
        usuarioLabel = new javax.swing.JLabel();
        usuarioField = new javax.swing.JTextField();
        fechaLabel = new javax.swing.JLabel();
        nombreLabel = new javax.swing.JLabel();
        nombreField = new javax.swing.JTextField();
        importanciaLabel = new javax.swing.JLabel();
        importanciaCombo = new javax.swing.JComboBox<>();
        telefonoLabel = new javax.swing.JLabel();
        reporteLabel = new javax.swing.JLabel();
        quejaRadio = new javax.swing.JRadioButton();
        sugerenciarRadio = new javax.swing.JRadioButton();
        descripcionLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcionText = new javax.swing.JTextArea();
        enviarButton = new javax.swing.JButton();
        calendarPanel = new javax.swing.JPanel();
        wtelefonoLabel = new javax.swing.JLabel();
        wnombreLabel = new javax.swing.JLabel();
        telefonoField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        direccionField = new javax.swing.JTextField();
        correoLabel = new javax.swing.JLabel();
        correoField = new javax.swing.JTextField();
        wcorreoLabe = new javax.swing.JLabel();
        nacimientoPanel = new javax.swing.JPanel();
        apellidoLabel = new javax.swing.JLabel();
        apellidoField = new javax.swing.JTextField();
        wapellidoLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabPanel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabPanelStateChanged(evt);
            }
        });

        listaPanel.setName("lista"); // NOI18N

        listaTabla.setModel(incidenciasModel);
        listaTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaTablaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listaTabla);

        listaAnteriorButton.setText("Anterior");
        listaAnteriorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaAnteriorButtonActionPerformed(evt);
            }
        });

        listaSiguienteButton.setText("Siguiente");
        listaSiguienteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaSiguienteButtonActionPerformed(evt);
            }
        });

        listaTituloLabel.setText("Lista de Incidencias");

        listaNombreLabel.setText("Nombre del cliente");

        listaNombreField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                listaNombreFieldFocusLost(evt);
            }
        });

        listaImportanciaLabel.setText("Importancia");

        listaTelefonoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                listaTelefonoFieldFocusLost(evt);
            }
        });

        listaImportanciaCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"EN ESPERA","URGENTE" }));

        listaTelefonoLabel.setText("Telefono");

        listaEdadLabel.setText("Edad");

        listaTipoLabel.setText("Tipo de Reporte");

        listaTipoGroup.add(listaQuejaRadio);
        listaQuejaRadio.setText("Queja");

        listaTipoGroup.add(listaSugerenciaRadio);
        listaSugerenciaRadio.setText("Sugerencia");

        listaDireccionLabel.setText("Direccion");

        listaFechaLabel.setText("Fecha");

        listaDescripcionLabel.setText("Descripcion");

        listaDescripcionArea.setColumns(20);
        listaDescripcionArea.setRows(5);
        jScrollPane4.setViewportView(listaDescripcionArea);

        listaModificarButton.setText("Modificar");
        listaModificarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaModificarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout listaCalendarioPanelLayout = new javax.swing.GroupLayout(listaCalendarioPanel);
        listaCalendarioPanel.setLayout(listaCalendarioPanelLayout);
        listaCalendarioPanelLayout.setHorizontalGroup(
            listaCalendarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        listaCalendarioPanelLayout.setVerticalGroup(
            listaCalendarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        wListaNombreLabel.setForeground(new java.awt.Color(255, 0, 0));
        wListaNombreLabel.setText("* Nombre invalido");

        wListaTelefonoLabel.setForeground(new java.awt.Color(255, 0, 0));
        wListaTelefonoLabel.setText("* Formato Invalido");

        wListaEdadLabel.setForeground(new java.awt.Color(255, 0, 0));
        wListaEdadLabel.setText("* Edad Invalida");

        listaSeguridadPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 1, true));

        listaEliminarLabel.setText("Eliminar Registro");

        listaEliminarButton.setText("Eliminar");
        listaEliminarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaEliminarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout listaSeguridadPanelLayout = new javax.swing.GroupLayout(listaSeguridadPanel);
        listaSeguridadPanel.setLayout(listaSeguridadPanelLayout);
        listaSeguridadPanelLayout.setHorizontalGroup(
            listaSeguridadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listaSeguridadPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(listaEliminarLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listaSeguridadPanelLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(listaEliminarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        listaSeguridadPanelLayout.setVerticalGroup(
            listaSeguridadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listaSeguridadPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(listaEliminarLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(listaEliminarButton)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout edadChooserPanelLayout = new javax.swing.GroupLayout(edadChooserPanel);
        edadChooserPanel.setLayout(edadChooserPanelLayout);
        edadChooserPanelLayout.setHorizontalGroup(
            edadChooserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 342, Short.MAX_VALUE)
        );
        edadChooserPanelLayout.setVerticalGroup(
            edadChooserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout listaPanelLayout = new javax.swing.GroupLayout(listaPanel);
        listaPanel.setLayout(listaPanelLayout);
        listaPanelLayout.setHorizontalGroup(
            listaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listaPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(listaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(listaPanelLayout.createSequentialGroup()
                        .addComponent(listaDireccionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(listaCalendarioPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listaPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(listaModificarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(wListaNombreLabel)
                    .addGroup(listaPanelLayout.createSequentialGroup()
                        .addGroup(listaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(listaImportanciaLabel)
                            .addComponent(listaImportanciaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(listaTelefonoLabel)
                            .addComponent(listaTelefonoField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(listaNombreField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(listaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(listaDescripcionLabel)
                            .addGroup(listaPanelLayout.createSequentialGroup()
                                .addComponent(listaEdadLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edadChooserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(listaPanelLayout.createSequentialGroup()
                        .addComponent(listaNombreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(listaFechaLabel))
                    .addGroup(listaPanelLayout.createSequentialGroup()
                        .addGroup(listaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(wListaTelefonoLabel)
                            .addComponent(listaDireccionField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(wListaEdadLabel)
                            .addGroup(listaPanelLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(listaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(listaTipoLabel)
                                    .addGroup(listaPanelLayout.createSequentialGroup()
                                        .addComponent(listaQuejaRadio)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(listaSugerenciaRadio))
                                    .addComponent(listaSeguridadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(listaPanelLayout.createSequentialGroup()
                .addGroup(listaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(listaPanelLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(listaAnteriorButton)
                        .addGap(84, 84, 84)
                        .addComponent(listaSiguienteButton))
                    .addGroup(listaPanelLayout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(listaTituloLabel)))
                .addGap(299, 299, 299))
        );
        listaPanelLayout.setVerticalGroup(
            listaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(listaTituloLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(listaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(listaPanelLayout.createSequentialGroup()
                        .addGroup(listaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(listaPanelLayout.createSequentialGroup()
                                .addGroup(listaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(listaNombreLabel)
                                    .addComponent(listaFechaLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(listaNombreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(wListaNombreLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(listaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(listaImportanciaLabel)
                                    .addComponent(listaEdadLabel)))
                            .addGroup(listaPanelLayout.createSequentialGroup()
                                .addComponent(listaCalendarioPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(edadChooserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(listaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(listaImportanciaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(listaDescripcionLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(listaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(listaPanelLayout.createSequentialGroup()
                                .addComponent(listaTelefonoLabel)
                                .addGap(18, 18, 18)
                                .addComponent(listaTelefonoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(wListaTelefonoLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(listaDireccionLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(listaDireccionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                .addComponent(wListaEdadLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(listaTipoLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(listaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(listaQuejaRadio)
                                    .addComponent(listaSugerenciaRadio))
                                .addGroup(listaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(listaPanelLayout.createSequentialGroup()
                                        .addGap(48, 48, 48)
                                        .addComponent(jLabel4)
                                        .addGap(58, 58, 58))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listaPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(listaSeguridadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(52, 52, 52))
                            .addGroup(listaPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(listaModificarButton)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(listaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(listaSiguienteButton)
                    .addComponent(listaAnteriorButton)))
        );

        tabPanel.addTab("Lista de Incidencias", listaPanel);

        registroPanel.setName("registro"); // NOI18N

        usuarioLabel.setText("Usuario: ");

        usuarioField.setEditable(false);

        fechaLabel.setText("Fecha");

        nombreLabel.setText("Nombre del Cliente");

        nombreField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreFieldFocusLost(evt);
            }
        });

        importanciaLabel.setText("Importancia");

        importanciaCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"URGENTE","EN ESPERA"}));

        telefonoLabel.setText("Telefono");

        reporteLabel.setText("Tipo de Reporte");

        tipoGrupo.add(quejaRadio);
        quejaRadio.setText("Queja");
        quejaRadio.setName("queja"); // NOI18N

        tipoGrupo.add(sugerenciarRadio);
        sugerenciarRadio.setText("Sugerencia");
        sugerenciarRadio.setName("sugerencia"); // NOI18N

        descripcionLabel.setText("Descripcion del Problema");

        descripcionText.setColumns(20);
        descripcionText.setRows(5);
        jScrollPane1.setViewportView(descripcionText);

        enviarButton.setText("Enviar");
        enviarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout calendarPanelLayout = new javax.swing.GroupLayout(calendarPanel);
        calendarPanel.setLayout(calendarPanelLayout);
        calendarPanelLayout.setHorizontalGroup(
            calendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        calendarPanelLayout.setVerticalGroup(
            calendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        wtelefonoLabel.setForeground(new java.awt.Color(255, 0, 0));
        wtelefonoLabel.setText("* Telefono no valido");

        wnombreLabel.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        wnombreLabel.setForeground(new java.awt.Color(255, 0, 0));
        wnombreLabel.setText("* Nombre no valido");

        telefonoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                telefonoFieldFocusLost(evt);
            }
        });

        jLabel1.setText("Direccion");

        correoLabel.setText("Correo");

        correoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                correoFieldFocusLost(evt);
            }
        });
        correoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                correoFieldActionPerformed(evt);
            }
        });

        wcorreoLabe.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        wcorreoLabe.setForeground(new java.awt.Color(255, 0, 0));
        wcorreoLabe.setText("* Correo invalido");

        javax.swing.GroupLayout nacimientoPanelLayout = new javax.swing.GroupLayout(nacimientoPanel);
        nacimientoPanel.setLayout(nacimientoPanelLayout);
        nacimientoPanelLayout.setHorizontalGroup(
            nacimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 617, Short.MAX_VALUE)
        );
        nacimientoPanelLayout.setVerticalGroup(
            nacimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        apellidoLabel.setText("Apellido");

        apellidoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                apellidoFieldFocusLost(evt);
            }
        });

        wapellidoLabel.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        wapellidoLabel.setForeground(new java.awt.Color(255, 0, 0));
        wapellidoLabel.setText("* Apellido Invalido");

        jLabel2.setText("Fecha Nacimiento");

        javax.swing.GroupLayout registroPanelLayout = new javax.swing.GroupLayout(registroPanel);
        registroPanel.setLayout(registroPanelLayout);
        registroPanelLayout.setHorizontalGroup(
            registroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registroPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(registroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registroPanelLayout.createSequentialGroup()
                        .addComponent(enviarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(descripcionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(registroPanelLayout.createSequentialGroup()
                        .addGroup(registroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(correoLabel)
                            .addGroup(registroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(correoField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(registroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(wcorreoLabe)
                                    .addComponent(wnombreLabel)
                                    .addGroup(registroPanelLayout.createSequentialGroup()
                                        .addGroup(registroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(nombreLabel)
                                            .addComponent(apellidoLabel)
                                            .addComponent(wapellidoLabel)
                                            .addComponent(reporteLabel)
                                            .addComponent(quejaRadio)
                                            .addComponent(jLabel1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(registroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(apellidoField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(sugerenciarRadio)
                                            .addComponent(direccionField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(111, 111, 111)
                        .addGroup(registroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nacimientoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(registroPanelLayout.createSequentialGroup()
                                .addComponent(telefonoLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(registroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(wtelefonoLabel)
                                    .addGroup(registroPanelLayout.createSequentialGroup()
                                        .addComponent(telefonoField, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(registroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(usuarioLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(importanciaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(registroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(usuarioField)
                                            .addComponent(importanciaCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(33, 33, 33))))
                            .addComponent(fechaLabel)
                            .addComponent(calendarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        registroPanelLayout.setVerticalGroup(
            registroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registroPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(registroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(correoLabel)
                    .addComponent(correoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usuarioLabel)
                    .addComponent(usuarioField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wcorreoLabe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(registroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreLabel)
                    .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefonoLabel)
                    .addComponent(telefonoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(importanciaLabel)
                    .addComponent(importanciaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(registroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registroPanelLayout.createSequentialGroup()
                        .addComponent(wnombreLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(registroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(apellidoLabel)
                            .addComponent(apellidoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(wapellidoLabel)
                        .addGap(6, 6, 6)
                        .addComponent(reporteLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(registroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(quejaRadio)
                            .addComponent(sugerenciarRadio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(registroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(direccionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(registroPanelLayout.createSequentialGroup()
                        .addGroup(registroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(registroPanelLayout.createSequentialGroup()
                                .addComponent(wtelefonoLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
                            .addGroup(registroPanelLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(nacimientoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fechaLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calendarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descripcionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enviarButton)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        tabPanel.addTab("Registrar Incidencia", registroPanel);

        jMenu1.setText("Menu");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabPanel))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabPanel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * borra los datos en los campos
     */
    private void borrarCampos(){
        nombreField.setText("");
        importanciaCombo.setSelectedIndex(0);
        telefonoField.setText("");
        tipoGrupo.setSelected(quejaRadio.getModel(), true);
        direccionField.setText("");
        descripcionLabel.setText("");
        apellidoField.setText("");
        correoField.setText("");
        descripcionText.setText("");
        nacimiento.setDate(new Date());
        calendario.setDate(new Date());

    }
    /**
     * borra los datos en los campos al ver los detalles de incidencias
     */
    private void borrarCamposDetalles(){
        listaNombreField.setText("");
        listaTelefonoField.setText("");
        listaDireccionField.setText("");
        listaDescripcionArea.setText("");
        listaCalendario.setDate(new Date());
    }
    /**
     * Este evento se desata al cambiar entre pestañas, ayuda a iniciar los datos
     * de las tablas
     * @param evt evento
     */
    private void tabPanelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabPanelStateChanged
        // TODO add your handling code here:
        if (tabPanel.getSelectedComponent().getName().equals("registro")) {
            usuarioField.setText(miUsuario.getUsername());
        }
        else if( tabPanel.getSelectedComponent().getName().equals("lista")){
            // cargar la lista de incidencias
            page = 1;
            incidenciasModel.setDataVector(manager.obtenerDatos(page, miUsuario.getIdUsuario()),tIncidencias);
            wListaNombreLabel.setVisible(false);
            wListaEdadLabel.setVisible(false);
            wListaTelefonoLabel.setVisible(false);
            borrarCamposDetalles();
        }        
    }//GEN-LAST:event_tabPanelStateChanged
    /**
     * Este evento se desata al seleccionar de la tabla alguna incidencias
     * @param evt evento
     */
    private void listaTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaTablaMouseClicked

        IncidenciaEJB  incidenciaSel = manager.obtenerDatosIncidencia(listaTabla.getSelectedRow());
        
        if (incidenciaSel != null) {
            listaNombreField.setText(incidenciaSel.getCliente().getNombre());
            listaTelefonoField.setText(incidenciaSel.getCliente().getTelefono());
            listaDireccionField.setText(incidenciaSel.getCliente().getDireccion());
            
            // llenar la fecha de nacimiento            
            edadChooser.setDate(incidenciaSel.getCliente().getEdad());
            
            listaDescripcionArea.setText(incidenciaSel.getDescripcion());
            
            if (incidenciaSel.getImportancia().equals("EN ESPERA")) {
                listaImportanciaCombo.setSelectedIndex(0);
            }else{
                listaImportanciaCombo.setSelectedIndex(1);
            }
            
            if (incidenciaSel.getTipo().equals("QUEJA")) {
                listaTipoGroup.setSelected(listaQuejaRadio.getModel(), true);
            }else{
                listaTipoGroup.setSelected(listaSugerenciaRadio.getModel(), true);
            }            
            //registrar fecha en el calendario
            listaCalendario.setDate(incidenciaSel.getFecha());            
        }
    }//GEN-LAST:event_listaTablaMouseClicked
    /**
     * Este evento se desata al modificar los datos de una incidencia
     * @param evt evento
     */
    private void listaModificarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaModificarButtonActionPerformed
        // TODO add your handling code here:
        // revisar nuevamente los datos y llamar al registro
        boolean integrity = true;
        IncidenciaEJB actualizar;
        Date fecha;
        Date edad;
                
        
        if (!validador.isAlpha(listaNombreField.getText()) || listaNombreField.getText().length() == 0) {
            integrity = false;
        }
        
        if (validador.isInteger(listaTelefonoField.getText()) == -1 || listaTelefonoField.getText().length() != 6) {
            integrity = false;
        }
        if (listaDescripcionArea.getText().length() == 0) {
            integrity = false;
        }
        
        if (listaDireccionField.getText().length() == 0) {
            integrity = false;
        }
        
        if (integrity) {
            if (listaTabla.getSelectedRow() != -1) {
                actualizar =  manager.obtenerDatosIncidencia(listaTabla.getSelectedRow());
                
                // paso la revision
                actualizar.getCliente().setNombre(listaNombreField.getText());
                actualizar.getCliente().setTelefono(listaTelefonoField.getText());                
                actualizar.getCliente().setDireccion(listaDireccionField.getText());
                
                actualizar.setIdUsuario(miUsuario);
                
                actualizar.setDescripcion(listaDescripcionArea.getText());

                // crear la fecha
                fecha = listaCalendario.getDate();
                actualizar.setFecha(fecha);
                
                // edad
                edad = edadChooser.getDate();
                actualizar.getCliente().setEdad(edad);

                if (listaImportanciaCombo.getSelectedIndex() == 0) {
                    actualizar.setImportancia("URGENTE");
                }else{
                    actualizar.setImportancia("EN ESPERA");
                }

                if (listaTipoGroup.isSelected(listaQuejaRadio.getModel())) {
                    actualizar.setTipo("QUEJA");
                }else{
                    actualizar.setTipo("SUGERENCIA");
                }
                
                manager.actualizarIncidencia(actualizar, page, miUsuario.getIdUsuario());
                
                JOptionPane.showMessageDialog(null, "Registro actualizado correctamente");
                incidenciasModel.setDataVector(manager.obtenerDatos(page, miUsuario.getIdUsuario()), tIncidencias);
                borrarCamposDetalles();
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Los datos no son validos o estan incompletos");
        }             
    }//GEN-LAST:event_listaModificarButtonActionPerformed
    /**
     * Este evento se desata para validar el nombre en la modificacion de incidencias
     * @param evt evento
     */
    private void listaNombreFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_listaNombreFieldFocusLost
        // validar nombre
        if (validador.isAlphaSpace(listaNombreField.getText())) {
            wListaNombreLabel.setVisible(false);
        }else{
            wListaNombreLabel.setVisible(true);
        }                
    }//GEN-LAST:event_listaNombreFieldFocusLost
    /**
     * Este evento se desata al validar el campo de telefono en la modificacion 
     * de la incidencia
     * @param evt evento
     */
    private void listaTelefonoFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_listaTelefonoFieldFocusLost
        // TODO add your handling code here:
        if (validador.isInteger(listaTelefonoField.getText()) != -1 &&
                listaTelefonoField.getText().length() == 6) {
            wListaTelefonoLabel.setVisible(false);
        }else{
            wListaTelefonoLabel.setVisible(true);
        }
    }//GEN-LAST:event_listaTelefonoFieldFocusLost
    
    /**
     * Este evento se desata al eliminar una incidencia
     * @param evt evento
     */
    private void listaEliminarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaEliminarButtonActionPerformed
        // eliminar el registro
        // eliminar el usuario
        if (listaTabla.getSelectedRow() != -1 ) {
            // obtener el id del usuario 
            Long id = manager.obtenerDatosIncidencia(listaTabla.getSelectedRow()).getIdIncidencia();
            // remover        
            int resp = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar esta incidencia?");
            
            if (resp == 0) {
                if (manager.removerIncidencia(id)) {
                    JOptionPane.showMessageDialog(null, "Incidencia removido con exito");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Error al remover al incidencia");
                }
                // regenerar la tabla
                incidenciasModel.setDataVector(manager.obtenerDatos(page,miUsuario.getIdUsuario()), tIncidencias);
            }            
        }               
    }//GEN-LAST:event_listaEliminarButtonActionPerformed
    /**
     * Este evento se desata al retonar el bloque de incidencias
     * @param evt evento
     */
    private void listaAnteriorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaAnteriorButtonActionPerformed
        // TODO add your handling code here:
        if (page > 1) {
            page-=1;
            incidenciasModel.setDataVector(manager.obtenerDatos(page,miUsuario.getIdUsuario()), tIncidencias);            
        }
    }//GEN-LAST:event_listaAnteriorButtonActionPerformed
    /**
     * Este evento se desata al saltar a la siguiente pagina de incidencias
     * @param evt evento
     */
    private void listaSiguienteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaSiguienteButtonActionPerformed
        // TODO add your handling code here:
        // @todo revisar los limites posibles
        page+=1;
        incidenciasModel.setDataVector(manager.obtenerDatos(page,miUsuario.getIdUsuario()), tIncidencias);
    }//GEN-LAST:event_listaSiguienteButtonActionPerformed
    
    /**
     * Este evento se desata para salir del frame actual
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
     * Este evento se desata al validar el campo de apellido del registro de
     * incidencias
     * @param evt evento
     */
    private void apellidoFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_apellidoFieldFocusLost
        // TODO add your handling code here:
        if (validador.isAlpha(apellidoField.getText())) {
            wapellidoLabel.setVisible(false);
        }else{
            wapellidoLabel.setVisible(true);
        }
    }//GEN-LAST:event_apellidoFieldFocusLost
    /**
     * Este evento se desata para autocompletar los datos en base al correo 
     * de usuario
     * @param evt evento 
     */
    private void correoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_correoFieldActionPerformed
        // al introducir buscar en la base de datos por coincidencias y cargar datos
        if (validador.isEmail(correoField.getText()) || correoField.getText().length() == 0) {
            ClienteEJB cliente = manager.getDataCliente(correoField.getText());
            if (cliente != null) {
                nombreField.setText(cliente.getNombre());
                apellidoField.setText(cliente.getApellido());
                direccionField.setText(cliente.getDireccion());
                telefonoField.setText(cliente.getTelefono());
                nacimiento.setDate(cliente.getEdad());
            }
        }                
    }//GEN-LAST:event_correoFieldActionPerformed

    /**
     * Este evento se desata al validar el campo de correo del registro
     * @param evt evento
     */
    private void correoFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_correoFieldFocusLost
        // TODO add your handling code here:
        if (validador.isEmail(correoField.getText()) || correoField.getText().length() == 0) {
            wcorreoLabe.setVisible(false);

        }else{
            wcorreoLabe.setVisible(true);
        }
    }//GEN-LAST:event_correoFieldFocusLost
    /**
     * Este evento se desata para validar el telefono del registro
     * @param evt evento
     */
    private void telefonoFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_telefonoFieldFocusLost
        // TODO add your handling code here:
        if (validador.isInteger(telefonoField.getText()) != -1 &&
            telefonoField.getText().length() == 6) {
            wtelefonoLabel.setVisible(false);
        }else{
            wtelefonoLabel.setVisible(true);
        }
    }//GEN-LAST:event_telefonoFieldFocusLost
    
    /**
     * Este evento se desata para realizar el registro de una incidencia
     * @param evt evento
     */
    private void enviarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarButtonActionPerformed
        // revisar nuevamente los datos y llamar al registro
        Incidencia registro = new Incidencia();
        boolean integrity = true;
        Date fecha;
        Date edad;

        if (!validador.isAlpha(nombreField.getText()) || nombreField.getText().length() == 0) {
            wnombreLabel.setVisible(true);
            integrity = false;
        }

        if (validador.isInteger(telefonoField.getText()) == -1 || telefonoField.getText().length() != 6) {
            wtelefonoLabel.setVisible(true);
            integrity = false;
        }
        if (descripcionText.getText().length() == 0) {
            integrity = false;
        }

        if (direccionField.getText().length() == 0) {
            integrity = false;
        }

        if (correoField.getText().length() == 0 || !validador.isEmail(correoField.getText())) {
            integrity = false;
            wcorreoLabe.setVisible(true);
        }

        if (apellidoField.getText().length() == 0 || !validador.isAlpha(apellidoField.getText())) {
            wapellidoLabel.setVisible(true);
            integrity = false;
        }

        if (integrity) {
            // paso la revision
            registro.setNombreCliente(nombreField.getText());
            registro.setTelefono(telefonoField.getText());
            registro.setDescripcion(descripcionText.getText());
            registro.setDireccion(direccionField.getText());
            registro.setIdUsuario(miUsuario.getIdUsuario());
            registro.setCorreo(correoField.getText());
            registro.setApellido(apellidoField.getText());

            // crear la fecha
            fecha = calendario.getDate();

            registro.setFecha(fecha);

            edad = nacimiento.getDate();

            registro.setEdad(edad);

            if (importanciaCombo.getSelectedIndex() == 0) {
                registro.setImportancia("URGENTE");
            }else{
                registro.setImportancia("EN ESPERA");
            }

            if (tipoGrupo.isSelected(quejaRadio.getModel())) {
                registro.setTipo("QUEJA");
            }else{
                registro.setTipo("SUGERENCIA");
            }

            if (registro.registrarIncidencia()) {
                JOptionPane.showMessageDialog(null, "Incidencia registrada correctamente");
                borrarCampos();
            }else{
                JOptionPane.showMessageDialog(null, "Error al registrar la incidencia");
            }

        }else{
            JOptionPane.showMessageDialog(null, "Los datos no son validos o estan incompletos");            
        }
    }//GEN-LAST:event_enviarButtonActionPerformed
    /**
     * Este evento se desata para validar el nombre dentro del registro
     * @param evt evento
     */
    private void nombreFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreFieldFocusLost
        // revisar validez
        if (validador.isAlphaSpace(nombreField.getText())) {
            wnombreLabel.setVisible(false);
        }else{
            wnombreLabel.setVisible(true);
        }
    }//GEN-LAST:event_nombreFieldFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidoField;
    private javax.swing.JLabel apellidoLabel;
    private javax.swing.JPanel calendarPanel;
    private javax.swing.JTextField correoField;
    private javax.swing.JLabel correoLabel;
    private javax.swing.JLabel descripcionLabel;
    private javax.swing.JTextArea descripcionText;
    private javax.swing.JTextField direccionField;
    private javax.swing.JPanel edadChooserPanel;
    private javax.swing.JButton enviarButton;
    private javax.swing.JLabel fechaLabel;
    private javax.swing.JComboBox<String> importanciaCombo;
    private javax.swing.JLabel importanciaLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton listaAnteriorButton;
    private javax.swing.JPanel listaCalendarioPanel;
    private javax.swing.JTextArea listaDescripcionArea;
    private javax.swing.JLabel listaDescripcionLabel;
    private javax.swing.JTextField listaDireccionField;
    private javax.swing.JLabel listaDireccionLabel;
    private javax.swing.JLabel listaEdadLabel;
    private javax.swing.JButton listaEliminarButton;
    private javax.swing.JLabel listaEliminarLabel;
    private javax.swing.JLabel listaFechaLabel;
    private javax.swing.JComboBox<String> listaImportanciaCombo;
    private javax.swing.JLabel listaImportanciaLabel;
    private javax.swing.JButton listaModificarButton;
    private javax.swing.JTextField listaNombreField;
    private javax.swing.JLabel listaNombreLabel;
    private javax.swing.JPanel listaPanel;
    private javax.swing.JRadioButton listaQuejaRadio;
    private javax.swing.JPanel listaSeguridadPanel;
    private javax.swing.JButton listaSiguienteButton;
    private javax.swing.JRadioButton listaSugerenciaRadio;
    private javax.swing.JTable listaTabla;
    private javax.swing.JTextField listaTelefonoField;
    private javax.swing.JLabel listaTelefonoLabel;
    private javax.swing.ButtonGroup listaTipoGroup;
    private javax.swing.JLabel listaTipoLabel;
    private javax.swing.JLabel listaTituloLabel;
    private javax.swing.JPanel nacimientoPanel;
    private javax.swing.JTextField nombreField;
    private javax.swing.JLabel nombreLabel;
    private javax.swing.JRadioButton quejaRadio;
    private javax.swing.JPanel registroPanel;
    private javax.swing.JLabel reporteLabel;
    private javax.swing.JRadioButton sugerenciarRadio;
    private javax.swing.JTabbedPane tabPanel;
    private javax.swing.JTextField telefonoField;
    private javax.swing.JLabel telefonoLabel;
    private javax.swing.ButtonGroup tipoGrupo;
    private javax.swing.JTextField usuarioField;
    private javax.swing.JLabel usuarioLabel;
    private javax.swing.JLabel wListaEdadLabel;
    private javax.swing.JLabel wListaNombreLabel;
    private javax.swing.JLabel wListaTelefonoLabel;
    private javax.swing.JLabel wapellidoLabel;
    private javax.swing.JLabel wcorreoLabe;
    private javax.swing.JLabel wnombreLabel;
    private javax.swing.JLabel wtelefonoLabel;
    // End of variables declaration//GEN-END:variables
}
