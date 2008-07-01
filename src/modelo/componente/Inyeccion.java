/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import modelo.*;

/**
 * La inyección de combustible es un sistema de alimentación de motores de combustión 
 * interna, alternativo al carburador, que es el que usan prácticamente todos los 
 * automóviles europeos desde 1993, debido a la obligación de reducir las emisiones 
 * contaminantes y para que sea posible y duradero el uso del catalizador.
 * 
 * @version	1.0
 * @see modelo.Alimentacion Alimentacion
 * @see <a href="http://es.wikipedia.org/wiki/Inyecci%C3%B3n_de_combustible">Inyeccion - Wikipedia</a>
 */
public class Inyeccion extends Alimentacion implements AfectablePorClima{
	/* implementado con muchas multiplicaciones */
	
	private double EfectoClimatico;
	
	/* no tienen setter pues son constantes */
	private static double CTE_HUMEDAD_OPTIMA = 40; // %
	private static double CTE_RELACION_POTENCIA = 92; // %
	
	/**
	 * Constructor de Inyeccion por defecto.
	 */
	public Inyeccion(){
		cargarDatos();
	}
		
	/**
	 * Constructor de Inyeccion con parametros.
	 */
	public Inyeccion(double cte_humedad_optima, 
						double cte_relacion_potencia){
		setPeso(30);
		CTE_HUMEDAD_OPTIMA = cte_humedad_optima;
		CTE_RELACION_POTENCIA = cte_relacion_potencia;
		cargarDatos();
	}
	
	public Element toXml(Document doc) {
		Element xmlElement = doc.createElement("alimentacion");
		xmlElement.setAttribute("tipo", "inyeccion");
		xmlElement.setAttribute("estado", String.valueOf(this.getEstado()));
		return xmlElement;
	}
	
	private void cargarDatos(){
		setEstado(100);
		setNombre("Inyeccion");
		setPrecio(new AlgoPesos(600,00)); //algo$
		setPeso(3); // Kg
		EfectoClimatico = 1;
	}
	
	/**
	 * Nos dice el combustible que necesita la alimentacion segun la Cilindrada 
	 * del Motor y las RPM.
	 * 
	 * La Inyeccion depende del clima, pues mezcla combustible con aire, entonces
	 * tenemos en cuenta el Efecto Climatico en las cuentas.
	 * 
	 * La Inyeccion consume menos combustible que el Carburador pero provee menos potencia
	 * 
	 * @see modelo.Carburador#CombustibleAConsumir() Carburador.CombustibleAConsumir
	 */
	public double CombustibleAConsumir(){
		/* se consume combustible segun la Cilindrada, el tipo de combustible
		 * y se afecta segun efectoclimatico y el Estado */         
		double valor = auto.getMotor().getCilindrada() * auto.getMotor().getRPM();
		return (valor * (1/(getEstado()+0.1)) * (EfectoClimatico/10)  );
	}
	
	/** 
	 * El clima afecta a la Inyeccion, pues mezcla combustible con aire. 
	 */
	public void afectar(Clima clima){
		/* la alimentacion se ve afectada por el clima
		 * supongamos que la humedad optima para la
		 * alimentacion es 30% 
		 */
		double EfectoClimaticoAUX = (clima.getHumedad()/ CTE_HUMEDAD_OPTIMA);
		// entonces el efecto climatico queda en 1 si es optimo
		// si es mas de eso el efecto es maypr a 1
		
		/* como no puede superar 10 */
		if (EfectoClimaticoAUX > 10){ EfectoClimaticoAUX =10 ;}
		
		/* lo asigno */
		EfectoClimatico = EfectoClimaticoAUX;
	}
	
	/**
	 * El clima afecta a la Inyeccion, pues mezcla combustible con aire. 
	 * pero lo afecta en menor medida que al Carburador
	 */
	public void desgastar(){
		
		setEstado(getEstado() - Constantes.tiempoPorCiclo * EfectoClimatico/2);
	}
	
	/**
	 * La potencia de la inyeccion es el CTE_RELACION_POTENCIA por ciento de 
	 * la potencia del combustible y ademas esta afectado por el
	 * efecto Climatico.
	 *
	 * Depende del tipo de combustible.
	 * 
	 * @return Potencia
	 */
	public double obtenerPotencia(){
		/* es una relacion matematica */
		double AUX = (((auto.getCombustible().obtenerPotencia() *
				 CTE_RELACION_POTENCIA) /100) * 
				  EfectoClimatico * getEstado())/100;
		
		if (AUX > 50){AUX=50;}
		
		return AUX;
	}
	
	/* toString */
	
	public String toString() {
		
		String cadena = super.toString();
		cadena += "  INYECCION: " +'\n' 
		            + "      Humedad optima: " + CTE_HUMEDAD_OPTIMA +"%";

		return(cadena);
	}
	
}