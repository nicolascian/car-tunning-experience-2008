/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * @Documentacion: Esta clase modela el motor de un auto. El motor tiene una cierta potencia
 * maxima dada su cilindrada, cantidad de cilindros y revoluciones maximas que puede alcanzar.
 * El motor transfiere la mayor parte de su pontecia a la caja, por lo cual solo un 10% es
 * obtenible asumiendolo como componente.
 * El motor gira a una determinada cantidad de revoluciones que podra ir variando con los
 * metodos correspientes, o al pasar un cambio de la caja, la cual lo modificara en forma
 * adecuada.
 * 
 * se ve afectado por la temperatura del clima
 * 
 * @version	1.0
 */
public class Motor extends Componente implements AfectablePorClima{
		
	private double cilindrada;//en centimetros cúbicos
	
	private int cantidadCilindros;
	
	private double temperaturaCritica;//en ºC
	
	private double temperaturaOptima;//en ºC
		
	private double temperaturaExterna;//en ºC

	private double potenciaMaxima;//en hp
	
	//revoluciones maximas del motor
	private double revolucionesMaximas;//en rpm
	
	//revoluciones maximas para un cambio dado
	private double revolucionesMaximasCambio;//en rpm
		
	/**
	 * contador de las revoluciones del motor
	 */
	private double RPM;//en rpm

	/**
	 * @Pre: 
	 * @Post: Se ha creado una instancia de la clase, inicializandola segun los parametros.
	 * @param cantidadCilindros 
	 * @param cilindrada en centimetros cubicos
	 * @param revolucionesMaximas revoluciones maximas del motor en rpm
	*/
	Motor(int cantidadCilindros,double cilindrada, double revolucionesMaximas){
		setCantidadCilindros(cantidadCilindros);
		setCilindrada(cilindrada);
		setRevolucionesMaximas(revolucionesMaximas);
		setRevolucionesMaximasCambio(revolucionesMaximas);
		setTemperaturaCritica(500); //Â°C
		setTemperaturaOptima(120); //Â°C
		setTemperaturaExterna(0); //Â°C
		setPotenciaMaxima(120*getCilindrada()*getCantidadCilindros()/getRevolucionesMaximas());
	}
	
	/**
	 * pasarce de revoluciones es perjudicial
	*/
	public void desgastar(){
		/* pasarce de revolucionesMaximas es perjudicial */
		this.setEstado( getEstado() - (getRPM()/revolucionesMaximas)/10 - 1/1000000000 - 
				        (getTemperatura()-getTemperaturaCritica())/getTemperaturaOptima() );
	}	
	
	public double obtenerPotencia(){
		
		return ( RPM + getEstado() + getCilindrada() + getCantidadCilindros() + 
				(getTemperatura()-getTemperaturaCritica())/getTemperaturaOptima() +
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

	/**
	 * @return the potenciaMaxima
	 */
	public double getPotenciaMaxima() {
		return potenciaMaxima;
	}

	/**
	 * @param potenciaMaxima the potenciaMaxima to set
	 */
	public void setPotenciaMaxima(double potenciaMaxima) {
		this.potenciaMaxima = potenciaMaxima;
	}
	
}