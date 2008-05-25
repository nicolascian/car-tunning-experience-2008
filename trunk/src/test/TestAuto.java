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
		assertNotNull(auto.getEjeDelantero().getNeumaticoDerecho().
			      getComponenteContenedor());
		assertNotNull(auto.getEjeDelantero().getNeumaticoIzquierdo().
			      getComponenteContenedor());
		assertNotNull(auto.getEjeDelantero().getLlantaDerecha().getNeumatico().
			      getComponenteContenedor());
		assertNotNull(auto.getEjeDelantero().getLlantaIzquierda().getNeumatico().
			      getComponenteContenedor());
		//comprobacion de neumaticos
		assertNotNull(auto.getEjeTrasero().getNeumaticoDerecho().
			      getComponenteContenedor());
		assertNotNull(auto.getEjeTrasero().getNeumaticoIzquierdo().
			      getComponenteContenedor());
		assertNotNull(auto.getEjeTrasero().getLlantaDerecha().getNeumatico().
			      getComponenteContenedor());
		assertNotNull(auto.getEjeTrasero().getLlantaIzquierda().getNeumatico().
			      getComponenteContenedor());
		assertNotNull(auto.getEjeTrasero().getLlantaIzquierda().getNeumatico().
				      getComponenteContenedor());
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
	public void testGetPeso(){
		try{
			assertTrue(auto.getPeso()>0);
		}catch(AssertionError a){
			 a.printStackTrace();
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
		  while(contador<60){
			 auto.acelerar(true);
		     contador++;
		     System.out.println("Iteracion "+contador+
		    		" Cambio "+auto.getCaja().getCambio()+
		    		" velocidad "+auto.getVelocidad()+
		    		" Rpm Eje "+auto.getEjeDelantero().getRpm()+
		    		" Rpm Motor "+auto.getMotor().getRPM()+
		    		" Rpm Maximas Motor "+auto.getCaja().getRevolucionesMaximasMotorParaCambioActual());
		     if((auto.getMotor().getRPM()>=auto.getCaja().getRevolucionesMaximasMotorParaCambioActual())&&
		        (auto.getCaja().getCambio()!=5)){
		    	 auto.embragar(true);
		    	 auto.getCaja().setCambio(auto.getCaja().getCambio()+1);
		    	 auto.embragar(false);
		     } 
		     if((auto.getMotor().getRPM()==auto.getMotor().getRevolucionesMaximas())&&
				        (auto.getCaja().getCambio()==5))
		       contador=60;	 
		  }
		 
		}catch(AssertionError a){
		a.printStackTrace();
	  }
	}
}
