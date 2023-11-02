/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Conexion.ConexionAccess;
import static Vista.Inicio.sentenciaInicio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author ACULPLAY
 */
public class Modificar implements ActionListener{
    private final String CODIGO = "Codigo: ";
    private final String NOMBRE = "Nombre: ";
    private final String FECHA_NACIMIENTO = "Fecha nacimiento: ";
    private final String NUMERO = "Numero: ";
    private final String DIRECCION = "Direccion: ";
    private final String NOMBRE_CLUB = "Nombre club: ";
    private final String FECHA_BAJA = "Fecha baja: ";
    private final String IMPORTE = "Importe: ";
    private final String VACIO = "";
    private final String TABLA_PERSONA = "Tabla Persona";
    private final String TABLA_JUGADOR = "Tabla Jugador";
    private final String TABLA_ENTRENADOR = "Tabla Entrenador";
    private final String ALTURA = "Altura: ";
    private final String PESO = "Peso: ";
    private final String FECHA_INICIO = "Fecha Inicio: ";
    
    private int codigo;
    private String nombre;
    private String fechaNacimientoString;
    private String telefonoString;
    private int numeroTelefono;
    
    private String direccion;
    private String nombreClub;
    private String fechaBajaString;
    private String importeString;
    private int importe;
    private int altura;
    private int peso;
    private String fechaInicio;
    private Calendar fechaNacimiento;
    private Calendar fechaBaja;
            
    
    //BOTONES
    private final String GUARDAR = "Guardar";
    private final String SALIR = "Salir"; 
    private final String JUGADOR = "Jugador";
    private final String ENTRENADOR = "Entrenador";
    private JButton btnGuardar;
    private JButton aceptar;
    private JButton jugador;
    private JButton entrenador;

    
    
    private String titulos [] = {"Codigo","nombre", 
        "Fecha_Nacimiento" , "numTelefono", "direccion",
        "nombre_club","fecha_baja","importe"};
 
   
    private Connection con = null;
    private String sql;
    private Statement sentencia;
    private JScrollPane ScrollPersona, ScrollJugador, ScrollEntrenador;
    
    private JFrame ventana;
    private JComboBox equipos;
    
    private JTextField  txtcodigo, txtnombre, txtfecha_nacimiento,
            txtnumero, txtdireccion, txtfecha_baja, txtimporte,
            txtaltura, txtpeso, txtFechaEntrenador;;
    private JLabel lblcodigo, lblnombre, lblfecha_nacimiento,
            lblnumero, lbldireccion, lblnombre_club, 
            lblfecha_baja, lblimporte, tablaPersona, tablaJugador,
            tablaEntrenador,lblaltura, lblpeso, lblFechaEntrenador;;
    
    
    public Modificar(){
        ScrollPersona = Inicio.ScrollPersona;
        ScrollJugador = Inicio.ScrollJugador;
        ScrollEntrenador = Inicio.ScrollEntrenador;
        sentencia = ConexionAccess.sentencia;
        crearVentana();      
        cargarEquipos();
    }     
   
