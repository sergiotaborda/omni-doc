/**
 * 
 */
package org.omnidoc.xbb.scanner;

import java.util.function.Consumer;

/**
 * 
 */
public class CommandState extends ParseState {

	StringBuilder builder = new StringBuilder();
	boolean terminator = false;


	@Override
	public ParseState recieve(ScanPosition pos, char c, Consumer<Token> consumer) {
		switch (c){
		case '/':
			// terminator
			terminator = true;
			return this;
		case '=':
			consumer.accept(new CommandToken(builder.toString(), terminator));
			consumer.accept(new AttributeNameToken("")); // default
			return new AttributeValueState(builder.toString().equalsIgnoreCase("source"), "source", builder.toString());
		case ' ':
			consumer.accept(new CommandToken(builder.toString(), terminator));
			return new AttributeNameState(builder.toString().equalsIgnoreCase("source"), "source", builder.toString());

		case ']':	
			consumer.accept(new CommandToken(builder.toString(), terminator));
			return new TextBuilderState();
		default:
			builder.append(c);
			return this;
		}
	}

}
