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
	
	private Motor motor;
	
	private Caja caja;

	private Combustible combustible;
	
	private Alimentacion alimentacion;
	
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
	 * para cada instante puede decirnos cuales
	 * es la potencia final del automovil
	 * 
	 * @return
	 */
	public double getPotenciaTotal(){
		//recorremos los componentes pertinentes, y hacemos:
		// componente.obtenerPotencia();
		return ( motor.obtenerPotencia() +   /* de aca salen: Caja, Alimentacion, Combustible*/
		         carroceria.obtenerPotencia() +
		         suspension.obtenerPotencia() +
		         escape.obtenerPotencia()           );
	}
	
	/**
	 * se afectan los componentes del auto por el clima
	 * - esta sobrecargada con Superficie
	 * 
	 * componentes afectados por el clima:
	 * alimentacion -humedad
	 * carroceria -humedad
	 * escape -presion, humedad
	 * llantas -presion
	 * caja -temperatura
	 * motor -temperatura
	 * suspencion -temperatura
	 * neumaticos -temperatura
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
		
		motor.setTemperaturaExterna(clima.getTemperatura());

		
	}
	
	/**
	 * se afectan los componentes del auto por la superficie
	 * - esta sobrecargada con Clima
	 * 
	 * componentes afectados por la superficie:
	 * carroceria
	 * suspension
	 * escape
	 * llanta
	 * neumaticos
	 * 
	 * @param superficie
	 */
	public void afectar(Superficie superficie){
		
		
	}
	
	
	/**
	 * es invocado en cada ciclo durante la carrera, se encarga
	 * de deteriorar los componentes del auto
	 */
	public void Desgastar(){
		//para todos los componentes, hacer: componente.desgastar();
		
		//hay que arreglar esto, con algo de Polimorfismo e iteracion
		//una lista de Componentes que se recorre
		caja.desgastar();
		motor.desgastar();
		alimentacion.desgastar();
		combustible.desgastar();
		carroceria.desgastar();
		suspension.desgastar();
		escape.desgastar();
		
		LlantaDelanteraIzquierda.desgastar();
		LlantaDelanteraDerecha.desgastar();
		LlantaTraceraIzquierda.desgastar();
		LlantaTraceraDerecha.desgastar();
	
		NeumaticoDelanteraIzquierda.desgastar();
		NeumaticoDelanteraDerecha.desgastar();
		NeumaticoTraceraIzquierda.desgastar();
		NeumaticoTraceraDerecha.desgastar();
	}
	
	
	
	
	
	/* setters y getters */
	
	public Combustible getCombustible() {
		return combustible;
	}

	public void setCombustible(Combustible combustible) {
		this.combustible = combustible;
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