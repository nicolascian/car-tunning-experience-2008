package test;

import junit.framework.TestCase;
import modelo.*;

public class TestClima extends TestCase {
	
	Clima clima1 = new Clima();
	Clima clima2 = new Clima(100, 90, 80);


	public void testGetHumedad() {
		assertEquals(50.0, clima1.getHumedad());
		assertEquals(90.0, clima2.getHumedad());
	}

	public void testGetPresion() {
		assertEquals(1013.0, clima1.getPresion());
		assertEquals(80.0, clima2.getPresion());
	}


	public void testGetTemperatura() {
		assertEquals(25.0, clima1.getTemperatura());
		assertEquals(100.0, clima2.getTemperatura());

	}


}
