package org.omnidoc.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;

import org.middleheaven.io.repository.ManagedFile;
import org.omnidoc.DocumentTransformer;
import org.omnidoc.TransformationException;
import org.omnidoc.process.DocumentSpace;
import org.w3c.dom.Document;

public class XSLTransfomer implements DocumentTransformer {

	Transformer transformer;
	
	public static XSLTransfomer forFile(File xslFile){
		try {
			return forInputStream( new FileInputStream(xslFile));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static XSLTransfomer forFile(ManagedFile xslFile){
		return  forInputStream(xslFile.getContent().getInputStream());
	}
	
	public static XSLTransfomer forInputStream(InputStream stream){
		TransformerFactory factory = TransformerFactory.newInstance();

		try {
			Transformer transformer = factory.newTransformer( new StreamSource( stream ) );
			return new XSLTransfomer(transformer);
		} catch (TransformerConfigurationException e) {
			throw new TransformationException(e);
		}	
	}
	
	private XSLTransfomer(Transformer transformer){
		this.transformer = transformer;
		
	}
	
	public void setParameter(String name, Object value){
		this.transformer.setParameter(name, value);
	}
	
	
	@Override
	public boolean canTransform(org.omnidoc.Document doc) {
		return doc instanceof XMLDOMDocument;
	}

	@Override
	public synchronized void transform(org.omnidoc.Document doc, DocumentSpace target) {

		
		try {

		
			DOMSource source = new DOMSource( ((XMLDOMDocument)doc).asXMLDocument());
			DOMResult result = new DOMResult();
				
			transformer.transform( source, result );

			target.addDocument(new XMLDOMDocument((Document)result.getNode(),doc.getName()));
			
		} catch (TransformerException e) {
			throw new TransformationException(e);
		}
	}


}
