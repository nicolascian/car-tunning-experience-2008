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
public abstract class Alimentacion extends Componente{
	/* comentario acerca de la implementacion de la clase */
	
	/**
	 * este atributo es modificado por el Clima, cuando hace Auto.afectar
	 */
	private double EfectoClimatico;
	
	/**
	 * Documentacion
	 */
	public abstract double CombustibleAConsumir();

	public double getEfectoClimatico() {
		return EfectoClimatico;
	}

	public void setEfectoClimatico(double efectoClimatico) {
		EfectoClimatico = efectoClimatico;
	}
	
	
}