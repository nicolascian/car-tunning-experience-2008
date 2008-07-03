/**
 * 
 */
package modelo;
import vista.ventanas.VentanaTaller;
import modelo.componente.Componente;
import vista.ventanas.VentanaReparacion;
import javax.swing.JOptionPane;
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
	
	public void reemplazo(Componente componenteActual,Componente componenteNuevo){
		if( JOptionPane.showConfirmDialog(null,"Desea reemplazar el componente?",
			"Reemplazo De Componente",
			JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){
			this.reemplazar(componenteActual,componenteNuevo);
		};
	}
	public boolean reemplazar(Componente componenteActual,Componente componenteNuevo){
		return false;
	}
	
	public boolean vender(Componente componente){
	    return false;
	}
	
	public void repararacion(Componente componente){
		VentanaReparacion ventana=new VentanaReparacion(componente, this.ventanaTaller);
	    ventanaTaller.setVisible(false);
		ventana.setVisible(true);
	}
	
	public boolean reparar(Componente componente,double porcentaje){
		AlgoPesos importe=Componente.calcularCostoReparacion(componente, porcentaje);
		AlgoPesos cobrado=usuario.cobrarDineroAJugador(importe);
		if(importe.compareTo(cobrado)<=0){
			componente.reparar(porcentaje);
			return true;
		}
		else
			return false;
	}
		
}
