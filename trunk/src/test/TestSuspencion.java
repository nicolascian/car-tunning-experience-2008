package test;
import modelo.*;
import modelo.componente.*;
import org.junit.Before;
import org.junit.Test;
import junit.framework.*;


public class TestSuspencion extends TestCase{
	
	Suspension suspension =new Suspension(10.3);
	
	@Before
	public void setUp() throws Exception {
	}
	@Test
	public void testEstado(){
		System.out.println(suspension.toString());
	}
	@Test
	public void testDesgastar(){
		suspension.afectar(new Clima(100,50,50));
		suspension.afectar(new Superficie(50,50,10));
		suspension.desgastar();
		assertEquals(98.9 , this.suspension.getEstado());
	}
	@Test
	public void testAfectarPorClima(){
		suspension.afectar(new Clima(100,50,50));
		assertEquals(1.0 , this.suspension.getEfectoClimatico());
		}
	@Test
	public void testAfectarPorSuperficie(){
		suspension.afectar(new Superficie(50,50,10));
		assertEquals(0.1 , this.suspension.getEfectoSuperficie());
		}
	@Test
	public void testObtenerPotencia(){
		suspension.setEfectoClimatico(2.1);
		suspension.setEfectoSuperficie(2.3);
		suspension.setEstado(85);
		assertEquals( 42.28665 , this.suspension.obtenerPotencia());
		}
}
