/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente;
import java.util.LinkedList;

import modelo.*;

import org.w3c.dom.Element;
import org.w3c.dom.Document;

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
	 * Persistencia
	 * @param xmlElement
	 */
	public Embrague(Element xmlElement){
		//levanto los valores
		this.estado=( Double.parseDouble(xmlElement.getAttribute("estado")) );
		setNombre("Embrague");
		setPrecio(new AlgoPesos(3200,00)); //algo$
		setPeso(4); // Kg
	}
	
	public Element toXml(Document doc) {
		Element xmlElement = doc.createElement("embrague");
		xmlElement.setAttribute("estado", String.valueOf(this.getEstado()));
		return xmlElement;
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
		
		ActualizarObservadores();
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

	/**
	 * @Pre:-
	 * @Post: Se genera una lista con varias instancias de componentes de la misma 
	 * clase con atributos diferentes.
	 * @return
	 */
	public static LinkedList<Componente> createVariosComponentesDistintos(){
		LinkedList<Componente> lista=new LinkedList<Componente>();
		lista.add(new Embrague());
		return lista;
	}
	
}
