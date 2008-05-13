/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

/**
 * Clase Clima
 * 
 * El clima es el conjunto de los valores promedios de las condiciones atmosféricas que 
 * caracterizan una región. Estos valores promedio se obtienen con la recopilación de la 
 * información meteorológica durante un periodo de tiempo suficientemente largo. Según se 
 * refiera al mundo, a una zona o región, o a una localidad concreta se habla de clima 
 * global, zonal,regional o local (microclima), respectivamente.
 * 
 * En este modelo, cada Clima consta de tres propiedades: temperatura, humedad y presion.
 * 
 * @version	1.0
 * @see <a href="http://es.wikipedia.org/wiki/Clima">Clima - Wikipedia</a> 
 */
public class Clima{
	/* Consta de setters y getters autogenerados para temperatura, humedad y presion */
	
	/**
	 * La temperatura es una magnitud referida a las nociones comunes de calor o frío, 
	 * por lo general un objeto más "caliente" tendrá una temperatura mayor. Físicamente 
	 * es una magnitud escalar dada por una función creciente del grado de agitación de las 
	 * partículas de los materiales. A mayor agitación, mayor temperatura. Así, en la escala 
	 * microscópica, la temperatura se define como el promedio de la energía de los movimientos 
	 * de una partícula individual por grado de libertad.
	 *
	 * En el caso de un sólido, los movimientos en cuestión resultan ser las vibraciones de las 
	 * partículas en sus sitios dentro del sólido. En el caso de un gas ideal monoatómico se trata 
	 * de los movimientos traslacionales de sus partículas (para los gases multiatómicos los 
	 * movimientos rotacional y vibracional deben tomarse en cuenta también).
	 *
	 * Multitud de propiedades fisicoquímicas de los materiales o las sustancias varían en función 
	 * de la temperatura a la que se encuentren, como por ejemplo su estado (gaseoso, líquido, 
	 * sólido, plasma...), su volumen, la solubilidad, la presión de vapor o la conductividad eléctrica. 
	 * Así mismo es uno de los factores que influyen en la velocidad a la que tienen lugar las 
	 * reacciones químicas.
	 * 
	 * La temperatura se mide en grados Celsius
	 * Alude a la temperatura ambiental.
	 * 
	 * @see <a href="http://es.wikipedia.org/wiki/Temperatura">Temperatura - Wikipedia</a> 
	 */
	private double Temperatura;

	/**
	 * La humedad relativa es la humedad que contiene una masa de aire, en 
	 * relación con la máxima humedad absoluta que podría admitir sin producirse 
	 * condensación, conservando las mismas condiciones de temperatura y presión 
	 * atmosférica. Esta es la forma más habitual de expresar la humedad ambiental. 
	 * Se expresa en tanto por ciento. %
	 * 
	 * Sin embargo, consideraremos que si la humedad es cercana al 100%, se produce
	 * condensacion y, por lo tanto, precipitacion.
	 * 
	 * 0% es muy seco
	 * 100% es muy lluvioso
	 * 
	 * @see <a href="http://es.wikipedia.org/wiki/Humedad#Humedad_relativa">Humedad - Wikipedia</a> 
	 */
	private double Humedad;
	
	/**
	 * La presión es una magnitud física que mide la fuerza por unidad de superficie, 
	 * y sirve para caracterizar como se aplica una determinada fuerza resultante 
	 * sobre una sueperficie.
	 * 
	 * En el Sistema Internacional de Unidades la presión se mide en una unidad derivada 
	 * que se denomina pascal (Pa) que es equivalente a una fuerza total de un newton 
	 * actuando uniformemente en un metro cuadrado.
	 * 
	 * En este modelo la presion se mide en hPa (1 atm = 1013 hPa)
	 * y se hace referencia a la presion atmosferica
	 * 
	 * @see <a href="http://es.wikipedia.org/wiki/Presion">Presion - Wikipedia</a> 
	 */
	private double Presion;

	
	/**
	 * Constructor de clima sin parametros 
	 * Se crea un clima con "Condiciones Estandar de Presion y Temperatura"
	 * Temperatura = 25 °C
	 * Humedad = 50 %
	 * Presion = 1013 hPa
	 */
	public Clima(){
		/* CNPT */
		Temperatura = 25; //°C
		Humedad = 50; //%
		Presion = 1013; //hPa
	}
	
	/**
	 * Constructor de clima con parametros
	 * 
	 * @param temperatura Valores en grados Celsius
	 * @param humedad Valores en porcentaje de agua en aire
	 * @param presion Valores en hPa
	 */
	public Clima(double temperatura, double humedad, double presion){
		Temperatura = temperatura; //°C
		Humedad = humedad; //%
		Presion = presion; //hPa
	}
	
	
	
	/* setters y getters */
	
	public double getHumedad() {
		return Humedad;
	}

	public void setHumedad(double humedad) {
		Humedad = humedad;
	}

	public double getPresion() {
		return Presion;
	}

	public void setPresion(double presion) {
		Presion = presion;
	}

	public double getTemperatura() {
		return Temperatura;
	}

	public void setTemperatura(double temperatura) {
		Temperatura = temperatura;
	}
	
	/* toString */
	
	public String toString() {
		
		String cadena="CLIMA: " +'\n' 
		            + "  Temperatura: " + getTemperatura()+"°C"
		            + "      Humedad: " + getHumedad()+"%"
                    + "      Presion: " + getPresion()+" hPa";

		return(cadena);
	}
	
}