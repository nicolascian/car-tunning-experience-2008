/**
 * 
 */
package modelo;

/**
 * @Documentacion: 
 *  			  Modela el eje de transmision de un Auto. Una instancia de esta clase
 *  es un intermediario entre una instancia de la clase Motor y una instancia de la 
 *  clase Caja, ambas correspondientes a la misma instancia de Auto. 
 *  
 *
 */
public class EjeDeTransmision extends Componente implements ReceptorDeFuerzas {

	private RepositorioDeFuerzas repositorio;
	
	private double rpm;
		
	protected double COEFICIENTE_OBTENCION_RPM=0.001;//revoluciones del eje
	
	//private double COEFICIENTE_DE_OBTENCION_DE_
	
	public EjeDeTransmision(Auto auto){
		//inicializacion de atributos
		this.setRepositorio(new RepositorioDeFuerzas(this));
		this.setAuto(auto);
		this.setEstado(100);
		this.setRpm(0);
		this.setPeso(5);
	}
	
	/* (non-Javadoc)
	 * @see modelo.Componente#desgastar()
	 */
	@Override
	public void desgastar() {
		
	}

	/* (non-Javadoc)
	 * @see modelo.Componente#obtenerPotencia()
	 */
	@Override
	public double obtenerPotencia() {
		
		return 0;
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
		if(fuerza.getEmisor()==this.getAuto().getMotor()){
		  try{ 	
			//se inserta en el repositorio y se pasa una copia de la fuerz a la Caja
			Fuerza fuerzaACaja=this.getRepositorio().insertarFuerzaRetornarCopia(fuerza);
			fuerzaACaja.setEmisor(this);
			fuerzaACaja.setReceptor(this.getAuto().getCaja());
			this.getAuto().getCaja().recibirFuerza(fuerzaACaja);
			actualizarRpm();
		  }catch(Exception e){}; 	
		}else
			if(fuerza.getEmisor()==this.getAuto().getCaja()){
			  try{
				//se inserta en el repositorio y se pasa una copia de la fuerz al motor
				Fuerza fuerzaAMotor=this.getRepositorio().insertarFuerzaRetornarCopia(fuerza);
				fuerzaAMotor.setEmisor(this);
				fuerzaAMotor.setReceptor(this.getAuto().getMotor());
				this.getAuto().getMotor().recibirFuerza(fuerzaAMotor);
				actualizarRpm();
			  }catch(Exception e){};
			}
	}

	private void actualizarRpm(){
		this.setRpm(this.getRpm()+this.getRepositorio().obtenerValorSumatoriaDeFuezas()*COEFICIENTE_OBTENCION_RPM);
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
	protected void setRepositorio(RepositorioDeFuerzas repositorio) {
		this.repositorio = repositorio;
	}

	/**
	 * @return the rpm
	 */
	public double getRpm() {
		return rpm;
	}

	/**
	 * @param rpm the rpm to set
	 */
	protected void setRpm(double rpm) {
		this.rpm = rpm;
	}
			
}
