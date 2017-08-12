/**
 * 
 */
package org.omnidoc.html;

import java.io.PrintWriter;

import org.omnidoc.stucture.Element;

/**
 * 
 */
public class ParagraphHtmlWriter  implements HtmlElementWriter {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeGenericElement(HtmlDocumentModel model, Element element, PrintWriter writer, int tab) {

		if (element.hasElements()){
			StringBuilder builder = new StringBuilder();
			
			for (int i =0; i < tab; i++){
				builder.append("\t");
			}
			
			writer.print(builder);
			writer.println("<p>");

			writeInnerElements(model, element, writer, tab);
			
			writer.println("</p>");
		}
		
		
	}
	
	public void writeInnerElements(HtmlDocumentModel model, Element element, PrintWriter writer, int tab) {
		for (Element el : element.getElements()){
			if (el.isPlainText()){
				writer.print(el.getAttribute("text"));	
			} else {
				model.writeElement(el, writer, tab);
			}
			writer.print(" ");
		}
	}

}
