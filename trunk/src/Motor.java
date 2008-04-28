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
	 * estas revoluciones indicadas para cada cambio
	 * especifican cuando hay que hacer un cambio
	 * 
	 * un motor de 8 cilindros, con cilindrada 4.0
	 * tiene maximas revoluciones de 6400 rpm
	 */
	private double revolucionesOptimas;
	
	/**
	 * las revoluciones maximas se calculan segun las revolucionesOptimas
	 * y se toma en cuenta el cambio actual
	 * 
	 * si las revolucionesOptimas son 6400
	 * entonces las revolucionesMaximas son:
	 * primera = 8060
	 * segunda = 8120
	 * tercera = 8180
	 * cuarta  = 8240
	 * etc...
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
		
		revolucionesOptimas = CantidadCilindros * Cilindrada * 200;
		revolucionesMaximas = (5/4)*revolucionesOptimas;
	}
	
	/**
	 * pasarce de revoluciones es perjudicial
	 */
	public void desgastar(){
		/* pasarce de revolucionesMaximas es perjudicial */
		this.setEstado(Estado - (RPM/revolucionesMaximas)*10 - 1/1000000000);
	}
	
	public double obtenerPotencia(){
		return 0;
		
	}
	
	
	
	public void setRPM(double rpm) {
		RPM = rpm;
		revolucionesMaximas = (5/4)*revolucionesOptimas + (auto.getCaja().getCambio() * 60);
	}
	
	/* setters y getters */

	public double getRPM() {
		return RPM;
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

	public double getRevolucionesOptimas() {
		return revolucionesOptimas;
	}

	public void setRevolucionesMaximas(double revolucionesMaximas) {
		this.revolucionesMaximas = revolucionesMaximas;
	}
	
}