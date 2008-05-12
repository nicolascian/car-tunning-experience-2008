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
	
	private double relieveSuperficie;
	
}
