package test;
import static org.junit.Assert.*;
import org.junit.Test;
import modelo.componente.*;
import modelo.Auto;

public class TestEje{

	Auto auto=new Auto();
	Eje eje=new Eje(auto, auto.getLlantaDelanteraDerecha(), auto.getLlantaDelanteraIzquierda());
	
	@Test
	public void testLlantas(){
	  try{	
		assertNotNull(eje.getAuto());
		assertNotNull(eje.getLlantaDerecha());
		assertNotNull(eje.getLlantaIzquierda());
		assertNotNull(eje.getLlantaDerecha().getEje());
		assertNotNull(eje.getLlantaIzquierda().getEje());
	  }catch (AssertionError a){
		  a.printStackTrace();
	  }
	}
	
	@Test
	public void testNeumaticos(){
	  try{	
		assertNotNull(eje.getLlantaDerecha().getNeumatico().
				getLlanta());
		assertNotNull(eje.getLlantaIzquierda().getNeumatico().
				getLlanta());
	  }catch (AssertionError a){
		  a.printStackTrace();
	  }
	}
		
	@Test
	public void testObtenerPotencia(){
	  try{	
		assertTrue(eje.obtenerPotencia()==0);
	  }catch(AssertionError a){
		  a.printStackTrace();
	  }  
	}
}

	



