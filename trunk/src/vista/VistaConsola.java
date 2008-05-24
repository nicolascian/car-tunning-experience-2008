/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package vista;
import modelo.*;

import java.util.Observable;
import java.util.Observer;

public class VistaConsola implements Observer
{	
	private Auto auto; //referencia al modelo
	private Pista pista; //referencia al modelo

	//Constructor de la vista
	public VistaConsola(Auto auto, Pista pista)
	{	

		// Conectamos esta vista con el modelo
		this.auto = auto;
		this.auto.addObserver(this); 
		
		// Conectamos esta vista con el modelo
		this.pista = pista;
		this.pista.addObserver(this);
	}

	//Metodo que es llamado por el modelo al actualizarse el mismo
	public void update(Observable t, Object o)
	{	
		
		System.out.println( 
		"===================================================================" + '\n' +
		" Caja tipo: " + " Manual: " + auto.isManual() + "   Secuencial: " + auto.isSecuencial() + "   Automatica: " + auto.isAutomatica() + '\n' + 
		"-------------------------------------------------------------------" + '\n' + '\n' +
				
		" Cambio: " + auto.getCaja().getCambio() + "   " +
		"RPM-> " + (long)auto.getMotor().getRPM() + "  " + "-" + "  " +
		 (long)auto.getCaja().getRevolucionesMaximasMotorParaCambioActual() + " <-RPMoptimas"  + '\n' + '\n' +
		
		" Velocidad: " + (long)auto.getVelocidad() + " Km/h" + "     " +
		"Distancia recorrida: " + (long)auto.getPosicion() + " m" + '\n' + '\n' +
		
		" Acelerando: " + auto.isAcelerando() + '\n' +
		"   Frenando: " + auto.isFrenando() + '\n' +
		"  Embragado: " + auto.isEmbragado() + '\n' +
		" Nitro act.: " + auto.isNitroActivado() + '\n' +
		"  Encendido: " + auto.isEncendido() + '\n' +  '\n' +
		
		"-------------------------------------------------------------------" + '\n' +
		" F:Acelerador D:Freno S:Embrague A:Nitro E:Encender" + '\n' +
		" Manual: I-1 K-2 O-3 L-4 P-5 Ñ-6 U.Neutro J.Reversa" + '\n' +
		" Secuencial: I++ K--" + '\n' +
		"===================================================================" + '\n' 
		);
		
	}
	
	
	/*FORMATO
	 * 
	 * 
	    ===================================================================
		 Caja tipo:  Manual: true   Secuencial: false   Automatica: false
		-------------------------------------------------------------------		
				
		 Cambio: 4   RPM-> 6400  -  6600 <-RPMoptimas
		
		 Velocidad: 120 Km/h     Distancia recorrida: 58 m
		
		 Acelerando: true
		   Frenando: false
		  Embragado: false
		 Nitro act.: false
		  Encendido: true
		
		-------------------------------------------------------------------
		 F:Acelerador D:Freno S:Embrague A:Nitro E:Encender
		 Manual: I-1 K-2 O-3 L-4 P-5 Ñ-6 U.Neutro J.Reversa
		 Secuencial: I++ K--
		===================================================================
	 * 
	 * 
	 */
		
}
