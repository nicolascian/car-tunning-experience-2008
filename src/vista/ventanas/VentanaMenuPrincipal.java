/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package vista.ventanas;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import vista.*;

/**
 * @author Usuario
 *
 */
public class VentanaMenuPrincipal extends JFrame {

	
private VistaVentana vistaVentana=null;
	
	
	public VentanaMenuPrincipal(VistaVentana vistaVentana){
		this.setTitle("Menu Principal - Car Tunnning Experience 2008");
		this.vistaVentana=vistaVentana;
		this.setLayout(new FlowLayout());
		
		JButton botonManejar=new JButton();  //boton para manejar solo
		botonManejar.setText("Manejar");
		botonManejar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonManejar();
			}
		});
		this.add(botonManejar);
		JButton botonCarrera=new JButton();  //boton para hacer una carrera
		botonCarrera.setText("Carrera");
		botonCarrera.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonCarrera();
			}
		});
		this.add(botonCarrera);
		JButton botonAuto=new JButton();  //boton para elegir un auto
		botonAuto.setText("Auto");
		botonAuto.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonAuto();
			}
		});
		this.add(botonAuto);
		JButton botonPista=new JButton();  //boton para elegir una pista
		botonPista.setText("Pista");
		botonPista.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonPista();
			}
		});
		this.add(botonPista);
		JButton botonGuardar=new JButton();  //boton encargado de la persistencia
		botonGuardar.setText("Guardar");
		botonGuardar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonGuardar();
			}
		});
		this.add(botonGuardar);
		JButton botonOpciones=new JButton();  //boton para ver las opciones
		botonOpciones.setText("Opciones");
		botonOpciones.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonOpciones();
			}
		});
		this.add(botonOpciones);
		JButton botonCreditos=new JButton();  //boton para ver los creditos
		botonCreditos.setText("Creditos");
		botonCreditos.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonCreditos();
			}
		});
		this.add(botonCreditos);
		this.pack();
		this.setResizable(false);//que no se pueda cambiar de tamaño
		this.setLocationRelativeTo(null); //centrada	
		this.setAlwaysOnTop(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void pressBotonManejar(){
	  try{	
	   vistaVentana.Manejar();
	  }catch(NullPointerException e){};
	}
	public void pressBotonCarrera(){
	 try{
		vistaVentana.Carrera();
	 }catch(NullPointerException e){};
	}
	public void pressBotonAuto(){
	 try{
		vistaVentana.Auto();
	 }catch(NullPointerException e){};
	}
	public void pressBotonPista(){
	 try{
		vistaVentana.Pista();
	 }catch(NullPointerException e){};
	}
	public void pressBotonGuardar(){
	 try{
		vistaVentana.Guardar();
	 }catch(NullPointerException e){};
	}
	public void pressBotonOpciones(){
	  try{	
		vistaVentana.Opciones();
	  }catch(NullPointerException e){};
	}
	public void pressBotonCreditos(){
	  try{
		vistaVentana.Creditos();
	  }catch(NullPointerException e){};
	}
	/**
	 * @return the vistaVentana
	 */
	public VistaVentana getVistaVentana() {
		return vistaVentana;
	}
	/**
	 * @param vistaVentana the vistaVentana to set
	 */
	public void setVistaVentana(VistaVentana vistaVentana) {
		this.vistaVentana = vistaVentana;
	}	
	
	
}
