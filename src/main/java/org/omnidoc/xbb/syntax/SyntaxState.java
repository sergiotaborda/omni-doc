/**
 * 
 */
package org.omnidoc.xbb.syntax;

import java.util.function.Consumer;

import org.omnidoc.Document;
import org.omnidoc.stucture.Element;
import org.omnidoc.xbb.scanner.Token;

/**
 * 
 */
public abstract class SyntaxState {

	public abstract SyntaxState recieve( Token token, Consumer<Element> consumer);

	/**
	 * @param sectionInitState
	 * @return
	 */
	protected final Element resolveDocument(Element parent) {
		Element it = parent;
		while (it != null && !(it instanceof Document)){
			it = it.getParent();
		}
		return it;
	}
}
