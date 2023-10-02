
package chapinmarket.backend.modelos;

/**
 *
 * @author CIROSS
 */
public class Existencia {
 
    private Producto producto;
    private Sucursal sucursal;
    private int cantidadBodega;
    private int cantidadEstante;
    private int numeroPasillo;

    public Existencia(Producto producto, Sucursal sucursal, int cantidadBodega) {
        this.producto = producto;
        this.sucursal = sucursal;
        this.cantidadBodega = cantidadBodega;
    }

    public Existencia(Producto producto, Sucursal sucursal, int cantidadBodega, int cantidadEstante, int numeroPasillo) {
        this.producto = producto;
        this.sucursal = sucursal;
        this.cantidadBodega = cantidadBodega;
        this.cantidadEstante = cantidadEstante;
        this.numeroPasillo = numeroPasillo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public int getCantidadBodega() {
        return cantidadBodega;
    }

    public void setCantidadBodega(int cantidadBodega) {
        this.cantidadBodega = cantidadBodega;
    }

    public int getCantidadEstante() {
        return cantidadEstante;
    }

    public void setCantidadEstante(int cantidadEstante) {
        this.cantidadEstante = cantidadEstante;
    }

    public int getNumeroPasillo() {
        return numeroPasillo;
    }

    public void setNumeroPasillo(int numeroPasillo) {
        this.numeroPasillo = numeroPasillo;
    }

    @Override
    public String toString() {
        return "Existencia{" + "codigo=" + producto.getCodigo() + " producto=" + producto.getNombre() + ", sucursal=" + sucursal.getId() + ", cantidadBodega=" + cantidadBodega + ", cantidadEstante=" + cantidadEstante + ", numeroPasillo=" + numeroPasillo + '}';
    }
    
    
    
    
}
