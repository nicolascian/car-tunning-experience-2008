/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente;

import modelo.AlgoPesos;

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
		setPrecio(new AlgoPesos(2100,00)); //algo$
		setPeso(90);
	}

	/**
	 * Persistencia
	 * @param xmlElement
	 */
	public Secuencial(Element xmlElement){
		//levanto los valores
		super( Integer.parseInt(xmlElement.getAttribute("cantcambios")));
		this.estado=( Double.parseDouble(xmlElement.getAttribute("estado")) );
		setCoefProdFzaAlPasarACambioMayor(Double.parseDouble(xmlElement.getAttribute("coefcambiomayor")));
		setCoefProdFzaAlPasarACambioMenor(Double.parseDouble(xmlElement.getAttribute("coefcambiomenor")));
		setRevolucionesMaximasMotorParaCambioActual(Double.parseDouble(xmlElement.getAttribute("revmaximas")));
		setRevolucionesMinimasMotorParaCambioActual(Double.parseDouble(xmlElement.getAttribute("revminimas")));
		setPrecio(new AlgoPesos(2100,00)); //algo$
		setPeso(90);
	}
	
	public Element toXml(Document doc) {
		Element xmlElement = doc.createElement("caja");
		xmlElement.setAttribute("tipo", "secuencial");
		xmlElement.setAttribute("estado", String.valueOf(this.getEstado()));
		xmlElement.setAttribute("cantcambios", String.valueOf(this.getCantidadCambios()));
		xmlElement.setAttribute("coefcambiomayor", String.valueOf(this.getCoefProdFzaAlPasarACambioMayor()));
		xmlElement.setAttribute("coefcambiomenor", String.valueOf(this.getCoefProdFzaAlPasarACambioMenor()));
		xmlElement.setAttribute("revmaximas", String.valueOf(this.getRevolucionesMaximasMotorParaCambioActual()));
		xmlElement.setAttribute("revminimas", String.valueOf(this.getRevolucionesMinimasMotorParaCambioActual()));
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