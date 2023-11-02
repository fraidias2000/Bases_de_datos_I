
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author ACULPLAY
 */
public class Insertar implements ActionListener{   
    
    private final String CODIGO = "Codigo: ";
    private final String NOMBRE = "Nombre: ";
    private final String FECHA_NACIMIENTO = "Fecha nacimiento: ";
    private final String NUMERO = "Numero: ";
    private final String DIRECCION = "Direccion: ";
    private final String NOMBRE_CLUB = "Nombre club: ";
    private final String FECHA_BAJA = "Fecha baja: ";
    private final String IMPORTE = "Importe: ";
    private final String NOMBRE_EQUIPO = "Equipos disp";
    private final String ALTURA = "Altura: ";
    private final String PESO = "Peso: ";
    private final String FECHA_INICIO = "Fecha Inicio: ";
    private final String VACIO = "";
    
    private int codigo;
    private String nombre;
    private String fechaNacimientoString;
    private long numeroTelefono;
    private String direccion;
    private String nombreClub;
    private String fechaBajaString;
    private int importe;
    private String fechaNacimiento;
    private String fechaBaja;
               
    //BOTONES
    private final String GUARDAR = "Guardar";
    private final String ACEPTAR = "Aceptar"; 
    private final String JUGADOR = "Jugador";
    private final String ENTRENADOR = "Entrenador";
    
    private JButton guardar;
    private JButton salir;
    private JButton jugador;
    private JButton entrenador;  
    
    private String titulos [] = {"Codigo","nombre", "Fecha_Nacimiento" , 
        "numTelefono", "direccion","nombre_club","fecha_baja","importe"}; 
    
    private Connection con = null;
    private String sql;
    private Statement sentencia;
    private JComboBox equipos; 
    private JFrame ventana;
     
    private JTextField  txtcodigo, txtnombre, txtfecha_nacimiento,
            txtnumero, txtdireccion, txtfecha_baja, txtimporte,
            txtaltura, txtpeso, txtFechaEntrenador;
    private JLabel lblcodigo, lblnombre, lblfecha_nacimiento, 
            lblnumero, lbldireccion, lblfecha_baja, lblimporte, 
            lblequipos, lblaltura, lblpeso, lblFechaEntrenador;
    
    
    public Insertar(){
        sentencia = ConexionAccess.sentencia;    
        crearVentana();  
        cargarEquipos();     
    }     
   
