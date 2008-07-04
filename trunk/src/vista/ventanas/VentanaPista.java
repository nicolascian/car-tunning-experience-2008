/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package vista.ventanas;

import vista.VistaVentana;
import vista.imagenTramo.Imagen;
import vista.imagenTramo.Posicion;
import java.awt.Dimension;
import java.util.*;
import javax.swing.*;

import java.awt.Color;
import modelo.Pista;
import vista.imagenTramo.Posicion;
import control.Usuario;
import java.awt.GridLayout;

public class VentanaPista extends JFrame{

	private VentanaMenuPrincipal ventanaMenu=null;
	
	private Pista pista=null;
	
	private JPanel panelPistas=null;
	
	private modelo.EditorDePista editor=null;
	
	public VentanaPista(VentanaMenuPrincipal ventanaMenu) {
		this.ventanaMenu=ventanaMenu;
		this.setLayout(null);
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
		this.getContentPane().setBackground(Color.black);
		this.setVisible(false);	
		this.editor=new modelo.EditorDePista();
		this.agregarBotones();
	}	
	
	private void agregarBotones(){
		panelPistas=new JPanel();
		panelPistas.setLayout(new GridLayout(4,4));
		this.setBackground(Color.black);
		this.add(panelPistas);
		panelPistas.setBounds(0,0,this.getWidth(),this.getHeight());
		
		JButton boton=new JButton("Autodromo de Buenos Aires");
		boton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent e) {
			 try{	
				ventanaMenu.getVistaVentana().getControlJuego().getDatos()
				.setPista(getEditor().createPistaAutodromo(pista.getAuto(0),pista.getAuto(1)));	
			
			 }catch(NullPointerException exception){
				 ventanaMenu.getVistaVentana().getControlJuego().getDatos()
					.setPista(getEditor().createPuenteRio(null,null));};
		    }
		});
		ImageIcon icono=new ImageIcon((new Imagen("src//vista//fotosDePistas//autodromo_buenos_aires.jpg",
				new Dimension((int)(panelPistas.getWidth()*0.05),(int)(panelPistas.getHeight()*0.05)),
				new Posicion()).getImage()).getScaledInstance(80,80, java.awt.Image.SCALE_DEFAULT));
		boton.setIcon(icono);
		panelPistas.add(boton);
		
		boton=new JButton("Zona Desertica");
		boton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent e) {
			 try{	
				ventanaMenu.getVistaVentana().getControlJuego().getDatos()
				.setPista(getEditor().createPistaCaminoDesertico(pista.getAuto(0),pista.getAuto(1)));	
			 }catch(NullPointerException exception){

				 ventanaMenu.getVistaVentana().getControlJuego().getDatos()
					.setPista(getEditor().createPuenteRio(null,null));
			 };
		    }
		});
		icono=new ImageIcon((new Imagen("src//vista//fotosDePistas//camino_desertico.jpg",
				new Dimension(10,10),new Posicion()).getImage()).
				getScaledInstance(80,80, java.awt.Image.SCALE_DEFAULT));
		boton.setIcon(icono);
		panelPistas.add(boton);
		
		boton=new JButton("Camino Rural");
		boton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent e) {
			 try{	
				ventanaMenu.getVistaVentana().getControlJuego().getDatos()
				.setPista(getEditor().createPistaCaminoRural(pista.getAuto(0),pista.getAuto(1)));
			 }catch(NullPointerException exception){

				 ventanaMenu.getVistaVentana().getControlJuego().getDatos()
					.setPista(getEditor().createPuenteRio(null,null));
			 };
		    }
		});
		icono=new ImageIcon((new Imagen("src//vista//fotosDePistas//camino_rural.jpg",
				new Dimension(10,10),new Posicion()).getImage()).
				getScaledInstance(80,80, java.awt.Image.SCALE_DEFAULT));
		boton.setIcon(icono);
		panelPistas.add(boton);
		
		boton=new JButton("Puente Rio Salado");
		boton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent e) {
			 try{
				 ventanaMenu.getVistaVentana().getControlJuego().getDatos()
				.setPista(getEditor().createPuenteRio(pista.getAuto(0),pista.getAuto(1)));	
			 }catch(NullPointerException exception){
				 ventanaMenu.getVistaVentana().getControlJuego().getDatos()
					.setPista(getEditor().createPuenteRio(null,null));
			 };
		    }
		});
		icono=new ImageIcon((new Imagen("src//vista//fotosDePistas//puente_rio_salado_ruta2.jpg",
				new Dimension(10,10),new Posicion()).getImage()).
				getScaledInstance(80,80, java.awt.Image.SCALE_DEFAULT));
		boton.setIcon(icono);
		panelPistas.add(boton);
		
		boton=new JButton("Terraplen Laguna De Chascomus");
		boton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent e) {
			 try{	
				ventanaMenu.getVistaVentana().getControlJuego().getDatos()
				.setPista(getEditor().createPistaTerraplen(pista.getAuto(0),pista.getAuto(1)));	
			 }catch(NullPointerException exception){
				 ventanaMenu.getVistaVentana().getControlJuego().getDatos()
					.setPista(getEditor().createPistaTerraplen(pista.getAuto(0),pista.getAuto(1)));	
			 };
		    }
		});
		icono=new ImageIcon((new Imagen("src//vista//fotosDePistas//terraplen_laguna.jpg",
				new Dimension(10,10),new Posicion()).getImage()).
				getScaledInstance(80,80, java.awt.Image.SCALE_DEFAULT));
		boton.setIcon(icono);
		panelPistas.add(boton);
		
		panelPistas.setVisible(true);
		
		boton=new JButton("Menu Principal");
		boton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent e) {
			 try{	
				cerrarVentana();
			 }catch(NullPointerException exception){exception.printStackTrace();};
		    }
		});
		boton.setBackground(Color.LIGHT_GRAY);
		this.panelPistas.add(boton);
	}
	
	private void aplicarCambios(){
		
	}
	
	private void cerrarVentana(){
		ventanaMenu.setVisible(true);
		this.dispose();
	}

	/**
	 * @return the ventanaMenu
	 */
	public VentanaMenuPrincipal getVentanaMenu() {
		return ventanaMenu;
	}

	/**
	 * @param ventanaMenu the ventanaMenu to set
	 */
	public void setVentanaMenu(VentanaMenuPrincipal ventanaMenu) {
		this.ventanaMenu = ventanaMenu;
	}

	/**
	 * @return the pista
	 */
	public Pista getPista() {
		return pista;
	}

	/**
	 * @param pista the pista to set
	 */
	public void setPista(Pista pista) {
		this.pista = pista;
	}

	/**
	 * @return the panelPistas
	 */
	public JPanel getPanelPistas() {
		return panelPistas;
	}

	/**
	 * @param panelPistas the panelPistas to set
	 */
	public void setPanelPistas(JPanel panelPistas) {
		this.panelPistas = panelPistas;
	}

	/**
	 * @return the editor
	 */
	public modelo.EditorDePista getEditor() {
		return editor;
	}

	/**
	 * @param editor the editor to set
	 */
	public void setEditor(modelo.EditorDePista editor) {
		this.editor = editor;
	}
	
}
