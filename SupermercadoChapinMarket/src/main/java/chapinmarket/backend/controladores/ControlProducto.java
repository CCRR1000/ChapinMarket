
package chapinmarket.backend.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

import chapinmarket.backend.conexiondb.Conexion;
import chapinmarket.backend.enums.CommSQL;
import chapinmarket.backend.modelos.Producto;

/**
 *
 * @author CIROSS
 */
public class ControlProducto {
    
    public static boolean ejecutarInstruccionPSQL(CommSQL operacion, Producto producto) {
        String instruccion;
        switch(operacion) {
            case DELETE:
                instruccion = "DELETE FROM Stock.Producto WHERE codigo_barras=?";
                break;
            case INSERT:
                instruccion = "INSERT INTO Stock.Producto VALUES (?,?,?,?,?)";
                break;
            case UPDATE:
                instruccion = "UPDATE Stock.Producto SET codigo_barras=?,nombre=?,marca=?,precio=?,descripcion=? WHERE codigo_barras=?";
                break;
            default:
                System.out.println("Operacion incorrecta, solo se aceptan los valores constantes de esta clase");
                return false;
        }
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(instruccion)) {
            preSt.setString(1, producto.getCodigo());
            if (operacion.ordinal()>=CommSQL.INSERT.ordinal()) {
                preSt.setString(2, producto.getNombre());
                preSt.setString(3, producto.getMarca());
                preSt.setDouble(4, producto.getPrecio());
                preSt.setString(5, producto.getDescripcion());
            }
            if (operacion.ordinal()==CommSQL.UPDATE.ordinal()) {
                preSt.setString(6, producto.getCodigo());
            }
            preSt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Ocurrió un error al ejecutar la instruccion: " + e.getMessage());
            return false;
        }
    }

    
    public static void mostrarRegistros(DefaultTableModel modelotabla) {
        String consulta = "SELECT * FROM GestionVenta.Producto";
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(consulta)) {
            
            ResultSet result = preSt.executeQuery();
            Object datos[] = new Object[5];
            
            while (result.next()) {  
                datos[0] = result.getString("codigo_barras");
                datos[1] = result.getString("nombre");
                datos[2] = result.getString("marca");
                datos[3] = result.getDouble("precio");
                datos[4] = result.getString("descripcion");
                modelotabla.addRow(datos);
                //System.out.println("Codigo: " + result.getString("codigo_barras") + " - Nombre: " + result.getString("nombre") + " - Marca: " + result.getString("marca"));
            }
        } catch (Exception e) {
            System.err.println("Error al visualizar registros: " + e.getMessage());
        }
    }

    public static Producto getProductoPorCodigo(String codigoProducto) {
        String consulta = "SELECT * FROM Stock.Producto WHERE codigo_barras=?";
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(consulta)) {
            preSt.setString(1, codigoProducto);
            ResultSet result = preSt.executeQuery();

            while (result.next()) {
                String codigo = result.getString("codigo_barras");
                String nombre = result.getString("nombre");
                String marca = result.getString("marca");
                double precio = result.getDouble("precio");
                String descripcion = result.getString("descripcion");
                
                return new Producto(codigo, nombre, marca, precio, descripcion);
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al ejecutar la instruccion: " + e.getMessage());
        }
        return null;
    }
}
