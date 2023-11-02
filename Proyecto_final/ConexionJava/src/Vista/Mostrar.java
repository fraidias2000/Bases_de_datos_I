package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 *
 * @author ACULPLAY
 */
public class Mostrar implements ActionListener{
    Connection con = null;
    Statement sentencia;
    JScrollPane ScrollPersona, ScrollJugador, ScrollEntrenador;
    JFrame ventana;
    JPanel panelCentro;
    JLabel tablaPersona, tablaJugador, tablaEntrenador;
    
    
    JButton aceptar;
    private final String ACEPTAR = "ACEPTAR"; 
    private final String PERSONA = "Tabla Persona";
    private final String JUGADOR = "Tabla Jugador";
    private final String ENTRENADOR = "Tabla Entrenador";
   
    /**
     * Creates new form Cargar
     */
       // @SuppressWarnings("unchecked")
    public Mostrar() {
        ScrollPersona = Inicio.ScrollPersona;
        ScrollJugador = Inicio.ScrollJugador;
        ScrollEntrenador = Inicio.ScrollEntrenador;
        crearVentana();           
    }
     
    public void crearVentana(){
        ventana = new JFrame();
        aceptar = crearBoton(ACEPTAR);
        
        tablaPersona = new JLabel(PERSONA);
        tablaJugador = new JLabel(JUGADOR);
        tablaEntrenador = new JLabel(ENTRENADOR);
        
        ventana.add(tablaPersona);
        ventana.add(tablaJugador);
        ventana.add(tablaEntrenador);
        
        ventana.add(ScrollPersona);
        ventana.add(ScrollJugador);
        ventana.add(ScrollEntrenador);
        ventana.add(aceptar);
        
        
        
        
        tablaPersona.setBounds(20,0,100,20);
        tablaJugador.setBounds(20,130,100,20);
        tablaEntrenador.setBounds(20,280,100,20);
                
        ScrollPersona.setBounds(20,20,400,100);
        ScrollJugador.setBounds(20,150,400,100);
        ScrollEntrenador.setBounds(20,300,400,100);
        
        aceptar.setBounds(160,450,100,20);
        
        ventana.setLayout(null);
        ventana.setSize(500,600);
        ventana.setVisible(true);
    }
    
    /*
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
            Logger.getLogger(Mostrar.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    private void notificacionAControl(String evento) throws IOException{
        switch(evento) {
            case ACEPTAR:
                ventana.dispose();
                break;
        
        }
    }
    
}