  private void crearVentana(){
        ventana = new JFrame(); 
        btnGuardar = crearBoton(GUARDAR);
        aceptar = crearBoton(SALIR);
        jugador = crearBoton(JUGADOR);
        entrenador = crearBoton(ENTRENADOR);
        
        
        txtcodigo = new JTextField();
        txtnombre = new JTextField();
        txtfecha_nacimiento = new JTextField();
        txtnumero = new JTextField();
        txtdireccion = new JTextField();
        
        equipos = new JComboBox(); 
        txtfecha_baja = new JTextField();
        txtimporte = new JTextField();   
        
        
        
        ventana.add(txtcodigo);
        ventana.add(txtnombre);
        ventana.add(txtfecha_nacimiento);
        ventana.add(txtnumero);
        ventana.add(txtdireccion);
        ventana.add(txtfecha_baja);
        ventana.add(txtimporte);
        
        lblcodigo = new JLabel(CODIGO); 
        lblnombre = new JLabel(NOMBRE);
        lblfecha_nacimiento = new JLabel(FECHA_NACIMIENTO);
        lblnumero = new JLabel(NUMERO);
        lbldireccion = new JLabel(DIRECCION);
        lblnombre_club = new JLabel(NOMBRE_CLUB);
        lblfecha_baja = new JLabel(FECHA_BAJA);
        lblimporte = new JLabel(IMPORTE); 
        
        
        tablaPersona  = new JLabel(TABLA_PERSONA);
        tablaJugador = new JLabel(TABLA_JUGADOR);
        tablaEntrenador = new JLabel(TABLA_ENTRENADOR);
        
        lblaltura = new JLabel(ALTURA);
        lblpeso = new JLabel(PESO);
        lblFechaEntrenador = new JLabel(FECHA_INICIO);
        txtaltura = new JTextField();
        txtpeso = new JTextField();
        txtFechaEntrenador = new JTextField();
        
        ventana.add(jugador);
        ventana.add(entrenador);
        
        ventana.add(lblaltura);
        ventana.add(lblpeso);
        ventana.add(lblFechaEntrenador);
        ventana.add(txtaltura);
        ventana.add(txtpeso);
        ventana.add(txtFechaEntrenador);
        
        ventana.add(lblcodigo);
        ventana.add(lblnombre);
        ventana.add(lblfecha_nacimiento);
        ventana.add(lblnumero);
        ventana.add(lbldireccion);
        ventana.add(lblnombre_club);
        ventana.add(equipos);
        ventana.add(lblfecha_baja);
        ventana.add(lblimporte);
        
        ventana.add(btnGuardar);
        ventana.add(aceptar);
        ventana.add(ScrollPersona);
        ventana.add(ScrollJugador);
        ventana.add(ScrollEntrenador);
        
        ventana.add(tablaPersona);
        ventana.add(tablaJugador);
        ventana.add(tablaEntrenador);
        
       
        
        tablaJugador.setBounds(470, 130, 100, 20);
        tablaPersona.setBounds(470, 0, 100, 20);
        tablaEntrenador.setBounds(470,270,100,20);
        
        ScrollPersona.setBounds(470, 20, 550, 100);
        ScrollJugador.setBounds(470, 150, 400, 100);
        ScrollEntrenador.setBounds(470,290,300,100);
        
        btnGuardar.setBounds(350,100,100,25);
        aceptar.setBounds(350,140,100,25);
        
      
        
        lblcodigo.setBounds(20,20,100,20);
        txtcodigo.setBounds(130,20,200,20);
        
        lblnombre.setBounds(20,50,100,20);
        txtnombre .setBounds(130,50,200,20);
        
        lblfecha_nacimiento.setBounds(20,85,100,20);
        txtfecha_nacimiento.setBounds(130,80,200,20);
        
        lblnumero.setBounds(20,110,100,20);
        txtnumero.setBounds(130,110,200,20);
        
        lbldireccion.setBounds(20,130,100,20);
        txtdireccion.setBounds(130,130,200,20);
        
        lblnombre_club.setBounds(20,150,100,20);
        equipos.setBounds(130,150,200,20);
        
        lblfecha_baja.setBounds(20,180,100,20);
        txtfecha_baja.setBounds(130,180,200,20);
        
        lblimporte.setBounds(20,210,100,20);
        txtimporte.setBounds(130,210,200,20);
        
        jugador.setBounds(20,240,100,20);
        entrenador.setBounds(130,240,100,20);
        
        lblaltura.setBounds(20,270,100,20);
        txtaltura.setBounds(130,270,200,20);
        
        txtpeso.setBounds(130,300,200,20);
        lblpeso.setBounds(20,300,100,20);
        
        lblFechaEntrenador.setBounds(20,270,100,20);
        txtFechaEntrenador.setBounds(130,270,200,20);
        
        
        lblaltura.setVisible(false);
        txtaltura.setVisible(false);
        txtpeso.setVisible(false);
        lblpeso.setVisible(false);
        lblFechaEntrenador.setVisible(false);
        txtFechaEntrenador.setVisible(false);
        
        
        ventana.setLayout(null);
        ventana.setSize(1100,500);
        ventana.setVisible(true);
  
  }
        
  /**
   * Crea botón barra de herramientas
   */ 
  private JButton crearBoton(String etiqueta) {
    JButton boton = new JButton(etiqueta);
    boton.addActionListener(this); 
    boton.setActionCommand(etiqueta);
    return boton;
  }  

