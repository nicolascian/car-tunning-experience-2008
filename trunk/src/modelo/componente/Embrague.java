/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente;
import modelo.*;

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
	
	/**
	 * Constructor de Embrague sin argumentos
	 * pone el estado de la clase en 100%
	 */
	public Embrague(){
		setEstado(100);
		setNombre("Embrague");
		setPrecio(new AlgoPesos(1830,00)); //algo$
		setPeso(4); // Kg
	}
	
	/**
	 * Nos informa el estado de la instancia: Embragado o no.
	 * @return
	 */
	public boolean isEmbragado(){
		return embragado;
	}
	
	/**
	 * Nos permite cambiar el estado de la instancia enre Acoplado 
	 * y Desacoplado.
	 * 
	 * @param valor
	 */
	public void embragar(boolean valor){
		embragado = valor;
		desgastar();
	}
	
	@Override
	public void desgastar() {
		setEstado(getEstado() - 0.00000008);
		
	}

	@Override
	/**
	 * El embrague no otorga potencia
	 */
	public double obtenerPotencia() {
		// no otorga potencia
		return 0;
	}

	/* toString */
	
	public String toString(){
		return super.toString();
	}

}
