package vista.imagenTramo;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.awt.Image;
public class Prueba extends JFrame {

	private Imagen imagen=null;

	private BufferedImage image=null;
	
	private Graphics2D grafico=null;
	
	private ImagenTramo imagenTramo=null;
	
	public Prueba(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(ImagenTramo.createDimensionOptima());//600 450
	    this.setVisible(true);
	    this.setLayout(new FlowLayout());
	    image = new BufferedImage(this.getWidth(),this.getHeight(), BufferedImage.TYPE_INT_RGB);
	    grafico=image.createGraphics();
	    imagenTramo=ImagenTramo.createTramoAsfaltoCespedDiaAlgoNublado(ImagenTramo.createDimensionOptima(),
	    		                                                       new Posicion());
	    super.paint(this.getGraphics());
	    
	}
	
	public void paint(Graphics g){
	  
	   try{	
		Imagen imagenAuxiliar=imagenTramo.getImagen();
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
		Prueba prueba=new Prueba();
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