package test;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import vista.imagenTramo.*;
import vista.imagenRueda.*;
import vista.imagenAuto.ImagenAutoDesdeAtras;

public class TestImagenTramoDeEscritorio extends JFrame{

	private BufferedImage image=null;
	
	private Graphics2D grafico=null;
	
	private ImagenTramo imagenTramo=null;
			
	private ImagenAutoDesdeAtras imagenAuto=null;
	
	public TestImagenTramoDeEscritorio(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(ImagenTramo.createDimensionOptima());
	    this.setVisible(true);
	    this.setLayout(new FlowLayout());
	    image = new BufferedImage(this.getWidth(),this.getHeight(), BufferedImage.TYPE_INT_RGB);
	    grafico=image.createGraphics();
	   /* imagenTramo=ImagenTramo.createTramoAsfaltoCespedDiaAlgoNublado(ImagenTramo.createDimensionOptima(),
	    		                                                       new Posicion());
	    		                                                       */

	    imagenTramo=ImagenTramo.createTramoTierraCespedDiaAlgoNublado(ImagenTramo.createDimensionOptima(),
		    		                                                       new Posicion());
        imagenAuto=new ImagenAutoDesdeAtras(null,"src//vista//imagenAuto//imagenes//DodgeViper//atras.png",
	    		                            new Dimension(270,170),new Posicion(250,400));
	    super.paint(this.getGraphics());
	}
	
	public void paint(Graphics g){
	  try{	
		Imagen imagenAuxiliar=imagenTramo.getImagen();
		grafico.drawImage(imagenAuxiliar.getImage(),
			     imagenAuxiliar.getPosicion().getX(),imagenAuxiliar.getPosicion().getY(),
		         imagenAuxiliar.getDimension().width,imagenAuxiliar.getDimension().height,this);
		imagenAuto.paint(grafico);
		((Graphics2D)g).drawImage(image,0 ,0,this.getWidth(),this.getHeight(),this);
	  }catch(Exception e){}
	}
	
	public static void main(String[] args) {
		Thread hilo=new Thread(){
		TestImagenTramoDeEscritorio prueba=new  TestImagenTramoDeEscritorio();
			public void run(){
				super.run();
				while(true){
					prueba.paint(prueba.getGraphics());
					try{   
						  this.sleep(50);
					}catch(Exception e){};
				}
			}
		};
		hilo.run();
		System.out.println("Fin");
	}
}