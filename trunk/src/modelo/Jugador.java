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
public abstract class Jugador {
	/* comentario acerca de la implementacion de la clase */
	
	/**
	 * Documentacion
	 */
	protected Auto auto;
	
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
		
		
			
	}
	
	public Auto getAuto() {
		return auto;
	}

	
}