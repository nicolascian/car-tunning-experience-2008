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
public class Inyeccion extends Alimentacion{
	/* comentario acerca de la implementacion de la clase */
	
	/**
	 * La inyeccion consume menos combustible pero provee menos potencia
	 */
	public double CombustibleAConsumir(){
		/* se consume combustible segun la Cilindrada, el tipo de combustible
		 * y se afecta segun efectoclimatico y el Estado */         

		double valor = ( this.getAuto().getMotor().getCilindrada() * this.getAuto().getMotor().getRPM() ) / this.getAuto().getCombustible().getCapacidad();
			
		return (valor * this.getEfectoClimatico() * this.getEstado());
	}
	
	/**
	 * La potencia de la inyeccion es el 94% de la potencia del combustible 
	 * 
	 * @return
	 */
	public double obtenerPotencia(){
		return ((this.getAuto().getCombustible().obtenerPotencia() *94) /100) * this.getEfectoClimatico() * this.getEstado();
	}
}