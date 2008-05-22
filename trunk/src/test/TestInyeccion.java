package test;

import junit.framework.*;
import modelo.*;
import modelo.componente.*;

public class TestInyeccion extends TestCase {

	Auto auto = new Auto();
	Constantes cte;
	
	public void testDesgastar() {
		assertEquals(0.000000000006,cte.tiempoPorCiclo);
		
		Inyeccion inyeccion = new Inyeccion();		
		inyeccion.desgastar();
		assertEquals(99.999999999997,inyeccion.getEstado());
				
	}

	public void testObtenerPotencia() {
		
		Alimentacion inyeccion1 = new Inyeccion();
		auto.setAlimentacion(inyeccion1);
		assertEquals(36.8,inyeccion1.obtenerPotencia());
		
		Alimentacion inyeccion2 = new Inyeccion(50,100);
		auto.setAlimentacion(inyeccion2);
		assertEquals(40.0,inyeccion2.obtenerPotencia());
	}

	public void testCombustibleAConsumir() {
		
		Inyeccion inyeccion1 = new Inyeccion();
		auto.setAlimentacion((Alimentacion)inyeccion1);
		assertEquals(0.0, inyeccion1.CombustibleAConsumir());
		
		Inyeccion inyeccion2 = new Inyeccion();
		auto.setAlimentacion((Alimentacion)inyeccion2);
		auto.setEncendido(true);
		assertEquals(true, auto.isEncendido());
		//cilindrada 1600
		//revolucionesMaximas = 8000
		//revolucionesMinimas = 8000 *0.08 = 640
		//combustibleAConsumir = cilindrada* RPM * ( 1/ (Estado-0.1))  * (EfectClima /10)
		assertEquals(1022.977023, inyeccion2.CombustibleAConsumir());
	}

	public void testAfectar() {
		
		AfectablePorClima inyeccion1 = new Inyeccion();
		auto.setAlimentacion((Alimentacion)inyeccion1);
		inyeccion1.afectar(new Clima());
		//efectoClimatico = 1.25
		assertEquals(40.0, ((Alimentacion)inyeccion1).obtenerPotencia());
		
		AfectablePorClima inyeccion2 = new Inyeccion();
		auto.setAlimentacion((Alimentacion)inyeccion2);
		inyeccion2.afectar(new Clima(100, 90, 80));
		//efectoClimatico = 2.25
		assertEquals(50.0, ((Alimentacion)inyeccion2).obtenerPotencia());
		
		AfectablePorClima inyeccion3 = new Inyeccion(80, 100);
		auto.setAlimentacion((Alimentacion)inyeccion3);
		inyeccion3.afectar(new Clima());
		//efectoClimatico = 0.625
		assertEquals(25.0, ((Alimentacion)inyeccion3).obtenerPotencia());
		
		AfectablePorClima inyeccion4 = new Inyeccion(80, 100);
		auto.setAlimentacion((Alimentacion)inyeccion4);
		inyeccion4.afectar(new Clima(100, 90, 80));
		//efectoClimatico = 1.125
		assertEquals(45.0, ((Alimentacion)inyeccion4).obtenerPotencia());
	}

}
