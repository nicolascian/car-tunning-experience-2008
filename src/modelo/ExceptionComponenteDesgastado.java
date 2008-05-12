package modelo;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/


public class ExceptionComponenteDesgastado extends Exception {
	public ExceptionComponenteDesgastado() {
		super ("Elemento Desgastado");
	}

	public ExceptionComponenteDesgastado(String s) {
		super ("Elemento desgastado: "+s);
	}
}
