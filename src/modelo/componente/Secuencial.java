/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente;

import org.w3c.dom.Element;
import org.w3c.dom.Document;

/**
 * Modela una caja secuencial, en la cual se puede pasar los cambios como su nombre lo dice
 * de manera secuencial, con las primitivas siguiente y anterior.
 * 
 * @version	1.0
 */
public class Secuencial extends Caja{
	/* comentario acerca de la implementacion de la clase */
	
	/**
	 * @Pre:
	 * @Post: Se ha creado una instancia de la clase Secuencial segun los parametros detallados a 
	 * continuacion.
	 * @param cantidadCambios: cantidad de cambios que posee la caja. Debe entre 4 y 6 sin contar el
	 * punto muerto.En esta implementacion no existe la reverza.
	 */
	public Secuencial(int cantidadCambios){
		super(cantidadCambios);
		setPeso(85);
	}

	public Element toXml(Document doc) {
		Element xmlElement = doc.createElement("secuencial");
		xmlElement.setAttribute("estado", String.valueOf(this.getEstado()));
		return xmlElement;
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