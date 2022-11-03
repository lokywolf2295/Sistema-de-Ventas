package CapaLogica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    static Connection conn = null; //creamos la variable de tipo conecction null
    
    public static Connection conectar(){
        String url="jdbc:sqlserver://localhost:1433;databaseName=db_ventas";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error de sql: "+ e.getMessage()); 
        }
        System.out.println("La conexion se realizó de manera exitosa");
        try {
            conn=DriverManager.getConnection(url,"sa","1234");
            System.out.println("Los driver fueron bien cargados");
        } catch (SQLException e) {
            System.out.println("Error de conexion del driver: "+ e.getMessage());
        }
        return conn;
    }
    
}
