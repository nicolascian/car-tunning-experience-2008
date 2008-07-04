/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package vista.ventanas;

import vista.VistaVentana;
import vista.imagenTramo.Posicion;
import java.awt.Dimension;
import java.util.*;
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.Color;
import modelo.Pista;
import vista.imagenTramo.Posicion;
import control.Usuario;

public class VentanaPista extends JFrame{

	private VentanaMenuPrincipal ventanaMenu=null;
	
	private Pista pista=null;
	
	private modelo.EditorDePista editor=null;
	
	public VentanaPista(VentanaMenuPrincipal ventanaMenu) {
		this.ventanaMenu=ventanaMenu;
		
		this.setSize(800, 600);
		this.setTitle("Pista");
		this.setLocationRelativeTo(null); //centrada
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent e) {
		    	cerrarVentana();
		    }
		});
		pista=ventanaMenu.getVistaVentana().getControlJuego().getDatos().getPista();
    	this.setResizable(false);
		this.setAlwaysOnTop(false);
		this.setBackground(Color.black);
		this.setVisible(false);	
		this.editor=new modelo.EditorDePista();
	}	
	
	private void agregarBotones(){
		
	}
	
	private void aplicarCambios(){
		
	}
	
	private void cerrarVentana(){
		ventanaMenu.setVisible(true);
		this.dispose();
	}
	
}
