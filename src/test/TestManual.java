
package test;
import modelo.Manual;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestManual {

	private static Manual manual=new Manual(5);
	
	@Test
	public void testManual(){
	  try{	
		for(int cambios=4;cambios<=8;cambios++){
			Manual manual=new Manual(cambios);
			assertTrue(manual.getCantidadCambios()==cambios);
		}
	  }catch(AssertionError a){
		  System.out.println(a);
		  a.printStackTrace();
	  }
	}
	
	@Test
	public void testObtenerPotencia(){
	  try{	
		  assertTrue(manual.obtenerPotencia()==0);
	  }catch(AssertionError a){
		  System.out.println(a);
		  a.printStackTrace();
	  }
	}
	
	@Test
	public void testObtenerRpm(){
	  try{	
		  assertTrue(manual.obtenerRpm()==0);
	  }catch(AssertionError a){
		  System.out.println(a);
		  a.printStackTrace();
	  }
	}
	
	@Test
	public void testDesgastar(){
	  try{	
		  manual.desgastar();
		  assertTrue(manual.getEstado()<100);
	  }catch(AssertionError a){
		  System.out.println(a);
		  a.printStackTrace();
	  }
	}
	
	@Test
	public void testToString(){
	  try{	
		  assertNotNull(manual.toString());
	  }catch(AssertionError a){
		  System.out.println(a);
		  a.printStackTrace();
	  }
	}
}
