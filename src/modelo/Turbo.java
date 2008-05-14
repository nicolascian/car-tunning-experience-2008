/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

/**
 * Un turbocompresor o turbocargador es un sistema de sobrealimentación que usa 
 * una turbina para comprimir gases. Este tipo de sistemas se suele utilizar en 
 * motores de combustión interna, aunque también se usan en estaciones distribuidoras 
 * de gas natural para enviarlo por gasoductos.
 * 
 * Los motores provistos de turbocompresores padecen de una demora mayor en la 
 * disposición de la potencia que los motores atmosféricos (NA Normal Aspiration 
 * o Aspiración Normal) o con compresor mecánico, debido a que el rendimiento del 
 * turbocompresor depende de la presión ejercida por éste. En esta demora influyen 
 * la inercia del grupo (su diámetro y peso) y el volumen del colector entre la 
 * turbina y la salida de los gases de escape del cilindro.
 *
 * Un turbocompresor no funciona de igual manera en distintos regímenes de motor. 
 * A bajas revoluciones, el turbocompresor no ejerce presión porque la escasa cantidad 
 * de gases no empuja con suficiente fuerza. Un turbocompresor más pequeño evita la 
 * demora en la respuesta, pero ejerce menos fuerza a altas revoluciones.
 * 
 * se ve afectado por la humedad del clima
 * 
 * @version	1.0
 * @see <a href="http://es.wikipedia.org/wiki/Turbocompresor">Turbocompresor - Wikipedia</a> 
 */
public class Turbo extends Componente implements AfectablePorClima{
	
	// en HectoPascales
	private static double presionInterna=500;
	
	//surge de la multiplicacion de una tempratura extrema, una humedad extrema,
	//y una presion normal, a este valor le calculamos un porcentaje que represente la media.
	private final static double coeficienteClimaticoNormal=3545500;
	
	
	private double coeficienteDeObtencionDePotencia;
	
	//mietras mas grande es el coeficienta, mas se desgasta
	private static double constanteDeDesgaste=3;
	
	private static double coeficienteInicial=0.16;
	
	/**
	 * constructor, queda instanciada 
	 * la clase
	 */
	public Turbo(){
		setEstado(100);
		this.setCoeficienteDeObtencionDePotencia(3);
		}
	
	/**
	 * con el correr del tiempo,
	 * los componentes se van desgastando
	 */
	public void desgastar(){
		  setEstado(getEstado()-tiempoPorCiclo*constanteDeDesgaste);
	}
	/**
	 * nos devuelve la potencia 
	 * que rinde el turbo
	 */
	
	public double obtenerPotencia(){
		return presionInterna*getCoeficienteDeObtencionDePotencia();
	
	}
	
	/** 
	 * el clima afecta a la potencia 
	 * del turbo
	 */
	public void afectar(Clima clima){
		double relacion;
		try{
		relacion=(coeficienteClimaticoNormal /clima.getPresion()*clima.getTemperatura()*clima.getHumedad());
		}catch (Exception e){
		relacion=0.5;
		}
		coeficienteDeObtencionDePotencia=coeficienteInicial*Math.abs(1- relacion);
		
		
	}

		// setters y getters //
	/**
	 * asignamos un valor a la presionInterna
	 * 
	 * @param presionInterna the presionInterna to set
	 */
	public void setPresionInterna(double presionInterna) {
		this.presionInterna = presionInterna;
	}
	/**
	 * Obtenemos el valor de presionInterna
	 * 
	 * @return the presionInterna
	 */
	public double getPresionInterna() {
		return presionInterna;
	}


	/**
	 * seteamos al coeficienteDeObtencionDePotencia
	 * 
	 * @param coeficienteDeObtencionDePotencia the coeficienteDeObtencionDePotencia to set
	 */
	public void setCoeficienteDeObtencionDePotencia(
			double coeficienteDeObtencionDePotencia) {
		this.coeficienteDeObtencionDePotencia = coeficienteDeObtencionDePotencia;
	}

	/**
	 * obtenemos el coeficienteDeObtencionDePotencia
	 * @return the coeficienteDeObtencionDePotencia
	 */
	public double getCoeficienteDeObtencionDePotencia() {
		return coeficienteDeObtencionDePotencia;
	}
	
}