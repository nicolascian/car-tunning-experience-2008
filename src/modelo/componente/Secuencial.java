/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente;
import modelo.*;

/**
 * En una caja secuencial podemos pasar los cambios;
 * como siguiente, y anterior.
 * 
 * @version	1.0
 */
public class Secuencial extends Caja{
	/* comentario acerca de la implementacion de la clase */
	
	/**
	 * @Pre:
	 * @Post: Se ha creado una instancia de la clase derivada de la clase Caja segun los parametros
	 * detallados a continuacion.
	 * @param auto: auto que contiene a la instancia.  
	 * @param cantidadCambios: cantidad de cambios que posee la caja, sin contar la reversa y punto
	 * muerto. Debe entre 4 y 8.
	*/
	public Secuencial(int cantidadCambios){
		super(cantidadCambios);
		setPeso(85);
	}

	/**
	 * @Pre: La instancia ha sido creada.
	 * @Post:Se ha subido un cambio. Cada vez que hacemos un Cambio, se altera las 
	 * revolucionesMaximas del Motor.
	*/
	public void siguiente(){
		embragar(true);
		setCambio(getCambio()+1);
		embragar(false);
	}
	
	/**
	 * @Pre: La instancia ha sido creada.
	 * @Post:Se ha bajado un cambio. Cada vez que hacemos un Cambio, se altera las 
	 * revolucionesMaximas del Motor.
	*/
	public void anterior(){
		embragar(true);
		setCambio(getCambio()-1);
		embragar(false);
	}
		
	/**
	 * @Pre: 
	 * @Post: Se han generado las relaciones de caja correspondientes.
	 * 
	*/
	protected void generarRelacionesDeCaja(){
		for(int cursor=0;cursor<=getCantidadCambios();cursor++)
		  if(cursor!=0)	   
			setRelacionDeCambio(cursor,15.0/(cursor-0.3)-9.0/(getCantidadCambios()*getCantidadCambios()));
		  else
			setRelacionDeCambio(cursor,60.0);  
	}
}