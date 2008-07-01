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
 * Frenos de cinta o de banda.
 * Utilizan una banda flexible, las mordazas o zapatas (suelen ser de amianto) 
 * se aplican para ejercer tensión sobre un cilindro o tambor giratorio que se 
 * encuentra solidario al eje que se pretenda controlar. La banda al ejercer presión, 
 * ejerce la fricción con la cual se disipa en calor la energía cinética del cuerpo 
 * a regular.
 * 
 * @see <a href="http://es.wikipedia.org/wiki/Freno#Frenos_de_fricci.C3.B3n">Freno de Cinta - Wikipedia</a>
 */
public class FrenoCinta extends Freno {

	//temperatura optima de un freno de Cinta
	private static double TEMP_OPTIMA = 24; // °C
	// humedad optima de un freno de Cinta
	private static double HUM_OPTIMA = 10; // %

	/**
	 * Constructor de Freno a Cinta sin paraetros
	 *coloca la instancia en estado 100%
	 */
	public FrenoCinta(){
		setEstado(100);
		setNombre("Freno de Cinta o de Banda");
		setPrecio(new AlgoPesos(370,00)); //algo$
		setPeso(50); // Kg
	}
	
	/**
	 * Persistencia
	 * @param xmlElement
	 */
	public FrenoCinta(Element xmlElement){
		//levanto los valores
		this.EfectoClimatico =( Double.parseDouble(xmlElement.getAttribute("efectoclimatico")) );
		this.estado=( Double.parseDouble(xmlElement.getAttribute("estado")) );
		setNombre(xmlElement.getAttribute("nombre"));
	}
	
	public Element toXml(Document doc) {
		Element xmlElement = doc.createElement("freno");
		xmlElement.setAttribute("tipo","cinta");
		xmlElement.setAttribute("estado", String.valueOf(this.getEstado()));
		xmlElement.setAttribute("efectoclimatico", String.valueOf(this.EfectoClimatico));
		xmlElement.setAttribute("nombre", this.getNombre());
		return xmlElement;
	}
	
	@Override
	/**
	 * El clima afecta al freno de disco (temperatura y humedad)
	 */
	public void afectar(Clima clima) {
		EfectoClimatico = clima.getTemperatura()/TEMP_OPTIMA * clima.getHumedad()/HUM_OPTIMA;
		
	}

	@Override
	public void desgastar() {
		setEstado(getEstado() - EfectoClimatico * 0.0015);
		
	}
	
	/* toString */
	
	public String toString(){
		return getNombre();
	}

}
