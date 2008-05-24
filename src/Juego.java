/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

import modelo.*;
import control.*;
import vista.*;

/**
 * Juego es el punto de entrada del programa "Car Tunning Experience 2008"
 *	 
 * @version	1.0
 * @author Nicolas Cian
 * @author Rafael Putaro
 * @author Nicolas Morandi
 * @author Lucas Uccello
 * @author Maxi Santos
 * 
 * @see <a href="http://code.google.com/p/car-tunning-experience-2008/">Car Tunning Experience 2008</a>
 */
public class Juego{
		
	public static void main(String args[]){

		Auto auto1 = new Auto(); //creo un auto	
		
		Jugador jugador1 = new Usuario(auto1); //creo un control y le paso el auto 
		//Jugador jugador2 = new Virtual(new Principiante, auto2); //creo un control automatico y le paso, habilidad y auto
		
		Pista pista = new Pista(auto1, auto1, 100); //creo una pista y le pongo el auto
		

		//se inicializan las vistas
 	
		new VistaConsola(auto1, pista); //creo una vista consola, y le paso el auto y la pista
		new VistaVentana(pista, jugador1); //creo una vista

		// esto es necesario para que las vistas se actualicen la 1ra vez
		pista.ActualizarObservadores();
		
	
	}// fin main
	
}

