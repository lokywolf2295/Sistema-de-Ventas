package CapaDatos;

import java.sql.Date;

public class M_Compra {

    private int idCompra;
    private Date fecha;
    private String hora;
    private String numDocumento;
    private String tipoDocumento;
    private Double subtotal;
    private Double iva;
    private Double total;
    private String estado;
    private int idUsuario;
    private String idProveedor;

    public M_Compra() {
    }

    public M_Compra(int idcompra, Date fecha, String hora, String numDocumento, String tipoDocumento, Double subtotal, Double igv, Double total, String estado, int idUsuario, String idProveedor) {
        this.idCompra = idcompra;
        this.fecha = fecha;
        this.hora = hora;
        this.numDocumento = numDocumento;
        this.tipoDocumento = tipoDocumento;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.estado = estado;
        this.idUsuario = idUsuario;
        this.idProveedor = idProveedor;
    }

    public int getIdcompra() {
        return idCompra;
    }

    public void setIdcompra(int idcompra) {
        this.idCompra = idcompra;
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

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
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

    public void setIva(Double igv) {
        this.iva = igv;
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

    public int getIdusuario() {
        return idUsuario;
    }

    public void setIdusuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdproveedor() {
        return idProveedor;
    }

    public void setIdproveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }
}
