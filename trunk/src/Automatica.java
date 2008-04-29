/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 * 
 * una caja auomatica hace los cambios sola, y de manera secuencial
 * 
 * @version	1.0
 */
public class Automatica extends Caja{
	/* comentario acerca de la implementacion de la clase */
	
	public void Chequear(double variacion){
		//revolucionesOptimas - revolucionesActuales
		double relacion = auto.getMotor().getRevolucionesOptimas() - auto.getMotor().getRPM(); 
		
		/*SI ESTA ACLERANDO*/
		if ((variacion > 0)&&(relacion <= 300)){ this.siguiente(); }
		
		/*SI ESTA FRENANDO*/
		if ((variacion < 0)&&(relacion >= 1200)){ this.anterior(); }
		
	}
	
	private void siguiente(){
		if ( Cambio < CantidadCambios ){
			this.setCambio(Cambio + 1);
			this.desgastar();
		}
	}
	
	private void anterior(){
		if ( Cambio > 0 ){
			this.setCambio(Cambio - 1);
			this.desgastar();
		}
	}

	
	public void desgastar(){
//		tener en cuenta la temperatura del clima
	}
	
	public double obtenerPotencia(){
//		tener en cuenta la temperatura del clima
		return 0;
		
	}
	
}