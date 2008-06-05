/*
 * ALGUNAS COSAS QUE SE ME OCURRIERON A LA NOCHE DE COMO PODRIA SER UNA CARRERA
 */

import modelo.*;
import control.*;

public class Carrera {

	private Pista pista;
	
	private AlgoPesos premio;
	
	private Jugador jugador[];
	
	public Carrera(){
		
	}
	
	/**
	 * Metodo que se encarga de inicializar los atributos para la carrera
	 */
	public void incializar(){
		/* setear posiciones de autos en 0, 
		 * inicializar controladores
		 * incializar vistas
		 * setear los observadores
		 * etc
		 */
	}
	
	public void finalizar(int jugadorGanador){
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
