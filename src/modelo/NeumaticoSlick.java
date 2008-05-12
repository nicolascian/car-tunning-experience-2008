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

	private double humedadEnPista;
	
	private double viscosidadSuperficie;
	/**
	 * El relieve en la superficie esta dado por la rugosidad y la
	 * cantidad de particulas sueltas en la misma.
	 */
	private double relieveSuperficie;
	
	public double calcularAdherencia(){
		
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
