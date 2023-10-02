
package chapinmarket.backend.modelos;

/**
 *
 * @author CIROSS
 */
public class Venta {
    
    private int id;
    private Factura factura;
    private Producto producto;
    private double monto;

    public Venta(Factura factura, Producto producto, double monto) {
        this.factura = factura;
        this.producto = producto;
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    
}
