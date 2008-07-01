package test;

import modelo.*;
import static org.junit.Assert.*;
import modelo.componente.Automatica;
import org.junit.Before;
import org.junit.Test;

public class TestAutoSecondEdition{
	
	Auto auto=new Auto();
	
	@Test
	public void testAcelerarCajaAutomatica(){
	  	 auto.setEncendido(true);
		 assertTrue(auto.isEncendido());
		 auto.setCaja(new Automatica(5));
		 int cambio=5;
		 int contador=0;
	 	 while((auto.getCaja().getCambio()<cambio)){
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
		 double velocidad=auto.getVelocidad();
		 contador=0;
		 while((contador<300)){
				 auto.acelerar(false);
			     contador++;
			     System.out.println("Iteracion "+contador+
			    		" Cambio "+auto.getCaja().getCambio()+
			    		" velocidad "+auto.getVelocidad()+
			    		" Rpm Eje "+auto.getEjeDelantero().getRpm()+
			    		" Rpm Motor "+auto.getMotor().getRPM()+
			    		" Rpm Maximas Motor "+auto.getCaja().getRevolucionesMaximasMotorParaCambioActual()+
			    		" Rpm Minimas Motor "+auto.getCaja().getRevolucionesMinimasMotorParaCambioActual());
			     
			     if((auto.getVelocidad()==0)
			    	||
			    	(auto.getCaja().getCambio()<=0)	 
			        ){
			    	 contador=20000;
			    	 System.out.println("Velocidad "+velocidad);
			     }
			     velocidad=auto.getVelocidad();
		 }
		 cambio=5;
		 contador=0;
		 while((auto.getCaja().getCambio()<cambio)){
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
		 contador=0;
	 	 while((auto.getCaja().getCambio()<cambio)){
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
	 	 velocidad=auto.getVelocidad();
		 contador=0;
		 while((contador<6000)){
				 auto.acelerar(false);
			     contador++;
			     System.out.println("Iteracion "+contador+
			    		" Cambio "+auto.getCaja().getCambio()+
			    		" velocidad "+auto.getVelocidad()+
			    		" Rpm Eje "+auto.getEjeDelantero().getRpm()+
			    		" Rpm Motor "+auto.getMotor().getRPM()+
			    		" Rpm Maximas Motor "+auto.getCaja().getRevolucionesMaximasMotorParaCambioActual()+
			    		" Rpm Minimas Motor "+auto.getCaja().getRevolucionesMinimasMotorParaCambioActual());
			     
			     if((auto.getVelocidad()==0)
			    	||
			    	(auto.getCaja().getCambio()<=0)	 
			        ){
			    	 contador=20000;
			    	 System.out.println("Velocidad "+velocidad);
			     }
			     velocidad=auto.getVelocidad();
		 }
	}
}
