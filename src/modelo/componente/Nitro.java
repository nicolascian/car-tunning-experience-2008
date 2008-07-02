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
 * Durante el proceso de la combustión en un encendido, a unos 300 grados Celsios 
 * el Óxido Nitroso simplemente se descompone en Nitrógeno y Oxígeno.
 *
 * Es el Oxígeno extra el que crea poder adicional al permitir que más combustible 
 * sea quemado. El poder siempre viene del combustible, tener esto en cuenta!
|*
|* Si Usted agrega solamente Óxido Nitroso y no agrega combustible no conseguirá 
|* potencia extra. Puede aumentar el rango de velocidad de su vehículo quemando 
 * combustible, eso conduce a detonaciones destructivas del sistema de combustión 
 * del automóvil. 
 *
 * @version 1.0
 * @see <a href="http://tuning.deautomoviles.com.ar/articulos/oxido_nitroso.html">Oxido Nitroso</a> 
 */
public class Nitro extends Componente {

	private boolean activado= false;
	
	/**
	 * Construcctor de Nitro sin parametros
	 * establece el estado  en 100%
	 */
	public Nitro(){
		setEstado(100);
		setNombre("Equipo de Oxido Nitroso");
		setPrecio(new AlgoPesos(3200,00)); //algo$
		setPeso(10); // Kg
	}
	
	/**
	 * Constructor de Nitro con parametro
	 * 
	 * @param estado
	 */
	public Nitro(double estado){
		setEstado(estado);
		setNombre("Equipo de Oxido Nitroso");
		setPrecio(new AlgoPesos(3200,00)); //algo$
		setPeso(10); // Kg
	}
	
	/**
	 * Persistencia
	 * @param xmlElement
	 */
	public Nitro(Element xmlElement){
		//levanto los valores
		this.estado=( Double.parseDouble(xmlElement.getAttribute("estado")) );
		setNombre(xmlElement.getAttribute("nombre"));
		setPrecio(new AlgoPesos(3200,00)); //algo$
		setPeso(10); // Kg
	}
	
	public Element toXml(Document doc) {
		Element xmlElement = doc.createElement("nitro");
		xmlElement.setAttribute("estado", String.valueOf(this.getEstado()));
		xmlElement.setAttribute("nombre", String.valueOf(this.getNombre()));
		return xmlElement;
	}

	
	/**
	 * Permite poner al Nitro en estado Activo / Desactivo
	 * 
	 * @param valor
	 */
	public void activar(boolean valor){
		activado = valor;
	}
	
	/**
	 * Nos dice si el Nitro se encuentra en estado Activado o no.
	 * 
	 * @return
	 */
	public boolean isActivado(){
		return activado;
	}
	
	@Override
	public void desgastar() {
		setEstado(getEstado() - 0.5);
		
	}

	@Override
	/**
	 * Obtener Potencia nos da una gran cantidad si esta activado
	 * y cero si no lo esta.
	 */
	public double obtenerPotencia() {
		if(isActivado() && getEstado()>0){
			desgastar();
			return 250;
		}else{
			return 0;
		}
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
		for(int cursor=50;cursor<=100;cursor+=10){
		    Nitro nitro=new Nitro(cursor);
		    nitro.setPrecio(new AlgoPesos(2000+cursor*20,0));
		    lista.add(nitro);
		}
		return lista;
	}
	
}
