/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

import org.w3c.dom.Element;
import org.w3c.dom.Document;

/**
 * este es el usuario del modelo, no el control
 *
 */
public class Usuario extends Jugador {

	private String nombre;
	

	public Usuario(String nombre, AlgoPesos capital, Auto auto){
		setNombre(nombre);
		this.setDinero(capital);
		setAuto(auto);
	}
	
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
