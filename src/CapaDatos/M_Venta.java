package CapaDatos;

import java.sql.Date;

public class M_Venta {

    private int idVenta;
    private Date fecha;
    private String hora;
    private String serie;
    private String num_documento;
    private String tipo_documento;
    private Double subtotal;
    private Double iva;
    private Double total;
    private String estado;
    private  int idUsuario;
    private  String idCliente;

    public M_Venta() {
    }

    public M_Venta(int idVenta, Date fecha, String hora, String serie, String num_documento, String tipo_documento, Double subtotal, Double iva, Double total, String estado, int idUsuario, String idCliente) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.hora = hora;
        this.serie = serie;
        this.num_documento = num_documento;
        this.tipo_documento = tipo_documento;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.estado = estado;
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
    }

    public int getIdventa() {
        return idVenta;
    }

    public void setIdventa(int idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNum_documento() {
        return num_documento;
    }

    public void setNum_documento(String num_documento) {
        this.num_documento = num_documento;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
}
