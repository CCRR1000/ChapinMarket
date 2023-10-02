
package chapinmarket.backend.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

import chapinmarket.backend.conexiondb.Conexion;
import chapinmarket.backend.enums.CommSQL;
import chapinmarket.backend.modelos.Empleado;
import chapinmarket.backend.modelos.Existencia;
import chapinmarket.backend.modelos.Producto;
import chapinmarket.backend.modelos.Sucursal;

/**
 *
 * @author CIROSS
 */
public class ControlExistencia {
    
    public static boolean ejecutarInstruccionPSQL(CommSQL operacion, Existencia existencia) {
        String instruccion;
        switch(operacion) {
            case DELETE:
                instruccion = "DELETE FROM Stock.Existencia WHERE producto=? AND sucursal=?";
                break;
            case INSERT:
                instruccion = "INSERT INTO Stock.Existencia VALUES (?,?,?)";
                break;
            case UPDATE:
                instruccion = "UPDATE Stock.Existencia SET producto=?,sucursal=?,cantidad_bodega=?,cantidad_estante=?,numero_pasillo=? WHERE producto=? AND sucursal=?";
                break;
            default:
                System.out.println("Operacion incorrecta, solo se aceptan los valores constantes de esta clase");
                return false;
        }
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(instruccion)) {
            preSt.setString(1, existencia.getProducto().getCodigo());
            preSt.setString(2, existencia.getSucursal().getId());
            if (operacion.ordinal()>=CommSQL.INSERT.ordinal()) {
                preSt.setInt(3, existencia.getCantidadBodega());
            }
            if (operacion.ordinal()==CommSQL.UPDATE.ordinal()) {
                preSt.setInt(4, existencia.getCantidadEstante());
                preSt.setInt(5, existencia.getNumeroPasillo());
                preSt.setString(6, existencia.getProducto().getCodigo());
                preSt.setString(7, existencia.getSucursal().getId());
            }
            preSt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Ocurrió un error al ejecutar la instruccion: " + e.getMessage());
            return false;
        }
    }
    
    public static Existencia getExistenciaPorIDS(String codigoProducto, String idSucursal) {
        String consulta = "SELECT * FROM Stock.Existencia WHERE producto=? AND sucursal=?";
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(consulta)) {
            preSt.setString(1, codigoProducto);
            preSt.setString(2, idSucursal);
            ResultSet result = preSt.executeQuery();

            while (result.next()) {
                String codigo = result.getString("producto");
                Producto producto = ControlProducto.getProductoPorCodigo(codigo);
                System.out.println(producto.toString());
                String idSuc = result.getString("sucursal");
                Sucursal sucursal = ControlSucursal.getSucursalPorId(idSuc);
                int cantidadBodega = result.getInt("cantidad_bodega");
                int cantidadEstante = result.getInt("cantidad_estante");
                int pasillo = result.getInt("numero_pasillo");
                
                
                return new Existencia(producto, sucursal, cantidadBodega, cantidadEstante, pasillo);
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al ejecutar la instruccion: " + e.getMessage());
        }
        return null;
    }

    public static void mostrarRegistros(DefaultTableModel modelotabla, Empleado empleado, boolean datosCompletos) {
        String consulta1 = "SELECT p.codigo_barras,p.nombre,p.marca,p.precio,e.cantidad_bodega,p.descripcion FROM Stock.Existencia AS e INNER JOIN Stock.Producto AS p ON e.producto = p.codigo_barras WHERE e.sucursal=? ORDER BY p.nombre";
        String consulta2 = "SELECT p.codigo_barras,p.nombre,p.marca,p.precio,e.cantidad_bodega,e.cantidad_estante,e.numero_pasillo,p.descripcion FROM Stock.Existencia AS e INNER JOIN Stock.Producto AS p ON e.producto = p.codigo_barras WHERE e.sucursal=? ORDER BY p.nombre";
        String consulta = datosCompletos ? consulta2 : consulta1;
        
        try (PreparedStatement preSt = Conexion.dbConnection.prepareStatement(consulta)) {
            String idSucursal = empleado.getSucursal().getId();
            preSt.setString(1, idSucursal);
            ResultSet result = preSt.executeQuery();
            int cantDatos = datosCompletos ? 8 : 6;
            Object datos[] = new Object[cantDatos];
            
            while (result.next()) {  
                datos[0] = result.getString("codigo_barras");
                datos[1] = result.getString("nombre");
                datos[2] = result.getString("marca");
                datos[3] = result.getDouble("precio");
                datos[4] = result.getInt("cantidad_bodega");
                
                if (!datosCompletos) {
                    datos[5] = result.getString("descripcion");
                } else {
                    datos[5] = result.getInt("cantidad_estante");
                    datos[6] = result.getInt("numero_pasillo");
                    datos[7] = result.getString("descripcion");
                }
                
                modelotabla.addRow(datos);
            }
        } catch (Exception e) {
            System.err.println("Error al visualizar registros: " + e.getMessage());
        }
    }
}
