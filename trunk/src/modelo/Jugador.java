/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

import java.awt.event.KeyListener;
import modelo.*;

/**
 * Clase Jugador (es una clase abstracta)
 * 
 * Un jugador puede ser "humano" (lo llamaremos Usuario) o "computadora" (lo llamaremos Virtual).
 * 
 * Humano = Usuario
 * Computadora = Virtual
 * 
 * @version	1.0
 * @see  modelo.Virtual  Virtual 
 * @see  modelo.Usuario  Usuario
 */
public abstract class Jugador{
	/* implementacion basada en polimorfismo */
	
	/**
	 * Todo jugador consta de un Auto para poder competir
	 */
	protected Auto auto;
	
	/** Un jugador tiene nombre */
	protected String nombre;
	
	protected AlgoPesos dinero;
	
	/* Constantes de Nombres */
	
	/** Es el nombre por defecto para un Usuario */
	protected final static String USER_DEFAULT_NAME = "Humano";
	/** Es el nombre por defecto para un Virtual */
	protected final static String PC_DEFAULT_NAME = "Computador";
	

	/**
	 * Constructor con nombre de Jugador
	 * 
	 * @param nombre recibe el nombre con el cual se identifica
	 */
	public Jugador(String nombre){
		setNombre(nombre);
	}
	
	public Jugador(){}
	
	
	/* setters y getters */
	
	public void setAuto(Auto auto){
		this.auto = auto;
	}
	
	public Auto getAuto() {
		return auto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/* toString */
	
	public String toString() {
		
		String cadena="JUGADOR: " +'\n' 
		            + "  Nombre: " + getNombre() +'\n' 
		            + "  Auto: " + getAuto().toString() +'\n' ;
		return(cadena);
	}


	public AlgoPesos getDinero() {
		return dinero;
	}


	public void setDinero(AlgoPesos dinero) {
		this.dinero = dinero;
	}
	
}
