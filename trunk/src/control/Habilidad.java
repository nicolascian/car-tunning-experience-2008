/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package control;
import modelo.Auto;
import java.util.Random;

/**
 * Clase Habilidad
 * 
 * Es la capacidad de conduccion del jugador virtual (PC)
 * Se entiende por jugador virtual a aquel que es manejado por la computadora.
 * 
 * Esta es una clase abstracta de la cual heredan: Principiante, Intermedio y Experto.
 * 
 * No tiene sentido la existencia de Habilidad si no existe un Jugador Virtual, 
 * es por eso que esta clase es parte de la clase Virtual.
 * 
 * @version	1.0
 * @see  modelo.Principiante  Principiante
 * @see  modelo.Intermedio  Intermedio
 * @see  modelo.Experto  Experto
 */
public abstract class Habilidad{
	/* simple uso de polimorfismo */
	
	protected Random rnd;
	protected double MARGEN_DE_ERROR_RND_MAXIMAS;
	protected double MARGEN_DE_ERROR_RND_MINIMAS;
	
	protected Auto auto;
	
	/**
	 * el constructor de habilidad
	 * inicializa los numeros aleatorios
	 */
	public Habilidad(){
		rnd = new Random();
	}
	
	/**
	 * Metodo Jugar
	 * 
	 * este metodo permite desarrollar los cambios durante una carrera,
	 * se ejecuta indicando que es el turno de jugar, de dicho jugador.
	 * 
	 * es Abstracta, asi que cualquier heredero esta obligado a implementarla
	 */
	public abstract void jugar(boolean X);
	
	/**
	 * en caso de querer que solo
	 * cumpla con un turno
	 */
	public abstract void manejar();
	
	
	public void setAuto(Auto auto) {
		this.auto = auto;
	}
	
}