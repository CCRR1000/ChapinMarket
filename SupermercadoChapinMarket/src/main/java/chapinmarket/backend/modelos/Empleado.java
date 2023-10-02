
package chapinmarket.backend.modelos;

import chapinmarket.backend.enums.Rol;

/**
 *
 * @author CIROSS
 */
public class Empleado {
    
    private String usuario;
    private String contrasena;
    private String CUI;
    private Rol rol;
    private Sucursal sucursal;
    private String nombre;
    private String apellido;
    private double salario;
    private int numeroCaja;

    public Empleado(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Empleado(String usuario, String contrasena, String CUI, Rol rol, String nombre, String apellido, double salario) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.CUI = CUI;
        this.rol = rol;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
    }

    public Empleado(String usuario, String contrasena, String CUI, Rol rol, Sucursal sucursal, String nombre, String apellido, double salario) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.CUI = CUI;
        this.rol = rol;
        this.sucursal = sucursal;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
    }

    public Empleado(String usuario, String contrasena, String CUI, Rol rol, Sucursal sucursal, String nombre, String apellido, double salario, int numeroCaja) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.CUI = CUI;
        this.rol = rol;
        this.sucursal = sucursal;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
        this.numeroCaja = numeroCaja;
    }

    public Empleado() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCUI() {
        return CUI;
    }

    public void setCUI(String CUI) {
        this.CUI = CUI;
    }

    public Rol getRol() {
        return rol;
    }

    public static Rol getRol(String roltxt) {
        if (roltxt.equals(Rol.ADMINISTRADOR.name())) 
            return Rol.ADMINISTRADOR;
        else if (roltxt.equals(Rol.BODEGA.name())) 
            return Rol.BODEGA;
        else if (roltxt.equals(Rol.INVENTARIO.name())) 
            return Rol.INVENTARIO;
        else 
            return Rol.CAJERO;
        
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getNumeroCaja() {
        return numeroCaja;
    }

    public void setNumeroCaja(int numeroCaja) {
        this.numeroCaja = numeroCaja;
    }

    @Override
    public String toString() {
        return "Empleado{" + "usuario=" + usuario + ", contrasena=" + contrasena + ", CUI=" + CUI + ", rol=" + rol + ", nombre=" + nombre + ", apellido=" + apellido + ", salario=" + salario + ", numeroCaja=" + numeroCaja + '}';
    }
    
    
}
