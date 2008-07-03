/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Es el tipo de dato con el que vamos a trabajar.
 * 
 * @version	1.0
 */
public class AlgoPesos{
	
	// parte entera de AlgoPesos
	private int entero;
	// parte decimal de AlgoPesos, debe estar entre 0 y 99
	private int decimal;

	
	/** Constructor, queda instanciada la clase.
	 * 
	 * @param entero
	 * @param decimal
	 */
	public AlgoPesos(int entero, int decimal){
	 	if(decimal<100){
			setEntero(Math.abs(entero));
			setDecimal(decimal);
		}
		else{
		    Double auxiliar=new Double(Math.abs(decimal/100.0));
		    setEntero(Math.abs(entero)+auxiliar.intValue());
			setDecimal(Math.abs(decimal)-(100*(auxiliar.intValue())));
		}
		if((decimal<0)||(entero<0)){
			this.setDecimal(this.getDecimal()*(-1));
			this.setEntero(this.getEntero()*(-1));
		}	  	
	}
	
	/**
	 * Persistencia
	 * @param xmlElement
	 */
	public AlgoPesos(Element xmlElement){
		//levanto los atributos de Dinero
		this.entero= Integer.parseInt(xmlElement.getAttribute("entero"));
		this.decimal= Integer.parseInt(xmlElement.getAttribute("decimal"));
	}
	
	/** Suma una instancia de AlgoPesos a la instancia
	 * 
	*/
	public void sumar(AlgoPesos otro){
		AlgoPesos auxiliar=AlgoPesos.toAlgoPesos(this.toDouble()+otro.toDouble());
		this.setDecimal(auxiliar.getDecimal());
		this.setEntero(auxiliar.getEntero());
	}
	
	public void restar(AlgoPesos otro){
		AlgoPesos auxiliar=AlgoPesos.toAlgoPesos(this.toDouble()-otro.toDouble());
		this.setDecimal(auxiliar.getDecimal());
		this.setEntero(auxiliar.getEntero());
	}
		
	public static AlgoPesos sumar(AlgoPesos algo1,AlgoPesos algo2){
		return(toAlgoPesos(algo1.toDouble()+algo2.toDouble()));
	}
	
	public static AlgoPesos restar(AlgoPesos algo1,AlgoPesos algo2){
		return(toAlgoPesos(algo1.toDouble()-algo2.toDouble()));
	}
	
	public static AlgoPesos dividir(AlgoPesos algo1,AlgoPesos algo2){
	  try{	
		return(toAlgoPesos(algo1.toDouble()/algo2.toDouble()));
	  }catch(Exception e){
		return(toAlgoPesos(0.0));
	  }
	}
	
	public static AlgoPesos multiplicar(AlgoPesos algo1,AlgoPesos algo2){
		return(toAlgoPesos(algo1.toDouble()*algo2.toDouble()));
	}
	
	public static AlgoPesos obtenerXPorcentaje(AlgoPesos algo1,double porcentaje){
		try{	
			return(toAlgoPesos(algo1.toDouble()*porcentaje/100.0));
		}catch(Exception e){
			return(toAlgoPesos(0.0));
		}
	}
	
	public void multiplicar(double numero){
		AlgoPesos aux=AlgoPesos.multiplicar(this,AlgoPesos.toAlgoPesos(numero));
		this.setDecimal(aux.getDecimal());
		this.setEntero(aux.getEntero());
	}
	
	public void dividir(double numero){
		AlgoPesos aux=AlgoPesos.dividir(this,AlgoPesos.toAlgoPesos(numero));
		this.setDecimal(aux.getDecimal());
		this.setEntero(aux.getEntero());
	}
	
	public void obtenerXPorcentaje(double porcentaje){
		AlgoPesos aux=AlgoPesos.multiplicar(this,AlgoPesos.toAlgoPesos(porcentaje/100));
		this.setDecimal(aux.getDecimal());
		this.setEntero(aux.getEntero());
	}
		
	/**
	 * Asigna la parte Entera
	 * 
	 * @param entero the entero to set
	 */
	public void setEntero(int entero) {
		this.entero = entero;
	}

	/**
	 * nos devuelve la parte entera
	 * 
	 * @return the entero
	 */
	public int getEntero() {
		return entero;
	}

	/**
	 * Asigna la parte decimal 
	 * 
	 * @param decimal the decimal to set
	 */
	public void setDecimal(int decimal) {
		this.decimal = decimal;
	}

	/**
	 * nos devuelve la parte decimal
	 * 
	 * @return the decimal
	 */
	public int getDecimal() {
		return decimal;
	}
	
	
	/** 
	 * Persistencia
	 */
	public Element toXml(Document doc){
		Element xmlElement = doc.createElement("dinero");
		xmlElement.setAttribute("decimal", String.valueOf(getDecimal()));
		xmlElement.setAttribute("entero", String.valueOf(getEntero()));
		return xmlElement;
	}
	
	
	public String toString() {
		return getEntero() + "," + getDecimal();

	}
	
	public String toStringConUnidades(){
		return getEntero() + "," + getDecimal()+" AlgoPesos";
	}
		
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		
		return super.equals(obj);
	}

	public int compareTo(AlgoPesos algoPesos){
		double instancia=this.toDouble();
		double parametro=algoPesos.toDouble();
		if(instancia==parametro)
		  return(0);
		else
			if(instancia>parametro)
				return(1);
			else
				return(-1);
	}
			
	public double toDouble(){
		return(this.getEntero()+this.getDecimal()/100.0);
	}
	
	public static AlgoPesos toAlgoPesos(double numero){	
		Double auxiliar=new Double(numero);
		return new AlgoPesos(auxiliar.intValue(),(int)((numero-(float)auxiliar.intValue())*100));
	}
		
}