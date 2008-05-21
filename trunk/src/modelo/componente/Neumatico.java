/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente;

import modelo.fuerzas.Fuerza;
import modelo.fuerzas.ReceptorDeFuerzas;

/**
 * El neumatico es aquel componente del auto que interactua en forma
 * directa con la superficie. Por lo tanto, es el encargado de transmitir
 * la pontecia del auto al movimiento.
 * Al estar en contacto directo con el suelo es afectable por la superficie, 
 * tanto como por el clima.
 */
public abstract class Neumatico extends Componente implements ReceptorDeFuerzas,
															  ComponenteContenidoEnComponente{
	
	/**
	 * Este atributo indica la potencia maxima que podra entregar un neumatico,
	 * que es baja en relacion con el resto de los componentes.
	 */
	private double potenciaMax;
	
	private Componente contenedor;
	
	protected final static double GRAVEDAD=9.8;
	
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

	public void setPotenciaMax(double potenciaMax) {
		this.potenciaMax = potenciaMax;
	}

	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#liberarFuerzas()
	 */
	@Override
	public void liberarFuerzas() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see modelo.ReceptorDeFuerzas#recibirFuerza(modelo.Fuerza)
	 */
	@Override
	public void recibirFuerza(Fuerza fuerza) {
		//de acuerdo al peso del auto se calcula la fuerza de rozamiento
		double valorFuerzaRetorno=getAuto().getPeso()*calcularAdherencia()*GRAVEDAD/(-100);
		try{
			valorFuerzaRetorno=valorFuerzaRetorno+fuerza.getValorDeLaFuerza();
		}catch (Exception e){}
		Fuerza fuerzaRetorno=new Fuerza(this,(ReceptorDeFuerzas)getComponenteContenedor(),
				                        valorFuerzaRetorno,true);
		((ReceptorDeFuerzas)getComponenteContenedor()).recibirFuerza(fuerza);
	}

	/* (non-Javadoc)
	 * @see modelo.Componente#desgastar()
	 */
	@Override
	public void desgastar() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see modelo.Componente#obtenerPotencia()
	 */
	@Override
	public double obtenerPotencia() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see modelo.ComponenteContenidoEnComponente#getComponenteContenedor()
	 */
	@Override
	public Componente getComponenteContenedor() {
		return contenedor;
	}

	/* (non-Javadoc)
	 * @see modelo.ComponenteContenidoEnComponente#setComponenteContenedor(modelo.Componente)
	 */
	@Override
	public void setComponenteContenedor(Componente contenedor) {
		this.contenedor=contenedor;
		
	}
	
	
}