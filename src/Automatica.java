/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

import java.lang.Thread; 

/**
 * Documentacion
 * 
 * una caja auomatica hace los cambios sola, y de manera secuencial
 * 
 * @version	1.0
 */
public class Automatica extends Caja{
	/* comentario acerca de la implementacion de la clase */
	
	protected long latencia;
	
	public void Chequear(double variacion){
	  try{	
		//revolucionesOptimas - revolucionesActuales
		double relacion = auto.getMotor().getRevolucionesMaximas() - auto.getMotor().getRPM(); 
		
		/*SI ESTA ACLERANDO*/
		if ((variacion > 0)&&(relacion <= 300)){ this.siguiente(); }
		
		/*SI ESTA FRENANDO*/
		if ((variacion < 0)&&(relacion >= 1200)){ this.anterior(); }
	  }catch(ExceptionCambioNoValido e){};
	}
	
	/**
	 * @Pre:
	 * @Post: Se ha creado una instancia de la clase Automatica segun los parametros detallados a continuación.
	 * @param auto: auto que contiene a la instancia.  
	 * @param cantidadCambios: cantidad de cambios que posee la caja, sin contar la reversa y punto
	 * muerto. Debe entre 4 y 8.
	*/
	public Automatica(Auto auto, int cantidadCambios){
		super(auto,cantidadCambios);
	}
		
	
	public void desgastar(){
    //tener en cuenta la temperatura del clima
	}
		
	/**
	 * @Pre: Se ha creado una instancia de la Automatica segun los parametros.
	 * @Post: Se ha obtenido la potencia entregada por la caja a una cantidad
	 * de Rpm y cambios dado, teniendo en cuenta una fuerza de rodamiento de
	 * 136N con una rueda de 1.872 metros de circunferencia.
	 * 
	*/
	public double obtenerPotencia(){
    //tener en cuenta la temperatura del clima
		double potencia=obtenerRpm()*1.872*136;
		return potencia;
	}
}