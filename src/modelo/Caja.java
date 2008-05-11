package modelo;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * @Documentacion
 * Esta clase modela la caja de velocidades de un vehiculo. Puede tener una cantidad de cambios 
 * determinada, que una vez creada no puede ser cambiada. Cada cambio tiene una relaci�n de velocidad
 * o de cambio, que es un factor n�merico el cual determinara la forma en la que caja entrega potencia
 * de a cuerdo a encontrarse en un cambio dado. Se toma como convenci�n que a mayor n�mero de cambio
 * menor relacionDeCambio. Esta clase es suceptible de ser afectada por la temperatura del clima.
 * @Nota1: La mayoria de los m�todos retornan una exception de tipo ExceptionCambioNoValido al
 * intentar acceder a un cambio que no existe.
 * @Nota2: Al pasar de cambio la caja disminuye en un porcentaje la cantidad de rpm del
 * motor del auto.
 * @version	3.2
 */
public abstract class Caja extends Componente implements AfectablePorClima{
		
	protected int cambio;
		
	protected double[] relacionDeCambio;//
		
	protected int cantidadCambios;
	
	protected final static long TIEMPO_MINIMO_ENTRE_DESGASTES=3000;//tiempo minimo transurrido entre
															       //dos desgastes consecutivos
	protected final static double COEFICIENTE_DE_OBTENCION_DE_POTENCIA_A_PARTIR_RPM=0.015;
	
	protected final static double COEFICIENTE_DE_DESGASTE_POR_TEMPERATURA_INICIAL=0.000000001;
	
	protected final static double COEFICIENTE_DE_OBTENCION_DE_TEMPERATURA_SEGUN_RPM_INICIAL=0.00000001;
		
	protected double TEMPERATURA_AIRE_OPTIMA=18;
	
	protected double TEMPERATURA_CAJA_OPTIMA=90;
	
	protected double coeficienteDeDesgastePorTemperatura;
	
	protected double coeficienteDeObtencionDeTemperaturaSegunRpm;
		
	protected long tiempoDeUltimoDesgaste;//tiempo del ultimo desgaste en milisegundos
	
	public abstract void Chequear();
	
	
		
	/**
	 * @Pre:
	 * @Post: Se ha creado una instancia de la clase derivada de la clase Caja segun los parametros
	 * detallados a continuaci�n.
	 * @param auto: auto que contiene a 
	 * la instancia.  
	 * @param cantidadCambios: cantidad de cambios que posee la caja, sin contar la reversa que en 
	 * esta implementaci�n no existe y punto muerto. Debe entre 4 y 8.
	*/
	public Caja(Auto auto, int cantidadCambios){
		this.cantidadCambios=cantidadCambios;
		relacionDeCambio=new double[cantidadCambios+1];
		setAuto(auto);
		cambio=0;
		generarRelacionesDeCaja();
		setEstado(100);
		setTiempoDeUltimoDesgaste(System.currentTimeMillis());
		setTemperatura(TEMPERATURA_CAJA_OPTIMA);
		setCoeficienteDeDesgastePorTemperatura(COEFICIENTE_DE_DESGASTE_POR_TEMPERATURA_INICIAL);
		setCoeficienteDeObtencionDeTemperaturaSegunRpm(COEFICIENTE_DE_OBTENCION_DE_TEMPERATURA_SEGUN_RPM_INICIAL);
	}
	
	/**
	 * @Pre: 
	 * @Post: Se han generado las relaciones de caja correspondientes.
	 * 
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
	 * @param cambio: cambio que se desea validad.
	*/
	protected void setCambio(int cambio){
		if(cambioValido(cambio)){
		 if(cambio!=getCambio()){  
		   Motor motor=getAuto().getMotor();
		   this.cambio=cambio;
		   this.desgastar();
		   double relacionRpm=Math.abs(obtenerRpm()/motor.getRevolucionesMaximas()-1); 
		   double rpm=motor.getRevolucionesMaximas()*relacionRpm;
		   //cambio de revoluciones actuales
		   if(rpm<motor.getRevolucionesMinimasEncendido())
			   rpm=motor.getRevolucionesMinimasEncendido();
		   motor.modificarRpmDesdeCaja(rpm-motor.getRPM());
		   //cambio de revoluciones Maximas del motor segun el cambio
		   rpm=motor.getRevolucionesMaximas()*(1-1/getRelacionDeCambio());
		   motor.setRevolucionesMaximasCambio(rpm);
		 }//fin cambio!=cambio instancia
		}//fin cambio valido
	}
		
