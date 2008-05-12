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
 * se ve afectado por la humedad del clima
 * 
 * @version	1.0
 */
public class Turbo extends Componente implements AfectablePorClima{
	/* comentario acerca de la implementacion de la clase */
	
	
	private static double presionInterna=500;/* en HectoPascales*/
	private final static double coeficienteClimaticoNormal=3545500; //surge de la multiplicacion de ....
	private double coeficienteDeObtencionDePotencia;
	private static double constanteDeDesgaste=3;
	private static double coeficienteInicial=0.16;
	
	
	public Turbo(){
		this.setCoeficienteDeObtencionDePotencia(3);
		}
	
	public void desgastar(){
		  setEstado(getEstado()-tiempoPorCiclo*constanteDeDesgaste);
	}
	
	public double obtenerPotencia(){
		return presionInterna*getCoeficienteDeObtencionDePotencia();
	
	}
	
	/** el clima afecta al turbo */
	public void afectar(Clima clima){
		double relacion;
		try{
		relacion=(coeficienteClimaticoNormal /clima.getPresion()*clima.getTemperatura()*clima.getHumedad());
		}catch (Exception e){
		relacion=0.5;
		}
		coeficienteDeObtencionDePotencia=coeficienteInicial*Math.abs(1- relacion);
		
		
	}

		
	/**
	 * @param presionInterna the presionInterna to set
	 */
	public void setPresionInterna(double presionInterna) {
		this.presionInterna = presionInterna;
	}
	/**
	 * @return the presionInterna
	 */
	public double getPresionInterna() {
		return presionInterna;
	}

	/**
	 * @param coeficienteDeObtencionDePotencia the coeficienteDeObtencionDePotencia to set
	 */

	/**
	 * @param coeficienteDeObtencionDePotencia the coeficienteDeObtencionDePotencia to set
	 */
	public void setCoeficienteDeObtencionDePotencia(
			double coeficienteDeObtencionDePotencia) {
		this.coeficienteDeObtencionDePotencia = coeficienteDeObtencionDePotencia;
	}

	/**
	 * @return the coeficienteDeObtencionDePotencia
	 */
	public double getCoeficienteDeObtencionDePotencia() {
		return coeficienteDeObtencionDePotencia;
	}

	
	
}