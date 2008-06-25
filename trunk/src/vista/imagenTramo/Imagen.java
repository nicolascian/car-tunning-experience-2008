package vista.imagenTramo;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * @author Usuario
 *
 */
public class Imagen {

	private Posicion posicion=null;
	
	private Dimension dimension=null;
		
	private Image image=null;	
	
	public Imagen(String ruta){
	 if(Imagen.validarExtension(ruta)){	  
	   try{	
		BufferedImage img =ImageIO.read(new File(ruta));
		this.setImage((Image)img);
		this.setDimension(new Dimension(img.getWidth(),img.getHeight()));
	   }catch(IOException e){}
	 }
	 this.setPosicion(new Posicion());
	}
	
	public Imagen(String ruta, Dimension dimension,Posicion posicion){
		 if(Imagen.validarExtension(ruta)){	  
		   try{	
		    BufferedImage imagen =ImageIO.read(new File(ruta));
			this.setImage(imagen);
		   }catch(IOException e){}
		 }
		 this.setPosicion(new Posicion(posicion.getX(),posicion.getY()));
		 this.setDimension(new Dimension(dimension));
	}
	
	public Imagen(Image image, Dimension dimension,Posicion posicion){
		 this.setImage(image);
		 this.setPosicion(new Posicion(posicion.getX(),posicion.getY()));
		 this.setDimension(new Dimension(dimension));
	}
	
	/**
	 * 
	 * @param ruta directorio donde se hallan las imagenes
	 * @return
	*/
	public static Imagen[] createVectorImagenes(String directorio){
		ArrayList<Imagen> arrayImagenes=new ArrayList<Imagen>();
		File rs=new File(directorio);
		File[] vectorArchivos=rs.listFiles();
		for(int cursor=0;cursor<vectorArchivos.length;cursor++){
			if(Imagen.validarExtension(vectorArchivos[cursor].getName()))
			   arrayImagenes.add(new Imagen(vectorArchivos[cursor].getPath()));
		}
		Imagen[] vectorImg=new Imagen[arrayImagenes.size()];
		Iterator<Imagen> it=arrayImagenes.iterator();
		int cursor=0;
		while(it.hasNext()){
			vectorImg[cursor]=it.next();
			cursor++;
		}
		return vectorImg;
	}
	
	private static boolean validarExtension(String ruta){
		boolean retorno=false;
		if(validarJpg(ruta))
		   retorno=true;
		else
		  if(validarPng(ruta))
		    retorno=true;
		return retorno;
	}
	
	private static boolean validarJpg(String ruta){
		if(ruta.endsWith("jpg")||ruta.endsWith("JPG"))
		     return true;	  
		else
			 return false;
    }
			
	private static boolean validarPng(String ruta){
	   if(ruta.endsWith("png")||ruta.endsWith("PNG"))
		     return true;	  
		   else
			 return false;
	}
	
	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @return the posicion
	 */
	public Posicion getPosicion() {
		return posicion;
	}

	/**
	 * @param posicion the posicion to set
	 */
	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	/**
	 * @return the dimension
	 */
	public Dimension getDimension() {
		return dimension;
	}

	/**
	 * @param dimension the dimension to set
	 */
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	
}
