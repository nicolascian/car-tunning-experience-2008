package modelo;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Un tramo es un sector de la pista a lo largo del cual se mantienen las
 * mismas condiciones tanto climaticas como de superficie.
 * Posee un largo caracteristico dado por la posicion inicial y final dentro
 * de la pista.
 */
public class Tramo {
	
	private Superficie superficie;
	private Clima clima;
	/**posInicial indica la posicion incial del tramo dentro de la pista*/
	private double posInicial;
	/**posFinal indica la posicion final del tramo dentro de la pista*/
	private double posFinal;
	
	public Tramo(){
		posInicial = 0;
		posFinal= 100;
		clima = new Clima();
		superficie = new Superficie();
	}
	public Tramo (double largo){
		posInicial = 0;
		posFinal = largo;
		clima = new Clima();
		superficie = new Superficie();
	}
	public boolean estaAutoEnTramo(Auto auto){
		
		return (auto.getPosicion() < posFinal);
		
	}
	
	
	/* setter y getters */
	
	public Clima getClima() {
		return clima;
	}

	public void setClima(Clima clima) {
		this.clima = clima;
	}

	public double getPosFinal() {
		return posFinal;
	}

	public void setPosFinal(double posFinal) {
		this.posFinal = posFinal;
	}

	public double getPosInicial() {
		return posInicial;
	}

	public void setPosInicial(double posInicial) {
		this.posInicial = posInicial;
	}

	public Superficie getSuperficie() {
		return superficie;
	}

	public void setSuperficie(Superficie superficie) {
		this.superficie = superficie;
	}
	
	

}
