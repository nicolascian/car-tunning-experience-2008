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
public class Llanta extends Componente implements 
	AfectablePorSuperficie, ReceptorDeFuerzas,  ComponenteContenidoEnComponente{
		
	// en hp
	private static double potenciaNormal=15;  
	
	//expresado en kg
	private double pesoNormal=25;
	
	// expresada en porcentaje}
	private double humedadOptima=50; 

	private static double constanteDeDesgaste=1;
	
	private double coeficienteDeDesgastePorSuperficie=1;
	
	private Componente contenedor=null;
	
	private Neumatico neumatico=null;
	
	private double rpm=0;//revoluciones a las que gira la instancia
	
	/**constructor, queda instanciada 
	 *  la clase Llanta.
	 */
	
	public Llanta(){
		setAuto(null);
		setComponenteContenedor(null);
		setEstado(100);
		this.setPeso(15);
		this.setCoeficienteDeDesgastePorSuperficie(3);
		this.setNeumatico(new NeumaticoMixto());
	}
	
	/**
	 * constructor con parametros,
	 * queda instanciada la clase Llanta
	 * 
	 * @param pesoNormal
	 */
	public Llanta(double peso){
		setAuto(null);
		setComponenteContenedor(null);
		setEstado(100);
		setPeso(peso);
		setCoeficienteDeDesgastePorSuperficie(3);
		setNeumatico(new NeumaticoMixto());
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
	public void liberarFuerzas() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#recibirFuerza(modelo.Fuerza)
	 */
	@Override
	public void recibirFuerza(Fuerza fuerza) {
		if(fuerza.getEmisor()==getComponenteContenedor()){
			//la fuerza viene del eje
			//se pasa la fuerza al neumatico
			fuerza.setEmisor(this);
			fuerza.setReceptor(getNeumatico());
			getNeumatico().recibirFuerza(fuerza);
		}else{
			//la fuerza viene del neumatico se pasa al eje
			fuerza.setEmisor(this);
			fuerza.setReceptor((ReceptorDeFuerzas)getComponenteContenedor());
			((ReceptorDeFuerzas)getComponenteContenedor()).recibirFuerza(fuerza);
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
		neumatico.instalar(auto,this);
	}

	/* (non-Javadoc)
	 * @see modelo.ComponenteContenidoEnComponente#getComponenteContenedor()
	 */
	@Override
	public Componente getComponenteContenedor() {
		return(contenedor);
	}

	/* (non-Javadoc)
	 * @see modelo.ComponenteContenidoEnComponente#setComponenteContenedor()
	 */
	@Override
	public void setComponenteContenedor(Componente contenedor) {
		this.contenedor=contenedor;
	}
	
	public void setAuto(Auto auto){
		super.setAuto(auto);
		instalar(getAuto(),null);
	}
	
	public double getPeso(){
		return(this.peso);
		
	}
	
	public void instalar(Auto auto,Eje eje){
		this.auto=auto;
		try{
			neumatico.instalar(auto,this);
		}catch(NullPointerException e){}
		setComponenteContenedor(eje);
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
		  try{
			  this.getNeumatico().setRpm(rpmFinal);
		  }catch(NullPointerException e){}
	}
		
}