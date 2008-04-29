/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 * 
 * cada vez que se crea una superficie nueva, esta se encarga de afectar 
 * al auto, en su constructor llama a auto.afectar(this);
 * 
 * @version	1.0
 */
public class Superficie{
	/* comentario acerca de la implementacion de la clase */
	
	private String nombre;
	
	private double coeficiente;
	/**
	 * El valor minimo de rugosidad es 0 y corresponde a 
	 * una superficie totalmente plana, sin desnivel alguno.
	 * Un mayor nivel de rugosidad de la superficie aumentara los daños
	 * en aquellas partes del auto que interactuen directamente con el suelo.
	 */
	private double rugosidad;
	
	/**
	 * Las particulas sueltas representan aquellos elementos de volumen
	 * por unidad de superficie considerables que dañan al auto. 
	 */
	private double particulasSueltas;
	
	/**
	 * La viscosidad es una caracteristica que representa la oposicion
	 * del terreno al deslizamiento del vehiculo sobre el
	 * viscosidad = 0 implica que la superficie es totalmente resbaladiza
	 * viscosidad = 1 implica la maxima adherencia posible entre el vehiculo
	 * y la superficie    
	 */
	private double viscosidad;
	
	Superficie(){

	}

	public double getCoeficiente() {
		return coeficiente;
	}

	public void setCoeficiente(double coeficiente) {
		this.coeficiente = coeficiente;
	}

	public double getRugosidad() {
		return rugosidad;
	}

	public void setRugosidad(double rugosidad) {
		this.rugosidad = rugosidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getParticulasSueltas() {
		return particulasSueltas;
	}

	public void setParticulasSueltas(double particulasSueltas) {
		this.particulasSueltas = particulasSueltas;
	}

	public double getViscosidad() {
		return viscosidad;
	}

	public void setViscosidad(double viscosidad) {
		this.viscosidad = viscosidad;
	}
	
}