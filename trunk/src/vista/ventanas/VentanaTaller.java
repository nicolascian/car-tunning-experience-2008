package vista.ventanas;

import javax.swing.JFrame;
import vista.VistaVentana;
import vista.imagenTramo.Posicion;
import java.awt.Dimension;
import java.util.*;

import modelo.Pista;
import vista.imagenTramo.Posicion;
import control.Usuario;

public class VentanaTaller extends JFrame {

	private JFrame ventanaMenu=null;
	
	public VentanaTaller(JFrame ventanaMenu) {
		this.ventanaMenu=ventanaMenu;
		
		this.setSize(800, 600);
		this.setTitle("Creditos");
		this.setLocationRelativeTo(null); //centrada
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent e) {
		    	cerrarVentana();
		    }
		});

    	this.setResizable(false);
		this.setAlwaysOnTop(false);
		this.setVisible(false);	
	
	}
	
	private void cerrarVentana(){
		ventanaMenu.setVisible(true);
		this.dispose();
	}
		
	
}
