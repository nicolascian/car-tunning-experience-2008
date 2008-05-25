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

import javax.swing.*;

import java.awt.*;
import java.util.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class VistaVentana implements Observer{

	private Auto auto = null; //referencia al modelo (auto)
	private Pista pista = null; //referencia al modelo (pista)
	
	private JWindow ventanaSplash  = null; //marco que contendra el splash
	private JProgressBar progressBar = null;
	
	private JFrame ventanaPrincipal  = null; //marco que contendra los controles del primer menu
	private JPanel panelPrimero = null;
	private JButton botonJuegoNuevo = null;  //boton para comenzar un juego nuevo
	private JButton botonCargarJuego = null;  //boton para cargar un juego previamente guardado
	
	private JFrame ventanaMenu  = null; //marco que contendra los controles del segundo menu
	private JPanel panelSegundo = null;
	private JLabel labelAlgoPesos = null; //indica la cantidad de algo$
	private JButton botonManejar = null;  //boton para manejar solo
	private JButton botonCarrera = null;  //boton para hacer una carrera
	private JButton botonAuto = null;  //boton para elegir un auto
	private JButton botonPista = null;  //boton para elegir una pista
	private JButton botonGuardar = null;  //boton encargado de la persistencia
	private JButton botonOpciones = null;  //boton para ver las opciones
	private JButton botonCreditos = null;  //boton para ver los creditos
	
	private JFrame ventanaJuego  = null; //marco que contendra los autos y pista
	
    // se ejecuta cuando hay cambios en el modelo
	public void update(Observable arg0, Object arg1) {
		
		System.out.println("update");
	}
	
	
	/** Constructor de la vista con ventanas */
	public VistaVentana(){

		// decorados
		JFrame.setDefaultLookAndFeelDecorated(true); //false para Windows estandar
		crearVentanaSplash();
	}
	
	
	/* BOTONES ACCIONES ******************************************************************************/
	
	private void JuegoNuevo(){
		cerrarVentanaPrincipal();
		ventanaMenu.setVisible(true);
	}
	
	private void CargarJuego(){}
	
	private void Manejar(){
		cerrarVentanaMenu();
		ventanaJuego.setVisible(true);
		
		auto.setCaja(new Manual(5));//le pongo una caja al auto
		new VistaConsola(auto, pista);//creo una vistaa de consola
		auto.ActualizarObservadores();// para que se actualice por primera vez
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
	
	private void Guardar(){}
	private void Opciones(){}
	private void Creditos(){}
	
	
	
	
	
	
	/* de aca para abajo pura GUI (embole mal!) */
	
	/* VENTANA SPLASH ********************************************************************************/
	private void crearVentanaSplash(){
		ventanaSplash = new JWindow();
		// ponemos el panelPrimero en la ventana
		JProgressBar progress = getProgressBar();
		ventanaSplash.add("South", getProgressBar());
		ventanaSplash.setSize(400,250);  //seteamos las dimensiones de la ventana
		ventanaSplash.setLocationRelativeTo(null); //centrada
		ventanaSplash.setAlwaysOnTop(true);
		ventanaSplash.setVisible(true);  //mostramos la ventana
		
		ventanaSplash.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); //le pongo cursor en espera
		progress.setValue(0);
		progress.setStringPainted(true);// le pongo numeritos al progressBar
		
		// Conectamos esta vista con el modelo
		this.auto = new Auto();
		this.pista = new Pista(auto, auto, 1000);
		this.auto.agregarObservador(this);
		this.pista.addObserver(this);
		
		progress.setValue(25);
		
		crearVentanaPrincipal();
		progress.setValue(50);
	
		crearVentanaMenu();
		progress.setValue(75);
		
		crearVentanaJuego();
		progress.setValue(100);
		
		cerrarVentanaSplash();
		ventanaPrincipal.setVisible(true);
	}
	
	private void cerrarVentanaSplash(){
//		 como no se cerrar ventanas, la desaparezco
		// y lo pongo en null, para que el recolector se encargue
		ventanaSplash.setVisible(false);
		ventanaSplash = null;
	}
	
	private JProgressBar getProgressBar() {
		if (progressBar == null) {
			progressBar = new JProgressBar();
		}
		return progressBar;
	}
	
	
	/* VENTANA PRINCIPAL *****************************************************************************/
	private void crearVentanaPrincipal(){
		ventanaPrincipal = new JFrame("Car Tunnning Experience 2008");
		// ponemos el panelPrimero en la ventana
		ventanaPrincipal.add("North", getPanelPrimero());
		ventanaPrincipal.pack(); //seteamos las dimensiones de la ventana
		ventanaPrincipal.setResizable(false);//que no se pueda cambiar de tamaño
		ventanaPrincipal.setLocationRelativeTo(null); //centrada
		//agregamos el listener del evento de cerrado de la ventana		
		ventanaPrincipal.addWindowListener(new CloseListener());
	}
	
	private void cerrarVentanaPrincipal(){
		// como no se cerrar ventanas, la desaparezco
		// y lo pongo en null, para que el recolector se encargue
		ventanaPrincipal.setVisible(false);
		ventanaPrincipal = null;
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
		ventanaMenu = new JFrame("Car Tunnning Experience 2008");
		// ponemos el panelPrimero en la ventana
		ventanaMenu.add("North", getPanelSegundo());
		ventanaMenu.pack(); //seteamos las dimensiones de la ventana
		ventanaMenu.setResizable(false);
		ventanaMenu.setLocationRelativeTo(null); //centrada
		//agregamos el listener del evento de cerrado de la ventana		
		ventanaMenu.addWindowListener(new CloseListener());
	
	}
	
	private void cerrarVentanaMenu(){
		// como no se cerrar ventanas, la desaparezco
		// y lo pongo en null, para que el recolector se encargue
		ventanaMenu.setVisible(false);
		ventanaMenu = null;
	}
	
	private JPanel getPanelSegundo(){
		if (panelSegundo == null) {
			panelSegundo = new JPanel();
			panelSegundo.add(getBotonManejar());
			panelSegundo.add(getBotonCarrera());
			panelSegundo.add(getBotonAuto());
			panelSegundo.add(getBotonPista());
			panelSegundo.add(getBotonGuardar());
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
	
	private JButton getBotonGuardar(){
		if (botonGuardar == null) {
			botonGuardar = new JButton();
			botonGuardar.setText("Guardar");
			botonGuardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Guardar();
				}
			});
		}
		return botonGuardar;
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
	
	/* VENTANA JUEGO *********************************************************************************/
	private void crearVentanaJuego(){
		ventanaJuego = new JFrame("Manejar - CTE08");
		ventanaJuego.setSize(300, 100);
		ventanaJuego.setResizable(false);
		ventanaJuego.setAlwaysOnTop(true);
		ventanaJuego.setLocationRelativeTo(null); //centrada
		ventanaJuego.addKeyListener(new Usuario(auto));
		//agregamos el listener del evento de cerrado de la ventana		
		ventanaJuego.addWindowListener(new CloseListener());	
	}
	
	private void cerrarVentanaJuego(){
//		 como no se cerrar ventanas, la desaparezco
		// y lo pongo en null, para que el recolector se encargue
		ventanaMenu.setVisible(false);
		ventanaMenu = null;
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
