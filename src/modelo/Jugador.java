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
 * @version	1.0
 */
public abstract class Jugador throws AutoApagadoException{
	/* comentario acerca de la implementacion de la clase */
	
	/**
	 * Documentacion
	 */
	protected Auto auto;
	
	public void jugar(){
		
		//SI ENCENDIDO:
		if (auto.getMotor().isEncendido()){
			
			//SI ACELERA
			auto.getMotor().acelerar(true);
		
			//NO ACELERA
			auto.getMotor().acelerar(false);
		
			//SI SECUENCIAL
				
				//SI SUBE CAMBIO
				auto.getCaja().siguiente();
			
				//SI BAJA CAMBIO
				auto.getCaja().anterior();
			
		}else{
			throw new AutoApagadoException();
		}
			
	}
	
	public Auto getAuto() {
		return auto;
	}

	
}