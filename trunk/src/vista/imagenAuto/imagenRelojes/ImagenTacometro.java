package vista.imagenAuto.imagenRelojes;

import java.awt.Color;
import java.awt.Dimension;

import modelo.Auto;
import vista.imagenTramo.Posicion;

public class ImagenTacometro extends ImagenReloj {

	
	public ImagenTacometro(Auto auto,String rutaImagen,Posicion posicion, Dimension dimension,
			   Color colorDeAguja,double anguloMinimo,double anguloMaximo,
			   double valorMinimo,double valorMaximo){
		super(auto,rutaImagen,posicion,dimension,colorDeAguja,anguloMinimo,anguloMaximo,
			  valorMinimo,valorMaximo);
		
	}
	
	public static ImagenTacometro createTacometroBlanco(Auto auto,Posicion posicion,Dimension dimension){
		return new ImagenTacometro(auto,"src//vista//imagenAuto//rpm.png",posicion,dimension,Color.RED,
				                   1.5*Math.PI,0,0,8000);
	}

	public static Dimension createDimensionStandar(){
		return new Dimension(200,200);
	}
	
	protected void actualizarAngulo() {
	  double rpm=this.getAuto().getMotor().getRPM();
	  if(rpm<=this.getValorMaximo())	
		this.setAngulo(this.getM()*rpm+this.getB());
	  else
		this.setAngulo(0);
	}
	
}
