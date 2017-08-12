/**
 * 
 */
package org.omnidoc.xbb.scanner;

import java.util.function.Consumer;

/**
 * 
 */
public class AttributeValueState extends ParseState {


    StringBuilder builder = new StringBuilder();
    boolean open = false;
	private boolean escapeContent;
	private boolean terminal = false;
	private String lookFor;
	private String commandName;

	/**
	 * Constructor.
	 * @param equalsIgnoreCase
	 * @param string
	 */
	public AttributeValueState( boolean escapeContent, String lookFor, String commandName) {
		this.escapeContent = escapeContent;
		this.lookFor = lookFor;
		this.commandName = commandName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParseState recieve(ScanPosition pos, char c, Consumer<Token> consumer) {
		 switch (c){
		 case '"':
		 case '\'':
			 // start or end of value
			 if (open){
				 // close
				 consumer.accept(new AttributeValueToken(builder.toString()));
				 return new AttributeNameState(escapeContent,lookFor, commandName);
			 } 
			 // open
			 open = !open;
			 return this;
		 case ' ':
			 if (!open){
				 // stop
				 consumer.accept(new AttributeValueToken(builder.toString()));
				 return new AttributeNameState(escapeContent,lookFor, commandName);
			 } 
		 case '/':
			 if (!open){
				 terminal = true;
				 return this;
			 }
		 case ']':
			// stop
			 if (!open){
				 consumer.accept(new AttributeValueToken(builder.toString()));
				 if (terminal){
					 consumer.accept(new CommandToken(commandName, true));
				 }
				 return new TextBuilderState(escapeContent, lookFor);
			 }
		 default:
			 builder.append(c);
			 return this;
		 }
	}

}
