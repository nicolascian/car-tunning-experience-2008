/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;
import java.util.*;

/**
 * @Domumentacion: Una instancia de la clase Auto modela un auto, con todos sus componentes,
 * como ser caja, motor, neumaticos, combustible, escape,etc, los cuales son suceptibles 
 * de ser desgastados por el clima o por la superficie durante una carrera.
 * Los componentes proveen potencia a la instancia de Auto, la cual es utilizada para
 * acelerar y aumentar su velocidad.
 * Una instancia de esta clase se puede encontrar, encendido, apagado o acelerando.
 * 	
 * @version 1.0
 */
public class Auto implements AfectablePorClima, AfectablePorSuperficie{
	
	private double CNTE_ACELERACION_POTENCIA = 0.0003;//permite calcular la aceleracion a
													  //partir de la potencia.
	private double Velocidad; //velocidad del auto
	private double Aceleracion; //aceleracion de del auto
	private double Posicion; // dstancia recorrida
	private double tiempo = 20;//
	private Motor motor;
	private Caja caja;
	private Combustible combustible;
	private Alimentacion alimentacion;
	private Carroceria carroceria;
	private Suspension suspension;	
	private Escape escape;
	private Turbo turbo;
	private Eje ejeDelantero;
	private Eje ejeTrasero;
			
	/**
	 *	@Pre:
	 *	@Post: Se ha creado una instancia de la clase Auto con valores por defecto 
	 *  correspondiente a un auto mediano de calle, con caja automatica de 5 velocidades,
	 *  motor de 1.6 litros con 4 cilindros, con revoluciones maximas de 8000 rpm.
	*/
	public Auto(){
		//creacion de componentes
		  setMotor(new Motor(4,1600,8000)); 
		  setCaja(new Automatica(5));
		  setCombustible(new Combustible(50.0,0.4));
		  setCarroceria(new Carroceria(2.4));
		  setAlimentacion(new Carburador());
		  setSuspension(new Suspension());
		  setEscape(new Escape());
		  setTurbo(new Turbo());
		/* eje delantero */
		  setEjeDelantero(new Eje());
		  ejeDelantero.setLlantaDerecha(new Llanta());
		  ejeDelantero.setLlantaIzquierda(new Llanta());
		  ejeDelantero.setNeumaticoDerecho(new NeumaticoMixto());
		  ejeDelantero.setNeumaticoIzquierdo(new NeumaticoMixto());
		/* eje trasero */
		  setEjeTrasero(new Eje());
		  ejeTrasero.setLlantaDerecha(new Llanta());
		  ejeTrasero.setLlantaIzquierda(new Llanta());
		  ejeTrasero.setNeumaticoDerecho(new NeumaticoMixto());
		  ejeTrasero.setNeumaticoIzquierdo(new NeumaticoMixto());
		//inicilizacion de otros atributos
		  setEncendido(false);
	      Velocidad=0;
		  Aceleracion=0;
		  Posicion=0;
	}
	
