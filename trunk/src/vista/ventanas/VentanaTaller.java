/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package vista.ventanas;
import modelo.Taller;
import java.util.LinkedList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.*;
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

	private PanelComponente panelComponente=null;
	
	private modelo.Usuario usuario=null;
	
	private JPanel panelVisor=null;
	
	private JPanel panelBotones=null;
	
	private JPanel panelInfo=null;
	
	private JFrame ventanaMenu=null;
	
	private Dimension dimensionImagenBoton=new Dimension(10,10);
	
	private Taller taller=null;
	
	private AdministradorDeImagenesYEtiquetasDeComponentes administrador=null;
	
	public VentanaTaller(JFrame ventanaMenu) {
		JFrame.setDefaultLookAndFeelDecorated(false);
		this.setLayout(null);
		this.ventanaMenu=ventanaMenu;
		this.setSize(800,700);
		this.setBackground(Color.black);
		this.setTitle("Taller - Car Tunnning Experience 2008");
		this.setLocationRelativeTo(null); //centrada
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent e) {
		    	cerrarVentana();
		    }
		});
    	this.setResizable(false);
		this.setAlwaysOnTop(false);
		this.setVisible(false);
		this.crearPaneles();
	}
	
	public void refrescarPanelVisorDeImagenes(){
		try{
			this.remove(panelVisor);
		}catch(Exception e){}
		panelVisor= new PanelVisorDeImagenes(new Dimension((int)(this.getSize().width*0.5),
                (int)(this.getSize().height*0.40)),new Posicion(),
                "src//vista//imagenAuto//imagenes//DodgeViper");
        this.add(panelVisor);
	}
	
	public void crearPaneles(){
		try{
			panelInfo.removeAll();
		}catch(NullPointerException e){};
		panelInfo= new JPanel();
		panelInfo.setBackground(Color.black);
		this.add(panelInfo);
		this.panelBotones=new JPanel();
		panelBotones.setBackground(Color.black);
		this.add(panelBotones);
		Dimension dimensionPanelInfo=new Dimension((int)(this.getSize().width*0.5),
                                                    (int)(this.getSize().height*0.40));
		panelInfo.setBounds(dimensionPanelInfo.width,0,dimensionPanelInfo.width,dimensionPanelInfo.height);
	}
	
	public void refrescarContenido(){
		this.administrador=new AdministradorDeImagenesYEtiquetasDeComponentes();
		this.refrescarPanelVisorDeImagenes();
		this.refrescarPanelInfo();
		this.refrescarPanelBotones();
	}
	
	public void refrescarPanelInfo(){
		try{
			panelInfo.removeAll();
		}catch(NullPointerException e){};
		JTextArea texto=new JTextArea(usuario.getAuto().toString());
		texto.setBackground(new Color(0,0,0,0));
		texto.setForeground(Color.white);
		texto.setEditable(false);
		texto.setVisible(true);
		panelInfo.add(texto);
		panelInfo.setVisible(true);
	}
	
	public void refrescarPanelBotones(){
		panelBotones.removeAll();
		panelBotones.setBounds(0,panelVisor.getHeight(),this.getWidth(),(int)(this.getHeight()*0.55));
		panelBotones.setVisible(true);
		panelBotones.setLayout(new GridLayout(6,6));
		LinkedList<Componente> lista=this.usuario.getAuto().obtenerComponentes();
		Iterator<Componente> it=lista.iterator();
		while(it.hasNext()){
		   this.agregarBoton(it.next());
		}
	}
	
	private void cerrarVentana(){
		ventanaMenu.setVisible(true);
		this.panelComponente=null;
		this.dispose();
	}

	private void agregarBoton(Componente componente){
		DatoClase dato=administrador.getDatoClase(componente.getClass());
		BotonComponente boton=new BotonComponente(componente,dato,this);
		boton.setVisible(true);
		this.panelBotones.add(boton);
	}
	
	public void pressBotonComponente(Componente componente,DatoClase dato){
		try{
		   this.remove(this.panelComponente);
		}catch(NullPointerException e){};
		Dimension dimension=new Dimension(this.panelBotones.getSize());
		this.panelBotones.setVisible(false);
		this.panelComponente=new PanelComponente(componente,dato,dimension,this);
		this.add(panelComponente);
		panelComponente.setVisible(true);
	}

	/* (non-Javadoc)
	 * @see java.awt.Window#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean b) {
		if(b){
		   this.refrescarContenido();
		}
		else{
		   this.administrador=null;	
		   this.panelVisor=null;
		}
		super.setVisible(b);
	}
	
	public void setUsuario(modelo.Usuario usuario){
		this.usuario=usuario;
		this.taller=new Taller(this.usuario);
	}

	/**
	 * @return the panelComponente
	 */
	public PanelComponente getPanelComponente() {
		return panelComponente;
	}

	/**
	 * @param panelComponente the panelComponente to set
	 */
	public void setPanelComponente(PanelComponente panelComponente) {
		this.panelComponente = panelComponente;
	}

	/**
	 * @return the panelBotones
	 */
	public JPanel getPanelBotones() {
		return panelBotones;
	}

	/**
	 * @param panelBotones the panelBotones to set
	 */
	public void setPanelBotones(JPanel panelBotones) {
		this.panelBotones = panelBotones;
	}

	/**
	 * @return the ventanaMenu
	 */
	public JFrame getVentanaMenu() {
		return ventanaMenu;
	}

	/**
	 * @param ventanaMenu the ventanaMenu to set
	 */
	public void setVentanaMenu(JFrame ventanaMenu) {
		this.ventanaMenu = ventanaMenu;
	}

	/**
	 * @return the administrador
	 */
	public AdministradorDeImagenesYEtiquetasDeComponentes getAdministrador() {
		return administrador;
	}

	/**
	 * @param administrador the administrador to set
	 */
	public void setAdministrador(
			AdministradorDeImagenesYEtiquetasDeComponentes administrador) {
		this.administrador = administrador;
	}

	/**
	 * @return the usuario
	 */
	public modelo.Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @return the panelVisor
	 */
	public JPanel getPanelVisor() {
		return panelVisor;
	}

	/**
	 * @param panelVisor the panelVisor to set
	 */
	public void setPanelVisor(JPanel panelVisor) {
		this.panelVisor = panelVisor;
	}

	/**
	 * @return the dimensionImagenBoton
	 */
	public Dimension getDimensionImagenBoton() {
		return dimensionImagenBoton;
	}

	/**
	 * @param dimensionImagenBoton the dimensionImagenBoton to set
	 */
	public void setDimensionImagenBoton(Dimension dimensionImagenBoton) {
		this.dimensionImagenBoton = dimensionImagenBoton;
	}

	/**
	 * @return the taller
	 */
	public Taller getTaller() {
		return taller;
	}

	/**
	 * @param taller the taller to set
	 */
	public void setTaller(Taller taller) {
		this.taller = taller;
	}
	
}