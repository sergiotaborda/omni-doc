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
public class StandByState extends ParseState {

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
	public ParseState recieve(ParseContext context, Token token, Consumer<Element> consumer) {
		if (token.isCommand()){
			return new CommandInitState(parent).recieve(context, token, consumer);
		} else if (token.isText() || token.isTitle()){
			return new SectionInitState(parent).recieve(context, token, consumer);
		} else {
			return this;
		}
	}

}
