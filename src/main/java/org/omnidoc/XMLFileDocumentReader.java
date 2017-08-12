package org.omnidoc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.omnidoc.xml.XMLDOMDocument;
import org.xml.sax.SAXException;


public class XMLFileDocumentReader implements StreamDocumentInput {

	private File file;
	public XMLFileDocumentReader(File file){
		this.file = file;
	}
	
	public XMLFileDocumentReader(String file){
		this.file = new File(file);
	}
	public File getFile(){
		return file;
	}
	
	@Override
	public Document readDocument() {
		try {

			// Create a builder factory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			factory.setNamespaceAware(false);
			factory.setExpandEntityReferences(false);

			// Create the builder and parse the file
			org.w3c.dom.Document doc = factory.newDocumentBuilder().parse(getFile());
			return new XMLDOMDocument(doc,getFile().getName());

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}  catch (IOException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public InputStream getInputStream() {
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
