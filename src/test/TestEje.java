package test;
import junit.framework.TestCase;
import modelo.*;

public class TestEje extends TestCase{

	Eje eje;
	
	public void setUp(){
		eje = new Eje();
		eje.setNeumaticoDerecho(new NeumaticoMixto());
		eje.setNeumaticoIzquierdo(new NeumaticoMixto());
		eje.setLlantaDerecha(new Llanta());
		eje.setLlantaIzquierda(new Llanta());
		
	}
 
	public void testEstado() {
		Eje eje= new Eje();
		System.out.println(eje.toString());
		
	}
	public void testObtenerPotencia(){
		assertTrue(eje.obtenerPotencia()>0);
		assertTrue(eje.obtenerPotencia()<50);
		System.out.println("Potencia: " +eje.obtenerPotencia());
		//assertTrue(eje.obtenerPotencia()<50);
	
	}
	
		
	
	
		
		
		
}

	



