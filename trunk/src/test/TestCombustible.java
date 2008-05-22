package test;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.componente.*;

public class TestCombustible {

	public void testObtenerPotencia() {
		Combustible combustible = new Combustible(10, 0.1);
		assertEquals(1.0, combustible.obtenerPotencia());
	}
	
	
	
	
	public void testGetCapacidad(){
		Combustible combustible= new Combustible(10.1,0.2);
		
		assertEquals(10.1, combustible.getCapacidad());
		
	}
	
	

	
	
}
