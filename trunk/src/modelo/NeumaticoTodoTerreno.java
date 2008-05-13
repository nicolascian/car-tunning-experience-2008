package modelo;

	/**
	 * Los neumaticos todo terreno son utilizados en las superficies mas
	 * exigentes, proporcionando una mayor adherencia y traccion en terrenos
	 * con mucho relieve, pero el desgaste es mayor que en un neumatico comun
	 * Al tener mayor dibujo y grandes tacos drenan mejor el agua.
	 */
public class NeumaticoTodoTerreno extends Neumatico
	implements AfectablePorClima, AfectablePorSuperficie{
	
	/*----------Atributos---------*/
	
	/*
	 * La humedad en pista representa la cantidad de agua en la misma.
	 * Un valor igual a 0 corresponde a la pista seca.
	 * Un valor igual a 1 corresponde a la pista totalmente mojada.
	 */
	private double humedadEnPista;
	
	/*
	 * La rugosidad de la superficie no disminuira la adherencia pero si
	 * aumentara considerablemente el desgaste del neumatico.
	 * Toma valores entre 0 y 1.
	 */
	private double rugosidadSuperficie;
	
	/*
	 * Las particulas sueltas en la superficie son aquellos elementos de 
	 * volumen sueltos en la pista q aumentaran el daño producido sobre 
	 * los neumaticos y disminuiran la adherencia aunque no de forma
	 * significativa.
	 * Toma valores entre 0 y 1.
	 */
	private double particulasEnSuperficie;
	
	/*
	 * La viscosidad de la superficie toma un valor entre 0 y 1 e indica
	 * la oposicion del terreno a resbalar sobre el mismo.
	 */
	private double viscosidadSuperficie;

	/*-----------Metodos----------*/
	/**
	 * El desgaste de este neumatico esta dado por la rugosidad de la superficie,
	 * por las particulas en la misma y por el simple uso.
	 */
	public void desgastar(){
		double desgaste = (1 + this.getParticulasEnSuperficie() + this.getRugosidadSuperficie())
							* Constantes.tiempoPorCiclo;
		this.setEstado(this.getEstado()- desgaste);
	}
	
	/**
	 * La potencia entregada se ve afectada por el estado del neumatico, por
	 * la cantidad de particulas en la superficie y por la rugosidad de la misma.
	 * En condiciones ideales, entrega una potencia igual a 3.
	 */
	public double obtenerPotencia(){
		return ((this.getEstado()/100)* (this.getPotenciaMax()-this.getParticulasEnSuperficie())
					- this.getRugosidadSuperficie());
	}
	
	/**
	 * La adherencia se ve afectada por las particulas sueltas en la
	 * superficie en un 5%, por la cantidad de agua presente en un 20%
	 * , y en un 40% por el estado del neumatico.
	 */
	public double calcularAdherencia(){
		double adherencia;
		adherencia = 1 - (0.05 * this.getParticulasEnSuperficie()) 
					   - (0.2 * this.getHumedadEnPista())
					   - (0.4 * (this.getEstado()/100));
		return adherencia;
	}
	
	/**
	 * Todos los parametros de la superficie afectan al neumatico.
	 * La viscosidad y las particulas sueltas afectan la adherencia,
	 * mientras que el desgaste se ve afectado por las particulas
	 * sueltas y por la rugosisdad del terreno.
	 */
	public void afectar(Superficie superficie){
		this.setRugosidadSuperficie(superficie.getRugosidad());
		this.setParticulasEnSuperficie(superficie.getParticulasSueltas());
		this.setViscosidadSuperficie(superficie.getViscosidad());
	}
	
	/**
	 * El unico efecto climatico sobre este tipo de neumatico es la cantidad
	 * de agua presente en la pista.
	 */
	public void afectar(Clima clima){
		this.setHumedadEnPista(clima.getHumedad()/100);
	}
	
	public double getHumedadEnPista() {
		return humedadEnPista;
	}

	public void setHumedadEnPista(double humedadEnPista) {
		this.humedadEnPista = humedadEnPista;
	}

	public double getRugosidadSuperficie() {
		return rugosidadSuperficie;
	}

	public void setRugosidadSuperficie(double rugosidadSuperficie) {
		this.rugosidadSuperficie = rugosidadSuperficie;
	}

	public double getParticulasEnSuperficie() {
		return particulasEnSuperficie;
	}

	public void setParticulasEnSuperficie(double particulasEnSuperficie) {
		this.particulasEnSuperficie = particulasEnSuperficie;
	}

	public double getViscosidadSuperficie() {
		return viscosidadSuperficie;
	}

	public void setViscosidadSuperficie(double viscosidadSuperficie) {
		this.viscosidadSuperficie = viscosidadSuperficie;
	}
}
