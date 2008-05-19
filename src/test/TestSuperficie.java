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
		assertEquals(0.0, supPorDefecto.getRugosidad());
		assertEquals(0.0, supPorDefecto.getParticulasSueltas());
		assertEquals(100.0, supPorDefecto.getViscosidad());
		assertEquals(56.235,supPersonalizada.getRugosidad());
		assertEquals(59.2,supPersonalizada.getParticulasSueltas());
		assertEquals(78.87,supPersonalizada.getViscosidad());
		assertEquals(2.5,supConNombre.getRugosidad());
		assertEquals(0.0,supConNombre.getParticulasSueltas());
		assertEquals(0.0,supConNombre.getViscosidad());
	}
	
	public void testNombres(){
		assertEquals("Asfalto",supPorDefecto.getNombre());
		assertEquals("Personalizada",supPersonalizada.getNombre());
		assertEquals("Hielo lavado", supConNombre.getNombre());
	}
	
	public void testStetters(){
		supPorDefecto.setNombre("Ripio");
		supPorDefecto.setParticulasSueltas(80);
		supPorDefecto.setRugosidad(60);
		supPorDefecto .setViscosidad(75);
		assertEquals("Ripio",supPorDefecto.getNombre());
		assertEquals(80.0,supPorDefecto.getParticulasSueltas());
		assertEquals(60.0,supPorDefecto.getRugosidad());
		assertEquals(75.0,supPorDefecto.getViscosidad());
	}
	
	public static Test suite(){
		return new TestSuite(TestSuperficie.class);
	}

	
	public static void main (String[] args)
	{
		junit.textui.TestRunner.run(suite());
	}
}
