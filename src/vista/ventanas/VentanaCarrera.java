/**
 * 
 */
package vista.ventanas;

import java.awt.Dimension;
import java.util.*;
import modelo.*;
import javax.swing.JFrame;
import vista.imagenTramo.Posicion;
import control.Usuario;
import javax.swing.JWindow;

/**
 * @author Usuario
 *usuario.getAuto().getVelocidad()
 */ 
public class VentanaCarrera extends JFrame implements Observer{
	
    private PanelCarril panel=null;
	
    private modelo.Usuario usuario = null;
    
	public void update(Observable arg0, Object arg1) {
	  
		panel.actualizarVelocidad(usuario.getAuto().getVelocidad());
	}
	
	public VentanaCarrera(modelo.Usuario usuario, modelo.Virtual virtual, Pista pista){
		this.usuario  = usuario;
		
		this.setSize(800, 600);
		this.setLocationRelativeTo(null); //centrada
		this.panel=PanelCarril.createPanelCarrilVistaAutoDesdeAtras(this.getSize(),new Posicion(), usuario);
		this.add(panel);
		this.setAlwaysOnTop(true);
		this.setVisible(false);
		
	}


	
}
