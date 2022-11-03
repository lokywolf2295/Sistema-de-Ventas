package CapaLogica;

import CapaDatos.M_Compra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class N_Compra {

    private Conexion SQL = new Conexion();
    private Connection cn = SQL.conectar();
    String sql = "";
    ResultSet rs;

    public DefaultTableModel mostrar() {
        DefaultTableModel modelo;

        String[] titulos = {"ID Producto", "Descripcion", "Cantidad", "Precio", "Iva", "Total"};
        modelo = new DefaultTableModel(null, titulos);
        String[] registro = new String[6];
        return modelo;
    }

    public DefaultTableModel mostrar1(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID Compra", "Cliente", "Fecha", "Empleado", "Comprobante", "Numero", "Estado", "Sub Total", "Impuesto", "Total"};
        modelo = new DefaultTableModel(null, titulos);
        String[] registro = new String[10];

        sql = ("sp_listar_compras'" + buscar + "'");
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                registro[0] = rs.getString(1);
                registro[1] = rs.getString(2);
                registro[2] = rs.getString(3);
                registro[3] = rs.getString(4);
                registro[4] = rs.getString(5);
                registro[5] = rs.getString(6);
                registro[6] = rs.getString(7);
                registro[7] = rs.getString(8);
                registro[8] = rs.getString(9);
                registro[9] = rs.getString(10);
                modelo.addRow(registro);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }

    public boolean InsertarCompra(M_Compra dts) {
        String fecha, hora;
        fecha = Funciones.getFechaActual(); //llamamos a las funciones de la capa lógica
        hora = Funciones.getHoraActual();

        sql = ("{call sp_guardar_compra (?,?,?,?,?,?,?,?,?,?)}");

        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, fecha);
            pst.setString(2, hora);
            pst.setString(3, dts.getNumDocumento());
            pst.setString(4, dts.getTipoDocumento());
            pst.setDouble(5, dts.getSubtotal());
            pst.setDouble(6, dts.getIva());
            pst.setDouble(7, dts.getTotal());
            pst.setString(8, dts.getEstado());
            pst.setInt(9, dts.getIdusuario());
            pst.setString(10, dts.getIdproveedor());

            int n = pst.executeUpdate();
            if (n != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }

    }

    public String IdCompra() {
        String idv = "";
        sql = ("select max(id_compra)  from compras");

        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                idv = rs.getString(1);
            }

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        return idv;
    }

    /**
     * Función que permitirá mediante la base de datos extraer la información y generar la factura
     * @return numero que será mediate la variable cod
     */
    public int GenerarComprobanteFactura() {
        String sql = ("select max(num_documento) as id from compras where tipo_documento='Factura'");
        int cod = 0;

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                cod = rs.getInt("id") + 1;
            }
        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, "Error sql:" + e1.getMessage());
        }
        return cod;
    }

    public int GenerarComprobanteFacturaBoleta() {
        String sql = ("select max(num_documento) as id from compras where tipo_documento='Boleta'");
        int cod = 0;

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                cod = rs.getInt("id") + 1;
            }
        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, "Error sql:" + e1.getMessage());
        }
        return cod;
    }

}
