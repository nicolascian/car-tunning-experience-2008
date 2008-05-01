/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

import java.util.*;

/**
 * Documentacion
 * 
 * @version 1.0
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
	protected double Estado;
	
	/** Temperatura interna del componente */
	protected double Temperatura;
	
	/** Peso especifico del componente */
	protected double Peso;
	
	protected Auto auto;
	
	protected AlgoPesos precio;
	
	/**
	 * es invocado por el auto
	 * tiene por objetivo deteriorar el estado de cada componente
	 * esto hace que los componentes pierdan la eficiencia, y den 
	 * menos potencia
	 */
	public abstract void desgastar(); 

	/**
	 * nos dice la potencia del componente en cualquier momento
	 * dependiendo del estado del componete y de los factores
	 * que influyen en su funcionamiento
	 * 
	 * @return
	 */
	public abstract double obtenerPotencia();
	
	/**
	 * en funcion de la disposicion del unidades monetarias, y
	 * tomando el cuenta el precio de cada componente, podemos
	 * restaurar el estado de la pieza.
	 * 
	 * @param porcentaje
	 */
	public void reparar(double porcentaje){
		Estado += porcentaje;
	}
	/**
	 * Metodo que recibe una lista de elementos afectables por clima, 
	 * y que agrega el componente a dicha lista si este es una instancia 
	 * de AfectablePorClima
	 * @param listaAC
	 */
	public void agregarAListaAfecClima(LinkedList<AfectablePorClima> lista){
		if (this instanceof AfectablePorClima) lista.add((AfectablePorClima)this);
	}
	
	/**
	 * Metodo que recibe una lista de elementos afectables por superficie, 
	 * y que agrega el componente a dicha lista si este es una instancia 
	 * de AfectablePorSuperficie
	 * @param listaAS
	 */
	public void agregarAListaAfecSuperficie(LinkedList<AfectablePorSuperficie> lista){
		if (this instanceof AfectablePorSuperficie) lista.add((AfectablePorSuperficie)this);
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