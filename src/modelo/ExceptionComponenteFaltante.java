package modelo;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

public class ExceptionComponenteFaltante extends Exception{
	public ExceptionComponenteFaltante() {
		super ("Elemento faltante");
	}

	public ExceptionComponenteFaltante(String s) {
		super ("Elemento faltante: "+s);
	}

}