	/**
	 * @Pre: Las intancias pasadas por parametro han sido creadas.
	 * @Post: Se ha creado una instancia de la clase Auto segun los componentes pasados
	 * por parametro.
	 * @param motor
	 * @param caja
	 * @param combustible
	 * @param carroceria
	 * @param alimentacion
	 * @param suspension
	 * @param escape
	 * @param turbo
	*/
	public Auto(Motor motor,Caja caja,Combustible combustible,
			Carroceria carroceria,Alimentacion alimentacion,
			Suspension suspension,Escape escape,Turbo turbo){
		setMotor(motor);
		setCaja(caja);
		setCombustible(combustible);
		setCarroceria(carroceria);
		setAlimentacion(alimentacion);
		setSuspension(suspension);
		setEscape(escape);
		setTurbo(turbo);
		setEjeDelantero(new Eje());
		  ejeDelantero.setLlantaDerecha(new Llanta());
		  ejeDelantero.setLlantaIzquierda(new Llanta());
		  ejeDelantero.setNeumaticoDerecho(new NeumaticoMixto());
		  ejeDelantero.setNeumaticoIzquierdo(new NeumaticoMixto());
		setEjeTrasero(new Eje());
		  ejeTrasero.setLlantaDerecha(new Llanta());
		  ejeTrasero.setLlantaIzquierda(new Llanta());
		  ejeTrasero.setNeumaticoDerecho(new NeumaticoMixto());
		  ejeTrasero.setNeumaticoIzquierdo(new NeumaticoMixto());
		setEncendido(false);
		//inicializacion de aceleracion y velocidad
		Velocidad=0;
		Aceleracion=0;
		Posicion=0;
    }

	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se ha obtenido la aceleracion de la instancia de acuerdo a la potencia. 
	*/
	public double getAceleracion(){
		
		Aceleracion = CNTE_ACELERACION_POTENCIA * ( (getPotenciaTotal()*getPotenciaTotal()) + getMotor().getRPM() );
		return Aceleracion;		
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se ha obtenido la velocidad de acuerdo a la potencia.
	*/
	public double getVelocidad(){
	 	double adherencia = (this.getEjeDelantero().getNeumaticoDerecho().calcularAdherencia()
	 					+ this.getEjeDelantero().getNeumaticoIzquierdo().calcularAdherencia()
	 					+ this.getEjeTrasero().getNeumaticoDerecho().calcularAdherencia()
	 					+ this.getEjeTrasero().getNeumaticoIzquierdo().calcularAdherencia())/4;
		Velocidad =Velocidad+getAceleracion()*adherencia*tiempo;
		return Velocidad;
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se ha obtenido la posicion en la pista de acuerdo a la velocidad y aceleracion de la
	 * instancia. 
	 * @return
	*/
	public double getPosicion() {
		
		Posicion += getVelocidad() * (0.00000006) + (0.5)*(getAceleracion());
		return Posicion;
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se ha seteado la posicion del auto en la pista.
	*/
	public void setPosicion(double posicion) {
		Posicion = posicion;
	}

	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se ha obtenido la potencia de la instancia en HP de acuerdo a la potencia total
	 * de sus componentes.
	 * @Nota: Todos los componentes excepto la carroceria aportan potencia solo al estar encendida
	 * la instancia, la carroceria solo con que el auto tenga velocidad mayor que cero aporta potencia. 
	*/
	public double getPotenciaTotal(){
		double potencia=0; 
		//seteo la velocidad de la carroceria
		this.getCarroceria().setVelocidad(Velocidad); 
		//hay componente que solo aportan potencia al estar encendido el auto
		if(this.isEncendido()){ 
		   potencia=motor.obtenerPotencia() +   /* de aca salen: Alimentacion, Combustible*/
		            caja.obtenerPotencia() +
		            suspension.obtenerPotencia() +
		            escape.obtenerPotencia() +
		         //   ejeDelantero.obtenerPotencia() +//de aca salen: llantas y neumaticos delanteros
		         //   ejeTrasero.obtenerPotencia() + //de aca salen: llantas y neumaticos traseros
		            turbo.obtenerPotencia();
		} 
		//en caso de que el auto se este moviendo la carroceria aporta potencia negativa
		potencia=potencia+getCarroceria().obtenerPotencia(); 
		return(potencia);
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se han desgastado todos los componentes de la instancia.
	 * @return
	*/
	public void Desgastar(){
		LinkedList<Componente> lista = this.obtenerComponentes();
		Iterator<Componente> it = lista.iterator();
		while (it.hasNext()){
			it.next().desgastar();
		}
	}	
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Retorna true en caso de que el auto se encuentre preparado para correr una carrera,
	 * es decir que tenga las caracteristicas minimas de componentes y estado para ello.
	*/
	public boolean estaListoParaCarrera() {
		boolean listo = true;
		try{
			comprobarComponentes();
		}catch (Exception e){
			return false;
		}
		return listo;
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Compruba la precencia y estado de los componentes del auto.
	 * @throws: ExceptionComponenteFaltante (en caso de que un componente falte),
	 *          ExceptionComponenteDesgastado (en caso de que un componente se halle totalmente desgastado)
	*/
	public void comprobarComponentes() 
	  throws ExceptionComponenteFaltante, ExceptionComponenteDesgastado{
		LinkedList<Componente> lista = this.obtenerComponentes();
		Iterator<Componente> it = lista.iterator();
		while (it.hasNext()){
			Componente aux = it.next();
			if( aux == null) throw new ExceptionComponenteFaltante();
			if(aux.getEstado()==0)throw new ExceptionComponenteDesgastado(aux.getClass().getName());
		}
	}

	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Solo si la instancia se encuentra lista para correr una carrera se enciende la instancia.
	 * Si luego de ejecutar este metodo no queda encendido puede deberse a componentes faltantes o 
	 * desgastados completamente.
	*/
	public void setEncendido(boolean encendido) {
      if(motor!=null){	
		if(encendido)
	  		getMotor().encender();
        else
	 		getMotor().apagar();
      }
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Retorna true en caso de que el auto se encuentre encendido.
	*/
	public boolean isEncendido() {
		if(motor!=null)
			return(getMotor().isEncendido());
		else
			return(false);
	}

	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: En caso de que la instancia se encuentre lista para carrera se enciende la instancia.
	*/
    public void acelerar(boolean valor){
	   	if(getMotor()!=null)
		   getMotor().acelerar(valor);
	  
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se retorna true en caso de que la instancia se encuentre acelerando.
	*/
	public boolean isAcelerando(){
		if(getMotor()!=null){
		   return(getMotor().isAcelerando());
		}else
		   return false;
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Retorna el estado promedio de la instancia, teniendo en cuenta el estado de todos
	 * sus componentes.
	*/
	public double getEstado() {
		double Estado = 0;
		int componentes=0;
		LinkedList<Componente> lista = this.obtenerComponentes();
		Iterator<Componente> it = lista.iterator();
		while (it.hasNext()){
			Estado = Estado + it.next().getEstado();
			componentes++;
		}
		if(componentes>0)
		   return Estado/componentes;
		else
		   return 0;
	}

	/**
	 * @Pre: Se ha creado la instancia de la clase Auto, la instancia del parametro clima ha sido
	 * creada.
	 * @Post: Se afecta a la instancia y a todos sus componentes por el clima pasado por parametro.
	*/
	public void afectar(Clima clima){
		LinkedList<AfectablePorClima> lista = this.obtenerAfectablesPorClima();
		Iterator<AfectablePorClima> it = lista.iterator();
		while (it.hasNext()){
			it.next().afectar(clima);
		}
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto, la instancia del parametro superficie ha sido
	 * creada.
	 * @Post: Se afecta a la instancia y a todos sus componentes por la supericie pasada por parametro.
	*/
	public void afectar(Superficie superficie){
		LinkedList<AfectablePorSuperficie> lista = this.obtenerAfectablesPorSup();
		Iterator<AfectablePorSuperficie> it = lista.iterator();
		while (it.hasNext()){
			it.next().afectar(superficie);
		}
	}
	
	public boolean isAutomatica(){
		return(getCaja().getClass().isInstance(new Automatica(5)));
	}
		
	public boolean isManual(){
		return(getCaja().getClass().isInstance(new Manual(5)));
	}
	
	public boolean isSecuencial(){
		return(getCaja().getClass().isInstance(new Secuencial(5)));
	}	

	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Retorna una instancia de lista LinkedList con todos los componentes de la instancia.
	*/
	public LinkedList<Componente> obtenerComponentes(){
		
		LinkedList<Componente> lista =  new LinkedList<Componente>();
		lista.add(this.alimentacion);
		lista.add(this.caja);
		lista.add(this.carroceria);
		lista.add(this.combustible);
		lista.add(this.escape);
		lista.add(this.motor);
		lista.add(this.suspension);
		lista.add(this.turbo);
		lista.add(this.ejeDelantero);
		lista.add(this.ejeDelantero.getLlantaDerecha());
		lista.add(this.ejeDelantero.getLlantaIzquierda());
		lista.add(this.ejeDelantero.getNeumaticoDerecho());
		lista.add(this.ejeDelantero.getNeumaticoIzquierdo());
		lista.add(this.ejeTrasero.getLlantaDerecha());
		lista.add(this.ejeTrasero.getLlantaIzquierda());
		lista.add(this.ejeTrasero.getNeumaticoDerecho());
		lista.add(this.ejeTrasero.getNeumaticoIzquierdo());
		lista.add(this.ejeTrasero);
		return lista;
	}

	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Retorna una instancia de lista LinkedList con todos los componentes afectables por
	 * superficie de la instancia.
	*/
	public LinkedList<AfectablePorSuperficie> obtenerAfectablesPorSup(){
		LinkedList<AfectablePorSuperficie> listaAS = new LinkedList<AfectablePorSuperficie>();
		LinkedList<Componente> listaComp = this.obtenerComponentes();
		Iterator<Componente> it = listaComp.iterator();
		while (it.hasNext()){
			it.next().agregarAListaAfecSuperficie(listaAS);
		}
		return listaAS;
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Retorna una instancia de lista LinkedList con todos los componentes afectables por
	 * clima de la instancia.
	*/
	public LinkedList<AfectablePorClima> obtenerAfectablesPorClima(){
		LinkedList<AfectablePorClima> listaAC = new LinkedList<AfectablePorClima>();
		LinkedList<Componente> listaComp = this.obtenerComponentes();
		Iterator<Componente> it = listaComp.iterator();
		while (it.hasNext()){
			it.next().agregarAListaAfecClima(listaAC);
		}
		return listaAC;
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se setea el eje delantero de la instancia.
	*/
	public void setEjeDelantero(Eje ejeDelantero) {
    	ejeDelantero.instalar(this);
		try{	
		  ejeDelantero.setLlantaDerecha(this.getEjeDelantero().getLlantaDerecha());
		  ejeDelantero.setLlantaIzquierda(this.getEjeDelantero().getLlantaIzquierda());
		  ejeDelantero.setNeumaticoDerecho(this.getEjeDelantero().getNeumaticoDerecho());
		  ejeDelantero.setNeumaticoIzquierdo(this.getEjeDelantero().getNeumaticoIzquierdo());
		}catch(Exception e){}
		this.ejeDelantero = ejeDelantero;
	 
	}

	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se obtiene el eje delantero de la instancia.
	*/
	public Eje getEjeDelantero() {
		return ejeDelantero;
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se setea el eje trasero de la instancia.
	*/
	public void setEjeTrasero(Eje ejeTrasero) {
	 
		ejeTrasero.instalar(this);
		try{
		  ejeTrasero.setLlantaDerecha(this.getEjeTrasero().getLlantaDerecha());
		  ejeTrasero.setLlantaIzquierda(this.getEjeTrasero().getLlantaIzquierda());
		  ejeTrasero.setNeumaticoDerecho(this.getEjeTrasero().getNeumaticoDerecho());
		  ejeTrasero.setNeumaticoIzquierdo(this.getEjeTrasero().getNeumaticoIzquierdo());
		}catch(Exception e){}
		this.ejeTrasero = ejeTrasero;
	 
	}

	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se obtiene el eje delantero de la instancia.
	*/
	public Eje getEjeTrasero() {
		return ejeTrasero;
	}
	
	public Combustible getCombustible() {
		return combustible;
	}

	public void setCombustible(Combustible combustible) {
		combustible.instalar(this);
		this.combustible = combustible;
	}

	public void setSuspension(Suspension suspension) {
		this.suspension = suspension;
		suspension.instalar(this);
	}

	public Suspension getSuspension() {
		return suspension;
	}
	
	public void setCaja(Caja caja){
		caja.instalar(this);
		this.caja = caja;
	}

	public Caja getCaja(){
		return this.caja;
	}

	public void setMotor(Motor motor){
		motor.instalar(this);
		this.motor = motor;
	}
	
	public Motor getMotor(){
		return this.motor;
	}

	public void setAlimentacion(Alimentacion alimentacion) {
		alimentacion.instalar(this);
		this.alimentacion = alimentacion;
	}

	public Alimentacion getAlimentacion() {
		return alimentacion;
	}

	public void setCarroceria(Carroceria carroceria) {
		carroceria.instalar(this);
		this.carroceria = carroceria;
	}
	
	public Carroceria getCarroceria() {
		return carroceria;
	}

	public void setEscape(Escape escape) {
		escape.instalar(this);
		this.escape = escape;
	}

	public Escape getEscape() {
		return escape;
	}

	public void setTurbo(Turbo turbo) {
		turbo.instalar(this);
		this.turbo = turbo;
	}
	
	public Turbo getTurbo() {
		return turbo;
	}
	
	/* toString */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	*/
	@Override
	public String toString() {
		String cadena="Auto Velocidad: "+getVelocidad()+"Km/h ";
		if(estaListoParaCarrera())
			cadena=cadena+" Esta Listo Para Carrera ";
		else
			cadena=cadena+" No Esta Listo Para Carrera ";
		cadena=cadena+'\n'+getMotor().toString()+getCaja().toString();
		return(cadena);
	}
}