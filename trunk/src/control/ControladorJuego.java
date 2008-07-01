package control;

import javax.swing.*;
import modelo.*;
import vista.ventanas.*;

public class ControladorJuego {

	
	private DatosPartida datos;
	
	
	public ControladorJuego(DatosPartida datos){
		
		this.datos = datos;
	}
	
	
	public void cargarUsuario(){
		
		datos.Cargar();
	}
	
	
	public void crearUsuario(String nombre){
		
		//creamos parte del modelo
		//creo un usuario nuevo con cosas por defecto

		datos.crearJuegoNuevo(nombre);

	}

	public void correrCarrera(JFrame ventanaMenu){
		
		datos.Carrera(ventanaMenu);
	}
	
	public void correrSolo(){
		
		datos.Manejar();
	}
	
	
	public void Guardar(){
		
		datos.Guardar();
	}
	
}
