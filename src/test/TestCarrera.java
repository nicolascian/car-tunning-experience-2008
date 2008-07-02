package test;

import junit.framework.TestCase;
import modelo.*;
import control.*;
import javax.swing.*;
import vista.ventanas.*;

public class TestCarrera extends TestCase {

	Carrera carrera;
	
	modelo.Usuario usuario;
	
	modelo.Virtual virtual;
	
	JFrame menu;
	
	Pista pista;
	
	public void setUp(){
		
		
		
		usuario = new modelo.Usuario("default", new AlgoPesos(1000,0), new Auto());
		
		Auto autoVirtual = new Auto();
		
		virtual = new modelo.Virtual(new control.Virtual(new control.Intermedio(autoVirtual), autoVirtual), autoVirtual);
		
		pista = new Pista(usuario.getAuto(), virtual.getAuto(), 100);
		
		carrera = new Carrera(usuario, virtual, pista, new AlgoPesos(100 , 0) , menu);
	}
	
	public void testHayOponente(){
		//assertNotNull(carrera.getVirtual().getControl());
	}
}
