
package chapinmarket.backend.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

import chapinmarket.backend.conexiondb.Conexion;
import chapinmarket.backend.enums.CommSQL;
import chapinmarket.backend.modelos.Tarjeta;

/**
 *
 * @author CIROSS
 */
public class ControlTarjeta {
    
    public boolean ejecutarInstruccionPSQL(CommSQL operacion, Tarjeta tarjeta) {

        String instruccion;
        switch (operacion) {
            case DELETE:
                instruccion = "DELETE FROM GestionVenta.Tarjeta WHERE numero_tarjeta=?";
                break;
            case INSERT:
                instruccion = "INSERT INTO GestionVenta.Tarjeta (categoria,cliente,puntos) VALUES (?,?,?)";
                break;
            case UPDATE:
                instruccion = "UPDATE FROM GestionVenta.Tarjeta SET categoria=?,cliente=?,puntos=? WHERE numero_tarjeta=?";
                break;
            default:
                System.out.println("Operacion incorrecta, solo se aceptan los valores constantes de esta clase");
                return false;
        }
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(instruccion)) {
            if (operacion.ordinal()==CommSQL.DELETE.ordinal()) {
                preSt.setInt(1, tarjeta.getNumeroTarjeta());
            } else {
                preSt.setString(1, tarjeta.getCategoria().name());
                preSt.setString(2, tarjeta.getCliente().getNit());
                preSt.setInt(3, tarjeta.getPuntos());

                if (operacion.ordinal()==CommSQL.UPDATE.ordinal()) {
                    preSt.setInt(4, tarjeta.getNumeroTarjeta());
                }
            }
            preSt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Ocurrió un error al ejecutar la instruccion: " + e.getMessage());
            return false;
        }
    }

    public void mostrarRegistros(DefaultTableModel modelotabla, Tarjeta tarjeta) {
        // String consulta = "SELECT * FROM GestionVenta.Tarjeta";
        String consulta = 
                "SELECT t.numero_tarjeta, t.categoria, c.nit, c.nombre, t.puntos " + 
                "FROM GestionVenta.Tarjeta AS t " + 
                "JOIN GestionVenta.Cliente AS c " + 
                "ON t.cliente = c.nit";
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(consulta)) {
            
            ResultSet result = preSt.executeQuery();
            Object datos[] = new Object[5];
            
            while (result.next()) {  
                datos[0] = result.getInt("t.numero_tarjeta");
                datos[1] = result.getString("t.categoria");
                datos[2] = result.getString("c.nit");
                datos[3] = result.getInt("c.nombre");
                datos[4] = result.getInt("t.puntos");
                modelotabla.addRow(datos);
                //System.out.println("No. Documento: " + result.getInt("noDocumento") + " - Cliente: " + result.getString("cliente"));
            }
        } catch (Exception e) {
            System.err.println("Error al visualizar registros: " + e.getMessage());
        }
    }
}
