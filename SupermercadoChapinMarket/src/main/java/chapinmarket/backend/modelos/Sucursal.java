
package chapinmarket.backend.modelos;

/**
 *
 * @author CIROSS
 */
public class Sucursal {
    
    private String id;
    private String nombre;
    private String direccion;

    public Sucursal(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Sucursal(String id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Sucursal() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    
    
}
