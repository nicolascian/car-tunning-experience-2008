/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

/**
 * Clase Jugador (es una clase abstracta)
 * 
 * Un jugador puede ser "humano" (lo llamaremos Usuario) o "computadora" (lo llamaremos Virtual).
 * 
 * Humano = Usuario
 * Computadora = Virtual
 * 
 * @version	1.0
 * @see  modelo.Virtual  Virtual 
 * @see  modelo.Usuario  Usuario
 */
public abstract class Jugador {
	/* implementacion basada en polimorfismo */
	
	/**
	 * Todo jugador consta de un Auto para poder competir
	 */
	protected Auto auto;
	
	/**
	 * Metodo Jugar
	 * 
	 * este metodo permite desarrollar los cambios durante una carrera,
	 * se ejecuta indicando que es el turno de jugar, de dicho jugador.
	 * 
	 * @throws ExceptionAutoApagado
	 */
	public abstract void jugar() throws ExceptionAutoApagado;
	
	
	/* setters y getters */
	
	public void setAuto(Auto auto){
		this.auto = auto;
	}
	
	public Auto getAuto() {
		return auto;
	}

	
}