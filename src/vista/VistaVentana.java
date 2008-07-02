/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package vista;

import control.*;
import vista.ventanas.*;
import javax.swing.*;


public class VistaVentana {

	private ControladorJuego controlJuego;
	private JFrame ventanaPrincipal  = null; //marco que contendra los controles del primer menu
	private JFrame ventanaMenu  = null; //marco que contendra los controles del segundo menu
	private JFrame ventanaCreditos  = null; 
	private VentanaTaller ventanaTaller = null;
	private JFrame ventanaPista = null;
	private JFrame ventanaOpciones = null;
    
	
	/** Constructor de la vista con ventanas */
	public VistaVentana(ControladorJuego ctrl){
		controlJuego = ctrl;
		// decorados
		JFrame.setDefaultLookAndFeelDecorated(false); //false para Windows estandar
		VentanaSplash ventanaSplash = new VentanaSplash();
			
		crearVentanaPrincipal();
		ventanaSplash.setProgresoProgressBar(16);
		crearVentanaMenu();
		ventanaSplash.setProgresoProgressBar(32);
		crearVentanaTaller();
		ventanaSplash.setProgresoProgressBar(48);
		crearVentanaPista();
		ventanaSplash.setProgresoProgressBar(64);
		crearVentanaOpciones();
		ventanaSplash.setProgresoProgressBar(80);
		crearVentanaCreditos();
		ventanaSplash.setProgresoProgressBar(96);
		
		ventanaSplash.setVisible(false);
		ventanaSplash.dispose();
		ventanaPrincipal.setVisible(true);
		
	}
			
	public void JuegoNuevo(){
		String nombre = JOptionPane.showInputDialog(ventanaPrincipal, "Ingresar Nombre:","Jugador Nuevo");
		if (!nombre.equalsIgnoreCase("")){
			cerrarVentanaPrincipal();
			controlJuego.crearUsuario(nombre);	
			ventanaMenu.setVisible(true);
			this.ventanaTaller.setUsuario(controlJuego.getDatos().getUsuario());
		}else{
			JOptionPane.showMessageDialog(ventanaPrincipal,
	        	    "Por favor ingrese un nombre" + '\n' + 
	        	    "de usuario valido.",
	        	    "Error, nombre no valido",
	        	    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void CargarJuego(){
		cerrarVentanaPrincipal();
		controlJuego.cargarUsuario();
		ventanaMenu.setVisible(true);
		this.ventanaTaller.setUsuario(controlJuego.getDatos().getUsuario());
	}
	
	public void Manejar(){
		ventanaMenu.setVisible(false);
		controlJuego.correrSolo(ventanaMenu);
	}
	
	public void Carrera(){
		String apuesta = JOptionPane.showInputDialog(ventanaMenu, "Ingresar Apuesta:","100.00");
		
		if (apuesta!=null){
		
		try{ 
			double monto = Double.parseDouble(apuesta);
			if (monto >= 0){
				ventanaMenu.setVisible(false);
				controlJuego.correrCarrera(ventanaMenu, monto );
			}else{
				JOptionPane.showMessageDialog(ventanaMenu,
		        	    "Por favor ingrese un valor positivo.",
		        	    "Error, apuesta no valida",
		        	    JOptionPane.ERROR_MESSAGE);
			}
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(ventanaMenu,
	        	    "Datos invalidos!. Los decimales" + '\n'+ 
	        	    "se separan con ' . ' (punto) ",
	        	    "Error, apuesta no valida",
	        	    JOptionPane.ERROR_MESSAGE);
			//muestro la ventana de apues nuevamente
			Carrera();
		}
		
		}//fin apuesta no nula
		
	}
	
	public void Auto(){
		//Taller
		ventanaMenu.setVisible(false);
		ventanaTaller.setVisible(true);
	}
	public void Pista(){
		//se pude elegir una pista
		ventanaMenu.setVisible(false);
		ventanaPista.setVisible(true);
	}
	
	public void Guardar(){
		controlJuego.Guardar();
	}
	
	public void Opciones(){	
		
		Object[] possibilities = {"Principiante", "Intermedio", "Experto"};
		String habilidad = (String)JOptionPane.showInputDialog(
		                    ventanaMenu,
		                    "Seleccione la dificultad:",
		                    "Cambiar dificultad",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    possibilities,
		                    "Intermedio");

		//si hay un string
		if ((habilidad != null) && (habilidad.length() > 0)) {
			
			controlJuego.cambiarHabilidad(habilidad);
		}

		//null/empty.
		
		
		
		//ESTO PARA USAR LA VENTANA
		//dificultad del PC, y el auto del PC
		//ventanaMenu.setVisible(false);
		//ventanaOpciones.setVisible(true);
	}
	
	public void Creditos(){
		ventanaMenu.setVisible(false);
		ventanaCreditos.setVisible(true);
	}
	
	

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
		
	
	/* VENTANA TALLER *********************************************************************************/
	private void crearVentanaTaller(){
		ventanaTaller = new VentanaTaller(this.ventanaMenu);
		
	}
	
	
	/* VENTANA PISTA *********************************************************************************/
	private void crearVentanaPista(){
		ventanaPista = new VentanaPista(this.ventanaMenu);
		
	}		

	
	/* VENTANA OPCIONES *********************************************************************************/
	private void crearVentanaOpciones(){
		ventanaOpciones = new VentanaOpciones(this.ventanaMenu);
		
	}
	
		
	/* VENTANA CREDITOS *********************************************************************************/
	private void crearVentanaCreditos(){
		ventanaCreditos = new VentanaCreditos(this.ventanaMenu);
		
	}
	
	
}
