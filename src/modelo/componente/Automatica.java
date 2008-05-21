/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente;
import modelo.*;

/**
 * @Documentacion: Una instancia de la clase caja Automatica modela una caja automatica de un auto,
 * como tal efectua los cambios en forma automatizada de acuerdo a las revoluciones del motor,
 * siempre controlando si se esta acelerando o no.
 * 
 * @version	1.0
 */
public class Automatica extends Caja{

	/**
	 * @Pre: La instancia de la clase Automatica ha sido creada, y su atributo auto tiene una instancia de la 
	 * clase Auto la cual es no nula y se encuentra listo para carrera.
	 * @Post: Se podrucen los siguientes estados finales:
	 *  
	 *  1)Si se esta acelerando el motor, y las revoluciones alcanzan cierto nivel, 
	 *  la caja sube un cambio
	 *  
	 *  2) Si no se esta acelerando el motor, y las rpm bajan de cierto nivel,
	 *  la caja baja un cambio
	*/	
	public void Chequear(){
		double rpm=getAuto().getMotor().getRPM();
		Motor motor=getAuto().getMotor();
		if(motor.isAcelerando()){
			if(rpm>=motor.getRevolucionesMaximasCambio()){
				embragar(true);
				setCambio(getCambio()+1);
				embragar(false);
			}
		}
		else
			if(rpm<motor.getRevolucionesMinimasEncendido()){
				embragar(true);
				setCambio(getCambio()-1);
				embragar(false);
			}
	}	
	
	/**
	 * @Pre:
	 * @Post: Se ha creado una instancia de la clase Automatica segun los parametros detallados a continuaci�n.
	 * @param auto: auto que contiene a la instancia.  
	 * @param cantidadCambios: cantidad de cambios que posee la caja, sin contar la reversa y punto
	 * muerto. Debe entre 4 y 8.
	*/
	public Automatica(int cantidadCambios){
		super(cantidadCambios);
	}
	
	/**
	 * @Pre: 
	 * @Post: Se han generado las relaciones de caja correspondientes.
	*/
	protected void generarRelacionesDeCaja(){
		for(int cursor=0;cursor<=cantidadCambios;cursor++)
		  if(cursor!=0)	   
			relacionDeCambio[cursor]=15.0/(cursor-0.3)-9.0/(cantidadCambios*cantidadCambios);
		  else
			relacionDeCambio[cursor]=60.0;  
	}	
}