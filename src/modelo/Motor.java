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
	protected final static double COEFICIENTE_TIEMPO_ACELERACION_CARACTERISTICO=0.8;
	
	protected final static double COEFICIENTE_RPM_ENCENDIDO=0.08;// 8%
	
	protected final static double COEFICIENTE_POTENCIA_A_OBTENER=0.05;// 5%
	
	protected final static double COEFICIENTE_DE_ABSORCION_CALORICO_INICIAL=0.0015;
	
	protected final static double COEFICIENTE_DE_DISIPACION_CALORICO_INICIAL=0.003;
	
	protected final static double COEFICIENTE_DE_DESGASTE_POR_TEMPERATURA=1.5;
	
	protected final static double COEFICIENTE_DE_DESGASTE_POR_EXCESO_DE_REVOLUCIONES=2;
	
	protected final static double COEFICIENTE_BASICO_DE_DESGASTE=1.5;
	
	//---------------------     atributos basicos de motor   -------------------
	
	private double cilindrada;//en centimetros c�bicos
	
	private int cantidadCilindros;
	
	private double revolucionesMaximas;//revoluciones maximas que el motor puede alcanzar segun su cilindrada 
	                                   //y cantidad de cilindros en rpm.
			
	private double RPM;//revoluciones a las que se encuentra trabajando el motor, en rpm.
	
	private boolean encendido;//indica si el motor se encuentra encendido
	
	private boolean acelerando;//indica si el motor esta acelerendo
		
	private double temperatura;//temperatura en �C del motor
	
	//----------------------  atributos secundarios    ----------------------------
	
	protected long tiempoCaracteristicoAceleracion;//en milisegundos
	
	private long tiempoDeControlAceleracion;//en milisegundos
	
	private long tiempoDeControlCurvaAceleracion;//en milisegundos
	
	protected double temperaturaAire;//temperatura del aire que ingresa al motor en �C
	
	protected double revolucionesMaximasCambio;//revoluciones maximas que el motor puede alcanzar 
											 //al encontrarse en un cambio dado, en rpm.
	
	protected double revolucionesMinimasEncendido;//revoluciones minimas que alcanza el auto mientras
												//se haya encendido.
	
	protected double coeficienteDeAbsorcionCalorico;
		
	protected double coeficienteDeDisipacionCalorico;
	
	protected boolean actualizandoRPM=false;//inicida si se estan actualizando las rpm evitando asi
	                                        //bucles
	
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
		setRevolucionesMaximasCambio(revolucionesMaximas*COEFICIENTE_RPM_ENCENDIDO);
		setRevolucionesMinimasEncendido(getRevolucionesMaximas()*COEFICIENTE_RPM_ENCENDIDO);
		RPM=0;
	 //inicializacion de temperaturas
		setTemperaturaAire(25); //°C
		setTemperatura(TEMPERATURA_INICIAL);
	 //inicilizacion de coeficientes
		setCoeficienteDeAbsorcionCalorico(COEFICIENTE_DE_ABSORCION_CALORICO_INICIAL);
		setCoeficienteDeDisipacionCalorico(COEFICIENTE_DE_DISIPACION_CALORICO_INICIAL);
	 //inicializacion de atributos booleanos
		setEncendido(false);
		setAcelerando(false);
	 //inicializacion de tiempos
		setTiempoDeControlAceleracion(0);
		setTiempoCaracteristicoAceleracion(Math.round(getRevolucionesMaximas()*
				                           COEFICIENTE_TIEMPO_ACELERACION_CARACTERISTICO));
	//auto y estado
		setAuto(null);
		setEstado(100);
	}
	
	
	
	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#liberarFuerzas()
	 */
	@Override
	public void liberarFuerzas() {
		// TODO Auto-generated method stub
		
	}



	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#recibirFuerza(modelo.Fuerza)
	 */
	@Override
	public void recibirFuerza(Fuerza fuerza) {
		// TODO Auto-generated method stub
		
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
		setTiempoDeControlAceleracion(0);
		setTiempoCaracteristicoAceleracion(Math.round(getRevolucionesMaximas()*
				                  COEFICIENTE_TIEMPO_ACELERACION_CARACTERISTICO));
		setAuto(null);
		setEstado(100);
		setRevolucionesMinimasEncendido(getRevolucionesMaximas()*COEFICIENTE_RPM_ENCENDIDO);
		setRevolucionesMaximasCambio(getRevolucionesMinimasEncendido()*1.6);
	}
	
	/**
	 *  @Pre: La instancia ha sido creada y se encuentra en estado encendido.
	 *  @Post: Se ha seteado la instancia como acelerando.    
	*/
	public void acelerar(boolean valor){
	  if(isEncendido()){
		if((valor)&&(!isAcelerando())){
			setAcelerando(true);
			this.setTiempoDeControlAceleracion(System.currentTimeMillis());
			setTiempoDeControlCurvaAceleracion(Math.round((2*getTiempoCaracteristicoAceleracion()/Math.PI)*
					                    Math.asin(Math.round(RPM/getRevolucionesMaximas()))));
		}//fin verdadero y sin acelerar
		else{
			setAcelerando(false);
			this.setTiempoDeControlAceleracion(System.currentTimeMillis());
			setTiempoDeControlCurvaAceleracion(Math.round((2*getTiempoCaracteristicoAceleracion()/Math.PI)*
                    Math.acos(Math.round(RPM/getRevolucionesMaximas()))));
	    }//fin falso y acelerando
		actualizarRpm();
	  }//fin encendido	
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
		//seteo de tiempos
			setTiempoDeControlCurvaAceleracion(0);
			setTiempoDeControlAceleracion(0);
		//seteo de temperaturas
			setTemperatura(TEMPERATURA_INICIAL);
		//seteo de revoluciones
			setRevolucionesMinimasEncendido(getRevolucionesMaximas()*COEFICIENTE_RPM_ENCENDIDO);
			setRevolucionesMaximasCambio(getRevolucionesMinimasEncendido()*1.6);
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
			this.setRevolucionesMaximasCambio(0);
		}
	}
	
	/**
	 *	@Pre: La instancia ha sido creada y se encuentra en estado encendido.
	 *  @Post: Si la instancia se encontraba acelerando se actualizan las rpm. Tambien se actualiza la temperatura 
	 *  de acuerdo a la variaci�n de las revoluciones.    
	*/
	private void actualizarRpm(){
	 if(isEncendido())
	  if(!isActualizandoRPM()){
	   setActualizandoRPM(true);
	   long diferenciaDeTiempoReal=System.currentTimeMillis()-getTiempoDeControlAceleracion();
	   long tiempoParametroFuncion=getTiempoDeControlCurvaAceleracion()+diferenciaDeTiempoReal;
	   if(tiempoParametroFuncion<getTiempoCaracteristicoAceleracion()){
		 if(isAcelerando()){
			 double rpmInicial=RPM;
			 setRPM(getRevolucionesMaximas()*(Math.sin((tiempoParametroFuncion)*Math.PI/
		    	 (2*getTiempoCaracteristicoAceleracion()))));
		     //actualizacion de temperatura
		     actualizarTemperaturaPorCambioDeRpm(rpmInicial, RPM, diferenciaDeTiempoReal);
		 }else
		   if(RPM>getRevolucionesMinimasEncendido()) {
			 double rpm=RPM;
			 double rpmInicial=RPM;
			 rpm=getRevolucionesMaximas()*Math.cos((tiempoParametroFuncion)*Math.PI/
			    	 (2*getTiempoCaracteristicoAceleracion()));
	    	 if(rpm<getRevolucionesMinimasEncendido())
	    		 rpm=getRevolucionesMinimasEncendido();
	    	 setRPM(rpm);
	    	 //actualizacion de temperatura
	    	 actualizarTemperaturaPorCambioDeRpm(rpmInicial, RPM, diferenciaDeTiempoReal);
           }
	     //chequeo para compatibilidad con caja automatica
		 getAuto().getCaja().Chequear(); 
	   }
	   setActualizandoRPM(false);
	  }
	}
	
	/**
	 *	@Pre: La instancia ha sido creada y se encuentra en estado encendido.
	 *  @Post: Se actualiza la temperatura de acuerdo a la variaci�n de las revoluciones.    
	*/
	private void actualizarTemperaturaPorCambioDeRpm(double rpmInicial,double rpmFinal,long diferenciaDeTiempo){
		double variacion;
		if(rpmInicial<rpmFinal){//sube temperatura
			variacion=((rpmFinal-rpmInicial)*diferenciaDeTiempo*getCoeficienteDeAbsorcionCalorico());
			setTemperatura(getTemperatura()+variacion);
		}
		else{
			if(getTemperatura()>80){//baja termperatura
			  variacion=((rpmFinal-rpmInicial)*diferenciaDeTiempo*getCoeficienteDeDisipacionCalorico());
			  setTemperatura(getTemperatura()+variacion);
		    }
		}
	}
	
	/**
	 *  @Pre: La instancia ha sido creada y se encuentra en estado encendido.
	 *  @Post: Se ha obtiene la potencia que podria alcanzar la instancia si llegaria al maximo
	 *  de revoluciones posible.    
	*/
	public double obtenerPotenciaMaximaTeorica(){
		return(getCilindrada()*getCantidadCilindros()*getRevolucionesMaximas()/640000);
	}
		
	/**
	 * @Pre: La instancia ha sido creada y se encuentra en estado encendido.
	 * @Post: Se ha desgastado la instancia segun las revoluciones maximas soportadas para el cambio
	 * actual y segun la temperatura a la que se encuentra el agua del motor.    
	*/
	public void desgastar(){
	  	//si se supera la temperatura critica el motor se funde y su
		//estado se torna 0
		if(getTemperatura()>=TEMPERATURA_CRITICA)
		   setEstado(0);	 
		else{
		   double acumulador=COEFICIENTE_BASICO_DE_DESGASTE;
		   if(getTemperatura()>TEMPERATURA_OPTIMA){
			  acumulador=acumulador+COEFICIENTE_DE_DESGASTE_POR_TEMPERATURA;
		   }
		   if(getRPM()>getRevolucionesMaximasCambio())
		     acumulador=acumulador+COEFICIENTE_DE_DESGASTE_POR_EXCESO_DE_REVOLUCIONES;
		   setEstado(getEstado()-acumulador*tiempoPorCiclo);
		}
	}
	 	
	
	/**
	 *  @Pre: La instancia ha sido creada y se encuentra en estado encendido.
	 *  @Post: Se ha     
	*/
	public void modificarRpmDesdeCaja(){
	 if(isEncendido()){ //debe estar encendido
	   double rpmInicial=RPM;
	   setRPM(getRevolucionesMinimasEncendido());
	   setTiempoDeControlAceleracion(System.currentTimeMillis());
		if(isAcelerando())	 
		     setTiempoDeControlCurvaAceleracion(Math.round((2*getTiempoCaracteristicoAceleracion()/Math.PI)*
					                    Math.asin(Math.round(RPM/getRevolucionesMaximas()))));
		else
		     setTiempoDeControlCurvaAceleracion(Math.round((2*getTiempoCaracteristicoAceleracion()/Math.PI)*
	                    Math.acos(Math.round(RPM/getRevolucionesMaximas())))); 
		//actualizacion de temperatura
		actualizarTemperaturaPorCambioDeRpm(rpmInicial, RPM, getTiempoCaracteristicoAceleracion());
	 }
	}
		
	/**
	 *  @Pre: La instancia ha sido creada y se encuentra en estado encendido.
	 *  @Post: Se ha obtenido la potencia la potencia de la instancia segun las rpm actuales.    
	*/
	public double obtenerPotencia(){
		if(isEncendido()){
		  actualizarRpm();
		  return (calcularPotenciaInterna()*COEFICIENTE_POTENCIA_A_OBTENER);
		}
		else
			return 0;
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
		if(isEncendido())
		   actualizarRpm();
		return RPM;
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
	 * @Pre: La instancia ha sido creada.
	 * @Post: Se han obtenido las Rpm maximas que puede alcanzar la instancia en el cambio actual.
	*/
	public double getRevolucionesMaximasCambio() {
		return revolucionesMaximasCambio;
	}	

	/**
	 * @Pre: La instancia ha sido creada y se encuentra encendida.
	 * @Post: Se han seteado las Rpm maximas que puede alcanzar la instancia en el cambio actual.
	*/
	public void setRevolucionesMaximasCambio(double revoluciones) {
		this.revolucionesMaximasCambio = revoluciones;
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
	 * @Pre: La instancia ha sido creada. El auto se encuentra encendido.
	 * @Pos: Se ha obtenido el calculo de la potencia interna del motor retornando en Hp.
	 * @return potencia interna del motor en Hp.
	*/
	protected double calcularPotenciaInterna(){
		double potenciaInterna=0;
		//potencia sin ser afectada por elementos
		potenciaInterna=getCilindrada()*getCantidadCilindros()*getRPM()/6400000;
		//calculo de potencia afectada por la temperatura del aire
		double relacionTemperaturas=(getTemperaturaAire()/TEMPERATURA_MEDIANA_AIRE)-1;
		potenciaInterna=potenciaInterna*Math.abs(1-relacionTemperaturas/25);
		return potenciaInterna;
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
	 * @return the tiempoDeControlAceleracion
	 */
	protected long getTiempoDeControlAceleracion() {
		return tiempoDeControlAceleracion;
	}

	/**
	 * @param tiempoDeControlAceleracion the tiempoDeControlAceleracion to set
	 */
	protected void setTiempoDeControlAceleracion(long tiempoDeControlAceleracion) {
		this.tiempoDeControlAceleracion = tiempoDeControlAceleracion;
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
	protected void setTiempoCaracteristicoAceleracion(long tiempoCaracteristicoAceleracion) {
		this.tiempoCaracteristicoAceleracion = tiempoCaracteristicoAceleracion;
	}

	/**
	 * @return the tiempoDeControlCurvaAceleracion
	 */
	protected long getTiempoDeControlCurvaAceleracion() {
		return tiempoDeControlCurvaAceleracion;
	}

	/**
	 * @param tiempoDeControlCurvaAceleracion the tiempoDeControlCurvaAceleracion to set
	 */
	protected void setTiempoDeControlCurvaAceleracion(long tiempoDeControlCurvaAceleracion) {
		this.tiempoDeControlCurvaAceleracion = tiempoDeControlCurvaAceleracion;
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
		actualizarRpm();
		return temperatura;
	}

	/**
	 * @param temperatura the temperatura to set
	 */
	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String cadena="Motor de "+getCantidadCilindros()+" cilindros "+obtenerPotenciaMaximaTeorica()+
		   " Hp a "+getRevolucionesMaximas()+" rpm"+'\n'+"  RPM:"+getRPM()+" rpm ; RPM MaxCambioActual: "+
		   getRevolucionesMaximasCambio()+"rpm Temperatura: "+getTemperatura()+"�C ";
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

	/**
	 * @return the actualizandoRPM
	 */
	protected boolean isActualizandoRPM() {
		return actualizandoRPM;
	}

	/**
	 * @param actualizandoRPM the actualizandoRPM to set
	 */
	protected void setActualizandoRPM(boolean actualizandoRPM) {
		this.actualizandoRPM = actualizandoRPM;
	}
	
}