/**
 * 
 */
package org.omnidoc.html;

import java.io.PrintWriter;

import org.omnidoc.stucture.Element;
import org.omnidoc.stucture.UrlElement;

/**
 * 
 */
public class UrlHtmlWriter extends AbstractCommandHtmElementWriter {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeGenericElement(HtmlDocumentModel model, Element element, PrintWriter writer, int tab) {

		UrlElement url = (UrlElement)element;
		
		writer.append("<a href=\"");
		if (url.getHref().startsWith("http") || url.getHref().startsWith("mailto")){
			writer.append(url.getHref());
		} else {
			writer.append("http://").append(url.getHref());
		}
		writer.append("\" >");

		writeInnerElements( model,  element,  writer,  tab);
		writer.println("</a>");
	}

}
