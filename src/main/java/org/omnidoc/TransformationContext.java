package org.omnidoc;

import org.middleheaven.io.repository.ManagedFile;


public interface TransformationContext {

	
	public Document getOrigin();
	public void addDocument(Document doc);
	public ManagedFile getTargetFolder ();

}
