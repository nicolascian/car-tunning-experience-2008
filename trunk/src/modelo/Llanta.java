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
	/** comentario acerca de la implementacion de la clase */
	private static double  potenciaNormal=15;  /** en hp*/
	
	private double pesoNormal;//expresado en kg
	
	private double humedadOptima=50; // expresada en porcentaje}
	
	private static double constanteDeDesgaste=1;
	
	private double coeficienteDeDesgastePorSuperficie;
	
	
	
	public void llanta(){
		this.setCoeficienteDeDesgastePorSuperficie(3);
	}
	public void llanta(double pesoNormal){
		this.setPesoNormal(8);
		this.setCoeficienteDeDesgastePorSuperficie(3);

	}
	
	public void desgastar(){
		setEstado(getEstado()-this.getCoeficienteDeDesgastePorSuperficie()*tiempoPorCiclo*constanteDeDesgaste);
	}
	
	public double obtenerPotencia(){
		return (this.getPeso()*potenciaNormal / this.getPesoNormal());
	}
	
	
	/** la superficie afecta a las llantas */
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
	/**
	 * @param coeficienteDeDesgastePorSuperficie the coeficienteDeDesgastePorSuperficie to set
	 */
	public void setCoeficienteDeDesgastePorSuperficie(
			double coeficienteDeDesgastePorSuperficie) {
		this.coeficienteDeDesgastePorSuperficie = coeficienteDeDesgastePorSuperficie;
	}
	/**
	 * @return the coeficienteDeDesgastePorSuperficie
	 */
	public double getCoeficienteDeDesgastePorSuperficie() {
		return coeficienteDeDesgastePorSuperficie;
	}
	
}