/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

/**
 * Los eje del auto contiene las ruedas las cual estan
 * compuestas por llantas y neumaticos.
 * 
 * Son afectados por y desgastados por la superficie.
 * 
 * @version 1.0
 */
public class Eje extends Componente implements AfectablePorSuperficie,ReceptorDeFuerzas{ 
	
	/* Atributos de la clase */
	private Llanta LlantaDerecha;
	private Llanta LlantaIzquierda;
	private Neumatico NeumaticoDerecho;
	private Neumatico NeumaticoIzquierdo;
	private double DesgastePorRugosidad;
	private double DesgastePorParticulas;
	private RepositorioDeFuerzas repositorio;	
	/*Constructor,inicia estado de eje en 100*/
	public Eje(){
		this.setEstado(100);
		repositorio=new RepositorioDeFuerzas(this);
	}
	
	/**
	 * @Pre: Potencias de las ruedas y neumaticos
	 * @Post: Retorna el valor de potencia generada por
	 * el eje.
	 * @Documentacion: El valor es la suma de la potencia generada por los ejes
	 * las dos llantas y los dos neumaticos que componene cada eje.
	 * El mejor valor de potencia que puede devolver un eje es de 5 watts.
	 */
	public double obtenerPotencia() {
		return ((5*this.getEstado()/100)+ LlantaDerecha.obtenerPotencia()+ LlantaIzquierda.obtenerPotencia() + NeumaticoDerecho.obtenerPotencia()+ NeumaticoIzquierdo.obtenerPotencia());
	}
	/**
	 * @Post: Eje desgastado, modificacion del estado del eje.
	 * @Documentacion: Metodo que se encarga de modificar el estado de el eje
	 * teniendo en cuenta que este se ira arruinando por la
	 * rugosidad que presenta la superficie y las particulas 
	 * sueltas que presenta dicha superficie que da�an al eje.
	 */
	public void desgastar(){
		double desgaste=0;
		desgaste = ((this.getDesgastePorRugosidad()/100) + (this.getDesgastePorParticulas())/10000)*Constantes.tiempoPorCiclo;
		this.setEstado(this.getEstado()- desgaste);
	}
	/**
	 * @Post: Atributos modificados por la dependencia del tipo de pista.
	 * @Documentacion: Metodo que modifica el valor producido por cada desgaste
	 * que provoca la superficie al eje.
	 */
	public void afectar(Superficie superficie){
		this.setDesgastePorParticulas(superficie.getParticulasSueltas());
		this.setDesgastePorRugosidad(superficie.getRugosidad());
	}

	/* getters y setters*/

	public double getDesgastePorRugosidad() {
		return DesgastePorRugosidad;
	}

	public void setDesgastePorRugosidad(double desgastePorRugosidad) {
		DesgastePorRugosidad = desgastePorRugosidad;
	}

	public double getDesgastePorParticulas() {
		return DesgastePorParticulas;
	}

	public void setDesgastePorParticulas(double desgastePorParticulas) {
		DesgastePorParticulas = desgastePorParticulas;
	}

	public Llanta getLlantaDerecha() {
		return LlantaDerecha;
	}

	public void setLlantaDerecha(Llanta llantaDerecha) {
		llantaDerecha.instalar(this.getAuto());
		LlantaDerecha = llantaDerecha;
	}

	public Llanta getLlantaIzquierda() {
		return LlantaIzquierda;
	}

	public void setLlantaIzquierda(Llanta llantaIzquierda) {
		llantaIzquierda.instalar(this.getAuto());
		LlantaIzquierda = llantaIzquierda;
	}

	public Neumatico getNeumaticoDerecho() {
		return NeumaticoDerecho;
	}

	public void setNeumaticoDerecho(Neumatico neumaticoDerecho) {
		neumaticoDerecho.instalar(this.getAuto());
		NeumaticoDerecho = neumaticoDerecho;
	}

	public Neumatico getNeumaticoIzquierdo() {
		return NeumaticoIzquierdo;
	}

	public void setNeumaticoIzquierdo(Neumatico neumaticoIzquierdo) {
		neumaticoIzquierdo.instalar(this.getAuto());
		NeumaticoIzquierdo = neumaticoIzquierdo;
	}
	
	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#liberarFuerzas()
	 */
	@Override
	public void liberarFuerzas() {
		repositorio.vaciar();
		
	}

	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#recibirFuerza(modelo.Fuerza)
	 */
	@Override
	public void recibirFuerza(Fuerza fuerza) {
		if(fuerza.getEmisor()==this.getAuto().getEjeDeTransmision()){
			  //viene del eje de transmision
			  double valorDeLaFuerza=0;
			  try{
			     valorDeLaFuerza=fuerza.getValorDeLaFuerza();
			  }catch (Exception e){}
			  //transmito fuerza a llanta derecha
			  Fuerza fuerzaALlantaDer=new Fuerza(this,getLlantaDerecha(),valorDeLaFuerza/2,true);
			  this.getLlantaDerecha().recibirFuerza(fuerzaALlantaDer);
			  //transmito fuerza a llanta izquierda			  			
			  Fuerza fuerzaALlantaIzq=new Fuerza(this,getLlantaIzquierda(),valorDeLaFuerza/2,true);
			  this.getLlantaIzquierda().recibirFuerza(fuerzaALlantaIzq);  	 	
		}else{//viene de la carroceria
			  if(fuerza.getEmisor()==getAuto().getCarroceria()){
				try{ 
				  repositorio.insertarFuerza(new Fuerza(fuerza.getEmisor(),fuerza.getReceptor(),
						  					fuerza.getValorDeLaFuerza(),false));
				}catch (Exception e){}
			  }
			  else{//llanta derecha o izquierda
				  //envio una fueza nula a la carroceria
				  getAuto().getCarroceria().recibirFuerza(new Fuerza(this,getAuto().getCarroceria(),0,
						                                  true));
				  //obtengo del repositorio el total de fuerzas que provienen de la carroceria
				  double totalFuerzas=repositorio.obtenerValorSumatoriaDeFuerzas(getAuto().getCarroceria());
				  double valorDeLaFuerza=0;
				  try{
				     valorDeLaFuerza=fuerza.getValorDeLaFuerza();
				  }catch (Exception e){}
				  Fuerza fuerzaAux=new Fuerza(this,getAuto().getEjeDeTransmision(),
		  					                  valorDeLaFuerza+totalFuerzas,true);
				  getAuto().getEjeDeTransmision().recibirFuerza(fuerzaAux);
			  }
		}
	}

	public String toString(){
		String cadena = this.getNombre()+", Estado: "+this.getEstado()+" %.";
		return cadena;
	}
	public String getNombre(){
		return "Eje";
	}
	
}
