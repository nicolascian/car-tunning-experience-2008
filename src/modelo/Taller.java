/**
 * 
 */
package modelo;
import vista.ventanas.VentanaTaller;
import modelo.componente.Componente;
import vista.ventanas.VentanaReparacion;
import javax.swing.JOptionPane;
import java.lang.reflect.*;
import java.util.LinkedList;
import java.util.Iterator;
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
			    importe.toStringConUnidades()+" menos un descuento segun el estado de su componente viejo ",
			    "Reemplazo De Componente",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){
			if(this.reemplazar(componenteActual,componenteNuevo));
			  this.ventanaTaller.getPanelComponente().actualizarComponente(componenteNuevo);	
		};
		}catch(NullPointerException e){
			JOptionPane.showMessageDialog(null,"No se ha podido reemplazar el componente."+'\n'+
					"Intentelo en otro momento. Muchas gracias","Reemplazo de componente."
					,JOptionPane.ERROR_MESSAGE);	  
			
		}
		this.ventanaTaller.refrescarInfo();
	}
	
	public boolean reemplazar(Componente componenteActual,Componente componenteNuevo){
		try{
		  AlgoPesos importe=componenteNuevo.getPrecio();
		  double descuento=componenteActual.getEstado()*0.60;
		  AlgoPesos importeConDescuento=AlgoPesos.restar(importe,AlgoPesos.multiplicar(componenteActual.getPrecio(),
				                        AlgoPesos.toAlgoPesos(descuento)));
		  if(importeConDescuento.compareTo(AlgoPesos.multiplicar(componenteNuevo.getPrecio(),AlgoPesos.toAlgoPesos(0.5)))<0)
			  importeConDescuento=AlgoPesos.multiplicar(importe,AlgoPesos.toAlgoPesos(0.5));
		  AlgoPesos cobrado=usuario.cobrarDineroAJugador(importeConDescuento);
		  if(importeConDescuento.compareTo(cobrado)<=0){
			Method[] metodos=Auto.class.getMethods();
			LinkedList<Method> listaMetodosSet=new LinkedList<Method>();
			LinkedList<Method> listaMetodosGet=new LinkedList<Method>();
			int cursor=-1;
			while(cursor<metodos.length-1){
			   cursor++;
			   if((metodos[cursor].getName().startsWith("set"))&&
				  (metodos[cursor].getName().substring(3).toLowerCase().
				  startsWith(componenteActual.getClass().getSimpleName().toLowerCase())||
				  (metodos[cursor].getName().substring(3).toLowerCase().
				  startsWith(componenteActual.getClass().getSuperclass().getSimpleName().toLowerCase()))	   
			     
			     )){
				 listaMetodosSet.add(metodos[cursor]); 	  
			   }
			   else
				  if((metodos[cursor].getName().startsWith("get"))&&
					  (metodos[cursor].getName().substring(3).toLowerCase().
					  startsWith(componenteActual.getClass().getSimpleName().toLowerCase())||
					 (metodos[cursor].getName().substring(3).toLowerCase().
					  startsWith(componenteActual.getClass().getSuperclass().getSimpleName().toLowerCase()))	   
							     
					)){
					listaMetodosGet.add(metodos[cursor]); 	  
				  }
			}
			Iterator<Method> itGet=listaMetodosGet.iterator();
			while(itGet.hasNext()){
				Method metodo=itGet.next();
				Object[] object=new Object[1];
				object[0]=null;
				try{   
				   if(metodo.invoke(this.usuario.getAuto(),object)!=componenteActual)
					  itGet.remove();
				}catch(Exception e){};
			}
			Iterator<Method> itSet=listaMetodosSet.iterator();
			while((itSet.hasNext())&&(!listaMetodosGet.isEmpty())){
				int indice=listaMetodosSet.getFirst().getName().indexOf("Set");
				Method metodoFinal=itSet.next();
				if(listaMetodosGet.getFirst().getName().substring(indice+3).
				    equals(metodoFinal.getName().substring(indice+3)))
					try{
						Object[] object=new Object[1];
						object[0]=componenteNuevo;
						metodoFinal.invoke(usuario.getAuto(),object);
					}catch(Exception e){}
					componenteActual.deleteObservers();
			}
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
	   	ventana.setVisible(true);
	   	this.ventanaTaller.refrescarInfo();
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
