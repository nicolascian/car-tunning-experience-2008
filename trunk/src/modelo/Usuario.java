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
	 * por default el nombre es: USER_DEFAULT_NAME
	 * 
	 * Al ejecutar este constructor, el Usuario se crea un
	 * Auto para ser utilizado por el mismo
	 */
	public Usuario(){
		super(USER_DEFAULT_NAME);
		/* se crea un auto por defecto para el */
		setAuto(new Auto());
	}
	
	/**
	 * Constructor con parametros de Usuario
	 * 
	 * @param nombre recibe el auto con el cual competira
	 * @param auto recibe el nombre con el cual se identifica
	 */
	public Usuario(String nombre, Auto auto){
		super(nombre);
		setAuto(auto);
	}
	
	/**
	 * Constructor con parametro de Usuario
	 * 
	 * por default el nombre es: USER_DEFAULT_NAME
	 * 
	 * @param auto recibe el auto con el cual competira
	 */
	public Usuario(Auto auto){
		super(USER_DEFAULT_NAME);
		setAuto(auto);
	}
	
	/**
	 * Constructor con parametro de Usuario
	 * 
	 * @param nombre recibe el nombre con el cual se identifica
	 */
	public Usuario(String nombre){
		super(nombre);
		setAuto(new Auto());
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
		
		/* SI ENCENDIDO: */
		if (auto.isEncendido()){
			
			/* SI ESTA ACELERANDO */
			if (control.Acelerador.presionado()){
				auto.acelerar(true);
				
			}else{
			/* SI NO ESTA ACELERANDO */
				auto.acelerar(false);
			}
		
			/* SI CAJA SECUENCIAL */
			if (auto.isSecuencial()){

				if (control.Palanca.fuePresionado()){
				
					/* SI SUBE CAMBIO */
					if (control.Palanca.subirCambio()){
						auto.getCaja().siguiente();
					}
			
					/* SI BAJA CAMBIO */
					if (control.Palanca.bajarCambio()){
						auto.getCaja().anterior();
					}
				
				}
			
			}// fin if secuencial
				
			/* SI CAJA MANUAL */
			if (auto.isManual()){

				/* SI HACE UN CAMBIO */
				if (control.Palanca.fuePresionado()){
					
					int Cambio = control.Palanca.cambioPresionado();
					auto.getCaja().setCambio(Cambio);
				}

			}// fin if manual
			
			
		}else{
		
			throw new ExceptionAutoApagado();
			
		}//fin if encendido
		
	}//fin jugar
	
}