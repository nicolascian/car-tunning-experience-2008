/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

/**
 * Clase Principiante
 * 
 * Es la menor capacidad disponible para la conduccion del auto por la computadora.
 * 
 * Hereda de la clase Habilidad.
 * 
 * @version	1.0
 * @see modelo.Habilidad Habilidad
 */
public class Principiante extends Habilidad{
	/* comentario acerca de la implementacion de la clase */
	
	
	private double MARGEN_DE_ERROR;
	
	public void jugar(){
		
		/* ACELERAR */
		auto.acelerar(true);
		
		
		double rpm = auto.getMotor().getRPM();
		
		Motor motor = auto.getMotor();
		
		//MARGEN_DE_ERROR = Aleatorio!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!groso
		
		if(motor.isAcelerando()){
			
			if( rpm >= (motor.getRevolucionesMaximasCambio() + MARGEN_DE_ERROR) )
				auto.getCaja().setCambio(auto.getCaja().getCambio()+1);
		
		}else{
		
			if( rpm < (motor.getRevolucionesMinimasEncendido() + MARGEN_DE_ERROR) )
				auto.getCaja().setCambio(auto.getCaja().getCambio()-1);
		}
		
	}
	
	
	
	
}