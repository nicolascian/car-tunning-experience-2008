package modelo;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 */
public class Eje extends Componente
implements AfectablePorSuperficie{ 
	/* Notas de la implementacion */
	
	private Llanta LlantaDerecha;
	private Llanta LlantaIzquierda;
	private Neumatico NeumaticoDerecho;
	private Neumatico NeumaticoIzquierdo;
	private double DesgastePorRugosidad;
	private double DesgastePorParticulas;
	
	
	
	public void Eje(){
		this.setEstado(100);
	}
	
	public double obtenerPotencia() {
		return ((5*this.getEstado()/100)+ LlantaDerecha.obtenerPotencia()+ LlantaIzquierda.obtenerPotencia() + NeumaticoDerecho.obtenerPotencia()+ NeumaticoIzquierdo.obtenerPotencia());
	}
	
	public void desgastar(){
		double desgaste=0;
		desgaste = ((this.getDesgastePorRugosidad()/100) + (this.getDesgastePorParticulas())/10000)*Constantes.tiempoPorCiclo;
		this.setEstado(this.getEstado()- desgaste);
	}
	
	public void afectar(Superficie superficie){
		this.setDesgastePorParticulas(superficie.getParticulasSueltas());
		this.setDesgastePorRugosidad(superficie.getRugosidad());
	}

	

	public double getDesgastePorRugosidad() {
		return DesgastePorRugosidad;
	}

	public void setDesgastePorRugosidad(double desgastePorRugosidad) {
		DesgastePorRugosidad = desgastePorRugosidad;
	}

	public double getDesgastePorParticulas() {
		return DesgastePorParticulas;
	}

	public void setDesgastePorParticulas(double desgastePorParticulas) {
		DesgastePorParticulas = desgastePorParticulas;
	}

	public Llanta getLlantaDerecha() {
		return LlantaDerecha;
	}

	public void setLlantaDerecha(Llanta llantaDerecha) {
		llantaDerecha.instalar(this.getAuto());
		LlantaDerecha = llantaDerecha;
	}

	public Llanta getLlantaIzquierda() {
		return LlantaIzquierda;
	}

	public void setLlantaIzquierda(Llanta llantaIzquierda) {
		llantaIzquierda.instalar(this.getAuto());
		LlantaIzquierda = llantaIzquierda;
	}

	public Neumatico getNeumaticoDerecho() {
		return NeumaticoDerecho;
	}

	public void setNeumaticoDerecho(Neumatico neumaticoDerecho) {
		neumaticoDerecho.instalar(this.getAuto());
		NeumaticoDerecho = neumaticoDerecho;
	}

	public Neumatico getNeumaticoIzquierdo() {
		return NeumaticoIzquierdo;
	}

	public void setNeumaticoIzquierdo(Neumatico neumaticoIzquierdo) {
		neumaticoIzquierdo.instalar(this.getAuto());
		NeumaticoIzquierdo = neumaticoIzquierdo;
	}

	
	
}
