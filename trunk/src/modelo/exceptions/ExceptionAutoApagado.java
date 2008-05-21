/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.exceptions;

/**
 * Clase de una excepcion creada por el programador
 * la cual hereda de ecxeption, y se produce cuando se intenta realizar
 * alguna accion con el auto y el mismo se encuentra apagado.
 */
public class ExceptionAutoApagado extends Exception {
	
	public ExceptionAutoApagado() {
		super ("Exception Auto apagado");
	}

	@Override
	public String toString() {
		return ("Error Auto Apagado, carrera finalizada");
	}
}
