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
 * Un freno de disco es un dispositivo cuya función es detener o reducir la 
 * velocidad de rotación de una rueda. Hecho normalmente de acero, está unido a 
 * la rueda o al eje. Para detener la rueda dispone de unas pastillas que son 
 * presionadas mecánica o hidráulicamente contra los laterales de los discos. 
 * La fricción entre el disco y las pastillas hace que la rueda se frene. Los 
 * frenos de disco son utilizados en automóviles, motocicletas y algunas bicicletas.
 * 
 * @see <a href="http://es.wikipedia.org/wiki/Freno_de_disco">Freno de Disco - Wikipedia</a>
 */
public class FrenoDisco extends Freno{
	
	//temperatura optima de un freno de Disco
	private static double TEMP_OPTIMA = 27; // °C

	/**
	 * Constructor de Freno a Disco sin parametros
	 * coloca el estado de la instancia al 100%
	 */
	public FrenoDisco(){
		setEstado(100);
		setNombre("Freno de Disco");
		setPrecio(new AlgoPesos(599,99)); //algo$
		setPeso(65); // Kg
	}
	
	/**
	 * Persistencia
	 * @param xmlElement
	 */
	public FrenoDisco(Element xmlElement){
		//levanto los valores
		this.EfectoClimatico =( Double.parseDouble(xmlElement.getAttribute("efectoclimatico")) );
		this.estado=( Double.parseDouble(xmlElement.getAttribute("estado")) );
		setNombre(xmlElement.getAttribute("nombre"));
	}
	
	public Element toXml(Document doc) {
		Element xmlElement = doc.createElement("freno");
		xmlElement.setAttribute("tipo","disco");
		xmlElement.setAttribute("estado", String.valueOf(this.getEstado()));
		xmlElement.setAttribute("efectoclimatico", String.valueOf(this.EfectoClimatico));
		xmlElement.setAttribute("nombre", this.getNombre());
		return xmlElement;
	}
	
	@Override
	/**
	 * El clima afecta a los frenos de Disco (temperatura)
	 */
	public void afectar(Clima clima) {
		EfectoClimatico = clima.getTemperatura()/TEMP_OPTIMA;
		
	}

	@Override
	public void desgastar() {
		setEstado(getEstado() - EfectoClimatico * 0.00082);
		
	}
	
	/* toString */
	
	public String toString(){
		return getNombre();
	}

	/**
	 * @Pre:-
	 * @Post: Se genera una lista con varias instancias de componentes de la misma 
	 * clase con atributos diferentes.
	 * @return
	 */
	public static LinkedList<Componente> createVariosComponentesDistintos(){
		LinkedList<Componente> lista=new LinkedList<Componente>();
		FrenoDisco freno=new FrenoDisco();
		lista.add(freno);
		return lista;
	}
	
}
