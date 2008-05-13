package test;
import modelo.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestMotor {

	Motor motor;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMotor() {
		motor=new Motor();
		assert motor.getAuto()!=null: "Hola";
		System.out.println(motor.toString());
	}
	
	@Test
	public void testEncender(){
		motor.encender();
		System.out.println(motor.toString());
	}
}
