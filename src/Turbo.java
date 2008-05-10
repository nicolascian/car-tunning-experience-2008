/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 * 
 * se ve afectado por la humedad del clima
 * 
 * @version	1.0
 */
public class Turbo extends Componente implements AfectablePorClima{
	/* comentario acerca de la implementacion de la clase */
	
	public void desgastar(){
//		tener en cuenta la humedad del clima
	}
	
	public double obtenerPotencia(){
//		tener en cuenta la humedad del clima
		return 0;
	}
	
	/** el clima afecta al turbo */
	public void afectar(Clima clima){
		
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