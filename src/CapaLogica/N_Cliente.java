package CapaLogica;

import CapaDatos.M_Cliente;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class N_Cliente {

    private Conexion SQL = new Conexion();
    private Connection con = SQL.conectar();
    String sql = "";

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Nombres", "Apellidos", "DNI", "CUIL", "Edad", "Sexo", "Telefono", "Direccion"};
        modelo = new DefaultTableModel(null, titulos);
        String[] registro = new String[9];

        sql = ("sp_buscar_clientes'" + buscar + "'");

        try {
            Statement st = con.createStatement();
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
                modelo.addRow(registro);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }

    public boolean insertar(M_Cliente dts) {
        sql = ("{call sp_guardar_clientes (?,?,?,?,?,?,?,?,?)}");

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, dts.getCodigo());
            pst.setString(2, dts.getNombres());
            pst.setString(3, dts.getAppellidos());
            pst.setString(4, dts.getDni());
            pst.setString(5, dts.getRuc());
            pst.setInt(6, dts.getEdad());
            pst.setString(7, dts.getSexo());
            pst.setString(8, dts.getTelefono());
            pst.setString(9, dts.getDireccion());

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

    public boolean editar(M_Cliente dts) {
        sql = ("{call sp_editar_clientes (?,?,?,?,?,?,?,?,?)}");

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, dts.getCodigo());
            pst.setString(2, dts.getNombres());
            pst.setString(3, dts.getAppellidos());
            pst.setString(4, dts.getDni());
            pst.setString(5, dts.getRuc());
            pst.setInt(6, dts.getEdad());
            pst.setString(7, dts.getSexo());
            pst.setString(8, dts.getTelefono());
            pst.setString(9, dts.getDireccion());

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

    public boolean eliminar(M_Cliente dts) {
        sql = ("{call sp_eliminar_clientes (?)}");

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, dts.getCodigo());

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

    public int generarIdCliente() {
        String sql = ("select max(id_cliente) as id from clientes");
        int cod = 0;

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                cod = rs.getInt("id") + 1;
            }

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Error sql:" + e.getMessage());
        }
        return cod;
    }
}
