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
public abstract class Caja extends Componente{
	/* comentario acerca de la implementacion de la clase */
	
	/**
	 * Representa la posicion de la Caja de Cambios o Caja
	 * de Velocidades
	 */
	protected int Cambio;

	protected int CantidadCambios;
	
	
	public int getCambio() {
		return Cambio;
	}

	public void setCambio(int cambio) {
		Cambio = cambio;
		auto.getMotor().setRevolucionesMaximas( (5/4)*auto.getMotor().getRevolucionesOptimas() + Cambio * 60 );
	}
	
}