package org.omnidoc.process;

import org.omnidoc.read.DocumentReader;

public class DocumentReaderSource implements Source{

	public static DocumentReaderSource forReader(DocumentReader reader){
		return new DocumentReaderSource(reader);
	}
	
	private DocumentReader reader;

	private DocumentReaderSource(DocumentReader reader){
		this.reader = reader;
	}
	
	@Override
	public DocumentSpace getDocumentSpace() {
		DocumentSpace space = new ListDocumentSpace();
		
		space.addDocument(reader.readDocument());
		
		return space;
	}

}
