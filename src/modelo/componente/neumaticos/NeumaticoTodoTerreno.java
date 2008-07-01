/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente.neumaticos;
import modelo.*;

import org.w3c.dom.Element;
import org.w3c.dom.Document;

/**
 * Los neumaticos todo terreno son utilizados en las superficies mas
 * exigentes, proporcionando una mayor adherencia y traccion en terrenos
 * con mucho relieve, pero el desgaste es mayor que en un neumatico comun
 * Al tener mayor dibujo y grandes tacos drenan mejor el agua.
 */
public class NeumaticoTodoTerreno extends Neumatico
	implements AfectablePorClima, AfectablePorSuperficie{
	
	/*----------Atributos---------*/
	
	/**
	 * La humedad en pista representa la cantidad de agua en la misma.
	 * Un valor igual a 0 corresponde a la pista seca.
	 * Un valor igual a 1 corresponde a la pista totalmente mojada.
	 */
	private double humedadEnPista;
	
	/**
	 * La rugosidad de la superficie no disminuira la adherencia pero si
	 * aumentara considerablemente el desgaste del neumatico.
	 * Toma valores entre 0 y 1.
	 */
	private double rugosidadSuperficie;
	
	/**
	 * Las particulas sueltas en la superficie son aquellos elementos de 
	 * volumen sueltos en la pista q aumentaran el da�o producido sobre 
	 * los neumaticos y disminuiran la adherencia aunque no de forma
	 * significativa.
	 * Toma valores entre 0 y 1.
	 */
	private double particulasEnSuperficie;
	
	/**
	 * La viscosidad de la superficie toma un valor entre 0 y 1 e indica
	 * la oposicion del terreno a resbalar sobre el mismo.
	 */
	private double viscosidadSuperficie;

	/*-----------Metodos----------*/
	
	public NeumaticoTodoTerreno(Auto auto){
		this.setNombre("Neumatico Todo Terreno");
		this.setPotenciaMax(3);
		this.setEstado(100);
		this.setPeso(12);//en kilos
		this.setHumedadEnPista(0);
		this.setParticulasEnSuperficie(0);
		this.setRugosidadSuperficie(0);
		this.setViscosidadSuperficie(0);
		this.setAuto(auto);
		this.setPrecio(new AlgoPesos(350,0));
		this.setLlanta(null);
	}

	public NeumaticoTodoTerreno(){
		this.setNombre("Neumatico Todo Terreno");
		this.setPotenciaMax(3);
		this.setEstado(100);
		this.setPeso(12);//en kilos
		this.setHumedadEnPista(0);
		this.setParticulasEnSuperficie(0);
		this.setRugosidadSuperficie(0);
		this.setViscosidadSuperficie(0);
		this.setPrecio(new AlgoPesos(350,0));
		this.setAuto(null);
		this.setLlanta(null);
	}
	
	/**
	 * Persistencia
	 * @param xmlElement
	 */
	public NeumaticoTodoTerreno(Element xmlElement, Auto auto){
		//levanto los valores
		particulasEnSuperficie=( Double.parseDouble(xmlElement.getAttribute("particulassup")) );
		humedadEnPista=( Double.parseDouble(xmlElement.getAttribute("humedadpista")) );
		setPotenciaMax(Double.parseDouble(xmlElement.getAttribute("potenciamax")));
		rugosidadSuperficie =( Double.parseDouble(xmlElement.getAttribute("rugosidadsup")) );
		viscosidadSuperficie =( Double.parseDouble(xmlElement.getAttribute("viscosidadsup")) );
		this.estado=( Double.parseDouble(xmlElement.getAttribute("estado")) );
		this.setNombre("Neumatico Todo Terreno");
		this.setPeso(12);//en kilos
		this.setPrecio(new AlgoPesos(350,0));
		this.setAuto(auto);
		this.setLlanta(null);
	}
	
	public Element toXml(Document doc) {
		Element xmlElement = doc.createElement("neumatico");
		xmlElement.setAttribute("tipo", "todoterreno");
		xmlElement.setAttribute("estado", String.valueOf(this.getEstado()));
		xmlElement.setAttribute("particulassup", String.valueOf(this.particulasEnSuperficie));
		xmlElement.setAttribute("humedadpista", String.valueOf(this.humedadEnPista));
		xmlElement.setAttribute("potenciamax", String.valueOf(this.getPotenciaMax()));
		xmlElement.setAttribute("rugosidadsup", String.valueOf(this.rugosidadSuperficie));
		xmlElement.setAttribute("viscosidadsup", String.valueOf(this.viscosidadSuperficie));
		return xmlElement;
	}
	
	/**
	 * El desgaste de este neumatico esta dado por la rugosidad de la superficie,
	 * por las particulas en la misma y por el simple uso.
	 */
	public void desgastar(){
		double desgaste = (1 + this.getParticulasEnSuperficie() + this.getRugosidadSuperficie())
							* Constantes.tiempoPorCiclo;
		this.setEstado(this.getEstado()- desgaste);
	}
	
	/**
	 * La potencia entregada se ve afectada por el estado del neumatico, por
	 * la cantidad de particulas en la superficie y por la rugosidad de la misma.
	 * En condiciones ideales, entrega una potencia igual a 3.
	 */
	public double obtenerPotencia(){
		return ((this.getEstado()/100)* (this.getPotenciaMax()-this.getParticulasEnSuperficie()
					- this.getRugosidadSuperficie()));
	}
	
	/**
	 * La adherencia se ve afectada por las particulas sueltas en la
	 * superficie en un 5%, por la cantidad de agua presente en un 20%
	 * , y en un 40% por el estado del neumatico.
	 */
	public double calcularAdherencia(){
		double adherencia;
		adherencia = 1 - (0.05 * this.getParticulasEnSuperficie()) 
					   - (0.2 * this.getHumedadEnPista())
					   - (0.4 * (this.getEstado()/100));
		return adherencia;
	}
	
	/**
	 * Todos los parametros de la superficie afectan al neumatico.
	 * La viscosidad y las particulas sueltas afectan la adherencia,
	 * mientras que el desgaste se ve afectado por las particulas
	 * sueltas y por la rugosisdad del terreno.
	 */
	public void afectar(Superficie superficie){
		this.setRugosidadSuperficie(superficie.getRugosidad()/100);
		this.setParticulasEnSuperficie(superficie.getParticulasSueltas()/100);
		this.setViscosidadSuperficie(superficie.getViscosidad()/100);
	}
	
	/**
	 * El unico efecto climatico sobre este tipo de neumatico es la cantidad
	 * de agua presente en la pista.
	 */
	public void afectar(Clima clima){
		this.setHumedadEnPista(clima.getHumedad()/100);
	}
	
	public double getHumedadEnPista() {
		return humedadEnPista;
	}

	private void setHumedadEnPista(double humedadEnPista) {
		this.humedadEnPista = humedadEnPista;
	}

	public double getRugosidadSuperficie() {
		return rugosidadSuperficie;
	}

	private void setRugosidadSuperficie(double rugosidadSuperficie) {
		this.rugosidadSuperficie = rugosidadSuperficie;
	}

	public double getParticulasEnSuperficie() {
		return particulasEnSuperficie;
	}

	private void setParticulasEnSuperficie(double particulasEnSuperficie) {
		this.particulasEnSuperficie = particulasEnSuperficie;
	}

	public double getViscosidadSuperficie() {
		return viscosidadSuperficie;
	}

	private void setViscosidadSuperficie(double viscosidadSuperficie) {
		this.viscosidadSuperficie = viscosidadSuperficie;
	}
}
