package org.omnidoc.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Writer;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.omnidoc.Document;
import org.omnidoc.writer.StreamDocumentWriter;

public class PlainXMLDocumentWriter implements StreamDocumentWriter {

	private OutputStream outStream;
	private Writer writer;

	public PlainXMLDocumentWriter(File file) {
		try {
			this.outStream = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public PlainXMLDocumentWriter(OutputStream outStream) {
		this.outStream = outStream;
	}
	
	public PlainXMLDocumentWriter(Writer writer) {
		this.writer = writer;
	}

	@Override
	public void writeDocument(Document document) {
		try {
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");    
			
			DOMSource source = new DOMSource(((XMLDOMDocument)document).asXMLDocument());
			StreamResult result;
			if (writer == null){
				result = new StreamResult(outStream);
			} else {
				result = new StreamResult(writer);
			}
			transformer.transform(source, result);
			
		} catch (TransformerConfigurationException e) {
			throw new RuntimeException(e);
		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}
	}
	
}
