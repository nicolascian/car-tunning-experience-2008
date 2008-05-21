/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente;
import modelo.*;

/**
 * Combustible es un componente capaz de 
 * liberar energia cuando se cambia o transforma una 
 * estructura quimica
 * es un componente
 * 
 * @version	1.0
 */
public class Combustible extends Componente{
	
	
	private double indiceDeCombustion;
	
	private double capacidad;
	
	/**
	 * constructor, queda instanciada la clase Combustible
	 * 
	 * @param capacidad
	 * @param indiceDeCombustion
	 */
	public Combustible(double capacidad, double indiceDeCombustion){
		setCapacidad(capacidad);
		setEstado(capacidad);
		setIndiceDeCombustion(indiceDeCombustion);
	}
	
	/**
	 * el combustible, como otros componentes
	 * se desgasta.
	 */
	public void desgastar(){
		
		this.setEstado(this.getEstado() - ( this.getAuto().getAlimentacion().CombustibleAConsumir())/this.getCapacidad() ); 
	}
	
	/**
	 * la potencia esta dada por el indice de combustion
	 * @return
	 */
	public double obtenerPotencia(){
		return indiceDeCombustion * 100;
	}
	
	
	//getters y setters //
	
	/**
	 * asigno un valor a indiceDeCombustion
	 */
	public void setIndiceDeCombustion(double indice){
		this.indiceDeCombustion = indice;
	}
	
	/**
	 * devuelve la capacidad
	 * @return
	 */
	public double getCapacidad() {
		return capacidad;
	}
	/**
	* le asigno un valor a la capacidad
	* @param capacidad
	*/
	public void setCapacidad(double capacidad) {
		this.capacidad = capacidad;
	}
	/**
	 * observo el estado de mi objeto
	 * mediante una cadena
	 */
	public String toString(){
		String cadena = this.getNombre()+", Estado: "+this.getEstado()+" %.";
		return cadena;
	}
	
	/**
	 * obtenemos el indiceDeCombustion
	 * @return
	 */
	public double getIndiceDeCombustion() {
		return indiceDeCombustion;
	}
	
}