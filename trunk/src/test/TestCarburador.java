package test;

import modelo.componente.*;
import modelo.*;
import junit.framework.TestCase;

public class TestCarburador extends TestCase {
	
	Auto auto = new Auto();
	Constantes cte;
	
	public void testDesgastar() {
		assertEquals(0.000000000006,cte.tiempoPorCiclo);
		
		Carburador carburador = new Carburador();		
		carburador.desgastar();
		assertEquals(99.999999999994,carburador.getEstado());
				
	}

	public void testObtenerPotencia() {
		
		Alimentacion carburador1 = new Carburador();
		auto.setAlimentacion(carburador1);
		assertEquals(39.2,carburador1.obtenerPotencia());
		
		Alimentacion carburador2 = new Carburador(50,100);
		auto.setAlimentacion(carburador2);
		assertEquals(40.0,carburador2.obtenerPotencia());
	}

	public void testCombustibleAConsumir() {
		
		Carburador carburador1 = new Carburador();
		auto.setAlimentacion((Alimentacion)carburador1);
		assertEquals(0.0, carburador1.CombustibleAConsumir());
		
		Carburador carburador2 = new Carburador();
		auto.setAlimentacion((Alimentacion)carburador2);
		auto.setEncendido(true);
		assertEquals(true, auto.isEncendido());
		//cilindrada 1600
		//revolucionesMaximas = 8000
		//revolucionesMinimas = 8000 *0.08 = 640
		//combustibleAConsumir = cilindrada* RPM * ( 1/ (Estado-0.1))  * (EfectClima /10)
		assertEquals(1022.977023, carburador2.CombustibleAConsumir());
	}

	public void testAfectar() {
		
		AfectablePorClima carburador1 = new Carburador();
		auto.setAlimentacion((Alimentacion)carburador1);
		carburador1.afectar(new Clima());
		//efectoClimatico = 1.25
		assertEquals(40.0, ((Alimentacion)carburador1).obtenerPotencia());
		
		AfectablePorClima carburador2 = new Carburador();
		auto.setAlimentacion((Alimentacion)carburador2);
		carburador2.afectar(new Clima(100, 90, 80));
		//efectoClimatico = 2.25
		assertEquals(50.0, ((Alimentacion)carburador2).obtenerPotencia());
		
		AfectablePorClima carburador3 = new Carburador(80, 100);
		auto.setAlimentacion((Alimentacion)carburador3);
		carburador3.afectar(new Clima());
		//efectoClimatico = 0.625
		assertEquals(25.0, ((Alimentacion)carburador3).obtenerPotencia());
		
		AfectablePorClima carburador4 = new Carburador(80, 100);
		auto.setAlimentacion((Alimentacion)carburador4);
		carburador4.afectar(new Clima(100, 90, 80));
		//efectoClimatico = 1.125
		assertEquals(45.0, ((Alimentacion)carburador4).obtenerPotencia());
	}

}
