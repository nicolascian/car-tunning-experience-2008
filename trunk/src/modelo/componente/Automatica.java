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
 * @Documentacion: Una instancia de la clase caja Automatica modela una caja automatica de un auto,
 * como tal efectua los cambios en forma automatizada de acuerdo a las revoluciones del motor,
 * siempre controlando si se esta acelerando o no.
 * 
 * @version	1.0
 */
public class Automatica extends Caja{
	
	/**
	 * @Pre: La instancia de la clase Automatica ha sido creada, y su atributo auto tiene una instancia de la 
	 * clase Auto la cual es no nula y se encuentra listo para carrera.
	 * 
	 * @Post: Se podrucen los siguientes estados finales:
	 *  
	 *  1)Si se esta acelerando el motor, y las revoluciones alcanzan cierto nivel, 
	 *  la caja sube un cambio
	 *  
	 *  2) Si no se esta acelerando el motor, y las rpm bajan de cierto nivel,
	 *  la caja baja un cambio
	*/	
	public void Chequear(){
		  double rpm=getAuto().getMotor().getRPM();
		  Motor motor=getAuto().getMotor();
		  if(motor.isAcelerando()){
			if(rpm>=getRevolucionesMaximasMotorParaCambioActual()){	
				embragar(true);
				setCambio(getCambio()+1);
				embragar(false);
			}
		  }
		  else
			if(rpm<=getRevolucionesMinimasMotorParaCambioActual()){
				embragar(true);
				setCambio(getCambio()-1);
				embragar(false);
			}
	}	
		
	/**
	 * @Pre:
	 * @Post: Se ha creado una instancia de la clase Automatica segun los parametros detallados a 
	 * continuacion.
	 * @param cantidadCambios: cantidad de cambios que posee la caja. Debe entre 4 y 6 sin contar el
	 * punto muerto.En esta implementacion no existe la reverza.
	*/
	public Automatica(int cantidadCambios){
		super(cantidadCambios);
		setPeso(80);
		setPrecio(new AlgoPesos(3000,00)); //algo$
	}

	/**
	 * Persistencia
	 * @param xmlElement
	 */
	public Automatica(Element xmlElement){
		//levanto los valores
		super( Integer.parseInt(xmlElement.getAttribute("cantcambios")));
		this.estado=( Double.parseDouble(xmlElement.getAttribute("estado")) );
		setCoefProdFzaAlPasarACambioMayor(Double.parseDouble(xmlElement.getAttribute("coefcambiomayor")));
		setCoefProdFzaAlPasarACambioMenor(Double.parseDouble(xmlElement.getAttribute("coefcambiomenor")));
		setRevolucionesMaximasMotorParaCambioActual(Double.parseDouble(xmlElement.getAttribute("revmaximas")));
		setRevolucionesMinimasMotorParaCambioActual(Double.parseDouble(xmlElement.getAttribute("revminimas")));
		setPrecio(new AlgoPesos(3000,00)); //algo$
		setPeso(80);
	}
	
	public Element toXml(Document doc) {
		Element xmlElement = doc.createElement("caja");
		xmlElement.setAttribute("tipo", "automatica");
		xmlElement.setAttribute("estado", String.valueOf(this.getEstado()));
		xmlElement.setAttribute("cantcambios", String.valueOf(this.getCantidadCambios()));
		xmlElement.setAttribute("coefcambiomayor", String.valueOf(this.getCoefProdFzaAlPasarACambioMayor()));
		xmlElement.setAttribute("coefcambiomenor", String.valueOf(this.getCoefProdFzaAlPasarACambioMenor()));
		xmlElement.setAttribute("revmaximas", String.valueOf(this.getRevolucionesMaximasMotorParaCambioActual()));
		xmlElement.setAttribute("revminimas", String.valueOf(this.getRevolucionesMinimasMotorParaCambioActual()));
		return xmlElement;
	}
			
	/**
	 * @Pre: 
	 * @Post: Se han generado las relaciones de caja correspondientes.
	*/
	protected void generarRelacionesDeCaja(){
		for(int cursor=0;cursor<=getCantidadCambios();cursor++)
		  if(cursor!=0)	   
			setRelacionDeCambio(cursor,15.0/(cursor-0.5)-9.0/(getCantidadCambios()*getCantidadCambios()));
		  else
			setRelacionDeCambio(cursor,60.0);  
	}
	
}