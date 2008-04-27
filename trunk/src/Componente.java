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
	
	/**
	 * Documentacion
	 */
	@SuppressWarnings("unused")	
	private String Nombre;
	@SuppressWarnings("unused")
	private double Estado ;
	@SuppressWarnings("unused")	
	private double Peso;
	
	public abstract double getPrecio();
	
	public abstract void desgastar(double porcentaje);
	
	public abstract void reparar(double porcentaje);
	
	public abstract double obtenerPotencia();
	
	public abstract double getPeso();
	
	
}