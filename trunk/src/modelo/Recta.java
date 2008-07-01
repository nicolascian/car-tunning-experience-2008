/**
 * 
 */
package modelo;

/**
 * @author Usuario
 *
 */
public class Recta {

	private double pendiente=0;
	
	private double terminoIndependiente=0;
	
	/**
	 * 
	 */
	public Recta(double xCero,double yCero,double xUno,double yUno) {
		pendiente=(yCero-yUno)/(xCero-xUno);
		terminoIndependiente=yCero-pendiente*xCero;
	}
	
	public double calcularRecta(double x){
		return (pendiente*x+terminoIndependiente);
	}
		
}
