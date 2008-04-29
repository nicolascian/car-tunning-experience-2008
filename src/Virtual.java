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
public class Virtual extends Jugador{
	/* comentario acerca de la implementacion de la clase */
	
	/**
	 * Documentacion
	 */
	private Habilidad habilidad;
	
	Virtual(Auto auto, Habilidad habilidad){
		this.auto = auto;
		this.habilidad = habilidad;
	}

	public Habilidad getHabilidad() {
		return habilidad;
	}

	public void setHabilidad(Habilidad habilidad) {
		this.habilidad = habilidad;
	}
	
}