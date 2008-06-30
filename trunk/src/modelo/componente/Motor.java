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

import org.w3c.dom.Element;
import org.w3c.dom.Document;

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
					
	protected final static double COEFICIENTE_RPM_ENCENDIDO=0.08;//
		
	protected final static double COEFICIENTE_DE_DESGASTE_POR_EXCESO_DE_REVOLUCIONES=2;
	
	protected final static double COEFICIENTE_DE_INCREMENTO_RPM_MAXIMO=75.0;//2.0
	
	protected final static double COEFICIENTE_DE_INCREMENTO_RPM_MINIMO=15.0;//1.0
	
	protected final static double COEFICIENTE_DE_DESGASTE=2;
			
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
	
	private double revolucionesMinimasEncendido=0;//revoluciones minimas que alcanza el auto mientras
												//se haya encendido.
	
	private double revolucionesUmbralPeligro=0;/*revoluciones a partir de las cuales el motor
												entra en una fatiga intensa, es decir las revoluciones
												que nomalmente estan pintadas de rojo en el tablero*/
		
	private double coeficienteDeIncrementoRpm=COEFICIENTE_DE_INCREMENTO_RPM_MINIMO;
	
	private double coeficienteDeProduccionDeFuerzaAPartirRpm=0;
	
	private double valorFuerzaContraMotor=0;
	
	private RepositorioDeFuerzas repositorio=null;
	
	private double temperaturaExterior=25;
	
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
	//inicilizacion de coeficientes
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
	 //inicilizacion de coeficientes
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
	
	public Element toXml(Document doc) {
		Element xmlElement = doc.createElement("motor");
		xmlElement.setAttribute("estado", String.valueOf(this.getEstado()));
		return xmlElement;
	}
	
	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#liberarFuerzas()
	 */
	public void liberarFuerzas() {
		repositorio.vaciar();
	}

	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#recibirFuerza(modelo.Fuerza)
	 */
	
	public void recibirFuerza(Fuerza fuerza) {
		repositorio.insertarFuerza(fuerza);
	}
	
	/**
	 * @Pre:La instancia se encuetra encendida.
	 * @Post:Se ha afectado las rpm segun la fuerza pasada por parametro.
	 */
	public void afectarRpmPorFuerza(Fuerza fuerza){
		   double rpmFinal=RPM;
		   double valorDeLaFuerza=0;
		   try{
			   valorDeLaFuerza=fuerza.getValorDeLaFuerza();
		   }catch (Exception e){}
		   rpmFinal+=valorDeLaFuerza/(400*coeficienteDeProduccionDeFuerzaAPartirRpm);
		   if(rpmFinal<getRevolucionesMinimasEncendido()){
			     rpmFinal=getRevolucionesMinimasEncendido();
		   }
		   if(rpmFinal>getRevolucionesMaximas()){
			     rpmFinal=getRevolucionesMaximas();
		   }
		   afectarCoeficienteDeIncrementoRpmPorCambioRpm(rpmFinal);
		   setRPM(rpmFinal);
	}
	
	/**
	 * @Pre:La instancia se encuetra encendida.
	 * @Post:Se ha incrementado el coeficiente de incremento de rpm.
	*/	
	private void incrementarCoeficienteDeIncrementoRpm(){
		double coeficiente=coeficienteDeIncrementoRpm+=20;//0.001;//0.0002
		if(coeficiente>COEFICIENTE_DE_INCREMENTO_RPM_MAXIMO)
			coeficiente=COEFICIENTE_DE_INCREMENTO_RPM_MAXIMO;
		this.setCoeficienteDeIncrementoRpm(coeficiente);
	}
	
	/**
	 * @Pre:La instancia se encuentra encendida.
	 * @Post:Se ha decrementado el coeficiente de incremento de rpm.
	 */
	private void decrementarCoeficienteDeIncrementoRpm(){
		double coeficiente=coeficienteDeIncrementoRpm-=20;//0.001;//0.0002
		if(coeficiente>COEFICIENTE_DE_INCREMENTO_RPM_MINIMO)
			coeficiente=COEFICIENTE_DE_INCREMENTO_RPM_MINIMO;
		this.setCoeficienteDeIncrementoRpm(coeficiente);
	}
	/**
	 * @Pre: La instancia se encuentra encendida.
	 * @Post: Se afecta el coeficiente de incremento de las rpm por una variacion de las rpm. 
	 * @param rpmFinal
	 */
	private void afectarCoeficienteDeIncrementoRpmPorCambioRpm(double rpmFinal){
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
		     getAuto().getSistemaDeRefrigeracion().chequearTemperatura();
		     actualizarCoeficienteDeProduccionDeFuerzaAPartirRpm();
		     setAcelerando(valor);
		     double valorFuerza=0;
		     if(isAcelerando()){
				 incrementarTemperatura();
		    	 incrementarRpm();
				 valorFuerza=getRPM()*coeficienteDeProduccionDeFuerzaAPartirRpm;
			 }	   
			 else{
				 decrementarRpm();
				 valorFuerza=valorFuerzaContraMotor;
		     }
		     /*Envio una fuerza al eje proporcional a las rpm y 
		     al coeficienteDeProduccionDeFuerzaAPartirDeRpm*/
		     Fuerza fuerza=new Fuerza(this,getAuto().getCaja(),valorFuerza,true);
		     getAuto().getCaja().recibirFuerza(fuerza);
		     //obtengo el total de fuerzas sobre el motor
		     double sumatoria=repositorio.obtenerValorSumatoriaDeFuerzas();
		     valorFuerza+=sumatoria;
		     valorFuerzaContraMotor=sumatoria/(5*getAuto().getCaja().getRelacionDeCambio());
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
		double rpmFinal=getRPM()-coeficienteDeIncrementoRpm;
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
	 * @Pre: La instancia se encuentra encendida.
	 * @Post: En caso de contar con la potencia extra necesaria se retorna la potencia pasada por por
	 * parametro. 
	 * @param potencia
	 * @return
	*/
	public double tomarPotencia(double potencia){
		double potenciaFinal=getPotenciaExtra()-potencia;
		if(potenciaFinal<=0){
		  setPotenciaExtra(0);
		  return 0;
		}  
		else{
			setPotenciaExtra(potenciaFinal);
			return(potenciaFinal);
		}
	}
	/**
	 * @Pre: La instancia se encuentra encendida.
	 * @Post: Se ha modificado la temperatura de la instancia de acuerdo a la temperatura exterior.
	 */
	protected void incrementarTemperatura(){
		if(getTemperatura()>0)
		   setTemperatura(getTemperatura()+this.getTemperaturaExterior()*0.08);
		else
		   setTemperatura(getTemperatura()+0.5);	
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
			double maximo=potencia*0.005;
			coeficiente+=0.0003;//
			if(coeficiente>maximo)
			  coeficiente=maximo;
		  }
		  else{
		    double medio=potencia*0.00435;
			coeficiente-=0.0003;
			if(coeficiente<medio)
			  coeficiente=medio;
		  }
		}
		else{
			if(getRPM()>=getRevolucionesMaximas()){
			   coeficiente=0.0001;	
			}
			else{  
			  double minimo=potencia*0.00135;
		      if(!isAcelerando()){
			     coeficiente+=0.0003;
		      }
		      else{
			     coeficiente-=0.0005;
			     if(coeficiente<minimo)
			        coeficiente=minimo;
		   
		      }
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
			setTemperatura(25);
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
	 * @Pre: La instancia ha sido creada y se encuentra en estado encendido.
	 * @Post: Se ha desgastado la instancia segun las revoluciones maximas soportadas para el cambio
	 * actual y segun la temperatura a la que se encuentra el agua del motor.    
	*/
	public void desgastar(){
		double desgaste=Constantes.tiempoPorCiclo*COEFICIENTE_DE_DESGASTE;
		if(getRPM()>this.getRevolucionesUmbralPeligro()){
			if(getRPM()==this.getRevolucionesMaximas())
				desgaste+=Constantes.tiempoPorCiclo*5*COEFICIENTE_DE_DESGASTE;
			else
				desgaste+=Constantes.tiempoPorCiclo*2*COEFICIENTE_DE_DESGASTE;
		}
		setEstado(getEstado()-desgaste);
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
	  try{	
		this.setTemperaturaExterior(clima.getTemperatura());
	  }catch(NullPointerException e){}
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

	/**
	 * @return the temperaturaExterior
	 */
	public double getTemperaturaExterior() {
		return temperaturaExterior;
	}

	/**
	 * @param temperaturaExterior the temperaturaExterior to set
	 */
	public void setTemperaturaExterior(double temperaturaExterior) {
		this.temperaturaExterior = temperaturaExterior;
	}
	
}