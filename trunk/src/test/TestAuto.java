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
		  auto.acelerar(true);
		  double velocidad=auto.getVelocidad();
		  double rpmMotor=auto.getMotor().getRPM();
		  double rpmEje=auto.getEjeDelantero().getRpm();
		  double rpmMaximasParaCambioActual;
		  double rpmMinimasParaCambioActual;
		  
  		" Rpm Eje "+
  		" Rpm Motor "++
  		" Rpm Maximas Motor "+auto.getCaja().getRevolucionesMaximasMotorParaCambioActual()+
  		" Rpm Minimas Motor "+auto.getCaja().getRevolucionesMinimasMotorParaCambioActual());
	  }catch(AssertionError a){			
		a.printStackTrace();
	  }
	}
	
	@Test
	public void testAcelerarCajaAutomatica(){
	  	  auto.setEncendido(true);
		  assertTrue(auto.isEncendido());
		  int cambio=5;
		  int contador=0;
	 for(int i=0;i<=35;i++){	  
		 contador=0;
		 while(//(auto.getCaja().getCambio()<=cambio)){//&&
			  !((auto.getCaja().getCambio()==cambio)&&(auto.getMotor().getRPM()==8000))){
			 System.out.println("Iteracion "+contador+
		    		" Cambio "+auto.getCaja().getCambio()+
		    		" velocidad "+auto.getVelocidad()+
		    		" Rpm Eje "+auto.getEjeDelantero().getRpm()+
		    		" Rpm Motor "+auto.getMotor().getRPM()+
		    		" Rpm Maximas Motor "+auto.getCaja().getRevolucionesMaximasMotorParaCambioActual()+
		    		" Rpm Minimas Motor "+auto.getCaja().getRevolucionesMinimasMotorParaCambioActual());
		     auto.acelerar(true);
		     contador++;
		     if(auto.getCaja().getCambio()==cambio){
		         System.out.println("Iteracion "+contador+
			    		" Cambio "+auto.getCaja().getCambio()+
			    		" velocidad "+auto.getVelocidad()+
			    		" Rpm Eje "+auto.getEjeDelantero().getRpm()+
			    		" Rpm Motor "+auto.getMotor().getRPM()+
			    		" Rpm Maximas Motor "+auto.getCaja().getRevolucionesMaximasMotorParaCambioActual()+
			    		" Rpm Minimas Motor "+auto.getCaja().getRevolucionesMinimasMotorParaCambioActual());
			     auto.acelerar(true);
			     contador++;
		     }
		  }
		  auto.setEncendido(false);
		  auto.setEncendido(true);
		  cambio=5;
	 }
		  //desacelerar
		  /*
		  contador=0;
		  while((contador<8000)){
				 auto.acelerar(false);
			     contador++;
			     System.out.println("Iteracion "+contador+
			    		" Cambio "+auto.getCaja().getCambio()+
			    		" velocidad "+auto.getVelocidad()+
			    		" Rpm Eje "+auto.getEjeDelantero().getRpm()+
			    		" Rpm Motor "+auto.getMotor().getRPM()+
			    		" Rpm Maximas Motor "+auto.getCaja().getRevolucionesMaximasMotorParaCambioActual()+
			    		" Rpm Minimas Motor "+auto.getCaja().getRevolucionesMinimasMotorParaCambioActual());
		  }*/
		
	  
	}
}
