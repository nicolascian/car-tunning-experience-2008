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
 *
 */ 
public class VentanaCarrera extends JFrame implements Observer{
	
    private PanelCarril panel=null;
	
	public void update(Observable arg0, Object arg1) {
	   panel.repaint();
	}
	
	public VentanaCarrera(modelo.Usuario usuario, modelo.Virtual virtual, Pista pista){
		this.setSize(800, 600);
		//this.setTitle("Carrera");
		this.setLocationRelativeTo(null); //centrada
		this.panel=PanelCarril.createPanelCarrilVistaAutoDesdeAtras(this.getSize(),new Posicion(), usuario);
		this.add(panel);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setVisible(false);
		
	}


	
}
