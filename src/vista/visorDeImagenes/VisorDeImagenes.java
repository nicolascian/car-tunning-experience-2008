/**
 * 
 */
package vista.visorDeImagenes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import vista.visorDeImagenes.ListaCircular;
import java.util.Iterator;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import vista.imagenTramo.Posicion;
import java.util.LinkedList;
/**
 * @author Usuario
 *
 */
public class VisorDeImagenes{

	private ListaCircular lista=null;
	
	private BufferedImage imagenActual=null;
	
	private BufferedImage imagenPantalla=null;
	
	private String ruta=null;
	
	private LinkedList<String> listaDeExcluidos=null;
	
	Hilo hilo=null;
	
	JFrame frame=null;
	
	Dimension dimensionPantalla=null;
			
	Dimension dimensionImagen=null;
	
	Posicion posicionImagen=null;
	
	Posicion posicionPantalla=null;
		
	private class Hilo extends Thread{
					
		private long tiempoDeEspera=3200;
		
		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			imagenActual=null;
			super.run();
			Iterator<BufferedImage> iterador=lista.iterator();
			while(iterador.hasNext()){
				imagenActual=(BufferedImage) iterador.next();
				imprimirImagen();
				try{   
				  this.sleep(tiempoDeEspera);
				}catch(Exception e){};
			}
		}
	}
			
	/**
	 * 
	 */
	public VisorDeImagenes(String ruta,JFrame frame,Dimension dimension,Posicion posicion) {
		this.frame=frame;
		this.ruta=ruta;
		this.posicionPantalla=posicion;
		this.setDimensionPantalla(dimension);
		this.cargarImagenPantalla();
		this.listaDeExcluidos=new LinkedList<String>();
		cargarImagenes();
	}
	
	private void ajustarPantalla(){
		//ajuste de posicion
		Posicion posicion;
		posicion=new Posicion((int)Math.round(dimensionPantalla.width*0.12),
				              (int)Math.round(dimensionPantalla.height*0.15));
		this.setPosicionImagen(posicion);
		dimensionImagen=new Dimension((int)Math.round(dimensionPantalla.width*0.759),
				                      (int)Math.round(dimensionPantalla.height*0.71));
	}
	/**
	 * @return the dimensionPantalla
	 */
	private Dimension getDimensionPantalla() {
		return dimensionPantalla;
	}

	/**
	 * @param dimensionPantalla the dimensionPantalla to set
	 */
	private void setDimensionPantalla(Dimension dimensionPantalla) {
		this.dimensionPantalla = dimensionPantalla;
		this.ajustarPantalla();
	}
	
	/**
	 * @return the posicionImagen
	 */
	private Posicion getPosicionImagen() {
		return posicionImagen;
	}

	/**
	 * @param posicionImagen the posicionImagen to set
	 */
	private void setPosicionImagen(Posicion posicionImagen) {
		this.posicionImagen = posicionImagen;
	}

	/**
	 * @return the posicionPantalla
	 */
	public Posicion getPosicionPantalla() {
		return posicionPantalla;
	}

	/**
	 * @param posicionPantalla the posicionPantalla to set
	 */
	public void setPosicionPantalla(Posicion posicionPantalla) {
		this.posicionPantalla = posicionPantalla;
	}

	private void cargarImagenPantalla(){
		try {
			   BufferedImage imagen =ImageIO.read(new File("src//vista//visorDeImagenes//pantalla.png"));
	       	   this.setImagenPantalla(imagen);
	    } catch (IOException e) {e.printStackTrace();}
	}
	
	private void cargarImagenes(){
		hilo=null;
		lista=new ListaCircular();
		File rs=new File(ruta);
		File[] vectorArchivos=rs.listFiles();
		for(int cursor=0;cursor<vectorArchivos.length;cursor++)
			if(rutaValida((vectorArchivos[cursor]).getName()))
			   try {
				   BufferedImage imagen =ImageIO.read(vectorArchivos[cursor]);
		       	   lista.add(imagen);
		       } catch (IOException e) {}
		this.hilo=new Hilo();
		hilo.start();
	}
	
	private void imprimirImagen(){
	 try{
		 Graphics grafico=frame.getGraphics().create();
		 Image imagen=frame.createImage(this.getDimensionPantalla().width,
				                        this.getDimensionPantalla().height);
		 Graphics2D grafico2=(Graphics2D)imagen.getGraphics();
		 ((Graphics2D)grafico2).drawImage(imagenPantalla,0,0,getDimensionPantalla().width,getDimensionPantalla().height,null);	 	  
		 grafico2.drawImage(imagenActual,posicionImagen.getX(),posicionImagen.getY(),dimensionImagen.width,
                 dimensionImagen.height,null);
		 grafico.drawImage(imagen,posicionImagen.getX(),posicionImagen.getY(),dimensionImagen.width,
                 dimensionImagen.height,null);
	  }catch(Exception e){}
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
	
	public void excluirArchivo(String nombre){
		this.listaDeExcluidos.add(nombre);
		this.cargarImagenes();
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
		boolean retorno=true;
		Iterator<String> it=this.listaDeExcluidos.iterator();
		while(it.hasNext()){
		   if(ruta.endsWith(it.next()))
			 retorno=false;
		}
		return retorno;		 
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

	/**
	 * @return the imagenPantalla
	 */
	private BufferedImage getImagenPantalla() {
		return imagenPantalla;
	}

	/**
	 * @param imagenPantalla the imagenPantalla to set
	 */
	private void setImagenPantalla(BufferedImage imagenPantalla) {
		this.imagenPantalla = imagenPantalla;
	}
	
}

