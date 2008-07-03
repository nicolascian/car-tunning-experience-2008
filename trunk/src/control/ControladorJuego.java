package control;

import javax.swing.*;

import modelo.*;
import vista.ventanas.*;
import control.*;

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

	public void correrCarrera(JFrame ventanaMenu, double apuestaD){

	AlgoPesos apuesta = new AlgoPesos((int) apuestaD,(int) (apuestaD - (double)((int)apuestaD)));
		if (datos.getUsuario().getDinero().compareTo(apuesta)>=0){
			if (datos.getUsuario().getAuto().estaListoParaCarrera()){
				datos.Carrera(ventanaMenu, apuesta);
			}else{
				JOptionPane.showMessageDialog(ventanaMenu, 
						" El auto del usuario no esta listo para la carrera ");
				ventanaMenu.setVisible(true);
			}
		}else{
			JOptionPane.showMessageDialog(ventanaMenu, 
					" No tiene suficiente dinero para afrontar la apuesta ");
			ventanaMenu.setVisible(true);
		}
		

	}
	
	public void correrSolo(JFrame ventanaMenu){
		
		datos.Manejar(ventanaMenu);
	}
	
	
	public void Guardar(){
		
		datos.Guardar();
	}


	public void cambiarHabilidad(String strg){
	
		datos.cambiarHabilidad(strg);
	}
	
	
	
	/**
	 * @return the datos
	 */
	public DatosPartida getDatos() {
		return datos;
	}


	/**
	 * @param datos the datos to set
	 */
	public void setDatos(DatosPartida datos) {
		this.datos = datos;
	}
	
}
