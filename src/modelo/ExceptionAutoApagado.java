package modelo;

public class ExceptionAutoApagado extends Exception {
	
	public ExceptionAutoApagado() {
		super ("Exception Auto apagado");
	}

	@Override
	public String toString() {
		return ("Error Auto Apagado, carrera finalizada");
	}
}
