package test;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import vista.visorDeImagenes.Posicion;
import vista.visorDeImagenes.VisorDeImagenes;
public class TestVisorDeImagenesDeEscritorio {
		
	public static void main(String[] args) {
		JFrame ventana=new JFrame("Visor de Imagenes");
		ventana.setDefaultCloseOperation(ventana.EXIT_ON_CLOSE);
	    ventana.setSize(600,600);
	    ventana.setVisible(true);
	    ventana.setLayout(new FlowLayout());
	    String ruta="src\\vista\\imagenes\\DodgeViper";
		VisorDeImagenes visor=new VisorDeImagenes(ruta,ventana,new Dimension(800,600),
					                                          new Posicion(50,50));
	    visor.excluirArchivo("autoParaPista.png"); 	     
	}
}
