/**
 * 
 */
package org.omnidoc.models;

import java.io.IOException;

import org.omnidoc.Document;
import org.omnidoc.inputsources.InputSource;
import org.omnidoc.inputsources.OutputStreamSource;

/**
 * 
 */
public interface DocumentModel {

	
	public boolean canRead();
	public boolean canWrite();
	public Document readDocument(InputSource source) throws IOException;
	public void writeDocument(Document document, OutputStreamSource source) throws IOException;
}
