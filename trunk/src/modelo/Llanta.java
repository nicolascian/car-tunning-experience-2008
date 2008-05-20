/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

/**
 * Las llantas de un auto estan ligadas al eje del mismo,
 * se afectan directamente por la superficie y la humedad
 * 
 * @version	1.0
 */
public class Llanta extends Componente implements AfectablePorSuperficie, ReceptorDeFuerzas{
		
	// en hp
	private static double potenciaNormal=15;  
	
	//expresado en kg
	private double pesoNormal=25;
	
	// expresada en porcentaje}
	private double humedadOptima=50; 

	private static double constanteDeDesgaste=1;
	
	private double coeficienteDeDesgastePorSuperficie=1;
	
	private double peso;
	
	private Eje eje=null;
	
	private Neumatico neumatico;
	
	/**constructor, queda instanciada 
	 *  la clase Llanta.
	 */
	
	public Llanta(){
		setEstado(100);
		this.setPeso(25);
		this.setCoeficienteDeDesgastePorSuperficie(3);
	}
	
	/**
	 * constructor con parametros,
	 * queda instanciada la clase Llanta
	 * 
	 * @param pesoNormal
	 */
	public Llanta(double pesoNormal){
		setEstado(100);
		this.setPeso(25);
		this.setCoeficienteDeDesgastePorSuperficie(3);
	}
	
	/**
	 * Con el pasar del tiempo, las llantas,
	 * asi como todos los componentes del auto,
	 * se van desgastando, hasta que su uso se hace nulo.
	 * 
	 */
	public void desgastar(){
		setEstado(getEstado()-this.getCoeficienteDeDesgastePorSuperficie()*tiempoPorCiclo*constanteDeDesgaste);
	}
	
	/**
	 * Las llantas, al igual que otros componentes,
	 * rinden una determinada potencia, mientras mas livianas 
	 * son, mas potencia puede otorgar
	 */
	public double obtenerPotencia(){
		return (this.getPesoNormal()*potenciaNormal / this.getPeso());
	}
		
	/** 
	 * la superficie afecta a las llantas 
	 * 
	 */
	public void afectar(Superficie superficie){           
		double relacion;
		try{
		relacion= (this.getCoeficienteDeDesgastePorSuperficie()/(superficie.getParticulasSueltas()*superficie.getRugosidad()*superficie.getViscosidad()));
		}catch (Exception e){
		relacion=0.5;
		}
		this.setCoeficienteDeDesgastePorSuperficie(this.getCoeficienteDeDesgastePorSuperficie()+(this.getCoeficienteDeDesgastePorSuperficie()*Math.abs(1- relacion)));
	}
	
	/**
	 * le asigna un valor al pesoNormal
	 * 
	 * @param pesoNormal the pesoNormal to set
	 */
	public void setPesoNormal(double pesoNormal) {
		this.pesoNormal = pesoNormal;
	}
	/**
	 * nos da el pesoNormal
	 * @return the pesoNormal
	 */
	public double getPesoNormal() {
		return pesoNormal;
	}
	/**
	 * asigna un valor a coeficienteDeDesgastePorSuperficie
	 * 
	 * @param coeficienteDeDesgastePorSuperficie the coeficienteDeDesgastePorSuperficie to set
	 */
	public void setCoeficienteDeDesgastePorSuperficie(
			double coeficienteDeDesgastePorSuperficie) {
		this.coeficienteDeDesgastePorSuperficie = coeficienteDeDesgastePorSuperficie;
	}
	
	/**
	 * observo el estado de mi objeto
	 * mediante una cadena
	 */
	public String toString(){
		String cadena = this.getNombre()+", Estado: "+this.getEstado()+" %.";
		return cadena;
	}
	public String getNombre(){
		return "Llanta";
	}
	/**
	 * devuelve el coeficienteDeDesgastePorSuperficie
	 * @return the coeficienteDeDesgastePorSuperficie
	 */
	public double getCoeficienteDeDesgastePorSuperficie() {
		return coeficienteDeDesgastePorSuperficie;
	}
	public double getPeso(){
		return this.peso;
		}
	public double getEstado(){
		return this.estado;
		
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#liberarFuerzas()
	 */
	@Override
	public void liberarFuerzas() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#recibirFuerza(modelo.Fuerza)
	 */
	@Override
	public void recibirFuerza(Fuerza fuerza) {
		if(fuerza.getEmisor()==getEje()){
			//la fuerza viene del eje
			//se pasa la fuerza al neumatico
			  double valorDeLaFuerza=0;
			  try{
			     valorDeLaFuerza=fuerza.getValorDeLaFuerza();
			  }catch (Exception e){}
			  //transmito fuerza a nuematico delantero
			  Fuerza fuerzaANeumatico=new Fuerza(this,getNeumatico(),valorDeLaFuerza,true);
			  getNeumatico().recibirFuerza(fuerzaANeumatico);
		}else{
			//la fuerza viene del neumatico
			
		}
	}

	/**
	 * @return the eje
	 */
	public Eje getEje() {
		return eje;
	}

	/**
	 * @param eje the eje to set
	 */
	public void setEje(Eje eje) {
		this.eje = eje;
	}

	/**
	 * @return the neumatico
	 */
	public Neumatico getNeumatico() {
		return neumatico;
	}

	/**
	 * @param neumatico the neumatico to set
	 */
	public void setNeumatico(Neumatico neumatico) {
		this.neumatico = neumatico;
	}
	
}