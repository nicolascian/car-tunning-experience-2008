package test;
import junit.framework.*;
import modelo.*;

public class TestNeumatico extends TestCase{
	
	NeumaticoInvierno NI;
	NeumaticoLluvia NL;
	NeumaticoMixto NM;
	NeumaticoSlick NS;
	NeumaticoTodoTerreno NTT;
	Clima clima1;
	Clima clima2;
	Superficie sup1;
	Superficie sup2;
	
	public void setUp(){
		clima1 = new Clima(35,5,900);
		clima2 = new Clima(0,95,1020);
		sup1 = new Superficie(5,5,95);
		sup2 = new Superficie (80,70,80);
		NI = new NeumaticoInvierno();
		NL = new NeumaticoLluvia();
		NM = new NeumaticoMixto();
		NS = new NeumaticoSlick();
		NTT = new NeumaticoTodoTerreno();
		
		NI.afectar(sup1);
		NL.afectar(sup1);
		NM.afectar(clima1);
		NM.afectar(sup1);
		NS.afectar(clima1);
		NS.afectar(sup1);
		NTT.afectar(clima1);
		NTT.afectar(sup1);
	}
	
	public void testDesgastar(){
		int i=0;
		while (i<10000000){
			NI.desgastar();
			NL.desgastar();
			NM.desgastar();
			NS.desgastar();
			NTT.desgastar();
			i++;
		}
		assertTrue(NI.getEstado()>0);
		assertTrue(NL.getEstado()>0);
		assertTrue(NM.getEstado()>0);
		assertTrue(NS.getEstado()>0);
		assertTrue(NTT.getEstado()>0);
		
		System.out.println(NI.getEstado());
		System.out.println(NL.getEstado());
		System.out.println(NM.getEstado());
		System.out.println(NS.getEstado());
		System.out.println(NTT.getEstado());
		
	}
	
	public void testObtenerPotencia(){
		this.testDesgastar();
		System.out.println(NI.obtenerPotencia());
		assertTrue(NI.obtenerPotencia()>0);
		assertTrue(NI.obtenerPotencia()<=3);
		System.out.println(NL.obtenerPotencia());
		assertTrue(NL.obtenerPotencia()>0);
		assertTrue(NL.obtenerPotencia()<=2);
		System.out.println(NM.obtenerPotencia());
		assertTrue(NM.obtenerPotencia()>0);
		assertTrue(NM.obtenerPotencia()<=1);
		System.out.println(NS.obtenerPotencia());
		assertTrue(NS.obtenerPotencia()>0);
		assertTrue(NS.obtenerPotencia()<=5);
		System.out.println(NTT.obtenerPotencia());
		assertTrue(NTT.obtenerPotencia()>0);
		assertTrue(NTT.obtenerPotencia()<=3);
	}
	/*
	public void testAdherencia(){
		this.testDesgastar();
	}
	*/
	public static Test suite(){
		return new TestSuite(TestNeumatico.class);
	}

	public static void main (String[] args)
	{
		junit.textui.TestRunner.run(suite());
	}
}
