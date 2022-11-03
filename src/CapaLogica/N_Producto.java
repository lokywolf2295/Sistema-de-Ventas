package CapaLogica;

import CapaDatos.M_Producto;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class N_Producto {

    private Conexion SQL = new Conexion();
    private Connection con = SQL.conectar();
    String sql = "";

    public DefaultTableModel mostrar(String buscar){
        DefaultTableModel modelo;
        
        String[] titulos={"ID","Serie","Nombre","Fecha Ingreso","Fecha Vencimiento","Precio Compra","Precio Venta","Cantidad","ID Categoria","Categoria"};
       modelo=new DefaultTableModel(null,titulos);
       String[] registro =new String[10];
       
       sql=("sp_listar_producto'"+buscar+"'");
        try {
            Statement st =con.createStatement();
            ResultSet rs =st.executeQuery(sql);
            
            while (rs.next()) {
               registro[0]=rs.getString(1);
               registro[1]=rs.getString(2);
               registro[2]=rs.getString(3);
               registro[3]=rs.getString(4);
               registro[4]=rs.getString(5);
               registro[5]=rs.getString(6);
               registro[6]=rs.getString(7);
               registro[7]=rs.getString(8);
               registro[8]=rs.getString(9);
               registro[9]=rs.getString(10);
               
               modelo.addRow(registro);
            }
            return modelo;
  
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    public boolean insertar(M_Producto dts){
       sql=("{call sp_guardar_producto (?,?,?,?,?,?,?,?)}");
        try {
            PreparedStatement pst =con.prepareStatement(sql);
            pst.setString(1, dts.getIdProducto());
            pst.setString(2, dts.getSerie());
            pst.setString(3, dts.getNombre());
            pst.setDate(4, (Date) dts.getFechaIngreso());
            pst.setDate(5, (Date) dts.getFechaVencimiento());
            pst.setDouble(6, dts.getPrecioCompra());
            pst.setDouble(7, dts.getPrecioVenta());
            pst.setInt(8, dts.getIdCategoria());
            
            int n= pst.executeUpdate();
            if (n!=0) {
                return true;
            }else{
                 return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
       
    }
    
    
     public boolean editar(M_Producto dts){
       sql=("{call sp_editar_producto (?,?,?,?,?,?,?,?)}");
        try {
            PreparedStatement pst =con.prepareStatement(sql);
          pst.setString(1, dts.getIdProducto());
            pst.setString(2, dts.getSerie());
            pst.setString(3, dts.getNombre());
            pst.setDate(4, (Date) dts.getFechaIngreso());
            pst.setDate(5, (Date) dts.getFechaVencimiento());
            pst.setDouble(6, dts.getPrecioCompra());
            pst.setDouble(7, dts.getPrecioVenta());
            pst.setInt(8, dts.getIdCategoria());
            
            int n= pst.executeUpdate();
            if (n!=0) {
                return true;
            }else{
                 return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
       
    }
     
     public boolean eliminar(M_Producto dts){
         sql=("{call sp_eliminar_producto(?)}");
         try {
             PreparedStatement pst =con.prepareStatement(sql);
             pst.setString(1, dts.getIdProducto());
             
             int n= pst.executeUpdate();
             
             if (n!=0) {
                 return true;
                 
             }else{
                 return false;
             }
         } catch (Exception e) {
             JOptionPane.showConfirmDialog(null, e);
             return false;
         }
     }
    
    
     public int generarIdProducto(){
            String sql =("select max(id_producto) as id from producto");
            int cod = 0;
            
            try {
                Statement st =con.createStatement();
                ResultSet rs=st.executeQuery(sql);
                if (rs.next()) {
                    cod=rs.getInt("id")+1;
                    
                }
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null,"Error sql:"+ e1.getMessage());
            }
            
            return cod;
        }
     
     public boolean disminuir(int idproducto, int cantidad){
         sql="update producto set cantidad=cantidad-?"+
                 " where id_producto=?";
         try {
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setInt(1, cantidad);
             pst.setInt(2, idproducto);
             int n =pst.executeUpdate();
             if (n!=0) {
                 return true;
             }else{
                 return false;
             }
         } catch (Exception e) {
             JOptionPane.showConfirmDialog(null, e);
             return false;
         }
     }
     
      public boolean aumentar(int idproducto, int cantidad){
         sql="update producto set cantidad=cantidad+?" + " where id_producto=?";
         try {
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setInt(1, cantidad);
             pst.setInt(2, idproducto);
             int n = pst.executeUpdate();
             if (n != 0) {
                 return true;
             }else{
                 return false;
             }
         } catch (Exception e) {
             JOptionPane.showConfirmDialog(null, e);
             return false;
         }
     }
}
