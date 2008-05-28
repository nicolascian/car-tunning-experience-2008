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
import modelo.fuerzas.RepositorioDeFuerzas;
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
	
	protected final static double COEFICIENTE_DE_ABSORCION_CALORICO_INICIAL=0.0015;
	
	protected final static double COEFICIENTE_DE_DISIPACION_CALORICO_INICIAL=0.003;
	
	protected final static double COEFICIENTE_DE_DESGASTE_POR_TEMPERATURA=1.5;
	
	protected final static double COEFICIENTE_DE_DESGASTE_POR_EXCESO_DE_REVOLUCIONES=2;
	
	protected final static double COEFICIENTE_DE_INCREMENTO_RPM_MAXIMO=2.0;
	
	protected final static double COEFICIENTE_DE_INCREMENTO_RPM_MINIMO=1.0;
	
	protected final static double COEFICIENTE_BASICO_DE_DESGASTE=1.5;
			
	protected final static double PORCENTAJE_REVOLUCIONES_UMBRAL_PELIGRO=0.898;
	
	//---------------------     atributos basicos de motor   -------------------
	
	private double cilindrada=0;//en centimetros c�bicos
	
	private int cantidadCilindros=0;
	
	private double revolucionesMaximas=0;//revoluciones maximas que el motor puede alcanzar segun su cilindrada 
	                                   //y cantidad de cilindros en rpm.
			
	private double RPM=0;//revoluciones a las que se encuentra trabajando el motor, en rpm.
	
	private boolean encendido=false;//indica si el motor se encuentra encendido
	
	private boolean acelerando=false;//indica si el motor esta acelerendo
		
	private double temperatura=0;//temperatura en �C del motor
	
	private double potenciaMaxima=0;
	
	private double potenciaExtra=0;
	
	//----------------------  atributos secundarios    ----------------------------
	
	private double temperaturaAire=25;//temperatura del aire que ingresa al motor en �C
	
	private double revolucionesMinimasEncendido=0;//revoluciones minimas que alcanza el auto mientras
												//se haya encendido.
	
	private double revolucionesUmbralPeligro=0;/*revoluciones a partir de las cuales el motor
												entra en una fatiga intensa, es decir las revoluciones
												que nomalmente estan pintadas de rojo en el tablero*/
	
	private double coeficienteDeAbsorcionCalorico=0;
		
	private double coeficienteDeDisipacionCalorico=0;
	
	private double coeficienteDeIncrementoRpm=COEFICIENTE_DE_INCREMENTO_RPM_MINIMO;
	
	private double coeficienteDeProduccionDeFuerzaAPartirRpm;
		
	private RepositorioDeFuerzas repositorio=null;
		
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
		setRevolucionesUmbralPeligro(getRevolucionesMaximas()*PORCENTAJE_REVOLUCIONES_UMBRAL_PELIGRO);
	//inicializacion de temperatura
		setTemperatura(TEMPERATURA_INICIAL);
	 //inicilizacion de coeficientes
		setCoeficienteDeAbsorcionCalorico(COEFICIENTE_DE_ABSORCION_CALORICO_INICIAL);
		setCoeficienteDeDisipacionCalorico(COEFICIENTE_DE_DISIPACION_CALORICO_INICIAL);
		this.actualizarCoeficienteDeProduccionDeFuerzaAPartirRpm();
	//inicializacion de atributos booleanos
		setEncendido(false);
		setAcelerando(false);
	//auto y estado
		setAuto(null);
		setEstado(100);
	//inicializacion de potencias 	
		potenciaMaxima=getCilindrada()*getCantidadCilindros()*getRevolucionesMaximas()/640000;
		potenciaExtra=0;
		repositorio=new RepositorioDeFuerzas(this);
	 
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
	  //inicializacion de cilindara y cilindros
		setCantidadCilindros(4);
		setCilindrada(1600);
	  //inicializacion de revoluciones
		setRevolucionesMaximas(8000);
		setRevolucionesMinimasEncendido(getRevolucionesMaximas()*COEFICIENTE_RPM_ENCENDIDO);
		setRevolucionesUmbralPeligro(getRevolucionesMaximas()*PORCENTAJE_REVOLUCIONES_UMBRAL_PELIGRO);
	  //inicializacion de temperatura
		setTemperatura(TEMPERATURA_INICIAL);
	  //inicilizacion de coeficientes
		setCoeficienteDeAbsorcionCalorico(COEFICIENTE_DE_ABSORCION_CALORICO_INICIAL);
		setCoeficienteDeDisipacionCalorico(COEFICIENTE_DE_DISIPACION_CALORICO_INICIAL);
		this.actualizarCoeficienteDeProduccionDeFuerzaAPartirRpm();
	 //inicializacion de atributos booleanos
		setEncendido(false);
		setAcelerando(false);
	  //auto y estado
		setAuto(null);
		setEstado(100);
	  //inicializacion de potencias 	
		potenciaMaxima=getCilindrada()*getCantidadCilindros()*getRevolucionesMaximas()/640000;
		potenciaExtra=0;
		repositorio=new RepositorioDeFuerzas(this);
	}
	
	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#liberarFuerzas()
	 */
	@Override
	public void liberarFuerzas() {
		repositorio.vaciar();
	}

	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#recibirFuerza(modelo.Fuerza)
	 */
	@Override
	public void recibirFuerza(Fuerza fuerza) {
		repositorio.insertarFuerza(fuerza);
		
	}
	
	public void afectarRpmPorFuerza(Fuerza fuerza){
		   double rpmFinal=RPM;
		   double valorDeLaFuerza=0;
		   try{
			   valorDeLaFuerza=fuerza.getValorDeLaFuerza();
		   }catch (Exception e){}
		   rpmFinal+=valorDeLaFuerza/coeficienteDeProduccionDeFuerzaAPartirRpm;
		   if(rpmFinal<getRevolucionesMinimasEncendido()){
			     rpmFinal=getRevolucionesMinimasEncendido();
		   }
		   if(rpmFinal>getRevolucionesMaximas()){
			     rpmFinal=getRevolucionesMaximas();
		   }
		   afectarCoeficienteDeIncrementoRpmPorCambioBruscoRpm(rpmFinal);
		   setRPM(rpmFinal);
	}
	
	private void incrementarCoeficienteDeIncrementoRpm(){
		double coeficiente=coeficienteDeIncrementoRpm+0.001;//0.0002
		if(coeficiente>COEFICIENTE_DE_INCREMENTO_RPM_MAXIMO)
			coeficiente=COEFICIENTE_DE_INCREMENTO_RPM_MAXIMO;
		this.setCoeficienteDeIncrementoRpm(coeficiente);
	}
	
	private void decrementarCoeficienteDeIncrementoRpm(){
		double coeficiente=coeficienteDeIncrementoRpm-0.001;//0.0002
		if(coeficiente>COEFICIENTE_DE_INCREMENTO_RPM_MINIMO)
			coeficiente=COEFICIENTE_DE_INCREMENTO_RPM_MINIMO;
		this.setCoeficienteDeIncrementoRpm(coeficiente);
	}
	
	private void afectarCoeficienteDeIncrementoRpmPorCambioBruscoRpm(double rpmFinal){
		double diferencia=RPM-rpmFinal;
		double coeficienteFinal=getCoeficienteDeIncrementoRpm()+
		                        diferencia/getRevolucionesMaximas();
		if(coeficienteFinal>COEFICIENTE_DE_INCREMENTO_RPM_MAXIMO)
			coeficienteFinal=COEFICIENTE_DE_INCREMENTO_RPM_MAXIMO;
		if(coeficienteFinal>COEFICIENTE_DE_INCREMENTO_RPM_MINIMO)
			coeficienteFinal=COEFICIENTE_DE_INCREMENTO_RPM_MINIMO;
		this.setCoeficienteDeIncrementoRpm(coeficienteFinal);
	}
	
	/**
	 *  @Pre: La instancia ha sido creada y se encuentra en estado encendido.
	 *  @Post: Se ha seteado la instancia como acelerando.    
	*/
	public void acelerar(boolean valor){
	  if(isEncendido()){	  
		  try{   
			 //se obtiene potencia extra del resto de componentes
		     setPotenciaExtra(getAuto().getPotenciaTotal());
		     actualizarCoeficienteDeProduccionDeFuerzaAPartirRpm();
		     setAcelerando(valor);
		     double valorFuerza=0;
		     if(isAcelerando()){
				 incrementarRpm();
				 valorFuerza=getRPM()*coeficienteDeProduccionDeFuerzaAPartirRpm;  
		     }	   
			 else{
				 decrementarRpm();
				 valorFuerza=getRPM()*coeficienteDeProduccionDeFuerzaAPartirRpm*0.28;
			 }
		     /*Envio una fuerza al eje proporcional a las rpm y 
		     al coeficienteDeProduccionDeFuerzaAPartirDeRpm*/
		     Fuerza fuerza=new Fuerza(this,getAuto().getCaja(),valorFuerza,true);
		     getAuto().getCaja().recibirFuerza(fuerza);
		     //obtengo el total de fuerzas sobre el motor
		     valorFuerza+=repositorio.obtenerValorSumatoriaDeFuerzas();
		     if(valorFuerza<0)
		         afectarRpmPorFuerza(new Fuerza(this,getAuto().getCaja(),valorFuerza,true));
		  }catch (NullPointerException e){}
	  }
	}
	
	/**
	 *  @Pre: La instancia ha sido creada y se encuentra en estado encendido.
	 *  @Post: Se han decrementado las rpm de acuerdo al estado actual del coeficienteDeIncrementoRpm 
	 *  de la instancia.    
	*/
	private void decrementarRpm(){
		double rpmFinal=getRPM()-this.coeficienteDeIncrementoRpm;
		if(rpmFinal<getRevolucionesMinimasEncendido())
			rpmFinal=getRevolucionesMinimasEncendido();
		decrementarCoeficienteDeIncrementoRpm();		
		setRPM(rpmFinal);
	}
	
	/**
	 *  @Pre: La instancia ha sido creada y se encuentra en estado encendido.
	 *  @Post: Se han incrementado las rpm de acuerdo al estado actual del coeficienteDeIncrementoRpm 
	 *  de la instancia.    
	*/
	private void incrementarRpm(){
		double rpmFinal=getRPM()+coeficienteDeIncrementoRpm;
		if(rpmFinal>this.getRevolucionesMaximas())
		   rpmFinal=getRevolucionesMaximas();
		this.incrementarCoeficienteDeIncrementoRpm();
		setRPM(rpmFinal);
	}
	
	/**
	 *  @Pre: La instancia ha sido creada y se encuentra en estado encendido.
	 *  @Post: Se ha actualizado el coeficienteDeProduccionDeFuerzaAPartirRpm de la instancia a partir
	 *  de la potencia maxima del motor, y de la potencia extra.    
	*/
	private void actualizarCoeficienteDeProduccionDeFuerzaAPartirRpm(){
		double potencia=getPotenciaMaxima()+getPotenciaExtra();
		double coeficiente=getCoeficienteDeProduccionDeFuerzaAPartirRpm();
		if(getRPM()<=getRevolucionesUmbralPeligro()){
		  if(!isAcelerando()){
			double maximo=potencia*0.0048;//0.0038
			coeficiente+=0.0003;//0.00003
			if(coeficiente>maximo)
			  coeficiente=maximo;
		  }
		  else{
		    double medio=potencia*0.00425;//0.00325
			coeficiente-=0.0003;//0.00003
			if(coeficiente<medio)
			  coeficiente=medio;
		  }
		}
		else{
		   double minimo=potencia*0.00135;
		   if(!isAcelerando()){
			  coeficiente+=0.0003;//0.0003
		   }
		   else{
			 coeficiente-=0.0005;//0.00005
			 if(coeficiente<minimo)
			   coeficiente=minimo;
		   }  
		}
		setCoeficienteDeProduccionDeFuerzaAPartirRpm(coeficiente);
	}
	
	/**
	 *	@Pre: La instancia ha sido creada.
	 *  @Post: Si la insntancia no se encontraba en estado encendido y ademas estaba lista para la carrera
	 *  se ha encendido, de manera tal que se setean las las rpm en a un porcentaje de las rpm maximas del
	 *  motor, tal como si estubiese regulando.    
	*/
	public void encender(){
		if(!isEncendido()){
			actualizarCoeficienteDeProduccionDeFuerzaAPartirRpm();
			setEncendido(true);
		  //seteo de temperaturas
			setTemperatura(TEMPERATURA_INICIAL);
		  //seteo de revoluciones
			setRevolucionesMinimasEncendido(getRevolucionesMaximas()*COEFICIENTE_RPM_ENCENDIDO);
			setRPM(getRevolucionesMinimasEncendido());
			setAcelerando(false);
		}
	}
	
	/**
	 *	@Pre: La instancia ha sido creada.
	 *  @Post: Si la insntacia se encontraba en estado encendido se colca no encendido, quedando inhabilitada
	 *  para acelerar.    
	*/
	public void apagar(){
		    setEncendido(false);
			setRPM(0);
			setTemperatura(25);
			setAcelerando(false);
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
		ActualizarObservadores();
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
	public double getCoeficienteDeProduccionDeFuerzaAPartirRpm() {
		return coeficienteDeProduccionDeFuerzaAPartirRpm;
	}

	/**
	 * @param coeficienteDeProduccionDeFuerzaAPartirRpm the coeficienteDeProduccionDeFuerzaAPartirRpm to set
	 */
	public void setCoeficienteDeProduccionDeFuerzaAPartirRpm(
			double coeficienteDeProduccionDeFuerzaAPartirRpm) {
		this.coeficienteDeProduccionDeFuerzaAPartirRpm = coeficienteDeProduccionDeFuerzaAPartirRpm;
	}
		
	/**
	 * @return the revolucionesUmbralPeligro
	 */
	public double getRevolucionesUmbralPeligro() {
		return revolucionesUmbralPeligro;
	}

	/**
	 * @param revolucionesUmbralPeligro the revolucionesUmbralPeligro to set
	 */
	public void setRevolucionesUmbralPeligro(double revolucionesUmbralPeligro) {
		this.revolucionesUmbralPeligro = revolucionesUmbralPeligro;
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