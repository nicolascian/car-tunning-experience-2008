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
 * o de cambio, que es un factor numerico el cual determinara la forma en la que caja entrega potencia
 * de a cuerdo a encontrarse en un cambio dado. Se toma como convencion que a mayor numero de cambio
 * menor relacionDeCambio. 
 * @Nota: Al pasar de cambio la caja disminuye en un porcentaje la cantidad de rpm del
 * motor del auto.
 * @version	3.2
 */
public abstract class Caja extends Componente implements ReceptorDeFuerzas{
		
	private int cambio=0;
	
	/**
	 * El embrague es un sistema que permite transmitir o no una energía mecánica a su acción final. 
	 * En un automóvil, por ejemplo, permite transmitir o no la potencia del motor a las ruedas.
	 */
	
	private double revolucionesMaximasMotorParaCambioActual=0;
	
	private double[] relacionDeCambio;//
		
	private int cantidadCambios;
	
	private RepositorioDeFuerzas repositorio;
		
	protected final static double COEFICIENTE_DE_DESGASTE=4;
			
	/**
	 * @Pre:
	 * @Post: Se ha creado una instancia de la clase derivada de la clase Caja segun los parametros
	 * detallados a continuacion.
	 * @param auto: auto que contiene a 
	 * la instancia.  
	 * @param cantidadCambios: cantidad de cambios que posee la caja, sin contar la reversa que en 
	 * esta implementacion no existe y punto muerto. Debe entre 4 y 8.
	 */
	public Caja(int cantidadCambios){
		this.cantidadCambios=cantidadCambios;
		relacionDeCambio=new double[cantidadCambios+1];
		generarRelacionesDeCaja();
		setEstado(100);
	}
	
	public void Chequear(){};
	
	/**
	 * @Pre: 
	 * @Post: Se han generado las relaciones de caja correspondientes.
	 */
	abstract protected void generarRelacionesDeCaja();
		
	/**
	 * @Pre: Se ha creado la instancia de la clase derivada de la clase Caja.
	 * @Post: Se ha validado el cambio pasado por parametro seg�n la codificaci�n siguiente.
	 * @param cambio: cambio que se desea validad.
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
	  
	  if(getCambio()!=0)
		if(fuerza.getEmisor()==getAuto().getMotor()){
			  //se pasa la mitad de la fuerza a cada eje
			  double valorDeLaFuerza=0;
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
			  //viene de alguno de los ejes
        	  double valorDeLaFuerza=0;
			  try{
			     valorDeLaFuerza=fuerza.getValorDeLaFuerza()/this.getRelacionDeCambio();
			  }catch (Exception e){}
			  //transmito fuerza al motor
			  Fuerza fuerzaAMotor=new Fuerza(this,getAuto().getMotor(),valorDeLaFuerza,
					                       true);
			  getAuto().getMotor().recibirFuerza(fuerzaAMotor);
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
	 * @Pre: La instancia ha sido creada.
	 * @Post:Se ha seteado el cambio. Cada vez que hacemos un Cambio, se altera las 
	 * revolucionesMaximas del Motor.
	 */
	public void setCambio(int cambio){
		if ((cambioValido(cambio))&&(cambio!=getCambio())&&(isEmbragado())){
		   //se calcula la fuerza que se debe ejercer al motor
		   double valorDeFuerza=0;
		   if(cambio>getCambio())
			  valorDeFuerza=getAuto().getMotor().getRPM()*(-0.1);
		   else
			  valorDeFuerza=getAuto().getMotor().getRPM()*(0.07);
		   //se pasa el cambio
		   this.cambio=cambio;
		   //se cambian las revoluciones maximas para el cambio actual
		   setRevolucionesMaximasMotorParaCambioActual(calcularRevolucionesMaximasMotorParaCambioActual());
		   //se pasa una fuerza motor 
		   Fuerza fuerza=new Fuerza(this,getAuto().getMotor(),valorDeFuerza,true);
		   this.getAuto().getMotor().recibirFuerza(fuerza);
		}//fin if
		if (!isEmbragado()) {this.desgastar();}
		
		ActualizarObservadores();
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
	
	public void siguiente(){}
	
	public void anterior(){}

	/**
	 * @Pre: La instancia de la clase derivada de Caja ha sido creada.
	 * @Post: Se retorna la cantidad de RPM de la instancia para le cambio actual.
	 */
	public double calcularRevolucionesMaximasMotorParaCambioActual() {
		return (getAuto().getMotor().getRPM()*(1-20*getRelacionDeCambio()/
			    (getAuto().getMotor().getRPM()+1)));
	}

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
		setEstado(getEstado()-tiempoPorCiclo*COEFICIENTE_DE_DESGASTE);
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
}