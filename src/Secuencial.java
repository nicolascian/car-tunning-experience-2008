/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 * 
 * en una caja secuencial podemos pasar los cambios;
 * como siguiente, y anterior.
 * 
 * @version	1.0
 */
public class Secuencial extends Caja{
	/* comentario acerca de la implementacion de la clase */
	
	public void siguiente(){
		if ( Cambio < CantidadCambios ){
			this.setCambio(Cambio + 1);
			this.desgastar();
		}
	}
	
	public void anterior(){
		if ( Cambio > 0 ){
			this.setCambio(Cambio - 1);
			this.desgastar();
		}
	}

	
	public void desgastar(){
		//tener en cuenta la temperatura del clima
	}
	
	public double obtenerPotencia(){
		//tener en cuenta la temperatura del clima
		return 0;
		
	}
	
}