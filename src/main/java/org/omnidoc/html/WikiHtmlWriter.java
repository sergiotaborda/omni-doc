/**
 * 
 */
package org.omnidoc.html;

import java.io.PrintWriter;
import java.util.Optional;

import org.omnidoc.stucture.Element;
import org.omnidoc.stucture.WikiWordElement;

/**
 * 
 */
public class WikiHtmlWriter extends AbstractCommandHtmElementWriter {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeGenericElement(HtmlDocumentModel model, Element element, PrintWriter writer, int tab) {

		WikiWordElement wiki = (WikiWordElement)element;
		
		String lang = Optional.of(wiki.getLanguage()).filter( s -> !s.isEmpty()).orElse("en");
		
		writer.append("<a href=\"http://").append(lang).append(".wikipedia.org/wiki/").append(wiki.getKeyword()).append("\" >");

		
		writeInnerElements( model,  element,  writer,  tab);

		writer.println("</a>");
	}



}
