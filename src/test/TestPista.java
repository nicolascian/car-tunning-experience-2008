package test;
import junit.framework.*;
import modelo.*;
import modelo.exceptions.ExceptionFinPista;

import java.util.*;

public class TestPista extends TestCase{
	Auto[] auto;
	ArrayList<Tramo> tramos;
	Pista pista ;
	Pista pista2;
	
	public void setUp(){
		Auto[] auto = new Auto[2];
		auto[0] = new Auto();
		auto[1] = new Auto();
		auto[0].setPosicion(0);
		auto[1].setPosicion(0);
		tramos = new ArrayList<Tramo>();
		tramos.add(new Tramo(0,150,new Clima(),new Superficie()));
		tramos.add(new Tramo(150, 321.89, new Clima(35,75,1014),new Superficie(50,50,50)));
		pista = new Pista(auto,tramos);
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
	
	public void testAuto1(){
		assertNotNull(pista.getAuto(0));
	}
	
	public void testAuto2(){
		assertNotNull(pista.getAuto(1));
	}
	
	/*
	 * Se le da al auto una poscion dentro de la pista y se
	 * espera que se encuentre el tramo en el que se encuentra
	 */
	public void testTramoActual2(){
		pista.getAuto(0).setPosicion(200);
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
		pista.getAuto(0).setPosicion(400);
		try{
			pista.buscarTramoActual(0);
		}catch (Exception e){
			assertEquals(ExceptionFinPista.class, e.getClass());
		};
	}

	/*
	 * Se prueba la actualizacion de las posiciones de los autos de los
	 * jugadores. Las posiciones de los autos son tales que tienen otro
	 * tramo por delante pero no deben cambiar de tramo, por lo que se 
	 * espera que actualizar posiciones no lance excepciones ni que
	 * cambien de tramo
	 */
	public void testActualizarPosiciones() throws ExceptionFinPista{
		try{
			pista.actualizarPosiciones();
		}catch (ExceptionFinPista e){
			fail("No deberian haber excepciones");
		}
	}
	
	/*
	 * Se prueba la actualizacion de las posiciones de los autos de los
	 * jugadores. Las posiciones de los autos son tales que tienen otro
	 * tramo por delante, por lo que se espera que actualizar posiciones
	 * no lance excepciones.
	 */
	public void testActualizarPosiciones2(){
		pista.getAuto(0).setPosicion(200);
		pista.getAuto(1).setPosicion(250);
		try{
			pista.actualizarPosiciones();
		}catch (ExceptionFinPista e){
			fail("No deberian haber excepciones");
		}
		assertEquals(pista.getTramos().get(1), pista.getTramoActual(0));
		assertEquals(pista.getTramos().get(1), pista.getTramoActual(1));
	}
	
	/*
	 * Se prueba la actualizacion de las posiciones de los autos, y se le
	 * da a uno de los autos una posicion fuera de la pista (mayor a su largo).
	 * Lo que se espera es que lance la excepcion fin de pista.
	 */
	public void testActualizarPosiciones3(){
		pista.getAuto(1).setPosicion(800.23);
		try{
			pista.actualizarPosiciones();
		}catch (Exception e){
			assertEquals(ExceptionFinPista.class, e.getClass());
		}
	}
	
	public void testConstructores2(){
		pista2 = new Pista(pista.getAuto(0),pista.getAuto(1), 500);
		assertNotNull(pista2);
		assertNotNull(pista2.getAuto(0));
		assertEquals(2,pista2.getCantAutos());
		assertEquals(pista2.getTramoActual(0),pista2.getTramoActual(1));
		
	}
	public static Test suite(){
		return new TestSuite(TestPista.class);
	}

	
	public static void main (String[] args){
	
		junit.textui.TestRunner.run(suite());
	}

}
