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
public abstract class Componente{
	/* comentario acerca de la implementacion de la clase */
	
	private String Nombre;
	/**
	 * El estado indica el porcentaje de integridad de un componente.
	 * Toma valores entre 0 y 100.
	 * 0 corresponde a roto.
	 * 100 corresponde a totalmente integro.
	 * Un auto no puede funcionar si alguno de sus elementos tiene Estado = 0.
	 */
	private double Estado ;

	private double Peso;
	
	public abstract double getPrecio();
	
	public abstract void desgastar(double porcentaje);
	
	public abstract void reparar(double porcentaje);
	
	public abstract double obtenerPotencia();
	
	public abstract double getPeso();
	
	
}