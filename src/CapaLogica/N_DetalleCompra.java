package CapaLogica;

import CapaDatos.M_DetalleCompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class N_DetalleCompra {

    private Conexion SQL = new Conexion();
    private Connection cn = SQL.conectar();
    String sql = "";

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Id Compra", "Producto", "Cantidad", "Precio", "Total"};
        modelo = new DefaultTableModel(null, titulos);
        String[] registro = new String[6];

        sql = ("sp_listar_detalle_compras'" + buscar + "'");
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

                modelo.addRow(registro);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }

    public boolean InsetarDetalleCompra(M_DetalleCompra dts) {
        sql = ("{call sp_guardar_detalle_compra (?,?,?,?,?)}");

        try {
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setInt(1, dts.getIdCompra());
            pst.setString(2, dts.getIdProducto());
            pst.setInt(3, dts.getCantidad());
            pst.setDouble(4, dts.getPrecio());
            pst.setDouble(5, dts.getTotal());

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
}
