package test;
import modelo.*;
import modelo.componente.Llanta;
import junit.framework.TestCase;
public class testAlgoPesos extends TestCase {
	AlgoPesos uno;
	AlgoPesos dos;
	public void setUp() throws Exception {
		uno = new AlgoPesos(25,23);
		dos = new AlgoPesos(35,27);
	}

	public void testRestar(){
		assertEquals(10,dos.restar(uno).getEntero());
		assertEquals(4,dos.restar(uno).getDecimal());
		uno.setDecimal(86);
		assertEquals(9,dos.restar(uno).getEntero());
		assertEquals(41,dos.restar(uno).getDecimal());
		assertEquals(-10,uno.restar(dos).getEntero());
		uno.setEntero(48);
		uno.setDecimal(65);
		
	}
	
}