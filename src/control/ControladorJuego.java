package control;

import modelo.*;
import vista.*;

public class ControladorJuego {

	
	private VistaVentana vista = null;
	
	private DatosPartida datos;
	
	
	public ControladorJuego(DatosPartida datos){
		
		this.datos = datos;
	}
	
	
	public void cargarUsuario(){
		
		//cargo el Usuario de archivo
		//se lo paso a datos
		datos.setUsuario(null);
	}
	
	
	public void crearUsuario(String nombre){
		
		//creamos parte del modelo
		//creo un usuario nuevo con cosas por defecto

		datos.setUsuario(new modelo.Usuario(nombre, new AlgoPesos(1000,00), new Auto()));

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
