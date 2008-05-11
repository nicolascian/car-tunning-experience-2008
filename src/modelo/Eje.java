package modelo;

public abstract class Eje extends Componente
implements AfectablePorSuperficie{ {
	
	private Llanta LDerecha;
	private Llanta LIzquierda;
	private Neumatico NDerecho;
	private Neumatico NIzquierdo;
	
	public void Eje(){
		this.setEstado(100);
	}
	
	public double ObtenerPotencia() {
		
	}
	
	
	
	
}
