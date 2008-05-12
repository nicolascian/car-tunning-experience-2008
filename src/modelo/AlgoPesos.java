package modelo;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 * 
 * @version	1.0
 */
public class AlgoPesos{
	/* comentario acerca de la implementacion de la clase */
	

	private int entero;
	private int decimal;
	
	
	
	public AlgoPesos(int entero, int decimal){
		setEntero(entero);
		setDecimal(decimal);
	}
	
	public AlgoPesos sumar( int entero1, int entero2, int decimal1, int decimal2){
	
		int enteros= entero1+entero2;
		int decimales= decimal1+decimal2;
		if (decimales>99){ 
		enteros= enteros +1;
		decimales= decimales -100;
		}
		AlgoPesos algoPesos= new AlgoPesos ( enteros, decimales);
		return algoPesos;
	}
	
	/**
	 * @param entero the entero to set
	 */
	public void setEntero(int entero) {
		this.entero = entero;
	}

	/**
	 * @return the entero
	 */
	public int getEntero() {
		return entero;
	}

	/**
	 * @param decimal the decimal to set
	 */
	public void setDecimal(int decimal) {
		this.decimal = decimal;
	}

	/**
	 * @return the decimal
	 */
	public int getDecimal() {
		return decimal;
	}
	
}