/**
 * 
 */
package org.omnidoc.html;

import java.io.PrintWriter;

import org.omnidoc.stucture.Element;
import org.omnidoc.stucture.ParagraphElement;
import org.omnidoc.stucture.SourceCodeElement;

/**
 * 
 */
public class SourceCodeHtmlWriter  extends AbstractCommandHtmElementWriter {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeGenericElement(HtmlDocumentModel model, Element element, PrintWriter writer, int tab) {

		SourceCodeElement sc = (SourceCodeElement)element;
		
		writer.append("<pre");
		writer.append(" language=\"").append(sc.getLanguage()).append("\" ");
		writer.append(">\n");

		writeInnerElements( model,  element,  writer,  tab);
		writer.println("</pre>");
	}

}
