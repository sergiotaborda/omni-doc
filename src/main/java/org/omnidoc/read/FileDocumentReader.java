package org.omnidoc.read;

import java.io.InputStream;

import org.middleheaven.io.repository.ManagedFile;



public class FileDocumentReader extends AbstractStreamDocumentInput {

	private ManagedFile file;
	public FileDocumentReader(ManagedFile file){
		this.file = file;
	}
	
	public ManagedFile getFile(){
		return file;
	}

	@Override
	public InputStream getInputStream() {
		return file.getContent().getInputStream();
	}

	@Override
	public String getName() {
		return file.getPath().getFileName();
	}

	@Override
	public ManagedFile getParent() {
		return file.getParent();
	}

}
