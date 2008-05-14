/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

/**
 * Juego es el punto de entrada del programa "Car Tunning Experience 2008"
 *	 
 * @version	1.0
 * @author Nicolas Cian
 * @author Rafael Putaro
 * @author Nicolas Morandi
 * @author Lucas Uccello
 * @author Maxi Santos
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
		
		
		boolean finCarrera = false;
/*
 * Se controlara los turnos de los jugadores
 * que  cada uno juegue en su turno de lo contrario terminaria la carrera.
 * Ademas se corrobora que la pista no termine, o sea, que algun auto llegue a la meta,
 * lo que marcaria la finalizacion de la carrera y el ganador.
 */		
		int cont=0;
		while(!finCarrera){
			
			jugador1.jugar();
			
			jugador2.jugar();
			
			/* vemos si alguien llego */
			double Long = pista.getLongitud();
			if ( (auto1.getPosicion() == Long) ||
				 (auto2.getPosicion() == Long) ){
				finCarrera = true;	}
			
			/* verifica el tramo de cada auto */
			try{
				pista.actualizarPosiciones();
			}catch (Exception e){
				System.out.println("Exception");
				finCarrera=true;
			}
			
			cont++;
			auto1.setPosicion(cont);
			auto2.setPosicion(cont);
			
		}//FIN CICLO
		
		System.out.print("Finalizo la carrera ");
		System.out.print("en: "+ cont + " turnos por jugador");
		
	}
	
}