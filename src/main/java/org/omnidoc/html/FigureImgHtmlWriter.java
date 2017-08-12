/**
 * 
 */
package org.omnidoc.html;

import java.io.PrintWriter;

import org.omnidoc.stucture.Element;
import org.omnidoc.stucture.ImageElement;

/**
 * 
 */
public class FigureImgHtmlWriter  extends AbstractCommandHtmElementWriter{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeGenericElement(HtmlDocumentModel model, Element element, PrintWriter writer, int tab) {

		ImageElement img = (ImageElement)element;
		
		writer.append("<div style='margin:auto' class='figure'><img src=\"").append(img.getSource()).append("\" /></div>");

	}

}
