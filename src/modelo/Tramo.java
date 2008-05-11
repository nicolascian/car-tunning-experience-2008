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
 * @version 1.0
 */
public class Tramo {
	
	private Superficie superficie;
	private Clima clima;
	private double posInicial;
	private double posFinal;
	
	public Tramo(){
		posInicial = 0;
		posFinal= 100;
		
		clima = new Clima();
		superficie = new Superficie();
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
