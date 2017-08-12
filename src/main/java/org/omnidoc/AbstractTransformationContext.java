package org.omnidoc;

import java.util.LinkedList;
import java.util.List;

import org.middleheaven.io.repository.ManagedFile;

public class AbstractTransformationContext implements TransformationContext {

	private List<Document> documents = new  LinkedList<Document>();
	private Document origin;
	private ManagedFile targetFolder;
	
	public AbstractTransformationContext(Document origin,ManagedFile targetFolder){
		this.origin = origin;
		this.targetFolder = targetFolder;
	}
	
	@Override
	public void addDocument(Document doc) {
		documents.add(doc);
	}

	@Override
	public Document getOrigin() {
		return origin;
	}

	@Override
	public ManagedFile getTargetFolder() {
		return targetFolder;
	}

}
