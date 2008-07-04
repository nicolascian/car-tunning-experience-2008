/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package control;

import modelo.Auto;

import java.awt.event.*;
import javax.swing.Timer;


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
	/* usamos Margen de error y tiempo de reaccion */
	
	private final static double MARGEN_DE_ERROR_PRINCIPIANTE = 6.0;
	
	/** tiempo de reaccion de PRINCIPIANTE */
	private static final int mSecsControl = 500; //0.5 segundos
	
	private Timer timer;
	
	/**
	 * Metodo Jugar
	 * 
	 * este metodo permite desarrollar los cambios durante una carrera,
	 * se ejecuta indicando que es el turno de jugar, de dicho jugador.
	 */
	public void jugar(boolean X){
		
		if(X){ timer.start();
		}else { timer.stop(); }
	}
	
	public void manejar(){	
		
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
	
	
		/* numeros aleaorios entre 0 y 5 incluidos */
        double x = (rnd.nextDouble() * MARGEN_DE_ERROR_PRINCIPIANTE);
        /* nuemero aleatorio entre 0 y 5 incluidos */
        double y = (rnd.nextDouble() * MARGEN_DE_ERROR_PRINCIPIANTE);
        /* numero entre -500 y 500 iincluidos */
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
	 * Constructor de Principiante
	 * Crea un Timer
	 */
	public Principiante(Auto auto){
		super();
		this.auto = auto;
		
		//creamos el ActionListener para el timer  
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				manejar();
  	      	}
  	  	};
  	  
  	  	//creamos el timer
  	  	timer = new Timer(mSecsControl, taskPerformer);
	}
	

	
	/* toString */
	
	public String toString() {
		return "Principiante";
	}
	
}