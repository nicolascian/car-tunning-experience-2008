/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente;
import modelo.*;

import org.w3c.dom.Element;
import org.w3c.dom.Document;

/**
 * El ABS (Antilock Brake System). 
 * Consiste en una bomba que se incorpora a los circuitos del líquido de freno 
 * y en unos detectores que controlan las revoluciones de las ruedas. Si en una 
 * frenada brusca una o varias ruedas reducen repentinamente sus revoluciones, 
 * el ABS lo detecta e interpreta que las ruedas están a punto de quedar bloqueadas 
 * sin que el vehículo se haya detenido. Esto quiere decir que el vehículo comenzará 
 * a patinar, y por lo tanto, a deslizarse sobre el suelo sin control. Para que esto 
 * no ocurra, los sensores envían una señal a la Central del sistema ABS, que reduce 
 * la presión realizada sobre los frenos, sin que intervenga en ello el conductor. 
 * Cuando la situación se ha normalizado y las ruedas giran de nuevo correctamente, 
 * el sistema permite que la presión sobre los frenos vuelva a actuar con toda la 
 * intensidad. El ABS controla nuevamente el giro de las ruedas y actúa otra vez si 
 * éstas están a punto de bloquearse por la fuerza del freno. En el caso de que este 
 * sistema intervenga, el procedimiento se repite de forma muy rápida, unas 50 a 100 
 * veces por minuto, lo que se traduce en que el conductor percibe una vibración en el 
 * pedal del freno.
 * 
 * @see <a href="http://es.wikipedia.org/wiki/Antilock_Brake_System">Freno ABS - Wikipedia</a>
 */
public class FrenoABS extends Freno{

	/**
	 * Constructor de Freno ABS sin parametros
	 * coloca el estado de la instancia en 100%
	 */
	public FrenoABS(){
		super();
		setNombre("Freno ABS (Antilock Brake System)");
		setPrecio(new AlgoPesos(1200,00)); //algo$
		setPeso(8); // Kg
	}
	
	public Element toXml(Document doc) {
		Element xmlElement = doc.createElement("frenoabs");
		xmlElement.setAttribute("estado", String.valueOf(this.getEstado()));
		return xmlElement;
	}
	
	@Override
	/**
	 * El clima no afecta a los frenos de tipo ABS
	 */
	public void afectar(Clima clima) {
		EfectoClimatico = 1;
		
	}

	@Override
	public void desgastar() {
		setEstado(getEstado() - EfectoClimatico * 0.00013);
		
	}
	
	/* toString */
	
	public String toString(){
		return getNombre();
	}

}
