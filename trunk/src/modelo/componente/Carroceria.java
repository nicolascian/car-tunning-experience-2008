/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente;
import modelo.*;
import modelo.fuerzas.Fuerza;
import modelo.fuerzas.ReceptorDeFuerzas;

import org.w3c.dom.Element;
import org.w3c.dom.Document;

/**
 * Esta clase modela la carroceria de un auto. La carroceria se ve afectada por el clima
 * de modo tal que siempre se encuentra a temperatura ambiente, afectando el coeficiente de arrastre
 * siendo que a mayor temperatura menos denso el aire y menor coeficiente de arrastre, ademas la humedad
 * afecta el desgaste de la carroceria debido a que contribuye a la corrocion de la chapa.
 * La carroceria a medida que aumenta la velocidad retornara un valor de potencia mayor en modulo, pero
 * negativo, ya que resta potencia al vehiculo debido a la fuerza de arrastre.
 * 
 * @version	1.0
 */
public class Carroceria extends Componente 
	implements AfectablePorClima, AfectablePorSuperficie, ReceptorDeFuerzas{
		
	private double superficieFrontal;//en metros cuadrados
	
	private final static double TEMPERATURAOPTIMA=25;
	
	private final static double COEFICIENTE_ARRASTRE=0.38;
	
	private final static double COEFICIENTE_OBTENCION_FUERZA=6;
	
	private double coeficienteDeOxidacionPorParticulas;
	
	private double coeficienteDeOxidacionPorHumedad;

	private double temperatura;
	
	/**
	 * @Pre:
	 * @Post: Se ha creado una instancia de la clase carroceria, inicializandola segun los
	 * siguientes parametros.
	 * @param arrastre: coeficiente de arrastre, siempre positivo
	 * @param superficieFrontal: seccion delantera del auto que se vera afectada por la pared
	 * de aire que enfrente el auto al desplazarse. 
	*/
	public Carroceria(double superficieFrontal){
		setTemperatura(25);
		setEstado(100);
		setSuperficieFrontal(superficieFrontal);
		setCoeficienteDeOxidacionPorHumedad(0);
		setCoeficienteDeOxidacionPorParticulas(0);
		setPeso(905);
	}
	
	/**
	 * @Pre:
	 * @Post: Se ha creado una instancia de la clase carroceria, inicializandola segun los
	 * siguientes parametros.
	 * @param arrastre: coeficiente de arrastre, siempre positivo
	 * @param superficieFrontal: seccion delantera del auto que se vera afectada por la pared
	 * de aire que enfrente el auto al desplazarse. 
	*/
	public Carroceria(double superficieFrontal,double peso){
		setTemperatura(25);
		setEstado(100);
		setSuperficieFrontal(superficieFrontal);
		setCoeficienteDeOxidacionPorHumedad(0);
		setCoeficienteDeOxidacionPorParticulas(0);
		setPeso(peso);
	}
	
	/**
	 * Persistencia
	 * @param xmlElement
	 */
	public Carroceria(Element xmlElement){
		//levanto los valores
		superficieFrontal= ( Double.parseDouble(xmlElement.getAttribute("supfrontal")) );
		coeficienteDeOxidacionPorParticulas= ( Double.parseDouble(xmlElement.getAttribute("coefoxparticulas")) );
		coeficienteDeOxidacionPorHumedad =( Double.parseDouble(xmlElement.getAttribute("coefoxhumedad")) );
		temperatura =( Double.parseDouble(xmlElement.getAttribute("temperatura")) );
		setPeso(( Double.parseDouble(xmlElement.getAttribute("peso")) ));
		this.estado=( Double.parseDouble(xmlElement.getAttribute("estado")) );
	}
	
	public Element toXml(Document doc) {
		Element xmlElement = doc.createElement("carroceria");
		xmlElement.setAttribute("estado", String.valueOf(this.getEstado()));
		xmlElement.setAttribute("supfrontal", String.valueOf(this.superficieFrontal));
		xmlElement.setAttribute("coefoxparticulas", String.valueOf(this.coeficienteDeOxidacionPorParticulas));
		xmlElement.setAttribute("coefoxhumedad", String.valueOf(this.coeficienteDeOxidacionPorHumedad));
		xmlElement.setAttribute("temperatura", String.valueOf(this.temperatura));
		xmlElement.setAttribute("peso", String.valueOf(this.peso));
		return xmlElement;
	}
	
	/**
	 * @Post: La carroceria desgastada.
	 */
	public void desgastar(){
		double desgaste = (getCoeficienteDeOxidacionPorParticulas()+
				  getCoeficienteDeOxidacionPorHumedad())*Constantes.tiempoPorCiclo;
		setEstado(getEstado()-desgaste);
	}
	/**
	 * @Pre: La instancia de esta clase ha sido creada y la instancia de la clase Auto a la
	 * cual se refiere el atributo esta clase ha sido creada.
	 * @Post: Se ha obtenido la potencia en Hp de acuerdo a la velocidad y el clima.
	 * @Nota: La carroceria retorna una potencia negativa, y a mayor velocidad mayor sera
	 * esta potencia en modulo.Una mejor carroceria ser� aquella que tenga un arrastre menor
	 * y por lo tanto obteniendose una menor potencia de arrastre. 	 
	*/
	public double obtenerPotencia(){
		return 0;
	}
	
	/** el clima afecta a la carroceria
	 *@Post:Coeficiente modificado teniendo en cuenta la temperatura
	 *en ese momento en ese clima. 
	*/
	public void afectar(Clima clima){
		setTemperatura(clima.getTemperatura());
		setCoeficienteDeOxidacionPorHumedad(clima.getHumedad()* 0.01);
	}
	
	/** la superficie  afecta a la carroceria 
	 *@Post: Coeficiente modificado en relacion a la superficie
	 *que esta atravesando. 
	*/ 
	public void afectar(Superficie superficie){
		setCoeficienteDeOxidacionPorParticulas(superficie.getParticulasSueltas()*0.011);
	}
	
	/**
	 * @return the superficieFrontal
	 */
	public double getSuperficieFrontal() {
		return superficieFrontal;
	}

	/**
	 * @param superficieFrontal the superficieFrontal to set
	 */
	public void setSuperficieFrontal(double superficieFrontal) {
		this.superficieFrontal = superficieFrontal;
	}

	/**
	 * @return the coeficienteDeOxidacionPorParticulas
	 */
	public double getCoeficienteDeOxidacionPorParticulas() {
		return coeficienteDeOxidacionPorParticulas;
	}

	/**
	 * @param coeficienteDeOxidacionPorParticulas the coeficienteDeOxidacionPorParticulas to set
	 */
	public void setCoeficienteDeOxidacionPorParticulas(
			double coeficienteDeOxidacionPorParticulas) {
		this.coeficienteDeOxidacionPorParticulas = coeficienteDeOxidacionPorParticulas;
	}

	/**
	 * @return the coeficienteDeOxidacionPorHumedad
	 */
	public double getCoeficienteDeOxidacionPorHumedad() {
		return coeficienteDeOxidacionPorHumedad;
	}

	/**
	 * @param coeficienteDeOxidacionPorHumedad the coeficienteDeOxidacionPorHumedad to set
	 */
	public void setCoeficienteDeOxidacionPorHumedad(
			double coeficienteDeOxidacionPorHumedad) {
		this.coeficienteDeOxidacionPorHumedad = coeficienteDeOxidacionPorHumedad;
	}
	/**
	 * @return the temperatura
	 */
	public double getTemperatura() {
		return temperatura;
	}
	/**
	 * @param temperatura the temperatura to set
	 */
	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}
	
	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#liberarFuerzas()
	 */
	
	public void liberarFuerzas() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#recibirFuerza(modelo.Fuerza)
	 */
	
	public void recibirFuerza(Fuerza fuerza) {
		if((fuerza.getEmisor()==getAuto().getEjeDelantero()||
		   (fuerza.getEmisor()==getAuto().getEjeTrasero()))){
			double coeficiente=Math.abs(COEFICIENTE_ARRASTRE-TEMPERATURAOPTIMA/(getTemperatura()*10.0));
			double valor=getAuto().getVelocidad()*coeficiente*getSuperficieFrontal()*COEFICIENTE_OBTENCION_FUERZA/(-2);
			getAuto().getEjeDelantero().recibirFuerza(new Fuerza(this,getAuto().getEjeDelantero(),valor,false));
			getAuto().getEjeTrasero().recibirFuerza(new Fuerza(this,getAuto().getEjeDelantero(),valor,false));
		}
	}
}