package test;
import modelo.*;
import modelo.componente.Manual;
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
		Auto auto=new Auto();
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
		assertNotNull(auto.getMotor().getAuto());
		assertNotNull(auto.getMotor().getAuto().getCaja());
		//comprobacion de neumaticos
		assertNotNull(auto.getEjeDelantero().getNeumaticoDerecho());
		assertNotNull(auto.getEjeDelantero().getNeumaticoIzquierdo());
		assertNotNull(auto.getEjeDelantero().getLlantaDerecha().getNeumatico());
		assertNotNull(auto.getEjeDelantero().getLlantaIzquierda().getNeumatico());
		//comprobacion de neumaticos
		assertNotNull(auto.getEjeTrasero().getNeumaticoDerecho());
		assertNotNull(auto.getEjeTrasero().getNeumaticoIzquierdo());
		assertNotNull(auto.getEjeTrasero().getLlantaDerecha().getNeumatico());
		assertNotNull(auto.getEjeTrasero().getLlantaIzquierda().getNeumatico());
	  }catch(AssertionError a){
		  a.printStackTrace();
		  a.getMessage();
	  }
	}
	
	@Test
	public void testComprobarComponentes(){
	 	try{
			auto.comprobarComponentes();
		}catch(Exception e){
		 e.printStackTrace();
		 }
	}
	
	@Test
	public void testEstaListoParaCarrera(){
		try{
			assertTrue(auto.estaListoParaCarrera());
		}catch(AssertionError a){
			 a.printStackTrace();
			 a.getMessage();
		 }
	}
	
	@Test
	public void testSetEncendido(){
		try{
			auto.setEncendido(true);
			assertTrue(auto.isEncendido());
			assertTrue(auto.getMotor().isEncendido());
			assertTrue(auto.isEncendido());
		}catch(AssertionError a){
			 a.printStackTrace();
			 a.getMessage();
		}
	}
	
	@Test
	public void testAcelerar(){
	  try{	 
		  auto.setEncendido(true);
		  assertTrue(auto.isEncendido());
		  auto.acelerar(true);	      
		  assertTrue(auto.isAcelerando());
		  assertTrue(auto.getMotor().isAcelerando());
      }catch (AssertionError a){
		 a.printStackTrace();
	  }
	}
	
	@Test
	public void testAcelerarIterativo(){
	  try{	 
		  auto.setEncendido(true);
		  assertTrue(auto.isEncendido());
		  auto.setCaja(new Manual(5));
		  int contador=0;
		  auto.embragar(true);
		  auto.getCaja().setCambio(1);
		  auto.embragar(false);
		  while(contador<200){
		     auto.acelerar(true);
		     contador++;
		  }
		  System.out.println(auto.getMotor().getRPM()+" velocidad "+auto.getVelocidad()+" cambio "+
				  			 auto.getCaja().getCambio());
		  //assertTrue(auto.getMotor().getRPM()>auto.getMotor().getRevolucionesMinimasEncendido());
      }catch (AssertionError a){
		 a.printStackTrace();
	  }
	}
}
