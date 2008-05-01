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
public class Inyeccion extends Alimentacion implements AfectablePorClima{
	/* comentario acerca de la implementacion de la clase */
	private double EfectoClimatico;
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
	
	/** el clima afecta a la inyeccion */
	public void afectar(Clima clima){
		/* la alimentacion se ve afectada por el clima
		 * supongamos que la humedad optima para la
		 * alimentacion es 30% 
		 */
		EfectoClimatico = (clima.getHumedad()/ 30);
		// entonces el efecto climatico queda en 1 si es optimo
		// si es mas de eso el efecto es maypr a 1
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