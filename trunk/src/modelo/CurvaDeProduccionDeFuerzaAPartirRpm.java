/**
 * 
 */
package modelo;

/**
 * @author Usuario
 *
 */
public class CurvaDeProduccionDeFuerzaAPartirRpm {

	private final static double MULTIPLICADOR_MAXIMO=0.00001;
	
	private final static double MULTIPLICADOR_MEDIO=0.0042;
	
	private final static double MULTIPLICADOR_MINIMO=0.0045;
	
	private double rpmFalla=0;
	
	private double valorParaFalla=0;
	
	private AproximacionPorDosRectas aproximacion=null;
		
	/**
	 * 
	 */
	public CurvaDeProduccionDeFuerzaAPartirRpm(double potencia,
			                                 double revolucionesMaximas) {
		double xCero=0;
		double yCero=potencia*MULTIPLICADOR_MAXIMO;
		double xUno=revolucionesMaximas*0.6;
		double yUno=potencia*MULTIPLICADOR_MEDIO;
		double xDos=revolucionesMaximas;
		double yDos=potencia*MULTIPLICADOR_MINIMO;
		this.aproximacion=new AproximacionPorDosRectas(xCero,yCero,xUno,yUno,xDos,yDos);
		this.rpmFalla=revolucionesMaximas*0.93;
		this.valorParaFalla=this.aproximacion.getValorEn(revolucionesMaximas*0.30);
	}

	public double getValor(double x){
		if(x<rpmFalla)
		   return this.aproximacion.getValorEn(x);
		else
		   return valorParaFalla;
	}
	
}
