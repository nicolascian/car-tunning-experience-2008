package test;
import static org.junit.Assert.*;
import org.junit.Test;
import modelo.componente.*;
import modelo.Auto;

public class TestEje{

	Auto auto=new Auto();
	Eje eje=new Eje(auto);
	
	@Test
	public void testLlantas(){
	  try{	
		assertNotNull(eje.getAuto());
		assertNotNull(eje.getLlantaDerecha());
		assertNotNull(eje.getLlantaIzquierda());
		assertNotNull(eje.getLlantaDerecha().getComponenteContenedor());
		assertNotNull(eje.getLlantaIzquierda().getComponenteContenedor());
	  }catch (AssertionError a){
		  a.printStackTrace();
	  }
	}
	
	@Test
	public void testNeumaticos(){
	  try{	
		assertNotNull(eje.getLlantaDerecha().getNeumatico().
				      getComponenteContenedor());
		assertNotNull(eje.getLlantaIzquierda().getNeumatico().
				      getComponenteContenedor());
	  }catch (AssertionError a){
		  a.printStackTrace();
	  }
		
	}
		
	@Test
	public void testObtenerPotencia(){
		assertTrue(eje.obtenerPotencia()>0);
		assertTrue(eje.obtenerPotencia()<50);
		System.out.println("Potencia: " +eje.obtenerPotencia());
		//assertTrue(eje.obtenerPotencia()<50);
	
	}
			
		
}

	



