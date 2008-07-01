package control;

import modelo.*;

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

	public void correrCarrera(){
		
		datos.Carrera();
	}
	
	public void correrSolo(){
		
		datos.Manejar();
	}
	
	
	public void Guardar(){
		
		datos.Guardar();
	}
	
}
