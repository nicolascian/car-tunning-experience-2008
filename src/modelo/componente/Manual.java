/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente;

/**
 * Una instancia de esta clase modela una caja manual estandar en la cual se puede pasar de un cambio
 * a otro en forma arbitraria. 
 * @version	1.0
*/
public class Manual extends Caja{
	/* comentario acerca de la implementacion de la clase */

	/**
	 * @Pre:
	 * @Post: Se ha creado una instancia de la clase Manual segun los parametros detallados a 
	 * continuacion.
	 * @param cantidadCambios: cantidad de cambios que posee la caja. Debe entre 4 y 6 sin contar el
	 * punto muerto.En esta implementacion no existe la reverza.
	 */
	public Manual(int cantidadCambios) {
		super(cantidadCambios);
		setPeso(85);
	}

	/**
	 * @Pre: 
	 * @Post: Se han generado las relaciones de caja correspondientes.
	 * 
	*/
	protected void generarRelacionesDeCaja(){
		for(int cursor=0;cursor<=getCantidadCambios();cursor++)
			if(cursor!=0)		 
			  setRelacionDeCambio(cursor,15.0/cursor-9.0/(getCantidadCambios()*getCantidadCambios()));
			else
			  setRelacionDeCambio(cursor,60.0);  
	}
	
	/**
	 * @Pre: La instancia ha sido creada.
	 * @Post:Se ha seteado el cambio. Cada vez que hacemos un Cambio, se altera las 
	 * revolucionesMaximas del Motor.
	*/
	public void setCambio(int cambio){
		super.setCambio(cambio);
	}

}