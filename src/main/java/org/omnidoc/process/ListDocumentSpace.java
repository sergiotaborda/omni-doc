package org.omnidoc.process;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.omnidoc.Document;

public class ListDocumentSpace implements DocumentSpace {

	private List<Document> documents = new LinkedList<Document>();
	
	public void addDocument(Document doc){
		documents.add(doc);
	}
	
	public void removeDocument(Document doc){
		documents.remove(doc);
	}

	@Override
	public void copyTo(DocumentSpace target) {
		for (Document document : documents){
			target.addDocument(document);
		}
	}

	@Override
	public Iterator<Document> iterator() {
		return documents.iterator();
	}
}
