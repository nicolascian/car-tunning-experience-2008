package test;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import vista.visorDeImagenes.VisorDeImagenes;
import vista.imagenTramo.Posicion;
public class TestVisorDeImagenesDeEscritorio {
		
	public static void main(String[] args) {
		JFrame ventana=new JFrame("Visor de Imagenes");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    ventana.setSize(800,600);
	    ventana.setVisible(true);
	    ventana.setLayout(new FlowLayout());
	    String ruta="src//vista//imagenAuto//imagenes//DodgeViper";
	    JPanel panel=new JPanel();
	    panel.setVisible(true);
	    panel.setSize(ventana.getSize());
	    ventana.add(panel);
		VisorDeImagenes visor=new VisorDeImagenes(ruta,panel,new Dimension(400,300),
					                                          new Posicion(0,0));
	    visor.excluirArchivo("atras.png");
	    visor.excluirArchivo("interior.jpg");
	}
}
