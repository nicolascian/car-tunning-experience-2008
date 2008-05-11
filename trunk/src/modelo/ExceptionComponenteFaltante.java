package modelo;

public class ExceptionComponenteFaltante extends Exception{
	public ExceptionComponenteFaltante() {
		super ("Elemento faltante");
	}

	public ExceptionComponenteFaltante(String s) {
		super ("Elemento faltante: "+s);
	}

}
