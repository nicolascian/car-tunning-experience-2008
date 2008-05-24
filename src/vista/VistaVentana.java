/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package vista;

import modelo.*;
import control.*;

import javax.swing.*;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.util.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class VistaVentana implements Observer{

	private Auto auto = null; //referencia al modelo (auto)
	private Pista pista = null; //referencia al modelo (pista)
	
	private JFrame ventana1  = null; //marco que contendra los controles del primer menu
	private JFrame ventana2  = null; //marco que contendra los controles del segundo menu
	
	private JPanel panelPrimero = null;
	private JButton botonJuegoNuevo = null;  //boton para comenzar un juego nuevo
	private JButton botonCargarJuego = null;  //boton para cargar un juego previamente guardado
	
	private JPanel panelSegundo = null;
	private JLabel labelAlgoPesos = null; //indica la cantidad de algo$
	private JButton botonManejar = null;  //boton para manejar solo
	private JButton botonCarrera = null;  //boton para hacer una carrera
	private JButton botonAuto = null;  //boton para elegir un auto
	private JButton botonPista = null;  //boton para elegir una pista
	private JButton botonOpciones = null;  //boton para ver las opciones
	private JButton botonCreditos = null;  //boton para ver los creditos
	
	
	public void update(Observable arg0, Object arg1) {
		
		System.out.println("update");
	}
	
	
	/** Constructor de la vista con ventanas */
	public VistaVentana(){
		
		// Conectamos esta vista con el modelo
		this.auto = new Auto();
		this.pista = new Pista(auto, auto, 1000);
		this.auto.addObserver(this); 
		this.pista.addObserver(this); 
		
		
		crearVentanaPrincipal();
	}
	
	
	/* BOTONES ACIONES********************************************************************************/
	private void JuegoNuevo(){
		cerrarVentanaPrincipal();
		crearVentanaMenu();
	}
	private void CargarJuego(){}
	private void Manejar(){
		Frame frame = new Frame("Manejar - CTE08");
		frame.setSize(300, 100);
		frame.setVisible(true);
		frame.addKeyListener(new Usuario(auto));
		new VistaConsola(auto, pista);
		
	}
	private void Carrera(){
		Jugador jugador = new Virtual(new Principiante(), auto);
		new VistaConsola(auto, pista);
	}
	private void Auto(){
		//Taller
	}
	private void Pista(){
		//se pude elegir una pista
	}
	private void Opciones(){}
	private void Creditos(){}
	
	/* VENTANA PRINCIPAL *****************************************************************************/
	private void crearVentanaPrincipal(){
		ventana1 = new JFrame("Car Tunnning Experience 2008");
		// ponemos el panelPrimero en la ventana
		ventana1.add("North", getPanelPrimero());
		ventana1.setSize(250,65);  //seteamos las dimensiones de la ventana
		ventana1.setVisible(true);  //mostramos la ventana
		//agregamos el listener del evento de cerrado de la ventana		
		ventana1.addWindowListener(new CloseListener());
	}
	
	private void cerrarVentanaPrincipal(){
		// como no se cerrar ventanas, la desaparezco
		// y lo pongo en null, para que el recolector se encargue
		ventana1.setVisible(false);
		ventana1 = null;
	}
	
	private JPanel getPanelPrimero(){
		if (panelPrimero == null) {
			panelPrimero = new JPanel();
			panelPrimero.add(getBotonJuegoNuevo());
			panelPrimero.add(getBotonCargarJuego());
		}
		return panelPrimero;
	}
	
	private JButton getBotonJuegoNuevo(){
		if (botonJuegoNuevo == null) {
			botonJuegoNuevo = new JButton();
			botonJuegoNuevo.setText("Juego Nuevo");
			botonJuegoNuevo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JuegoNuevo();
				}
			});
		}
		return botonJuegoNuevo;
	}
	
	private JButton getBotonCargarJuego(){
		if (botonCargarJuego == null) {
			botonCargarJuego = new JButton();
			botonCargarJuego.setText("Cargar Juego");
			botonCargarJuego.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					CargarJuego();
				}
			});
		}
		return botonCargarJuego;
	}
	
	/* VENTANA MENU **********************************************************************************/
	private void crearVentanaMenu(){
		ventana2 = new JFrame("Car Tunnning Experience 2008");
		// ponemos el panelPrimero en la ventana
		ventana2.add("North", getPanelSegundo());
		ventana2.setSize(510,65);  //seteamos las dimensiones de la ventana
		ventana2.setVisible(true);  //mostramos la ventana
		//agregamos el listener del evento de cerrado de la ventana		
		ventana2.addWindowListener(new CloseListener());
	
	}
	
	private void cerrarVentanaMenu(){
		// como no se cerrar ventanas, la desaparezco
		// y lo pongo en null, para que el recolector se encargue
		ventana2.setVisible(false);
		ventana2 = null;
	}
	
	private JPanel getPanelSegundo(){
		if (panelSegundo == null) {
			panelSegundo = new JPanel();
			panelSegundo.add(getBotonManejar());
			panelSegundo.add(getBotonCarrera());
			panelSegundo.add(getBotonAuto());
			panelSegundo.add(getBotonPista());
			panelSegundo.add(getBotonOpciones());
			panelSegundo.add(getBotonCreditos());
			//panelSegundo.add(getBotonCarrera());  //LABEL ALGOPESOS
		}
		return panelSegundo;
	}
	
	private JButton getBotonManejar(){
		if (botonManejar == null) {
			botonManejar = new JButton();
			botonManejar.setText("Manejar");
			botonManejar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Manejar();
				}
			});
		}
		return botonManejar;
	}
	
	private JButton getBotonCarrera(){
		if (botonCarrera == null) {
			botonCarrera = new JButton();
			botonCarrera.setText("Carrera");
			botonCarrera.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Carrera();
				}
			});
		}
		return botonCarrera;
	}
	
	private JButton getBotonAuto(){
		if (botonAuto == null) {
			botonAuto = new JButton();
			botonAuto.setText("Auto");
			botonAuto.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Auto();
				}
			});
		}
		return botonAuto;
	}
	
	private JButton getBotonPista(){
		if (botonPista == null) {
			botonPista = new JButton();
			botonPista.setText("Pista");
			botonPista.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Pista();
				}
			});
		}
		return botonPista;
	}
		
	private JButton getBotonOpciones(){
		if (botonOpciones == null) {
			botonOpciones = new JButton();
			botonOpciones.setText("Opciones");
			botonOpciones.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opciones();
				}
			});
		}
		return botonOpciones;
	}
	
	private JButton getBotonCreditos(){
		if (botonCreditos == null) {
			botonCreditos = new JButton();
			botonCreditos.setText("Creditos");
			botonCreditos.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Creditos();
				}
			});
		}
		return botonCreditos;
	}
	
	/* ***********************************************************************************************/
//	Clase auxiliar para escuchar el evento de cerrado de la ventana
	public static class CloseListener extends WindowAdapter
	{	public void windowClosing(WindowEvent e)
		{	e.getWindow().setVisible(false);
			System.exit(0);
		}
	}

}
