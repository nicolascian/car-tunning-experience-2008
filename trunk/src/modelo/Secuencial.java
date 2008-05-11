package modelo;
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
	 * detallados a continuaci�n.
	 * @param auto: auto que contiene a la instancia.  
	 * @param cantidadCambios: cantidad de cambios que posee la caja, sin contar la reversa y punto
	 * muerto. Debe entre 4 y 8.
	*/
	public Secuencial(Auto auto, int cantidadCambios){
		super(auto, cantidadCambios);
	}

	/**
	 * @Pre: La instancia ha sido creada.
	 * @Post:Se ha subido un cambio. Cada vez que hacemos un Cambio, se altera las 
	 * revolucionesMaximas del Motor.
	*/
	public void siguiente(){
		setCambio(getCambio()+1);
	}
	
	/**
	 * @Pre: La instancia ha sido creada.
	 * @Post:Se ha bajado un cambio. Cada vez que hacemos un Cambio, se altera las 
	 * revolucionesMaximas del Motor.
	*/
	public void anterior(){
		setCambio(getCambio()-1);
	}
		
	/**
	 * @Pre: 
	 * @Post: Se han generado las relaciones de caja correspondientes.
	 * 
	*/
	protected void generarRelacionesDeCaja(){
		for(int cursor=1;cursor<=cantidadCambios;cursor++)
	     if(cursor!=0)		    
			relacionDeCambio[cursor]=15/(cursor-0.15)-9/(cantidadCambios*cantidadCambios);
	     else
	    	relacionDeCambio[cursor]=1/60;   
	}	
		
	/* Como esta caja no es automatica, no hace nada */
	public void Chequear(){}
	
	public void desgastar(){
		
	}

	
}