/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente.neumaticos;

import modelo.*;
import modelo.componente.*;
import modelo.fuerzas.*;
/**
 * El neumatico es aquel componente del auto que interactua en forma
 * directa con la superficie. Por lo tanto, es el encargado de transmitir
 * la pontecia del auto al movimiento.
 * Al estar en contacto directo con el suelo es afectable por la superficie, 
 * tanto como por el clima.
 */
public abstract class Neumatico extends Componente implements ReceptorDeFuerzas{
	
	/**
	 * Este atributo indica la potencia maxima que podra entregar un neumatico,
	 * que es baja en relacion con el resto de los componentes.
	*/
	private double potenciaMax;
	
	private Llanta llanta;//indica el componente en el cual se encuentra
	
	/**
	 * Metodo que a partir del estado del neumatico y de las condiciones climaticas
	 * y de la superficie se encarga de calcular la adherencia.
	 * Devuelve un valor entre 0 y 1. 1 corresponde a una adherencia del 100%, y 
	 * 0 a una adherencia de 0%.
	*/
	public abstract double calcularAdherencia();

	public String toString(){
		String cadena = this.getNombre()+", Estado: "+this.getEstado()+" %.";
		return cadena;
	}

	public double getPotenciaMax() {
		return potenciaMax;
	}

	protected void setPotenciaMax(double potenciaMax) {
		this.potenciaMax = potenciaMax;
	}

	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#liberarFuerzas()
	 */
	@Override
	public void liberarFuerzas() {}

	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#recibirFuerza(modelo.Fuerza)
	 */
	@Override
	public void recibirFuerza(Fuerza fuerza) {
		//de acuerdo al peso del auto se calcula la fuerza de rozamiento
		double valorFuerzaDeRozamiento=0;
		if(getAuto().getVelocidad()>0)  
		  try{
			valorFuerzaDeRozamiento=(getAuto().getPeso()*calcularAdherencia()*Constantes.GRAVEDAD)/(-660);
		  }catch (Exception e){}
		//actualizar Rpm del neumatico
		//se retorna fuerza a llanta
		Fuerza fuerzaRetorno=new Fuerza(this,(ReceptorDeFuerzas)getLlanta(),
				                        valorFuerzaDeRozamiento,true);
		((ReceptorDeFuerzas)getLlanta()).recibirFuerza(fuerzaRetorno);
	}

	public Llanta getLlanta() {
		return llanta;
	}
	
	public void setLlanta(Llanta llanta) {
		this.llanta=llanta;
	}
	
	/**
	 * @Pre: Las instancias de las clases pasadas por parametro han sido creadas.
	 * @Post: Se instala la instancia en la instancia de Llanta correpondiente a la instacia de Auto
	 * pasada por parametro.
	*/
	public void instalar(Auto auto,Llanta llanta){
		this.setAuto(auto);
		this.setLlanta(llanta);
		try{
			getLlanta().setNeumatico(this);
		}catch(NullPointerException e){}
	}

}