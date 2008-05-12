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
/**
 * Creacion de dos autos que realizaran la competencia.
 */		
		Auto auto1 = new Auto();
		Auto auto2 = new Auto();
		
		Habilidad habilidad = new Principiante();
/**
 * Se crean dos jugadores uno virtual con un determinado nivel
 * de habilidad y dificualtad para con el jugador que utilice el usuario.
 */		
		Jugador jugador1 = new Usuario(auto1);
		Jugador jugador2 = new Virtual(auto2, habilidad);
/**
 * Se crea la pista con los jugadores y su longitud.
 */		
		Pista pista = new Pista(jugador1, jugador2, 100);
/**
 * Se encienden los motores de los autos.
 */		
		jugador1.getAuto().getMotor().encender();
		jugador2.getAuto().getMotor().encender();
		
		boolean finCarrera = false;
/**
 * Se controlara los turnos de los jugadores
 * que  cada uno juegue en su turno de lo contrario terminaria la carrera.
 * Ademas se corrobora que la pista no termine, o sea, que algun auto llegue a la meta,
 * lo que marcaria la finalizacion de la carrera y el ganador.
 */		
		while(!finCarrera){
			//Control1.acelerando ? true
			
			try{
				jugador1.jugar();
				
			}catch (Exception e){
				finCarrera=true;
			}
			
			try{
				jugador2.jugar();
				
			}catch (Exception e){
				finCarrera=true;
			}
			
			double Long = pista.getLongitud();
			
			if ( (auto1.getPosicion() == Long) ||
				 (auto2.getPosicion() == Long) ){
				finCarrera = true;
			}
			
			/* verifica el tramo de cada auto */
			//pista.chequearPosicion();
			
		}//FIN CICLO
		
		System.out.print("Finaliz� la carrera");
		
	}
	
}