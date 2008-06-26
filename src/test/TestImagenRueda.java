package test;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import vista.imagenTramo.*;
import vista.imagenRueda.*;
import java.awt.Dimension;
public class TestImagenRueda extends JFrame{

	private BufferedImage image=null;
	
	private Graphics2D grafico=null;
	
	private ImagenRueda imagenRueda=null;
	
	public TestImagenRueda(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(800,600);
	    this.setVisible(true);
	    this.setLayout(new FlowLayout());
	    imagenRueda=ImagenRueda.createImagenRuedaNeumaticoDibujo2(new Dimension(100,250), new Posicion());
	    image = new BufferedImage((int)imagenRueda.getDimension().getWidth(),(int)imagenRueda.getDimension().getHeight(),
	    		                  BufferedImage.TYPE_INT_RGB);
	    grafico=image.createGraphics();
	    super.paint(this.getGraphics());
	}
	
	public void paint(Graphics g){
	  
	   try{	
		Imagen imagenAuxiliar=imagenRueda.getImagen();
		grafico.drawImage(imagenAuxiliar.getImage(),
			     imagenAuxiliar.getPosicion().getX(),imagenAuxiliar.getPosicion().getY(),
		         imagenAuxiliar.getDimension().width,imagenAuxiliar.getDimension().height,this);
		((Graphics2D)g).drawImage(image,
			     imagenAuxiliar.getPosicion().getX(),imagenAuxiliar.getPosicion().getY(),
		         imagenAuxiliar.getDimension().width,imagenAuxiliar.getDimension().height,this);
	  }catch(Exception e){}
	}
	
	public static void main(String[] args) {
		Thread hilo=new Thread(){
		TestImagenRueda prueba=new  TestImagenRueda();
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
	}
}