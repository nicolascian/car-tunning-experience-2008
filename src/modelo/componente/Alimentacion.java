/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente;
import modelo.*;

/**
 * La alimentacion de un motor de combustion interna es la encargada
 * de mezclar el aire con el combustible y luego colocarlo en el motor.
 * 
 * En este modelo utilizaremos Inyecion y Carburador, que son dos
 * sistemas de alimentacion de distinto funcionamiento.
 * 
 * @version	1.0
 * @see modelo.Inyeccion Inyeccion
 * @see modelo.Carburador Carburador
 * @see <a href="http://es.wikipedia.org/wiki/Motor_de_combusti%C3%B3n_interna">Motor de combustion interna - Wikipedia</a>
 */
public abstract class Alimentacion extends Componente {
	/* uso de polimorfismo */
	
	/**
	 * Nos dice el combustible que necesita la alimentacion segun la Cilindrada 
	 * del Motor y las RPM
	 * 
	 * @see modelo.Inyeccion#CombustibleAConsumir() Inyeccion.CombustibleAConsumir
	 * @see modelo.Carburador#CombustibleAConsumir() Carburador.CombustibleAConsumir
	 */
	public abstract double CombustibleAConsumir();
	
	public Alimentacion(){
		setEstado(100);
	}
	
	/* toString */
	
	public String toString() {
		
		return "ALIMENTACION tipo: " +'\n';
	}
	
}