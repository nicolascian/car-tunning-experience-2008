package test;
import modelo.*;
import org.junit.Test;
import junit.framework.TestCase;

public class TestLLanta extends TestCase{
	
	Llanta llanta = new Llanta();
	
	public void setUp() throws Exception {
	}
	
	@Test
	public void testEstado(){
		Llanta llanta = new Llanta();
		System.out.println(llanta.toString());
	}
	public void testAfectar(){
		llanta.setCoeficienteDeDesgastePorSuperficie(0.8);
		llanta.afectar(new Superficie(20,20,20));
	    assertEquals(1.59992,llanta.getCoeficienteDeDesgastePorSuperficie());
	}
	public void testDesgastar(){
		llanta.desgastar();
		llanta.setCoeficienteDeDesgastePorSuperficie(1.0);
		llanta.setEstado(80);
		//assertEquals(99.0, llanta.getEstado());
	}
	public void testObtenerPotencia(){
		llanta.setPesoNormal(50);
		llanta.setPeso(1000);
		assertEquals(0.75 , llanta.obtenerPotencia());
	}
	
}

