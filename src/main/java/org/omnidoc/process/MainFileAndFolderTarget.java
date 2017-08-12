package org.omnidoc.process;

import java.io.File;
import java.io.Writer;

import org.middleheaven.io.IOTransport;
import org.middleheaven.io.repository.ManagedFile;
import org.middleheaven.io.repository.machine.MachineFiles;
import org.omnidoc.BinaryDocument;
import org.omnidoc.Document;
import org.omnidoc.xml.PlainXMLDocumentWriter;
import org.omnidoc.xml.XMLDOMDocument;

public class MainFileAndFolderTarget implements Target{

	private PlainXMLDocumentWriter writer;
	private ManagedFile folder;

	public static Target forFileAndFolder(ManagedFile file, File folder) {
		return forFileAndFolder(file, MachineFiles.resolveFile(folder.getAbsolutePath()));
	}
	
	public static Target forFileAndFolder(Writer writer, File folder) {
		return forFileAndFolder(writer, MachineFiles.resolveFile(folder.getAbsolutePath()));
	}
	
	public static Target forFileAndFolder(ManagedFile file, ManagedFile folder) {
		return new MainFileAndFolderTarget(new PlainXMLDocumentWriter(file.getContent().getOutputStream()),folder);
	}

	public static Target forFileAndFolder(Writer writer, ManagedFile folder) {
		return new MainFileAndFolderTarget(new PlainXMLDocumentWriter(writer),folder);
	}

	
	private MainFileAndFolderTarget(PlainXMLDocumentWriter writer, ManagedFile folder) {
		this.writer = writer;
		this.folder = folder;
	}

	@Override
	public void setDocumentSpace(DocumentSpace space) {

		for(Document doc : space){
			
			if (doc instanceof XMLDOMDocument){
			
				writer.writeDocument(doc);
			} else if (doc instanceof BinaryDocument){
				BinaryDocument bin = (BinaryDocument) doc;
				ManagedFile result = resolveFileFor(folder, (BinaryDocument)doc);
				IOTransport.copy(bin.getContent()).to(result.getContent());
			} else {
				System.err.println("Unkown Document type");
			}
		} 
	}

	private ManagedFile resolveFileFor (ManagedFile parentFolder , BinaryDocument doc){
		return parentFolder.retrive(doc.getName()).createFile();
	}
	
}
