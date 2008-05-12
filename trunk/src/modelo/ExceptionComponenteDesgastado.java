package modelo;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * 	@Documentacion: Clase de una excepcion creada por el programador
 * la cual hereda de ecxeption, y se produce cuando se intenta realizar
 * una competencia y el auto posee uno o mas componentes con estado 0,
 * o lo que es igual, totalmente desgastados y fuera de alguna utilizacion.
 */

public class ExceptionComponenteDesgastado extends Exception {
	public ExceptionComponenteDesgastado() {
		super ("Elemento Desgastado");
	}
/* mensaje que aclara que componente es el desgastado*/
	public ExceptionComponenteDesgastado(String s) {
		super ("Elemento desgastado: " + s);
	}
}
