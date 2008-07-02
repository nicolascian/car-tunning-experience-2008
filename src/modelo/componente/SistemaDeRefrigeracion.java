package modelo.componente;
import java.util.LinkedList;

import modelo.AlgoPesos;
import modelo.Auto;
import modelo.Clima;
import modelo.Constantes;
import modelo.AfectablePorClima;

import org.w3c.dom.Element;
import org.w3c.dom.Document;


/**
 * @Descripcion:
 *  Una instancia de esta clase simula el sistema de refrigeracion del motor de un auto.
 *  Se encarga de bajar la temperatura del motor de a cuerdo a la temperatura del exterior.
 *  Una instancia de esta clase se enciende cuando el motor llega a una temperatura dada, y 
 *  al bajar la temperatura hasta una temperatura de corte la instancia se apaga, tal cual un
 *  termostato.
 *  Para dicho proceso una instancia de esta clase, toma potencia del motor y en caso de ser
 *  efectiva la obtencion baja la temperatura del mismo.
 * 
 */
public class SistemaDeRefrigeracion extends Componente implements AfectablePorClima {

	private double temperaturaEncendido=100;//temperatura (C)a la que se inicia el intercambio de temperatura
	
	private double temperaturaCorte=80;//temperatura (C)a la que se finaliza el intercambio de temperatura
	
	private double temperaturaPuntoFrio=25;//temperatura (C) el medio al que se cede la temperatura
	
	private boolean encendido=false;//indica si la instancia se encuentra intercambiando temperatura
	
	protected final static double COEFICIENTE_DE_DISMINUCION_DE_TEMPERATURA=0.03;
	
	protected final static double COEFICIENTE_DE_DESGASTE=2;
	/**
	 * @Pre:
	 * @Post:Se crea la istancia segun los parametros.
	 * @param auto
	 * @param temperaturaCorte
	 * @param temperaturaEncendido
	 */
	public SistemaDeRefrigeracion(Auto auto,double temperaturaCorte, 
			                      double temperaturaEncendido){
		setEstado(100);
		this.setTemperaturaCorte(temperaturaCorte);
		this.setTemperaturaEncendido(temperaturaEncendido);
		this.setPeso(15);
		this.setPrecio(new AlgoPesos(70,0));
	}
	
	/**
	 * @Pre:
	 * @Post:Se crea la istancia segun el parametro.
	*/
	public SistemaDeRefrigeracion(Auto auto){
		this.setAuto(auto);
		this.setPeso(15);
		setEstado(100);
		this.setPrecio(new AlgoPesos(70,0));
	}
	
	/**
	 * Persistencia
	 * @param xmlElement
	 */
	public SistemaDeRefrigeracion(Element xmlElement, Auto auto){
		//levanto los valores
		temperaturaEncendido=( Double.parseDouble(xmlElement.getAttribute("tempencendido")) );
		temperaturaCorte=( Double.parseDouble(xmlElement.getAttribute("tempcorte")) );
		temperaturaPuntoFrio=( Double.parseDouble(xmlElement.getAttribute("tempfrio")) );
		this.estado=( Double.parseDouble(xmlElement.getAttribute("estado")) );
		this.setAuto(auto);
		this.setPeso(15);
		this.setPrecio(new AlgoPesos(70,0));
	}
	
	public Element toXml(Document doc) {
		Element xmlElement = doc.createElement("sistemaderefrigeracion");
		xmlElement.setAttribute("estado", String.valueOf(this.getEstado()));
		xmlElement.setAttribute("tempencendido", String.valueOf(this.temperaturaEncendido));
		xmlElement.setAttribute("tempcorte", String.valueOf(this.temperaturaCorte));
		xmlElement.setAttribute("tempfrio", String.valueOf(this.temperaturaPuntoFrio));
		return xmlElement;
	}
	
