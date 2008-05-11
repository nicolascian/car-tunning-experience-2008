package modelo;

public abstract class Eje extends Componente
implements AfectablePorSuperficie{ 
	
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
		return (5*this.getEstado()/100);
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

	public Llanta getLDerecha() {
		return LlantaDerecha;
	}

	public void setLDerecha(Llanta derecha) {
		LlantaDerecha = derecha;
	}

	public Llanta getLIzquierda() {
		return LlantaIzquierda;
	}

	public void setLIzquierda(Llanta izquierda) {
		LlantaIzquierda = izquierda;
	}

	public Neumatico getNDerecho() {
		return NeumaticoDerecho;
	}

	public void setNDerecho(Neumatico derecho) {
		NeumaticoDerecho = derecho;
	}

	public Neumatico getNIzquierdo() {
		return NeumaticoIzquierdo;
	}

	public void setNIzquierdo(Neumatico izquierdo) {
		NeumaticoIzquierdo = izquierdo;
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

	
	
}
