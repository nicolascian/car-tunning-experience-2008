/**
 * 
 */
package vista.ventanas;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author Usuario
 *
 */
public class VentanaPerfilDeUsuario extends JFrame {

	private VistaVentana vistaVentana=null;
	
	public VentanaPerfilDeUsuario(VistaVentana vistaVentana){
		JButton botonNuevoUsuario; 
		JButton botonCargarUsuario;
		this.setTitle("Perfil De Usuario - Car Tunnning Experience 2008");
		this.vistaVentana=vistaVentana;
		botonNuevoUsuario = new JButton();
		botonNuevoUsuario.setText("Crear Usuario Nuevo");
		botonNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonNuevoUsuario();
			}
		});
		botonCargarUsuario = new JButton();
		botonCargarUsuario.setText("Cargar Usuario Nuevo");
		botonCargarUsuario.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonCargarUsuario();
			}
		});
		this.add(botonNuevoUsuario);
		this.add(botonCargarUsuario);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(false);
	}
	
	public void pressBotonNuevoUsuario(){
	 try{	
		vistaVentana.JuegoNuevo();
	 }catch(NullPointerException e){};
	}
	
	public void pressBotonCargarUsuario(){
	  try{	
		vistaVentana.CargarJuego();
	  }catch(NullPointerException e){};
	}
	
}
