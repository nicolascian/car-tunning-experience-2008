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
	
	public void EjeDeTransmision(Auto auto){
		//inicializacion de atributos
		this.setRepositorio(new RepositorioDeFuerzas(this));
		this.setAuto(auto);
		this.setEstado(100);
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
		   	this.getRepositorio().insertarFuerza(fuerza);
		   	this.getAuto().getCaja().recibirFuerza(fuerza);
		}else
			if(fuerza.getEmisor()==this.getAuto().getCaja()){
				this.getRepositorio().insertarFuerza(fuerza);
				this.getAuto().getMotor().recibirFuerza(fuerza);
			}
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
}
