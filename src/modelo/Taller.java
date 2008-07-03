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
		AlgoPesos importe=componenteNuevo.getPrecio();
		try{
		if( JOptionPane.showConfirmDialog(null,"Desea reemplazar el componente?"+'\n'+
			    importe.toStringConUnidades()+" menos el costo de su componente "+
			    componenteActual.getPrecio().toStringConUnidades(),"Reemplazo De Componente",
			JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){
			this.reemplazar(componenteActual,componenteNuevo);
				
		};
		}catch(NullPointerException e){
			JOptionPane.showMessageDialog(null,"No se ha podido reemplazar el componente."+'\n'+
					"Intentelo en otro momento. Muchas gracias","Reemplazo de componente."
					,JOptionPane.ERROR_MESSAGE);	  
			
		}
	}
	
	public boolean reemplazar(Componente componenteActual,Componente componenteNuevo){
		try{
		  AlgoPesos importe=componenteNuevo.getPrecio();
		  AlgoPesos cobrado=usuario.cobrarDineroAJugador(importe);
		  if(importe.compareTo(cobrado)<=0){
			//----reemplazo de componente
			return true;
		  }
		  else{
		    JOptionPane.showMessageDialog(null,"No se ha podido reemplazar el componente."+'\n'+
		    		"cuesta "+importe.toStringConUnidades()+" usted pago "+cobrado.toStringConUnidades(),
	                    "Reemplazo de componente",JOptionPane.ERROR_MESSAGE);	  
			usuario.entregarDineroAJugador(cobrado);
	
			return false;
		  }
		 }catch(NullPointerException e){
			e.printStackTrace();
			return false;
		 }
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
		else{
			usuario.entregarDineroAJugador(cobrado);
			return false;
		}
	}
		
}
