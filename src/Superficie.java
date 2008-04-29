/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 * 
 * cada vez que se crea una superficie nueva, esta se encarga de afectar 
 * al auto, en su constructor llama a auto.afectar(this);
 * 
 * @version	1.0
 */
public class Superficie{
	/* comentario acerca de la implementacion de la clase */
	
	private String nombre;
	
	private double coeficiente;
	
	Superficie(){

	}

	public double getCoeficiente() {
		return coeficiente;
	}

	public void setCoeficiente(double coeficiente) {
		this.coeficiente = coeficiente;
	}
	
}