/**
 * 
 */
package vista.visorDeImagenes;
import vista.imagenTramo.Imagen;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import vista.imagenAuto.imagenRelojes.ImagenReloj;
import vista.imagenTramo.Posicion;


/**
 * @author Usuario
 *
 */
public class PanelVisorDeImagenes extends JPanel {

	private ListaCircular lista=null;
	
	private Dimension dimensionImagen=null;
	
	private BufferedImage imagenActual=null;
	
	private BufferedImage buffImage=null;

	private Graphics2D grafico=null;
	
	private long tiempoDeActualizacion=3200;
	
	private Thread hiloDeActualizacion=null;
	
	public PanelVisorDeImagenes(Dimension dimension,Posicion posicion,String ruta){
		this.dimensionImagen=dimension;
		this.setBounds(posicion.getX(),posicion.getY(),dimension.width,dimension.height);
		this.setSize(dimension);
		this.buffImage=new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
		this.grafico=buffImage.createGraphics();
		cargarImagenes(ruta);
		this.hiloDeActualizacion=new Thread(){
		    public void run(){
			     super.run();
			     Iterator<BufferedImage> iterador=lista.iterator();
					while(iterador.hasNext()){
						imagenActual=(BufferedImage) iterador.next();
						repaint();
						try{   
						  this.sleep(tiempoDeActualizacion);
						}catch(Exception e){};
					}
			}
		};
		this.hiloDeActualizacion.start();
		
	}
	
	public void repaint(){
		this.paint(this.getGraphics());
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
	  	try{	
	  		grafico.drawImage(imagenActual,0,0,(int)dimensionImagen.getWidth(),
	  				          (int)dimensionImagen.getHeight(),null);
			((Graphics2D)g).drawImage(buffImage,0 ,0,(int)(dimensionImagen.getWidth()),
					                 (int)(dimensionImagen.getHeight()),this);
		}catch(Exception e){};
	}
	
	private void cargarImagenes(String ruta){
		lista=new ListaCircular();
		File rs=new File(ruta);
		File[] vectorArchivos=rs.listFiles();
		for(int cursor=0;cursor<vectorArchivos.length;cursor++)
			if(rutaValida((vectorArchivos[cursor]).getName()))
			   try {
				   BufferedImage imagen =ImageIO.read(vectorArchivos[cursor]);
		       	   lista.add(imagen);
		       } catch (IOException e) {}
	}
	
	private boolean rutaValida(String ruta){
		if(validarExtension(ruta)){
		  if(validarNombre(ruta))
		     return true;
		  else
			 return false;
		}
		else
		    return false;
	}
	
	private boolean validarExtension(String ruta){
		boolean retorno=false;
		if(validarJpg(ruta))
		   retorno=true;
		else
		  if(validarPng(ruta))
		    retorno=true;
		return retorno;
	}
	
	private boolean validarNombre(String ruta){
		if((ruta.endsWith("atras.png"))||(ruta.endsWith("interior.jpg")))
		     return false;	  
		   else
			 return true;	 
	}
		
	private boolean validarJpg(String ruta){
		if(ruta.endsWith("jpg"))
		     return true;	  
		else
			 return false;
    }
			
	private boolean validarPng(String ruta){
	   if(ruta.endsWith("png"))
		     return true;	  
		   else
			 return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean flag) {
		if(flag)
		  this.hiloDeActualizacion.resume();
		else
		  this.hiloDeActualizacion.suspend();
		super.setVisible(flag);
	}

	
	
}
