package CapaDatos;

import java.util.Date;

public class M_Producto {

    private String idProducto;
    private int idCategoria;
    private String serie;
    private String nombre;
    private Date fechaIngreso;
    private Date fechaVencimiento;
    private double precioCompra;
    private double precioVenta;

    public M_Producto() {
    }

    public M_Producto(String idProducto, int idCategoria, String serie, String nombre, Date fechaIngreso, Date fechaVencimiento, double precioCompra, double precioVenta, String cantidad) {
        this.idProducto = idProducto;
        this.idCategoria = idCategoria;
        this.serie = serie;
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
        this.fechaVencimiento = fechaVencimiento;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }  
}
