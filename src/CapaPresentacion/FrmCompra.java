/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CapaPresentacion;

import CapaDatos.M_Compra;
import CapaDatos.M_DetalleCompra;
import CapaLogica.Conexion;
import CapaLogica.Funciones;
import CapaLogica.N_Compra;
import CapaLogica.N_DetalleCompra;
import CapaLogica.N_Producto;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author MATIAS
 */
public class FrmCompra extends javax.swing.JInternalFrame {

    /**
     * cambiamos el jframe que por defecto está como:      *
     * public class FrmCategoria extends javax.swing.JFrame { … } Agregando el
     * Internal: porque sinó no se puede agregar este formulario a al formulario
     * principal (menú)
     */
    /**
     * Creates new form FrmCompra
     */
    public FrmCompra() {
        initComponents();
        dimencionarImagen();
        mostrar();
        fechas();
        txtidUsuario.setVisible(false);
        txtidProducto.setVisible(false);
    }

    N_Compra func = new N_Compra();

    DefaultTableModel modelo = new DefaultTableModel();

    /**
     * dimensionarImagen es una función que permite seleccionar una imagen de
     * nuestro repertorio y que se la coloque en un area específica del
     * formulario con su tamaño detallado
     */
    private void dimencionarImagen() {
        ImageIcon fot = new ImageIcon(getClass().getResource("/Files/compra.png"));
        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(490, 220, Image.SCALE_DEFAULT));
        lblimagen.setIcon(icono);
        this.repaint();
    }

    void mostrar() {
        try {
            DefaultTableModel modelo;
            N_Compra func = new N_Compra();
            modelo = func.mostrar();
            tablalistado.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(rootPane, e);
        }
    }

    /**
     * la función fecha invoca a la cuncion obtener fecha actual de la capa
     * logica la cual mostrará en pantalla la fecha actual.
     */
    void fechas() {
        Funciones func = new Funciones();
        String fecha;
        fecha = func.getFechaActual();
        dcfecha.setText(fecha);
    }

    /**
     * genera un numero correlativo para cada factura generada
     */
    void generarCorrelativoFactura() {
        DecimalFormat formato = new DecimalFormat("0000");
        txtnum_comprobante.setText(formato.format(func.GenerarComprobanteFactura()));
    }

    /**
     * genera un numero correlativo para cada boleta generada
     */
    void generarCorrelativoBoleta() {
        DecimalFormat formato = new DecimalFormat("0000");
        txtnum_comprobante.setText(formato.format(func.GenerarComprobanteFacturaBoleta()));
    }

    /**
     * Recibe todos los datos de ña tabla para emviarlos a la capa logica
     */
    void guardarCompra() {
        M_Compra dts = new M_Compra();
        int idv = 1;
        dts.setIdproveedor(txtidProveedor.getText());
        dts.setIdusuario(Integer.parseInt(txtidUsuario.getText()));
        dts.setNumDocumento(txtnum_comprobante.getText());
        int selecionado = cbotipo_comprobante.getSelectedIndex();
        dts.setTipoDocumento((String) cbotipo_comprobante.getItemAt(selecionado));
        dts.setEstado("RECIBIDO");
        dts.setSubtotal(Double.parseDouble(txtsubtotal.getText()));
        dts.setIva(Double.parseDouble(txtimpuesto.getText()));
        dts.setTotal(Double.parseDouble(txttotal_pagar.getText()));
        func.InsertarCompra(dts);
    }

    /**
     * esta función permite completar la tabla de detalles con todos los datos
     * de la compra realizada.
     */
    void guardarDetalle() {
        String idv = func.IdCompra();
        M_DetalleCompra dts = new M_DetalleCompra();
        N_DetalleCompra fun = new N_DetalleCompra();
        N_Producto fun1 = new N_Producto();

        int idve = Integer.parseInt(idv);

        for (int i = 0; i < tablalistado.getRowCount(); i++) {
            dts.setIdCompra(idve);
            dts.setIdProducto(tablalistado.getValueAt(i, 0).toString());
            dts.setCantidad(Integer.parseInt(tablalistado.getValueAt(i, 2).toString()));
            dts.setPrecio(Double.parseDouble(tablalistado.getValueAt(i, 3).toString()));
            dts.setTotal(Double.parseDouble(tablalistado.getValueAt(i, 5).toString()));
            fun.InsetarDetalleCompra(dts);
            fun1.aumentar(Integer.parseInt(tablalistado.getValueAt(i, 0).toString()), Integer.parseInt(tablalistado.getValueAt(i, 2).toString())); //envía el idProducto y la cantidad
        }
    }

    /**
     * Este procedimiento permite modificar la tabla productos agregando nuevos
     * productos a la tabla con toda su información correspondiente
     */
    void AgregarProducto() {
        double subtotal, impst, totalpagar;

        modelo = (DefaultTableModel) tablalistado.getModel();

        int cant = Integer.parseInt(txtcantidad.getText());
        double pre = Double.parseDouble(txtprecio_compra.getText());
        int impuesto = Integer.parseInt(txtiva.getText());

        subtotal = cant * pre;
        impst = (subtotal * impuesto) / 100;
        totalpagar = subtotal + impst;

        ArrayList lista = new ArrayList();
        lista.add(txtidProducto.getText());
        lista.add(txtproducto.getText());
        lista.add(txtcantidad.getText());
        lista.add(txtprecio_compra.getText());
        lista.add(impst);
        lista.add(totalpagar);

        Object[] obj = new Object[6];
        obj[0] = lista.get(0);
        obj[1] = lista.get(1);
        obj[2] = lista.get(2);
        obj[3] = lista.get(3);
        obj[4] = lista.get(4);
        obj[5] = lista.get(5);
        modelo.addRow(obj);
        tablalistado.setModel(modelo);
        calcularTotal();
    }

    /**
     * Este procedimieto va a recorrer todos los datos de la tabla para poder
     * carcular los subtotales, impuesto y total a pagar
     */
    void calcularTotal() {
        double subtotal = 0.0;
        double impuesto = 0.0;
        double tpagar = 0;

        for (int i = 0; i < tablalistado.getRowCount(); i++) {
            int cantidad = Integer.parseInt(tablalistado.getValueAt(i, 2).toString());
            Double precio = Double.parseDouble(tablalistado.getValueAt(i, 3).toString());
            Double imp = Double.parseDouble(tablalistado.getValueAt(i, 4).toString());

            subtotal = subtotal + (cantidad * precio);
            impuesto = impuesto + imp;
            tpagar = subtotal + impuesto;
        }

        txtsubtotal.setText("" + subtotal);
        txtimpuesto.setText("" + impuesto);
        txttotal_pagar.setText("" + tpagar);
    }

    /**
     * permite borrar los datos en los txt cantidad, precio_compra y producto
     */
    void limpiardetalle() {
        txtcantidad.setText("");
        txtprecio_compra.setText("");
        txtproducto.setText("");
        txtiva.setText("");
    }

    /**
     * limpia todos los campos del formulario como así tambien la tabla y
     * detalles
     */
    void limpiarformulario() {
        txtidProducto.setText("");
        txtproveedor.setText("");
        txtnum_comprobante.setText("");
        txtiva.setText("");
        txtsubtotal.setText("");
        txtimpuesto.setText("");
        txttotal_pagar.setText("");
        limpiardetalle();
        limpiartabla();
    }

    /**
     * recorre cada una de las columnas removiendo la información mostrada
     */
    void limpiartabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    /**
     * borra todos los datos y calcula su total
     */
    private void remover() {
        DefaultTableModel compras = (DefaultTableModel) tablalistado.getModel();
        int item = tablalistado.getSelectedRow();
        if (item >= 0) {
            compras.removeRow(item);
            calcularTotal();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "Compras", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblimagen = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dcfecha = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbotipo_comprobante = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lbltipo = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtnum_comprobante = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtidProducto = new javax.swing.JTextField();
        txtproducto = new javax.swing.JTextField();
        btnbuscarproducto = new javax.swing.JButton();
        txtprecio_compra = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtcantidad = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtiva = new javax.swing.JTextField();
        btnagregar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtidProveedor = new javax.swing.JTextField();
        txtproveedor = new javax.swing.JTextField();
        txtidUsuario = new javax.swing.JTextField();
        txtnombreUsuario = new javax.swing.JTextField();
        btnbuscarproveedor = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtsubtotal = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtimpuesto = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txttotal_pagar = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        btnguardar = new javax.swing.JButton();
        btnimprimir = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablalistado = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));

        lblimagen.setBackground(new java.awt.Color(0, 102, 153));

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FECHA DE COMPRA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel2.setText("Fecha:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dcfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(dcfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 102, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TIPO DOCUMENTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel3.setText("Tipo:");

        cbotipo_comprobante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "BOLETA", "FACTURA" }));
        cbotipo_comprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbotipo_comprobanteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbotipo_comprobante, 0, 125, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbotipo_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 102, 153));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("CUIT:     2010345673245");

        jPanel5.setBackground(new java.awt.Color(50, 194, 177));

        lbltipo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbltipo.setText("FACTURA DE COMPRA");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(lbltipo)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbltipo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Nº:");

        txtnum_comprobante.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(48, 48, 48)
                .addComponent(txtnum_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(39, 39, 39))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtnum_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(0, 102, 153));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PRODUCTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel9.setText("Producto:");

        jLabel10.setText("Precio:");

        btnbuscarproducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnbuscarproducto.setText("...");
        btnbuscarproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarproductoActionPerformed(evt);
            }
        });

        jLabel11.setText("Cantidad:");

        jLabel12.setText("IVA:");

        txtiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtivaActionPerformed(evt);
            }
        });

        btnagregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/Agregar_p1.png"))); // NOI18N
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });

        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/Remove.png"))); // NOI18N
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(25, 25, 25)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtidProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnbuscarproducto))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtprecio_compra, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtiva, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscarproducto))
                .addGap(11, 11, 11)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtprecio_compra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(btnagregar)
                .addGap(18, 18, 18)
                .addComponent(btneliminar)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(0, 102, 153));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS DEL PROVEEDOR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel7.setText("Proveedor:");

        jLabel8.setText("Trabajador:");

        txtnombreUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreUsuarioActionPerformed(evt);
            }
        });

        btnbuscarproveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnbuscarproveedor.setText("...");
        btnbuscarproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarproveedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtidProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(txtproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtnombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtidUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btnbuscarproveedor)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtidProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscarproveedor))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtidUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel8.setBackground(new java.awt.Color(0, 102, 102));
        jPanel8.setForeground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("SUB TOTAL:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("IVA:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("TOTAL A PAGAR:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtimpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txttotal_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtimpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txttotal_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(0, 102, 153));
        jPanel9.setForeground(new java.awt.Color(255, 255, 255));

        btnguardar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnguardar.setText("Guardar");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnimprimir.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnimprimir.setText("Imprimir");
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        btnsalir.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnguardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnimprimir, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(btnsalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnsalir, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                .addContainerGap())
        );

        tablalistado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablalistado);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbotipo_comprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbotipo_comprobanteActionPerformed
        int tipo = cbotipo_comprobante.getSelectedIndex();

        if (tipo == 0) {
            lbltipo.setText("DOCUMENTO DE COMPRA");
        }

        if (tipo == 1) {
            lbltipo.setText("BOLETA DE COMPRA");
            generarCorrelativoBoleta();
        }
        if (tipo == 2) {
            lbltipo.setText("FACTURA DE COMPRA");
            generarCorrelativoFactura();
        }

    }//GEN-LAST:event_cbotipo_comprobanteActionPerformed

    private void btnbuscarproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarproductoActionPerformed
        FrmVistaProducto form = new FrmVistaProducto();
        form.toFront(); //trae al formulario al frente
        form.setVisible(true); //lo hace visible
        form.opcion = 1;
    }//GEN-LAST:event_btnbuscarproductoActionPerformed

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
        if (txtproducto.getText().length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Seleccione un producto");
            return;
        }
        if (txtcantidad.getText().length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Ingrese una cantidad a la compra");
            return;
        }
        if (txtcantidad.getText().equals("0")) {
            JOptionPane.showMessageDialog(rootPane, "Ingrese una cantidad a 0");
            return;
        }
        if (txtiva.getText().length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Ingrese un impuesto a la compra");
            return;
        }
        AgregarProducto();
        limpiardetalle();
    }//GEN-LAST:event_btnagregarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        remover();
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnbuscarproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarproveedorActionPerformed
        FrmVistaProveedor form = new FrmVistaProveedor();
        form.toFront();//lo trae al frente
        form.setVisible(true); //lo hace visible
    }//GEN-LAST:event_btnbuscarproveedorActionPerformed

    /**
     * revisa que los campos estén completados y sinó los pide de maera
     * obligatoria
     *
     * @param evt devuelve la información solicitada.
     */
    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        if (txtidProveedor.getText().length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Seleccione un proveedor");
            txtidProveedor.requestFocus();
            return;
        }
        if (txtnum_comprobante.getText().length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Seleccione un tipo de comprobante");
            txtnum_comprobante.requestFocus();
            return;
        }
        if (txttotal_pagar.getText().length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Agrege una compra");
            txttotal_pagar.requestFocus();
            return;
        }

        guardarCompra();
        guardarDetalle();
        JOptionPane.showMessageDialog(this, "la compra se realizo con exito");
        limpiarformulario();
        generarCorrelativoBoleta();
        generarCorrelativoFactura();

        txtnum_comprobante.setText("");
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void txtnombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreUsuarioActionPerformed

    private void txtivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtivaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtivaActionPerformed

    private Connection conn = new Conexion().conectar();
    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
        String idv = func.IdCompra();

        String tipodocumento;
        tipodocumento = cbotipo_comprobante.getSelectedItem().toString();

        if (tipodocumento.equals("BOLETA")) {
            Map p = new HashMap();
            p.put("id_compra", idv);

            JasperReport report;
            JasperPrint print;

            try {
                report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/Reportes/comprobantecompra.jrxml");
                print = JasperFillManager.fillReport(report, p, this.conn);
                JasperViewer view = new JasperViewer(print, false);
                view.setTitle("Comprobante Compra");
                view.setVisible(true);
            } catch (JRException e) {
                e.printStackTrace();
            }

        } else if (tipodocumento.equals("FACTURA")) {
            Map p = new HashMap();
            p.put("id_compra", idv);

            JasperReport report;
            JasperPrint print;

            try {
                report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/Reportes/comprobantecompra.jrxml");
                print = JasperFillManager.fillReport(report, p, this.conn);
                JasperViewer view = new JasperViewer(print, false);
                view.setTitle("Comprobante Compra");
                view.setVisible(true);
            } catch (JRException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showConfirmDialog(this, "Selecione una compra", "Compras", JOptionPane.ERROR_MESSAGE);
        }
        cbotipo_comprobante.setSelectedIndex(0);
    }//GEN-LAST:event_btnimprimirActionPerformed

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
            java.util.logging.Logger.getLogger(FrmCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCompra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnagregar;
    private javax.swing.JButton btnbuscarproducto;
    private javax.swing.JButton btnbuscarproveedor;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnimprimir;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox<String> cbotipo_comprobante;
    private javax.swing.JTextField dcfecha;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblimagen;
    private javax.swing.JLabel lbltipo;
    private javax.swing.JTable tablalistado;
    private javax.swing.JTextField txtcantidad;
    public static javax.swing.JTextField txtidProducto;
    public static javax.swing.JTextField txtidProveedor;
    public static javax.swing.JTextField txtidUsuario;
    private javax.swing.JTextField txtimpuesto;
    private javax.swing.JTextField txtiva;
    public static javax.swing.JTextField txtnombreUsuario;
    private javax.swing.JTextField txtnum_comprobante;
    public static javax.swing.JTextField txtprecio_compra;
    public static javax.swing.JTextField txtproducto;
    public static javax.swing.JTextField txtproveedor;
    private javax.swing.JTextField txtsubtotal;
    private javax.swing.JTextField txttotal_pagar;
    // End of variables declaration//GEN-END:variables
}
