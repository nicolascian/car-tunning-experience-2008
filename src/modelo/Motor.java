/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

/**
 * Esta clase modela el motor de un auto. El motor tiene una cierta potencia
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

public class Motor extends Componente implements AfectablePorClima, ReceptorDeFuerzas{
		
    //----------------------      constantes   ---------------------------------
	
	protected final static double TEMPERATURA_CRITICA=200;//temperatura critica del circuito de agua en �C
	
	protected final static double TEMPERATURA_OPTIMA=95;//temperatura optima del circuito de agua en �C
	
	protected final static double TEMPERATURA_INICIAL=80;//temperatura inicial del circuito de agua en �C
	
	protected final static double TEMPERATURA_MEDIANA_AIRE=15;//temperatura del aire la cual si se sobrepasa se 
												//se disminuye la potencia del motor, y si se opera por debajo
												//de esa temperatura aumenta el rendimiento.
		
	protected final static double COEFICIENTE_RPM_ENCENDIDO=0.08;// 8%
	
	protected final static double COEFICIENTE_PRODUCCION_FUERZA_POTENCIA=0.1;

	protected final static double COEFICIENTE_DE_ABSORCION_CALORICO_INICIAL=0.0015;
	
	protected final static double COEFICIENTE_DE_DISIPACION_CALORICO_INICIAL=0.003;
	
	protected final static double COEFICIENTE_DE_DESGASTE_POR_TEMPERATURA=1.5;
	
	protected final static double COEFICIENTE_DE_DESGASTE_POR_EXCESO_DE_REVOLUCIONES=2;
	
	protected final static double COEFICIENTE_BASICO_DE_DESGASTE=1.5;
	
	protected final static double COEFICIENTE_ACTUALIZACION_DE_CTE_DE_PROD_FZA_A_PATIR_RPM=0.1;
	
	//---------------------     atributos basicos de motor   -------------------
	
	private double cilindrada;//en centimetros c�bicos
	
	private int cantidadCilindros;
	
	private double revolucionesMaximas;//revoluciones maximas que el motor puede alcanzar segun su cilindrada 
	                                   //y cantidad de cilindros en rpm.
			
	private double RPM;//revoluciones a las que se encuentra trabajando el motor, en rpm.
	
	private boolean encendido;//indica si el motor se encuentra encendido
	
	private boolean acelerando;//indica si el motor esta acelerendo
		
	private double temperatura;//temperatura en �C del motor
	
	private double potenciaMaxima=0;
	
	private double potenciaExtra=0;
	
	//----------------------  atributos secundarios    ----------------------------
	
	private double temperaturaAire=25;//temperatura del aire que ingresa al motor en �C
	
	private double revolucionesMinimasEncendido;//revoluciones minimas que alcanza el auto mientras
												//se haya encendido.
	private double coeficienteDeAbsorcionCalorico;
		
	private double coeficienteDeDisipacionCalorico;
	
	private double coeficienteDeIncrementoRpm;
	
	private double coeficienteDeProduccionDeFuerzaAPartirRpm;
		
	/**
	 * @Pre: -
	 * @Post: Se ha creado una instancia de la clase, inicializandola segun los parametros.
	 * @param cantidadCilindros 
	 * @param cilindrada en centimetros cubicos
	 * @param revolucionesMaximas revoluciones maximas del motor en rpm
	*/
	public Motor(int cantidadCilindros,double cilindrada, double revolucionesMaximas){
	 //inicializacion de cilindara y cilindros
		setCantidadCilindros(cantidadCilindros);
		setCilindrada(cilindrada);
	 //inicializacion de revoluciones
		setRevolucionesMaximas(revolucionesMaximas);
		setRevolucionesMinimasEncendido(getRevolucionesMaximas()*COEFICIENTE_RPM_ENCENDIDO);
	//inicializacion de temperatura
		setTemperatura(TEMPERATURA_INICIAL);
	 //inicilizacion de coeficientes
		setCoeficienteDeAbsorcionCalorico(COEFICIENTE_DE_ABSORCION_CALORICO_INICIAL);
		setCoeficienteDeDisipacionCalorico(COEFICIENTE_DE_DISIPACION_CALORICO_INICIAL);
	 //inicializacion de atributos booleanos
		setEncendido(false);
		setAcelerando(false);
	//auto y estado
		setAuto(null);
		setEstado(100);
	//inicializacion de potencias 	
		potenciaMaxima=getCilindrada()*getCantidadCilindros()*getRevolucionesMaximas()/640000;
		potenciaExtra=0;
	}
		
	/**
	 * @Pre: -
	 * @Post: Se ha creado una instancia de la clase, inicializandola segun los parametros
	 * siguientes valores por defecto:
	 *	4 cilindros
	 *	1600 centimetros cubicos
	 *  8000 rpm como maximo
	*/
	public Motor(){
		setCantidadCilindros(4);
		setCilindrada(1600);
		//inicializacion de revoluciones
		setRevolucionesMaximas(8000);
		RPM=0;
		//inicializacion de temperaturas
		setTemperaturaAire(25); //°C
		setTemperatura(TEMPERATURA_INICIAL);
		//inicilizacion de coeficientes
		setCoeficienteDeAbsorcionCalorico(COEFICIENTE_DE_ABSORCION_CALORICO_INICIAL);
		setCoeficienteDeDisipacionCalorico(COEFICIENTE_DE_DISIPACION_CALORICO_INICIAL);
		encendido=false;
		acelerando=false;
		setAuto(null);
		setEstado(100);
		setRevolucionesMinimasEncendido(getRevolucionesMaximas()*COEFICIENTE_RPM_ENCENDIDO);
		
	}
	
	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#liberarFuerzas()
	 */
	@Override
	public void liberarFuerzas() {}

	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#recibirFuerza(modelo.Fuerza)
	 */
	@Override
	public void recibirFuerza(Fuerza fuerza) {
		if(fuerza!=null){
			if(fuerza.getEmisor()==this.getAuto().getEjeDeTransmision()){
			   afectarRpmPorFuerza(fuerza);
			   if(isAcelerando())
				   incrementarRpm();
			   else
				   decrementarRpm();
			}//fin fuerza desde eje
		}//fin fuerza no nula
	}
	
	private void afectarRpmPorFuerza(Fuerza fuerza){
		   double rpm=0;
		   try{
			   rpm=RPM+fuerza.getValorDeLaFuerza()/coeficienteDeProduccionDeFuerzaAPartirRpm;
		   }catch (Exception e){}
		   if(rpm<getRevolucionesMinimasEncendido())
			  rpm=getRevolucionesMinimasEncendido();
		   if(rpm>getRevolucionesMaximas())
			  rpm=getRevolucionesMaximas();
		   setRPM(rpm);
	}
	
	/**
	 *  @Pre: La instancia ha sido creada y se encuentra en estado encendido.
	 *  @Post: Se ha seteado la instancia como acelerando.    
	*/
	public void acelerar(boolean valor){
	  if(isEncendido()){
		  setAcelerando(valor);
		  /*Envio una fuerza al eje proporcional a las rpm y 
		    al coeficienteDeProduccionDeFuerzaAPartirDeRpm*/
		  Fuerza fuerza=new Fuerza(this,this.getAuto().getEjeDeTransmision(),getRPM()*
			                       this.coeficienteDeProduccionDeFuerzaAPartirRpm,true);
		  this.getAuto().getEjeDeTransmision().recibirFuerza(fuerza);
	  }//fin encendido	
	}
	
	/**
	 *  @Pre: La instancia ha sido creada y se encuentra en estado encendido.
	 *  @Post: Se han decrementado las rpm de acuerdo al estado actual del coeficienteDeIncrementoRpm 
	 *  de la instancia.    
	*/
	private void decrementarRpm(){
		double rpmFinal=getRPM()-this.coeficienteDeIncrementoRpm;
		if(rpmFinal>this.getRevolucionesMinimasEncendido()){
		  setRPM(rpmFinal);
		  this.setCoeficienteDeIncrementoRpm(coeficienteDeIncrementoRpm-
			                                2*Math.sqrt(coeficienteDeIncrementoRpm));
		}
		else
		  this.setRPM(this.getRevolucionesMinimasEncendido());
	}
	
	/**
	 *  @Pre: La instancia ha sido creada y se encuentra en estado encendido.
	 *  @Post: Se han incrementado las rpm de acuerdo al estado actual del coeficienteDeIncrementoRpm 
	 *  de la instancia.    
	*/
	private void incrementarRpm(){
		double rpmFinal=getRPM()+this.coeficienteDeIncrementoRpm;
		if(rpmFinal<this.getRevolucionesMaximas()){
		  setRPM(rpmFinal);
		  this.setCoeficienteDeIncrementoRpm(coeficienteDeIncrementoRpm+
			                                2*Math.sqrt(coeficienteDeIncrementoRpm));
		}
		else
		  this.setRPM(this.getRevolucionesMaximas());
	}
	
	/**
	 *  @Pre: La instancia ha sido creada y se encuentra en estado encendido.
	 *  @Post: Se ha actualizado el coeficienteDeProduccionDeFuerzaAPartirRpm de la instancia a partir
	 *  de la potencia maxima del motor, y de la potencia extra.    
	*/
	private void actualizarCoeficienteDeProduccionDeFuerzaAPartirRpm(){
		setCoeficienteDeProduccionDeFuerzaAPartirRpm((getPotenciaMaxima()+getPotenciaExtra())*
				                      COEFICIENTE_ACTUALIZACION_DE_CTE_DE_PROD_FZA_A_PATIR_RPM);
	}
	
	/**
	 *	@Pre: La instancia ha sido creada.
	 *  @Post: Si la insntancia no se encontraba en estado encendido y ademas estaba lista para la carrera
	 *  se ha encendido, de manera tal que se setean las las rpm en a un porcentaje de las rpm maximas del
	 *  motor, tal como si estubiese regulando.    
	*/
	public void encender(){
		if((!isEncendido())&&(getAuto()!=null)){
			setEncendido(true);
		//seteo de temperaturas
			setTemperatura(TEMPERATURA_INICIAL);
		//seteo de revoluciones
			setRevolucionesMinimasEncendido(getRevolucionesMaximas()*COEFICIENTE_RPM_ENCENDIDO);
			
			setRPM(getRevolucionesMinimasEncendido());
			getAuto().getCaja().setCambio(0);
			setAcelerando(false);
		}
	}
	
	/**
	 *	@Pre: La instancia ha sido creada.
	 *  @Post: Si la insntacia se encontraba en estado encendido se colca no encendido, quedando inhabilitada
	 *  para acelerar.    
	*/
	public void apagar(){
		if(isEncendido()){
			getAuto().getCaja().setCambio(0);
			setEncendido(false);
			setRPM(0);
			setTemperatura(25);
			setAcelerando(false);
			this.liberarFuerzas();
		}
	}
	
	
	/**
	 *	@Pre: La instancia ha sido creada y se encuentra en estado encendido.
	 *  @Post: Se actualiza la temperatura de acuerdo a la variaci�n de las revoluciones.    
	*/
	private void actualizarTemperaturaPorCambioDeRpm(double rpmInicial,double rpmFinal,long diferenciaDeTiempo){
		
	}
			
	/**
	 * @Pre: La instancia ha sido creada y se encuentra en estado encendido.
	 * @Post: Se ha desgastado la instancia segun las revoluciones maximas soportadas para el cambio
	 * actual y segun la temperatura a la que se encuentra el agua del motor.    
	*/
	public void desgastar(){
	  	
	}
		
	/**
	 *  @Pre: La instancia ha sido creada y se encuentra en estado encendido.
	 *  @Post: Se han seteado las rpm de la instancia.    
	*/
	protected void setRPM(double rpm) {
		this.RPM=rpm;
	}
	
	/**
	 * @Pre: La instancia ha sido creada.
	 * @Post: Se ha afectado la instancia por el clima.
	*/
	public void afectar(Clima clima){
		setTemperaturaAire(clima.getTemperatura());
	}
	
	/**
	 * @Pre: La instancia ha sido creada y se encuentra en estado encendido.
	 * @Post: Se ha obtenido las Rpm del motor.
	*/
	public double getRPM() {
		return RPM;
	}
		
	/* (non-Javadoc)
	 * @see modelo.Componente#obtenerPotencia()
	 */
	@Override
	public double obtenerPotencia() {
		return 0;
	}

	/**
	 * @Pre: La instancia ha sido creada.
	 * @Post: Se ha obtenido las Rpm maximas que puede alcanzar la instancia segun su configuraci�n.
	*/
	public double getRevolucionesMaximas() {
		return revolucionesMaximas;
	}
	
	/**
	 * @Pre: La instancia ha sido creada.
	 * @Post: Se han seteado las Rpm maximas que puede alcanzar la instancia.
	*/
	protected void setRevolucionesMaximas(double revoluciones) {
		this.revolucionesMaximas = revoluciones;
	}
			
	/**
	 * @return the temperaturaAire
	 */
	protected double getTemperaturaAire() {
		return temperaturaAire;
	}

	/**
	 * @param temperaturaExterna the temperaturaAire to set
	 */
	protected void setTemperaturaAire(double temperatura) {
		this.temperaturaAire = temperatura;
	}

	/**
	 * @param cilindrada the cilindrada to set
	 */
	protected void setCilindrada(double cilindrada) {
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
	protected void setCantidadCilindros(int cantidadCilindros) {
		this.cantidadCilindros = cantidadCilindros;
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
	private void setEncendido(boolean encendido) {
		this.encendido = encendido;
	}

	/**
	 * @return the acelerando
	 */
	public boolean isAcelerando() {
		return acelerando;
	}

	/**
	 * @param acelerando the acelerando to set
	 */
	private void setAcelerando(boolean acelerando) {
		this.acelerando = acelerando;
	}
		
	/**
	 * @return the revolucionesMinimasEncendido
	 */
	public double getRevolucionesMinimasEncendido() {
		return revolucionesMinimasEncendido;
	}
	
	/**
	 * @param revolucionesMinimasEncendido the revolucionesMinimasEncendido to set
	 */
	protected void setRevolucionesMinimasEncendido(double revolucionesMinimasEncendido) {
		this.revolucionesMinimasEncendido = revolucionesMinimasEncendido;
	}

	/**
	 * @return the coeficienteDeAbsorcionCalorico
	 */
	protected double getCoeficienteDeAbsorcionCalorico() {
		return coeficienteDeAbsorcionCalorico;
	}

	/**
	 * @param coeficienteDeAbsorcionCalorico the coeficienteDeAbsorcionCalorico to set
	 */
	protected void setCoeficienteDeAbsorcionCalorico(double coeficienteDeAbsorcionCalorico) {
		this.coeficienteDeAbsorcionCalorico = coeficienteDeAbsorcionCalorico;
	}

	/**
	 * @return the coeficienteDeDisipacionCalorico
	 */
	protected double getCoeficienteDeDisipacionCalorico() {
		return coeficienteDeDisipacionCalorico;
	}

	/**
	 * @param coeficienteDeDisipacionCalorico the coeficienteDeDisipacionCalorico to set
	 */
	protected void setCoeficienteDeDisipacionCalorico(double coeficienteDeDisipacionCalorico) {
		this.coeficienteDeDisipacionCalorico = coeficienteDeDisipacionCalorico;
	}

	/* (non-Javadoc)
	 * @see modelo.Componente#instalar(modelo.Auto)
	 */
	@Override
	public void instalar(Auto auto) {
		setAuto(auto);
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
	
	/**
	 * @return the coeficienteDeIncrementoRpm
	 */
	protected double getCoeficienteDeIncrementoRpm() {
		return coeficienteDeIncrementoRpm;
	}

	/**
	 * @param coeficienteDeIncrementoRpm the coeficienteDeIncrementoRpm to set
	 */
	protected void setCoeficienteDeIncrementoRpm(double coeficienteDeIncrementoRpm) {
		this.coeficienteDeIncrementoRpm = coeficienteDeIncrementoRpm;
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

	/**
	 * @return the potenciaExtra
	 */
	public double getPotenciaExtra() {
		return potenciaExtra;
	}

	/**
	 * @param potenciaExtra the potenciaExtra to set
	 */
	private void setPotenciaExtra(double potenciaExtra) {
		this.potenciaExtra = potenciaExtra;
	}
			
	/**
	 * @return the coeficienteDeProduccionDeFuerzaAPartirRpm
	 */
	private double getCoeficienteDeProduccionDeFuerzaAPartirRpm() {
		return coeficienteDeProduccionDeFuerzaAPartirRpm;
	}

	/**
	 * @param coeficienteDeProduccionDeFuerzaAPartirRpm the coeficienteDeProduccionDeFuerzaAPartirRpm to set
	 */
	public void setCoeficienteDeProduccionDeFuerzaAPartirRpm(
			double coeficienteDeProduccionDeFuerzaAPartirRpm) {
		this.coeficienteDeProduccionDeFuerzaAPartirRpm = coeficienteDeProduccionDeFuerzaAPartirRpm;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String cadena="Motor de "+getCantidadCilindros()+" cilindros "+getPotenciaMaxima()+
		   " Hp a "+getRevolucionesMaximas()+" rpm"+'\n'+"  RPM:"+getRPM()
		   +"rpm Temperatura: "+getTemperatura()+"�C ";
		if(isEncendido())
			cadena=cadena+"Encendido ";
		else
			cadena=cadena+"Apagado ";
		if(isAcelerando())
			cadena=cadena+"Acelerando ";
		else
			cadena=cadena+"No Acelerando";
		
		return(cadena);	
	}
	
}