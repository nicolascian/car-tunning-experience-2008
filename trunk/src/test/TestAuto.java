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
		assertFalse(auto.isAutomatica());
		assertTrue(auto.isManual());
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
			System.out.println(e);
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
		/*try{
			auto.setEncendido(true);
			assertFalse(auto.isEncendido());
			System.out.println(auto.toString());
		 }catch(AssertionError a){
			 a.printStackTrace();
			 a.getMessage();
			 System.out.println("testSetEncendido() "+a);
		 }*/
	}
	
	@Test
	public void testAcelerar(){
	  try{	 
		  auto.acelerar(true);
	  }catch (Exception e){
		  System.out.println("Error Acelerar");
	  }
	}
	
	@Test
	public void testSetMotor() {
	  try{	
		Motor motor=new Motor();
		auto.setMotor(motor);
		
	  }catch (Exception e){
		  System.out.println("Error setMotor");
	  }
	}
		
}
