/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo.componente;
import modelo.*;

/**
 * El carburador es el dispositivo que hace la mezcla de aire-combustible en 
 * los motores de gasolina. A fin de que el motor funcione más económicamente y 
 * obtenga la mayor potencia de salida, es importante que la gasolina esté en las 
 * mejores condiciones. A fin de hacer una mezcla óptima de aire-combustible, el 
 * carburador usará varias técnicas.
 * 
 * @version	1.0
 * @see modelo.Alimentacion Alimentacion
 * @see <a href="http://es.wikipedia.org/wiki/Carburador">Carburador - Wikipedia</a>
 */
public class Carburador extends Alimentacion implements AfectablePorClima{
	/* implementado con muchas multiplicaciones */
	
	private double EfectoClimatico;
	
	/* no tienen setter pues son constantes */
	private static double CTE_HUMEDAD_OPTIMA = 30; // %
	private static double CTE_RELACION_POTENCIA = 98; // %
	
	/**
	 * Constructor de Carburador por defecto.
	 */
	public Carburador(){
		cargarDatos();
		setPeso(10);
	}
	
	/**
	 * Constructor de Carburador con parametros.
	 */
	public Carburador(double cte_humedad_optima, 
						double cte_relacion_potencia){
		
		CTE_HUMEDAD_OPTIMA = cte_humedad_optima;
		CTE_RELACION_POTENCIA = cte_relacion_potencia;
		cargarDatos();
		setPeso(10);
	}
	
	private void cargarDatos(){
		setEstado(100);
		setNombre("Carburador");
		setPrecio(new AlgoPesos(400,00)); //algo$
		setPeso(4); // Kg
		EfectoClimatico = 1;
	}
	
	/**
	 * Nos dice el combustible que necesita la alimentacion segun la Cilindrada 
	 * del Motor y las RPM.
	 * 
	 * El Carburador depende del clima, pues mezcla combustible con aire, entonces
	 * tenemos en cuenta el Efecto Climatico en las cuentas.
	 * 
	 * El Carburador es un sistema de alimentacion que consume ineficientemente 
	 * el combustible en mayor cantidad, por ciclo, pero provee mayor potencia.
	 * 
	 * @see modelo.Inyeccion#CombustibleAConsumir() Inyeccion.CombustibleAConsumir
	 */
	public double CombustibleAConsumir(){
		/* se consume combustible segun la Cilindrada, el tipo de combustible
		 * y se afecta segun efectoclimatico y el Estado */         
		double valor = auto.getMotor().getCilindrada() * auto.getMotor().getRPM();
		return (valor * EfectoClimatico * (1/(getEstado()+0.1)) );
	}
	
	/** 
	 * El clima afecta al Carburador, pues mezcla combustible con aire. 
	 */
	public void afectar(Clima clima){
		/* la alimentacion se ve afectada por el clima
		 * supongamos que la humedad optima para la
		 * alimentacion es CTE_HUMEDAD_OPTIMA 
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
	 * El efecto climatico afecta al carburador y hace que se desgaste.
	 */
	public void desgastar(){
		
		setEstado(getEstado() - Constantes.tiempoPorCiclo * EfectoClimatico);
	}
	
	/**
	 * La potencia del carburador es el CTE_RELACION_POTENCIA por ciento de 
	 * la potencia del combustible y ademas es afectado por el 
	 * Efecto Climatico.
	 * 
	 * Depende del tipo de combustible.
	 * 
	 * @return Potencia
	 */
	public double obtenerPotencia(){
	    /* es una operacion matematica */
		double AUX = (((auto.getCombustible().obtenerPotencia() *
				 CTE_RELACION_POTENCIA) /100) * 
				  EfectoClimatico * getEstado())/100;
		
		if (AUX > 50){AUX=50;}
		
		return AUX;
	}
	
	/* toString */
	
	public String toString() {
		
		String cadena = super.toString();
		cadena += "  CARBURADOR: " +'\n' 
		            + "      Humedad optima: " + CTE_HUMEDAD_OPTIMA +"%";

		return(cadena);
	}
}