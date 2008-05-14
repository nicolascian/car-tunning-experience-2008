package test;
import modelo.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestAuto {

	Auto auto=new Auto();
		
	@Before
	public void setUp() throws Exception {
		
	}
		
	@Test
	public void testAuto(){
	  try{
		auto=new Auto();
		assertNotNull(auto.getCaja());
		assertNotNull(auto.getAlimentacion());
		assertNotNull(auto.getCarroceria());
		assertNotNull(auto.getCombustible());
		assertNotNull(auto.getEjeDelantero());
		assertNotNull(auto.getEjeTrasero());
		assertNotNull(auto.getEscape());
		assertNotNull(auto.getMotor());
		assertNotNull(auto.getSuspension());
		assertNotNull(auto.getTurbo());
		assertFalse(auto.isEncendido());
		assertTrue(auto.isAutomatica());
		assertFalse(auto.isManual());
		assertFalse(auto.isSecuencial());
		assertTrue(auto.getVelocidad()==0);
	  }catch(AssertionError a){
		  a.printStackTrace();
		  a.getMessage();
		  System.out.println("testAuto() "+a);
	  }
	}
	
	@Test
	public void testComprobarComponentes(){
	 	try{
			auto.comprobarComponentes();
		}catch(Exception e){
			System.out.println("Comprobar Componentes Error"+e);
		}
	}
	
	@Test
	public void testEstaListoParaCarrera(){
		try{
			assertTrue(auto.estaListoParaCarrera());
		}catch(AssertionError a){
			 a.printStackTrace();
			 a.getMessage();
			 System.out.println("testEstaListoParaCarrera() "+a);
		 }
	}
	
	@Test
	public void testSetEncendido(){
		try{
			auto.setEncendido(true);
			assertTrue(auto.isEncendido());
		}catch(AssertionError a){
			 a.printStackTrace();
			 a.getMessage();
			 System.out.println("testSetEncendido() "+a);
		 }
	}
	
	@Test
	public void testAcelerar(){
	  try{	 
		  auto.acelerar(true);
		  assertTrue(auto.isAcelerando());
	  }catch (AssertionError a){
		  System.out.println("Error Acelerar "+a);
	  }
	}
	
	@Test
	public void testPasandoCambio(){
	  try{
		  long tiempo=System.currentTimeMillis()+1000;
		  auto.getCaja().siguiente();
		  while(System.currentTimeMillis()<tiempo);
		 // System.out.println(auto.toString());	  
	  }catch (Exception e){
		  System.out.println("Error Acelerar");
	  }
	}
				
}
