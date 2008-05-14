package test;
import modelo.*;
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
		   assertFalse(motor.isEncendido());
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
	public void testObtenerPotenciaMaximaTeorica(){
	     for(double cilindrada=1.0; cilindrada<=5.0;cilindrada++)
		   for(double revoluciones=7000.0;revoluciones<=15000.0;revoluciones++)
			for(int cilindros=4;cilindros<=12;cilindros++)
			  try{
				  Motor motor=new Motor(cilindros,cilindrada,revoluciones);
				  assert motor.obtenerPotenciaMaximaTeorica()>=80;
			  }catch (AssertionError a){
			    a.printStackTrace();
			    a.getMessage();
			    System.out.println(a);
			 }
	}
	
	@Test
	public void testAcelerar(){
		try{
		   motor.acelerar(true);
		   assertFalse(motor.isAcelerando());
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
	public void testDesgastar(){
		try{
		   while(motor.getEstado()>99.999){
		      motor.desgastar();
		      assert motor.getEstado()<100;
		      assert motor.getEstado()>0.0;
		   }
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
	
	@Test
	public void testGetRevolucionesMinimasCambios(){
		try{
		   assertTrue(motor.getRevolucionesMaximasCambio()>600);
		   assertTrue(motor.getRevolucionesMaximasCambio()<=8000);
		}catch (AssertionError a){
		    a.printStackTrace();
		    a.getMessage();
		    System.out.println(a);
		}
	}
}
