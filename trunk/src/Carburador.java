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
	 * este atributo es modificado por el Clima, cuando hace Auto.afectar
	 */
	private double efectoClimatico;
	
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
		this.getAuto().getCombustible().Desgastar(valor ponderado);
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
	
		return ((this.getAuto().getCombustible().obtenerPotencia() *98) /100) *efectoClimatico *getEstado();
	}
	
}