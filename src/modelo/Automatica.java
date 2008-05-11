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
 * una caja auomatica hace los cambios sola, y de manera secuencial
 * 
 * @version	1.0
 */
public class Automatica extends Caja{
	/* comentario acerca de la implementacion de la clase */


	/**
	 * cada vez que el motor cambia sus revoluciones, se invoca este metodo
	 * 
	 *  si se esta acelerando el motor, y las revoluciones alcanzan cierto nivel, 
	 *  la caja sube un cambio
	 *  
	 *  si no se esta acelerando el motor, y las rpm bajan de cierto nivel,
	 *  la caja baja un cambio
	 */	
	public void Chequear(){
		double rpm=getAuto().getMotor().getRPM();
		Motor motor=getAuto().getMotor();
		if(motor.isAcelerando()){
			if(rpm>=motor.getRevolucionesMaximasCambio())
				setCambio(getCambio()+1);
		}
		else
			if(rpm<motor.getRevolucionesMinimasEncendido())
				setCambio(getCambio()-1);
	}	
	
	/**
	 * @Pre:
	 * @Post: Se ha creado una instancia de la clase Automatica segun los parametros detallados a continuaci�n.
	 * @param auto: auto que contiene a la instancia.  
	 * @param cantidadCambios: cantidad de cambios que posee la caja, sin contar la reversa y punto
	 * muerto. Debe entre 4 y 8.
	*/
	public Automatica(Auto auto, int cantidadCambios){
		super(auto,cantidadCambios);
	}
		
	protected void generarRelacionesDeCaja(){
		for(int cursor=0;cursor<=cantidadCambios;cursor++)
		  if(cursor!=0)	   
			relacionDeCambio[cursor]=15/(cursor-0.3)-9/(cantidadCambios*cantidadCambios);
		  else
			relacionDeCambio[cursor]=1/60;  
	}	
	
	public void desgastar(){
	  if((System.currentTimeMillis()-getTiempoDeUltimoDesgaste())>=TIEMPO_MINIMO_ENTRE_DESGASTES){	
		
	  }  	
	}

	
}