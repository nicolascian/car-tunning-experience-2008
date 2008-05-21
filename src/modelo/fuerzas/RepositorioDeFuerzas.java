/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/
package modelo.fuerzas;
import java.util.LinkedList;
import java.util.Iterator;

import modelo.exceptions.ExceptionAccesoNoPermitido;
/**
 * @Descripcion:
 * 				Esta clase modela un repositorio de Fuerzas sobre un ReceptorDeFuerzas.
 * En una instancia de esta Clase se insertar instancias de la clase Fuerza, que tienen como 
 * receptor a el ReceptorDeFuerzas que coincida con el atributo propietario de esta
 * instancia. Solo se mantendra una Fuerza de acceso ilimitado que tenga como emisor a un 
 * ReceptorDeFuerzas dado.
 * En caso de insertar una Fuerza cuyo emisor ya tiene una fuerza de acceso ilimitado en esta 
 * instancia se elimina la fuerza previa y se inserta la nueva.
 * De una instancia de esta clase se puede obtener la sumatoria total de las fuerzas 
 * que llegan a ella. En caso de que una instancia de la clase Fuerza contenida en 
 * una instancia de esta clase, se halle con acceso limitado y no haya mas posibilidades
 * de acceder al valor de su fuerza se procede a su eliminacion.
*/
public class RepositorioDeFuerzas {

	private LinkedList<Fuerza> listaDeFuerzas;
	
	private ReceptorDeFuerzas propietario;//objeto propietario de la instancia.
	
	/**
	 * @Pre: La instancia de ReceptorDeFuerzas pasada por parametro es no nula.
	 * @Post: Se ha creado una instancia de esta clase inicializandola segun el
	 * parametro.
	*/
	public RepositorioDeFuerzas(ReceptorDeFuerzas propietario) {
		listaDeFuerzas=new LinkedList<Fuerza>();
		this.setPropietario(propietario);
	}

	/**
	 * @Pre: La instancia ha sido creada.
	 * @Post: Se ha obtenido la sumatoria de los valores de las instancias de la clase
	 * Fuerza que se encuentran en esta instancia.
	*/
	public double obtenerValorSumatoriaDeFuerzas(){
	    double sumatoria=0;
	    Iterator<Fuerza> iterador=listaDeFuerzas.iterator();
		Fuerza fuerza=null;
		while(iterador.hasNext()){
		  try{
			  fuerza=iterador.next();
			  sumatoria=sumatoria+fuerza.getValorDeLaFuerza();
		  }catch(ExceptionAccesoNoPermitido e){
			  //en caso de no poder accederse al valor de la fuerza se elimina dicha fuerza
			  iterador.remove();
		  }
		}
	    return sumatoria;
	}

	/**
	 * @Pre: La instancia ha sido creada.
	 * @Post: Se ha obtenido la sumatoria de los valores de las instancias de la clase 
	 * Fuerza que se encuentran en esta instancia que son del receptor pasado por parametro.
	*/
	public double obtenerValorSumatoriaDeFuerzas(ReceptorDeFuerzas emisor){
	    double sumatoria=0;
	    Iterator<Fuerza> iterador=listaDeFuerzas.iterator();
		Fuerza fuerza=null;
		while(iterador.hasNext()){
		  try{
			  fuerza=iterador.next();
			  //si es del emisor pasado por parametro se tiene en cuenta en la suma
			  if(fuerza.getEmisor()==emisor)
				sumatoria=sumatoria+fuerza.getValorDeLaFuerza();
		  }catch(ExceptionAccesoNoPermitido e){
			  //en caso de no poder accederse al valor de la fuerza se elimina dicha fuerza
			  iterador.remove();
		  }
		}
	    return sumatoria;
	}
	
