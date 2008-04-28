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
	private double Estado;
	
	private double Peso;
	
	private Auto auto;
	
	private AlgoPesos precio;
	

	public abstract void desgastar(); 

	public abstract double obtenerPotencia();
	
	
	public void reparar(double porcentaje){
		this.setEstado(this.getEstado() + porcentaje);
	}
	

	
	
	/* Setters y Getters */
	
	public AlgoPesos getPrecio() {
		return precio;
	}

	public void setPrecio(AlgoPesos precio) {
		this.precio = precio;
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}
	
	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	public double getEstado(){
		return Estado;
	}
	
	public void setEstado(double estado) {
		Estado = estado;
	}
	
	public double getPeso() {
		return Peso;
	}
	
	public void setPeso(double peso) {
		Peso = peso;
	}
	
}