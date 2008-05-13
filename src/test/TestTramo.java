package test;
import junit.framework.*;
import modelo.*;

public class TestTramo extends TestCase {
	Tramo t = new Tramo();
		
	public void testLongitud(){
		assertEquals(100.0, t.getLongitud());
	}
	
	public void testInicio(){
		assertEquals(0.0,t.getPosInicial());
	}
	
	public void testFinal(){
		assertEquals (100.0, t.getPosFinal());
	}
	
	public void testToString(){
		assertEquals("Tramo de 100.0 metros", t.toString());
	}
	
	public static Test suite(){
		return new TestSuite(TestTramo.class);
	}
	
	public static void main (String[] args)
	{
		junit.textui.TestRunner.run(suite());
	}
}
