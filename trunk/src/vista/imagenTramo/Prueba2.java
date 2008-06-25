package imagenTramo;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.awt.Image;
public class Prueba2 extends JFrame {

	private Imagen imagen=null;
	
	private ImagenSecuencial imagenPasto=null;
	
	private ImagenSecuencial imagenCielo=null;
	
	private ImagenSecuencial imagenRuta=null;
	
	private BufferedImage image=null;
	
	private int contador=21;
	
	public Prueba2(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(800,600);
	    this.setVisible(true);
	    this.setLayout(new FlowLayout());
	    ImagenSecuencial imagenCarretera=new ImagenSecuencial("src\\imagenTramo\\carretera2",
	    		                             false,true,new Dimension(800,360),new Posicion(0,0));
	    ImagenSecuencial imagenPasto=new ImagenSecuencial("src\\imagenTramo\\pasto",
                                             false,false,new Dimension(800,360),new Posicion(0,240));
	    imagenRuta=new ImagenSecuencial(imagenCarretera,imagenPasto,ImagenTramo.createShapeRuta(new Dimension(800,360)));
	    imagenCielo=new ImagenSecuencial("src\\imagenTramo\\cieloSemiNublado",true,false,
                                         new Dimension(800,240),new Posicion(0,0));
	    image = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);
	    
	    super.paint(this.getGraphics());
	    
	}
	
	public void paint(Graphics g){
	  try{	
		Imagen imagenAuxiliar;
		Graphics2D g2=(Graphics2D) g;
		if(contador>20){
		imagenAuxiliar=imagenCielo.getImagen();
		g2.drawImage(imagenAuxiliar.getImage(),
				     imagenAuxiliar.getPosicion().getX(),imagenAuxiliar.getPosicion().getY(),
			         imagenAuxiliar.getDimension().width,imagenAuxiliar.getDimension().height,
			         this);
		contador=0;
		}
		else
			contador++;
		imagenAuxiliar=imagenRuta.getImagen();
		g2.drawImage(imagenAuxiliar.getImage(),
			     imagenAuxiliar.getPosicion().getX(),imagenAuxiliar.getPosicion().getY(),
		         imagenAuxiliar.getDimension().width,imagenAuxiliar.getDimension().height,this);
	  }catch(Exception e){}
	}
	
	public static void main(String[] args) {
		Thread hilo=new Thread(){
		Prueba2 prueba=new Prueba2();
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