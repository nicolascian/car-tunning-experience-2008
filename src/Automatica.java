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
			
	public void Chequear(double variacion){
	  try{	
		//revolucionesOptimas - revolucionesActuales
		double relacion = auto.getMotor().getRevolucionesMaximas() - auto.getMotor().getRPM(); 
		
		/*SI ESTA ACLERANDO*/
		if ((variacion > 0)&&(relacion <= 300)){ this.siguiente(); }
		
		/*SI ESTA FRENANDO*/
		if ((variacion < 0)&&(relacion >= 1200)){ this.anterior(); }
	  }catch(ExceptionCambioNoValido e){};
	}
	
	/**
	 * @Pre:
	 * @Post: Se ha creado una instancia de la clase Automatica segun los parametros detallados a continuación.
	 * @param auto: auto que contiene a la instancia.  
	 * @param cantidadCambios: cantidad de cambios que posee la caja, sin contar la reversa y punto
	 * muerto. Debe entre 4 y 8.
	*/
	public Automatica(Auto auto, int cantidadCambios, AlgoPesos precio){
		super(auto,cantidadCambios, precio);
	}
		
	protected void generarRelacionesDeCaja(){
		for(int cursor=0;cursor<=cantidadCambios;cursor++)
		  if(cursor==0)	   
			relacionDeCambio[cursor]=15/(cursor-0.3)-9/(cantidadCambios*cantidadCambios);
		  else
			relacionDeCambio[cursor]=1;  
	}	
	
	public void desgastar(){
       //tener en cuenta la temperatura del clima
	    	
	}
}