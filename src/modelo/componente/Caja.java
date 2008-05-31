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
 * @Documentacion
 * Esta clase modela la caja de velocidades de un vehiculo. Puede tener una cantidad de cambios 
 * determinada, que una vez creada no puede ser cambiada. Cada cambio tiene una relacion de velocidad
 * o de cambio. Se toma como convencion que a mayor numero de cambio menor relacionDeCambio.
 * Una instancia de una clase derivada de Caja intercambia instancias de la clase Fuerza con las instancias
 * de la clase Motor y Eje de un Auto determinado.
 * La cantidad de cambios puede ser de 4 ad 6. 
 * @Nota: Al pasar de cambio la caja disminuye en un porcentaje la cantidad de rpm del motor del auto.
 * @version	3.2
 */
public abstract class Caja extends Componente implements ReceptorDeFuerzas{
		
	private int cambio=0;//cambio actual de la instancia
	
	private double revolucionesMaximasMotorParaCambioActual=0;/*revoluciones maximas optimas para el motor
	 															al cambio actual*/
	private double revolucionesMinimasMotorParaCambioActual=0;/*revoluciones minimas optimas para el motor
																al cambio actual*/	
	private double[] relacionDeCambio;//almacena las distintas relaciones de cambio para cada cambio
		
	private int cantidadCambios;//cantidad de cambios que tiene la caja
	
	private RepositorioDeFuerzas repositorio;//guarda las fuerzas que llegan a la caja
		
	protected final static double COEFICIENTE_DE_DESGASTE=4;
			
	private double coefProdFzaAlPasarACambioMayor;/*coeficiente para obtener la fuerza al pasar de cambio*/
		
	private double coefProdFzaAlPasarACambioMenor;/*coeficiente para obtener la fuerza al pasar de cambio*/
	/**
	 * @Pre:
	 * @Post: Se ha creado una instancia de la clase derivada de la clase Caja segun los parametros
	 * detallados a continuacion.
	 * @param cantidadCambios: cantidad de cambios que posee la caja. Debe entre 4 y 6 sin contar el
	 * punto muerto.En esta implementacion no existe la reverza.
	 */
	public Caja(int cantidadCambios){
		this.cantidadCambios=cantidadCambios;
		repositorio=new RepositorioDeFuerzas(this);
		relacionDeCambio=new double[cantidadCambios+1];
		generarRelacionesDeCaja();
		setEstado(100);
		setCoefProdFzaAlPasarACambioMayor(getCantidadCambios()*3.8847*
		        		             (1+47/(getCantidadCambios()*getCantidadCambios())));
		setCoefProdFzaAlPasarACambioMenor(0.46*getCoefProdFzaAlPasarACambioMayor());
	}
	/**
	 *	@Pre: La instancia ha sido creada.
	 *	@Post: Se ha chequeado algun aspecto relacionado con las revoluciones del motor y realizando
	 *	alguna operacion sobre este y la instancia de la clase Caja. 
	*/
	public void Chequear(){};
	
	/**
	 * @Pre: 
	 * @Post: Se han generado las relaciones de caja correspondientes.
	 */
	abstract protected void generarRelacionesDeCaja();
		
	/**
	 * @Pre: Se ha creado la instancia de la clase derivada de la clase Caja.
	 * @Post: Se ha validado el cambio pasado por parametro segun la codificacion siguiente.
	 * @param cambio: cambio que se desea validar.
	 * @return "true" en caso de que el cambio sea valido y "false" en caso contrario.
	 */
	protected boolean cambioValido(int cambio){
		if((cambio>=0)&&(cambio<=getCantidadCambios()))	
		   return (true);
		else
		   return false;
	}
		
	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#liberarFuerzas()
	 */
	@Override
	public void liberarFuerzas() {
		this.getRepositorio().vaciar();
	}

	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#recibirFuerza(modelo.Fuerza)
	 */
	@Override
	public void recibirFuerza(Fuerza fuerza) {
	    if(fuerza.getEmisor()==getAuto().getMotor()){
		  	  //se pasa la mitad de la fuerza a cada eje
			  double valorDeLaFuerza=0;
			  if(getCambio()!=0)   
			     try{
			        valorDeLaFuerza=fuerza.getValorDeLaFuerza();
			     }catch (Exception e){}
			  //transmito fuerza a eje delantero
			  Fuerza fuerzaAEje=new Fuerza(this,getAuto().getEjeDelantero(),
					                       valorDeLaFuerza/2,true);
			  getAuto().getEjeDelantero().recibirFuerza(fuerzaAEje);
			  //transmito fuerza a eje trasero			  			
			  fuerzaAEje=new Fuerza(this,getAuto().getEjeTrasero(),
					                valorDeLaFuerza/2,true);
			  this.getAuto().getEjeTrasero().recibirFuerza(fuerzaAEje);
		}else{
		  if(getCambio()!=0){	  
			  //viene de alguno de los ejes
        	  double valorDeLaFuerza=0;
			  try{
			     valorDeLaFuerza=fuerza.getValorDeLaFuerza()/getRelacionDeCambio();
			  }catch (Exception e){}
			  //transmito fuerza al motor
			  Fuerza fuerzaAMotor=new Fuerza(this,getAuto().getMotor(),valorDeLaFuerza,true);
			  getAuto().getMotor().recibirFuerza(fuerzaAMotor);
		  }
		  Chequear();
		}
	}

