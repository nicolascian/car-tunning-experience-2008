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
	
	/**
	 * Documentacion
	 * 
	 * Carburador depende del clima, pues mezcla combustible con aire.
	 * 
	 * el Carburador es un sis de alimentacion que consume ineficientemente 
	 * el combustible en mayor cantidad, por ciclo, pero provee mayor potencia.
	 */
	public void consumirCombustible(){
		/* ponderar Auto.Motor.getCilindrada
		 *          Auto.Combustible.Capacidad
		 *          Pista.Clima
		 *          
		 *          y pasar el valor ponderado
		 */         
		//Auto.Combustible.Desgastar(valor ponderado);
	}
	
	/**
	 * La potencia de la carburador es el 98% de la potencia del combustible 
	 * 
	 * @return
	 */
	public double obtenerPotencia(){
		/* depende del tipo de combustible */
		return ( Auto.Combustible.obtenerPotencia() *98 )/100;
	}
	
}