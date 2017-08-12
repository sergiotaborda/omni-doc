/**
 * 
 */
package org.omnidoc.inputsources;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * 
 */
public interface OutputStreamSource {

	public OutputStream getOutputStream() throws IOException;
	public PrintWriter getPrintWriter() throws IOException;
}
