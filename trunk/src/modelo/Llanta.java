package modelo;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 * 
 * se afecta por la presion del clima
 * y la superficie
 * 
 * @version	1.0
 */
public class Llanta extends Componente implements AfectablePorSuperficie{
	/* comentario acerca de la implementacion de la clase */
	private static double  potenciaNormal=15; // en hp
	private double pesoNormal;//expresado en kg

	
	public void llanta(){
		
	}
	public void llanta(double pesoNormal){
		this.setPesoNormal(8);		
	}
	
	public void desgastar(){
		//tener en cuenta Presion y superficie
	}
	
	public double obtenerPotencia(){
		return (this.getPeso()*potenciaNormal / this.getPesoNormal());
	}
	
	
	/** la superficie afecta a las llantas */
	public void afectar(Superficie superficie){
		
	}
	/**
	 * @param pesoNormal the pesoNormal to set
	 */
	public void setPesoNormal(double pesoNormal) {
		this.pesoNormal = pesoNormal;
	}
	/**
	 * @return the pesoNormal
	 */
	public double getPesoNormal() {
		return pesoNormal;
	}
	
}