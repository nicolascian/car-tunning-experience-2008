/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

/**
 * Clase Usuario 
 * 
 * Se define al Usuario como aquel "humano" que competira en el juego contra la computadora o contra otro jugador.
 * 
 * Hereda de Jugador.
 * 
 * @version	1.0
 * @see  modelo.Jugador  Jugador
 */
public class Usuario extends Jugador{
	/* comentario acerca de la implementacion de la clase */
	
	/**
	 * Constructor sin parametros de Usuario
	 * 
	 * Al ejecutar este constructor, el Usuario se crea un
	 * Auto para ser utilizado por el mismo
	 */
	public Usuario(){
		/* se crea un auto por defecto para el */
		setAuto(new Auto());
	}
	
	/**
	 * Constructor con parametro de Usuario
	 * 
	 * @param auto recibe el auto con el cual competira
	 */
	public Usuario(Auto auto){
		setAuto(auto);
	}
	
	/**
	 * Metodo Jugar
	 * 
	 * este metodo permite desarrollar los cambios durante una carrera,
	 * se ejecuta indicando que es el turno de jugar, de dicho jugador.
	 * 
	 * @throws ExceptionAutoApagado
	 */
	public void jugar() throws ExceptionAutoApagado{
		
		//SI ENCENDIDO:
		if (auto.getMotor().isEncendido()){
			
			//SI ESTA ACELERANDO
			auto.getMotor().acelerar(true);
		
			//SI NO ESTA ACELERA
			auto.getMotor().acelerar(false);
		
			//SI CAJA SECUENCIAL
			//ver sintaxis*****
			//	auto.getCaja().isInstanceOf(Secuencial);
				//SI SUBE CAMBIO
				//auto.getCaja().siguiente();
			
				//SI BAJA CAMBIO
				//auto.getCaja().anterior();
			
		}else{
		
			throw new ExceptionAutoApagado();
		}
		
	}//fin jugar
	
}