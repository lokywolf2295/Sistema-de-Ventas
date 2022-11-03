package CapaLogica;

import CapaDatos.M_Categoria;
import java.sql.*;
import javax.swing.JOptionPane;

public class N_Categoria {

    private Conexion SQL = new Conexion();
    private Connection con = SQL.conectar();
    String sql ="";
    
    public boolean insertar(M_Categoria dts){
        sql=("{call Registrar_Categoria(?)}");
        
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, dts.getNcategoria());
            
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
}
