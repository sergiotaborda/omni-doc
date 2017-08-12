/**
 * 
 */
package org.omnidoc.html;

import java.io.PrintWriter;

import org.omnidoc.stucture.Element;

/**
 * 
 */
public interface HtmlElementWriter {

	/**
	 * @param element
	 * @param writer
	 * @param tab
	 */
	void writeGenericElement(HtmlDocumentModel model, Element element, PrintWriter writer, int tab);

}
