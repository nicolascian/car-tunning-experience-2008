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
	
	/**
	 * 
	 *
	 */
	public void Desgastar(){
		//para todos los componentes, hacer:. componente.desgastar(); excepto para Combustible
	}
	
	public void afectar(Clima clima){
		
	}
	
	/**
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