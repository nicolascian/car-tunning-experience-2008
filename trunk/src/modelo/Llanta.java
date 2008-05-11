package modelo;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 * 
 * se afecta por la presion del clima
 * y la superficie
 * 
 * @version	1.0
 */
public class Llanta extends Componente implements AfectablePorSuperficie{
	/* comentario acerca de la implementacion de la clase */
	
	public void desgastar(){
		//tener en cuenta Presion y superficie
	}
	
	public double obtenerPotencia(){
		//tener en cuenta Presion y superficie
		return 0;
	}
	
	
	/** la superficie afecta a las llantas */
	public void afectar(Superficie superficie){
		
	}
	
}