/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

/**
 * Clase  Virtual
 * 
 * Se define Virtual como aquel que competira en el juego controlado por la computadora.
 * EL jugador Virtual tiene una Habilidad especifica, que puede cambiarse en cualquier momento;
 * las habilidades que puede tener, son: Principiante, Intermedio o Experto.
 * 
 * Hereda de Jugador.
 * 
 * @version	1.0
 * @see  modelo.Jugador  Jugador
 * @see  modelo.Habilidad  Habilidad
 */
public class Virtual extends Jugador{
	/* comentario acerca de la implementacion de la clase */
	
	/**
	 * Todo jugador Virtual, tiene una Habilidad que le dice que tan "bien" jugar.
	 */
	private Habilidad habilidad;

	/**
	 * Constructor de jugador Virtual con 1 parametro
	 *  
	 * Al ejecutar este constructor, el Virtual se crea un
	 * Auto para ser utilizado por el mismo 
	 */
	public Virtual(Habilidad habilidad){
		/* se crea un auto por defecto para el */
		setAuto(new Auto());
		setHabilidad(habilidad);
	}
	
	/**
	 * Constructor de jugador Virtual con 2 parametros 
	 * 
	 * @param auto recibe el auto con el cual competira
	 * @param habilidad recibe una habilidad para jugar
	 */
	public Virtual(Auto auto, Habilidad habilidad){
		setAuto(auto);
		setHabilidad(habilidad);
	}

	/**
	 * Metodo Jugar
	 * 
	 * este metodo permite desarrollar los cambios durante una carrera,
	 * se ejecuta indicando que es el turno de jugar, de dicho jugador.
	 * 
	 * @throws ExceptionAutoApagado
	 */
	public void jugar() throws ExceptionAutoApagado{
		
		/* SI ENCENDIDO: */
		if (auto.getMotor().isEncendido()){
			
			/* le paso la pelota a la habilidad */
			habilidad.jugar();
			
		}else{/* si esta apagado, que lo resuelva otro */
		
			throw new ExceptionAutoApagado();
		}
		
	}//fin jugar
	
	
	/* getters y setters */

	/**
	 * Cuando le asignamos una habilidad a Virtual, este mismo metodo
	 * se encarga de avisarle a esta habilidad cual es el auto con
	 * el cual esta compitiendo Virtual.
	 */
	public void setHabilidad(Habilidad habilidad) {
		this.habilidad = habilidad;
		this.habilidad.setAuto(auto);
	}

	public Habilidad getHabilidad() {
		return habilidad;
	}
	
}