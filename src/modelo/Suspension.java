package modelo;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 * la suspensión es un componente que absorbe 
 * las irregularidades del terreno por el que se circula
 * para aumentar la comodidad y el control del vehículo.
 * se ve afectada por la temperatura del clima y la superficie
 * 
 * @version	1.0
 */
public class Suspension extends Componente
implements AfectablePorSuperficie, AfectablePorClima{
	
	private double EfectoClimatico;
	private double EfectoSuperficie;
	
	private double rigidez;
	
	/**
	 * constructor, queda instanciada la clase Suspension
	 */
	public Suspension(){
		setEstado(100);
	}
	
	/**
	 * constructor con parametros
	 * queda instanciada la clase
	 * @param rigidez
	 */
	public Suspension(double rigidez){
		this.rigidez = rigidez;
		setEstado(100);
	}
	/**
	 * como es un componente, con el pasar del tiempo se
	 * va desgastando hasta quedar imposible de usar
	 */
	public void desgastar(){
//		tener en cuenta la temperatura del clima y superficie
		this.setEstado(getEstado() - EfectoClimatico - EfectoSuperficie/100 - 1/1000000000);
	}
	/**
	 * observo el estado de mi objeto
	 * mediante una cadena
	 */
	public String toString(){
		String cadena = this.getNombre()+", Estado: "+this.getEstado()+" %.";
		return cadena;
	}
	/**
	 * la suspension otorga una
	 * potencia que se ve afectada por 
	 * el clima y la superficie
	 */
	public double obtenerPotencia(){
//		tener en cuenta la temperatura del clima y superficie
		return (EfectoSuperficie*EfectoClimatico/100) * rigidez/10 * getEstado();
	}
	
	/** 
	 * La suspension se 
	 * ve afectada por el clima
	 */
	public void afectar(Clima clima){
		EfectoClimatico = clima.getTemperatura()/100;
	}
	
	/** 
	 * la suspension se 
	 * ve afectada por la superficie 
	 */
	public void afectar(Superficie superficie){

	}

	
	
}