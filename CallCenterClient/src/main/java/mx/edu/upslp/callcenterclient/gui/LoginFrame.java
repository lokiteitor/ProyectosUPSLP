/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.upslp.callcenterclient.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import mx.edu.upslp.callserver.usuario.UsuarioEJB;
import mx.edu.upslp.callserver.usuario.remote.UsuarioSessionBeanRemote;

/**
 *
 * @author David Delgado Hernandez 150205@upslp.edu.mx
 */
public class LoginFrame extends javax.swing.JFrame {
    private UsuarioEJB miUsuario;
    /**
     * Creates new form LoginFrame
     */
    public LoginFrame() {
        initComponents();
        warningLabel.setVisible(false);
        this.setTitle("Asistencia de Quejas y Sugerencias");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ga = new javax.swing.JPanel();
        usuarioLabel = new javax.swing.JLabel();
        usuarioField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        warningLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ga.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        usuarioLabel.setLabelFor(usuarioField);
        usuarioLabel.setText("Usuario");

        passwordLabel.setLabelFor(ga);
        passwordLabel.setText("Contraseña");

        warningLabel.setForeground(new java.awt.Color(204, 0, 0));
        warningLabel.setText("* Usuario o Contraseña Incorrectos");

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout gaLayout = new javax.swing.GroupLayout(ga);
        ga.setLayout(gaLayout);
        gaLayout.setHorizontalGroup(
            gaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gaLayout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addGroup(gaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(warningLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(usuarioField)
                    .addComponent(usuarioLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passwordField)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        gaLayout.setVerticalGroup(
            gaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gaLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(usuarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuarioField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(14, 14, 14)
                .addComponent(warningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        // loguear al usuario

        boolean flag  = false;
        Properties props = new Properties();

        // cargamos la configuracion del JNDI
        try{
            props.load(new FileInputStream("jndi.properties"));
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo");
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo");
        }

        try{
            // cargamos el contexto para crear el objeto remoto
            InitialContext ctx = new InitialContext(props);
            // cargamos el Session Bean UsuarioEJB intefece
            UsuarioSessionBeanRemote usuarioBean = (UsuarioSessionBeanRemote) ctx.lookup("mx.edu.upslp.callserver.usuario.remote.UsuarioSessionBeanRemote");
            flag = usuarioBean.login(usuarioField.getText(), passwordField.getText());
            if (flag) {
                // obtener el usuario
                miUsuario = usuarioBean.obtenerUsuario(usuarioField.getText());

                if (usuarioBean.isAdmin(usuarioField.getText())) {
                    // lanzar ventana de administracion
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            new administradorFrame(miUsuario).setVisible(true);
                        }
                    });
                }
                else{
                    // lanzar ventana de gestor
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            new gestorFrame(miUsuario).setVisible(true);
                        }
                    });
                }
                this.dispose();
            }
            else{
                // el usuario no es correcto
                usuarioField.setText("");
                passwordField.setText("");
                warningLabel.setVisible(true);
            }

        }catch (NamingException ex) {
            System.out.println("Error durante consulta a JNDI");
            System.err.println(ex.getMessage());
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed
      

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ga;
    private javax.swing.JButton jButton1;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField usuarioField;
    private javax.swing.JLabel usuarioLabel;
    private javax.swing.JLabel warningLabel;
    // End of variables declaration//GEN-END:variables
}
