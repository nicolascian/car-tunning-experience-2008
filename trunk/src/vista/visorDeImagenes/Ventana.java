package vista.visorDeImagenes;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.*;
/**
 * @author Usuario
 *
 */
public class Ventana extends JFrame {
	
	private String ruta="C:\\Documents and Settings\\Usuario\\Escritorio" +
                        "\\Nueva carpeta\\Dodge Viper SRT10 Coupe";
	private VisorDeImagenes visor=new VisorDeImagenes(ruta,this,new Dimension(800,600),
			                                          new Posicion(50,50));
	
	public Ventana() {
	     super("Visor de  Imagenes");
	     this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	     this.setSize(600,600);
	     this.setVisible(true);
	     this.setLayout(new FlowLayout());
	     visor.excluirArchivo("167c7d3655p.png"); 	     
	}
	   
	 public void paint(Graphics g) {
		 super.paint(g);
	 }
	 
}
