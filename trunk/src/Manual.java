/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 * 
 * en una caja manual, podemos pasar de cambio directamente
 * haciendo setCambio(cambioNuevo) sin importar cual era el
 * cambio anterior
 * 
 * @version	1.0
 */
public class Manual extends Caja{
	/* comentario acerca de la implementacion de la clase */

	/**
	 * @Pre:
	 * @Post: Se ha creado una instancia de la clase derivada de la clase Manual segun los parametros
	 * detallados a continuación.
	 * @param auto: auto que contiene a la instancia.  
	 * @param cantidadCambios: cantidad de cambios que posee la caja, sin contar la reversa y punto
	 * muerto. Debe entre 4 y 8.
	*/	
	public Manual(Auto auto, int cantidadCambios) {
		super(auto, cantidadCambios);
		
	}

	/* Como esta caja no es automatica, no hace nada */
	public void Chequear(double variacion){}
	
	public void desgastar(){
    //tener en cuenta la temperatura del clima
		this.setEstado(Estado - 1/1000000000);
	}
	
	public double obtenerPotencia(){
    //tener en cuenta la temperatura del clima
		return 0;
		
	}
	
	
}