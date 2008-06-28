package modelo;

import vista.*;
import control.*;
import modelo.*;


public class DatosPartida {

	
	private modelo.Usuario usuario = null;
	private modelo.Virtual virtual = null;
	
	private Pista pista = null;

	
	private ControladorJuego control;
	private vista.ventanas.VistaVentana vista ;
	
	private GestorPersistencia gestor = null;
	
	
	public DatosPartida(){
		
		gestor = new GestorPersistencia();
		
		control = new ControladorJuego(this);
		vista = new vista.ventanas.VistaVentana(control);
		
		
	}

	public void Manejar(){
		
		Manejar manejar = new Manejar(usuario, pista);
		manejar.run();
		
	}
	
	public void Carrera(){
		
		AlgoPesos apuesta = new AlgoPesos(100,00);
		Carrera carrera = new Carrera(usuario, virtual, pista, apuesta);
		carrera.run();
		
	}
	
	public void Guardar(){
		
		try{
			gestor.Guardar(usuario);
		}
		catch(Exception e1){
			//MANEJAR LAS EXCEPTION
		}
		
	}

	

	public Pista getPista() {
		return pista;
	}


	public void setPista(Pista pista) {
		this.pista = pista;
	}


	public modelo.Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(modelo.Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
}
