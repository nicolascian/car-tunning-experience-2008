/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package vista;
import modelo.*;

import java.util.Observable;
import java.util.Observer;

public class VistaConsola implements Observer
{	
	private Pista pista; //referencia al modelo

	//Constructor de la vista
	public VistaConsola(Pista pista)
	{	
		// Conectamos esta vista con el modelo
		this.pista = pista;
		this.pista.addObserver(this); 
	}

	//Metodo que es llamado por el modelo al actualizarse el mismo
	public void update(Observable t, Object o)
	{	
		System.out.println( pista );
	}
		
}
