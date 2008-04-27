/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 * 
 * @version	1.0
 */
public class Combustible{
	/* comentario acerca de la implementacion de la clase */
	
	/**
	 * Documentacion
	 */
	private double indiceDeCombustion;
	
	/**
	 * 
	 * @return
	 */
	public double obtenerPotencia(){
		return indiceDeCombustion * 100;
	}
	
	public void setIndiceDeCombustion(double indice){
		this.indiceDeCombustion = indice;
	}
	
}