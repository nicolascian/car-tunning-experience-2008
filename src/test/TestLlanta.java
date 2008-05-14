package test;

import modelo.Llanta;
import junit.framework.TestCase;

public class TestLlanta extends TestCase {

	public void testDesgastar(){
		Llanta llanta = new Llanta();
		llanta.setEstado(100);
		llanta.desgastar();
		
		assertEquals(99.999999999982, llanta.getEstado());
	}



	public void testObtenerPotencia(){
		Llanta llanta = new Llanta();
		
		llanta.setPesoNormal(4);
		llanta.setPeso(3);
		assertEquals(20.0, llanta.obtenerPotencia());
		
}
	public void testAfectar(){
		Llanta llanta = new Llanta();
		assertEquals(3.0, llanta.getCoeficienteDeDesgastePorSuperficie());
		
	}
}