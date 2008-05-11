package modelo;

public class ExceptionComponenteDesgastado extends Exception {
	public ExceptionComponenteDesgastado() {
		super ("Elemento Desgastado");
	}

	public ExceptionComponenteDesgastado(String s) {
		super ("Elemento desgastado: "+s);
	}
}