	/**
	 * @Pre:La instancia ha sido creada y su atributo auto ha sido creado.
	 * @Post:Se verifica si la temperatura del motor debe ser bajada, y en ese caso se toma la potencia
	 * necesaria para ello.
	*/
	public void chequearTemperatura(){
	  try{
		Motor motor=getAuto().getMotor();
		if(!this.isEncendido()){
		  if(motor.getTemperatura()>=getTemperaturaEncendido())
		     setEncendido(true);	
		}  
		else{
			if(motor.getTemperatura()<=getTemperaturaCorte())
			  setEncendido(false);
			else{
				if(motor.tomarPotencia(2)==2)
				   if(getTemperaturaPuntoFrio()>15)
					 motor.setTemperatura(motor.getTemperatura()-
						getTemperaturaPuntoFrio()*COEFICIENTE_DE_DISMINUCION_DE_TEMPERATURA);
				   else
					 motor.setTemperatura(motor.getTemperatura()-1);  
			}
		}
	  }catch(NullPointerException e){}
	}
	
	@Override
	public void desgastar() {
		setEstado(getEstado()-Constantes.tiempoPorCiclo*COEFICIENTE_DE_DESGASTE);
	}
	
	@Override
	public double obtenerPotencia() {
    	return 0;
	}

	/**
	 * @return the temperaturaEncendido
	 */
	public double getTemperaturaEncendido() {
		return temperaturaEncendido;
	}

	/**
	 * @param temperaturaEncendido the temperaturaEncendido to set
	 */
	public void setTemperaturaEncendido(double temperaturaEncendido) {
		this.temperaturaEncendido = temperaturaEncendido;
	}

	/**
	 * @return the temperaturaCorte
	 */
	public double getTemperaturaCorte() {
		return temperaturaCorte;
	}

	/**
	 * @param temperaturaCorte the temperaturaCorte to set
	 */
	public void setTemperaturaCorte(double temperaturaCorte) {
		this.temperaturaCorte = temperaturaCorte;
	}

	/**
	 * @return the temperaturaPuntoFrio
	 */
	public double getTemperaturaPuntoFrio() {
		return temperaturaPuntoFrio;
	}

	/**
	 * @param temperaturaPuntoFrio the temperaturaPuntoFrio to set
	 */
	public void setTemperaturaPuntoFrio(double temperaturaPuntoFrio) {
		this.temperaturaPuntoFrio = temperaturaPuntoFrio;
	}

	/**
	 * @return the encendido
	 */
	public boolean isEncendido() {
		return encendido;
	}

	/**
	 * @param encendido the encendido to set
	 */
	public void setEncendido(boolean encendido) {
		this.encendido = encendido;
	}
	
	public void instalar(Auto auto){
		setAuto(auto);
    }

	/* (non-Javadoc)
	 * @see modelo.AfectablePorClima#afectar(modelo.Clima)
	 */
	
	public void afectar(Clima clima) {
	  try{	
		this.setTemperaturaPuntoFrio(clima.getTemperatura());
	  }catch(NullPointerException e){}	
	}
	/**
	 * @Pre:-
	 * @Post: Se genera una lista con varias instancias de componentes de la misma 
	 * clase con atributos diferentes.
	 * @return
	 */
	public static LinkedList<Componente> createVariosComponentesDistintos(){
		LinkedList<Componente> lista=new LinkedList<Componente>();
		
	    SistemaDeRefrigeracion sistema=new SistemaDeRefrigeracion(null,80.0,100.0);
		sistema.setPrecio(new AlgoPesos(70,00));
		lista.add(sistema);
		sistema=new SistemaDeRefrigeracion(null,75.0,90.0);
		sistema.setPrecio(new AlgoPesos(170,00));
		lista.add(sistema);
		sistema=new SistemaDeRefrigeracion(null,70.0,85.0);
		sistema.setPrecio(new AlgoPesos(250,00));
		lista.add(sistema);
		return lista;
	}
}
