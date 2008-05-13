package test;
import junit.framework.*;
import modelo.*;
import java.util.*;

public class TestPista extends TestCase{
	Jugador[] jugador;
	Pista pista;
	ArrayList<Tramo> tramos;
	
	public void setUp(){
		Jugador[] jugador = new Jugador[2];
		jugador[0]= new Virtual(new Principiante());
		jugador[1] = new Virtual(new Principiante());
		tramos = new ArrayList<Tramo>();
		tramos.add(new Tramo(0,150,new Clima(),new Superficie()));
		tramos.add(new Tramo(150, 321.89, new Clima(35,75,1014),new Superficie(50,50,50)));
		pista = new Pista(jugador,tramos);
	}
	
	public void testLongitud(){
		assertEquals(321.89,pista.getLongitud());
	}
	
	public void testTramoActual1()throws Exception{
		try{
		assertEquals(tramos.get(0),pista.buscarTramoActual(0));
		}catch (Exception e){
			throw e;
			};
	}
	
	public void testJugador1(){
		assertNotNull(pista.getJugador(0));
	}
		
	public void testTramoActual2(){
		pista.getJugador(0).getAuto().setPosicion(200);
		try{
			assertEquals(tramos.get(1), pista.buscarTramoActual(0));
		}catch (Exception e){};
	}

	public static Test suite(){
		return new TestSuite(TestPista.class);
	}

	
	public static void main (String[] args){
		junit.textui.TestRunner.run(suite());
	}

}
