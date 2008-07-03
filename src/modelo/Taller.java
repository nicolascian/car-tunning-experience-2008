/**
 * 
 */
package modelo;
import vista.ventanas.VentanaTaller;
import modelo.componente.Componente;
import vista.ventanas.VentanaReparacion;
/**
 * @author Usuario
 *
 */
public class Taller {
	
	private modelo.Usuario usuario=null;
	private VentanaTaller ventanaTaller=null;
	/**
	 * 
	 */
	public Taller(modelo.Usuario usuario,VentanaTaller ventanaTaller) {
		this.usuario=usuario;
		this.ventanaTaller=ventanaTaller;
	}
	
	public boolean reemplazar(Componente componente){
		return false;
	}
	
	public void reparar(Componente componente){
		componente.setEstado(50);
		VentanaReparacion ventana=new VentanaReparacion(componente,
				                                        this.ventanaTaller);
	    ventanaTaller.setVisible(false);
		ventana.setVisible(true);
	}
	
	public boolean vender(Componente componente){
	    return false;
	}
	
}
