package modelo;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * cada vez que se crea una superficie nueva, esta se encarga de afectar 
 * al auto, en su constructor llama a auto.afectar(this);
 * 
 * @version	1.0
 */
public class Superficie{
	/* comentario acerca de la implementacion de la clase */
	
	private String nombre;
	
	/**
	 * El valor minimo de rugosidad es 0 y corresponde a 
	 * una superficie totalmente plana, sin desnivel alguno.
	 * Un valor de rugosidad igual a 100 corresponde a una superficie
	 * totalmente dañada.
	 * Un mayor nivel de rugosidad de la superficie aumentara los daños
	 * en aquellas partes del auto que interactuen directamente con el suelo.
	 */
	private double rugosidad;
	
	/**
	 * Las particulas sueltas representan aquellos elementos de volumen
	 * por unidad de superficie considerables que dañan al auto.
	 * 0 corresponde a ninguna particula suelta
	 * 100 corresponde al maximo de particulas sueltas 
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
	
	/**
	 * Constructor por defecto. Inicializa todos los coeficientes con valor
	 * nulo.
	 * post: Queda creada una instancia de superficie.
	 */
	Superficie(){
		rugosidad = 0;
		particulasSueltas = 0;
		viscosidad = 0;
	}

	/**
	 * Constructor con parametros. Inicializa los coeficientes con los valores
	 * segun los parametros
	 * post: Queda creada una instancia de superficie con los valores indicados.
	 */
	Superficie(double indicerugosidad, double indiceparticulas, double indiceviscosidad){
		rugosidad = indicerugosidad;
		particulasSueltas = indiceparticulas;
		viscosidad = indiceviscosidad;
	}
	
	/* setters y getters */

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