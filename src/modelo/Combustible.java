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
 * @version	1.0
 */
public class Combustible extends Componente{
	/* comentario acerca de la implementacion de la clase */
	
	/**
	 * Documentacion
	 */
	private double indiceDeCombustion;
	
	private double capacidad;
	
	public void desgastar(){
		
		this.setEstado(this.getEstado() - ( this.getAuto().getAlimentacion().CombustibleAConsumir())/this.getCapacidad() ); 
	}
	
	public double getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(double capacidad) {
		this.capacidad = capacidad;
	}

	public double getIndiceDeCombustion() {
		return indiceDeCombustion;
	}

	/**
	 * la potencia esta dada por el indice de combustion
	 * @return
	 */
	public double obtenerPotencia(){
		return indiceDeCombustion * 100;
	}
	
	public void setIndiceDeCombustion(double indice){
		this.indiceDeCombustion = indice;
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