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
public class Auto implements AfectablePorClima, AfectablePorSuperficie{
	/* comentario acerca de la implementacion de la clase */
	
	private double Velocidad; // integral de Aaceleracion
	private double Aceleracion; // = Potencia de las RPM
	private double Posicion; // dstancia recorrida
	
	private Motor motor;
	private Caja caja;
	private Combustible combustible;
	private Alimentacion alimentacion;
	private Carroceria carroceria;
	private Suspension suspension;	
	private Escape escape;
	private Turbo turbo;
	
	private Llanta LlantaDelanteraIzquierda;
	private Llanta LlantaDelanteraDerecha;
	private Llanta LlantaTraceraIzquierda;
	private Llanta LlantaTraceraDerecha;
	
	private Neumatico NeumaticoDelanteraIzquierda;
	private Neumatico NeumaticoDelanteraDerecha;
	private Neumatico NeumaticoTraceraIzquierda;
	private Neumatico NeumaticoTraceraDerecha;

	/**
	 * constructor
	 *
	 */
	Auto(){
		
		
	}
	
	public double getVelocidad(){
		Velocidad = this.getAceleracion() * 100;
		return Velocidad;
	}
	
	public double getAceleracion(){
		Aceleracion = this.getPotenciaTotal() /10;
		return Aceleracion;
	}
	
	/**
	 * para cada instante puede decirnos cual
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
		         escape.obtenerPotencia()     
		         /*ETC...*/    );
	}
	
	/**
	 * se afectan los componentes del auto por el clima
	 * - esta sobrecargada con Superficie
	 *
	 * @param clima
	 */
	public void afectar(Clima clima){
		
		alimentacion.afectar(clima); //humedad
		carroceria.afectar(clima); //humedad
		escape.afectar(clima); //presion, humedad
		motor.afectar(clima); //temperatura
		suspension.afectar(clima); //temperatura
		turbo.afectar(clima); //humedad
		
		NeumaticoDelanteraIzquierda.afectar(clima); //temperatura
		NeumaticoDelanteraDerecha.afectar(clima);
		NeumaticoTraceraIzquierda.afectar(clima);
		NeumaticoTraceraDerecha.afectar(clima);
		
	}
	
	/**
	 * se afectan los componentes del auto por la superficie
	 * - esta sobrecargada con Clima
	 * 
	 * @param superficie
	 */
	public void afectar(Superficie superficie){
		
		carroceria.afectar(superficie);
		escape.afectar(superficie);
		suspension.afectar(superficie);
		
		NeumaticoDelanteraIzquierda.afectar(superficie);
		NeumaticoDelanteraDerecha.afectar(superficie);
		NeumaticoTraceraIzquierda.afectar(superficie);
		NeumaticoTraceraDerecha.afectar(superficie);
		
		LlantaDelanteraIzquierda.afectar(superficie);
		LlantaDelanteraDerecha.afectar(superficie);
		LlantaTraceraIzquierda.afectar(superficie);
		LlantaTraceraDerecha.afectar(superficie);
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
		turbo.desgastar();
		
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

	public double getPosicion() {
		return Posicion;
	}
	
}