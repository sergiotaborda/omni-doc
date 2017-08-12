/**
 * 
 */
package org.omnidoc.inputsources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * 
 */
public class FileSource implements InputSource , OutputStreamSource{

	private File file;

	public FileSource(File file){
		this.file = file;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws FileNotFoundException 
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		return new FileInputStream(file);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OutputStream getOutputStream() throws IOException {
		return new FileOutputStream(file);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSourceName() throws IOException {
		return file.getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PrintWriter getPrintWriter() throws IOException {
		return new PrintWriter(file, "ISO-8859-1");
	}

}
