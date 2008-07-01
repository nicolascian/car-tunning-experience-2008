package test;

import junit.framework.*;
import modelo.*;
import control.*;

public class TestIntermedio extends TestCase {

	public void testJugar() {
		
		Auto auto = new Auto();
		Habilidad habilidad = new Intermedio(auto);
		control.Virtual ctrlVirtual = new control.Virtual(habilidad, auto);
		
		modelo.Virtual virtual = new modelo.Virtual(ctrlVirtual, auto);
		
		virtual.jugar();
		
		
		
	}

}
