/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package control;
import modelo.Auto;

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
	 */
	public void jugar(){
		
		/* si gira la llave... */
		if (llave.fuePresionado()){
			/* encender el auto */
			auto.setEncendido(true);
		}
		
		/* SI ENCENDIDO: */
		if (auto.isEncendido()){
			
		
			/* vemos que pasa con el acelerador */
			resolverAcelerador();
			
			/* vemos que pasa con el freno */
			resolverFrenos();
		
			/* vemos que pasa con el nitro */
			resolverNitro();
			
			/* vemos que pasa con el freno */
			resolverEmbrague();
			
			/* SI HACE UN CAMBIO */
			if (palanca.fuePresionado()){ resolverCambios(); }
	
			
	     }
		
	}//fin jugar
	
	/**
	 * se encarga de resolver el tema de los cambios
	 */
	private void resolverCambios(){
		
		/* SI CAJA SECUENCIAL */
		if (auto.isSecuencial()){
		
			/* SI SUBE CAMBIO */
			if (palanca.subirCambio()){
				auto.getCaja().siguiente();
			}
	
			/* SI BAJA CAMBIO */
			if (palanca.bajarCambio()){
				auto.getCaja().anterior();
			}
		}// fin if secuencial
		
		/* SI CAJA MANUAL */
		if (auto.isManual()){
			
			int Cambio = palanca.cambioPresionado();
			auto.getCaja().setCambio(Cambio);
		}
		
	}
	
	/**
	 * se encarga de resolver el tema del acelerador
	 */
	private void resolverAcelerador(){
		
		/* SI ESTA ACELERANDO */
		if (acelerador.isPresionado()){
			auto.acelerar(true);
			
		}else{
		/* SI NO ESTA ACELERANDO */
			auto.acelerar(false);
		}
		
	}
	
	/**
	 * se encarga de resolver el tema del embrague
	 */
	private void resolverEmbrague(){
		
		/* SI ESTA EMBRAGANDO */
		if (embrague.isPresionado()){
			auto.embragar(true);
		}else{
		/* SI NO ESTA EMBRAGANDO */
			auto.embragar(false);
		}
		
	}
	
	/**
	 * se encarga de resolver el tema del freno
	 */
	private void resolverFrenos(){
		
		/* SI ESTA APRETANDO EL FRENO */
		if (freno.isPresionado()){
			//auto.frenar(true);
		}else{
		/* SI NO ESTA APRETANDO EL FRENO */
			//auto.frenar(false),
		}
		
	}
	
	/**
	 * se encarga de resolver el tema del nitro
	 */
	private void resolverNitro(){
		
		/* SI ESTA APRETANDO EL NITRO */
		if (nitro.isPresionado()){
			auto.activarNitro(true);
		}else{
		/* SI NO ESTA APRETANDO EL NITRO */
			auto.activarNitro(false);
		}
		
	}
	
	
	/* toString */
	
	public String toString() {
		return super.toString();
	}
	
}