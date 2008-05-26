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
	private double DesgastePorRugosidad;
	private double DesgastePorParticulas;
	private RepositorioDeFuerzas repositorio;	
	private double rpm=0;
	protected final static double COEFICIENTE_OBTENCION_RPM=0.004006;
		
	/*Constructor,inicia estado de eje en 100*/
	public Eje(Auto auto){
		setPeso(50);
		setAuto(auto);
		setEstado(100);
		repositorio=new RepositorioDeFuerzas(this);
		setLlantaDerecha(new Llanta());
		setLlantaIzquierda(new Llanta());
		instalar(getAuto());
	}
	
	public void instalar(Auto auto){
		setAuto(auto);
		try{
			getLlantaDerecha().instalar(auto,this);
		}catch(NullPointerException e){}
		try{
			getLlantaIzquierda().instalar(auto,this);
		}catch(NullPointerException e){}
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
		return ((5*this.getEstado()/100)+ LlantaDerecha.obtenerPotencia()+ LlantaIzquierda.obtenerPotencia());
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
		llantaDerecha.instalar(getAuto(),this);
		LlantaDerecha = llantaDerecha;
	}

	public Llanta getLlantaIzquierda() {
		return LlantaIzquierda;
	}

	public void setLlantaIzquierda(Llanta llantaIzquierda) {
		llantaIzquierda.instalar(this.getAuto(),this);
		LlantaIzquierda = llantaIzquierda;
	}

	public Neumatico getNeumaticoDerecho() {
		return getLlantaDerecha().getNeumatico();
	}

	public void setNeumaticoDerecho(Neumatico neumaticoDerecho) {
		neumaticoDerecho.instalar(this.getAuto(),getLlantaDerecha());
		getLlantaDerecha().setNeumatico(neumaticoDerecho);
	}

	public Neumatico getNeumaticoIzquierdo() {
		return getLlantaIzquierda().getNeumatico();
	}

	public void setNeumaticoIzquierdo(Neumatico neumaticoIzquierdo) {
		neumaticoIzquierdo.instalar(this.getAuto(),getLlantaIzquierda());
		getLlantaIzquierda().setNeumatico(neumaticoIzquierdo);
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
			  setRpm(getRpm()+valorDeLaFuerza*COEFICIENTE_OBTENCION_RPM);
		}else{//viene de la carroceria
			  if(fuerza.getEmisor()==getAuto().getCarroceria()){
				Fuerza fuerzaACaja=fuerzaACaja=repositorio.insertarFuerzaRetornarCopia(fuerza);
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
	}

	public double getPeso(){
		double pesoTotal=this.peso;
		try{
			pesoTotal+=getLlantaDerecha().getPeso();
		}catch (NullPointerException e){}
		try{
			pesoTotal+=getLlantaIzquierda().getPeso();
		}catch (NullPointerException e){}
		return pesoTotal;
	}
	
	public String toString(){
		String cadena = this.getNombre()+", Estado: "+this.getEstado()+" %.";
		return cadena;
	}
	public String getNombre(){
		return "Eje";
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
		this.rpm = rpm;
	}
}