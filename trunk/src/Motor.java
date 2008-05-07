/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 * 
 * se ve afectado por la temperatura del clima
 * 
 * @version	1.0
 */
public class Motor extends Componente implements AfectablePorClima{
	/* comentario acerca de la implementacion de la clase */
	
	private double Cilindrada;
	
	private int CantidadCilindros;
	
	private double TemperaturaCritica;
	private double TemperaturaOptima;
	private double TemperaturaExterna;

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
		TemperaturaCritica = 500; //°C
		TemperaturaOptima = 120; //°C
		TemperaturaExterna = 0; //°C
	}
	
	/**
	 * pasarce de revoluciones es perjudicial
	 */
	public void desgastar(){
		/* pasarce de revolucionesMaximas es perjudicial */
		this.setEstado( Estado - (RPM/revolucionesMaximas)*10 - 
				        1/1000000000 - (Temperatura-TemperaturaCritica)/TemperaturaOptima );
	}
	
	public double obtenerPotencia(){
		
		return ( RPM + Estado + Cilindrada + CantidadCilindros + 
				(Temperatura-TemperaturaCritica)/TemperaturaOptima +
				 // Caja
				 auto.getCaja().obtenerPotencia() +
				 // Alimentacion.obtenerPotencias hace: Combustible.obtenerPotencia
				 auto.getAlimentacion().obtenerPotencia() ); 
	}
	
	/**
	 * cada vez que cambiamos las RPM, cambian la Temperatura del motor
	 * ademas le avisa a la Caja que hubo un cambio de revoluciones y en este
	 * caso le envia la variacion con repecto al cambio anterior
	 * 
	 * @param rpm
	 */
	public void setRPM(double rpm) {
		double anterior = RPM;
		
		RPM = rpm;
		
		auto.getCaja().Chequear(RPM - anterior);
		
		Temperatura = ( RPM/6000 + TemperaturaExterna );
	}
	
	/** el clima afecta al motor */
	public void afectar(Clima clima){
		TemperaturaExterna = clima.getTemperatura();
	}
	
	/* setters y getters */

	public double getRPM() {
		return RPM;
	}

	public void disminuirRpmDesdeCaja(double porcentaje){
	   if(porcentaje>20){
		  if(porcentaje<80)	
	   		 setRPM(getRPM()-getRPM()*porcentaje/100);
		  else
			 setRPM(getRPM()-getRPM()*0.8); 
       }
	   else
		   setRPM(getRPM()-getRPM()*0.2);
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