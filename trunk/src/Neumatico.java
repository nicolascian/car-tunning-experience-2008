/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 * 
 * se ve afectado por la temperatura del clima y superficie
 * 
 * @version	1.0
 */
public abstract class Neumatico extends Componente
implements AfectablePorClima, AfectablePorSuperficie{
	
	
	public Neumatico(){		
	this.setEstado(100);
	}
	public void desgastar(){
		//tener en cuenta la temperatura del clima y superficie
	}
	
	public double obtenerPotencia(){
		//tener en cuenta la temperatura del clima y superficie
		return 0;
	}
	
	/** el clima afecta a los neumaticos */
	public void afectar(Clima clima){
	
	}
	
	/** la superficie afecta a los neumaticos */
	public void afectar(Superficie superficie){
		
	}

	/* (non-Javadoc)
	 * @see Componente#isListoParaCarrera()
	 */
	@Override
	public boolean isListoParaCarrera() {
		// TODO Auto-generated method stub
	return listoParaCarrera;
	}

	/* (non-Javadoc)
	 * @see Componente#actualizarListoParaCarrera()
	 */
	@Override
	public void actualizarListoParaCarrera() {
		// TODO Auto-generated method stub
		if (getEstado()> 0)
			listoParaCarrera= true;
		else 
			listoParaCarrera=false;
			
	}

	

	
	
}