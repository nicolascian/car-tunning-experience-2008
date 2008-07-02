/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package vista.ventanas;

import java.util.LinkedList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.*;
import javax.swing.ImageIcon;
import modelo.componente.Componente;
import vista.imagenTramo.Imagen;
import vista.imagenTramo.Posicion;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.Color;
import vista.visorDeImagenes.PanelVisorDeImagenes;
import vista.imagenAuto.imagenesDeComponentes.*;

public class VentanaTaller extends JFrame {

	private modelo.Usuario usuario=null;
	
	private JPanel panelVisor=null;
	
	private JPanel panelBotones=null;
	
	private JPanel panelInfo=null;
	
	private JFrame ventanaMenu=null;
	
	private Dimension dimensionImagenBoton=new Dimension(10,10);
	
	private AdministradorDeImagenesYEtiquetasDeComponentes administrador=new AdministradorDeImagenesYEtiquetasDeComponentes();
	
	public VentanaTaller(JFrame ventanaMenu) {
		JFrame.setDefaultLookAndFeelDecorated(false);
		this.setLayout(null);
		this.ventanaMenu=ventanaMenu;
		this.setSize(800,700);
		this.setBackground(new Color(0,0,0,0));
		this.setTitle("Taller");
		this.setLocationRelativeTo(null); //centrada
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent e) {
		    	cerrarVentana();
		    }
		});
    	this.setResizable(false);
		this.setAlwaysOnTop(false);
		this.setVisible(false);
		
	}
	
	public void setUsuario(modelo.Usuario usuario){
		this.usuario=usuario;
	}
	
	private void crearPanelVisorDeImagenes(){
		panelVisor= new PanelVisorDeImagenes(new Dimension((int)(this.getSize().width*0.5),
                (int)(this.getSize().height*0.40)),new Posicion(),
                "src//vista//imagenAuto//imagenes//DodgeViper");
        this.add(panelVisor);
	}
	
	private void crearPaneles(){
		this.crearPanelVisorDeImagenes();
		panelInfo= new JPanel();
		panelInfo.setBounds(panelVisor.getWidth(),0,panelVisor.getWidth(),panelVisor.getHeight());
		panelInfo.setVisible(true);
		this.add(panelInfo);
		this.panelBotones=new JPanel();
		panelBotones.setBounds(0,panelVisor.getHeight(),this.getWidth(),(int)(this.getHeight()*0.55));
		panelBotones.setVisible(true);
		panelBotones.setLayout(new GridLayout(6,6));
	}
	
	private void agregarBotones(){
		LinkedList<Componente> lista=this.usuario.getAuto().obtenerComponentes();
		this.add(panelBotones);	
		Iterator<Componente> it=lista.iterator();
		while(it.hasNext()){
		   this.agregarBoton(it.next());
		}
		JTextArea texto=new JTextArea(usuario.getAuto().toString());
		texto.setBackground(new Color(0,0,0,0));
		texto.setVisible(true);
		panelInfo.add(texto);
		
	}
	
	private void cerrarVentana(){
		ventanaMenu.setVisible(true);
		this.dispose();
	}

	private void agregarBoton(Componente componente){
		DatoClase dato=administrador.getDatoClase(componente.getClass());
		ImageIcon icono=new ImageIcon((new Imagen(dato.getRutaImagen(),
                                dimensionImagenBoton,new Posicion())).getImage());
		icono= new ImageIcon(icono.getImage().getScaledInstance(40,40, java.awt.Image.SCALE_DEFAULT));
		JButton boton=new JButton(dato.getNombre(),icono);
		boton.addActionListener(new java.awt.event.ActionListener() {
			
			private Componente componente=null;
			
			private DatoClase dato=null;
			
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonComponente(componente,dato);
			}			
		});
		boton.setVerticalTextPosition(AbstractButton.CENTER);
	    boton.setHorizontalTextPosition(AbstractButton.LEFT);
		boton.setVisible(true);
		this.panelBotones.add(boton);
	}
	
	private void pressBotonComponente(Componente componente,DatoClase dato){
		this.panelBotones.setVisible(false);
	}

	/* (non-Javadoc)
	 * @see java.awt.Window#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean b) {
		try{
			this.panelVisor.setVisible(b);
		}catch(NullPointerException e){}
		if((b)&&(this.panelBotones==null)){
		   this.crearPaneles();
		   this.agregarBotones();
		}
		super.setVisible(b);
	}
}
