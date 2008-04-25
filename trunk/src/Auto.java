/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *                        Catedra Ing. Carlos Fontela                         *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 * 
 * @version	1.0 - 24 Abr 2008
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
	
}