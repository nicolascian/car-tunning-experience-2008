package test;
import junit.framework.*;
import modelo.*;

public class TestTramo extends TestCase {
	Tramo t = new Tramo();
		
	public void testLongitud(){
		assert(t.getLongitud() == 100);
	}
	
	public void testInicio(){
		assert (t.getPosInicial() ==0);
	}
	
	public void testFinla(){
		assert (t.getPosFinal()==100);
	}
	
	public static Test suite(){
		return new TestSuite(TestTramo.class);
	}
	
	public static void main (String[] args)
	{
		junit.textui.TestRunner.run(suite());
	}
}
