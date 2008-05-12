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
	
	private control.Acelerador acelerador;
	private control.Palanca palanca;
	
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
		
	/* ------------------------------------------------------------------------------------------ */
		acelerador = new control.Acelerador();  
		palanca = new control.Palanca();
	/* ------------------------------------------------------------------------------------------ */
		
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
		
		/* ------------------------------------------------------------------------------------------ */
		acelerador = new control.Acelerador();  
		palanca = new control.Palanca();
	/* ------------------------------------------------------------------------------------------ */
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
		
		/* ------------------------------------------------------------------------------------------ */
		acelerador = new control.Acelerador();  
		palanca = new control.Palanca();
	/* ------------------------------------------------------------------------------------------ */
	}
	
	/**
	 * Constructor con parametro de Usuario
	 * 
	 * @param nombre recibe el nombre con el cual se identifica
	 */
	public Usuario(String nombre){
		super(nombre);
		setAuto(new Auto());
		
		/* ------------------------------------------------------------------------------------------ */
		acelerador = new control.Acelerador();  
		palanca = new control.Palanca();
	/* ------------------------------------------------------------------------------------------ */
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
		
			/* vemos que pasa con el acelerador */
			resolverAcelerador();
			
			/* vemos que pasa con el freno */
			resolverFrenos();
		
			/* SI HACE UN CAMBIO */
			if (palanca.fuePresionado()){ resolverCambios(); }
		
			
		}else{
		
			throw new ExceptionAutoApagado();
			
		}//fin if encendido
		
	}//fin jugar
	
	
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
	
	private void resolverAcelerador(){
		
		/* SI ESTA ACELERANDO */
		if (acelerador.isPresionado()){
			auto.acelerar(true);
			
		}else{
		/* SI NO ESTA ACELERANDO */
			auto.acelerar(false);
		}
		
	}
	
	private void resolverFrenos(){
		
	}
	
	
}