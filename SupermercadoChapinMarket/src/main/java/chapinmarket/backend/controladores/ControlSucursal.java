
package chapinmarket.backend.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import chapinmarket.backend.conexiondb.Conexion;
import chapinmarket.backend.enums.CommSQL;
import chapinmarket.backend.modelos.Sucursal;

/**
 *
 * @author CIROSS
 */
public class ControlSucursal {

    private ArrayList<Sucursal> listaSucursales = new ArrayList<>();;

    public boolean ejecutarInstruccionPSQL(CommSQL operacion, Sucursal sucursal) {
        String instruccion;
        switch(operacion) {
            case DELETE:
                instruccion = "DELETE FROM Sucursales.Sucursal WHERE id=?";
                break;
            case INSERT:
                instruccion = "INSERT INTO Sucursales.Sucursal VALUES (?,?,?)";
                break;
            case UPDATE:
                instruccion = "UPDATE Sucursales.Sucursal SET id=?,nombre=?,direccion=? WHERE id=?";
                break;
            default:
                System.out.println("Operacion incorrecta, solo se aceptan los valores constantes de esta clase");
                return false;
        }
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(instruccion)) {
            preSt.setString(1, sucursal.getId());
            if (operacion.ordinal()>=CommSQL.INSERT.ordinal()) {
                preSt.setString(2, sucursal.getNombre());
                preSt.setString(3, sucursal.getDireccion());
            }
            if (operacion.ordinal()==CommSQL.UPDATE.ordinal()) {
                preSt.setString(4, sucursal.getId());
            }
            preSt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Ocurrió un error al ejecutar la instruccion: " + e.getMessage());
            return false;
        }
        
    }

    public void mostrarRegistros(DefaultTableModel modelotabla) {
        String consulta = "SELECT * FROM Sucursales.Sucursal";
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(consulta)) {
            
            ResultSet result = preSt.executeQuery();
            Object datos[] = new Object[3];
            
            while (result.next()) {  
                datos[0] = result.getString("id");
                datos[1] = result.getString("nombre");
                datos[2] = result.getString("direccion");
                modelotabla.addRow(datos);
                System.out.println("ID: " + datos[0] + ", Nombre: " + datos[1]);
            }
        } catch (Exception e) {
            System.err.println("Error al visualizar registros: " + e.getMessage());
        }
    }
    
    public ArrayList<Sucursal> generarListaSucursales() {
        
        String consulta = "SELECT id FROM Sucursales.Sucursal";
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(consulta)) {
            ResultSet result = preSt.executeQuery();
            while (result.next()) {  
                String idText = result.getString("id");
                Sucursal suc = getSucursalPorId(idText);
                listaSucursales.add(suc);
            }
        } catch (Exception e) {
            System.err.println("Error al obtener registros: " + e.getMessage());
        }
        return listaSucursales;
    }
    
    public static Sucursal getSucursalPorId(String idSucursal) {
        String consulta = "SELECT * FROM Sucursales.Sucursal WHERE id=?";
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(consulta)) {
            preSt.setString(1, idSucursal);
            ResultSet result = preSt.executeQuery();

            while (result.next()) {
                String id = result.getString("id");
                String nombre = result.getString("nombre");
                String direccion = result.getString("direccion");
                
                return new Sucursal(id, nombre, direccion);
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al ejecutar la instruccion: " + e.getMessage());
        }
        return null;
    }

}
