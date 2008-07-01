package test;

import junit.framework.*;
import modelo.*;
import control.*;
import modelo.componente.*;
public class TestPrincipiante extends TestCase {

	public void TestPrincipiante(){
		
	}
	
	public void testJugar() {
		
		Auto auto = new Auto();
		
		Habilidad principiante = new Principiante(auto);
		
		control.Jugador virtual = new control.Virtual(principiante, new Auto());
		
		principiante.jugar(true);
		
		Auto auto2 = virtual.getAuto();
		Motor motor = auto2.getMotor();
		
		assertEquals(true, auto2.isEncendido());
		assertEquals(true, motor.isAcelerando());
		
	}

}
