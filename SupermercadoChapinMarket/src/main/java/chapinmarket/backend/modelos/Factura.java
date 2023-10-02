
package chapinmarket.backend.modelos;

import java.util.Date;

/**
 *
 * @author CIROSS
 */
public class Factura {
    
    private int noDocumento;
    private Cliente cliente;
    private java.util.Date fechaDate;
    private java.sql.Date fechaSql;
    private double totalConsumo;
    private double totalDescuento;
    private Empleado cajero;
    private Sucursal sucursal;
    private static int ultimaFactura;

    public Factura(int noDocumento, Cliente cliente, Empleado cajero, Sucursal sucursal) {
        this.noDocumento = noDocumento;
        this.cliente = cliente;
        this.cajero = cajero;
        this.sucursal = sucursal;
    }

    public Factura(int noDocumento, Cliente cliente, java.util.Date fechaDate, Empleado cajero, Sucursal sucursal) {
        this.noDocumento = noDocumento;
        this.cliente = cliente;
        this.fechaDate = fechaDate;
        this.fechaSql = new java.sql.Date(fechaDate.getTime());;
        this.cajero = cajero;
        this.sucursal = sucursal;
    }

    public Factura(int noDocumento, Cliente cliente, java.sql.Date fechaSql, Empleado cajero, Sucursal sucursal) {
        this.noDocumento = noDocumento;
        this.cliente = cliente;
        this.fechaSql = fechaSql;
        this.cajero = cajero;
        this.sucursal = sucursal;
    }

    public Factura(int noDocumento, Cliente cliente, java.sql.Date fechaSql, double totalConsumo, double totalDescuento,
            Empleado cajero, Sucursal sucursal) {
        this.noDocumento = noDocumento;
        this.cliente = cliente;
        this.fechaSql = fechaSql;
        this.totalConsumo = totalConsumo;
        this.totalDescuento = totalDescuento;
        this.cajero = cajero;
        this.sucursal = sucursal;
    }

    public Factura(int noDocumento, Cliente cliente, Date fechaDate, java.sql.Date fechaSql, double totalConsumo, double totalDescuento, Empleado cajero, Sucursal sucursal) {
        this.noDocumento = noDocumento;
        this.cliente = cliente;
        this.fechaDate = fechaDate;
        this.fechaSql = fechaSql;
        this.totalConsumo = totalConsumo;
        this.totalDescuento = totalDescuento;
        this.cajero = cajero;
        this.sucursal = sucursal;
    }

    public int getNoDocumento() {
        return noDocumento;
    }

    public void setNoDocumento(int noDocumento) {
        this.noDocumento = noDocumento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaDate() {
        return fechaDate;
    }

    public void setFechaDate(Date fechaDate) {
        this.fechaDate = fechaDate;
    }

    public java.sql.Date getFechaSql() {
        return fechaSql;
    }

    public void setFechaSql(java.sql.Date fechaSql) {
        this.fechaSql = fechaSql;
    }

    public double getTotalConsumo() {
        return totalConsumo;
    }

    public void setTotalConsumo(double totalConsumo) {
        this.totalConsumo = totalConsumo;
    }

    public double getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(double totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    public Empleado getCajero() {
        return cajero;
    }

    public void setCajero(Empleado cajero) {
        this.cajero = cajero;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public static int getUltimoDigito() {
        return ultimaFactura;
    }

    public static void setUltimoDigito(int ultimoDigito) {
        Factura.ultimaFactura = ultimoDigito;
    }
    
    
}
