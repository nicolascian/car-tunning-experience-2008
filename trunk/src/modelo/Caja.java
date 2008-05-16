/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

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
public abstract class Caja extends Componente{
		
	protected int cambio;
	
	protected boolean embragado = false;
		
	protected double[] relacionDeCambio;//
		
	protected int cantidadCambios;
	
	protected final static double COEFICIENTE_DE_OBTENCION_DE_POTENCIA_A_PARTIR_RPM=0.015;
	
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
		cambio=0;
		generarRelacionesDeCaja();
		setEstado(100);
	}
	
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
	protected void setCambio(int cambio){
		if ((cambioValido(cambio))&&(cambio!=getCambio())&&(isEmbragado())){
	
		   Motor motor=getAuto().getMotor();
		   this.cambio=cambio;
		   this.desgastar();
		   double rpm=obtenerRpm();
		   motor.modificarRpmDesdeCaja();
		   //cambio de revoluciones Maximas del motor segun el cambio
		   rpm=motor.getRevolucionesMaximas();
		   motor.setRevolucionesMaximasCambio(rpm*(1-40*getRelacionDeCambio()/rpm));
		 
		}//fin if
		
		if (!isEmbragado()) {this.desgastar();}
	}
		
	/**
	 * @Pre: La instancia de la clase derivada de Caja ha sido creada.
	 * @Post: Se retorna la cantidad de cambios que tiene la instancia.
	*/
	public int getCantidadCambios(){
		return(cantidadCambios);
	}
	
	/* para hacer uso del polimorfismo
	 * estos metodos los usa la caja
	 * heredada Automatica */
	public void Chequear(){}
	
	public void siguiente(){}
	
	public void anterior(){}

	/**
	 * @Pre: La instancia de la clase derivada de Caja ha sido creada.
	 * @Post: Se retorna la cantidad de RPM de la instancia para le cambio actual.
	*/
	public double obtenerRpm() {
	  if(getAuto()!=null){	
		double rpm=getAuto().getMotor().getRPM();
		return (rpm*(1-20*getRelacionDeCambio()/(rpm+1)));
	  }else
		return 0;  
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
			COEFICIENTE_DE_OBTENCION_DE_POTENCIA_A_PARTIR_RPM/(relacionDeCambio[cursor]+1);
		
		potencia = potencia+obtenerRpm()*COEFICIENTE_DE_OBTENCION_DE_POTENCIA_A_PARTIR_RPM;
		
		return potencia;
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
    		this.setEmbragado(valor);
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Caja.
	 * @Post: Se retorna true en caso de que la instancia se encuentre embragado.
	*/
	public boolean isEmbragado(){
		   return this.getEmbragado();
	}

	public void setEmbragado(boolean embragado) {
		this.embragado = embragado;
	}
		
	public boolean getEmbragado() {
		return this.embragado;
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


		
}