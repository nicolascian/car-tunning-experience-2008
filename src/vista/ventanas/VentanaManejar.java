package vista.ventanas;

import java.awt.Dimension;
import java.util.*;

import javax.swing.JFrame;

import modelo.Pista;
import vista.imagenTramo.Posicion;
import control.Usuario;

public class VentanaManejar extends JFrame implements Observer{

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	public VentanaManejar(modelo.Usuario usuario, Pista pista){
		this.setSize(800, 600);
		this.setTitle("Carrera");
		this.setLocationRelativeTo(null); //centrada
		this.add(PanelCarril.createPanelCarrilVistaAutoDesdeAtras(this.getSize(),new Posicion(), usuario));
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setVisible(false);	

	}
	
	
	
	
}
