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

/**
 * @author Usuario
 *
 */
public class VentanaCarrera extends JFrame implements Observer{

	
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public VentanaCarrera(modelo.Usuario usuario, modelo.Virtual virtual, Pista pista){
		
	}

	public VentanaCarrera(Dimension dimension,Posicion posicion){

		this.setSize(800, 600);
		this.setTitle("Carrera");
		this.setLocationRelativeTo(null); //centrada
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setResizable(false);
		this.setAlwaysOnTop(true);
	}
	
}
