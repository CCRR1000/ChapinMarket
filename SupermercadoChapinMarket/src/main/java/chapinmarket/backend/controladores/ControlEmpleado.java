
package chapinmarket.backend.controladores;

import chapinmarket.backend.conexiondb.Conexion;
import chapinmarket.backend.enums.*;
import chapinmarket.backend.modelos.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CIROSS
 */
public class ControlEmpleado {
  
    public static Empleado validarEmpleado(String usuario, String contrasena) {
        Empleado empleado = obtenerEmpleadoPorId(usuario);
        System.out.println(empleado.toString());
        
        if (empleado!=null && contrasena.equals(empleado.getContrasena())) {
            return empleado;
        } else {
            System.out.println("La contrasena es incorrecta");
        }
        return null;
    }
    
    public static boolean ejecutarInstruccionPSQL(CommSQL operacion, Empleado empleado) {
        
        String instruccion;
        switch(operacion) {
            case DELETE:
                instruccion = "DELETE FROM Personal.Empleado WHERE usuario=?";
                break;
            case INSERT:
                instruccion = "INSERT INTO Personal.Empleado VALUES (?,?,?,?,?,?,?,?,?)";
                break;
            case UPDATE:
                instruccion = "UPDATE Personal.Empleado SET usuario=?,contrasena=?,cui=?,rol=?,sucursal=?,nombre=?,apellido=?,salario=? WHERE usuario=?";
                break;
            default:
                System.out.println("Operacion incorrecta, solo se aceptan los valores constantes de esta clase");
                return false;
        }
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(instruccion)) {
            preSt.setString(1, empleado.getUsuario());
            if (operacion.ordinal()>=CommSQL.INSERT.ordinal()) {
                preSt.setString(2, empleado.getContrasena());
                preSt.setString(3, empleado.getCUI());
                preSt.setString(4, empleado.getRol().toString());
                preSt.setString(5, empleado.getSucursal().getId());
                preSt.setString(6, empleado.getNombre());
                preSt.setString(7, empleado.getApellido());
                preSt.setDouble(8, empleado.getSalario());
                preSt.setInt(9, empleado.getNumeroCaja());

            }
            if (operacion.ordinal()==CommSQL.UPDATE.ordinal()) {
                preSt.setString(10, empleado.getUsuario());
            }
            preSt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Ocurrió un error al ejecutar la instruccion: " + e.getMessage());
            return false;
        }
        
    }
    
    public static void mostrarRegistros(DefaultTableModel modelotabla) {
        String consulta = "SELECT * FROM Personal.Empleado";
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(consulta)) {
            
            ResultSet result = preSt.executeQuery();
            Object datos[] = new Object[8];
            
            while (result.next()) {  
                datos[0] = result.getString("usuario");
                datos[1] = result.getString("nombre");
                datos[2] = result.getString("apellido");
                datos[3] = result.getString("rol");
                datos[5] = result.getString("cui");
                datos[6] = result.getString("salario");
                datos[7] = result.getDouble("numero_caja");
                
                if (result.getString("sucursal")==null) {
                    datos[4] = "";
                } else {
                    datos[4] = result.getString("sucursal");
                }

                if (result.getInt("numero_caja")==0) {
                    datos[7] = "";
                } else {
                    datos[7] = result.getInt("numero_caja");
                }
                modelotabla.addRow(datos);
            }
        } catch (Exception e) {
            System.err.println("Error al visualizar registros: " + e.getMessage());
        }
    }

    public static Empleado obtenerEmpleadoPorId(String usuarioEmpleado) {
        String consulta = "SELECT * FROM Personal.Empleado WHERE usuario=?";
        Empleado empleado = new Empleado();
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(consulta)) {
            preSt.setString(1, usuarioEmpleado);
            ResultSet result = preSt.executeQuery();

            while (result.next()) {
                String usuario = result.getString("usuario");
                String contrasena = result.getString("contrasena");
                String CUI = result.getString("cui");
                Rol rol = Empleado.getRol(result.getString("rol"));
                Sucursal sucursal = ControlSucursal.getSucursalPorId(result.getString("sucursal"));
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                double salario = result.getDouble("salario");
                int caja = result.getInt("numero_caja");
                empleado = new Empleado(usuario, contrasena, CUI, rol, sucursal, nombre, apellido, salario,caja);
            }
            
        } catch (Exception e) {
            System.out.println("Ocurrió un error al ejecutar la instruccion: " + e.getMessage());
            
        }
        return empleado;
    }
    
    
}
