
package chapinmarket.backend.conexiondb;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author CIROSS
 */
public class Conexion {
    
    public static Connection dbConnection;
    String url = "jdbc:postgresql://localhost:5432/chapinmarket";
    String user = "postgres";
    String password = "012345";
    
    public boolean inicializarConexion() {
        try {
            dbConnection = DriverManager.getConnection(url, user, password);
            return true;
        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            return false;
        }
    }
}
