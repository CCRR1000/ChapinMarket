
package chapinmarket.backend;

import chapinmarket.backend.conexiondb.Conexion;
import chapinmarket.frontend.vistas.Login;
import chapinmarket.frontend.vistas.VistaAdministrador;
import chapinmarket.frontend.vistas.VistaBodega;
import chapinmarket.frontend.vistas.VistaCajero;
import chapinmarket.frontend.vistas.VistaInventario;
import javax.swing.JOptionPane;

/**
 *
 * @author CIROSS
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
//        String codigo = "00105";
//        int num = 0;
//        
//        System.out.println("String = "+codigo);
//        num = Integer.parseInt(codigo);
//        System.out.println("Int = " + num);
//        int lenCodigo = String.valueOf(num).length();
//        System.out.println("longitud = " + lenCodigo + "\n");
//
//        String fin = completarNumero(num, 8);
//        System.out.println("\ncodigo final = " + fin);
        Conexion con = new Conexion();
        
        if (con.inicializarConexion()) {
            Login login = new Login();
//            VistaCajero login = new VistaCajero();
//            VistaBodega login = new VistaBodega();
//            VistaInventario login = new VistaInventario();
//            VistaAdministrador login = new VistaAdministrador();

            login.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo conectar con la base de datos");
        }
    }   
    
    public static String completarNumero(int numero, int cantDigitos) {
        
        String codigotxt = String.valueOf(numero);
        
        while (codigotxt.length() < cantDigitos) {            
            codigotxt = "0" + codigotxt;
        }
        
        return codigotxt;
    }
    
}
