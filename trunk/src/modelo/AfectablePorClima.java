/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

/**
 * Interfaz que implementan todas aquellas clases que sean afectadas
 * de alguna manera por el clima.
 */
public interface AfectablePorClima{

	/**
	 * El metodo expuesto recibe como parametro un clima, y la clase que
	 * implementa la interfaz sera la que implemente la interaccion entre
	 * ambas clases.
	 * @param clima
	 */
	public void afectar(Clima clima);
}