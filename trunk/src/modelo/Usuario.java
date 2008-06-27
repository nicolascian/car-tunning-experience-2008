/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

/**
 * este es el usuario del modelo, no del control
 *
 */
public class Usuario {

	private String nombre;
	
	private AlgoPesos capital;

	private Auto auto;

	
	
	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

	public AlgoPesos getCapital() {
		return capital;
	}

	public void setCapital(AlgoPesos capital) {
		this.capital = capital;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
