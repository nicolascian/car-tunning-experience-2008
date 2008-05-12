package modelo;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/
/**
 * @Documentacion: Clase de una excepcion creada por el programador
 * la cual hereda de ecxeption, y se produce cuando se intenta realizar
 * una competencia y el auto no posee uno o mas componentes.
 */
public class ExceptionComponenteFaltante extends Exception{
	public ExceptionComponenteFaltante() {
		super ("Elemento faltante");
	}
/* Mensaje que aclara que componente es el que falta en el auto*/
	public ExceptionComponenteFaltante(String s) {
		super ("Elemento faltante: " + s);
	}

}
