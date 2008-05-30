/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente;

import modelo.AfectablePorSuperficie;
import modelo.Superficie;
import modelo.fuerzas.Fuerza;
import modelo.fuerzas.ReceptorDeFuerzas;
import modelo.*;
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
	
	private Eje eje=null;
	
	private Neumatico neumatico=null;
	
	
	/**constructor, queda instanciada 
	 *  la clase Llanta.
	 */
	
	public Llanta(){
		setAuto(null);
		setEstado(100);
		this.setPeso(15);
		this.setCoeficienteDeDesgastePorSuperficie(3);
	}
	
	/**
	 * constructor con parametros,
	 * queda instanciada la clase Llanta
	 * 
	 * @param pesoNormal
	 */
	public Llanta(double peso){
		setAuto(null);
		setEstado(100);
		setPeso(peso);
		setCoeficienteDeDesgastePorSuperficie(3);
		setNeumatico(null);
	}
	
	/**
	 * Con el pasar del tiempo, las llantas,
	 * asi como todos los componentes del auto,
	 * se van desgastando, hasta que su uso se hace nulo.
	 * 
	 */
	public void desgastar(){
		setEstado(super.getEstado()-this.getCoeficienteDeDesgastePorSuperficie()*Constantes.tiempoPorCiclo*constanteDeDesgaste);
		try{
			this.getNeumatico().desgastar();
		}catch(NullPointerException e){}
	}
	
	/**
	 * Las llantas, al igual que otros componentes,
	 * rinden una determinada potencia, mientras mas livianas 
	 * son, mas potencia puede otorgar
	 */
	public double obtenerPotencia(){
		return (this.getPesoNormal()*potenciaNormal / super.getPeso()+
				getNeumatico().obtenerPotencia());
	}
		
	/** 
	 * la superficie afecta a las llantas 
	 * 
	 */
	public void afectar(Superficie superficie){           
		double relacion;
		try{
		   relacion= (this.getCoeficienteDeDesgastePorSuperficie()/(superficie.getParticulasSueltas()
				     *superficie.getRugosidad()*superficie.getViscosidad()));
		}catch (Exception e){
		   relacion=0.5;
		}
		this.setCoeficienteDeDesgastePorSuperficie(this.getCoeficienteDeDesgastePorSuperficie()+
				               (this.getCoeficienteDeDesgastePorSuperficie()*Math.abs(1- relacion)));
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
	
	public void setPeso(double peso) {
		this.peso = peso;
	}

	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#liberarFuerzas()
	 */
	@Override
	public void liberarFuerzas() {}

	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#recibirFuerza(modelo.Fuerza)
	 */
	@Override
	public void recibirFuerza(Fuerza fuerza) {
		if(fuerza.getEmisor()==getEje()){
			//la fuerza viene del eje
			//se pasa la fuerza al neumatico
			fuerza.setEmisor(this);
			fuerza.setReceptor(getNeumatico());
			getNeumatico().recibirFuerza(fuerza);
		}else{
			//la fuerza viene del neumatico se pasa al eje
			fuerza.setEmisor(this);
			fuerza.setReceptor((ReceptorDeFuerzas)getEje());
			((ReceptorDeFuerzas)getEje()).recibirFuerza(fuerza);
		}
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

	public Eje getEje() {
		return(eje);
	}
	
	public void setEje(Eje eje) {
		this.eje=eje;
	}
	
	public void setAuto(Auto auto){
		super.setAuto(auto);
	}
	
	public double getPeso(){
		return(this.peso);
		
	}
	/**
	 * 
	 * @param auto
	 * @param eje
	 * @param neumatico
	 * @param derecha indica si es la llanta derecha del eje si se pasa true como parametro, en
	 * caso contrario se paso false y sera la llanta izquierda
	 */
	public void instalar(Auto auto,Eje eje,Neumatico neumatico,boolean derecha){
		this.setAuto(auto);
		this.setEje(eje);
		this.setNeumatico(neumatico);
		try{
			getNeumatico().setLlanta(this);
		}catch(NullPointerException e){}
		try{
			if(derecha)
			  getEje().setLlantaDerecha(this);
			else
			  getEje().setLlantaIzquierda(this);
		}catch(NullPointerException e){}
	}

}