    @Override
    public void actionPerformed(ActionEvent e) {  
        try {
            notificacionAControl(e.getActionCommand());
        
        } catch (SQLException ex) {
            Logger.getLogger(Modificar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Modificar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
       
    private void notificacionAControl(String evento) throws IOException, SQLException{
        switch(evento) {
            case GUARDAR:
                modificarDatos();              
                break;
            case SALIR:
                ventana.dispose();
                break;
            case JUGADOR:
                habilitarJugador();
                break;
                
            case ENTRENADOR:
                habilitarEntrenador();
                break;
        
        
        }}
    
    
       private void habilitarJugador(){
        lblFechaEntrenador.setVisible(false);
        txtFechaEntrenador.setVisible(false);     
        
        lblaltura.setVisible(true);
        txtaltura.setVisible(true);
        lblpeso.setVisible(true);
        txtpeso.setVisible(true);
    
    
    }
    
    private void habilitarEntrenador(){
        lblaltura.setVisible(false);
        txtaltura.setVisible(false);
        lblpeso.setVisible(false);
        txtpeso.setVisible(false);
        
        lblFechaEntrenador.setVisible(true);
        txtFechaEntrenador.setVisible(true);  
    }
    
    private void modificarDatos()  {
        codigo = Integer.parseInt(txtcodigo.getText());
        nombre = txtnombre.getText();     
        fechaNacimientoString = txtfecha_nacimiento.getText();     
        telefonoString = txtnumero.getText();
        direccion = txtdireccion.getText();
        nombreClub = (String) equipos.getSelectedItem();      
        fechaBajaString = txtfecha_baja.getText(); 
        importeString =txtimporte.getText();
        
      
      
           try {
            
            if(!nombre.equals(VACIO)){  
                
                sql = "UPDATE Persona SET Nombre = '" + nombre 
                         + "' WHERE Persona.Codigo = " + codigo + ";";
           
                sentencia.executeUpdate(sql);
            
            }
            
            if(!fechaNacimientoString.equals(VACIO)){  
                //fechaNacimiento = crearFecha(fechaNacimientoString);               
                sql = "UPDATE Persona SET Fecha_Nacimiento = '" 
                        + fechaNacimientoString 
                        + "' WHERE Persona.Codigo = " + codigo + ";";
                sentencia.executeUpdate(sql);
            }
            
            if(!telefonoString.equals(VACIO)){   
                 numeroTelefono = Integer.parseInt(txtnumero.getText());
                 sql = "UPDATE Persona SET Numero_Telefono = '" 
                         + numeroTelefono 
                         +"' WHERE Persona.Codigo = " + codigo + ";";
                 sentencia.executeUpdate(sql);
            }
            
            if(!direccion.equals(VACIO)){    
                sql = "UPDATE Persona SET Direccion = '" 
                        + direccion + "' WHERE Persona.Codigo = " 
                        + codigo + ";";
                sentencia.executeUpdate(sql);
            }
            
            if(!nombreClub.equals(VACIO)){
                nombreClub = (String) equipos.getSelectedItem();
                sql = "UPDATE Persona SET Nombre_Club = '" 
                        + nombreClub + "' WHERE Persona.Codigo = " 
                        + codigo + ";";
                sentencia.executeUpdate(sql);
            }
            
            if(!fechaBajaString.equals(VACIO)){  
                //fechaBaja = crearFecha(fechaBajaString);
                sql = "UPDATE Persona SET Fecha_Baja = '" 
                        + fechaBaja + "' WHERE Persona.Codigo = " 
                        + codigo + ";";
             
                    sentencia.executeUpdate(sql);
            }
                
             if(!importeString.equals(VACIO)){   
                 importe = Integer.parseInt(txtimporte.getText());
                 sql = "UPDATE Persona SET Importe = '" 
                         + importe + "' WHERE Persona.Codigo = " 
                         + codigo + ";";
                    sentencia.executeUpdate(sql);
                
            }          
             //Modificar un jugador
        String alturaString = txtaltura.getText();
       
        if(!alturaString.equals(VACIO) ){
            altura = Integer.parseInt(txtaltura.getText());
             sql = "UPDATE Jugador SET Altura = '" 
                        + altura + "' WHERE Jugador.Codigo = " 
                        + codigo + ";";
             
                    sentencia.executeUpdate(sql);       
        }     
         String pesoString = txtpeso.getText();
            if(!pesoString.equals(VACIO) ){
            peso = Integer.parseInt(txtpeso.getText());
             sql = "UPDATE Jugador SET Peso = '" 
                        + peso + "' WHERE Jugador.Codigo = " 
                        + codigo + ";";
             
                    sentencia.executeUpdate(sql);       
        }  
            
            //Modificar un entrenador
              fechaInicio = txtFechaEntrenador.getText();
            if(!pesoString.equals(VACIO) ){
             sql = "UPDATE Entrenador SET Fecha_Inicio = '" 
                        + fechaInicio + "' WHERE Entrenador.Codigo = " 
                        + codigo + ";";
             
                    sentencia.executeUpdate(sql);       
        }  
            } catch (SQLException ex) {
                Logger.getLogger(Modificar.class.getName()).log(Level.SEVERE, null, ex);
            }
         
         
            Inicio.refrescarBase();  
            
     
    }
        
    
    
    /*
     * Rellena el listado de equipos disponibles
     */
    private void cargarEquipos() {   
        Object  nombreEquipo = new Object();    
        String sql = "select Nombre_club from Club";        
        sentenciaInicio = ConexionAccess.sentencia;          
        try {
            ResultSet resultado = sentenciaInicio.executeQuery(sql);  
            while (resultado.next()) { 
                nombreEquipo = resultado.getString("Nombre_club");  
                equipos.addItem(nombreEquipo);    
            }
        } catch (SQLException ex) {
            Logger.getLogger(Insertar.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
}
}
