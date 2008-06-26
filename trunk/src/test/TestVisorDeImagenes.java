package test;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import org.junit.Test;
import vista.imagenTramo.Posicion;
import vista.visorDeImagenes.VisorDeImagenes;

public class TestVisorDeImagenes {

	public class Ventana extends JFrame {
		
		private String ruta="src//vista//imagenAuto//imagenesAutos//DodgeViper";
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
	
	
	@Test
	public void testVisorDeImagenes() {
		Ventana ventana=new Ventana();
	}

}
