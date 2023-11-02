package Vista;

import Conexion.ConexionAccess;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Borrar implements ActionListener{
    private final String INFORMACION = "Introduce"
            + " el codigo de la persona que quieras eliminar";
    private final String BORRAR = "BORRAR";
    private final String PERSONA = "Tabla Persona";
    private final String JUGADOR = "Tabla Jugador";
    private final String ENTRENADOR = "Tabla Entrenador";
    
    JFrame ventana;
    JScrollPane ScrollPersona, ScrollJugador, ScrollEntrenador;
    JButton borrar;
    JTextField id;
    JLabel informacion;
    Statement sentenciaBorrar;
    private JPanel panelNorte;
    private JLabel tablaPersona, tablaJugador, tablaEntrenador;
    
    private String sql;
    
    public Borrar(){
        ScrollPersona = Inicio.ScrollPersona;
        ScrollJugador = Inicio.ScrollJugador;
        ScrollEntrenador  = Inicio.ScrollEntrenador;
        
        sentenciaBorrar = ConexionAccess.sentencia;
        crearVentanaInicio();
    }
     /*
     * Crea la ventana principal
     */
    public void crearVentanaInicio(){
        ventana = new JFrame();
        panelNorte = new JPanel();
        crearPanelNorte(panelNorte);
        
        tablaPersona = new JLabel(PERSONA);
        tablaJugador = new JLabel(JUGADOR);
        tablaEntrenador = new JLabel(ENTRENADOR);
        
        ventana.add(panelNorte);
        
        ventana.add(tablaPersona);
        ventana.add(tablaJugador);
        ventana.add(tablaEntrenador);
        
        ventana.add(ScrollPersona);
        ventana.add(ScrollJugador);
        ventana.add(ScrollEntrenador);

        panelNorte.setBounds(0,0,750,40);
        
        tablaPersona.setBounds(20,40,100,20);
        tablaJugador.setBounds(20,170,100,20);
        tablaEntrenador.setBounds(20,320,100,20);
                
        ScrollPersona.setBounds(20,60,700,100);
        ScrollJugador.setBounds(20,190,350,100);
        ScrollEntrenador.setBounds(20,350,300,100);
        
        ventana.setLayout(null);
        ventana.setSize(800,600);
        ventana.setVisible(true);
  
    }
    
    private void crearPanelNorte(JPanel panel){
        borrar = crearBoton(BORRAR);
        id = new JTextField(20);
        informacion = new JLabel(INFORMACION);
        panel.add(informacion);
        panel.add(id);
        panel.add(borrar);
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
            Logger.getLogger(Borrar.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
       
    }
    private void notificacionAControl(String evento) throws IOException{
        switch(evento) {
            case BORRAR:
                borrarRegistro();
                break;
        
        }
    }
    private void borrarRegistro() {
        int idcodigo = Integer.parseInt(id.getText());
      

        try {
            sql = "DELETE FROM Persona WHERE Persona.Codigo = "
                + idcodigo + ";" ;
            sentenciaBorrar.executeUpdate(sql);
            
             //Elimina si es un jugador
            sql = "DELETE FROM Jugador WHERE Jugador.Codigo = "
                    +idcodigo + ";" ;
            sentenciaBorrar.executeUpdate(sql);
            
            //Elimina si es un entrenador
            sql = "DELETE FROM Entrenador WHERE Entrenador.Codigo = "
                    +idcodigo + ";" ;
            sentenciaBorrar.executeUpdate(sql);
            
            Inicio.refrescarBase();  
            JOptionPane.showMessageDialog(null,
                    "Persona borrada correctamente\n" );
        } catch (SQLException ex) {
            Logger.getLogger(Borrar.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    
    }   
}
