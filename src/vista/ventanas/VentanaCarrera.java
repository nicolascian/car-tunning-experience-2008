/**
 * 
 */
package vista.ventanas;

import java.awt.Dimension;

import javax.swing.JFrame;
import vista.imagenTramo.Posicion;
import control.Usuario;

/**
 * @author Usuario
 *
 */
public class VentanaCarrera extends JFrame {

	
	public VentanaCarrera(Dimension dimension,Posicion posicion,
			              DatoPilotoAutoParaCarrera datoPilotoAuto){
		this.setSize(800, 600);
		this.setTitle("Carrera");
		this.setLocationRelativeTo(null); //centrada
		try{
		   this.addKeyListener(new Usuario(datoPilotoAuto.getAuto()));
		}catch(NullPointerException e){};
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setResizable(false);
		this.setAlwaysOnTop(true);
	}
	
}
