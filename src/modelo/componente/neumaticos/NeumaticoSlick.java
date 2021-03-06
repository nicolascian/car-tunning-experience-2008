/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente.neumaticos;
import java.util.LinkedList;

import modelo.*;
import modelo.componente.Componente;

import org.w3c.dom.Element;
import org.w3c.dom.Document;

/**
 * El neumatico Slick es un neumatico liso (carece de dibujo en la
 * banda de rodadura). Tiene la mayor superficie posible en contacto
 * con la superficie, y maximiza el agarre en asfalto seco.
 * Su utilizacion es recomendable en pistas secas, ya que tienen poca
 * capacidad para evacuar agua, y planas, ya que el relieve y las 
 * particulas sueltas lo desgastan rapidamente.
 */
public class NeumaticoSlick extends Neumatico
	implements AfectablePorSuperficie, AfectablePorClima{
	
	/*----------Atributos---------*/
	
	/**
	 * La humedad en pista representa la cantidad de agua en la misma.
	 * Un valor igual a 0 corresponde a la pista seca.
	 * Un valor igual a 1 corresponde a la pista totalmente mojada.
	 */
	private double humedadEnPista;
	
	/**
	 * Este tipo de neumatico pierde eficacia cuando la temperatura externa
	 * es inferior a los 7�C.
	 * El efectoTemperaturaExterna toma un valor nulo si la temperatura es
	 * superior a los 7�C, y en caso de ser inferior toma un valor cercano
	 * a 1.
	 */
	private double efectoTemperaturaExterna;
	
	/**
	 * La viscosidad de la superficie toma un valor entre 0 y 1 e indica
	 * la oposicion del terreno a resbalar sobre el mismo.
	 */
	private double viscosidadSuperficie;
	
	/**
	 * El relieve en la superficie esta dado por la rugosidad y la
	 * cantidad de particulas sueltas en la misma.
	 * Puede tener un valor entre 0 y 2.
	 */
	private double relieveSuperficie;
	
	/*-----------Metodos----------*/
	
	public NeumaticoSlick(Auto auto){
		this.setNombre("Neumatico Slick");
		this.setPotenciaMax(5);
		this.setEfectoTemperaturaExterna(0);
		this.setEstado(100);
		this.setHumedadEnPista(0);
		this.setPeso(10);//en kilos
		this.setViscosidadSuperficie(0);
		this.setRelieveSuperficie(0);
		this.setAuto(auto);
		this.setPrecio(new AlgoPesos(300,0));
		this.setLlanta(null);
	}

	public NeumaticoSlick(){
		this.setNombre("Neumatico Slick");
		this.setPotenciaMax(5);
		this.setEfectoTemperaturaExterna(0);
		this.setEstado(100);
		this.setHumedadEnPista(0);
		this.setPeso(10);//en kilos
		this.setViscosidadSuperficie(0);
		this.setRelieveSuperficie(0);
		this.setPrecio(new AlgoPesos(300,0));
		this.setAuto(null);
		this.setLlanta(null);
	}
	
	/**
	 * Persistencia
	 * @param xmlElement
	 */
	public NeumaticoSlick(Element xmlElement, Auto auto){
		//levanto los valores
		efectoTemperaturaExterna=( Double.parseDouble(xmlElement.getAttribute("eftempexterna")) );
		humedadEnPista=( Double.parseDouble(xmlElement.getAttribute("humedadpista")) );
		setPotenciaMax(Double.parseDouble(xmlElement.getAttribute("potenciamax")));
		relieveSuperficie =( Double.parseDouble(xmlElement.getAttribute("relievesup")) );
		viscosidadSuperficie =( Double.parseDouble(xmlElement.getAttribute("viscosidadsup")) );
		this.estado=( Double.parseDouble(xmlElement.getAttribute("estado")) );
		this.setNombre("Neumatico Slick");
		this.setPeso(10);//en kilos
		this.setPrecio(new AlgoPesos(300,0));
		this.setAuto(auto);
		this.setLlanta(null);
	}
	
	public Element toXml(Document doc) {
		Element xmlElement = doc.createElement("neumatico");
		xmlElement.setAttribute("tipo", "slick");
		xmlElement.setAttribute("estado", String.valueOf(this.getEstado()));
		xmlElement.setAttribute("eftempexterna", String.valueOf(this.efectoTemperaturaExterna));
		xmlElement.setAttribute("humedadpista", String.valueOf(this.humedadEnPista));
		xmlElement.setAttribute("potenciamax", String.valueOf(this.getPotenciaMax()));
		xmlElement.setAttribute("relievesup", String.valueOf(this.relieveSuperficie));
		xmlElement.setAttribute("viscosidadsup", String.valueOf(this.viscosidadSuperficie));
		return xmlElement;
	}
	
	/**
	 * La adherencia se ve comprometida en un 40% por la cantidad de agua en la
	 * pista, en otro 40% por la visicosidad de la misma, en un 8% si la temperatura
	 * externa es menor a 7�C y en maximo del 2% por el relieve.
	 */
	public double calcularAdherencia(){
		double adherencia;
		adherencia = 1- (0.4 * this.getHumedadEnPista()) - (0.4* (1-this.getViscosidadSuperficie()))
					- (.01* this.getRelieveSuperficie()) - (.08*this.getEfectoTemperaturaExterna());
		return adherencia;
	}
	
	/**
	 * El desgaste esta dado basicamente por el relieve de la superficie, y un 
	 * minimo desgaste por el tiempo de uso.
	 */
	public void desgastar(){
		double desgaste;
		desgaste = (0.001 + 5 * this.getRelieveSuperficie()) * Constantes.tiempoPorCiclo;
		this.setEstado(this.getEstado()- desgaste);
	}
	
	/**
	 * Todos los parametros de la superficie afectan al neumatico.
	 * La viscosidad afectara la adherencia, mientras que la rugosisidad y las 
	 * particulas sueltas afectaran el desgaste y la potencia entregada.
	 */
	public void afectar(Superficie superficie){
		this.setViscosidadSuperficie(superficie.getViscosidad()/100);
		this.setRelieveSuperficie((superficie.getRugosidad()+superficie.getParticulasSueltas())/100);
	}

	/**
	 * Los efectos climaticos considerados sobre este tipo de neumatico son
	 * la cantidad de agua presente y la temperatura exterior.
	 * La temperatura tiene un efecto negativo cuando es menor a 7�C, y dicho
	 * efecto se agrava con la disminucion de la temperatura.
	 */
	public void afectar(Clima clima){
		this.setHumedadEnPista(clima.getHumedad()/100);
		if (clima.getTemperatura()<7){
			double aux = clima.getTemperatura()-100;
			aux = -.01*aux;
			this.setEfectoTemperaturaExterna(aux);
		}else this.setEfectoTemperaturaExterna(0);
	}
	
	/**
	 * La potencia entregada se ve afectada por el estado del neumatico y por
	 * el relieve en la superficie.
	 * En condiciones ideales, entrega una potencia igual a 5.
	 */
	public double obtenerPotencia(){
		return ((this.getEstado()/100) * (this.getPotenciaMax()-this.getRelieveSuperficie()));
	}
	
	public double getHumedadEnPista() {
		return humedadEnPista;
	}
	private void setHumedadEnPista(double humedadEnPista) {
		this.humedadEnPista = humedadEnPista;
	}
	public double getViscosidadSuperficie() {
		return viscosidadSuperficie;
	}
	private void setViscosidadSuperficie(double viscosidadSuperficie) {
		this.viscosidadSuperficie = viscosidadSuperficie;
	}
	public double getRelieveSuperficie() {
		return relieveSuperficie;
	}
	private void setRelieveSuperficie(double relieveSuperficie) {
		this.relieveSuperficie = relieveSuperficie;
	}

	public double getEfectoTemperaturaExterna() {
		return efectoTemperaturaExterna;
	}

	private void setEfectoTemperaturaExterna(double temperaturaExterna) {
		this.efectoTemperaturaExterna = temperaturaExterna;
	}
	/**
	 * @Pre:-
	 * @Post: Se genera una lista con varias instancias de componentes de la misma 
	 * clase con atributos diferentes.
	 * @return
	 */
	public static LinkedList<Componente> createVariosComponentesDistintos(){
		LinkedList<Componente> lista=new LinkedList<Componente>();
		lista.add(new NeumaticoSlick());
		return lista;
	}
}
