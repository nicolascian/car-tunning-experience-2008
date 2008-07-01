package test;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modelo.*;

public class TestPersistenciaVisual {

	
	public TestPersistenciaVisual(){
		
		
	}
	
	public static void main(String[] args) {
		
		//me hago un gestor
		GestorPersistencia gestor = new GestorPersistencia();
		//creo un usuario
		modelo.Usuario usuario = new modelo.Usuario("Test", new AlgoPesos(45,99), new Auto());
		//imprimo el usuario
		System.out.print(usuario.toString());
		//ahora guardo el usuario
		try{	
			gestor.Guardar(usuario, "guardadoTest.xml");
		}
		catch(Exception e1){
			JOptionPane.showMessageDialog(new JFrame(),
					"No se pudo guardar el Juego actual.",
        	    	"Error al guardar",
        	    	JOptionPane.ERROR_MESSAGE);
		}
		//levanto el usuario
		try{
			modelo.Usuario usuarioLevantado = gestor.Cargar("guardadoTest.xml");  
			//imprimo de nuevo el usuario
			System.out.print(usuarioLevantado.toString());
			//Tienen el mismo contenido pero ¿es la misma colección?
			if(usuarioLevantado==usuario)
				System.out.println("Es el mismo usuario");
			else 
				System.out.println("Son usuarios distintos");	
			
		}
		catch(Exception e1){
			JOptionPane.showMessageDialog(new JFrame(),
	        	    "No se pudo cargar desde archivo.",
	        	    "Error al cargar",
	        	    JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}
