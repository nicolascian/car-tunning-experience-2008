package vista.ventanas;

import vista.VistaVentana;
import vista.imagenTramo.Posicion;
import java.awt.Dimension;
import java.util.*;

import javax.swing.JFrame;

import modelo.Pista;
import vista.imagenTramo.Posicion;
import control.Usuario;

public class VentanaCreditos extends JFrame {

	private JFrame ventanaMenu=null;
	
	public VentanaCreditos(JFrame ventanaMenu) {
		this.ventanaMenu=ventanaMenu;
		
		this.setSize(800, 600);
		this.setTitle("Creditos");
		this.setLocationRelativeTo(null); //centrada
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent e) {
		    	haceralgo();
		    }
		});

    	this.setResizable(false);
		this.setAlwaysOnTop(false);
		this.setVisible(false);	
	
	}
	
	private void haceralgo(){
		ventanaMenu.setVisible(true);
		this.dispose();
	}
	
	
	
}
