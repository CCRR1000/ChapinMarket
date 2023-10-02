
package chapinmarket.backend.modelos;

import chapinmarket.backend.enums.CategoriaTarjeta;

/**
 *
 * @author CIROSS
 */
public class Tarjeta {
    
    private int numeroTarjeta;
    private CategoriaTarjeta categoria;
    private Cliente cliente;
    private int puntos;
    
    public Tarjeta(CategoriaTarjeta categoria, Cliente cliente, int puntos) {
        this.categoria = categoria;
        this.cliente = cliente;
        this.puntos = puntos;
    }

    public int getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(int numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public CategoriaTarjeta getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaTarjeta categoria) {
        this.categoria = categoria;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    
}
