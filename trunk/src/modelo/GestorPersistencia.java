package modelo;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;



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
		
        
        System.out.println("GUARDADO (GestorPersistencia)");
	}
	
	public modelo.Usuario Cargar(){
		return null;
	}
	
}
