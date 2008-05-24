package test;
import modelo.*;
import modelo.componente.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestMotor {

	Motor motor=new Motor();
	
	@Test
	public void testMotor() {
		//creacion de motores de distinto tamaño
		for(double cilindrada=1.0; cilindrada<=5.0;cilindrada++)
		   for(double revoluciones=7000.0;revoluciones<=15000.0;revoluciones++)
			for(int cilindros=4;cilindros<=12;cilindros++)
			  try{
				  Motor motor=new Motor(cilindros,cilindrada,revoluciones);
				  assertNotNull(motor);
			  }catch (AssertionError a){
			    a.printStackTrace();
			    a.getMessage();
			    System.out.println(a);
			 }
	}
	
	@Test
	public void testEncender(){
		try{
		   motor.encender();
		   assertTrue(motor.isEncendido());
		}catch (AssertionError a){
		    a.printStackTrace();
		    a.getMessage();
		    System.out.println(a);
		}
	}
	
	@Test
	public void testApagar(){
		try{
		   motor.apagar();
		   assertFalse(motor.isEncendido());
		}catch (AssertionError a){
		    a.printStackTrace();
		    a.getMessage();
		    System.out.println(a);
		}
	}
	
	@Test
	public void testObtenerPotencia(){
		try{
		   assert motor.obtenerPotencia()>=0;
		}catch (AssertionError a){
		    a.printStackTrace();
		    a.getMessage();
		    System.out.println(a);
		}
	}
	
	
	
	@Test
	public void testAcelerar(){
		try{
		   //no encendido->no acelera
		   motor.acelerar(true);
		   assertFalse(motor.isAcelerando());
		   //si encendido->si acelera
		   Auto auto=new Auto();
		   motor.setAuto(auto);
		   motor.encender();
		   assertTrue(motor.isEncendido());
		   motor.acelerar(true);
		   assertTrue(motor.isAcelerando());
		}catch (AssertionError a){
		    a.printStackTrace();
		    a.getMessage();
		    System.out.println(a);
		}
	}
	
	@Test
	public void testGetTemperatura(){
		try{
		   assert motor.getTemperatura()>=80;
		}catch (AssertionError a){
		    a.printStackTrace();
		    a.getMessage();
		    System.out.println(a);
		}
	}
		
	@Test
	public void testGetRevolucionesMinimasEncendido(){
		try{
		   assertTrue(motor.getRevolucionesMinimasEncendido()>600);
		   assertTrue(motor.getRevolucionesMinimasEncendido()<8000);
		}catch (AssertionError a){
		    a.printStackTrace();
		    a.getMessage();
		    System.out.println(a);
		}
	}
}
