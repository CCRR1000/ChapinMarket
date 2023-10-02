
package chapinmarket.backend.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

import chapinmarket.backend.conexiondb.Conexion;
import chapinmarket.backend.enums.CommSQL;
import chapinmarket.backend.modelos.Cliente;

/**
 *
 * @author CIROSS
 */
public class ControlCliente {
    
    public boolean ejecutarInstruccionPSQL(CommSQL operacion, Cliente cliente) {
        
        String instruccion;
        switch(operacion) {
            case DELETE:
                instruccion = "DELETE FROM GestionVenta.Cliente WHERE nit=?";
                break;
            case INSERT:
                instruccion = "INSERT INTO GestionVenta.Cliente VALUES (?,?)";
                break;
            case UPDATE:
                instruccion = "UPDATE GestionVenta.Cliente SET nit=?,nombre=?,direccion=?,total_gastado=?,consumo_con_tarjeta=? WHERE nit=?";
                break;
            default:
                System.out.println("Operacion incorrecta, solo se aceptan los valores constantes de esta clase");
                return false;
        }
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(instruccion)) {
            preSt.setString(1, cliente.getNit());
            if (operacion.ordinal()>=CommSQL.INSERT.ordinal()) {
                preSt.setString(2, cliente.getNombre());
            }
            if (operacion.ordinal()==CommSQL.UPDATE.ordinal()) {
                preSt.setString(3, cliente.getDireccion());
                preSt.setDouble(4, cliente.getTotalGastado());
                preSt.setDouble(5, cliente.getConsumoTarjeta());
                preSt.setString(6, cliente.getNit());
            }
            preSt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Ocurrió un error al ejecutar la instruccion: " + e.getMessage());
            return false;
        }
        
    }
    
    public void mostrarRegistros(DefaultTableModel modelotabla) {
        String consulta = "SELECT * FROM GestionVenta.Cliente";
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(consulta)) {
            
            ResultSet result = preSt.executeQuery();
            Object datos[] = new Object[5];
            
            while (result.next()) {  
                datos[0] = result.getString("nit");
                datos[1] = result.getString("nombre");
                datos[2] = result.getString("direccion");
                datos[3] = result.getString("total_gastado");
                datos[4] = result.getString("consumo_con_tarjeta");

                modelotabla.addRow(datos);
                System.out.println("NIT: " + result.getString("nit") + " - Nombre: " + result.getString("nombre") + " - Telefono: " + result.getString("telefono"));
            }
        } catch (Exception e) {
            System.err.println("Error al visualizar registros: " + e.getMessage());
        }
        
    }
    
    public static Cliente getClientePorNit(String nitCliente) {

        Cliente cliente = null;
        String consulta = "SELECT * FROM GestionVenta.Cliente WHERE nit=?";
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(consulta)) {
            preSt.setString(1, nitCliente);
            ResultSet result = preSt.executeQuery();

            while (result.next()) {
                String nit = result.getString("nit");
                String nombre = result.getString("nombre");
                String direccion = result.getString("direccion");
                Double totalGastos = result.getDouble("total_gastado");
                Double consumoTarjeta = result.getDouble("consumo_con_tarjeta");
                
                cliente = new Cliente(nit, nombre, direccion,totalGastos,consumoTarjeta);
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al ejecutar la instruccion: " + e.getMessage());
        }
        return cliente;
    }

}
