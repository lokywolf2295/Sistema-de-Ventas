package CapaDatos;

public class M_Empleado {
    
   private String idEmpleado;
    private String nombre;
    private String apellidos;
    private String dni;
    private String telefono;
    private String direccion;
    
    public M_Empleado() {
    }

    public M_Empleado(String id_empleado, String nombre, String apellidos, String dni, String telefono, String direccion) {
        this.idEmpleado = id_empleado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String id_empleado) {
        this.idEmpleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }   
}
