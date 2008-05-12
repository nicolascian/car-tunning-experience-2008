package modelo;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/
/**
 * Excepcion que avisa cuando ah finalizado la pista.
 */
public class ExceptionFinPista extends Exception{
	public ExceptionFinPista() {
		super ("Fin de la Pista");
	}
}
