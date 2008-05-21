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
/*
 * Creacion de dos autos que realizaran la competencia.
 */		
		Auto auto1 = new Auto();
		Auto auto2 = new Auto();
		
		Habilidad habilidad = new Principiante();
/*
 * Se crean dos jugadores uno virtual con un determinado nivel
 * de habilidad y dificualtad para con el jugador que utilice el usuario.
 */		
		
		Jugador jugador1 = new Usuario(auto1);
		Jugador jugador2 = new Virtual(habilidad, auto2);
/*
 * Se crea la pista con los jugadores y su longitud.
 */		
		Pista pista = new Pista(jugador1, jugador2, 100);
		
/*
 * se inicializan las vistas
 */		
		new VistaConsola(auto1, pista);
		new VistaControles(pista, jugador1);

//		 esto es necesario para que las vistas se actualicen la 1ra vez
		pista.ActualizarObservadores();
	
	}// fin main
	
}

