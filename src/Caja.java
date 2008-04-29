/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 * 
 * se ve afectada por la temperatura del clima
 * 
 * @version	1.0
 */
public abstract class Caja extends Componente{
	/* comentario acerca de la implementacion de la clase */
	
	/**
	 * Representa la posicion de la Caja de Cambios o Caja
	 * de Velocidades
	 */
	protected int Cambio;

	protected int CantidadCambios;
	
	public abstract void Chequear(double variacion);
	
	public int getCambio() {
		return Cambio;
	}
	
	/**
	 * cada vez que hacemos un Cambio, se altera las revolucionesMaximas del Motor
	 * 
	 * @param cambio
	 */
	public void setCambio(int cambio) {
		Cambio = cambio;
		auto.getMotor().setRevolucionesMaximas( (5/4)*auto.getMotor().getRevolucionesOptimas() + Cambio * 60 );
	}
	
	/** el clima afecta a la caja*/
	public void afectar(Clima clima){
		
	}
	
	/** la superficie no afecta a la caja*/
	public void afectar(Superficie superficie){}
	
}