	/**
	 * @Pre: La instancia ha sido creada.
	 * @Post: Se ha insertado una instancia de la clase Fuerza
	*/
	public void insertarFuerza(Fuerza fuerza){
	  //fuerza no nula
	  if(fuerza!=null)	
		  //el receptor de la fuerza es el propietario de esta instancia
		  if(fuerza.getReceptor()==getPropietario()){
			//verifico si existen fuerzas de acceso ilimitado previas del emisor en el repositorio  
			Fuerza fuerzaPreviaDeEmisor=this.obtenerFuerzaDeAccesoIlimitadoDeEmisor(fuerza.getEmisor());
			//si existen fuerzas de acceso ilimitado del emisor en el repositorio se reemplazan
			if(fuerzaPreviaDeEmisor!=null)
				listaDeFuerzas.remove(listaDeFuerzas.indexOf((Object) fuerzaPreviaDeEmisor));
			this.getListaDeFuerzas().add(fuerza);
		  }
	}

	/**
	 * @Pre: La instancia ha sido creada.
	 * @Post: Se ha insertado una instancia de la clase Fuerza
	*/
	public Fuerza insertarFuerzaRetornarCopia(Fuerza fuerza){
	  Fuerza copia=null;
	  //fuerza no nula
	  if(fuerza!=null){	
	      double valorDeLaFuerza=0;
	      try{	
	    	valorDeLaFuerza=fuerza.getValorDeLaFuerza();
	      }catch(Exception e){};
	      copia=new Fuerza(fuerza.getEmisor(),fuerza.getReceptor(),valorDeLaFuerza,
	    		           fuerza.isAccesoLimitado());
	      if(!fuerza.isAccesoLimitado())
	         this.insertarFuerza(fuerza);
	      else
	    	 insertarFuerza(new Fuerza(fuerza.getEmisor(),fuerza.getReceptor(),valorDeLaFuerza,true));
	    }
	  return copia;
	}
	
	/**
	 * @Pre: La instancia ha sido creada y instancia de la clase ReceptorDeFuerzas pasada por 
	 * parametro es no nula.
	 * @Post: Se ha obtenido la sumatoria de los valores de las instancias de la clase Fuerza que se 
	 * encuentran en esta instancia.
	*/
	public Fuerza obtenerFuerzaDeAccesoIlimitadoDeEmisor(ReceptorDeFuerzas emisor){
	    Fuerza fuerza=null;
	 	Fuerza fuerzaCursor=null;
		boolean encontrado=false;
		Iterator<Fuerza> iterador=listaDeFuerzas.iterator();
		while((iterador.hasNext())&&(!encontrado)){
		  fuerzaCursor=iterador.next();
		  //si la fuerza es de acceso ilimitado
	  	  if(!(fuerzaCursor.isAccesoLimitado())){     
		  	  //si la fuerza es del emisor
		  	  if(fuerzaCursor.getEmisor()==emisor){
			        fuerza=fuerzaCursor;
				    encontrado=true;
		  	  }
		  }
		}
	    return fuerza;
	}
	
	/**
	 * @Pre: La instancia ha sido creada y instancia de la clase ReceptorDeFuerzas pasada por 
	 * parametro es no nula.
	 * @Post: Se han liberado todas las fuerzas contenidas.
	*/
	public void vaciar(){
		this.getListaDeFuerzas().clear();
	}
	
	/**
	 * @return the listaDeFuerzas
	 */
	public LinkedList<Fuerza> getListaDeFuerzas() {
		return listaDeFuerzas;
	}

	/**
	 * @param listaDeFuerzas the listaDeFuerzas to set
	 */
	public void setListaDeFuerzas(LinkedList<Fuerza> listaDeFuerzas) {
		this.listaDeFuerzas = listaDeFuerzas;
	}

	/**
	 * @return the propietario
	 */
	public ReceptorDeFuerzas getPropietario() {
		return propietario;
	}

	/**
	 * @param propietario the propietario to set
	 */
	public void setPropietario(ReceptorDeFuerzas propietario) {
		this.propietario = propietario;
	}	
}
