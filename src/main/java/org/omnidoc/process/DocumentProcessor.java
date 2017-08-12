package org.omnidoc.process;

import org.omnidoc.DocumentTransformer;

public class DocumentProcessor {

	
	public static DocumentProcessor createProcessor(){
		return new DocumentProcessor();
	}

	private CompositeDocumentSpaceTransformer processTransformer = new CompositeDocumentSpaceTransformer();
	
	private DocumentProcessor(){
	}
	
	public DocumentProcessor add(DocumentTransformer transformer){
		processTransformer.addTransformer( DocumentTransformerSpaceTransformer.forTransformer(transformer) );
		return this;
	}
	
	public DocumentProcessor add(DocumentSpaceTransformer transformer){
		processTransformer.addTransformer(transformer);
		return this;
	}
	
	public DocumentProcessor remove(DocumentSpaceTransformer transformer){
		processTransformer.removeTransformer(transformer);
		return this;
	}
	
	public void process(Source source, Target target){
		
		DocumentSpace sourceSpace = source.getDocumentSpace();
		
		DocumentSpace targetSpace = new ListDocumentSpace();
		
		processTransformer.transform(sourceSpace, targetSpace);
		
		target.setDocumentSpace(targetSpace);
	}
}
