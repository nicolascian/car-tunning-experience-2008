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
		
    //constantes
	
	protected final static double TEMPERATURA_CRITICA=200;//en ºC
	
	protected final static double TEMPERATURA_OPTIMA=95;//en ºC
	
	protected final static double COEFICIENTE_TIEMPO_ACELERACION_CARACTERISTICO=0.25;
		
	protected final static double PORCENTAJE_RPM_ENCENDIDO=0.08;
	
	private double cilindrada;//en centimetros cúbicos
	
	private int cantidadCilindros;
				
	private boolean encendido;//indica si el motor se encuentra encendido
	
	protected boolean acelerando;//indica si el motor esta acelerendo
		
	private long horaDeEncendido;//en milisengundos
	
	protected long tiempoCaracteristicoAceleracion;//en milisegundos
	
	private long horaDeInicioFinAceleracion;//en milisegundos
	
	private long tiempoDeInicioFinCurvaAceleracion;//en milisegundos
	
	protected double temperaturaAire;//en ºC
	
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
	public Motor(int cantidadCilindros,double cilindrada, double revolucionesMaximas){
		setCantidadCilindros(cantidadCilindros);
		setCilindrada(cilindrada);
		setRevolucionesMaximas(revolucionesMaximas);
		setRevolucionesMaximasCambio(revolucionesMaximas);
		setRPM(0);
		setTemperaturaExterna(25); //Â°C
		setHoraDeEncendido(0);
		setEncendido(false);
		setAcelerando(false);
		setHoraDeInicioFinAceleracion(0);
		setTiempoCaracteristicoAceleracion(Math.round(getRevolucionesMaximas()*COEFICIENTE_TIEMPO_ACELERACION_CARACTERISTICO));
	}
	
	public void encender(){
		if(!isEncendido()){
			setEncendido(true);
			setHoraDeEncendido(System.currentTimeMillis());
			setRPM(getRevolucionesMaximas()*PORCENTAJE_RPM_ENCENDIDO);
			setAcelerando(false);
		}
	}
		
	public void apagar(){
		if(isEncendido()){
			setEncendido(false);
			setHoraDeEncendido(0);
			setRPM(0);
			setAcelerando(false);
		}
	}
	
	private void actualizarRpm(){
	  if(RPM<getRevolucionesMaximas()){
		 double rpm;
	     if(isAcelerando()){	
		     rpm=getRevolucionesMaximas()*Math.sin(getTiempoDeInicioFinCurvaAceleracion()*Math.PI/
		    	 (2*getTiempoCaracteristicoAceleracion()));
	  
	     }else{
	    	 rpm=getRevolucionesMaximas()*Math.cos(getTiempoDeInicioFinCurvaAceleracion()*Math.PI/
			    	 (2*getTiempoCaracteristicoAceleracion()));
         }
	     setRPM(rpm);
	  }
	}
	
	/**
	 *  
	*/
	public void acelerar(){
		if(isEncendido())
		  if(!isAcelerando()){
			setAcelerando(true);
			this.setHoraDeInicioFinAceleracion(System.currentTimeMillis());
			setTiempoDeInicioFinCurvaAceleracion(System.currentTimeMillis()-getHoraDeInicioFinAceleracion()
					  +Math.round(getTiempoCaracteristicoAceleracion()*Math.asin(getRevolucionesMaximas()/RPM)/(Math.PI/2)));
			actualizarRpm();
		  }
	}
	
	public void desacelerar(){
		if(isEncendido())
	       if(isAcelerando()){
		      setAcelerando(false);
		      this.setHoraDeInicioFinAceleracion(System.currentTimeMillis());
		      setTiempoDeInicioFinCurvaAceleracion(System.currentTimeMillis()-getHoraDeInicioFinAceleracion()
						  +Math.round(getTiempoCaracteristicoAceleracion()*Math.acos(getRevolucionesMaximas()/RPM)/(Math.PI/2)));
			  actualizarRpm();
		   }
	}
	
	/**
	 * pasarce de revoluciones es perjudicial
	*/
	public void desgastar(){
	
	}	
	
	public double obtenerPotencia(){
		actualizarRpm();
		return (calcularPotenciaInterna()*0.05); 
	}
	
	/**
	 * cada vez que cambiamos las RPM, cambian la Temperatura del motor
	 * ademas le avisa a la Caja que hubo un cambio de revoluciones y en este
	 * caso le envia la variacion con repecto al cambio anterior
	 * 
	 * @param rpm
	*/
	public void setRPM(double rpm) {
		this.RPM=rpm;
	}
	
	/** el clima afecta al motor */
	public void afectar(Clima clima){
		setTemperaturaAire(clima.getTemperatura());
	}
		
	public double getRPM() {
		//actualizacion de RPM en caso de estar acelerando o desacelerendo
		actualizarRpm();
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
			
	/**
	 * @return the temperaturaAire
	 */
	public double getTemperaturaAire() {
		return temperaturaAire;
	}

	/**
	 * @param temperaturaExterna the temperaturaAire to set
	 */
	public void setTemperaturaAire(double temperatura) {
		this.temperaturaAire = temperatura;
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
	 * @Pre: La instancia ha sido creada.
	 * @Pos: Se ha obtenido el calculo de la potencia interna del motor retornando en Hp.
	 * @return potencia interna del motor en Hp.
	*/
	public double calcularPotenciaInterna(){
		double potenciaInterna=0;
		if(getRPM()>0){
			//potencia sin ser afectada por elementos
			potenciaInterna=120*getCilindrada()*getCantidadCilindros()/getRPM();
			//calculo de potencia afectada por elementos
			
		}
		return potenciaInterna;
	}

	/**
	 * @return the horaDeEncendido
	 */
	protected long getHoraDeEncendido() {
		return horaDeEncendido;
	}

	/**
	 * @param horaDeEncendido the horaDeEncendido to set
	 */
	protected void setHoraDeEncendido(long horaDeEncendido) {
		this.horaDeEncendido = horaDeEncendido;
	}

	/**
	 * @return the encendido
	 */
	protected boolean isEncendido() {
		return encendido;
	}

	/**
	 * @param encendido the encendido to set
	 */
	protected void setEncendido(boolean encendido) {
		this.encendido = encendido;
	}

	/**
	 * @return the acelerando
	 */
	protected boolean isAcelerando() {
		return acelerando;
	}

	/**
	 * @param acelerando the acelerando to set
	 */
	protected void setAcelerando(boolean acelerando) {
		this.acelerando = acelerando;
	}

	/**
	 * @return the horaDeInicioFinAceleracion
	 */
	protected long getHoraDeInicioFinAceleracion() {
		return horaDeInicioFinAceleracion;
	}

	/**
	 * @param horaDeInicioFinAceleracion the horaDeInicioFinAceleracion to set
	 */
	protected void setHoraDeInicioFinAceleracion(long horaDeInicioFinAceleracion) {
		this.horaDeInicioFinAceleracion = horaDeInicioFinAceleracion;
	}

	/**
	 * @return the tiempoCaracteristicoAceleracion
	 */
	protected long getTiempoCaracteristicoAceleracion() {
		return tiempoCaracteristicoAceleracion;
	}

	/**
	 * @param tiempoCaracteristicoAceleracion the tiempoCaracteristicoAceleracion to set
	 */
	protected void setTiempoCaracteristicoAceleracion(
			long tiempoCaracteristicoAceleracion) {
		this.tiempoCaracteristicoAceleracion = tiempoCaracteristicoAceleracion;
	}

	/**
	 * @return the tiempoDeInicioFinCurvaAceleracion
	 */
	protected long getTiempoDeInicioFinCurvaAceleracion() {
		return tiempoDeInicioFinCurvaAceleracion;
	}

	/**
	 * @param tiempoDeInicioFinCurvaAceleracion the tiempoDeInicioFinCurvaAceleracion to set
	 */
	protected void setTiempoDeInicioFinCurvaAceleracion(
			long tiempoDeInicioFinCurvaAceleracion) {
		this.tiempoDeInicioFinCurvaAceleracion = tiempoDeInicioFinCurvaAceleracion;
	}
}