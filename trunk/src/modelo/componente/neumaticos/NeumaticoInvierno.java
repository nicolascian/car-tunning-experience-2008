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
 * El neumatico para invierno es un tipo de neumatico que contiene clavos 
 * redondeados en su banda de rodadura. Esto le garantiza un maximo agarre
 * en cualquier condicion, pero su vida util es muy corta debido al rapido
 * desgaste de los clavos.
 */
public class NeumaticoInvierno extends Neumatico implements	AfectablePorSuperficie {

	/*----------Atributos---------*/
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
	
	/*-----------Metodos----------*/
	
	public NeumaticoInvierno(Auto auto){
		this.setNombre("Neumatico para invierno");
		this.setPotenciaMax(3);
		this.setEstado(100);
		this.setPeso(15);//en kilos
		this.setParticulasEnSuperficie(0);
		this.setRugosidadSuperficie(0);
		this.setAuto(auto);
		this.setPrecio(new AlgoPesos(400,0));
		this.setLlanta(null);
	}

	public NeumaticoInvierno(){
		this.setNombre("Neumatico para invierno");
		this.setPotenciaMax(3);
		this.setEstado(100);
		this.setPeso(15);//en kilos
		this.setParticulasEnSuperficie(0);
		this.setRugosidadSuperficie(0);
		this.setPrecio(new AlgoPesos(400,0));
		this.setAuto(null);
		this.setLlanta(null);
	}
	
	/**
	 * Persistencia
	 * @param xmlElement
	 */
	public NeumaticoInvierno(Element xmlElement, Auto auto){
		//levanto los valores
		setPotenciaMax(Double.parseDouble(xmlElement.getAttribute("potenciamax")));
		rugosidadSuperficie =( Double.parseDouble(xmlElement.getAttribute("rugosidadsup")) );
		particulasEnSuperficie =( Double.parseDouble(xmlElement.getAttribute("particulassup")) );
		this.estado=( Double.parseDouble(xmlElement.getAttribute("estado")) );
		this.setNombre("Neumatico para invierno");
		this.setPeso(15);//en kilos
		this.setPrecio(new AlgoPesos(400,0));
		this.setAuto(auto);
		this.setLlanta(null);
	}

	public Element toXml(Document doc) {
		Element xmlElement = doc.createElement("neumatico");
		xmlElement.setAttribute("tipo", "invierno");
		xmlElement.setAttribute("estado", String.valueOf(this.getEstado()));
		xmlElement.setAttribute("potenciamax", String.valueOf(this.getPotenciaMax()));
		xmlElement.setAttribute("rugosidadsup", String.valueOf(this.rugosidadSuperficie));
		xmlElement.setAttribute("particulassup", String.valueOf(this.particulasEnSuperficie));
		return xmlElement;
	}
	
	/**
	 * La adherencia solo se ve comprometida por el estado del neumatico.
	 * La relacion no es lineal, sino de una raiz cuadrada.
	 */
	public double calcularAdherencia() {
		return 1* (Math.sqrt(this.getEstado()/100));
	}

	/**
	 * El neumatico de invierno es el tipo de neumatico que mas rapido s desgasta
	 * post: el estado del neumatico queda reducido.
	 */
	public void desgastar() {
		double desgaste = (5 +5* this.getParticulasEnSuperficie() +5* this.getRugosidadSuperficie())
							* Constantes.tiempoPorCiclo;
		this.setEstado(this.getEstado()- desgaste);

	}

	/**
	 * La potencia entregada se ve afectada solo por el estado del neumatico.
	 */
	public double obtenerPotencia() {
		return (this.getPotenciaMax()* Math.sqrt(this.getEstado()/100));
	}

	/**
	 * Las particulas y la rugosidad de la superficie afectaran el desgaste
	 * del neumatico
	 * post: los atributos particulasEnSuperficie y rugosidadSuperficie quedan
	 * modificados.
	 */
	public void afectar(Superficie superficie) {
		this.setRugosidadSuperficie(superficie.getRugosidad()/100);
		this.setParticulasEnSuperficie(superficie.getParticulasSueltas()/100);

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


}
