package test;

import modelo.*;
import modelo.componente.*;
import modelo.componente.neumaticos.*;

import junit.framework.TestCase;

public class TestLlanta extends TestCase{
	
	Llanta llanta ;
	
	public void setUp() throws Exception {
		llanta = new Llanta();
	}
	
	
	public void testEstado(){
		assertEquals(100.0, llanta.getEstado());
	}
	public void testAfectar(){
		llanta.setCoeficienteDeDesgastePorSuperficie(0.8);
		llanta.afectar(new Superficie(20,20,20));
	    assertEquals(1.59992,llanta.getCoeficienteDeDesgastePorSuperficie());
	}
	
	public void testDesgastar(){

		llanta.setEstado(100);
		llanta.desgastar();
		
		assertEquals(99.99999999999999, llanta.getEstado());
	}

	public void testObtenerPotencia(){
		llanta.setNeumatico(new NeumaticoMixto());
		llanta.setPesoNormal(15);
		llanta.setPeso(20);
		assertEquals(12.25, llanta.obtenerPotencia());
		
    }
	
}

