package modelo;
/**
 * @Documentacion
 * Indica si se ha intentado acceder a un cambio no válido en una instancia de de una clase
 * derivada la clase Caja.
*/
public class ExceptionCambioNoValido extends Exception {
	
	/**
	 * @Pre:
	 * @Post:Se ha creado una instancia de la clase Caja. 
	*/
	public ExceptionCambioNoValido() {
		super("Exception Cambio no valido");
	}

	@Override
	public String toString() {
		return ("Error Cambio No Valido");
	}
	
}
