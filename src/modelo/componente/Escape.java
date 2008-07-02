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
 * Escape es un componenta que
 * se ve afectado por la humedad y presion del clima
 * y el tipo de superficie
 * 
 * @version	1.0
 */
public class Escape extends Componente
implements AfectablePorClima, AfectablePorSuperficie{
	

	private double EfectoSuperficie=1;
	private double EfectoClimatico=1;
	
	//valor optimo de humedad
	private double HumedadOptima = 50;
	
	//valor optimo de presion
	private double PresionOptima = 1013;
	
	/**
	 * constructor, queda instanciada la clase 
	 * Escape
	 * 
	 */
	public Escape(){
		setPeso(20);
		HumedadOptima = 50;
		PresionOptima = 1013;
		this.setEstado(100);
		setPrecio(new AlgoPesos(60,0)); //algo$
		setNombre("Escape generico");
	}
	
	/**
	 * costructor con parametros,
	 * queda instanciada la clase
	 * Escape
	 * @param HumedadOptima
	 * @param PresionOptima
	 */
	public Escape(double HumedadOptima, double PresionOptima){
		setPeso(20);
		this.HumedadOptima = HumedadOptima;
		this.PresionOptima = PresionOptima;
		this.setEstado(100);
		setPrecio(new AlgoPesos(60,0)); //algo$
		setNombre("Escape Modificado");
	}
	
	/**
	 * Persistencia
	 * @param xmlElement
	 */
	public Escape(Element xmlElement){
		//levanto los valores
		setNombre(( xmlElement.getAttribute("nombre") ));
		EfectoSuperficie= ( Double.parseDouble(xmlElement.getAttribute("efectosuperficie")) );
		EfectoClimatico= ( Double.parseDouble(xmlElement.getAttribute("efectoclimatico")) );
		HumedadOptima = ( Double.parseDouble(xmlElement.getAttribute("humedadoptima")) );
		PresionOptima =( Double.parseDouble(xmlElement.getAttribute("presionoptima")) ); 
		this.estado=( Double.parseDouble(xmlElement.getAttribute("estado")) );
		setPrecio(new AlgoPesos(60,0)); //algo$
		setPeso(20);
	}
	
	public Element toXml(Document doc) {
		Element xmlElement = doc.createElement("escape");
		xmlElement.setAttribute("estado", String.valueOf(this.getEstado()));
		xmlElement.setAttribute("nombre", String.valueOf(this.getNombre()));
		xmlElement.setAttribute("efectosuperficie", String.valueOf(this.EfectoSuperficie));
		xmlElement.setAttribute("efectoclimatico", String.valueOf(this.EfectoClimatico));
		xmlElement.setAttribute("humedadoptima", String.valueOf(this.HumedadOptima));
		xmlElement.setAttribute("presionoptima", String.valueOf(this.PresionOptima));
		return xmlElement;
	}
	
	/**
	 * El escape se  va desgastando 
	 * con el correr del tiempo.
	 * Se desgasta por el clima y 
	 * por la superficie
	 */
	public void desgastar(){
		//tener en cuenta Humedad, presion y superficie
		this.setEstado(getEstado() - (EfectoClimatico - EfectoSuperficie/100 - 1/1000000000)*Constantes.tiempoPorCiclo);
	}
	
	/**
	 * devuelve la potencia que
	 * se ve modificada por diversas situaciones
	 * 
	 */
	public double obtenerPotencia(){
		//tener en cuenta Humedad, presion y Estado
		return (HumedadOptima*PresionOptima*EfectoClimatico/1000000) * getEstado() -2;
	}
	
	/**
	 * El escape se afectado por el clima
	 */
	public void afectar(Clima clima){
		EfectoClimatico = (clima.getHumedad()/HumedadOptima) + (clima.getPresion()/PresionOptima);
	}
	
	/** 
	 * El escape se ve afectado por la superficie
	 */
	public void afectar(Superficie superficie){

	}

	/**
	 * observo el estado de mi objeto
	 * mediante una cadena
	 */
	public String toString(){
		String cadena = this.getNombre()+", Estado: "+this.getEstado()+" %.";
		return cadena;
	}
	
	/* setters y getters */
	
	public void setHumedadOptima(double humedadOptima) {
		HumedadOptima = humedadOptima;
	}

	public void setPresionOptima(double presionOptima) {
		PresionOptima = presionOptima;
	}
	
	/**
	 * @Pre:-
	 * @Post: Se genera una lista con varias instancias de componentes de la misma 
	 * clase con atributos diferentes.
	 * @return
	 */
	public static LinkedList<Componente> createVariosComponentesDistintos(){
		LinkedList<Componente> lista=new LinkedList<Componente>();
		Escape escape;
		for(int cursor=0;cursor<=5;cursor++){
			escape=new Escape(50+cursor,1013+cursor);
			lista.add(escape);
		}				
		return lista;
	}
}