package test;
import junit.framework.*;
import modelo.*;
import java.util.*;

public class TestPista extends TestCase{
	Jugador[] jugador;
	ArrayList<Tramo> tramos;
	Pista pista ;
	Pista pista2;
	
	public void setUp(){
		Jugador[] jugador = new Jugador[2];
		jugador[0]= new Virtual(new Principiante());
		jugador[1] = new Virtual(new Principiante());
		jugador[0].getAuto().setPosicion(0);
		jugador[1].getAuto().setPosicion(0);
		tramos = new ArrayList<Tramo>();
		tramos.add(new Tramo(0,150,new Clima(),new Superficie()));
		tramos.add(new Tramo(150, 321.89, new Clima(35,75,1014),new Superficie(50,50,50)));
		pista = new Pista(jugador,tramos);
	}
	
	public void testLongitud(){
		assertEquals(321.89,pista.getLongitud());
	}
	
	public void testToString(){
		assertEquals("Pista de 321.89 metros, compuesta por 2 tramos.", pista.toString());
	}
	
	public void testTramoActual1(){
		try{
		assertEquals(tramos.get(0),pista.buscarTramoActual(0));
		}catch (Exception e){
			fail("No deberian haber excepciones");
			};
	}
	
	public void testJugador1(){
		assertNotNull(pista.getJugador(0));
	}
	
	public void testJugador2(){
		assertNotNull(pista.getJugador(1));
	}
	
	/*
	 * Se le da al auto una poscion dentro de la pista y se
	 * espera que se encuentre el tramo en el que se encuentra
	 */
	public void testTramoActual2(){
		pista.getJugador(0).getAuto().setPosicion(200);
		try{
			assertEquals(tramos.get(1), pista.buscarTramoActual(0));
		}catch (Exception e){
			fail("No deberian haber excepciones");
		};
	}

	/*
	 * Se le da al auto una posicion mayor al largo de la pista y
	 * se espera que se lance la excepcion fin de pista
	 */
	public void testTramoActual3(){
		pista.getJugador(0).getAuto().setPosicion(400);
		try{
			pista.buscarTramoActual(0);
		}catch (Exception e){
			assertEquals(ExceptionFinPista.class, e.getClass());
		};
	}

	/*
	 * Se prueba la actualizacion de las posiciones de los autos de los
	 * jugadores. Las posiciones de los autos son tales que tienen otro
	 * tramo por delante, por lo que se espera que actualizar posiciones
	 * no lance excepciones.
	 */
	public void testActualizarPosiciones() throws ExceptionFinPista{
		try{
			pista.actualizarPosiciones();
		}catch (ExceptionFinPista e){
			fail("No deberian haber excepciones");
		}
	}
	
	public void testAcutualizarPosiciones2(){
		pista.getJugador(1).getAuto().setPosicion(800.23);
		try{
			pista.actualizarPosiciones();
		}catch (Exception e){
			assertEquals(ExceptionFinPista.class, e.getClass());
		}
	}
	
	public void testConstructores2(){
		pista2 = new Pista(pista.getJugador(0),pista.getJugador(1), 500);
		assertNotNull(pista2);
		assertNotNull(pista2.getJugador(0));
		assertEquals(2,pista2.getCantJugadores());
		assertEquals(pista2.getTramoActual(0),pista2.getTramoActual(1));
		
	}
	public static Test suite(){
		return new TestSuite(TestPista.class);
	}

	
	public static void main (String[] args){
	
		junit.textui.TestRunner.run(suite());
	}

}
