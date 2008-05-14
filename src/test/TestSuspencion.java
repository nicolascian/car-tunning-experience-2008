package test;
import modelo.*;
import org.junit.Before;
import org.junit.Test;

public class TestSuspencion {
	
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
		//suspension.afectar();
		//suspension.afectar();
		suspension.desgastar();
		System.out.println(suspension.toString());
	}
	
}
