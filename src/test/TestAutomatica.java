package test;
import junit.framework.*;
import modelo.componente.*;
public class TestAutomatica extends TestCase {

	public void testGenerarRelacionesDeCaja() {
		for(int cambios=4;cambios<=6;cambios++){
		  Automatica automatica=new Automatica(cambios);
		  for(int cambio=0;cambio<=cambios;cambio++)
			try{
				if(cambio>0)
				  assertTrue(automatica.getRelacionDeCambio(cambio-1)>
				             automatica.getRelacionDeCambio(cambio));
				else
					assertTrue(automatica.getRelacionDeCambio(cambio)>0);	
			}catch(AssertionError a){
				a.printStackTrace();
			}
		}
	}

}	