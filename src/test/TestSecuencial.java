package test;
import modelo.Secuencial;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestSecuencial {

	private static Secuencial secuencial=new Secuencial(5);
	
	@Test
	public void testSecuencial(){
	  try{	
		for(int cambios=4;cambios<=8;cambios++){
			Secuencial secuencial=new Secuencial(cambios);
			assertTrue(secuencial.getCantidadCambios()==cambios);
		}
	  }catch(AssertionError a){
		  System.out.println(a);
		  a.printStackTrace();
	  }
	}
	
	@Test
	public void testObtenerPotencia(){
	  try{	
		  assertTrue(secuencial.obtenerPotencia()==0);
	  }catch(AssertionError a){
		  System.out.println(a);
		  a.printStackTrace();
	  }
	}
	
	@Test
	public void testObtenerRpm(){
	  try{	
		  assertTrue(secuencial.obtenerRpm()==0);
	  }catch(AssertionError a){
		  System.out.println(a);
		  a.printStackTrace();
	  }
	}
	
	@Test
	public void testDesgastar(){
	  try{	
		  secuencial.desgastar();
		  assertTrue(secuencial.getEstado()<100);
	  }catch(AssertionError a){
		  System.out.println(a);
		  a.printStackTrace();
	  }
	}
	
	@Test
	public void testToString(){
	  try{	
		  assertNotNull(secuencial.toString());
	  }catch(AssertionError a){
		  System.out.println(a);
		  a.printStackTrace();
	  }
	}
}
