package test;
import junit.framework.*;
import modelo.*;

public class TestTramo extends TestCase {
	Tramo t = new Tramo(122.59,457.58, new Clima(), new Superficie());
	Auto auto = new Auto();
	public void testLongitud(){
		assertEquals(334.99, t.getLongitud());
	}
	
	public void testInicio(){
		assertEquals(122.59 ,t.getPosInicial());
	}
	
	public void testFinal(){
		assertEquals (457.58, t.getPosFinal());
	}
	
	public void testToString(){
		assertEquals("Tramo de 334.99 metros", t.toString());
	}
	
	
	public void testCambioFin(){
		t.setPosFinal(122.6);
		assertEquals(122.6,t.getPosFinal());
	}
	
	public void testLongFinCambiado(){
		t.setPosFinal(122.6);
		assertEquals(0.01, t.getLongitud());
	}
	
	public void testEstaEnTramo(){
		auto.setPosicion(258.658);
		assertEquals(true, t.estaAutoEnTramo(auto));
	}
	
	public void testEstaEnTramo2(){
		auto.setPosicion(t.getPosFinal());
		assertEquals(false, t.estaAutoEnTramo(auto));
	}
	
	public void testEstaEnTramo3(){
		auto.setPosicion(t.getPosInicial());
		assertEquals(true, t.estaAutoEnTramo(auto));
	}
	
	public void testEstaEnTramo4(){
		auto.setPosicion(122);
		assertEquals(false, t.estaAutoEnTramo(auto));
	}
	
	public static Test suite(){
		return new TestSuite(TestTramo.class);
	}
	
	public static void main (String[] args)
	{
		junit.textui.TestRunner.run(suite());
	}
}
