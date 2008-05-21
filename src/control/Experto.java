/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/
 
package control;
import modelo.Auto;
import modelo.componente.Motor;

/**
 * Clase Experto
 * 
 * Es la mayor capacidad disponible para la conduccion del auto por la computadora.
 * 
 * Hereda de la clase Habilidad.
 * 
 * @version	1.0
 * @see modelo.Habilidad Habilidad
 */
public class Experto extends Habilidad{
	/* comentario acerca de la implementacion de la clase */
	
	private final static double MARGEN_DE_ERROR_EXPERTO = 1.5;
	
	/**
	 * Metodo Jugar
	 * 
	 * este metodo permite desarrollar los cambios durante una carrera,
	 * se ejecuta indicando que es el turno de jugar, de dicho jugador.
	 */
	public void jugar(){
		
		/* si el auto no esta acelerando */
		if (!auto.isAcelerando()){
			/* ACELERAR */
			auto.acelerar(true);
		}
		
		/* si la caja no es automatica */
		if (!auto.isAutomatica()){
			pasarCambios();
		}
		
	}
	
	
	private void pasarCambios(){
		
		double rpm = auto.getMotor().getRPM();
		
		Motor motor = auto.getMotor();
	
		/* numeros aleaorios entre 0 y 0.5 incluidos */
        double x = (rnd.nextDouble() * MARGEN_DE_ERROR_EXPERTO);
        /* nuemero aleatorio entre 0 y 0.5 incluidos */
        double y = (rnd.nextDouble() * MARGEN_DE_ERROR_EXPERTO);
        /* numero entre -50 y 50 iincluidos */
        MARGEN_DE_ERROR_RND_MAXIMAS = (x - y)*100;
        /* numero entre 0 y 50 tomando x e y */
        MARGEN_DE_ERROR_RND_MINIMAS = ((x + y) - (x + y)/2) *100;
        
		
		if(motor.isAcelerando()){
			
			if( rpm >= (motor.getRevolucionesMaximasCambio() + MARGEN_DE_ERROR_RND_MAXIMAS) ){
				auto.embragar(true);
				auto.getCaja().setCambio(auto.getCaja().getCambio()+1);
				auto.embragar(false);
			}
		
		}else{
		
			if( rpm < (motor.getRevolucionesMinimasEncendido() + MARGEN_DE_ERROR_RND_MINIMAS) ){
				auto.embragar(true);
				auto.getCaja().setCambio(auto.getCaja().getCambio()-1);
				auto.embragar(false);
			}
		}
		
	}
	
	
	public Experto(){
		super();
	}
	
	
	/* toString */
	
	public String toString() {
		return "Experto";
	}
	
}