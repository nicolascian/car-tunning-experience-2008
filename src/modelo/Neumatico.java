package modelo;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 * 
 * se ve afectado por la temperatura del clima y superficie
 * 
 * @version	1.0
 */
public abstract class Neumatico extends Componente
implements AfectablePorClima, AfectablePorSuperficie{
	
		
	private double DeterioroPorRozamiento;
	private double CoeficienteDeDesgastePorTemperatura;
	
	public Neumatico(){		
		this.setEstado(100);
	}
	
	public void desgastar(){
		setEstado(getEstado()-(getCoeficienteDeDesgastePorTemperatura()+ getDeterioroPorRozamiento()));
	}
	
	public double obtenerPotencia(){
		//tener en cuenta la temperatura del clima y superficie
		return (DeterioroPorRozamiento*CoeficienteDeDesgastePorTemperatura) * getEstado();
	}
	
	/** el clima afecta a los neumaticos */
	public void afectar(Clima clima){
		setTemperatura(clima.getTemperatura());
		setCoeficienteDeDesgastePorTemperatura(clima.getTemperatura()/8000);
	}
	
	/** la superficie afecta a los neumaticos */
	public void afectar(Superficie superficie){
		setDeterioroPorRozamiento(superficie.getRugosidad() + superficie.getParticulasSueltas());
	}
	
	
	public void setDeterioroPorRozamiento(double DeterioroPorRozamiento){
		this.DeterioroPorRozamiento= DeterioroPorRozamiento;
	}
	
	
	
	public void setCoeficienteDeDesgastePorTemperatura (double CoeficienteDeDesgastePorTemperatura){
		this.CoeficienteDeDesgastePorTemperatura = CoeficienteDeDesgastePorTemperatura;
	}
	
	
	public double getDeterioroPorRozamiento(){
		return DeterioroPorRozamiento;
	}
	
	
	
	public double getCoeficienteDeDesgastePorTemperatura(){
		return CoeficienteDeDesgastePorTemperatura;
	}

}