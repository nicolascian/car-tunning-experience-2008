/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/


/**
 * Documentacion
 * 
 * una pista esta compuesta por una sucecion de superficies
 * y tiene una logitud determinada
 * 
 * @version	1.0
 */
public class Pista{
	/* comentario acerca de la implementacion de la clase */
	
	private Jugador jugador1;
	private Jugador jugador2;
	
	private Auto auto1;
	private Auto auto2;
	
	private double Longitud;
	
	private Clima clima;
	
	private Superficie superficie;
	
	Pista(Jugador player1, Jugador player2, double longitud){
		Longitud = longitud;
		
		jugador1 = player1;
		jugador2 = player2;
		
		auto1 = jugador1.getAuto();
		auto2 = jugador2.getAuto();
	}

	public double getLongitud() {
		return Longitud;
	}
	
}