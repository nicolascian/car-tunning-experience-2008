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

		double valor = auto.getMotor().getCilindrada() * auto.getMotor().getRPM();
			
		return (valor * (1/Estado) * (EfectoClimatico/10)  );
	}
	
	/**
	 * a la inyeccion, el clima, lo afecta en menor medida
	 */
	public void desgastar(){
		Estado = (Estado - (EfectoClimatico/10000000) - 1/1000000000 );
	}
	
	/**
	 * La potencia de la inyeccion es el 94% de la potencia del combustible 
	 * 
	 * @return
	 */
	public double obtenerPotencia(){
		return ((auto.getCombustible().obtenerPotencia() *94) /100) * EfectoClimatico * Estado;
	}
}