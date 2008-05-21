/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/
package modelo.exceptions;

/**
 * @Descripcion: Inidica que no se puede acceder al método invocado.
 *
 */
public class ExceptionAccesoNoPermitido extends Exception {

	public ExceptionAccesoNoPermitido() {
		super("No se puede acceder al método solicitado");
	}
}
