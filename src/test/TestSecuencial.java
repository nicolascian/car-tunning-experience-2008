package test;
import modelo.componente.*;
import junit.framework.*;

public class TestSecuencial extends TestCase {

	private static Secuencial secuencial=new Secuencial(5);
	

	public void testSecuencial(){
		for(int cambios=4;cambios<=6;cambios++){
			Secuencial secuencial=new Secuencial(cambios);
			assertTrue(secuencial.getCantidadCambios()==cambios);
		}
	}
	

	public void testObtenerPotencia(){

		  assertTrue(secuencial.obtenerPotencia()==0);
	}

	public void testDesgastar(){

		  secuencial.desgastar();
		  assertTrue(secuencial.getEstado()<100);
	}
	

	public void testToString(){

		  assertNotNull(secuencial.toString());
	}
	
	public static Test suite(){
		return new TestSuite(TestSecuencial.class);
	}

	
	public static void main (String[] args)
	{
		junit.textui.TestRunner.run(suite());
	}
}
