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
 * se ve afectada por la temperatura del clima y la superficie
 * 
 * @version	1.0
 */
public class Suspension extends Componente
implements AfectablePorSuperficie, AfectablePorClima{
	/* comentario acerca de la implementacion de la clase */
	
	public void desgastar(){
//		tener en cuenta la temperatura del clima y superficie
	}
	
	public double obtenerPotencia(){
//		tener en cuenta la temperatura del clima y superficie
		return 0;
	}
	
	/** el clima afecta a la suspension */
	public void afectar(Clima clima){
		
	}
	
	/** la superficie afecta a la suspension */
	public void afectar(Superficie superficie){
		
	}

	/* (non-Javadoc)
	 * @see Componente#isListoParaCarrera()
	 */
	@Override
	public boolean isListoParaCarrera() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see Componente#actualizarListoParaCarrera()
	 */
	@Override
	public void actualizarListoParaCarrera() {
		// TODO Auto-generated method stub
		
	}

	
	
	
}