package modelo;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

	/**
	 * El neumatico es aquel componente del auto que interactua en forma
	 * directa con la superficie. Por lo tanto, es el encargado de transmitir
	 * la pontecia del auto al movimiento.
	 * Al estar en contacto directo con el suelo es afectable por la superficie, 
	 * tanto como por el clima.
	 */
public abstract class Neumatico extends Componente{

	/**
	 * Metodo que a partir del estado del neumatico y de las condiciones climaticas
	 * se encarga de calgular la adherencia.
	 * Devuelve un valor entre 0 y 1. 1 corresponde a una adherencia del 100%, y 
	 * 0 a una adherencia de 0%.
	 */
	public abstract double calcularAdherencia();

}