package test;

import static org.junit.Assert.*;
import modelo.componente.Carroceria;
import org.junit.Test;

public class TestCarroceria {

	@Test
	public void testCarroceria() {
		Carroceria carroceria=new Carroceria(2.5);
		try{
			assertTrue(carroceria.getSuperficieFrontal()==2.5);
			assertTrue(carroceria.getEstado()==100);
			assertTrue(carroceria.getPeso()==905);
			assertTrue(carroceria.getTemperatura()==25);
		}catch(AssertionError a){
			a.printStackTrace();
		}
	}


}
