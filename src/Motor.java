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
public class Motor extends Componente{
	/* comentario acerca de la implementacion de la clase */
	
	private double Cilindrada;
	
	private int CantidadCilindros;
	
	/**
	 * las revoluciones maximas se calculan segun la cantidad
	 * de cilindros y la cilindrada
	 * 
	 * un motor de 8 cilindros, con cilindrada 4.0
	 * tiene maximas revoluciones de 6400 rpm
	 */
	private double revolucionesMaximas;
	
	/**
	 * contador de las revoluciones del motor
	 */
	private double RPM;

	/**
	 * constructor de Motor
	 * 
	 * @param cantidadCilindros
	 * @param cilindrada
	 */
	Motor(int cantidadCilindros,double cilindrada){
		CantidadCilindros = cantidadCilindros;
		Cilindrada = cilindrada;
		
		revolucionesMaximas = CantidadCilindros * Cilindrada * 200;
	}
	
	/**
	 * pasarce de revoluciones es perjudicial
	 */
	public void desgastar(){
		/* pasarce de revolucionesMaximas es perjudicial */
		this.setEstado(Estado - RPM/revolucionesMaximas - 1/1000000000);
	}
	
	public double obtenerPotencia(){
		return 0;
		
	}
	
	/* setters y getters */

	public double getRPM() {
		return RPM;
	}

	public void setRPM(double rpm) {
		RPM = rpm;
	}

	public int getCantidadCilindros() {
		return CantidadCilindros;
	}
	
	public double getCilindrada() {
		return Cilindrada;
	}

	public double getRevolucionesMaximas() {
		return revolucionesMaximas;
	}
	
}