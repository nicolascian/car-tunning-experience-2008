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
	public void consumirCombustible(){
//		Auto.Combustible.Desgastar(Auto.Motor.getCilindrada / Auto.Combustible.Capacidad);
	}
	
	/**
	 * La potencia de la inyeccion es el 94% de la potencia del combustible 
	 * 
	 * @return
	 */
	public double obtenerPotencia(){
		return ( Auto.Combustible.obtenerPotencia() * 94 )/100;
	}
}