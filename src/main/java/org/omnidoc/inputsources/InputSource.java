/**
 * 
 */
package org.omnidoc.inputsources;

import java.io.IOException;
import java.io.InputStream;

/**
 * 
 */
public interface InputSource {

	public InputStream getInputStream() throws IOException ;
	public String getSourceName() throws IOException;
	
}
