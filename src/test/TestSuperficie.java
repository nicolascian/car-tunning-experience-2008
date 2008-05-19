package test;
import junit.framework.*;
import modelo.*;

import junit.framework.TestCase;

public class TestSuperficie extends TestCase {
	Superficie supPorDefecto;
	Superficie supPersonalizada;
	Superficie supConNombre;
	
	public void setUp(){
		supPorDefecto = new Superficie();
		supPersonalizada = new Superficie(56.235, 59.2, 78.87);
		supConNombre = new Superficie(2.5,0,0,"Hielo lavado");
	}
	
	public void testCoeficientes(){
		
	}
	
	public void testNombres(){
		assertEquals("Asfalto",supPorDefecto.getNombre());
		assertEquals("Personalizada",supPersonalizada.getNombre());
		assertEquals("Hielo lavado", supConNombre.getNombre());
	}
	
	public void testStetters(){
		
	}
}
