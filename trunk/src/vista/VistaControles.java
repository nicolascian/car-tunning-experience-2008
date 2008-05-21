/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package vista;

import modelo.*;
import control.*;

import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.util.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class VistaControles implements Observer{

	private Pista pista; //referencia al modelo (pista)
	
	private Frame frame; //marco que contendr� los controles
	private Label l1;
	
//	Constructor de la vista
	public VistaControles(Pista pista,Jugador jugador){
		
//		armado de la ventana
		frame = new Frame("Key Listener!"); //creamos el marco
		
//		agregamos un titulo
		frame.add("North", new Label("Key Listener! usar: E A S D F I K O L P Ñ                         " ));  
		
		Panel panel = new Panel(); //creamos un panel para el Label
		
		l1= new Label("Key Listener! usar: E A S D F I K O L P Ñ                         " );
		panel.add(l1);  
		
		frame.add("South", panel);  //agregamos el panel al marco
		
		frame.setSize(500,100);  //seteamos las dimensiones del marco
		frame.setVisible(true);  //mostramos el marco

		//agregamos el listener del evento de cerrado de la ventana		
		frame.addWindowListener(new CloseListener());
		
		//escuha el Teclado
		frame.addKeyListener(jugador);		

		// Conectamos esta vista con el modelo
		this.pista = pista;
		this.pista.addObserver(this); 
		
	}
	
	
	public void update(Observable arg0, Object arg1) {
		
		
		l1.setText("update");
		
	}
	
//	Clase auxiliar para escuchar el evento de cerrado de la ventana
	public static class CloseListener extends WindowAdapter
	{	public void windowClosing(WindowEvent e)
		{	e.getWindow().setVisible(false);
			System.exit(0);
		}
	}

}
