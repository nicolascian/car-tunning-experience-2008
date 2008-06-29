/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package vista;

import modelo.*;
import modelo.componente.*;
import control.*;
import vista.ventanas.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import vista.VistaConsola;

public class VistaVentana {

	private ControladorJuego controlJuego;
		
	private JFrame ventanaPrincipal  = null; //marco que contendra los controles del primer menu
	
	private JFrame ventanaMenu  = null; //marco que contendra los controles del segundo menu
	
	private JFrame ventanaCreditos  = null; //marco que contendra los autos y pista
	
    
	
	/** Constructor de la vista con ventanas */
	public VistaVentana(ControladorJuego ctrl){
		controlJuego = ctrl;
		// decorados
		JFrame.setDefaultLookAndFeelDecorated(true); //false para Windows estandar
		VentanaSplash ventanaSplash = new VentanaSplash();
			
		crearVentanaPrincipal();
		ventanaSplash.setProgresoProgressBar(25);
	
		crearVentanaMenu();
		ventanaSplash.setProgresoProgressBar(75);
		
		crearVentanaCreditos();
		ventanaSplash.setProgresoProgressBar(100);
		
		ventanaSplash.setVisible(false);
		ventanaSplash.dispose();
		ventanaPrincipal.setVisible(true);
		
	}
			
	public void JuegoNuevo(){
		
		String nombre = JOptionPane.showInputDialog("Ingresar Nombre:");
		
		cerrarVentanaPrincipal();
		
		controlJuego.crearUsuario(nombre);	
		
		ventanaMenu.setVisible(true);
		
	}
	
	public void CargarJuego(){
		cerrarVentanaPrincipal();
		
		controlJuego.cargarUsuario();
		
		ventanaMenu.setVisible(true);
	}
	
	public void Manejar(){
		ventanaMenu.setVisible(false);
		
		controlJuego.correrSolo();
		
		ventanaMenu.setVisible(true);
	}
	
	public void Carrera(){
		ventanaMenu.setVisible(false);
		
		controlJuego.correrCarrera();
		
		ventanaMenu.setVisible(true);
	}
	
	public void Auto(){
		//Taller
	}
	public void Pista(){
		//se pude elegir una pista
	}
	
	public void Guardar(){
		controlJuego.Guardar();
		
	}
	public void Opciones(){	
		//dificultad del PC, y el auto del PC
	}
	public void Creditos(){
		ventanaMenu.setVisible(false);
		
		ventanaCreditos.setVisible(true);
		
		//ventanaMenu.setVisible(true);
	}
	
	
	
	
	
	
	/* de aca para abajo pura GUI (embole mal!) */
	

	/* VENTANA PRINCIPAL *****************************************************************************/
	private void crearVentanaPrincipal(){
		ventanaPrincipal = new VentanaPerfilDeUsuario(this);
	}
	
	private void cerrarVentanaPrincipal(){
		ventanaPrincipal.dispose();
		ventanaPrincipal = null;
	}
		
	/* VENTANA MENU **********************************************************************************/
	private void crearVentanaMenu(){
		ventanaMenu = new VentanaMenuPrincipal(this);	
	}
	
	private void cerrarVentanaMenu(){
		ventanaMenu.dispose();
		ventanaMenu = null;
	}
		
	
	/* VENTANA CREDITOS *********************************************************************************/
	private void crearVentanaCreditos(){
		ventanaCreditos = new VentanaCreditos(this.ventanaMenu);
		
	}
	
	private void cerrarVentanaCreditos(){
		ventanaCreditos.dispose();
		ventanaMenu = null;
	}		
	
}
