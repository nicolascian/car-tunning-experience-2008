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
	
	public void Chequear(){
		if ( (auto.getMotor().getRevolucionesOptimas() - auto.getMotor().getRPM()) == 200 ){
			if (/*ESTA ACELERANDO*/){
				this.siguiente();
			}else/*ESTA FRENANDO*/{
				this.anterior();
			}
		}
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

	
	public void desgastar(){}
	
	public double obtenerPotencia(){
		return 0;
		
	}
	
}