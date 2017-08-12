/**
 * 
 */
package org.omnidoc.xml;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.omnidoc.Document;
import org.omnidoc.inputsources.InputSource;
import org.omnidoc.inputsources.OutputStreamSource;
import org.omnidoc.models.DocumentModel;
import org.xml.sax.SAXException;

/**
 * 
 */
public class XDocModel implements DocumentModel {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canRead() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canWrite() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	@Override
	public Document readDocument(InputSource source) throws IOException {
		// Create a builder factory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(false);
		factory.setExpandEntityReferences(false);

		// Create the builder and parse the file
		org.w3c.dom.Document doc;
		try {
			doc = factory.newDocumentBuilder().parse(source.getInputStream());
			return new XMLDOMDocument(doc, source.getSourceName());

		} catch (SAXException | ParserConfigurationException e) {
			throw new IOException(e);
		}

	}

	/**
	 * {@inheritDoc}
	 * @throws IOException 
	 */
	@Override
	public void writeDocument(Document document, OutputStreamSource source) throws IOException {
		try {

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");    

			DOMSource domSource = new DOMSource(((XMLDOMDocument)document).asXMLDocument());
			StreamResult result= new StreamResult(source.getOutputStream());

			transformer.transform(domSource, result);

		} catch (TransformerConfigurationException e) {
			throw new RuntimeException(e);
		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}
	}

}