	/**
	 * @Pre: Se ha creado la instancia de la clase derivada de la clase Caja.
	 * @Post: Se ha obtenido el cambio actual.
	 */
	public int getCambio(){
		return cambio;
	}
			
	/**
	 * @Pre: La instancia de la clase derivada de Caja ha sido creada.
	 * @Post: Se ha obtenido la relacion de la caja para el cambio actual.
	 */
	protected double getRelacionDeCambio(){
		return (relacionDeCambio[getCambio()]);
	}
	
	/**
	 * @Pre: La instancia de la clase derivada de Caja ha sido creada.
	 * @Post: Se ha obtenido la relacion de la caja para el cambio pasado por parametro.
	 */
	protected double getRelacionDeCambio(int cambio){
		return (relacionDeCambio[cambio]);
	}
	
	/**
	 * 
	 * @Pre: La instancia ha sido creada.
	 * @Post:Se ha seteado el cambio. Cada vez que hacemos un Cambio, se altera las 
	 * revolucionesMaximas del Motor.
	 */
	public void setCambio(int cambio){
	  try{	
		if ((cambioValido(cambio))&&(cambio!=getCambio())&&(isEmbragado())){
		 if(cambio!=0){ 
		   //se calcula la fuerza que se debe ejercer al motor
		   double valorDeFuerza=0;
		   if(cambio>getCambio())
		      valorDeFuerza=getAuto().getMotor().getRPM()*(-1)*(getCoefProdFzaAlPasarACambioMayor()*
			                    getCantidadCambios()/(cambio)+1.1/Math.pow(getRelacionDeCambio(),2));   
		   else
			   valorDeFuerza=getAuto().getMotor().getRPM()*(getCoefProdFzaAlPasarACambioMenor()*
	                    getCantidadCambios()/(cambio)+1.1/Math.pow(getRelacionDeCambio(),2));
		   //se pasa el cambio
		   this.cambio=cambio;
		   //se actualizan las revoluciones minimas y maximas para el cambio actual
		   actualizarRevolucionesLimiteMotorParaCambioActual();
		   //se pasa una fuerza motor 
		   Fuerza fuerza=new Fuerza(this,getAuto().getMotor(),valorDeFuerza,true);
		   this.getAuto().getMotor().afectarRpmPorFuerza(fuerza);
		 }else{
			//se actualizan las revoluciones minimas y maximas para el cambio actual
			  actualizarRevolucionesLimiteMotorParaCambioActual();
			  this.cambio=0;
		 }
		}//fin if
		if (!isEmbragado()) {
			this.desgastar();}
		ActualizarObservadores();
	  }catch(NullPointerException e){}
	}
	
	/**
	 * @Pre: La instancia de la clase Auto asociada a esta instancia se encuentra encendida
	 * @Post: Se actualizan las revoluciones maximas y minimas del motor para el cambio actual.
	*/
	protected void actualizarRevolucionesLimiteMotorParaCambioActual(){
	 try{ 
	  double minimas;
	  double rpmMaximas;
	  if(getCambio()!=0){	
		minimas=0.6*getAuto().getMotor().getRevolucionesMaximas()*getCambio()/getCantidadCambios();
		rpmMaximas=getAuto().getMotor().getRevolucionesUmbralPeligro();
	  }else{
		  minimas=getAuto().getMotor().getRevolucionesMinimasEncendido();
		  rpmMaximas=getAuto().getMotor().getRevolucionesMinimasEncendido()*1.2;
	  }
	  double rpmMinimas=0.6*getAuto().getMotor().getRPM()*getCambio()/getCantidadCambios();
	  if(rpmMinimas<minimas)
		 rpmMinimas=minimas;
	  this.setRevolucionesMinimasMotorParaCambioActual(rpmMinimas);
	  this.setRevolucionesMaximasMotorParaCambioActual(rpmMaximas);
	 }catch(NullPointerException e){}
	}
		
	/**
	 * @Pre: La instancia de la clase derivada de Caja ha sido creada.
	 * @Post: Se retorna la cantidad de cambios que tiene la instancia.
	 */
	public int getCantidadCambios(){
		return(cantidadCambios);
	}
		
