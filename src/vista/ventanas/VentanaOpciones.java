/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package vista.ventanas;

import vista.VistaVentana;
import vista.imagenTramo.Posicion;

import java.awt.Color;
import java.awt.Dimension;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import modelo.Pista;
import vista.imagenTramo.Posicion;
import control.Usuario;
import java.awt.BorderLayout;
public class VentanaOpciones extends JFrame {
	
	private VistaVentana ventana=null;
	
	public VentanaOpciones(VistaVentana ventana) {
	    this.ventana=ventana;
		this.setSize(800, 600);
		this.setTitle("Opciones - Car Tunnning Experience 2008");
		this.setLocationRelativeTo(null); //centrada
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent e) {
		    	cerrarVentana();
		    }
		});
    	this.setResizable(false);
		this.setAlwaysOnTop(false);
		this.setVisible(false);	
		JLabel label= new JLabel("Cambiar Habilidad: ");
		this.add(label);
		JPanel panelBotones = new JPanel();
		this.add(panelBotones);
		JButton botonHabilidad = new JButton("Cambiar dificultad");
		panelBotones.add(botonHabilidad);
		botonHabilidad.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				cambiarHabilidad();
				
			}});
		JButton boton=new JButton("Menu Principal");
		boton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent e) {
			 try{	
				cerrarVentana();
			 }catch(NullPointerException exception){};
		    }
		});
		boton.setBackground(Color.LIGHT_GRAY);
		panelBotones.add(boton);
		panelBotones.setVisible(true);
	}
	
	public void cambiarHabilidad(){
		ventana.cambiarHabilidad();
	}
	
	private void cerrarVentana(){
		ventana.getVentanaMenu().setVisible(true);
		this.dispose();
	}
	
}
