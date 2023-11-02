/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Conexion.ConexionAccess;
import Vista.Inicio;
import java.io.FileNotFoundException;
import java.sql.Connection;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author ACULPLAY
 */
public class BaseDatos{
     private Inicio inicioVista;
     ConexionAccess miConexion;

    public BaseDatos() throws FileNotFoundException { 
        miConexion = new ConexionAccess();
        inicioVista = Inicio.devolverInstancia();
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
         new BaseDatos();
    }

     
}
