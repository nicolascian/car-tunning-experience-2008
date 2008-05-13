package test;
import modelo.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestAuto {

	Auto auto;
		
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testAuto() {
	  try{	
		auto=new Auto();
		System.out.println(auto.toString());
	  }catch (Exception e){
		  System.out.print("Error Auto");
	  }
	}
			
	@Test
	public void testSetEncendido(){
		try{
		    //auto.setEncendido(true);
	        auto.getMotor().encender();
			System.out.println(auto.toString());
		}catch (Exception e){
			System.out.println(" Error Encendido");
		}
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
