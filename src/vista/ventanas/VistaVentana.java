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

	private Auto auto = null; //referencia al modelo (auto)
	private Pista pista = null; //referencia al modelo (pista)
	
	private VentanaSplash ventanaSplash  = null; //marco que contendra el splash
	private JProgressBar progressBar = null;
	
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
	public VistaVentana(){

		// decorados
		JFrame.setDefaultLookAndFeelDecorated(true); //false para Windows estandar
		crearVentanaSplash();
	}
	
	
	/* BOTONES ACCIONES ******************************************************************************/
	
	public void JuegoNuevo(){
		cerrarVentanaPrincipal();
		ventanaMenu.setVisible(true);
	}
	
	public void CargarJuego(){}
	
	public void Manejar(){
		cerrarVentanaMenu();
		ventanaJuego.setVisible(true);
		
		auto.setCaja(new Manual(5));//le pongo una caja al auto
		new VistaConsola(auto, pista);//creo una vistaa de consola
		auto.ActualizarObservadores();// para que se actualice por primera vez

	}
	
	public void Carrera(){
		Jugador jugador = new Virtual(new Principiante(), auto);
		new VistaConsola(auto, pista);
	}
	
	public void Auto(){
		//Taller
	}
	public void Pista(){
		//se pude elegir una pista
	}
	
	public void Guardar(){}
	public void Opciones(){}
	public void Creditos(){}
	
	
	
	
	
	
	/* de aca para abajo pura GUI (embole mal!) */
	
	/* VENTANA SPLASH ********************************************************************************/
	private void crearVentanaSplash(){
		ventanaSplash = new VentanaSplash();
		// ponemos el panelPrimero en la ventana
		
		
		// Conectamos esta vista con el modelo
		this.auto = new Auto();
		this.pista = new Pista(auto, auto, 1000);
		this.auto.agregarObservador(this);
		this.pista.addObserver(this);
			
		crearVentanaPrincipal();
		ventanaSplash.setProgresoProgressBar(25);
	
		crearVentanaMenu();
		ventanaSplash.setProgresoProgressBar(75);
		
		crearVentanaJuego();
		ventanaSplash.setProgresoProgressBar(100);
		
		cerrarVentanaSplash();
		ventanaPrincipal.setVisible(true);
	}
	
	private void cerrarVentanaSplash(){
		ventanaSplash.setVisible(false);
		ventanaSplash.dispose();
		ventanaSplash = null;
	}
		
	/* VENTANA PRINCIPAL *****************************************************************************/
	private void crearVentanaPrincipal(){
		ventanaPrincipal = new VentanaPerfilDeUsuario(this);
	}
	
	private void cerrarVentanaPrincipal(){
		ventanaPrincipal.setVisible(false);
		ventanaPrincipal.dispose();
		ventanaPrincipal = null;
	}
		
	/* VENTANA MENU **********************************************************************************/
	private void crearVentanaMenu(){
		ventanaMenu = new VentanaMenuPrincipal(this);	
	}
	
	private void cerrarVentanaMenu(){
		ventanaMenu.setVisible(false);
		ventanaMenu.dispose();
		ventanaMenu = null;
	}
	
	/* VENTANA JUEGO *********************************************************************************/
	private void crearVentanaJuego(){
		ventanaJuego = new JFrame("Manejar - CTE08");
		ventanaJuego.setSize(800, 600);
		
		ventanaJuego.setLocationRelativeTo(null); //centrada
		
		ventanaJuego.addKeyListener(new Usuario(auto));
		//agregamos el listener del evento de cerrado de la ventana		
		ventanaJuego.addWindowListener(new CloseListener());
	  
		imagen = new ImagenAuto(ventanaJuego, auto, pista);
		// cargamso la imagen desde archivo
		ventanaJuego.add(imagen);
		
		//ventanaJuego.pack();
		ventanaJuego.setResizable(false);
		ventanaJuego.setAlwaysOnTop(true);
		
	}
	
	private void cerrarVentanaJuego(){
//		 como no se cerrar ventanas, la desaparezco
		// y lo pongo en null, para que el recolector se encargue
		ventanaMenu.setVisible(false);
		ventanaMenu = null;
	}
		
}
