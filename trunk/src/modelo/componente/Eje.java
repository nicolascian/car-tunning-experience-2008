/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente;
import modelo.*;
import modelo.fuerzas.*;

/**
 * Una instancia de esta clase modela el eje delantero o trasero de un vehiculo
 * Un eje gira con una determinada cantidad de revoluciones las cuales afectan el desplazamiento del
 * vehiculo y las fuerzas sobre la caja, llantas, neumaticos y carroceria.
 * Son afectados por y desgastados por la superficie. * 
 * @version 1.0
 */
public class Eje extends Componente implements AfectablePorSuperficie,ReceptorDeFuerzas{ 
	
	/* Atributos de la clase */
	private Llanta LlantaDerecha;
	private Llanta LlantaIzquierda;
	private double DesgastePorRugosidad;
	private double DesgastePorParticulas;
	private RepositorioDeFuerzas repositorio;//donde se almacenan las fuerzas que llegan a la isntancia
	private double rpm;//revoluciones a las que gira la instancia.
	protected final static double COEFICIENTE_INCREMENTO_RPM=0.006999;
	protected final static double COEFICIENTE_DECREMENTO_RPM=0.02219;	
	
	/**
	 * @Pre: 
	 * @Post: Se ha creado la instaica de la clase Eje inicializandola con la instancia de auto pasada
	 * por parametro, con su estado al 100% .
	 * @param auto
	*/
	
	public Eje(Auto auto){
		setPeso(50);
		setAuto(auto);
		setEstado(100);
		repositorio=new RepositorioDeFuerzas(this);
		setLlantaDerecha(null);
		setLlantaIzquierda(null);
		this.rpm = 0;
		this.DesgastePorParticulas = 0 ;
		this.DesgastePorRugosidad = 0;
	}
	
	/**
	 * @Pre: 
	 * @Post: Se ha creado la instaica de la clase Eje inicializandola con la instancia
	 * de auto y con llantas pasadas por parametro, con su estado al 100%.
	 * @param auto
	*/
	public Eje(Auto auto, Llanta LlantaDer, Llanta LlantaIzq){
		setPeso(50);
		setAuto(auto);
		setEstado(100);
		repositorio=new RepositorioDeFuerzas(this);
		setLlantaDerecha(LlantaDer);
		setLlantaIzquierda(LlantaIzq);
		this.rpm = 0;
		this.DesgastePorParticulas = 0 ;
		this.DesgastePorRugosidad = 0;
	}
	
	/**
	 * @Pre: La instancia de la clase Auto pasada por parametro ha sido creda.
	 * @Post: Se ha instalado la instancia en la instancia de la clase Auto pasada por parametro.
	 */
	public void instalar(Auto auto){
		setAuto(auto);
	}
	
	/**
	 * @Pre: La instancia de la clase Auto pasada por parametro ha sido creda.
	 * @Post: Se ha instalado la instancia en la instancia de la clase Auto pasada por parametro.
	 */
	public void instalar(Auto auto,Llanta llantaDerecha,Llanta llantaIzquierda){
		setAuto(auto);
		this.setLlantaDerecha(llantaDerecha);
		this.setLlantaIzquierda(llantaIzquierda);
		try{
			getLlantaDerecha().setEje(this);
			getLlantaIzquierda().setEje(this);
		}catch(NullPointerException e){}
	}
	
