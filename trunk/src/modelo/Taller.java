/**
 * 
 */
package modelo;
import vista.ventanas.VentanaTaller;
import modelo.componente.Componente;

/**
 * @author Usuario
 *
 */
public class Taller {
	
	modelo.Usuario usuario;
	
	/**
	 * 
	 */
	public Taller(modelo.Usuario usuario) {
		this.usuario=usuario;
	}
	
	public boolean reemplazar(Componente componente){
		return false;
	}
	
	public boolean reparar(Componente componente){
		return false;
	}
	
	public boolean vender(Componente componente){
	    return false;
	}
	
}
