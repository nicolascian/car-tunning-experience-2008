package test;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.Automatica;
import modelo.Carburador;
import modelo.Carroceria;
import modelo.Combustible;
import modelo.Auto;
import modelo.Escape;
import modelo.Motor;
import modelo.Suspension;
import modelo.Turbo;
import modelo.*;

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
