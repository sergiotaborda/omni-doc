/**
 * 
 */
package org.omnidoc.html;

import java.io.PrintWriter;

import org.omnidoc.stucture.Element;

/**
 * 
 */
public class RefHtmlWriter  extends AbstractCommandHtmElementWriter{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeGenericElement(HtmlDocumentModel model, Element element, PrintWriter writer, int tab) {

	
		writer.append("<sup><a href=\"#")
		.append(element.getAttribute("id"))
		.append("\" >").append("[*]");

		
		writer.println("</a></sup>");
	}

}
