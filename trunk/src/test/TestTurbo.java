package test;

import static org.junit.Assert.*;

import org.junit.Test;
import modelo.Turbo;
import modelo.Clima;


public class TestTurbo {

	@Test
	public void testDesgastar() {
		Turbo turbo = new Turbo();
		turbo.setEstado(100);
		turbo.desgastar();
		
		assertEquals(99.999999999982, turbo.getEstado());
		
	}

	
	
	@Test
	public void testObtenerPotencia() {
		Turbo turbo= new Turbo();
		
		assertEquals(5.0, turbo.obtenerPotencia());
	}

	
	
	@Test
	public void testAfectar() {
		Turbo turbo= new Turbo();
		Clima clima= new Clima(25.0, 50.0, 1013.0);
		turbo.afectar(clima);
		
		assertEquals(1.0, turbo.getCoeficienteDeObtencionDePotencia());
		
		
	}

}
