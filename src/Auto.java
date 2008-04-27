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
	
	
	public Inyeccion getInyeccion() {
		return inyeccion;
	}

	public void setInyeccion(Inyeccion inyeccion) {
		this.inyeccion = inyeccion;
	}

	/**
	 * Documentacion
	 */
	public void setCaja(Caja caja){
		this.caja = caja;
	}
	
	/**
	 * Documentacion
	 */
	public Caja getCaja(){
		return this.caja;
	}

	/**
	 * Documentacion
	 */
	public void setMotor(Motor motor){
		this.motor = motor;
	}
	
	/**
	 * Documentacion
	 */
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