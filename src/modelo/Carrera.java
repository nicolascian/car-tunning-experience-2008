/*
 * ALGUNAS COSAS QUE SE ME OCURRIERON A LA NOCHE DE COMO PODRIA SER UNA CARRERA
 */

package modelo;

import control.*;
import vista.ventanas.*;
import modelo.exceptions.*;

public class Carrera implements Runnable {

	private Pista pista;
	
	/*
	 * la apuesta corresponde a la cantidad de dinero que cada jugador debera
	 * pagar en caso de perder la carrera
	 */ 
	private AlgoPesos apuesta;
	
	private modelo.Usuario usuario;
	
	private modelo.Virtual virtual;
	
	
	/*
	 * 	ANTES DE CONSTRUIR LA CARRERA SE DEBE CHEQUEAR QUE EL AUTO
	 * 	TENGA TODOS SUS COMPONENTES EN UN ESTADO VALIDO, SI NO
	 * 	AL INTENTAR INICIALIZAR SE SALDRA DELA EJECUCION.
	 * 	TODOS LOS JUGADORES TIENEN QUE TENER DINERO SUFICIENTE PARA
	 * 	ENFRENTAR LA APUESTA
	 */
	public Carrera(Usuario usuario,Virtual virtual, Pista pista, AlgoPesos apuesta){
		this.usuario = usuario;
		this.virtual = virtual;
		this.pista = pista;
		this.apuesta = apuesta;
	}
	
	/**
	 * Metodo que se encarga de inicializar los atributos para la carrera
	 */
	public void incializar(){
		this.usuario.getAuto().setPosicion(0);
		this.virtual.getAuto().setPosicion(0);
		VentanaCarrera vista =  new VentanaCarrera(this.usuario, this.virtual, this.pista);
		this.usuario.getAuto().agregarObservador(vista);
		this.virtual.getAuto().agregarObservador(vista);
		this.usuario.getAuto().ActualizarObservadores();
		this.virtual.getAuto().ActualizarObservadores();
		
		/* setear posiciones de autos en 0, 
		 * inicializar controladores
		 * incializar vistas
		 * setear los observadores
		 * etc
		 */
	}
	
	public void finalizar(int jugadorGanador){
		
		if (this.usuario.getAuto().getPosicion() < this.virtual.getAuto().getPosicion()){
			this.usuario.setDinero(this.usuario.getDinero().restar(this.apuesta.getEntero(), this.apuesta.getDecimal()));
		}else this.usuario.setDinero(this.usuario.getDinero().sumar(this.apuesta));
		
		
		/* aumentar / disminuir la plata del jugador que gano / perdio
		 * cerra la vista
		 * terminar los controles
		 */
	}
	
	public void run() {
		this.incializar();
		
	}
	
	public void correr(){
		/* dentro de un ciclo:
		 * 	discretiza tiempos para el control y para el cambio de posiciones?
		 * 	llamar al desgastar de cada auto
		 * 	finalizar el ciclo cuando a un auto se le acaba la pista-> 
		 *  ->atrapa la excepcion, de donde obtiene el numero correspondiente 
		 *  al auto que finalizo, y dicho numero corresponde tambien al nro de jugador,
		 *  por lo tanto este sera el ganador
		 *
		 * tiene q lanzar un thread para la vista?
		 */
	}
}
