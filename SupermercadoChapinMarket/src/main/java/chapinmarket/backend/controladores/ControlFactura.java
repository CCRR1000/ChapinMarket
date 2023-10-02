
package chapinmarket.backend.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

import chapinmarket.backend.conexiondb.Conexion;
import chapinmarket.backend.enums.CommSQL;
import chapinmarket.backend.modelos.*;

/**
 *
 * @author CIROSS
 */
public class ControlFactura {
    
    public boolean ejecutarInstruccionPSQL(CommSQL operacion, Factura factura) {
        String instruccion;
        switch(operacion) {
            case DELETE:
                instruccion = "DELETE FROM GestionVenta.Factura WHERE noDocumento=?";
                break;
            case INSERT:
                instruccion = "INSERT INTO GestionVenta.Factura (cliente,fecha,total_consumo,total_descuento,cajero,sucursal) VALUES (?,?,?,?,?,?)";
                break;
            case UPDATE:
                instruccion = "UPDATE GestionVenta.Factura SET cliente=?,fecha=?,total_consumo=?,total_descuento=?,cajero=?,sucursal=? WHERE noDocumento=?";
                break;
            default:
                System.out.println("Operacion incorrecta, solo se aceptan los valores constantes de esta clase");
                return false;
        }
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(instruccion)) {
            if (operacion.ordinal()==CommSQL.DELETE.ordinal()) {
                preSt.setInt(1, factura.getNoDocumento());
            } else {
                preSt.setString(1, factura.getCliente().getNit());
                preSt.setDate(2, factura.getFechaSql());
                preSt.setDouble(3, factura.getTotalConsumo());
                preSt.setDouble(4, factura.getTotalDescuento());
                preSt.setString(5, factura.getCajero().getUsuario());
                preSt.setString(6, factura.getSucursal().getId());
                if (operacion.ordinal()==CommSQL.UPDATE.ordinal()) {
                    preSt.setInt(7, factura.getNoDocumento());
                }
            }
            preSt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Ocurrió un error al ejecutar la instruccion: " + e.getMessage());
            return false;
        }
    }

    public void mostrarRegistros(DefaultTableModel modelotabla) {
        String consulta = "SELECT * FROM GestionVenta.Factura";
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(consulta)) {
            
            ResultSet result = preSt.executeQuery();
            Object datos[] = new Object[7];
            
            while (result.next()) {  
                datos[0] = result.getString("noDocumento");
                datos[1] = result.getString("cliente");
                datos[2] = result.getDate("fecha");
                datos[3] = result.getDouble("total_gastado");
                datos[4] = result.getDouble("total_descuento");
                datos[5] = result.getString("cajero");
                datos[6] = result.getString("sucursal");
                modelotabla.addRow(datos);
                System.out.println("No. Documento: " + result.getString("noDocumento") + " - Cliente: " + result.getString("cliente"));
            }
        } catch (Exception e) {
            System.err.println("Error al visualizar registros: " + e.getMessage());
        }
    }

    public static int getNumeroUltimaFactura() {
        String consulta = "SELECT MAX(noDocumento) FROM GestionVenta.Factura";
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(consulta)) {
            ResultSet result = preSt.executeQuery();
            if (result.next()) {
                return result.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al ejecutar la instruccion: " + e.getMessage());
        }
        return -1;
    }

    public static Factura getFacturaPorNumero(String numeroFactura) {
        String consulta = "SELECT * FROM GestionVenta.Factura WHERE noDocumento=?";
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(consulta)) {
            preSt.setString(1, numeroFactura);
            preSt.executeUpdate();
            ResultSet result = preSt.executeQuery();

            while (result.next()) {
                int noDocumento = result.getInt("noDocumento");
                Cliente cliente = ControlCliente.getClientePorNit(result.getString("cliente"));
                java.sql.Date fechaSql = result.getDate("fecha");
                double tGastado = result.getDouble("total_gastado");
                double tDescuento = result.getDouble("total_descuento");
                Empleado cajero = ControlEmpleado.obtenerEmpleadoPorId(result.getString("cajero"));
                Sucursal sucursal = ControlSucursal.getSucursalPorId(result.getString("sucursal"));
                
                return new Factura(noDocumento, cliente, fechaSql, tGastado, tDescuento, cajero, sucursal);
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al ejecutar la instruccion: " + e.getMessage());
        }
        return null;
    }
}
