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
public class Auto{
	/* comentario acerca de la implementacion de la clase */
	
	/**
	 * Documentacion
	 */
	private Caja caja;
	
	/**
	 * Documentacion
	 */
	private Motor motor;
	
	private Inyeccion inyeccion;
	
	private Alimentacion alimentacion;
	
	private Combustible combustible;
	
	private Carroceria carroceria;
	
	private Suspension suspension;
	
	private Escape escape;
	
	private Llanta LlantaDelanteraIzquierda;
	private Llanta LlantaDelanteraDerecha;
	private Llanta LlantaTraceraIzquierda;
	private Llanta LlantaTraceraDerecha;
	
	private Neumatico NeumaticoDelanteraIzquierda;
	private Neumatico NeumaticoDelanteraDerecha;
	private Neumatico NeumaticoTraceraIzquierda;
	private Neumatico NeumaticoTraceraDerecha;
	
	/**
	 * 
	 *
	 */
	public void Desgastar(){
		//para todos los componentes, hacer:. componente.desgastar(); excepto para Combustible
	}
	
	/**
	 * se afectan los componentes del auto por el clima
	 * - esta sobrecargada con Superficie
	 * 
	 * @param clima
	 */
	public void afectar(Clima clima){
		
		/* la alimentacion se ve afectada por el clima
		 * supongamos que la humedad optima para la
		 * alimentacion es 30% 
		 */
		alimentacion.setEfectoClimatico(clima.getHumedad()/ 30);
		// entonces el efecto climatico queda en 1 si es optimo
		// si es mas de eso el efecto es maypr a 1
		
		
	}
	
	/**
	 * se afectan los componentes del auto por la superficie
	 * - esta sobrecargada con Clima
	 * 
	 * @param superficie
	 */
	public void afectar(Superficie superficie){
		
		
	}
	
	
	/* setters y getters */
	
	public Combustible getCombustible() {
		return combustible;
	}

	public void setCombustible(Combustible combustible) {
		this.combustible = combustible;
	}

	public Inyeccion getInyeccion() {
		return inyeccion;
	}

	public void setInyeccion(Inyeccion inyeccion) {
		this.inyeccion = inyeccion;
	}


	public void setCaja(Caja caja){
		this.caja = caja;
	}
	

	public Caja getCaja(){
		return this.caja;
	}

	public void setMotor(Motor motor){
		this.motor = motor;
	}
	
	public Motor getMotor(){
		return this.motor;
	}

	public Alimentacion getAlimentacion() {
		return alimentacion;
	}

	public void setAlimentacion(Alimentacion alimentacion) {
		this.alimentacion = alimentacion;
	}
	
}