package test;
import junit.framework.*;
import modelo.componente.neumaticos.*;
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
		sup2 = new Superficie (80,100,80);
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
		while (i<10000){
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
		
	}
	
	public void testObtenerPotencia(){
		this.testDesgastar();
	
		assertTrue(NI.obtenerPotencia()>0);
		assertTrue(NI.obtenerPotencia()<=3);
		
		assertTrue(NL.obtenerPotencia()>0);
		assertTrue(NL.obtenerPotencia()<=2);
		
		assertTrue(NM.obtenerPotencia()>0);
		assertTrue(NM.obtenerPotencia()<=1);
		
		assertTrue(NS.obtenerPotencia()>0);
		assertTrue(NS.obtenerPotencia()<=5);
		
		assertTrue(NTT.obtenerPotencia()>0);
		assertTrue(NTT.obtenerPotencia()<=3);
	}

	public void testAdherencia(){
		this.testDesgastar();
		assertTrue(NI.calcularAdherencia()>0);
		assertTrue(NL.calcularAdherencia()>0);
		assertTrue(NM.calcularAdherencia()>0);
		assertTrue(NS.calcularAdherencia()>0);
		assertTrue(NTT.calcularAdherencia()>0);
		
		System.out.println("test en Superficie sin relieve seca");
		System.out.println("N invierno: "+NI.calcularAdherencia());
		System.out.println("N lluvia: "+NL.calcularAdherencia());
		System.out.println("N mixto: "+NM.calcularAdherencia());
		System.out.println("N slick: "+NS.calcularAdherencia());
		System.out.println("N todo terreno: "+NTT.calcularAdherencia());
	}

	public void testAdherenciaSupMojada(){
		NM.afectar(clima2);
		NS.afectar(clima2);
		NTT.afectar(clima2);
		
		this.testDesgastar();
		assertTrue(NI.calcularAdherencia()>0);
		assertTrue(NL.calcularAdherencia()>0);
		assertTrue(NM.calcularAdherencia()>0);
		assertTrue(NS.calcularAdherencia()>0);
		assertTrue(NTT.calcularAdherencia()>0);
		
		System.out.println("test en Superficie sin relieve mojada");
		System.out.println("N invierno: "+NI.calcularAdherencia());
		System.out.println("N lluvia: "+NL.calcularAdherencia());
		System.out.println("N mixto: "+NM.calcularAdherencia());
		System.out.println("N slick: "+NS.calcularAdherencia());
		System.out.println("N todo terreno: "+NTT.calcularAdherencia());
	}
	
	public void testAdherenciaSupRelieveMojada(){
		NI.afectar(sup2);
		NL.afectar(sup2);
		NM.afectar(clima2);
		NM.afectar(sup2);
		NS.afectar(clima2);
		NS.afectar(sup2);
		NTT.afectar(clima2);
		NTT.afectar(sup2);
		
		this.testDesgastar();
		assertTrue(NI.calcularAdherencia()>0);
		assertTrue(NL.calcularAdherencia()>0);
		assertTrue(NM.calcularAdherencia()>0);
		assertTrue(NS.calcularAdherencia()>0);
		assertTrue(NTT.calcularAdherencia()>0);
		
		System.out.println("test en Superficie con relieve mojada");
		System.out.println("N invierno: "+NI.calcularAdherencia());
		System.out.println("N lluvia: "+NL.calcularAdherencia());
		System.out.println("N mixto: "+NM.calcularAdherencia());
		System.out.println("N slick: "+NS.calcularAdherencia());
		System.out.println("N todo terreno: "+NTT.calcularAdherencia());
	}
	
	public static Test suite(){
		return new TestSuite(TestNeumatico.class);
	}

	public static void main (String[] args)
	{
		junit.textui.TestRunner.run(suite());
	}
}
