/**
 * 
 */
package vista.imagenAuto.imagenesDeComponentes;
import java.util.*;
import modelo.componente.*;
import modelo.componente.neumaticos.*;

/**
 * @author Usuario
 *
 */
public class AdministradorDeImagenesYEtiquetasDeComponentes {

	public final static String DIRECTORIO="src//vista//imagenAuto//imagenesDeComponentes//";
	
	LinkedList<DatoClase> listaClases=null;
	
	/**
	 * 
	 */
	public AdministradorDeImagenesYEtiquetasDeComponentes() {
		listaClases=new LinkedList<DatoClase>();
		this.generarLista();
	}
	
	private void generarLista(){
		
		listaClases.add(new DatoClase(Alimentacion.class, "Alimentacion",
				DIRECTORIO+"alimentacion.jpg"));
		listaClases.add(new DatoClase(Automatica.class, "Caja Automatica",
				DIRECTORIO+"automatica.jpg"));
		listaClases.add(new DatoClase(Caja.class, "Caja",
				DIRECTORIO+"caja.jpg"));
		listaClases.add(new DatoClase(Carburador.class, "Carburador",
				DIRECTORIO+"carburador.jpg"));
		listaClases.add(new DatoClase(Carroceria.class, "Carroceria",
				DIRECTORIO+"carroceria.jpg"));
		listaClases.add(new DatoClase(Combustible.class, "Combustible",
				DIRECTORIO+"combustible.jpg"));
		listaClases.add(new DatoClase(Componente.class, "Componente",
				DIRECTORIO+"componente.png"));
		listaClases.add(new DatoClase(Eje.class, "Eje",
				DIRECTORIO+"eje.jpg"));
		listaClases.add(new DatoClase(Embrague.class, "Embrague",
				DIRECTORIO+"embrague.jpg"));
		listaClases.add(new DatoClase(Escape.class, "Escape",
				DIRECTORIO+"escape.jpg"));
		listaClases.add(new DatoClase(Freno.class, "Freno",
				DIRECTORIO+"freno.png"));
		listaClases.add(new DatoClase(FrenoABS.class, "Freno ABS",
				DIRECTORIO+"frenoAbs.jpg"));
		listaClases.add(new DatoClase(FrenoCinta.class, "Freno a Cinta",
				DIRECTORIO+"frenoCampana.jpg"));
		listaClases.add(new DatoClase(FrenoDisco.class, "Freno a Disco",
				DIRECTORIO+"frenoDisco.gif"));
		listaClases.add(new DatoClase(Inyeccion.class, "Inyeccion",
				DIRECTORIO+"inyeccion.jpg"));
		listaClases.add(new DatoClase(Llanta.class, "Llanta",
				DIRECTORIO+"llanta.jpg"));
		listaClases.add(new DatoClase(Manual.class, "Caja Manual",
				DIRECTORIO+"manualCaja.png"));
		listaClases.add(new DatoClase(Motor.class, "Motor",
				DIRECTORIO+"motor.jpg"));
		listaClases.add(new DatoClase(Neumatico.class, "Neumatico",
				DIRECTORIO+"neumatico.gif"));
		listaClases.add(new DatoClase(NeumaticoInvierno.class, "Neumatico Invierno",
				DIRECTORIO+"neumaticoInvierno.gif"));
		listaClases.add(new DatoClase(NeumaticoLluvia.class, "Neumatico Lluvia",
				DIRECTORIO+"neumaticoLluvia.jpg"));
		listaClases.add(new DatoClase(NeumaticoMixto.class, "Neumatico Mixto",
				DIRECTORIO+"neumaticoMixto.jpg"));
		listaClases.add(new DatoClase(NeumaticoSlick.class, "Neumatico Slick",
				DIRECTORIO+"neumaticoSlick.gif"));
		listaClases.add(new DatoClase(NeumaticoTodoTerreno.class, "Neumatico Todo Terreno",
				DIRECTORIO+"neumaticoTodoTerreno.jpg"));
		listaClases.add(new DatoClase(Nitro.class, "Nitro",
				DIRECTORIO+"nitro.gif"));
		listaClases.add(new DatoClase(SistemaDeRefrigeracion.class, 
				"Sistema De Refrigeracion",DIRECTORIO+"refrigeracion.jpg"));
		listaClases.add(new DatoClase(Secuencial.class, "Caja Secuencial",
				DIRECTORIO+"secuencial.jpg"));
		listaClases.add(new DatoClase(Suspension.class, "Suspension",
				DIRECTORIO+"suspension.jpg"));
		listaClases.add(new DatoClase(Turbo.class, "Turbo",
				DIRECTORIO+"turbo.gif"));
	}
	
	public DatoClase getDatoClase(Class clase){
		Class claseAux=clase;
		DatoClase retorno=null;
		boolean encontrado=false;
		while(!encontrado){
			DatoClase datoBuscado=new DatoClase(claseAux,"","");
			int indice=listaClases.indexOf(datoBuscado);
			if(indice>=0){
			  retorno=listaClases.get(indice);
			  encontrado=true;
			}
			else
			  claseAux=claseAux.getSuperclass();				  
		}
		return retorno;
	}

}
