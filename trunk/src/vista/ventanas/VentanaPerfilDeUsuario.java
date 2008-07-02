/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package vista.ventanas;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.*;

import vista.*;

/**
 * @author Usuario
 *
 */
public class VentanaPerfilDeUsuario extends JFrame {

	private VistaVentana vistaVentana=null;
	
	public VentanaPerfilDeUsuario(VistaVentana vistaVentana){
		JButton botonNuevoUsuario; 
		JButton botonCargarUsuario;
		this.setTitle("Perfil De Usuario");
		this.vistaVentana=vistaVentana;
		botonNuevoUsuario = new JButton();
		botonNuevoUsuario.setText("Crear Usuario");
		botonNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonNuevoUsuario();
			}
		});
		botonCargarUsuario = new JButton();
		botonCargarUsuario.setText("Cargar Usuario");
		botonCargarUsuario.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonCargarUsuario();
			}
		});
		this.setLayout(new FlowLayout());
		this.add("North", botonNuevoUsuario);
		this.add("North", botonCargarUsuario);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null); //centrada
		this.setVisible(false);
	}
	
	public void pressBotonNuevoUsuario(){
	 try{	
		 this.setAlwaysOnTop(false);
		 vistaVentana.JuegoNuevo();
		 this.setAlwaysOnTop(true);
	 }catch(NullPointerException e){};
	}
	
	public void pressBotonCargarUsuario(){
	  try{	
		this.setAlwaysOnTop(false);  
		vistaVentana.CargarJuego();
		this.setAlwaysOnTop(true);
	  }catch(NullPointerException e){};
	}
	
}
