package org.omnidoc;

import org.omnidoc.process.DocumentSpace;



public interface DocumentTransformer {

	public abstract void transform(Document doc, DocumentSpace target );
	public boolean canTransform(Document doc);
}