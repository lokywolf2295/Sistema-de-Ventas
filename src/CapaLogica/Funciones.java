package CapaLogica;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Funciones {

    public static String getHoraActual(){
        Date hora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("hh,mm,ss");
        return formateador.format(hora);
        
    }
    
     public static String getFechaActual(){
        Date hora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        return formateador.format(hora);
        
    }
    
}
