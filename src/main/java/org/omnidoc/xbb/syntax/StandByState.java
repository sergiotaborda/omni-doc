/**
 * 
 */
package org.omnidoc.xbb.syntax;

import java.util.function.Consumer;

import org.omnidoc.stucture.Element;
import org.omnidoc.xbb.scanner.Token;

/**
 * 
 */
public class StandByState extends SyntaxState {

	private Element parent;


	/**
	 * Constructor.
	 * @param doc
	 */
	public StandByState(Element parent) {
		this.parent = parent;
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SyntaxState recieve(Token token, Consumer<Element> consumer) {
		if (token.isCommand()){
			return new CommandInitState(parent).recieve(token, consumer);
		} else if (token.isText()){
			return new SectionInitState(parent).recieve(token, consumer);
		} else {
			return this;
		}
	}

}
