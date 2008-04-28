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
public class Carburador extends Alimentacion{
	/* comentario acerca de la implementacion de la clase */
	
	public void desgastar(){
		 this.setEstado(this.getEstado() - (this.getEfectoClimatico()/10000) - 1/1000000000 );
	}
	
	/**
	 * Documentacion
	 * 
	 * Carburador depende del clima, pues mezcla combustible con aire.
	 * 
	 * el Carburador es un sis de alimentacion que consume ineficientemente 
	 * el combustible en mayor cantidad, por ciclo, pero provee mayor potencia.
	 */
	public double CombustibleAConsumir(){
		/* se consume combustible segun la Cilindrada, el tipo de combustible
		 * y se afecta segun efectoclimatico y el Estado */         

		double valor = this.getAuto().getMotor().getCilindrada() * this.getAuto().getMotor().getRPM();
		
		return (valor * this.getEfectoClimatico() * (1/this.getEstado()) );
	}
	
	/**
	 * La potencia de la carburador es el 98% de la potencia del combustible 
	 * y ademas es afectado por el multiplicador "efectoClimatico"
	 * 
	 * depende del tipo de combustible
	 * 
	 * @return
	 */
	public double obtenerPotencia(){
	
		return ((this.getAuto().getCombustible().obtenerPotencia() *98) /100) * this.getEfectoClimatico() * this.getEstado();
	}
	
}