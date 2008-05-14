package test;

import modelo.*;

import junit.framework.TestCase;

public class TestLlanta extends TestCase{
	
	Llanta llanta = new Llanta();
	
	public void setUp() throws Exception {
	}
	
	
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
		Llanta llanta = new Llanta();
		llanta.setEstado(100);
		llanta.desgastar();
		
		assertEquals(99.999999999982, llanta.getEstado());
	}



	public void testObtenerPotencia(){
		Llanta llanta = new Llanta();
		
		llanta.setPesoNormal(15);
		llanta.setPeso(20);
		assertEquals(11.25, llanta.obtenerPotencia());
		
}
	
}

