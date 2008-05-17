/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/
package modelo;

/**
 * @Descripcion:
 * 				Modela el elemento de la fisica fuerza, la cual como tal posee un 
 * emisor, un receptor y un valor. Puede haber instancias de fuerzas con valores negativos,
 * positivos o cero.
 * Se puede obtener tanto el receptor como el emisor
 * y el valor de la instancia. Ademas se pueden realizar algunas operaciones 
 * matematicas con ella. 
 * Una instancia de la clase Fuerza puede manifestar un acceso limitado a solo una vez 
 * a las operaciones operaciones matematicas o propiedades correspondientes al atributo
 * valorDeLaFuerza. Es decir por ejemplo si llamo a cualquiera de los metodos mencionados
 * una vez, la siguiente vez que quiera llamar a cualquiera de ellos se lanzara una 
 * excepcion de tipo ExceptionAccesoNoPermitido.
 */
public class Fuerza {
	
	private ReceptorDeFuerzas emisor;/* instancia que emite la fuerza y que puede recibir
									   una fuerza en de retorno.*/
	private ReceptorDeFuerzas receptor;/*instancia a la que llega la fuerza*/
	
	private double valorDeLaFuerza;//valor de la fuerza en Newton
	
	private boolean accesoLimitado=false;//indica si la instancia es de acceso limitado
	
	private boolean accedida=false;//en caso de ser una instancia de acceso limitado indica
							 	   //si fue accedida al menos una vez.	
	
	/**
	 * @Pre: Las instancias derivadas de ReceptorDeFuerzas pasadas por parametros han sido
	 * creadas.
	 * @Post: Se ha creado una instancia de la clase derivada de Fuerza segun los parametros y
	 * por defecto de acceso ilimitado. 
	 * @param emisor
	 * @param receptor
	 * @param valorDeLaFuerza mayor o igual a cero
	*/
	public Fuerza(ReceptorDeFuerzas emisor, ReceptorDeFuerzas receptor, 
			     double valorDeLaFuerza){
		this.setEmisor(emisor);
		this.setReceptor(receptor);
		this.valorDeLaFuerza=valorDeLaFuerza;
	}

	/**
	 * @Pre: Las instancias derivadas de ReceptorDeFuerzas pasadas por parametros han sido
	 * creadas.
	 * @Post: Se ha creado una instancia de la clase derivada de Fuerza segun los parametros. 
	 * @param emisor
	 * @param receptor
	 * @param valorDeLaFuerza
	 * @param accesoLimitado si se pasa por parametro true sera de acceso limitado, en caso
	 * contrario sera de acceso ilimitado.
	*/
	public Fuerza(ReceptorDeFuerzas emisor, ReceptorDeFuerzas receptor, 
			     double valorDeLaFuerza, boolean accesoLimitado) {
		this.setEmisor(emisor);
		this.setReceptor(receptor);
		this.valorDeLaFuerza=valorDeLaFuerza;
		this.accesoLimitado=accesoLimitado;
	}
	
