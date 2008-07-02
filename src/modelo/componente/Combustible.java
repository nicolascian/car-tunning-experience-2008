/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente;

import modelo.*;

import org.w3c.dom.Element;
import org.w3c.dom.Document;

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
		setPeso(33.75);
		setCapacidad(capacidad);
		setEstado(capacidad);
		setIndiceDeCombustion(indiceDeCombustion);
		setPrecio(new AlgoPesos(3,5)); //algo$
	}
	
	/**
	 * Persistencia
	 * @param xmlElement
	 */
	public Combustible(Element xmlElement){
		//levanto los valores
		this.estado=( Double.parseDouble(xmlElement.getAttribute("estado")) );
		indiceDeCombustion =( Double.parseDouble(xmlElement.getAttribute("indicecombustion")) );
		capacidad =( Double.parseDouble(xmlElement.getAttribute("capacidad")) );
		this.peso=33.75;
		setPrecio(new AlgoPesos(3,5)); //algo$
	}
	
	public Element toXml(Document doc) {
		Element xmlElement = doc.createElement("combustible");
		xmlElement.setAttribute("estado", String.valueOf(this.getEstado()));
		xmlElement.setAttribute("indicecombustion", String.valueOf(this.indiceDeCombustion));
		xmlElement.setAttribute("capacidad", String.valueOf(this.capacidad));
		return xmlElement;
	}
	
	/**
	 * el combustible, como otros componentes
	 * se desgasta.
	 */
	public void desgastar(){
		
		this.setEstado(this.getEstado() - ( this.getAuto().getAlimentacion().CombustibleAConsumir())/this.getCapacidad()*Constantes.tiempoPorCiclo ); 
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