/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/


/**
 * Documentacion
 * 
 * @version	1.0
 */
public class Clima{
	/* comentario acerca de la implementacion de la clase */
	
	/**
	 * la temeratura se mide en grados Celsius
	 */
	private double Temperatura;

	/**
	 * La humedad relativa es la humedad que contiene una masa de aire, en 
	 * relación con la máxima humedad absoluta que podría admitir sin producirse 
	 * condensación, conservando las mismas condiciones de temperatura y presión 
	 * atmosférica. Esta es la forma más habitual de expresar la humedad ambiental. 
	 * Se expresa en tanto por ciento. %
	 * 
	 * 0% es muy seco
	 * 100% es muy lluvioso
	 */
	private double Humedad;
	
	/**
	 * se mide en hPa, 1 atm = 1013 hPa
	 */
	private double Presion;

	
	/**
	 * constructor de clima sin parametros 
	 * Condiciones Estandar de Presion y Temperatura
	 */
	Clima(){
		/* CNPT */
		Temperatura = 25; //°C
		Humedad = 50; //%
		Presion = 1013; //hPa
		
		Auto.afectar(this);
	}
	
	Clima(double temperatura, double humedad, double presion){
		Temperatura = temperatura; //°C
		Humedad = humedad; //%
		Presion = presion; //hPa
		
		Auto.afectar(this);
	}
	
}