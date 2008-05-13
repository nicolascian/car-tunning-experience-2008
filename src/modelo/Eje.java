package modelo;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * @Documentacion: Los eje del auto contiene las ruedas las cual estan
 * compuestas por llantas y neumaticos.
 * Son afectados por y desgastados por la superficie.
 * @version 1.0
 */
public class Eje extends Componente
implements AfectablePorSuperficie{ 
	
	/* Atributos de la clase */
	private Llanta LlantaDerecha;
	private Llanta LlantaIzquierda;
	private Neumatico NeumaticoDerecho;
	private Neumatico NeumaticoIzquierdo;
	private double DesgastePorRugosidad;
	private double DesgastePorParticulas;
	
	
	/*Constructor,inicia estado de eje en 100*/
	public Eje(){
		this.setEstado(100);
	}
	/**
	 * @Pre: Potencias de las ruedas y neumaticos
	 * @Post: Retorna el valor de potencia generada por
	 * el eje.
	 * @Documentacion: El valor es la suma de la potencia generada por los ejes
	 * las dos llantas y los dos neumaticos que componene cada eje.
	 * El mejor valor de potencia que puede devolver un eje es de 5 watts.
	 */
	
	public double obtenerPotencia() {
		return ((5*this.getEstado()/100)+ LlantaDerecha.obtenerPotencia()+ LlantaIzquierda.obtenerPotencia() + NeumaticoDerecho.obtenerPotencia()+ NeumaticoIzquierdo.obtenerPotencia());
	}
	/**
	 * @Post: Eje desgastado, modificacion del estado del eje.
	 * @Documentacion: Metodo que se encarga de modificar el estado de el eje
	 * teniendo en cuenta que este se ira arruinando por la
	 * rugosidad que presenta la superficie y las particulas 
	 * sueltas que presenta dicha superficie que dañan al eje.
	 */
	public void desgastar(){
		double desgaste=0;
		desgaste = ((this.getDesgastePorRugosidad()/100) + (this.getDesgastePorParticulas())/10000)*Constantes.tiempoPorCiclo;
		this.setEstado(this.getEstado()- desgaste);
	}
	/**
	 * @Post: Atributos modificados por la dependencia del tipo de pista.
	 * @Documentacion: Metodo que modifica el valor producido por cada desgaste
	 * que provoca la superficie al eje.
	 */
	public void afectar(Superficie superficie){
		this.setDesgastePorParticulas(superficie.getParticulasSueltas());
		this.setDesgastePorRugosidad(superficie.getRugosidad());
	}

	/* getters y setters*/

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
