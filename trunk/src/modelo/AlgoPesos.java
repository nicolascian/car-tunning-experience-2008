/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Es el tipo de dato con el que vamos a trabajar.
 * 
 * @version	1.0
 */
public class AlgoPesos{
	
	// parte entera de AlgoPesos
	private int entero;
	// parte decimal de AlgoPesos, debe estar entre 0 y 99
	private int decimal;

	
	/** Constructor, queda instanciada la clase.
	 * 
	 * @param entero
	 * @param decimal
	 */
	public AlgoPesos(int entero, int decimal){
		setEntero(entero);
		setDecimal(decimal);
	}
	
	/** Suma dos instancias de AlgoPesos
	 * 
	 * @param entero1
	 * @param entero2
	 * @param decimal1
	 * @param decimal2
	 * @return
	 */
	
	public AlgoPesos sumar(AlgoPesos otro){
		AlgoPesos aux = new AlgoPesos(0,0);
		
		if ((otro.getDecimal() + this.getDecimal()) >99){
			aux.setEntero(aux.getEntero()+1);
			aux.setDecimal(this.getDecimal() + otro.getDecimal() - 100);
		}else aux.setDecimal(this.getDecimal() + otro.getDecimal());
		aux.setEntero(aux.getEntero() + this.getEntero() + otro.getEntero());
		
		return aux;
	}
	
	public AlgoPesos sumar(int entero1, int decimal1){
	
		int parteEntera=(this.getEntero()+ entero1); 
		int parteDecimal= (this.getDecimal()+ decimal1);
		if (parteDecimal>99){
			parteEntera++;
			parteDecimal= parteDecimal -100;
		}
		return new AlgoPesos(parteEntera,parteDecimal);
	}
	
	/** Resta dos instancias de AlgoPesos
	 * 
	 * @param entero1
	 * @param decimal1
	 * @return
	 */
	public AlgoPesos restar(int entero1, int decimal1){
		
		int parteEntera= (this.getEntero()- entero1);
		int parteDecimal=Math.abs((this.getDecimal()- decimal1));
		return new AlgoPesos(parteEntera, parteDecimal);
	}
	
	/**
	 * Asigna la parte Entera
	 * 
	 * @param entero the entero to set
	 */
	public void setEntero(int entero) {
		this.entero = entero;
	}

	/**
	 * nos devuelve la parte entera
	 * 
	 * @return the entero
	 */
	public int getEntero() {
		return entero;
	}

	/**
	 * Asigna la parte decimal 
	 * 
	 * @param decimal the decimal to set
	 */
	public void setDecimal(int decimal) {
		this.decimal = decimal;
	}

	/**
	 * nos devuelve la parte decimal
	 * 
	 * @return the decimal
	 */
	public int getDecimal() {
		return decimal;
	}
	
	
	
	public Element toXml(Document doc){
		Element xmlElement = doc.createElement("capital");
		xmlElement.setAttribute("entero", String.valueOf(getEntero()));
		xmlElement.setAttribute("decimal", String.valueOf(getDecimal()));
		return xmlElement;
	}
	
	
	public String toString() {
		return "capital: " + getEntero() + "," + getDecimal();

	}
	
}