	protected void actualizarTemperatura(){
		double varicacionTemperatura=obtenerRpm()*getCoeficienteDeObtencionDeTemperaturaSegunRpm();
		setTemperatura(getTemperatura()+varicacionTemperatura);
	}
	
	/**
	 * @Pre: La instancia de la clase derivada de Caja ha sido creada.
	 * @Post: Se retorna la cantidad de cambios que tiene la instancia.
	*/
	public int getCantidadCambios(){
		return(cantidadCambios);
	}

	/**
	 * @Pre: La instancia de la clase derivada de Caja ha sido creada.
	 * @Post: Se retorna la cantidad de RPM de la instancia para le cambio actual.
	*/
	public double obtenerRpm() {
		return (getAuto().getMotor().getRPM()/getRelacionDeCambio());
	}

	/**
	 * @Pre: Se ha creado una instancia de la Automatica segun los parametros.
	 * @Post: Se ha obtenido la potencia entregada por la caja a una cantidad
	 * de Rpm y cambios dado.
	 * 
	*/
	public double obtenerPotencia(){
		
		double potencia=0;
		
		for(int cursor=0;cursor<getCambio();cursor++)	
			potencia = potencia+getAuto().getMotor().getRevolucionesMaximas()*
			COEFICIENTE_DE_OBTENCION_DE_POTENCIA_A_PARTIR_RPM/relacionDeCambio[cursor];
		
		potencia = potencia+obtenerRpm()*COEFICIENTE_DE_OBTENCION_DE_POTENCIA_A_PARTIR_RPM;
		
		return potencia;
	}
	
	/**
	 * @return the tiempoDeUltimoDesgaste
	 */
	protected long getTiempoDeUltimoDesgaste() {
		return tiempoDeUltimoDesgaste;
	}

	/**
	 * @param tiempoDeUltimoDesgaste the tiempoDeUltimoDesgaste to set
	 */
	protected void setTiempoDeUltimoDesgaste(long tiempoDeUltimoDesgaste) {
		this.tiempoDeUltimoDesgaste = tiempoDeUltimoDesgaste;
	}

	/**
	 * @return the coeficienteDeDesgastePorTemperatura
	 */
	protected double getCoeficienteDeDesgastePorTemperatura() {
		return coeficienteDeDesgastePorTemperatura;
	}

	/**
	 * @param coeficienteDeDesgastePorTemperatura the coeficienteDeDesgastePorTemperatura to set
	 */
	protected void setCoeficienteDeDesgastePorTemperatura(
			double coeficienteDeDesgastePorTemperatura) {
		this.coeficienteDeDesgastePorTemperatura = coeficienteDeDesgastePorTemperatura;
	}

	/**
	 * @return the coeficienteDeObtencionDeTemperaturaSegunRpm
	 */
	protected double getCoeficienteDeObtencionDeTemperaturaSegunRpm() {
		return coeficienteDeObtencionDeTemperaturaSegunRpm;
	}

	/**
	 * @param coeficienteDeObtencionDeTemperaturaSegunRpm the coeficienteDeObtencionDeTemperaturaSegunRpm to set
	 */
	protected void setCoeficienteDeObtencionDeTemperaturaSegunRpm(
			double coeficienteDeObtencionDeTemperaturaSegunRpm) {
		this.coeficienteDeObtencionDeTemperaturaSegunRpm = coeficienteDeObtencionDeTemperaturaSegunRpm;
	}
		
	/* (non-Javadoc)
	 * @see modelo.AfectablePorClima#afectar(modelo.Clima)
	 */
	@Override
	public void afectar(Clima clima) {
		double temperatura=clima.getTemperatura();
		double relacion=temperatura/TEMPERATURA_AIRE_OPTIMA-1;
		this.setCoeficienteDeObtencionDeTemperaturaSegunRpm(COEFICIENTE_DE_OBTENCION_DE_TEMPERATURA_SEGUN_RPM_INICIAL*(1-relacion));
	}
}