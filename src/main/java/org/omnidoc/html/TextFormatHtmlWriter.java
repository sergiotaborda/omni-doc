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
public class TextFormatHtmlWriter  extends AbstractCommandHtmElementWriter{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeGenericElement(HtmlDocumentModel model, Element element,PrintWriter writer, int tab) {
		
		String tag = "";
		if (element.getName().equals("emph")){
			tag = "i";
		} else if (element.getName().equals("strong")){
			tag = "strong";
		} else if (element.getName().equals("estrang")){
			tag = "i";
		}
	
		writer.append("<").append(tag).append(">");
		
		writeInnerElements( model,  element,  writer,  tab);
		writer.append("</").append(tag).append(">");
	}

}
