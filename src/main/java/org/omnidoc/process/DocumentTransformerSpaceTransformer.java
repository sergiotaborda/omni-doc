package org.omnidoc.process;

import org.omnidoc.Document;
import org.omnidoc.DocumentTransformer;

public class DocumentTransformerSpaceTransformer implements DocumentSpaceTransformer{

	public static DocumentTransformerSpaceTransformer forTransformer(DocumentTransformer transformer){
		return new DocumentTransformerSpaceTransformer(transformer);
	}

	private DocumentTransformer transformer;
	
	private DocumentTransformerSpaceTransformer(DocumentTransformer transformer){
		this.transformer = transformer;
	}
	
	@Override
	public void transform(DocumentSpace source, DocumentSpace target) {

		for (Document doc : source){
			if (transformer.canTransform(doc)){
				transformer.transform(doc, target);
			}
		}

	}






}