	/**
	 * @Pre: 
	 * @Post: Se ha creado una instancia de la clase Fuerza con su receptor y emisor nulos,
	 * con valorDeLaFuerza 0 y como de acceso ilimitado.
	*/
	public Fuerza(){
		this.setEmisor(null);
		this.setReceptor(null);
		this.valorDeLaFuerza=0;
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Fuerza. 
	 * @Post: Se ha limitado el acceso a la instancia.
	*/	
	public void limitarAcceso(){
		this.accesoLimitado=true;
	}	
	
	/**
	 * @return the accesoLimitado
	 */
	public boolean isAccesoLimitado(){
		return(accesoLimitado);
	}
	
	/**
	 * @return the accedida
	 */
	public boolean isAccedida(){
		return(accedida);
	}
	
	/**
	 * @return the emisor
	 */
	public ReceptorDeFuerzas getEmisor() {
		return emisor;
	}

	/**
	 * @param emisor the emisor to set
	 */
	public void setEmisor(ReceptorDeFuerzas emisor) {
		this.emisor = emisor;
	}

	/**
	 * @return the receptor
	 */
	public ReceptorDeFuerzas getReceptor() {
		return receptor;
	}

	/**
	 * @param receptor the receptor to set
	 */
	public void setReceptor(ReceptorDeFuerzas receptor) {
		this.receptor = receptor;
	}

	/**
	 * @return the valorDeLaFuerza
	 * @throws en caso no poder acceder al valor se retorna una exception del tipo 
	 * ExceptionAccesoNoPermitido
	 */
	public double getValorDeLaFuerza() throws ExceptionAccesoNoPermitido{
		if(!isAccesoLimitado())
		  return valorDeLaFuerza;
		else{
		  if(accedida)	
		     throw new ExceptionAccesoNoPermitido();
		  else{
			 accedida=true;
			 return valorDeLaFuerza;
		  }
		}
	}

	/**
	 * @param valorDeLaFuerza the valorDeLaFuerza to set
	 * @throws en caso no poder acceder al valor se retorna una exception del tipo 
	 * ExceptionAccesoNoPermitido
	 */
	public void setValorDeLaFuerza(double valorDeLaFuerza) throws ExceptionAccesoNoPermitido{
		if(!isAccesoLimitado())
		   this.valorDeLaFuerza = valorDeLaFuerza;
		else{
		   if(accedida)
			  throw new ExceptionAccesoNoPermitido();
		   else{
			   accedida=true;
			   this.valorDeLaFuerza =valorDeLaFuerza;
		   }
		}
	}
	
	/**
	 * @Pre: La instancia pasada por parametro ha sido creada.
	 * @Post: Se ha modificado el valor de la fuerza con el valor de la suma entre el valor de la fuerza 
	 * de la instancia y el del parametro.
	 * @throws en caso no poder acceder al valor se retorna una exception del tipo 
	 * ExceptionAccesoNoPermitido
	 */
	public void sumar(Fuerza fuerza) throws ExceptionAccesoNoPermitido{
		double suma=fuerza.valorDeLaFuerza+this.getValorDeLaFuerza();
		this.setValorDeLaFuerza(suma);
	}
			
	/**
	 * @Pre: Las instancias pasadas por parametro han sido creadas.
	 * @Post: Se ha retornado la suma de ambas fuerzas.
	 * @throws en caso no poder acceder a algun valor se retorna una exception del tipo 
	 * ExceptionAccesoNoPermitido
	 */
	public static double sumar(Fuerza fuerza1,Fuerza fuerza2) throws ExceptionAccesoNoPermitido{
		double suma=fuerza1.getValorDeLaFuerza()+fuerza2.getValorDeLaFuerza();
		return suma;
	}
	
	/**
	 * @Pre: La instancia ha sido creada.
	 * @Post: Se ha modificado el valor de la fuerza con el valor de la suma entre el valor de la fuerza 
	 * de la instancia y el del parametro. 
	 * @throws en caso no poder acceder al valor se retorna una exception del tipo 
	 * ExceptionAccesoNoPermitido
	 */
	public void sumar(double valor) throws ExceptionAccesoNoPermitido{
		double suma=valor+this.getValorDeLaFuerza();
		this.setValorDeLaFuerza(suma);
	}
	
	/**
	 * @Pre: La instancia pasada por parametro ha sido creada.
	 * @Post: Se ha modificado el valor de la fuerza con el valor de la resta entre el valor de la 
	 * fuerza de la instancia y el de la fuerza pasada por parametro.
	 * @throws en caso no poder acceder al valor se retorna una exception del tipo 
	 * ExceptionAccesoNoPermitido
	 */
	public void restar(Fuerza fuerza) throws ExceptionAccesoNoPermitido{
		double producto=this.valorDeLaFuerza-fuerza.getValorDeLaFuerza();
		this.setValorDeLaFuerza(producto);
	}
			
	
	/**
	 * @Pre: Las instancias pasadas por parametro han sido creadas.
	 * @Post: Se ha retornado la resta de entre fuerza1 y fuerza2.
	 * @throws en caso no poder acceder a algun valor se retorna una exception del tipo 
	 * ExceptionAccesoNoPermitido
	 */
	public static double restar(Fuerza fuerza1,Fuerza fuerza2) throws ExceptionAccesoNoPermitido{
		double resta=fuerza1.getValorDeLaFuerza()-fuerza2.getValorDeLaFuerza();
		return resta;
	}
	
	/**
	 * @Pre: La instancia ha sido creada.
	 * @Post: Se decrementa el valor de la fuerza segun el valor por parametro. 
	 * @throws en caso no poder acceder al valor se retorna una exception del tipo 
	 * ExceptionAccesoNoPermitido
	*/
	public void restar(double valor) throws ExceptionAccesoNoPermitido{
		double resta=this.valorDeLaFuerza-valor;
			this.setValorDeLaFuerza(resta);
	}
		
	/**
	 * @Pre: La instancia pasada por parametro ha sido creada.
	 * @Post: Se ha modificado el valor de la fuerza con el valor de la multiplicacion entre el valor de la 
	 * fuerza de la instancia y el de la fuerza pasada por parametro.
	 * @throws en caso no poder acceder al valor se retorna una exception del tipo 
	 * ExceptionAccesoNoPermitido
	 */
	public void multiplicar(Fuerza fuerza) throws ExceptionAccesoNoPermitido{
		double producto=this.valorDeLaFuerza*fuerza.getValorDeLaFuerza();
		this.setValorDeLaFuerza(producto);
	}
			
	/**
	 * @Pre: Las instancias pasadas por parametro han sido creadas.
	 * @Post: Se ha retornado la multiplicacion entre fuerza1 y fuerza2.
	 * @throws en caso no poder acceder a algun valor se retorna una exception del tipo 
	 * ExceptionAccesoNoPermitido
	 */
	public static double multiplicar(Fuerza fuerza1,Fuerza fuerza2) throws ExceptionAccesoNoPermitido{
		double producto=fuerza1.getValorDeLaFuerza()*fuerza2.getValorDeLaFuerza();
		return producto;
	}
	
	/**
	 * @Pre: La instancia ha sido creada.
	 * @Post: Se ha modificado el valor de la fuerza con el valor de la mutliplicacion entre el valor de la 
	 * fuerza de la instancia y el del parametro.
	 * @throws en caso no poder acceder al valor se retorna una exception del tipo 
	 * ExceptionAccesoNoPermitido
	*/
	public void multiplicar(double valor) throws ExceptionAccesoNoPermitido{
		double producto=this.valorDeLaFuerza*valor;
		this.setValorDeLaFuerza(producto);
	}
	
	/**
	 * @Pre: La instancia pasada por parametro ha sido creada.
	 * @Post: Se ha modificado el valor de la fuerza con el valor de la multiplicacion entre el valor de la 
	 * fuerza de la instancia y el de la fuerza pasada por parametro.
	 * @throws en caso no poder acceder al valor se lanza una exception del tipo ExceptionAccesoNoPermitido
	 * @throws en caso de que la fuerza pasado por parametro tenga valor 0 se lanza una ArithmeticException.
	*/ 
	public void dividir(Fuerza fuerza) throws ExceptionAccesoNoPermitido{
		double division=this.valorDeLaFuerza/fuerza.getValorDeLaFuerza();
		this.setValorDeLaFuerza(division);
	}
			
	/**
	 * @Pre: Las instancias pasadas por parametro han sido creadas.
	 * @Post: Se ha retornado la multiplicacion entre fuerza1 y fuerza2.
	 * @throws en caso no poder acceder a algun valor se retorna una exception del tipo ExceptionAccesoNoPermitido
	 * @throws en caso de que la fuerza2 pasado por parametro tenga valor 0 se lanza una ArithmeticException.
	 */
	public static double dividir(Fuerza fuerza1,Fuerza fuerza2) throws ExceptionAccesoNoPermitido{
		double division=fuerza1.getValorDeLaFuerza()/fuerza2.getValorDeLaFuerza();
		return division;
	}
	
	/**
	 * @Pre: La instancia ha sido creada.
	 * @Post: Se ha modificado el valor de la fuerza con el valor de la mutliplicacion entre el valor de la 
	 * fuerza de la instancia y el del parametro.
	 * @throws en caso no poder acceder al valor se retorna una exception del tipo ExceptionAccesoNoPermitido
	 * @throws en caso de que valor pasado por parametro tenga valor 0 se lanza una ArithmeticException.
	*/
	public void dividir(double valor) throws ExceptionAccesoNoPermitido{
		double division=this.valorDeLaFuerza/valor;
		this.setValorDeLaFuerza(division);
	}
	
}
