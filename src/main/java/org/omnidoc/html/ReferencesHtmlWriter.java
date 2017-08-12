/**
 * 
 */
package org.omnidoc.html;

import java.io.PrintWriter;

import org.omnidoc.stucture.Element;

/**
 * 
 */
public class ReferencesHtmlWriter  extends AbstractCommandHtmElementWriter{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeGenericElement(HtmlDocumentModel model, Element element, PrintWriter writer, int tab) {

		writer.println("<table class='referencesTable'>");

		for (Element el : element.getElements()){
			writer.println("<tr>");
			writer.println("<td>");
			writer.println("<a name='"+ el.getAttribute("id") + "'>" + el.getAttribute("author"));
			writer.println("</a>");
			writer.println("<td>");
			writer.println("<td>");
			writer.println("<a href='"+ el.getAttribute("url") + "'>" + el.getElements().iterator().next());
			writer.println("</a>");
			writer.println("<td>");
			writer.println("</tr>");
		}
		writer.println("</table>");
	}

}
