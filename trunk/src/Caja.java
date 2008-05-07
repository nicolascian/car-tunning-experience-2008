/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * @Documentacion
 * Esta clase modela la caja de velocidades de un vehiculo. Puede tener una cantidad de cambios 
 * determinada, que una vez creada no puede ser cambiada. Cada cambio tiene una relación de velocidad
 * o de cambio, que es un factor númerico el cual determinara la forma en la que caja entrega potencia
 * de a cuerdo a encontrarse en un cambio dado. Se toma como convención que a mayor número de cambio
 * menor relacionDeCambio. Esta clase es suceptible de ser afectada por la temperatura del clima.
 * @Nota1: La mayoria de los métodos retornan una exception de tipo ExceptionCambioNoValido al
 * intentar acceder a un cambio que no existe.
 * @Nota2: Al pasar de cambio la caja disminuye en un porcentaje la cantidad de rpm del
 * motor del auto.
 * @version	3.2
 */
public abstract class Caja extends Componente{
		
	protected int cambio;
		
	protected double[] relacionDeCambio;//
	
	protected int cantidadCambios;
				
	public abstract void Chequear(double variacion);
	
	/**
	 * @Pre:
	 * @Post: Se ha creado una instancia de la clase derivada de la clase Caja segun los parametros
	 * detallados a continuación.
	 * @param auto: auto que contiene a la instancia.  
	 * @param cantidadCambios: cantidad de cambios que posee la caja, sin contar la reversa y punto
	 * muerto. Debe ser mayor que cuatro, y para trabajar en un entorno real se recomienda que sea
	 * menor o igual que 8.
	*/
	public Caja(Auto auto, int cantidadCambios){
		this.cantidadCambios=cantidadCambios;
		relacionDeCambio=new double[cantidadCambios];
		setAuto(auto);
		generarRelacionesDeCaja();
	}
	
	/**
	 * @Pre: 
	 * @Post: Se han generado las relaciones de caja correspondientes.
	 * 
	*/
	private void generarRelacionesDeCaja(){
		for(int cursor=1;cursor<=cantidadCambios;cursor++)
			   relacionDeCambio[cursor]=15/cursor-9/(cantidadCambios*cantidadCambios);
	}
		
	/**
	 * @Pre: Se ha creado la instancia de la clase derivada de la clase Caja.
	 * @Post: Se ha validado el cambio pasado por parametro según la codificación siguiente.
	 * @param cambio: cambio que se desea validad.
	 * @return "true" en caso de que el cambio sea valido y "false" en caso contrario.
	 * @throws ExceptionCambioNoValido en caso de que el cambio no sea valido.
	*/
	protected boolean cambioValido(int cambio) throws ExceptionCambioNoValido{
		if(cambio<=getCantidadCambios())	
		   return (true);
		else
		   throw new ExceptionCambioNoValido();
	}
	
	
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
	 * revolucionesMaximas del Motor
	 * @param cambio: cambio que se desea validad.
	 * @throws ExceptionCambioNoValido en caso de que el cambio no sea valido.
	*/
	public void setCambio(int cambio) throws ExceptionCambioNoValido {
		if(cambioValido(cambio)){
		 if(cambio!=getCambio()){  
		   if(getCambio()<cambio)
			 getAuto().getMotor().disminuirRpmDesdeCaja(77-cambio*30/getCantidadCambios());
		   else
			 getAuto().getMotor().disminuirRpmDesdeCaja(77-cambio*50/getCantidadCambios());
		   setCambio(cambio);	
		 }
		}
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
	
}