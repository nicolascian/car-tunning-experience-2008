package modelo;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/


public class ExceptionAutoApagado extends Exception {
	
	public ExceptionAutoApagado() {
		super ("Exception Auto apagado");
	}

	@Override
	public String toString() {
		return ("Error Auto Apagado, carrera finalizada");
	}
}