	/**
	 * @return the repositorio
	 */
	public RepositorioDeFuerzas getRepositorio() {
		return repositorio;
	}

	/**
	 * @param repositorio the repositorio to set
	 */
	public void setRepositorio(RepositorioDeFuerzas repositorio) {
		this.repositorio = repositorio;
	}
	
	/**
	 * @Pre: La instancia ha sido creada.
	 * @Post: Se pasa al cambio siguiente.
	*/	
	public void siguiente(){}
	
	/**
	 * @Pre: La instancia ha sido creada.
	 * @Post: Se pasa al cambio anterior.
	*/
	public void anterior(){}

	/**
	 * @Pre: Se ha creado una instancia de la Automatica segun los parametros.
	 * @Post: Se ha obtenido la potencia entregada por la caja.
	 * 
	 */
	public double obtenerPotencia(){
		return 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see modelo.Componente#desgastar()
	 */
	public void desgastar(){
		setEstado(getEstado()-Constantes.tiempoPorCiclo*COEFICIENTE_DE_DESGASTE);
	}
	
	/* (non-Javadoc)
	 * @see modelo.Componente#instalar(modelo.Auto)
	 */
	@Override
	public void instalar(Auto auto) {
		setAuto(auto);
	}
		
	/**
	 * @Pre: Se ha creado la instancia de la clase Caja.
	 * @Post: En caso de que la instancia se encuentre lista para carrera se embraga.
	 */
    public void embragar(boolean valor){
    		this.getAuto().getEmbrague().embragar(valor);
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Caja.
	 * @Post: Se retorna true en caso de que la instancia se encuentre embragado.
	*/
	public boolean isEmbragado(){
		   return this.getAuto().getEmbrague().isEmbragado();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String cadena="Caja de "+getCantidadCambios()+" cambios"+'\n'+"  Relaciones De Cambio: ";
		for(int cambio=0;cambio<=getCantidadCambios();cambio++)
			cadena=cadena+"("+cambio+")"+relacionDeCambio[cambio]+" ";
		cadena=cadena+"Estado "+getEstado()+"%"+'\n';
		return cadena;
	}
	
	/**
	 * @param relacionDeCambio the relacionDeCambio to set
	 */
	protected void setRelacionDeCambio(int cambio,double relacionDeCambio) {
	 try{	
		this.relacionDeCambio[cambio] = relacionDeCambio;
	 }catch(Exception e){}
	}

	/**
	 * @return the revolucionesMaximasMotorParaCambioActual
	 */
	public double getRevolucionesMaximasMotorParaCambioActual() {
		return revolucionesMaximasMotorParaCambioActual;
	}

	/**
	 * @param revolucionesMaximasMotorParaCambioActual the revolucionesMaximasMotorParaCambioActual to set
	 */
	public void setRevolucionesMaximasMotorParaCambioActual(
			double revolucionesMaximasMotorParaCambioActual) {
		this.revolucionesMaximasMotorParaCambioActual = revolucionesMaximasMotorParaCambioActual;
		ActualizarObservadores();
	}

	/**
	 * @return the revolucionesMinimasMotorParaCambioActual
	 */
	public double getRevolucionesMinimasMotorParaCambioActual() {
		return revolucionesMinimasMotorParaCambioActual;
	}

	/**
	 * @param revolucionesMinimasMotorParaCambioActual the revolucionesMinimasMotorParaCambioActual to set
	 */
	public void setRevolucionesMinimasMotorParaCambioActual(
			double revolucionesMinimasMotorParaCambioActual) {
		this.revolucionesMinimasMotorParaCambioActual = revolucionesMinimasMotorParaCambioActual;
	}

	/**
	 * @return the fuerzaMaximaAlPasarACambioMayor
	*/
	public double getCoefProdFzaAlPasarACambioMayor() {
		return coefProdFzaAlPasarACambioMayor;
	}

	/**
	 * @param coefProdfzaAlPasarACambioMayor the coefProdfzaAlPasarACambioMayor to set
	*/
	public void setCoefProdFzaAlPasarACambioMayor(double coefProdFzaAlPasarACambioMayor) {
		this.coefProdFzaAlPasarACambioMayor = coefProdFzaAlPasarACambioMayor;
	}
	
	/**
	 * @return the fuerzaMaximaAlPasarACambioMayor
	*/
	public double getCoefProdFzaAlPasarACambioMenor() {
		return coefProdFzaAlPasarACambioMenor;
	}

	/**
	 * @param coefProdfzaAlPasarACambioMenor the coefProdfzaAlPasarACambioMenor to set
	*/
	public void setCoefProdFzaAlPasarACambioMenor(double coefProdFzaAlPasarACambioMenor) {
		this.coefProdFzaAlPasarACambioMenor = coefProdFzaAlPasarACambioMenor;
	}
}