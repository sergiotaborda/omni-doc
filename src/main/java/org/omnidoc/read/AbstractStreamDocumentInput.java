package org.omnidoc.read;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.middleheaven.io.repository.ManagedFile;
import org.omnidoc.Document;
import org.omnidoc.StreamDocumentInput;
import org.omnidoc.xml.XMLDOMDocument;
import org.xml.sax.SAXException;

public abstract class AbstractStreamDocumentInput implements StreamDocumentInput{

	
	public abstract ManagedFile getParent();
	public abstract String getName();
	
	@Override
	public final Document readDocument() {
		String name = getName();
		try {

			// Create a builder factory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			factory.setNamespaceAware(false);
			factory.setExpandEntityReferences(false);

			// Create the builder and parse the file
			org.w3c.dom.Document doc = factory.newDocumentBuilder().parse(this.getInputStream());
		
			if (name==null){
				name="";
			} else if (name.contains(".")){
				name = name.substring(0, name.indexOf("."));
			}
			return new XMLDOMDocument(doc, name);

		} catch (FileNotFoundException e) {
			throw new RuntimeException("Error reading " + name, e);
		}  catch (IOException e) {
			throw new RuntimeException("Error reading " + name,e);
		} catch (SAXException e) {
			throw new RuntimeException("Error reading " + name,e);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException("Error reading " + name,e);
		}
	}

}
