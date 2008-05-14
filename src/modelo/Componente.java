/* ****************************************************************************
/*                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;
import java.util.*;

/**
 * Clase padre de la cual heredan todos lo elementos
 * que integraran el auto para poder competir.
 * un auto no puede funcionar si no esta completo, es decir,
 * incluye a todos los componentes.
 * 
 * @version 1.0
 */
public abstract class Componente extends Constantes {
	/* comentario acerca de la implementacion de la clase */
	
	private String nombre;
	
	/**
	 * El estado indica el porcentaje de integridad de un componente.
	 * Toma valores entre 0 y 100.
	 * 0 corresponde a roto.
	 * 100 corresponde a totalmente integro.
	 * Un auto no puede funcionar si alguno de sus elementos tiene Estado = 0.
	 */
	protected double estado;
			
	/** Peso especifico del componente */
	protected double peso;
	
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
	 * @return Potencia
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
		estado += porcentaje;
	}
	
	public AlgoPesos getPrecioAmortizado(){
		return precio;//( getEstado()*100/getPrecio().getValor());
		
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
	
	/**
	 * se usa para que cada componente conozca su auto 
	 * 
	 * @param auto
	 */
	public void instalar(Auto auto){
		this.setAuto(auto);
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
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public double getEstado(){
		return estado;
	}
	
	/* Valor minimo de estado posible es 0 */
	public void setEstado(double estado) {
		if(estado<0)
		  this.estado = 0;
		else
		  this.estado=estado;
	}
	
	public double getPeso() {
		return this.peso;
	}
	
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
}