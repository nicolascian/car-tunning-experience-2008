/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

/**
 * Frenos de cinta o de banda.
 * Utilizan una banda flexible, las mordazas o zapatas (suelen ser de amianto) 
 * se aplican para ejercer tensión sobre un cilindro o tambor giratorio que se 
 * encuentra solidario al eje que se pretenda controlar. La banda al ejercer presión, 
 * ejerce la fricción con la cual se disipa en calor la energía cinética del cuerpo 
 * a regular.
 * 
 * @see <a href="http://es.wikipedia.org/wiki/Freno#Frenos_de_fricci.C3.B3n">Freno de Cinta - Wikipedia</a>
 */
public class FrenoCinta extends Freno {

	//temperatura optima de un freno de Cinta
	private static double TEMP_OPTIMA = 24; // °C
	// humedad optima de un freno de Cinta
	private static double HUM_OPTIMA = 10; // %

	
	public FrenoCinta(){
		super();
		setNombre("Freno de Cintao de Banda");
		setPrecio(new AlgoPesos(370,00)); //algo$
		setPeso(11); // Kg
	}
	
	@Override
	public void afectar(Clima clima) {
		EfectoClimatico = clima.getTemperatura()/TEMP_OPTIMA * clima.getHumedad()/HUM_OPTIMA;
		
	}

	@Override
	public void desgastar() {
		setEstado(getEstado() - EfectoClimatico * 0.0015);
		
	}

}
