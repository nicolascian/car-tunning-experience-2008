package test;
import junit.framework.*;
import modelo.*;

public class TestTramo extends TestCase {
	Tramo t = new Tramo();
		
	public void testLongitud(){
		assert(t.getLongitud() == 100);
	}
	
	public void testInicio(){
		assertEquals(0.0,t.getPosInicial());
	}
	
	public void testFinla(){
		assert (t.getPosFinal()==100);
	}
	
	public void testToString(){
		assert (t.toString() == "Tramo de 100 metros");
	}
	
	public static Test suite(){
		return new TestSuite(TestTramo.class);
	}
	
	public static void main (String[] args)
	{
		junit.textui.TestRunner.run(suite());
	}
}
