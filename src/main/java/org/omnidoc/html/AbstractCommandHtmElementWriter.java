/**
 * 
 */
package org.omnidoc.html;

import java.io.PrintWriter;

import org.omnidoc.stucture.Element;
import org.omnidoc.stucture.ParagraphElement;

/**
 * 
 */
public abstract class AbstractCommandHtmElementWriter implements HtmlElementWriter{

	/**
	 * @param element
	 */
	protected void writeInnerElements(HtmlDocumentModel model, Element element, PrintWriter writer, int tab) {
		for (Element el : element.getElements()){
			if (el instanceof ParagraphElement){
				
				ParagraphHtmlWriter w = (ParagraphHtmlWriter)model.getWriter(el);
				w.writeInnerElements(model, el, writer, tab);

			} else {
				model.writeElement(el, writer, tab);
			}
		}
	}
}
