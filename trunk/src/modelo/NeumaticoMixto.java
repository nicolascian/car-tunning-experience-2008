package modelo;

/**
 * El neumatico mixto sirve tanto para superficies planas como con relieve,
 * y para secas asi como para humedas, aunque su rendimiento no es el mismo
 * que el que se obtendria con un neumatico especifico para cada situacion,
 */
public class NeumaticoMixto extends Neumatico
	implements AfectablePorClima, AfectablePorSuperficie{

	/*----------Atributos---------*/
	/**
	 * Los surcos en un neumatico sirven para mejorar la adherencia y
	 * el drenaje del agua.
	 * El atributo toma valores entre 0 y 100
	 */
	private double cantidadSurcos;
	
	/**
	 * La humedad en pista representa la cantidad de agua en la misma.
	 * Un valor igual a 0 corresponde a la pista seca.
	 * Un valor igual a 1 corresponde a la pista totalmente mojada.
	 */
	private double humedadEnPista;
	
	/**
	 * El relieve en la superficie esta dado por la rugosidad y la
	 * cantidad de particulas sueltas en la misma.
	 * Puede tener un valor entre 0 y 2.
	 */
	private double relieveSuperficie;
	
	/**
	 * La viscosidad de la superficie toma un valor entre 0 y 1 e indica
	 * la oposicion del terreno a resbalar sobre el mismo.
	 */
	private double viscosidadSuperficie;

	/*-----------Metodos----------*/
	/**
	 * Constructor con parametros.
	 * pre: la cantidad de surcos debe estar entre 0 y 100
	 * post: Queda creada una instancia de NeumaticoMixto 
	 * segun los parametros.
	 */
	public NeumaticoMixto(Auto auto, double cantSurcos){
		this.setNombre("Neumatico Mixto");
		this.setCantidadSurcos(cantSurcos);
		this.setPotenciaMax(1);
		this.setEstado(100);
		this.setHumedadEnPista(0);
		this.setPeso(9);//en kilos
		this.setViscosidadSuperficie(0);
		this.setRelieveSuperficie(0);
		this.setAuto(auto);
	}
	
	/**
	 * Constructor sin parametros
	 * pre:-
	 * post: Queda crado una instancia de NeumaticoMixto estandar
	 */
	public NeumaticoMixto(){
		this.setNombre("Neumatico Estandar");
		this.setCantidadSurcos(50);
		this.setPotenciaMax(1);
		this.setEstado(100);
		this.setHumedadEnPista(0);
		this.setPeso(9);//en kilos
		this.setViscosidadSuperficie(0);
		this.setRelieveSuperficie(0);
	}

	/**
	 * La adherencia se ve afectada en un 30%por la viscocidad de la superficie,
	 * en un 30% por la cantidad de agua en la misma, y en hasta un 30% por el 
	 * relieve.
	 * La adherencia aumenta con la cantidad de surcos
	 */
	public double calcularAdherencia(){
		return ( 1- 0.3*(1-(this.getCantidadSurcos()/150))*this.getHumedadEnPista() 
				- 0.3 * (1-this.getViscosidadSuperficie())
				- .15*(1-(this.getCantidadSurcos()/200))*this.getRelieveSuperficie());
	}
	
	public double obtenerPotencia(){
		return ((this.getEstado()/100)*(this.getPotenciaMax()- (this.getRelieveSuperficie()/2)));
	}
	
	/**
	 * El desgaste esta dado basicamente por el relieve de la superficie y por
	 * el simple uso.
	 */
	public void desgastar(){
		double desgaste;
		desgaste= (0.01 + this.getRelieveSuperficie())* Constantes.tiempoPorCiclo;
		this.setEstado(this.getEstado()- desgaste);
	}
	
	/**
	 * Este tipo de neumaticos se ve afectado solo por la humedad.
	 * post: Queda modificado el atributo "humedadEnPista".
	 */
	public void afectar(Clima clima){
		this.setHumedadEnPista(clima.getHumedad()/100);
	}
	
	/**
	 * Este tipo de neumatico se ve afectado por todos los atributos
	 * de la superficie.
	 * post: los atributos relieveSuperficie y viscosidadSuperficie
	 * quedan modificados.
	 */
	public void afectar(Superficie superficie){
		this.setViscosidadSuperficie(superficie.getViscosidad()/100);
		this.setRelieveSuperficie((superficie.getRugosidad()+superficie.getParticulasSueltas())/100);
	}
	
	public double getCantidadSurcos() {
		return cantidadSurcos;
	}

	public void setCantidadSurcos(double cantidadSurcos) {
		this.cantidadSurcos = cantidadSurcos;
	}

	public double getHumedadEnPista() {
		return humedadEnPista;
	}

	public void setHumedadEnPista(double humedadEnPista) {
		this.humedadEnPista = humedadEnPista;
	}

	public double getRelieveSuperficie() {
		return relieveSuperficie;
	}

	public void setRelieveSuperficie(double relieveSuperficie) {
		this.relieveSuperficie = relieveSuperficie;
	}

	public double getViscosidadSuperficie() {
		return viscosidadSuperficie;
	}

	public void setViscosidadSuperficie(double viscosidadSuperficie) {
		this.viscosidadSuperficie = viscosidadSuperficie;
	}
	
}
