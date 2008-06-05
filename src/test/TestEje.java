package test;
import junit.framework.*;
import modelo.*;
import modelo.componente.*;

public class TestEje extends TestCase{

	Auto auto;
	Eje eje;
	

	public void setUp(){
		auto=new Auto();
		eje=new Eje(auto, auto.getLlantaDelanteraDerecha(), auto.getLlantaDelanteraIzquierda());
	}
	
	public void testLlantas(){

		assertNotNull(eje.getAuto());
		assertNotNull(eje.getLlantaDerecha());
		assertNotNull(eje.getLlantaIzquierda());
		assertNotNull(eje.getLlantaDerecha().getEje());
		assertNotNull(eje.getLlantaIzquierda().getEje());

	}
	

	public void testNeumaticos(){

		assertNotNull(eje.getLlantaDerecha().getNeumatico().
				getLlanta());
		assertNotNull(eje.getLlantaIzquierda().getNeumatico().
				getLlanta());

	}
		

	public void testObtenerPotencia(){

		assertTrue(eje.obtenerPotencia()== 5);
 	}
	
	public static Test suite(){
		return new TestSuite(TestEje.class);
	}

	
	public static void main (String[] args)
	{
		junit.textui.TestRunner.run(suite());
	}
}

	



