package org.omnidoc;

import org.middleheaven.io.IOTransport;
import org.middleheaven.io.StreamableContent;
import org.middleheaven.io.repository.BufferedMediaManagedFileContent;



public class BinaryDocument implements Document{

	private String name;
	private BufferedMediaManagedFileContent content;
	
	public BinaryDocument (String name , String contentType){
		this.name = name;
		content = new BufferedMediaManagedFileContent();
		content.setContentType(contentType);
		
	}
	
	@Override
	public String getName() {
		return name;
	}

	public StreamableContent getContent(){
		return content;
	}

	@Override
	public Document duplicate() {
		BinaryDocument other = new BinaryDocument(this.name, this.content.getContentType());
		IOTransport.copy(this.getContent()).to(other.getContent());
		return other;
	}

}
