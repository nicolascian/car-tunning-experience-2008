package test;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import junit.framework.TestCase;

import modelo.AfectablePorClima;
import modelo.AlgoPesos;
import modelo.Auto;
import modelo.Clima;
import modelo.Constantes;
import modelo.GestorPersistencia;
import modelo.componente.Alimentacion;
import modelo.componente.Carburador;

public class TestPersistencia extends TestCase{

	Auto auto = new Auto();
	Constantes cte;
	
	public void testPersistencia() {
		
		//me hago un gestor
		GestorPersistencia gestor = new GestorPersistencia();
		//creo un usuario
		modelo.Usuario USRguardado = new modelo.Usuario("Test de Persistencia", new AlgoPesos(89,77), new Auto());

		//ahora guardo el usuario
		try{	
			gestor.Guardar(USRguardado, "guardadoTest.xml");
		}
		catch(Exception e1){
			System.out.println( e1.getMessage() );
		}
		
		//levanto el usuario
		try{
			modelo.Usuario USRlevantado = gestor.Cargar("guardadoTest.xml"); 	

			
			//vemos si son parecidos
			//assertEquals( USRguardado, USRlevantado );
			
			assertEquals( USRguardado , USRlevantado );
			assertEquals( USRguardado.getClass() , USRlevantado.getClass() );
			
			assertEquals( USRguardado.getNombre() , USRlevantado.getNombre() );
			assertEquals( USRguardado.getDinero() , USRlevantado.getDinero() );
			
			assertEquals( USRguardado.getAuto() , USRlevantado.getAuto() );
			
			assertEquals( USRguardado.getAuto().getCaja() , USRlevantado.getAuto().getCaja() );
			assertEquals( USRguardado.getAuto().getCaja().getCantidadCambios() , USRlevantado.getAuto().getCaja().getCantidadCambios() );
			
			assertEquals( USRguardado.getAuto().getEjeDelantero() , USRlevantado.getAuto().getEjeDelantero() );
			assertEquals( USRguardado.getAuto().getEjeTrasero() , USRlevantado.getAuto().getEjeTrasero() );
			
			assertEquals( USRguardado.getAuto().getNeumaticoTraseroDerecho() , USRlevantado.getAuto().getNeumaticoTraseroDerecho() );
			
			
			
		}
		catch(Exception e2){
			System.out.println( e2.getMessage() );
		}
		
				
	}

	
}
