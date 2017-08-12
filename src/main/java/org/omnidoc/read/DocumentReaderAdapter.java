package org.omnidoc.read;

import org.omnidoc.Document;

public class DocumentReaderAdapter implements DocumentReader {

	
	private Document doc;

	public DocumentReaderAdapter(Document doc){
		this.doc = doc;
	}
	
	@Override
	public Document readDocument() {
		return doc;
	}

}
