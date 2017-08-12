package org.omnidoc.process;

import java.util.LinkedList;
import java.util.List;

public class CompositeDocumentSpaceTransformer implements DocumentSpaceTransformer{

	
	private final List<DocumentSpaceTransformer> transformers = new LinkedList<DocumentSpaceTransformer>();
	
	public void addTransformer(DocumentSpaceTransformer transformer) {
		transformers.add(transformer);
	}
	
	public void removeTransformer(DocumentSpaceTransformer transformer) {
		transformers.remove(transformer);
	}
	
	@Override
	public void transform(DocumentSpace source, DocumentSpace target) {

		DocumentSpace currentSource = source;
		DocumentSpace currentTarget = null;
		
		for (DocumentSpaceTransformer transformer : transformers){
			currentTarget = new ListDocumentSpace();
			transformer.transform(currentSource, currentTarget);
			currentSource = currentTarget;
			
		}
		
		if (currentTarget != null){
			currentTarget.copyTo(target);
		}
		
	}

	

}
