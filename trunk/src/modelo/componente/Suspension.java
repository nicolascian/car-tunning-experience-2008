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
 * la suspension es un componente que absorbe 
 * las irregularidades del terreno por el que se circula
 * para aumentar la comodidad y el control del vehiculo.
 * se ve afectada por la temperatura del clima y la superficie
 * 
 * @version	1.0
 */
public class Suspension extends Componente
implements AfectablePorSuperficie, AfectablePorClima{
	
	private double EfectoClimatico;
	private double EfectoSuperficie;
	
	private double rigidez;
	
	/**
	 * constructor, queda instanciada la clase Suspension
	 */
	public Suspension(){
		this.setEstado(100);
		this.setPeso(56);
		this.setEfectoClimatico(1);
		this.setEfectoSuperficie(1);
		this.rigidez=1;
		this.setPrecio(new AlgoPesos(800,0));
		this.setNombre("Suspencion estandar");
		this.setAuto(null);
	}
	
	/**
	 * constructor con parametros
	 * queda instanciada la clase
	 * @param rigidez
	 */
	public Suspension(double rigidez){
		this.rigidez = rigidez;
		this.setEstado(100);
		this.setPeso(56);
		this.setEfectoClimatico(1);
		this.setEfectoSuperficie(1);
		this.setPrecio(new AlgoPesos(800,0));
		this.setNombre("Suspencion estandar");
		this.setAuto(null);
	}
	
	/**
	 * Persistencia
	 * @param xmlElement
	 */
	public Suspension(Element xmlElement){
		//levanto los valores
		EfectoClimatico = ( Double.parseDouble(xmlElement.getAttribute("efectoclimatico")) );
		EfectoSuperficie = ( Double.parseDouble(xmlElement.getAttribute("efectosuperficie")) );
		rigidez = ( Double.parseDouble(xmlElement.getAttribute("rigidez")) );
		this.estado=( Double.parseDouble(xmlElement.getAttribute("estado")) );
		this.setPrecio(new AlgoPesos(800,0));
		this.setNombre("Suspencion estandar");
		this.setAuto(null);
		this.setPeso(56);
	}
	
	public Element toXml(Document doc) {
		Element xmlElement = doc.createElement("suspension");
		xmlElement.setAttribute("estado", String.valueOf(this.getEstado()));
		xmlElement.setAttribute("efectoclimatico", String.valueOf(this.EfectoClimatico));
		xmlElement.setAttribute("efectosuperficie", String.valueOf(this.EfectoSuperficie));
		xmlElement.setAttribute("rigidez", String.valueOf(this.rigidez));
		return xmlElement;
	}
	
	/**
	 * como es un componente, con el pasar del tiempo se
	 * va desgastando hasta quedar imposible de usar
	 */
	public void desgastar(){
//		tener en cuenta la temperatura del clima y superficie
		this.setEstado(getEstado() - EfectoClimatico - EfectoSuperficie - 1/1000000000);
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
	 * la suspension otorga una
	 * potencia que se ve afectada por 
	 * el clima y la superficie
	 */
	public double obtenerPotencia(){
//		tener en cuenta la temperatura del clima y superficie
		return (EfectoSuperficie*EfectoClimatico/10) * rigidez/10 * getEstado();
	}
	
	/** 
	 * La suspension se 
	 * ve afectada por el clima
	 */
	public void afectar(Clima clima){
		EfectoClimatico = clima.getTemperatura()/100;
	}
	
	/** 
	 * la suspension se 
	 * ve afectada por la superficie 
	 */
	public void afectar(Superficie superficie){
		EfectoSuperficie = (superficie.getRugosidad()/1000 + superficie.getParticulasSueltas()/1000);
	}

	public double getEfectoClimatico() {
		return EfectoClimatico;
	}

	public void setEfectoClimatico(double efectoClimatico) {
		EfectoClimatico = efectoClimatico;
	}

	public double getEfectoSuperficie() {
		return EfectoSuperficie;
	}

	public void setEfectoSuperficie(double efectoSuperficie) {
		EfectoSuperficie = efectoSuperficie;
	}

	
}