	/**
	 * @Pre:
	 * @Post: Retorna el valor de potencia generada por el eje.
	*/
	public double obtenerPotencia() {
		return ((5*this.getEstado()/100));
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
		this.setEstado(super.getEstado()- desgaste);
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

	/**
	 * @Pre: La instacia de la Clase Llanta pasada por parametro ha sido creda.
	 * @Post: Se ha instalado la instancia pasada por parametro en esta instancia.
	 * @param llantaDerecha
	*/
	public void setLlantaDerecha(Llanta llantaDerecha) {
	  LlantaDerecha = llantaDerecha;  
	}

	public Llanta getLlantaIzquierda() {
		return LlantaIzquierda;
	}

	/**
	 * @Pre: La instacia de la Clase Llanta pasada por parametro ha sido creda.
	 * @Post: Se ha instalado la instancia pasada por parametro en esta instancia.
	 * @param llantaDerecha
	*/
	public void setLlantaIzquierda(Llanta llantaIzquierda) {
	  LlantaIzquierda = llantaIzquierda;
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
	  try{	
		if(fuerza.getEmisor()==this.getAuto().getCaja()){
			  //viene de la caja
			  //envio una fuerza nula a la carroceria para que se actualice la fueza que ejerce sobre el eje
			  getAuto().getCarroceria().recibirFuerza(new Fuerza(this,getAuto().getCarroceria(),0,true));
			  double valorDeLaFuerza=0;
			  //inserto copia de la fuerza en el repositorio de fuerzas
			  Fuerza fuerzaCopia=repositorio.insertarFuerzaRetornarCopia(fuerza);
			  try{
			     valorDeLaFuerza=fuerzaCopia.getValorDeLaFuerza();
			  }catch (Exception e){}
			  //obtengo la sumatoria de fuerza del repositorio proveniente de la carroceria
			  valorDeLaFuerza+=repositorio.obtenerValorSumatoriaDeFuerzas(getAuto().getCarroceria());
			  //transmito lo que queda de la fuerza a la llanta derecha
			  Fuerza fuerzaALlantaDer=new Fuerza(this,getLlantaDerecha(),valorDeLaFuerza/2,true);
			  this.getLlantaDerecha().recibirFuerza(fuerzaALlantaDer);
			  //transmito fuerza a llanta izquierda			  			
			  Fuerza fuerzaALlantaIzq=new Fuerza(this,getLlantaIzquierda(),valorDeLaFuerza/2,true);
			  this.getLlantaIzquierda().recibirFuerza(fuerzaALlantaIzq);
			  //obtendo la sumatorio total de fuerzas sobre el eje
			  valorDeLaFuerza=repositorio.obtenerValorSumatoriaDeFuerzas();
			  //actualizo las rpm del eje
			  if(valorDeLaFuerza>0)
			    setRpm(getRpm()+valorDeLaFuerza*COEFICIENTE_INCREMENTO_RPM);
			  else
				  setRpm(getRpm()+valorDeLaFuerza*COEFICIENTE_DECREMENTO_RPM);  
		}else{//viene de la carroceria
			  if(fuerza.getEmisor()==getAuto().getCarroceria()){
				Fuerza fuerzaACaja=repositorio.insertarFuerzaRetornarCopia(fuerza);
				//envio una copia de la fuerza a la caja
				((ReceptorDeFuerzas)getAuto().getCaja()).recibirFuerza(fuerzaACaja);
			  }
			  else{
				  //llanta derecha o izquierda
				  //inserto en el repositorio
				  Fuerza fuerzaACaja=repositorio.insertarFuerzaRetornarCopia(fuerza);
				  //envio una copia de la fuerza a la caja
				  fuerzaACaja.setEmisor(this);
				  fuerzaACaja.setReceptor(getAuto().getCaja());
				  fuerzaACaja.limitarAcceso();
				  ((ReceptorDeFuerzas)getAuto().getCaja()).recibirFuerza(fuerzaACaja);				  
			  }
		}
	  }catch(NullPointerException e){}
	}
	
	public double getPeso(){
		return peso;
	}
	/**
	 * @Pre: La instancia ha sido creada.
	 * @Post: Se ha retornado la instancia expresada como una cadena de caracteres.
	*/
	public String toString(){
		String cadena = this.getNombre()+", Estado: "+this.getEstado()+" %.";
		return cadena;
	}
	public String getNombre(){
		return "Eje";
	}
	
	/* (non-Javadoc)
	 * @see modelo.componente.Componente#getEstado()
	 */
	@Override
	public double getEstado() {
		return(super.getEstado());
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
	public void setRpm(double rpm) {
	  double rpmFinal;
	  if(rpm<0)	
		rpmFinal=0;
	  else
		rpmFinal=rpm;
	  this.rpm=rpmFinal;
	}
	
}