package modelo;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 * Escape es un componenta que
 * se ve afectado por la humedad y presion del clima
 * y el tipo de superficie
 * 
 * @version	1.0
 */
public class Escape extends Componente
implements AfectablePorClima, AfectablePorSuperficie{
	

	private double EfectoSuperficie;
	private double EfectoClimatico;
	
	//valor optimo de humedad
	private double HumedadOptima = 50;
	
	//valor optimo de presion
	private double PresionOptima = 1013;
	
	/**
	 * constructor, queda instanciada la clase 
	 * Escape
	 * 
	 */
	Escape(){
		HumedadOptima = 50;
		PresionOptima = 1013;
		setEstado(100);
	}
	
	/**
	 * costructor con parametros,
	 * queda instanciada la clase
	 * Escape
	 * @param HumedadOptima
	 * @param PresionOptima
	 */
	Escape(double HumedadOptima, double PresionOptima){
		this.HumedadOptima = HumedadOptima;
		this.PresionOptima = PresionOptima;
		setEstado(100);
	}
	/**
	 * El escape se  va desgastando 
	 * con el correr del tiempo.
	 * Se desgasta por el clima y 
	 * por la superficie
	 */
	
	public void desgastar(){
		//tener en cuenta Humedad, presion y superficie
		this.setEstado(getEstado() - EfectoClimatico - EfectoSuperficie/100 - 1/1000000000);
	}
	
	/**
	 * devuelve la potencia que
	 * se ve modificada por diversas situaciones
	 * 
	 */
	public double obtenerPotencia(){
		//tener en cuenta Humedad, presion y Estado
		return (HumedadOptima*PresionOptima*EfectoClimatico/100) * getEstado();
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
	
	
}