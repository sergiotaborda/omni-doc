package org.omnidoc.html;

import java.io.PrintWriter;

import org.omnidoc.stucture.Element;
import org.omnidoc.stucture.InlineCodeElement;

public class InlineCodeHtmlWriter extends AbstractCommandHtmElementWriter {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeGenericElement(HtmlDocumentModel model, Element element, PrintWriter writer, int tab) {

		InlineCodeElement sc = (InlineCodeElement)element;
		
		writer.append("<span class='code' ");
		writer.append(">");
		writeInnerElements( model,  element,  writer,  tab);
		writer.println("</span>");
	}

}