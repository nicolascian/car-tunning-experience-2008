/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

/**
 * El embrague es un sistema que permite transmitir o no una energía mecánica a 
 * su acción final. En un automóvil, por ejemplo, permite transmitir o no la 
 * potencia del motor a las ruedas.
 *
 * Está constituido por un conjunto de piezas situadas entre el motor y los 
 * dispositivos de transmisión.
 *
 * @version 1.0
 * @see <a href="http://es.wikipedia.org/wiki/Embrague">Embrague - Wikipedia</a> 
 */
public class Embrague extends Componente {

	
	private boolean embragado = false;
	
	public Embrague(){
		setEstado(100);
	}
	
	public Embrague(double estado){
		setEstado(estado);
	}
	
	@Override
	public void desgastar() {
		setEstado(getEstado() - 0.00000008);
		
	}

	@Override
	public double obtenerPotencia() {
		// no otorga potencia
		return 0;
	}


}
