package modelo;
	/**
	 * El neumatico Slick es un neumatico liso (carece de dibujo en la
	 * banda de rodadura). Tiene la mayor superficie posible en contacto
	 * con la superficie, y maximiza el agarre en asfalto seco.
	 * Su utilizacion es recomendable en pistas secas, ya que tienen poca
	 * capacidad para evacuar agua, y planas, ya que el relieve y las 
	 * particulas sueltas lo desgastan rapidamente.
	 */
public class NeumaticoSlick extends Neumatico
	implements AfectablePorSuperficie, AfectablePorClima{
	
	/**
	 * La humedad en pista representa la cantidad de agua en la misma.
	 * Un valor igual a 0 corresponde a la pista seca.
	 * Un valor igual a 1 corresponde a la pista totalmente mojada.
	 */
	private double humedadEnPista;
	
	/**
	 * La viscosidad de la superficie toma un valor entre 0 y 1 e indica
	 * la oposicion del terreno a resbalar sobre el mismo.
	 */
	private double viscosidadSuperficie;
	
	/**
	 * El relieve en la superficie esta dado por la rugosidad y la
	 * cantidad de particulas sueltas en la misma.
	 * Puede tener un valor entre 0 y 2.
	 */
	private double relieveSuperficie;
	
	/**
	 * La adherencia se ve comprometida en un 44% por la cantidad de agua en la
	 * pista, en otro 44% por la visicosidad de la misma, y en maximo del 2%
	 * por el relieve.
	 */
	public double calcularAdherencia(){
		double adherencia;
		adherencia = 1- (0.44 * this.getHumedadEnPista()) - (0.44* this.getViscosidadSuperficie())
					- (.01* this.getRelieveSuperficie());
		return adherencia;
	}
	
	/**
	 * El desgaste esta dado basicamente por el relieve de la superficie, y un 
	 * minimo desgaste por el tiempo de uso.
	 */
	public void desgastar(){
		double desgaste;
		desgaste = (0.001 + 5 * this.getRelieveSuperficie()) * Constantes.tiempoPorCiclo;
		this.setEstado(this.getEstado()- desgaste);
	}
	
	/**
	 * Todos los parametros de la superficie afectan al neumatico.
	 * La viscosidad afectara la adherencia, mientras que la rugosisidad y las 
	 * particulas sueltas afectaran el desgaste y la potencia entregada.
	 */
	public void afectar(Superficie superficie){
		this.setViscosidadSuperficie(superficie.getViscosidad()/100);
		this.setRelieveSuperficie((superficie.getRugosidad()+superficie.getParticulasSueltas())/100);
	}

	/**
	 * El unico efecto climatico considerado sobre este tipo de neumatico es la
	 * cantidad de agua presente.
	 */
	public void afectar(Clima clima){
		this.setHumedadEnPista(clima.getHumedad()/100);
	}
	
	/**
	 * La potencia entregada se ve afectada por el estado del neumatico y por
	 * el relieve en la superficie.
	 * En condiciones ideales, entrega una potencia igual a 5.
	 */
	public double obtenerPotencia(){
		return ((this.getEstado()/100) * (5-this.getRelieveSuperficie()));
	}
	
	public double getHumedadEnPista() {
		return humedadEnPista;
	}
	public void setHumedadEnPista(double humedadEnPista) {
		this.humedadEnPista = humedadEnPista;
	}
	public double getViscosidadSuperficie() {
		return viscosidadSuperficie;
	}
	public void setViscosidadSuperficie(double viscosidadSuperficie) {
		this.viscosidadSuperficie = viscosidadSuperficie;
	}
	public double getRelieveSuperficie() {
		return relieveSuperficie;
	}
	public void setRelieveSuperficie(double relieveSuperficie) {
		this.relieveSuperficie = relieveSuperficie;
	}
	
}
