package org.omnidoc.process;

import org.omnidoc.Document;
import org.omnidoc.SearchableDocument;
import org.omnidoc.writer.NodeDocumentWriter;
import org.omnidoc.xml.XMLDOMDocument;

public class SearchDocumentTarget implements Target {

	private NodeDocumentWriter writer = new NodeDocumentWriter();
	
	public SearchDocumentTarget(){
		
	}
	
	@Override
	public void setDocumentSpace(DocumentSpace space) {
		
		for(Document doc : space){
			if (doc instanceof XMLDOMDocument){
			
				writer.writeDocument(doc);
			} 
		} 
	}

	public SearchableDocument getDocument(){
		org.w3c.dom.Document doc = (org.w3c.dom.Document) writer.getNode();
		
		return new SearchableDocument(new XMLDOMDocument(doc, "document"));
	}
	
}
