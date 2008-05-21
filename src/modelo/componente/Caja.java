/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente;
import modelo.*;

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
		
	protected int cambio;
	
	/**
	 * El embrague es un sistema que permite transmitir o no una energía mecánica a su acción final. 
	 * En un automóvil, por ejemplo, permite transmitir o no la potencia del motor a las ruedas.
	 */

		
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
	  if(this.getCambio()!=0){
		if(fuerza.getEmisor()==getAuto().getEjeDeTransmision()){
			  //se pasa la mitad de la fuerza a cada eje
			  double valorDeLaFuerza=0;
			  try{
			     valorDeLaFuerza=fuerza.getValorDeLaFuerza();
			  }catch (Exception e){}
			  //transmito fuerza a eje delantero
			  Fuerza fuerzaAEje=new Fuerza(this,getAuto().getEjeDelantero(),valorDeLaFuerza/2,true);
			  getAuto().getEjeDelantero().recibirFuerza(fuerzaAEje);
			  //transmito fuerza a eje trasero			  			
			  fuerzaAEje=new Fuerza(this,getAuto().getEjeTrasero(),valorDeLaFuerza/2,true);
			  this.getAuto().getEjeTrasero().recibirFuerza(fuerzaAEje);  	 
		}else{
              //viene de alguno de los ejes
        	  double valorDeLaFuerza=0;
			  try{
			     valorDeLaFuerza=fuerza.getValorDeLaFuerza()/this.getRelacionDeCambio();
			  }catch (Exception e){}
			  //transmito fuerza al ejeDeTransmision 
			  Fuerza fuerzaAEje=new Fuerza(this,getAuto().getEjeDeTransmision(),valorDeLaFuerza,true);
			  getAuto().getEjeDeTransmision().recibirFuerza(fuerzaAEje);
		}
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
	protected void setCambio(int cambio){
		if ((cambioValido(cambio))&&(cambio!=getCambio())&&(isEmbragado())){
		   /*
		    * setear el cambio
		    * cambiar rpm maximas para el cambio internas
		    * si se pasa a un cambio menor pasar una tension muy grande al motor
		    * 
		    */
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
		
}