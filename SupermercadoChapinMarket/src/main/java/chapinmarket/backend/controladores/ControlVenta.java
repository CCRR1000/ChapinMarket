
package chapinmarket.backend.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

import chapinmarket.backend.conexiondb.Conexion;
import chapinmarket.backend.enums.CommSQL;
import chapinmarket.backend.modelos.Empleado;
import chapinmarket.backend.modelos.Venta;

/**
 *
 * @author CIROSS
 */
public class ControlVenta {
    
    public static boolean ejecutarInstruccionPSQL(CommSQL operacion, Venta venta) {
        String instruccion;
        switch(operacion) {
            case DELETE:
                instruccion = "DELETE FROM GestionVenta.Venta WHERE id=?";
                break;
            case INSERT:
                instruccion = "INSERT INTO GestionVenta.Venta (factura,producto,monto) VALUES (?,?,?)";
                break;
            case UPDATE:
                instruccion = "UPDATE GestionVenta.Venta SET factura=?,producto=?,monto=? WHERE id=?";
                break;
            default:
                System.out.println("Operacion incorrecta, solo se aceptan los valores constantes de esta clase");
                return false;
        }
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(instruccion)) {
            if (operacion.ordinal()==CommSQL.DELETE.ordinal()) {
                preSt.setInt(1, venta.getId());
            } else {
                preSt.setInt(1, venta.getFactura().getNoDocumento());
                preSt.setString(2, venta.getProducto().getCodigo());
                preSt.setDouble(3, venta.getMonto());

                if (operacion.ordinal()==CommSQL.UPDATE.ordinal()) {
                    preSt.setInt(4, venta.getId());
                }
            }
            preSt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Ocurrió un error al ejecutar la instruccion: " + e.getMessage());
            return false;
        }
    }

    public static void mostrarRegistros(DefaultTableModel modelotabla, Empleado empleado) {
        String consulta = "SELECT * FROM GestionVenta.Venta";
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(consulta)) {
            
            ResultSet result = preSt.executeQuery();
            Object datos[] = new Object[4];
            
            while (result.next()) {  
                datos[0] = result.getInt("id");
                datos[1] = result.getInt("noDocumento");
                datos[2] = result.getString("producto");
                datos[3] = result.getDouble("monto");
                modelotabla.addRow(datos);
                System.out.println("No. Documento: " + result.getInt("noDocumento") + " - Cliente: " + result.getString("cliente"));
            }
        } catch (Exception e) {
            System.err.println("Error al visualizar registros: " + e.getMessage());
        }
    }
}
