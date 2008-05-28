package test;

import junit.framework.*;
import modelo.*;
import control.*;
import modelo.componente.*;
public class TestPrincipiante extends TestCase {

	public void testJugar() {
		
		Habilidad principiante = new Principiante();
		
		Jugador virtual = new Virtual(principiante, new Auto());
		
		principiante.jugar();
		
		Auto auto = virtual.getAuto();
		Motor motor = auto.getMotor();
		
		assertEquals(true, auto.isEncendido());
		assertEquals(true, motor.isAcelerando());
		
	}

}
