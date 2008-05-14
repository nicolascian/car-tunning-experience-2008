package test;

import junit.framework.*;
import modelo.*;

public class TestAutomatica extends TestCase {

	public void testGenerarRelacionesDeCaja() {
		fail("Not yet implemented");
	}

	public void testChequear() {
		
		
		Auto auto = new Auto();
		
		Caja automatica = new Automatica(5);
		
		auto.setCaja(automatica);
		
		auto.setEncendido(true);
		auto.acelerar(true);
		
		assertEquals(true, auto.isEncendido());
		assertEquals(true, auto.getMotor().isAcelerando());
		
		/* lo pongo a 7000 rpm */
		while( auto.getMotor().getRPM() <= 700){ 
		
			automatica.Chequear();	
			
			if (auto.getMotor().getRPM() > auto.getMotor().getRevolucionesMaximasCambio()){
				assertEquals(1 ,auto.getCaja().getCambio());
			}
		}//fin while
		
		
	}

}
