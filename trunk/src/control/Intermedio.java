/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package control;

import modelo.Auto;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Clase Intermedio
 * 
 * Es la capacidad que se encuentra ubicada entre la mayor y la menor disponible 
 * para la conduccion del auto por la computadora.
 * 
 * Hereda de la clase Habilidad.
 * 
 * @version	1.0
 * @see modelo.Habilidad Habilidad
 */
public class Intermedio extends Habilidad{
	/* usamos Margen de error y tiempo de reaccion */
	
	private final static double MARGEN_DE_ERROR_INTERMEDIO = 3.5;
	
	/** tiempo de reaccion de INTERMEDIO */
	private static final long mSecsControl = 250; //0.25 segundos
	
	/**
	 * Metodo Jugar
	 * 
	 * este metodo permite desarrollar los cambios durante una carrera,
	 * se ejecuta indicando que es el turno de jugar, de dicho jugador.
	 */
	public void jugar(){
		
		/* si el auto esta apagado */
		if (!auto.isEncendido()){
			auto.setEncendido(true);
		}
		
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
		
	
		/* numeros aleaorios entre 0 y 2.5 incluidos */
        double x = (rnd.nextDouble() * MARGEN_DE_ERROR_INTERMEDIO);
        /* nuemero aleatorio entre 0 y 2.5 incluidos */
        double y = (rnd.nextDouble() * MARGEN_DE_ERROR_INTERMEDIO);
        /* numero entre -250 y 250 iincluidos */
        MARGEN_DE_ERROR_RND_MAXIMAS = (x - y)*100;
        /* numero entre 0 y 250 tomando x e y */
        MARGEN_DE_ERROR_RND_MINIMAS = ((x + y) - (x + y)/2) *100;
        
		
		
		if(auto.isAcelerando()){
			if(rpm >= (auto.getCaja().getRevolucionesMaximasMotorParaCambioActual()+ MARGEN_DE_ERROR_RND_MAXIMAS) ){
				auto.embragar(true);
				auto.getCaja().setCambio(auto.getCaja().getCambio()+1);
				auto.embragar(false);
			}
		}else
			if(rpm< (auto.getMotor().getRevolucionesMinimasEncendido()+ MARGEN_DE_ERROR_RND_MINIMAS) ){
				auto.embragar(true);
				auto.getCaja().setCambio(auto.getCaja().getCambio()-1);
				auto.embragar(false);
			}
		
	}
	
	/**
	 * Constructor de Intermedio
	 * Crea un Timer
	 */
	public Intermedio(Auto auto){
		super();
		this.auto = auto;
		//lanzamos el timer
        Timer t = new Timer();
        t.schedule(new Temporizador(), mSecsControl, mSecsControl);
	}
	
	/**
	 * Clase interna Temporizador
	 * Hereda de TimerTask e implementa run();
	 */
	private class Temporizador extends TimerTask {
		
		public void run() {
			jugar();
        }
        
	}
	
	/* toString */
	
	public String toString() {
		return "Intermedio";
	}
}