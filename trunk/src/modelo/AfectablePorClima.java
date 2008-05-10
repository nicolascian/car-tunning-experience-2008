package modelo;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 */
public interface AfectablePorClima{
	/**
	 * Interfaz que implementan todas aquellas clases que sean afectadas
	 * por el clima
	 * @param clima
	 */
	public void afectar(Clima clima);
}