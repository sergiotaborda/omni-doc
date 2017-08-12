/**
 * 
 */
package org.omnidoc.xbb.scanner;

import java.util.function.Consumer;

/**
 * 
 */
public class AttributeNameState extends ParseState {

    StringBuilder builder = new StringBuilder();
	private boolean escapeContent;
	private String lookFor;
	private String commandName;
    private boolean terminal = false;
    
	/**
	 * Constructor.
	 * @param escapeContent
	 * @param string 
	 */
	public AttributeNameState(boolean escapeContent, String lookFor, String commandName) {
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
		 case ']':
			 if (terminal){
				 consumer.accept(new CommandToken(commandName, true));
			 } else if (builder.toString().trim().length() > 0){
				 consumer.accept(new AttributeNameToken(builder.toString()));
			 }
			 return new TextBuilderState(escapeContent, lookFor);
		 case '=':
			 if (builder.length() > 0){
				 consumer.accept(new AttributeNameToken(builder.toString()));
			 }
			 return new AttributeValueState(escapeContent, lookFor,commandName);
		 case ' ':
			 if (builder.length() > 0){
				 consumer.accept(new AttributeNameToken(builder.toString()));
			 }
			 return new AttributeNameState(escapeContent, lookFor,commandName);
		 case '/':
			 if (builder.length() == 0){
				 terminal = true;
				 return this;
			 }
		 default:
			 builder.append(c);
			 return this;
		 }
	}

}
