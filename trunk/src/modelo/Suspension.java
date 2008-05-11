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
	
	private double EfectoClimatico;
	private double EfectoSuperficie;
	
	private double rigidez;
	
	public Suspension(){
		setEstado(100);
	}
	
	public Suspension(double rigidez){
		this.rigidez = rigidez;
		setEstado(100);
	}
	
	public void desgastar(){
//		tener en cuenta la temperatura del clima y superficie
		this.setEstado(getEstado() - EfectoClimatico - EfectoSuperficie/100 - 1/1000000000);
	}
	
	public double obtenerPotencia(){
//		tener en cuenta la temperatura del clima y superficie
		return (EfectoSuperficie*EfectoClimatico/100) * rigidez/10 * getEstado();
	}
	
	/** el clima afecta a la suspension */
	public void afectar(Clima clima){
		EfectoClimatico = clima.getTemperatura()/100;
	}
	
	/** la superficie afecta a la suspension */
	public void afectar(Superficie superficie){
		EfectoSuperficie = superficie.getCoeficiente();
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