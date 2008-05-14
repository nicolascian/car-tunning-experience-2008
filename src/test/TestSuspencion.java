package test;
import modelo.*;
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
	
}
