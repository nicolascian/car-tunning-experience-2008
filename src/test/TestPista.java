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

	public static Test suite(){
		return new TestSuite(TestPista.class);
	}

	
	public static void main (String[] args){
		junit.textui.TestRunner.run(suite());
	}

}
