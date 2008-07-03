/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * este es el usuario del modelo, no el control
 *
 */
public class Usuario extends Jugador {

	private String nombre;
	

	public Usuario(String nombre, AlgoPesos dinero, Auto auto){
		setNombre(nombre);
		this.setDinero(dinero);
		setAuto(auto);
	}
	
	/**
	 * Persistencia
	 * @param xmlElement
	 */
	public Usuario(Element xmlElement){
		//traigo el atributo nombre
		this.nombre=xmlElement.getAttribute("nombre");
		//traigo los sub nodos
		
		//levanto dinero
		NodeList nodosDinero=xmlElement.getElementsByTagName("dinero");
		this.dinero = new AlgoPesos((Element)nodosDinero.item(0));
		
		//levanto el auto
		NodeList nodosAuto=xmlElement.getElementsByTagName("auto");
		this.auto = new Auto((Element)nodosAuto.item(0));
	}
	
	/** 
	 * Persistencia
	 */
	public Element toXml(Document doc){
		Element xmlElement = doc.createElement("usuario");
		xmlElement.setAttribute("nombre", getNombre());
		xmlElement.appendChild(getDinero().toXml(doc));
		xmlElement.appendChild(getAuto().toXml(doc));
		return xmlElement;
	}
	
	
	public String toString() {
		String str = "Usuario: " + getNombre() + "\n" +
		             "Capital: " + getDinero() + "\n" +
		             "Auto: " + getAuto() + "\n" ;
		return str;
	}
	
	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
