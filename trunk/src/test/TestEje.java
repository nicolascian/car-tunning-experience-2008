package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import modelo.Eje;
import modelo.Eje;
import modelo.Llanta;
import modelo.Superficie;
public class TestEje {

	

	@Test
	public void testDesgastar() {
		Eje eje= new Eje();
		eje.setEstado(100);
		eje.desgastar();
	
		assertEquals(100, eje.getEstado());
	}

	
	public void testObtenerPotencia() {
		Eje eje= new Eje();
		eje.setEstado(100);
		Llanta llanta= new Llanta();
		
		assertEquals(65.0, llanta.obtenerPotencia()*4);
	
	}
		
		
		
}

	



