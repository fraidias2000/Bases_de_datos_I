/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Recursos.ImagenInicial;
import Conexion.ConexionAccess;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACULPLAY
 */
public class Inicio implements ActionListener{
    
    private static Inicio instancia = null;
    
    private JFrame ventanaInicio;
    private JButton mostrar;
    private JButton modificar;
    private JButton borrar;
    private JButton insertar;
    private ImageIcon icono;
    private ImagenInicial miImagen;
    
    private Mostrar mostrarMiBase ;
    private Insertar insertarEnMibase;
    private Borrar borrarRegistro;
    private Modificar modificarTabla;
    
    public static JScrollPane ScrollPersona, ScrollJugador, ScrollEntrenador;
    public static JTable tablaPersona, tablaJugador, tablaEntrenador;
    public static String titulosPersona [] = {"Codigo","nombre", 
        "Fecha_Nacimiento" , "Numero_telefono", "Direccion",
        "Nombre_club","Fecha_Baja","Importe"};
    public static String titulosJugador[] = {"Codigo", "Peso", "Altura"};
    public static String titulosEntrenador[] = {"Codigo", "Fecha_Inicio"};
    
    public static DefaultTableModel tablaModelPersona;
    public static DefaultTableModel tablaModelJugador;
    public static DefaultTableModel tablaModelEntrenador;
    public static Statement sentenciaInicio;
    
    private static final String MOSTRAR = "MOSTRAR";
    private static final String MODIFICAR = "MODIFICAR";
    private static final String BORRAR = "BORRAR";
    private static final String INSERTAR = "INSERTAR";
    private static final String BASES_DATOS = "Base de datos Toriko";
    
    private static final String RUTA_RECURSOS = "/recursos/";
  private static final String ICONO_APLICACION = "Captura.PNG";

    public Inicio() { 
        tablaPersona = new JTable();           
        ScrollPersona = new JScrollPane(tablaPersona);
        
        tablaJugador = new JTable();  
        ScrollJugador = new JScrollPane(tablaJugador);
        
         tablaEntrenador = new JTable();  
        ScrollEntrenador = new JScrollPane(tablaEntrenador);
        
        
        refrescarBase();
        crearVentanaInicio(); 
    }
    
    public static void refrescarBase(){
        tablaModelPersona = new DefaultTableModel();
        tablaModelEntrenador = new DefaultTableModel();
        tablaModelJugador = new DefaultTableModel();
        cargarColumasPersona();
        cargarDatosPersona() ;
        
        cargarColumnasJugador();
        cargarDatosJugador() ;
          
        cargarColumnasEntrenador();
        cargarDatosEntrenador() ;     
    }
    
    
    /*
    * Crea una unica instancia
    */
    public static synchronized Inicio devolverInstancia()
                             throws FileNotFoundException {
    if (instancia == null)
      instancia = new Inicio();    
    return instancia;
  } 
    
    /*
     * Crea la ventana principal
     */
    public void crearVentanaInicio(){
        ventanaInicio = new JFrame(BASES_DATOS);
        ventanaInicio.addWindowListener(new WindowAdapter() {
      
            @Override
      public void windowClosing(WindowEvent e) {
            ventanaInicio.dispose();
      } });
        JPanel panelBotones = new JPanel();
        
           
        //Creamos los 4 botones superiores
        ventanaInicio.getContentPane().setLayout(new BorderLayout());
        crearBotones(panelBotones);
        ventanaInicio.getContentPane().add(panelBotones, BorderLayout.NORTH);
        
        //Creamos y pintamos la imagen
        icono = new ImageIcon(this.getClass().getResource(RUTA_RECURSOS + ICONO_APLICACION));
        JPanel panelCentral = new JPanel();
        crearImagenCentral(panelCentral);
        ventanaInicio.add(panelCentral, BorderLayout.CENTER);
        
        //Damos valores a la ventana
        ventanaInicio.setResizable(false);    
        ventanaInicio.pack();  // ajusta ventana y sus componentes
        ventanaInicio.setVisible(true);
        ventanaInicio.setLocationRelativeTo(null);
    }
    
    public void crearImagenCentral(JPanel panel){
        miImagen = new ImagenInicial();
        miImagen.setIcon(icono);
        panel.add(miImagen);
    }
    
