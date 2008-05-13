package test;
import junit.framework.*;
import modelo.*;

public class TestTramo extends TestCase {
	Tramo t = new Tramo(122.59,457.58, new Clima(), new Superficie());
	Auto auto = new Auto();
	
	public void testSetFinal(){
		t.setPosFinal(300.15);
		assertEquals(300.15,t.getPosFinal());
	}
	
	public void testSetInicio(){
		t.setPosInicial(100.32);
		assertEquals(100.32, t.getPosInicial());
	}
	
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
	
	public void testLongInicCambiado(){
		t.setPosInicial(0);
		assertEquals(457.58, t.getLongitud());
	}

	public void testMaxFin(){
		t.setPosInicial(0);
		t.setPosFinal(Double.MAX_VALUE);
		assertEquals(Double.MAX_VALUE,t.getLongitud());
	}
	
	public void testMaxFinMasxInicMenos1(){
		t.setPosInicial(Double.MAX_VALUE - 1);
		t.setPosFinal(Double.MAX_VALUE);
		assertEquals(1.0,t.getLongitud());
	}
	
	public void testEstaEnTramo(){
		//Se le da al auto una poscion dentro del tramo
		auto.setPosicion(258.658);
		assertEquals(true, t.estaAutoEnTramo(auto));
	}
	
	public void testEstaEnTramo2(){
		//Se le da al auto la posicion final del tramo
		auto.setPosicion(t.getPosFinal());
		assertEquals(false, t.estaAutoEnTramo(auto));
	}
	
	public void testEstaEnTramo3(){
		//se le da al auto la posicion inicial del tramo
		auto.setPosicion(t.getPosInicial());
		assertEquals(true, t.estaAutoEnTramo(auto));
	}
	
	public void testEstaEnTramo4(){
		//se le da al auto una posicion fuera del tramo
		auto.setPosicion(122);
		assertEquals(false, t.estaAutoEnTramo(auto));
	}
	
	public void testCompruebaClima(){
		assertNotNull(t.getClima());
	}
	
	public void testCompruebaSuperficie(){
		assertNotNull(t.getSuperficie());
	}
	
	public static Test suite(){
		return new TestSuite(TestTramo.class);
	}

	
	public static void main (String[] args)
	{
		junit.textui.TestRunner.run(suite());
	}
}
