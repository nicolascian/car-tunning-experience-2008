package vista.imagenAuto.imagenRelojes;

import java.awt.Color;
import java.awt.Dimension;

import modelo.Auto;
import vista.imagenTramo.Posicion;

public class ImagenVelocimetro extends ImagenReloj {
	public ImagenVelocimetro(Auto auto,String rutaImagen,Posicion posicion, Dimension dimension,
			   Color colorDeAguja,double anguloMinimo,double anguloMaximo,
			   double valorMinimo,double valorMaximo){
		super(auto,rutaImagen,posicion,dimension,colorDeAguja,anguloMinimo,anguloMaximo,
			  valorMinimo,valorMaximo);
		
	}
	
	public static ImagenVelocimetro createVelocimetroBlanco(Auto auto,Posicion posicion,Dimension dimension){
		return new ImagenVelocimetro(auto,"src//vista//imagenAuto//kmh.png",posicion,dimension,Color.RED,
				                   1.5*Math.PI,0,0,240);
	}

	public static Dimension createDimensionStandar(){
		return new Dimension(200,200);
	}
	
	protected void actualizarAngulo() {
		this.setAngulo(this.getM()*this.getAuto().getVelocidad()+this.getB());
	}
	
}
