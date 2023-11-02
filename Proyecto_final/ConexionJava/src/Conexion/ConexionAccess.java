
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConexionAccess {   
    public static Connection conexion;
    public static Statement sentencia;
    
    
    public ConexionAccess(){
        conectarBaseDatos();
    }

    public Connection getConexion() {
        return conexion;
    }

    public Statement getSentencia() {
        return sentencia;
    }
      
    
    public void conectarBaseDatos() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar Dirver");
        }
        try {
            conexion = DriverManager.getConnection("jdbc:ucanaccess:"
                    + "//C:\\Users\\ACULPLAY\\Desktop\\PROYECTO\\BasesDatos\\TORIKO.accdb");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Error en la dirección de la base de datos");
        }
        try {
            sentencia = conexion.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Error al crear la conexión con la base de datos");
        }
    }
    
  
    //Cerrar la base de datos cuando cerramos la ventana principal
    private void fromWindowClosing (java .awt.event.WindowEvent evt){
    try {
            sentencia.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Error al cerrar la base de datos" + e);
        }
    }
}
