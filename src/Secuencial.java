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
	
	/**
	 * @Pre:
	 * @Post: Se ha creado una instancia de la clase derivada de la clase Caja segun los parametros
	 * detallados a continuación.
	 * @param auto: auto que contiene a la instancia.  
	 * @param cantidadCambios: cantidad de cambios que posee la caja, sin contar la reversa y punto
	 * muerto. Debe entre 4 y 8.
	*/
	public Secuencial(Auto auto, int cantidadCambios){
		super(auto, cantidadCambios);
	}

	/* Como esta caja no es automatica, no hace nada */
	public void Chequear(double variacion){}
	
	public void desgastar(){
		//tener en cuenta la temperatura del clima
	}
	
	public double obtenerPotencia(){
		//tener en cuenta la temperatura del clima
		return 0;
		
	}
	
}