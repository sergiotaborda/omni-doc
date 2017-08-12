package org.omnidoc.writer;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;

import org.omnidoc.Document;
import org.omnidoc.xml.XMLDOMDocument;
import org.w3c.dom.Node;

public class NodeDocumentWriter implements DocumentWriter {


	DOMResult result = new DOMResult();
	
	@Override
	public void writeDocument(Document document) {
		try {
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");    
			
			DOMSource source = new DOMSource(((XMLDOMDocument)document).asXMLDocument());


			transformer.transform(source, result);
			
		} catch (TransformerConfigurationException e) {
			throw new RuntimeException(e);
		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}
	}

	public Node getNode(){
		return result.getNode();
	}
}
