/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package vista.ventanas;

import modelo.*;
import modelo.componente.*;
import control.*;
import vista.imagen.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import vista.VistaConsola;

public class VistaVentana implements Observer{

	private ControladorJuego controlJuego;
		
	private JFrame ventanaPrincipal  = null; //marco que contendra los controles del primer menu
	
	private JFrame ventanaMenu  = null; //marco que contendra los controles del segundo menu
	
	private JFrame ventanaJuego  = null; //marco que contendra los autos y pista
	
	ImagenAuto imagen = null;
	
    // se ejecuta cuando hay cambios en el modelo
	public void update(Observable arg0, Object arg1) {
		if (!(ventanaJuego==null)){
			imagen.repaint();
		}		
	}
	
	/** Constructor de la vista con ventanas */
	public VistaVentana(ControladorJuego ctrl){
		controlJuego = ctrl;
		// decorados
		JFrame.setDefaultLookAndFeelDecorated(true); //false para Windows estandar
		VentanaSplash ventanaSplash = new VentanaSplash();
		// Conectamos esta vista con el modelo
		//this.auto = new Auto();
		//this.pista = new Pista(auto, auto, 1000);
		//this.auto.agregarObservador(this);
		//this.pista.addObserver(this);
			
		crearVentanaPrincipal();
		ventanaSplash.setProgresoProgressBar(25);
	
		crearVentanaMenu();
		ventanaSplash.setProgresoProgressBar(75);
		
		crearVentanaJuego();
		ventanaSplash.setProgresoProgressBar(100);
		
		ventanaSplash.setVisible(false);
		//ventanaSplash.dispose();
		ventanaPrincipal.setVisible(true);
		
	}
			
	public void JuegoNuevo(){
		
		cerrarVentanaPrincipal();
	
		controlJuego.crearUsuario();	
		
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
		
		/*
		new VistaConsola(auto, pista);//creo una vistaa de consola
		auto.ActualizarObservadores();// para que se actualice por primera vez
		 */
		
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
	public void Opciones(){}
	public void Creditos(){}
	
	
	
	
	
	
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
	
	/* VENTANA JUEGO *********************************************************************************/
	private void crearVentanaJuego(){
		//ventanaJuego = new VentanaCarrera()
		
	}
	
	private void cerrarVentanaJuego(){
		ventanaJuego.dispose();
		ventanaMenu = null;
	}		
}
