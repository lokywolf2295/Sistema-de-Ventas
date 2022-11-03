package CapaDatos;

public class M_Usuario {

   private int idUsuario;
   private String idEmpleado;
   private String usuario;
   private String password;
   private String acceso;
   private String estado;

    public M_Usuario() {
    }

    public M_Usuario(int idUsuario, String idEmpleado, String usuario, String password, String acceso, String estado) {
        this.idUsuario = idUsuario;
        this.idEmpleado = idEmpleado;
        this.usuario = usuario;
        this.password = password;
        this.acceso = acceso;
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
