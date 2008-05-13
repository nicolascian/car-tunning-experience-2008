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
		
		
	}
	
	@Test
	public void testDesgastar() {
		
	}

	@Test
	public void testObtenerPotencia() {
		//motor.getPotencia();
	}

	@Test
	public void testInstalar() {
		
	}


	@Test
	public void testEncender() {

	}

	@Test
	public void testApagar() {

	}

	@Test
	public void testAcelerar() {

	}

	@Test
	public void testModificarRpmDesdeCaja() {

	}

	@Test
	public void testSetRPM() {

	}

	@Test
	public void testAfectar() {

	}

	@Test
	public void testSetEncendido() {

	}

	@Test
	public void testIsAcelerando() {

	}

	@Test
	public void testSetAcelerando() {

	}

}
