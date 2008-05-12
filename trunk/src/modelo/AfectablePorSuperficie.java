package modelo;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

	/**
	 * Interfaz que deberan implementar todas aquellas clases que sean
	 * afectables de alguna manera por la superficie.
	 */
public interface AfectablePorSuperficie{

	/**
	 * El metodo expuesto recibe como parametros una superficie,y la clase
	 * que implementa esta interfaz sera la encargada de determinar la
	 * interaccion entre ambas clases. 
	 * @param superficie
	 */
	public void afectar(Superficie superficie);
}