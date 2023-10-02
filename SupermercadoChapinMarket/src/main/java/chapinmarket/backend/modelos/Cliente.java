
package chapinmarket.backend.modelos;

/**
 *
 * @author CIROSS
 */
public class Cliente {
    private String nit;
    private String nombre;
    private String direccion;
    private double totalGastado;
    private double consumoTarjeta;

    public Cliente(String nit, String nombre) {
        this.nit = nit;
        this.nombre = nombre;
    }
    
    public Cliente() {
        this.nit = "C/F";
        this.nombre = "Consumidor Final";
    }

    public Cliente(String nit, String nombre, String direccion, double totalGastado, double consumoTarjeta) {
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.totalGastado = totalGastado;
        this.consumoTarjeta = consumoTarjeta;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(double totalGastado) {
        this.totalGastado = totalGastado;
    }

    public double getConsumoTarjeta() {
        return consumoTarjeta;
    }

    public void setConsumoTarjeta(double consumoTarjeta) {
        this.consumoTarjeta = consumoTarjeta;
    }
    
    
}