  private void crearVentana(){
        ventana = new JFrame(); 
        guardar = crearBoton(GUARDAR);
        salir = crearBoton(ACEPTAR);
        equipos = new JComboBox(); 
        
        jugador = crearBoton(JUGADOR);
        entrenador = crearBoton(ENTRENADOR);
        
        txtcodigo = new JTextField();
        txtnombre = new JTextField();
        txtfecha_nacimiento = new JTextField();
        txtnumero = new JTextField();
        txtdireccion = new JTextField();
        txtfecha_baja = new JTextField();
        txtimporte = new JTextField();
        
        txtaltura = new JTextField();
        txtpeso = new JTextField();
        txtFechaEntrenador = new JTextField();
        
        ventana.add(txtcodigo);
        ventana.add(txtnombre);
        ventana.add(txtfecha_nacimiento);
        ventana.add(txtnumero);
        ventana.add(txtdireccion);
        ventana.add(txtfecha_baja);
        ventana.add(txtimporte);
        
        ventana.add(jugador);
        ventana.add(entrenador);
        
        lblcodigo = new JLabel(CODIGO); 
        lblnombre = new JLabel(NOMBRE);
        lblfecha_nacimiento = new JLabel(FECHA_NACIMIENTO);
        lblnumero = new JLabel(NUMERO);
        lbldireccion = new JLabel(DIRECCION);
        lblfecha_baja = new JLabel(FECHA_BAJA);
        lblimporte = new JLabel(IMPORTE);  
        lblequipos = new JLabel(NOMBRE_EQUIPO);
        
        lblaltura = new JLabel(ALTURA);
        lblpeso = new JLabel(PESO);
        lblFechaEntrenador = new JLabel(FECHA_INICIO);
        
        ventana.add(lblcodigo);
        ventana.add(lblnombre);
        ventana.add(lblfecha_nacimiento);
        ventana.add(lblnumero);
        ventana.add(lbldireccion);
        ventana.add(lblfecha_baja);
        ventana.add(lblimporte);
        
        ventana.add(guardar);
        ventana.add(salir);
        
        ventana.add(equipos);
        ventana.add(lblequipos);
        
        ventana.add(lblaltura);
        ventana.add(txtaltura);
        
        ventana.add(lblFechaEntrenador);
        ventana.add(txtFechaEntrenador);
        
        ventana.add(lblpeso);
        ventana.add(txtpeso);
        
        guardar.setBounds(350,100,100,25);
        salir.setBounds(350,140,100,25);
        
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
        
        lblequipos.setBounds(20,150,100,20);       
        equipos.setBounds(130,150,200,20);
        
        lblfecha_baja.setBounds(20,180,100,20);
        txtfecha_baja.setBounds(130,180,200,20);
        
        lblimporte.setBounds(20,210,100,20);
        txtimporte.setBounds(130,210,200,20);
        
        jugador.setBounds(20, 240, 100, 20);
        entrenador.setBounds(130, 240, 100, 20);
        
        lblaltura.setBounds(20, 270, 100, 20);
        txtaltura.setBounds(130, 270, 100, 20);
        
        lblFechaEntrenador.setBounds(20, 270, 100, 20);
        txtFechaEntrenador.setBounds(130, 270, 100, 20);
        
        lblpeso.setBounds(20, 300, 100, 20);
        txtpeso.setBounds(130, 300, 100, 20);      
        
        lblaltura.setVisible(false);
        txtaltura.setVisible(false);
        
        lblFechaEntrenador.setVisible(false);
        txtFechaEntrenador.setVisible(false);
        
        lblpeso.setVisible(false);
        txtpeso.setVisible(false);
        
        ventana.setLayout(null);
        ventana.setSize(600,500);
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
         } catch (IOException ex) {
             Logger.getLogger(Insertar.class.getName())
                     .log(Level.SEVERE, null, ex);
         }
    }
       
    
    /*
     * Acciones que se toman en base al boton clicado
     */
    private void notificacionAControl(String evento) throws IOException{
        switch(evento) {
            case GUARDAR:
                guardarDatos();              
                break;
            case ACEPTAR:
                ventana.dispose();
                break;
            case JUGADOR:
                habilitarJugador();
                break;
                
            case ENTRENADOR:
                habilitarEntrenador();
                break;    
        }
    }
    
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
    
    
    private void guardarDatos(){       
     try { 
         //Crea una persona
        codigo = Integer.parseInt(txtcodigo.getText());
        nombre = txtnombre.getText();
        
        fechaNacimiento = txtfecha_nacimiento.getText();
           
        numeroTelefono = Integer.parseInt(txtnumero.getText());
        direccion = txtdireccion.getText();
        nombreClub = (String) equipos.getSelectedItem();
        
        fechaBaja = txtfecha_baja.getText();
              
        importe = Integer.parseInt(txtimporte.getText());
        
        sql = "insert into Persona(Codigo,Nombre,Fecha_nacimiento,"
                + "Numero_telefono,Direccion, Nombre_club,Fecha_Baja,"
                + " Importe)values ('" + codigo + "','" + nombre 
                + "','" + fechaNacimiento + "','" + numeroTelefono 
                + "','" + direccion+ "','" + nombreClub + "','" 
                + fechaBaja + "','" + importe + "')";
        sentencia.executeUpdate(sql); 
                    
        //Crea un jugador
        String Stringaltura = txtaltura.getText();
        String Stringpeso = txtpeso.getText();
        if(!Stringaltura.equals(VACIO) && !Stringpeso.equals(VACIO)){
            int altura = Integer.parseInt(txtaltura.getText());
            int peso = Integer.parseInt(txtpeso.getText());
            sql = "insert into Jugador (Codigo,Altura,Peso)values" 
                  + "('" + codigo + "','" + altura + "','" + peso + "')";
            sentencia.executeUpdate(sql);
        }      
            Inicio.refrescarBase();  
            
           JOptionPane.showMessageDialog(null, 
                   "Persona insertada correctamente\n" );
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                    "Error, sus datos no fueron ingresados\n" + ex);
        }
     
        //Crea un entrenador
        String fechaInicio = txtFechaEntrenador.getText();
        if(!fechaInicio.equals(VACIO)){  
            sql = "insert into Entrenador (Codigo,Fecha_Inicio)values" 
                + "('" + codigo + "','" + fechaInicio +  "')";
            try {
                sentencia.executeUpdate(sql);
            } catch (SQLException ex) {
                Logger.getLogger(Insertar.class.getName())
                    .log(Level.SEVERE, null, ex);
            }
        }      
        Inicio.refrescarBase();           
    }
    
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
