/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/Application.java to edit this template
 */
package CapaPresentacion;

/**
 *
 * @author MATIAS
 */
public class FrmMenu extends javax.swing.JFrame {

    /**
     * Creates new form FrmMenu
     */
    public FrmMenu() {
        initComponents();

        //para que se vea toda la pantalla
        this.setTitle("Sistema de Compras y Ventas"); //configuramos el nombre de la ventana a mostrar
        this.setExtendedState(FrmMenu.MAXIMIZED_BOTH); //ordenamos que se inicie con el tamaño maximo de la pantalla
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contenedor = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblidUsuario = new javax.swing.JLabel();
        lblnombre = new javax.swing.JLabel();
        lblapellidos = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        mnualmacen = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        mnucompras = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        mnuventas = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        mnuconsultas = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        mnuconfig = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        mnureportes = new javax.swing.JMenu();
        mnuayuda = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        mnuarchivo = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        contenedor.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Usuario: ");

        lblidUsuario.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblidUsuario.setText("jLabel2");

        lblnombre.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblnombre.setText("jLabel3");

        lblapellidos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblapellidos.setText("jLabel4");

        javax.swing.GroupLayout contenedorLayout = new javax.swing.GroupLayout(contenedor);
        contenedor.setLayout(contenedorLayout);
        contenedorLayout.setHorizontalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(lblidUsuario)
                .addGap(87, 87, 87)
                .addComponent(lblnombre)
                .addGap(102, 102, 102)
                .addComponent(lblapellidos)
                .addContainerGap(251, Short.MAX_VALUE))
        );
        contenedorLayout.setVerticalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorLayout.createSequentialGroup()
                .addContainerGap(379, Short.MAX_VALUE)
                .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblidUsuario)
                    .addComponent(lblnombre)
                    .addComponent(lblapellidos))
                .addContainerGap())
        );

        mnualmacen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/Almacen.png"))); // NOI18N
        mnualmacen.setMnemonic('e');
        mnualmacen.setText("Almacen");

        cutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/Productos.jpg"))); // NOI18N
        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Productos");
        cutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutMenuItemActionPerformed(evt);
            }
        });
        mnualmacen.add(cutMenuItem);

        copyMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/categoria.png"))); // NOI18N
        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Categorías");
        copyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyMenuItemActionPerformed(evt);
            }
        });
        mnualmacen.add(copyMenuItem);

        menuBar.add(mnualmacen);

        mnucompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/compras1.png"))); // NOI18N
        mnucompras.setMnemonic('h');
        mnucompras.setText("Compras");

        contentsMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/compras.png"))); // NOI18N
        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Compras");
        contentsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentsMenuItemActionPerformed(evt);
            }
        });
        mnucompras.add(contentsMenuItem);

        aboutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/proveedores_m.png"))); // NOI18N
        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("Proveedores");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        mnucompras.add(aboutMenuItem);

        menuBar.add(mnucompras);

        mnuventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/ventas_1.png"))); // NOI18N
        mnuventas.setText("Ventas");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/ventas (1).png"))); // NOI18N
        jMenuItem1.setText("Ventas");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mnuventas.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/clientes.png"))); // NOI18N
        jMenuItem2.setText("Clientes");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        mnuventas.add(jMenuItem2);

        menuBar.add(mnuventas);

        mnuconsultas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/consultas.png"))); // NOI18N
        mnuconsultas.setText("Consultas");

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/ventasrealizadas.png"))); // NOI18N
        jMenuItem3.setText("Ventas Realizadas");
        mnuconsultas.add(jMenuItem3);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/comprasrealizadas.png"))); // NOI18N
        jMenuItem4.setText("Compras Realizadas");
        mnuconsultas.add(jMenuItem4);

        menuBar.add(mnuconsultas);

        mnuconfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/configuraciones.png"))); // NOI18N
        mnuconfig.setText("Configuraciones");

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/User.png"))); // NOI18N
        jMenuItem5.setText("Configuración de Usuarios");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        mnuconfig.add(jMenuItem5);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/empleados.png"))); // NOI18N
        jMenuItem6.setText("Empleados");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        mnuconfig.add(jMenuItem6);

        menuBar.add(mnuconfig);

        mnureportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/rptes.png"))); // NOI18N
        mnureportes.setText("Reportes");
        menuBar.add(mnureportes);

        mnuayuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/ayuda.png"))); // NOI18N
        mnuayuda.setText("Ayuda");

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/info.png"))); // NOI18N
        jMenuItem7.setText("Ayuda");
        mnuayuda.add(jMenuItem7);

        menuBar.add(mnuayuda);

        mnuarchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/inicio.png"))); // NOI18N
        mnuarchivo.setMnemonic('f');
        mnuarchivo.setText("Sistema");

        openMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/salir.png"))); // NOI18N
        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Cerrar Sesión");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        mnuarchivo.add(openMenuItem);

        saveMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/Puerta.png"))); // NOI18N
        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Salir");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        mnuarchivo.add(saveMenuItem);

        menuBar.add(mnuarchivo);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        dispose();
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        FrmUsuario form = new FrmUsuario();
        contenedor.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void cutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutMenuItemActionPerformed
        FrmProducto form = new FrmProducto();
        contenedor.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_cutMenuItemActionPerformed

    private void copyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyMenuItemActionPerformed
        FrmCategoria form = new FrmCategoria();
        contenedor.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_copyMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        FrmProveedor form = new FrmProveedor();
        contenedor.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        FrmCliente form = new FrmCliente();
        contenedor.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        FrmEmpleado form = new FrmEmpleado();
        contenedor.add(form);
        form.toFront();
        form.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        FrmLogin form = new FrmLogin();
        form.setVisible(true);
        form.pack();
        this.setVisible(false);
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void contentsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentsMenuItemActionPerformed
        FrmCompra form = new FrmCompra();
        contenedor.add(form);
        form.toFront();
        form.setVisible(true);
        FrmCompra.txtidUsuario.setText(lblidUsuario.getText());
        FrmCompra.txtnombreUsuario.setText(lblnombre.getText() + " " + lblapellidos.getText());

    }//GEN-LAST:event_contentsMenuItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        FrmVenta form = new FrmVenta();
        contenedor.add(form);
        form.toFront();
        form.setVisible(true);
        FrmVenta.txtidUsuario.setText(lblidUsuario.getText());
        FrmVenta.txtnombreUsuario.setText(lblnombre.getText() + " " + lblapellidos.getText());
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JPanel contenedor;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    public static javax.swing.JLabel lblapellidos;
    public static javax.swing.JLabel lblidUsuario;
    public static javax.swing.JLabel lblnombre;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu mnualmacen;
    private javax.swing.JMenu mnuarchivo;
    private javax.swing.JMenu mnuayuda;
    private javax.swing.JMenu mnucompras;
    private javax.swing.JMenu mnuconfig;
    private javax.swing.JMenu mnuconsultas;
    private javax.swing.JMenu mnureportes;
    private javax.swing.JMenu mnuventas;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration//GEN-END:variables

}
