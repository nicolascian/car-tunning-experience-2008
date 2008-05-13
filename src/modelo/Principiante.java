/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;
import java.util.Random;

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
	
	private final static double MARGEN_DE_ERROR_PRINCIPIANTE = 6.0;
	
	public void jugar(){
		
		/* ACELERAR */
		auto.acelerar(true);
		
		
		double rpm = auto.getMotor().getRPM();
		
		Motor motor = auto.getMotor();
	
		/* numeros aleaorios entre 0 y 5 incluidos */
        double x = (rnd.nextDouble() * MARGEN_DE_ERROR_PRINCIPIANTE);
        /* nuemero aleatorio entre 0 y 5 incluidos */
        double y = (rnd.nextDouble() * MARGEN_DE_ERROR_PRINCIPIANTE);
        /* numero entre -500 y 500 iincluidos */
        MARGEN_DE_ERROR_RND_MAXIMAS = (x - y)*100;
        /* numero entre 0 y 250 tomando x e y */
        MARGEN_DE_ERROR_RND_MINIMAS = ((x + y) - (x + y)/2) *100;
		
        
		if(motor.isAcelerando()){
			
			if( rpm >= (motor.getRevolucionesMaximasCambio() + MARGEN_DE_ERROR_RND_MAXIMAS) )
				auto.getCaja().setCambio(auto.getCaja().getCambio()+1);
		
		}else{
		
			if( rpm < (motor.getRevolucionesMinimasEncendido() + MARGEN_DE_ERROR_RND_MINIMAS) )
				auto.getCaja().setCambio(auto.getCaja().getCambio()-1);
		}
		
	}
	
	
	public Principiante(){
		super();
	}
	
	
	/* toString */
	
	public String toString() {
		return "Principiante";
	}
	
}