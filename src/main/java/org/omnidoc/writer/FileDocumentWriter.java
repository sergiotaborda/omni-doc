package org.omnidoc.writer;

import java.io.File;

import org.omnidoc.xml.PlainXMLDocumentWriter;


public class FileDocumentWriter extends PlainXMLDocumentWriter {

	private File file;
	public FileDocumentWriter(String file){
		this(new File(file));
	}
	
	
	public FileDocumentWriter(File file){
		super(file);
		this.file = file;
	}

	public File getFile(){
		return file;
	}
	
	

}
