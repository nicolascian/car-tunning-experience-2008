/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente;
import modelo.*;

/**
 * El neumatico para lluvia esta hecho de forma tal que drena el agua y por
 * lo tanto no lo afecta la cantidad de la misma que se encuentre en la pista.
 * Es similar al neumatico mixto, salvo que no se ve afectado por el clima,
 * y el desgaste es mayor.
 * Su desgaste es mayor al normal y no tiene un buen comportamiento en superficies
 * con mucho relieve.
 */
public class NeumaticoLluvia extends Neumatico implements AfectablePorSuperficie{

	/*----------Atributos---------*/
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
	 * pre: -
	 * post: Queda creada una instancia de NeumaticoLluvia 
	 * segun los parametros.
	 */
	public NeumaticoLluvia(Auto auto){
		this.setNombre("Neumatico para lluvia");
		this.setPotenciaMax(2);
		this.setEstado(100);
		this.setPeso(9);//en kilos
		this.setViscosidadSuperficie(0);
		this.setRelieveSuperficie(0);
		this.setAuto(auto);
		this.setPrecio(new AlgoPesos(300,0));
	}
	
	/**
	 * Constructor sin parametros
	 * pre:-
	 * post: Queda crado una instancia de NeumaticoLluvia
	 */
	public NeumaticoLluvia(){
		this.setNombre("Neumatico para lluvia");
		this.setPotenciaMax(2);
		this.setEstado(100);
		this.setPeso(9);//en kilos
		this.setViscosidadSuperficie(0);
		this.setRelieveSuperficie(0);
		this.setPrecio(new AlgoPesos(300,0));
		this.setAuto(null);
	}
	
	/**
	 * La potencia entregada esta dada por el Estado del neumatico y por 
	 * el relieve de la superficie. Cuanto mas plana sea, mayor potencia
	 * entregara.
	 */
	public double obtenerPotencia(){
		return ((this.getEstado()/100))*(this.getPotenciaMax()- this.getRelieveSuperficie());
	}
	
	/**
	 * La adherencia se ve comprometida en un 40% por la viscosidad de la superficie
	 * y en hasta un 40% por el relieve de la misma.
	 */
	public double calcularAdherencia(){
		return (1- 0.4*(1-this.getViscosidadSuperficie()) - 0.2* this.getRelieveSuperficie());
	}
	
	/**
	 * El desgaste esta dado basicamente por el relieve de la superficie y por
	 * el simple uso.
	 */
	public void desgastar(){
		double desgaste;
		desgaste= (0.5 + 4*this.getRelieveSuperficie())* Constantes.tiempoPorCiclo;
		this.setEstado(this.getEstado()- desgaste);
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
