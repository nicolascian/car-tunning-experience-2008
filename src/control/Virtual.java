/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package control;
import java.awt.event.KeyEvent;

import modelo.Auto;

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
	/* hacemos delegacion a Habilidad */

	/**
	 * Todo jugador Virtual, tiene una Habilidad que le dice que tan "bien" jugar.
	 */
	private Habilidad habilidad;

	/**
	 * Constructor de Virtual con 3 parametros
	 * 
	 * @param nombre recibe el nombre con el cual se identifica
	 * @param habilidad recibe su habilidad
	 * @param auto recibe el auto con el cual competira
	 */
	public Virtual(String nombre, Habilidad habilidad, Auto auto){
		super(nombre);
		setAuto(auto);
		setHabilidad(habilidad);
	}
	
	/**
	 * Constructor de jugador Virtual con 2 parametros
	 * 
	 * por default el nombre es: PC_DEFAULT_NAME
	 * 
	 * @param habilidad recibe una habilidad para jugar
	 * @param auto recibe el auto con el cual competira
	 */
	public Virtual(Habilidad habilidad, Auto auto){
		super(PC_DEFAULT_NAME);
		setAuto(auto);
		setHabilidad(habilidad);
	}

	
	/**
	 * Metodo Jugar
	 * 
	 * este metodo permite desarrollar los cambios durante una carrera,
	 * se ejecuta indicando que es el turno de jugar, de dicho jugador.
	 * 
	 */
	public void jugar(boolean X){
		
		/* le paso la pelota a la habilidad */
		habilidad.jugar(X);	
	}
		
	
	/**
	 * para que solo lo haga una vez durante un turno
	 */
	public void manejar(){
		
		/* le paso la pelota a la habilidad */
		habilidad.manejar();	
	}
	
	
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
	
	public void keyPressed(KeyEvent arg0) {}

	public void keyReleased(KeyEvent arg0) {}

	public void keyTyped(KeyEvent arg0) {}
	
	/* toString */
	
	public String toString() {
		
		return super.toString() + "  Habilidad: " +  habilidad.toString() +'\n'; 
	}
}