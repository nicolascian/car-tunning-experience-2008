package test;
import junit.framework.*;
import modelo.*;
import java.util.*;

public class TestPista extends TestCase{
	Jugador[] jugador;
	ArrayList<Tramo> tramos;
	Pista pista ;
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
	
	public void testToString(){
		assertEquals("Pista de 321.89 metros, compuesta por 2 tramos.", pista.toString());
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
	
	public void testJugador2(){
		assertNotNull(pista.getJugador(1));
	}
		
	public void testTramoActual2()throws Exception{
		pista.getJugador(0).getAuto().setPosicion(200);
		try{
			assertEquals(tramos.get(1), pista.buscarTramoActual(0));
		}catch (Exception e){
			throw e;
		};
	}

	public void testTramoActual3()throws Exception{
		/*Se espera que se lance una excepcion ya que el auto se
		 *encuentra en una poscion mayor al largo de la pista */
		pista.getJugador(0).getAuto().setPosicion(400);
		try{
			pista.buscarTramoActual(0);
		}catch (Exception e){
			assertEquals(ExceptionFinPista.class, e.getClass());
		};
	}

	public void testActualizarPosiciones() throws ExceptionFinPista{
		try{
			pista.actualizarPosiciones();
		}catch (ExceptionFinPista e){
			throw e;
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
	
	public static Test suite(){
		return new TestSuite(TestPista.class);
	}

	
	public static void main (String[] args){
	
		junit.textui.TestRunner.run(suite());
	}

}
