/**
 * 
 */
package org.omnidoc.xbb.syntax;

import java.util.function.Consumer;

import org.omnidoc.stucture.Element;
import org.omnidoc.stucture.ParagraphElement;
import org.omnidoc.stucture.PlainTextElement;
import org.omnidoc.xbb.scanner.AttributeNameToken;
import org.omnidoc.xbb.scanner.AttributeValueToken;
import org.omnidoc.xbb.scanner.CommandToken;
import org.omnidoc.xbb.scanner.TextToken;
import org.omnidoc.xbb.scanner.Token;

/**
 * 
 */
public class CommandInitState extends ParseState {

	private Element parent;
	private Element command;
	private String name = "";

	/**
	 * Constructor.
	 * @param doc
	 */
	public CommandInitState(Element parent) {
		this.parent = parent;
	}

	public CommandInitState(Element parent,Element command) {
		this.parent = parent;
		this.command = command;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParseState recieve(ParseContext context, Token token, Consumer<Element> consumer) {
		if (token.isCommand()){
			CommandToken t = token.as(CommandToken.class).get();

			if (command != null){
				if (t.isTerminator()){
					return new StandByState(parent);
				}
			} else {
				if (t.isTerminator()){
					return new StandByState(parent);
				}
			}
			
			command = context.getCommandFromElementFactory().createCommand(t.getName());

			if (command.isRootable()){
				parent = this.resolveDocument(parent);
				parent.add(command);
				parent = null;
			} else {
				parent.add(command);
			}
				
			
			

			return this;
		} else if (token.as(AttributeNameToken.class).isPresent()){
			name =  token.as(AttributeNameToken.class).get().getName();
			return this;
		} else if (token.as(AttributeValueToken.class).isPresent()){

			if (name.length() > 0){
				command.setAttribute(name, token.as(AttributeValueToken.class).get().getValue());
			} else {
				command.setDefaultAttribute(token.as(AttributeValueToken.class).get().getValue());
			}
			return this;
		} else if (token.isText()){
			if (command.hasElements()){
				
				command.getElements().iterator().next().add(new PlainTextElement(((TextToken)token).getText()));
			} else {
				ParagraphElement paragraph = new ParagraphElement();
				
				paragraph.add(new PlainTextElement(((TextToken)token).getText()));
				
				command.add(paragraph);
			}
		
			
			return this;
		} else {
			return new StandByState(parent == null ? command : parent).recieve(null, token, consumer);
		}
	}



}
