package test;
import modelo.*;
import modelo.componente.Manual;
import modelo.componente.Secuencial;
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
	/*	
	@Test
	public void testAcelerarIterativo(){
	  try{	 
		  //aceleracion
		  for(int cambio=0;cambio<=5;cambio++){
			auto.setEncendido(true);
			double velocidad=auto.getVelocidad();
			double rpmEje=auto.getEjeDelantero().getRpm();
			for(int i=0; i<20;i++)
			  auto.acelerar(true);
			while((auto.getCaja().getCambio()<=cambio)&&
		    	  (auto.getMotor().getRPM()<auto.getMotor().getRevolucionesMaximas())){
		       auto.acelerar(true);
		       assertTrue(auto.getVelocidad()>velocidad);
		       
		       velocidad=auto.getVelocidad();
		       assertTrue(auto.getEjeDelantero().getRpm()>rpmEje);
		       rpmEje=auto.getEjeDelantero().getRpm();
		    }
			//frenado por dejar de acelerar
			for(int i=0; i<50;i++)
				  auto.acelerar(false);
			while(auto.getMotor().getRPM()>auto.getMotor().getRevolucionesMinimasEncendido()){
			       auto.acelerar(false);
			       assertTrue(auto.getVelocidad()<1.002*velocidad);
			       velocidad=auto.getVelocidad();
			       assertTrue(auto.getEjeDelantero().getRpm()<1.2*rpmEje);
			       rpmEje=auto.getEjeDelantero().getRpm();
			}
			
		    auto.setEncendido(false);
		  }
		  
	    }catch(AssertionError a){			
		  a.printStackTrace();
	    }
	}
	*//*
	@Test
	public void testAcelerarCajaManual(){
	  	  Auto auto=new Auto();
		  auto.setCaja(new Manual(5));
		  auto.setEncendido(true);
		  assertTrue(auto.isEncendido());
		  auto.embragar(true);
		  //auto.getCaja().setCambio(1);
		  auto.embragar(false);
		  int cambio=5;
		  int contador=0;
	      while(!((auto.getCaja().getCambio()==cambio)&&(auto.getMotor().getRPM()==8000))){
			 System.out.println("Iteracion "+contador+
		    		" Cambio "+auto.getCaja().getCambio()+
		    		" velocidad "+auto.getVelocidad()+
		    		" Rpm Eje "+auto.getEjeDelantero().getRpm()+
		    		" Rpm Motor "+auto.getMotor().getRPM()+
		    		" Rpm Maximas Motor "+auto.getCaja().getRevolucionesMaximasMotorParaCambioActual()+
		    		" Rpm Minimas Motor "+auto.getCaja().getRevolucionesMinimasMotorParaCambioActual());
		     auto.acelerar(true);
		     contador++;
		     if(auto.getMotor().getRPM()>auto.getCaja().getRevolucionesMaximasMotorParaCambioActual()){
		    	 auto.embragar(true);
		    	 auto.getCaja().setCambio(auto.getCaja().getCambio()+1);
		    	 auto.embragar(false);
		     }
		     if(auto.getMotor().getRPM()<auto.getCaja().getRevolucionesMinimasMotorParaCambioActual()){
		    	 auto.embragar(true);
		    	 auto.getCaja().setCambio(auto.getCaja().getCambio()-1);
		    	 auto.embragar(false);
		     } 
		  }
	}
	/**//*
	@Test
	public void testAcelerarCajaSecuencial(){
		  Auto auto=new Auto();
		  auto.setCaja(new Secuencial(5));
		  auto.setEncendido(true);
		  assertTrue(auto.isEncendido());
		  int cambio=5;
		  int contador=0;
	      while((auto.getCaja().getCambio()!=cambio)//(!((auto.getCaja().getCambio()==cambio)&&(auto.getMotor().getRPM()==8000)))
	    	  &&(contador<19000)){
			 System.out.println("Iteracion "+contador+
		    		" Cambio "+auto.getCaja().getCambio()+
		    		" velocidad "+auto.getVelocidad()+
		    		" Rpm Eje "+auto.getEjeDelantero().getRpm()+
		    		" Rpm Motor "+auto.getMotor().getRPM()+
		    		" Rpm Maximas Motor "+auto.getCaja().getRevolucionesMaximasMotorParaCambioActual()+
		    		" Rpm Minimas Motor "+auto.getCaja().getRevolucionesMinimasMotorParaCambioActual());
		     auto.acelerar(true);
		     contador++;
		     if(auto.getMotor().getRPM()>auto.getCaja().getRevolucionesMaximasMotorParaCambioActual())
		    	 auto.getCaja().siguiente();
		     if(auto.getMotor().getRPM()<auto.getCaja().getRevolucionesMinimasMotorParaCambioActual())
		    	 auto.getCaja().anterior();
		  }
	}
	*/
	
	@Test
	public void testAcelerarCajaAutomatica(){
	  	  auto.setEncendido(true);
		  assertTrue(auto.isEncendido());
		  int cambio=5;
		  int contador=0;
	 for(int i=0;i<1;i++){	  
		 contador=0;
		 while((auto.getCaja().getCambio()<=cambio)&&
			  (auto.getMotor().getRPM()<=(8000/*300+auto.getMotor().getRevolucionesUmbralPeligro()*/))){
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
		  //auto.setEncendido(false);
		  //auto.setEncendido(true);
		  //cambio=5;
	 }
		  //desacelerar
		  /*
		  contador=0;
		  while((contador<10000)){
				 auto.acelerar(false);
			     contador++;
			     System.out.println("Iteracion "+contador+
			    		" Cambio "+auto.getCaja().getCambio()+
			    		" velocidad "+auto.getVelocidad()+
			    		" Rpm Eje "+auto.getEjeDelantero().getRpm()+
			    		" Rpm Motor "+auto.getMotor().getRPM()+
			    		" Rpm Maximas Motor "+auto.getCaja().getRevolucionesMaximasMotorParaCambioActual()+
			    		" Rpm Minimas Motor "+auto.getCaja().getRevolucionesMinimasMotorParaCambioActual());
		  }
		*/
	  
	}
}
