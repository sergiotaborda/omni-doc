package org.omnidoc;

import java.io.InputStream;

import org.omnidoc.read.DocumentReader;

public interface StreamDocumentInput extends DocumentReader {

	
	public InputStream getInputStream();
}
