package test;

import static org.junit.Assert.*;

import org.junit.Test;
import modelo.Combustible;
import modelo.Auto;


public class TestCombustible {

	@Test
	/*public void testDesgastar() {
		Combustible combustible = new Combustible(10, 0.1);
		Auto auto= new Auto();
		combustible.setEstado(100);
		combustible.desgastar();
		
		assertEquals(100.0, combustible.getEstado());
	}
*/
	
	public void testObtenerPotencia() {
		Combustible combustible = new Combustible(10, 0.1);
		assertEquals(1.0, combustible.obtenerPotencia());
	}
}
