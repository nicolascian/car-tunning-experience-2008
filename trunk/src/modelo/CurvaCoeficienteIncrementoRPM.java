package modelo;

public class CurvaCoeficienteIncrementoRPM {
		
	private double rpmFalla=0;
	
	private double valorParaFalla=0;
	
	private AproximacionPorDosRectas aproximacion=null;
		
	/**
	 * 
	 */
	public CurvaCoeficienteIncrementoRPM(double revolucionesMaximas) {
		double xCero=0;
		double yCero=120;
		double xUno=revolucionesMaximas*0.6;
		double yUno=60;
		double xDos=revolucionesMaximas;
		double yDos=0;
		this.aproximacion=new AproximacionPorDosRectas(xCero,yCero,xUno,yUno,xDos,yDos);
		this.rpmFalla=revolucionesMaximas*0.93;
		this.valorParaFalla=aproximacion.getValorEn(xUno)*(-1);
	}

	public double getValor(double x){
		if(x<rpmFalla)
		   return this.aproximacion.getValorEn(x);
		else
		   return valorParaFalla;
	}
	
}
