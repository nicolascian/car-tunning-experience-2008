package test;
import modelo.Manual;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestManual {

	Manual manual;
	
	@Before
	public void setUp() throws Exception {
	
	}
	
	@Test
	public void Manual(){
		
		for(int cambios=1;cambios<=8;cambios++)
			manual=new Manual(cambios);
		
	}
	
	@Test
	public void testChequear() {
		
	}

	@Test
	public void testSetCambio() {
		
	}

}
