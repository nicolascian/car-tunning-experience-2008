package modelo;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 * 
 * aca esta el punto de entrada
 * 
 * @version	1.0
 */
public class Juego{
	/* comentario acerca de la implementacion de la clase */
	
	public static void main(String args[]){
		
		Auto auto1 = new Auto();
		Auto auto2 = new Auto();
		
		Habilidad habilidad = new Principiante();
		
		Jugador jugador1 = new Usuario(auto1);
		Jugador jugador2 = new Virtual(auto2, habilidad);
		
		Pista pista = new Pista(jugador1, jugador2, 100);
		
		jugador1.getAuto().getMotor().encender();
		jugador2.getAuto().getMotor().encender();
		
		boolean finCarrera = false;
		
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
			pista.chequearPosicion();
			
		}//FIN CICLO
		
		System.out.print("Finaliz� la carrera");
		
	}
	
}