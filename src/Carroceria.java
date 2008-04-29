/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 * 
 * se ve afectada por la Humedad del clima, la superficie
 * 
 * @version	1.0
 */
public class Carroceria extends Componente{
	/* comentario acerca de la implementacion de la clase */
	
	public void desgastar(){
		//tener en cuenta la humedad, y superficie
	}
	
	public double obtenerPotencia(){
		//tener en cuenta la humedad y superficie
		return 0;
	}
	
	/** el clima afecta a la carroceria */
	public void afectar(Clima clima){
		
	}
	
	/** la superficie  afecta a la carroceria */
	public void afectar(Superficie superficie){
		
	}
}