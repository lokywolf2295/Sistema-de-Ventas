package CapaLogica;

import CapaDatos.M_Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class N_Empleado {

    private Conexion SQL = new Conexion();
     private Connection con = SQL.conectar();
     String sql= "";
     
     
   
     public DefaultTableModel mostrar(String buscar){
         DefaultTableModel modelo;
         
         String[] titulos = {"Codigo","Nombres","Apellidos","Dni","Telefono","Direccion"};
         modelo=new DefaultTableModel(null, titulos);
         String[] registro = new String[6];
         
         sql=("sp_buscar_empleado'"+buscar+"'");
         
         try {
             Statement st=con.createStatement();
             ResultSet rs= st.executeQuery(sql);
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
       
     public boolean insertar(M_Empleado dts){
      sql=("{call sp_guardar_empleado (?,?,?,?,?,?)}");
         try {
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setString(1,dts.getIdEmpleado());
             pst.setString(2,dts.getNombre());
             pst.setString(3,dts.getApellidos());
             pst.setString(4,dts.getDni());
             pst.setString(5,dts.getTelefono());
             pst.setString(6,dts.getDireccion());
             
             int n = pst.executeUpdate();
             if (n != 0) {
                 return  true;
             }else{
                 return false;
             }
             
         } catch (Exception e) {
             JOptionPane.showConfirmDialog(null, e);
             return false;
         }
     }
     
     public boolean editar(M_Empleado dts){
      sql=("{call sp_editar_empleado (?,?,?,?,?,?)}");
         try {
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setString(1,dts.getIdEmpleado());
             pst.setString(2,dts.getNombre());
             pst.setString(3,dts.getApellidos());
             pst.setString(4,dts.getDni());
             pst.setString(5,dts.getTelefono());
             pst.setString(6,dts.getDireccion());
             
             int n = pst.executeUpdate();
             if (n!=0) {
                 return  true;
             }else{
                 return false;
             }
             
         } catch (Exception e) {
             JOptionPane.showConfirmDialog(null, e);
             return false;
         }
     }
    
    public boolean eliminar(M_Empleado dts){
      sql=("{call sp_eliminar_empleado (?)}");
         try {
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setString(1,dts.getIdEmpleado());
            
             
             int n = pst.executeUpdate();
             if (n!=0) {
                 return  true;
             }else{
                 return false;
             }
             
         } catch (Exception e) {
             JOptionPane.showConfirmDialog(null, e);
             return false;
         }
     }
    public int gernerarIdEmpleado(){
            String sql = ("select max(id_empleado) as id from empleados");
            int cod =0;
            
            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs.next()) {
                    cod = rs.getInt("id")+1;
                    
                }
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null,"Error sql:"+ e1.getMessage());
            }
            return cod;
        }  
    
}
