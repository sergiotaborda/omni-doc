/**
 * 
 */
package org.omnidoc.xbb.syntax;

import java.util.function.Consumer;

import org.omnidoc.stucture.Element;
import org.omnidoc.stucture.StructuredDocument;
import org.omnidoc.xbb.scanner.CommandToken;
import org.omnidoc.xbb.scanner.Token;

/**
 * 
 */
public class DocumentInitState extends ParseState {

	private StructuredDocument doc;

	/**
	 * Constructor.
	 * @param doc
	 */
	public DocumentInitState(StructuredDocument doc) {
		this.doc = doc;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParseState recieve(ParseContext context, Token token, Consumer<Element> consumer) {
		if (token.isCommand()){
			if (token.as(CommandToken.class).map(t -> t.getName()).orElse("").equals("doc")){
				consumer.accept(doc);
				return new CommandInitState(doc, doc);
			} else {
				consumer.accept(doc); // send default document
				return new CommandInitState(doc).recieve(context, token, consumer);
			}
		} else if (token.isText()){
			consumer.accept(doc); // send default document
			return new SectionInitState(doc).recieve(null, token, consumer);
		} 
			
		return this;
		
	}





}
