package test;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.Auto;
import modelo.Combustible;
import modelo.Escape;
import modelo.Clima;

public class TestEscape {

	@Test
	public void testDesgastar() {
		Escape escape= new Escape();
		escape.setEstado(100);
		escape.desgastar();
		
		assertEquals(100.0, escape.getEstado());
		
	}

	
	
	@Test
	public void testObtenerPotencia() {
		Escape escape= new Escape();
		escape.setEstado(100);
		
		assertEquals(4.1, escape.obtenerPotencia());
	}

	
}