    /*
    *Crea los botones principales
    */
    public void crearBotones(JPanel panel){
    mostrar = crearBotonBarraHerramientas(MOSTRAR);
    modificar = crearBotonBarraHerramientas(MODIFICAR);
    borrar = crearBotonBarraHerramientas(BORRAR);
    insertar = crearBotonBarraHerramientas(INSERTAR);
    
    panel.add(mostrar);
    panel.add(modificar);
    panel.add(borrar);
    panel.add(insertar);
    }
    
  
      /**
        * Crea botón barra de herramientas
         */ 
    private JButton crearBotonBarraHerramientas(String etiqueta) {
        JButton boton = new JButton(etiqueta);
        boton.addActionListener(this); 
        boton.setActionCommand(etiqueta);
        return boton;
    }  

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            notificacionAControl(e.getActionCommand());
        } catch (IOException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    * Recibe notificación de un evento de la interfaz de usuario
    */
    private void notificacionAControl(String evento) throws IOException{
        switch(evento) {
            case MOSTRAR:
                mostrarMiBase = new Mostrar();
                break;
                
            case INSERTAR:
                insertarEnMibase = new Insertar();
                break;
                
            case BORRAR:
                borrarRegistro = new Borrar();
                break;
            case MODIFICAR:
                modificarTabla = new Modificar();
                break;     
        }
    }
    
    
    public static void cargarColumasPersona(){    
        for (int i = 0; i < titulosPersona.length; i++) {
           tablaModelPersona.addColumn(titulosPersona[i]);
        }   
        tablaPersona.setModel(tablaModelPersona);    
    }
        
    public static void cargarColumnasJugador(){
        for (int i = 0; i < titulosJugador.length; i++) {
            tablaModelJugador.addColumn(titulosJugador[i]);
        }
        tablaJugador.setModel(tablaModelJugador);
    }
        
    public static void cargarColumnasEntrenador(){
        for (int i = 0; i < titulosEntrenador.length; i++) {
            tablaModelEntrenador.addColumn(titulosEntrenador[i]);
        }
        tablaEntrenador.setModel(tablaModelEntrenador);
    }
        
    public static void cargarDatosEntrenador() {   
        Object  datos[] = new Object[2];   
        String sql = "select * from Entrenador";  
        sentenciaInicio = ConexionAccess.sentencia;
        try {
            ResultSet resultado = sentenciaInicio.executeQuery(sql); 

            while (resultado.next()) {
                datos[0] = resultado.getInt(titulosEntrenador[0]); //codigo
                datos[1] = resultado.getString(titulosEntrenador[1]); //Fecha Inicio
                                 
                tablaModelEntrenador.addRow(datos);               
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar los Datos\n" + ex);
        }
    }
        
    public static void cargarDatosJugador() {   
        Object  datos[] = new Object[3];   
        String sql = "select * from Jugador";  
        
        sentenciaInicio = ConexionAccess.sentencia;
        try {
            ResultSet resultado = sentenciaInicio.executeQuery(sql); 

        while (resultado.next()) {
            datos[0] = resultado.getInt(titulosJugador[0]); //codigo
            datos[1] = resultado.getString(titulosJugador[1]); //Altura
            datos[2] = resultado.getString(titulosJugador[2]); //Peso                  
            tablaModelJugador.addRow(datos);         
                
        }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar los Datos\n" + ex);
        }
    }  
        
    public static void cargarDatosPersona() {   
        Object  datos[] = new Object[8];   
              
        try {
            sentenciaInicio = ConexionAccess.sentencia;
            String sql = "select * from Persona";
            ResultSet resultado = sentenciaInicio.executeQuery(sql); 
                   
            while (resultado.next()) { 
                datos[0] = resultado.getInt(titulosPersona[0]); //codigo
                datos[1] = resultado.getString(titulosPersona[1]); //nombre
                datos[2] = resultado.getString(titulosPersona[2]); //fecha nacimiento
                datos[3] = resultado.getString(titulosPersona[3]); // numeroTelefono
                datos[4] = resultado.getString(titulosPersona[4]); // direccion
                datos[5] = resultado.getString(titulosPersona[5]); // nombre_club
                datos[6] = resultado.getString(titulosPersona[6]); // fecha baja
                datos[7] = resultado.getString(titulosPersona[7]); // importe                         
                tablaModelPersona.addRow(datos);              
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar los Datos\n" + ex);
        }
    }       
}