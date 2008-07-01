package modelo;

//guardar
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

//cargar
import org.xml.sax.SAXException;
import org.apache.xerces.parsers.DOMParser;


public class GestorPersistencia {

	
	public GestorPersistencia(){
		
	}
	
	public void Guardar(modelo.Usuario usuario) throws IOException, ParserConfigurationException{
		
		String nombreArchivo = "guardado.xml";
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder loader = factory.newDocumentBuilder();
        Document doc = loader.newDocument();

        doc.appendChild(usuario.toXml(doc));
        
        XMLSerializer serializer = new XMLSerializer();
        
        com.sun.org.apache.xml.internal.serialize.OutputFormat outFormat = new com.sun.org.apache.xml.internal.serialize.OutputFormat();

       
        outFormat.setVersion("1.0");
        outFormat.setIndenting(true);
        outFormat.setIndent(4);
        
        serializer.setOutputFormat(outFormat);
        serializer.setOutputCharStream(
          new java.io.FileWriter(nombreArchivo));
        serializer.serialize(doc);
		
        JOptionPane.showMessageDialog(new JFrame(),
        	    "Se ha guardado el usuario: " + usuario.getNombre() +'\n'+" satisfactoriamente.", 
        	    "Informacion de guardado", JOptionPane.INFORMATION_MESSAGE);

	}
	
	public modelo.Usuario Cargar(String nombreArchivo) throws IOException, ClassNotFoundException{
		
		DOMParser parser = new DOMParser();

		try {
		    parser.parse(nombreArchivo);

		} catch (SAXException se) {
		    se.printStackTrace();
		} catch (IOException ioe) {
		    ioe.printStackTrace();
		}

		Document docXml = parser.getDocument();
		
		return new modelo.Usuario(docXml.getDocumentElement());
	}
	
}
