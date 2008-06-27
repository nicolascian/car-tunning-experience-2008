/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

import modelo.*;
import control.*;
import vista.*;

/**
 * Juego es el punto de entrada del programa "Car Tunning Experience 2008"
 *	 
 * @version	1.0
 * @author Nicolas Cian
 * @author Rafael Putaro
 * @author Nicolas Morandi
 * @author Lucas Uccello
 * @author Maxi Santos
 * 
 * @see <a href="http://code.google.com/p/car-tunning-experience-2008/">Car Tunning Experience 2008</a>
 */
public class Juego{
		
	public static void main(String args[]){


		//CREAMOS EL MODELO
		new modelo.Usuario(String nombre, new Auto(), new AlgoPesos());
		
		//CREAMOS UNA VISTA, y le pasamos el modelo
		new VistaVentana(); 


	}// fin main
	
}

