/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente;

/**
 * @Documentacion: Una instancia de la clase caja Automatica modela una caja automatica de un auto,
 * como tal efectua los cambios en forma automatizada de acuerdo a las revoluciones del motor,
 * siempre controlando si se esta acelerando o no.
 * 
 * @version	1.0
 */
public class Automatica extends Caja{

	private double velocidadMaximaParaCambioRegistrada[];
	
	private double velocidadMinimaParaCambioRegistrada[];
	
	protected final static double MAXIMA_VELOCIDAD_PARA_AUTO=1000;
	
	private int intentos=0;
	
	/**
	 * @Pre: La instancia de la clase Automatica ha sido creada, y su atributo auto tiene una instancia de la 
	 * clase Auto la cual es no nula y se encuentra listo para carrera.
	 * 
	 * @Post: Se podrucen los siguientes estados finales:
	 *  
	 *  1)Si se esta acelerando el motor, y las revoluciones alcanzan cierto nivel, 
	 *  la caja sube un cambio
	 *  
	 *  2) Si no se esta acelerando el motor, y las rpm bajan de cierto nivel,
	 *  la caja baja un cambio
	*/	
	public void Chequear(){
		  double rpm=getAuto().getMotor().getRPM();
		  Motor motor=getAuto().getMotor();
		  if(motor.isAcelerando()){
			if((rpm>=getRevolucionesMaximasMotorParaCambioActual())&&
			    chequearVelocidadMaxima()){	
				embragar(true);
				setCambio(getCambio()+1);
				embragar(false);
			}
		  }
		  else
			if((rpm<=getRevolucionesMinimasMotorParaCambioActual())
			    &&(chequearVelocidadMinima())){
				embragar(true);
				setCambio(getCambio()-1);
				embragar(false);
			}
	}	
		
	/**
	 * @Pre:
	 * @Post: Se ha creado una instancia de la clase Automatica segun los parametros detallados a 
	 * continuacion.
	 * @param cantidadCambios: cantidad de cambios que posee la caja. Debe entre 4 y 6 sin contar el
	 * punto muerto.En esta implementacion no existe la reverza.
	*/
	public Automatica(int cantidadCambios){
		super(cantidadCambios);
		setPeso(80);
		this.velocidadMaximaParaCambioRegistrada=new double[getCantidadCambios()+1];
		for(int cursor=0;cursor<getCantidadCambios();cursor++)
			velocidadMaximaParaCambioRegistrada[cursor]=0;
		this.velocidadMinimaParaCambioRegistrada=new double[getCantidadCambios()+1];
		for(int cursor=0;cursor<getCantidadCambios();cursor++)
			velocidadMinimaParaCambioRegistrada[cursor]=0;
	}
	
	protected boolean chequearVelocidadMaxima(){
		if((intentos<getAuto().getMotor().getRevolucionesMaximas()*0.1)
		    &&(getAuto().getVelocidad()<velocidadMaximaParaCambioRegistrada[getCambio()])){
			intentos++;
			return false;
		}
		else{
			registrarVelocidadMaxima();
			intentos=0;
		    return true;
		}
	}
	
	protected boolean chequearVelocidadMinima(){
		if((intentos<getAuto().getMotor().getRevolucionesMaximas()*0.1)
		  &&(getAuto().getVelocidad()>velocidadMinimaParaCambioRegistrada[getCambio()])){
				intentos++;
				return false;
		}
		else{
				registrarVelocidadMinima();
				intentos=0;
			    return true;
		}
	}
	
	/**
	 * @Pre: 
	 * @Post: Se han generado las relaciones de caja correspondientes.
	*/
	protected void generarRelacionesDeCaja(){
		for(int cursor=0;cursor<=getCantidadCambios();cursor++)
		  if(cursor!=0)	   
			setRelacionDeCambio(cursor,15.0/(cursor-0.5)-9.0/(getCantidadCambios()*getCantidadCambios()));
		  else
			setRelacionDeCambio(cursor,60.0);  
	}
	
	protected void registrarVelocidadMaxima(){
		this.velocidadMaximaParaCambioRegistrada[getCambio()]=getAuto().getVelocidad();
		if(cambioValido(getCambio()+1))
		  velocidadMinimaParaCambioRegistrada[getCambio()+1]=getAuto().getVelocidad();
	}
	
	protected void registrarVelocidadMinima(){
		this.velocidadMinimaParaCambioRegistrada[getCambio()]=getAuto().getVelocidad();
	}
	
	public void print(){
		System.out.println("maximas");
		for(int cursor=0;cursor<=getCantidadCambios();cursor++)
			System.out.println(velocidadMaximaParaCambioRegistrada[cursor]);
		System.out.println("minimas");
		for(int cursor=0;cursor<=getCantidadCambios();cursor++)
			System.out.println(velocidadMinimaParaCambioRegistrada[cursor]);
	}
}