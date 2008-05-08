/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 * 
 * se vve afectado por la humedad y presion del clima
 * y el tipo de superficie
 * 
 * @version	1.0
 */
public class Escape extends Componente
implements AfectablePorClima, AfectablePorSuperficie{
	/* comentario acerca de la implementacion de la clase */
	private double EfectoSuperficie;
	private double EfectoClimatico;
	private double HumedadOptima = 50;
	private double PresionOptima = 1013;
	
	Escape(){
		HumedadOptima = 50;
		PresionOptima = 1013;
	}
	
	Escape(double HumedadOptima, double PresionOptima){
		this.HumedadOptima = HumedadOptima;
		this.PresionOptima = PresionOptima;
	}
	
	public void desgastar(){
		//tener en cuenta Humedad, presion y superficie
		this.setEstado(getEstado() - EfectoClimatico - EfectoSuperficie/100 - 1/1000000000);
	}
	
	public double obtenerPotencia(){
		//tener en cuenta Humedad, presion y Estado
		return (HumedadOptima*PresionOptima*EfectoClimatico/100) * getEstado();
	}
	
	/** el clima afecta al escape */
	public void afectar(Clima clima){
		EfectoClimatico = (clima.getHumedad()/HumedadOptima) + (clima.getPresion()/PresionOptima);
	}
	
	/** la superficie afecta al escape */
	public void afectar(Superficie superficie){
		EfectoSuperficie = superficie.getCoeficiente();
	}

	/* setters y getters */
	
	public void setHumedadOptima(double humedadOptima) {
		HumedadOptima = humedadOptima;
	}

	public void setPresionOptima(double presionOptima) {
		PresionOptima = presionOptima;
	}
}