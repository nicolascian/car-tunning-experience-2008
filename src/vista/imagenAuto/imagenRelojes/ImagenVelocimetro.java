package vista.imagenAuto.imagenRelojes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Font;
import modelo.Auto;
import vista.imagenTramo.Imagen;
import vista.imagenTramo.Posicion;

public class ImagenVelocimetro extends ImagenReloj {
	public ImagenVelocimetro(Auto auto,String rutaImagen,Posicion posicion, Dimension dimension,
			   Color colorDeAguja,double anguloMinimo,double anguloMaximo,
			   double valorMinimo,double valorMaximo){
		super(auto,rutaImagen,posicion,dimension,colorDeAguja,anguloMinimo,anguloMaximo,
			  valorMinimo,valorMaximo);
		this.getGrafico().setFont(new Font("Arial",Font.ROMAN_BASELINE,20));
	}
	
	public static ImagenVelocimetro createVelocimetroBlanco(Auto auto,Posicion posicion,Dimension dimension){
		return new ImagenVelocimetro(auto,"src//vista//imagenAuto//kmh.png",posicion,dimension,Color.RED,
				                   1.5*Math.PI,0,0,240);
	}

	public static Dimension createDimensionStandar(){
		return new Dimension(200,200);
	}
	
	protected void actualizarAngulo() {
	  double velocidad=this.getAuto().getVelocidad();
		if(velocidad<240)	
		   this.setAngulo(this.getM()*velocidad+this.getB());
		else
		   this.setAngulo(0);	
	}

	
	public Image getImage() {
		this.actualizarAngulo();
		this.getGrafico().drawImage(this.getImagenReloj().getImage(),this.getImagenReloj().getPosicion().getX(),
			this.getImagenReloj().getPosicion().getY(),this.getImagenReloj().getDimension().width,
		    this.getImagenReloj().getDimension().height,null);
		this.getGrafico().setColor(Color.GREEN.brighter());
		this.getGrafico().drawString(String.valueOf((int)(this.getAuto().getPosicion()/1000)),(int)(this.getPosicionCentro().getX()*1.2),
				                     (int)(this.getPosicionCentro().getY()*1.51));
		this.getGrafico().setColor(this.getColorAguja());
		this.getGrafico().setStroke(new BasicStroke(4.0f));
		this.getGrafico().drawLine(this.getPosicionCentro().getX(),getPosicionCentro().getY(),
		    		(int)(getPosicionCentro().getX()+getLargoAguja()*Math.cos(this.getAngulo())),
		    		(int)(getPosicionCentro().getY()-getLargoAguja()*Math.sin(this.getAngulo())));
		return this.getBuffImage();
	}
	
}
