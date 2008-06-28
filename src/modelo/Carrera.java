/*
 * ALGUNAS COSAS QUE SE ME OCURRIERON A LA NOCHE DE COMO PODRIA SER UNA CARRERA
 */

package modelo;

import control.*;
import modelo.exceptions.*;

public class Carrera implements Runnable {

	public void run() {
		// TODO Auto-generated method stub
		
	}

	private Pista pista;
	
	/*
	 * la apuesta corresponde a la cantidad de dinero que cada jugador debera
	 * pagar en caso de perder la carrera
	 */ 
	private AlgoPesos apuesta;
	
	private Jugador jugador[];
	
	private int cantidadJugadores;
	
	/*
	 * 	ANTES DE CONSTRUIR LA CARRERA SE DEBE CHEQUEAR QUE EL AUTO
	 * 	TENGA TODOS SUS COMPONENTES EN UN ESTADO VALIDO, SI NO
	 * 	AL INTENTAR INICIALIZAR SE SALDRA DELA EJECUCION.
	 * 	TODOS LOS JUGADORES TIENEN QUE TENER DINERO SUFICIENTE PARA
	 * 	ENFRENTAR LA APUESTA
	 */
	public Carrera(Jugador[] jugador, Pista pista, AlgoPesos apuesta){
		this.jugador = jugador;
		this.pista = pista;
		this.cantidadJugadores = jugador.length;
		this.apuesta = apuesta;
	}
	
	/**
	 * Metodo que se encarga de inicializar los atributos para la carrera
	 */
	public void incializar() throws ExceptionAutoNoListoParaCarrera{
		
		/*	se comprueba que los autos contengan todos sus componentes 
		 * 	en estados validos, y se setean las posiciones en 0 
		 */
		for(int i = 0; i < this.cantidadJugadores; i++){
			try{
				this.jugador[i].getAuto().comprobarComponentes();
			}catch (ExceptionComponenteFaltante e){
				throw new ExceptionAutoNoListoParaCarrera();
			}catch (ExceptionComponenteDesgastado e){
				throw new ExceptionAutoNoListoParaCarrera();
			}
			this.jugador[i].getAuto().setPosicion(0);
		}
		
		/* setear posiciones de autos en 0, 
		 * inicializar controladores
		 * incializar vistas
		 * setear los observadores
		 * etc
		 */
	}
	
	public void finalizar(int jugadorGanador){
		
		for(int i = 0; i < this.cantidadJugadores; i++){
			if (i != jugadorGanador) this.jugador[i].setDinero(this.jugador[i].getDinero().restar(this.apuesta.getEntero(), this.apuesta.getDecimal()));
			else this.jugador[jugadorGanador].setDinero(this.jugador[jugadorGanador].getDinero().sumar(this.apuesta));
		}
		
		/* aumentar / disminuir la plata del jugador que gano / perdio
		 * cerra la vista
		 * terminar los controles
		 */
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
