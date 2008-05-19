/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/
package modelo;

/**
 * @Descripcion: Una instancia de una clase derivada de ReceptorDeFuerzas puede
 * recibir el valor de una fuerza mediante la primitiva recibirFuerza y de 
 * acuerdo a ella modificar su estado.
 * Una instancia de esta clase puede recibir varias fuerzas a lo largo de su vida
 * algunas de las cual podra ir acumulandolas de alguna manera y en un momento dado mediante 
 * la primitiva liberar fuerzas desacerse de ellas y eliminarlas.
 * Las instancias de clase Fuerza que sean de acceso ilimitado deben conservarse hasta
 * la proxima liberacion de fuerzas o hasta que llegue una fuerza del mismo tipo de acceso
 * del mismo emisor.
 * En cambio las de acceso limitado deben irse elimando periodicamente a medida que se las
 * procese, puede haber varias de este tipo del mismo emisor.
 */
public interface ReceptorDeFuerzas {
	
	/**
	 * @Pre: Se ha creado una instancia de la clase derivada de ReceptorDeFuerzas.
	 * @Post: Se ha recibido una fuerza y de acuerdo a ella se modifica el estado
	 * de la instancia.
	*/
	public void recibirFuerza(Fuerza fuerza);

	/**
	 * @Pre: Se ha creado una instancia de la clase derivada de ReceptorDeFuerzas.
	 * @Post: Se han liberado todas las fuerzas recibidas desde la creacion de la isntancias
	 * o desde la ultima liberacion de fuerzas.
	*/
	public void liberarFuerzas();
}
