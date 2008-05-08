/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 * 
 * se ve afectado por la temperatura del clima
 * 
 * @version	1.0
 */
public class Motor extends Componente implements AfectablePorClima{
		
	private double cilindrada;
	
	private int cantidadCilindros;
	
	private double temperaturaCritica;
	
	private double temperaturaOptima;
		
	private double temperaturaExterna;

	//revoluciones maximas del motor
	private double revolucionesMaximas;
	
	//revoluciones maximas para un cambio dado
	private double revolucionesMaximasCambio;
		
	/**
	 * contador de las revoluciones del motor
	 */
	private double RPM;

	/**
	 * @Pre: 
	 * @Post: Se ha creado una instancia de la clase, inicializandola segun los parametros.
	 * @param cantidadCilindros 
	 * @param cilindrada
	 * @param revolucionesMaximas revoluciones maximas del motor
	 */
	Motor(int cantidadCilindros,double cilindrada, double revolucionesMaximas){
		setCantidadCilindros(cantidadCilindros);
		setCilindrada(cilindrada);
		setRevolucionesMaximas(revolucionesMaximas);
		setRevolucionesMaximasCambio(revolucionesMaximas);
		setTemperaturaCritica(500); //°C
		setTemperaturaOptima(120); //°C
		setTemperaturaExterna(0); //°C
	}
	
	/**
	 * pasarce de revoluciones es perjudicial
	 */
	public void desgastar(){
		/* pasarce de revolucionesMaximas es perjudicial */
		this.setEstado( Estado - (RPM/revolucionesMaximas)*10 - 
				        1/1000000000 - (Temperatura-getTemperaturaCritica())/getTemperaturaOptima() );
	}	
	
	public double obtenerPotencia(){
		
		return ( RPM + Estado + getCilindrada() + getCantidadCilindros() + 
				(Temperatura-getTemperaturaCritica())/getTemperaturaOptima() +
				 // Caja
				 auto.getCaja().obtenerPotencia() +
				 // Alimentacion.obtenerPotencias hace: Combustible.obtenerPotencia
				 auto.getAlimentacion().obtenerPotencia() ); 
	}
	
	/**
	 * cada vez que cambiamos las RPM, cambian la Temperatura del motor
	 * ademas le avisa a la Caja que hubo un cambio de revoluciones y en este
	 * caso le envia la variacion con repecto al cambio anterior
	 * 
	 * @param rpm
	*/
	public void setRPM(double rpm) {
		double anterior = RPM;
		
		RPM = rpm;
		
		auto.getCaja().Chequear(RPM - anterior);
		
		setTemperatura(RPM/6000 + getTemperaturaExterna() );
	}
	
	/** el clima afecta al motor */
	public void afectar(Clima clima){
		setTemperaturaExterna(clima.getTemperatura());
	}
	
	/* setters y getters */

	public double getRPM() {
		return RPM;
	}
	
	public double getRevolucionesMaximas() {
		return revolucionesMaximas;
	}
	
	public void setRevolucionesMaximas(double revoluciones) {
		this.revolucionesMaximas = revoluciones;
	}
	
	public double getRevolucionesMaximasCambio() {
		return revolucionesMaximasCambio;
	}
	
	public void setRevolucionesMaximasCambio(double revoluciones) {
		this.revolucionesMaximasCambio = revoluciones;
	}
		
	public void acelerar(){
		
	}

	/**
	 * @return the temperaturaCritica
	 */
	public double getTemperaturaCritica() {
		return temperaturaCritica;
	}

	/**
	 * @param temperaturaCritica the temperaturaCritica to set
	 */
	public void setTemperaturaCritica(double temperaturaCritica) {
		this.temperaturaCritica = temperaturaCritica;
	}

	/**
	 * @return the temperaturaOptima
	 */
	public double getTemperaturaOptima() {
		return temperaturaOptima;
	}

	/**
	 * @param temperaturaOptima the temperaturaOptima to set
	 */
	public void setTemperaturaOptima(double temperaturaOptima) {
		this.temperaturaOptima = temperaturaOptima;
	}

	/**
	 * @return the temperaturaExterna
	 */
	public double getTemperaturaExterna() {
		return temperaturaExterna;
	}

	/**
	 * @param temperaturaExterna the temperaturaExterna to set
	 */
	public void setTemperaturaExterna(double temperaturaExterna) {
		this.temperaturaExterna = temperaturaExterna;
	}

	/**
	 * @param cilindrada the cilindrada to set
	 */
	public void setCilindrada(double cilindrada) {
		this.cilindrada = cilindrada;
	}
	
	/**
	 * @return the cilindrada
	 */
	public double getCilindrada() {
		return cilindrada;
	}

	/**
	 * @return the cantidadCilindros
	 */
	public int getCantidadCilindros() {
		return cantidadCilindros;
	}

	/**
	 * @param cantidadCilindros the cantidadCilindros to set
	 */
	public void setCantidadCilindros(int cantidadCilindros) {
		this.cantidadCilindros = cantidadCilindros;
	}

}