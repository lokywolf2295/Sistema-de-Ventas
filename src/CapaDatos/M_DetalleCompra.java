package CapaDatos;

public class M_DetalleCompra {

    private int idDetalleCompra;
    private int idCompra;
    private String idProducto;
    private int cantidad;
    private Double precio;
    private Double total;

    public M_DetalleCompra() {
    }

    public M_DetalleCompra(int idDetalleCompra, int idCompra, String idProducto, int cantidad, Double precio, Double total) {
        this.idDetalleCompra = idDetalleCompra;
        this.idCompra = idCompra;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }

    public int getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(int idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
