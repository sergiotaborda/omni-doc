/**
 * 
 */
package org.omnidoc.html;

import java.io.PrintWriter;

import org.omnidoc.stucture.Element;
import org.omnidoc.stucture.SectionElement;

/**
 * 
 */
public class SectionHtmlWriter  extends AbstractCommandHtmElementWriter{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeGenericElement(HtmlDocumentModel model, Element element, PrintWriter writer, int tab) {
		StringBuilder builder = new StringBuilder();
		
		for (int i =0; i < tab; i++){
			builder.append("\t");
		}
		
		SectionElement section = (SectionElement)element;
		writer.print(builder);
		writer.print("<h");
		writer.print(section.getLevel());
		writer.print(">");
		writer.print(section.getAttribute("title"));
		writer.print("</h");
		writer.print(section.getLevel());
		writer.println(">");
		
		for (Element el : element.getElements()){
			model.writeElement(el, writer,tab+1);
		}
	}

}
