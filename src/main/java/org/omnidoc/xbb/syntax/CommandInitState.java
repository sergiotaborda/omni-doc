/**
 * 
 */
package org.omnidoc.xbb.syntax;

import java.util.function.Consumer;

import org.omnidoc.stucture.Element;
import org.omnidoc.stucture.EmphasisTextElement;
import org.omnidoc.stucture.FigureElement;
import org.omnidoc.stucture.GenericCommandElement;
import org.omnidoc.stucture.ImageElement;
import org.omnidoc.stucture.OtherLanguageTextElement;
import org.omnidoc.stucture.ParagraphElement;
import org.omnidoc.stucture.PlainTextElement;
import org.omnidoc.stucture.ReferenceCodeElement;
import org.omnidoc.stucture.ReferencesCodeElement;
import org.omnidoc.stucture.SourceCodeElement;
import org.omnidoc.stucture.StrongTextElement;
import org.omnidoc.stucture.UrlElement;
import org.omnidoc.stucture.WikiWordElement;
import org.omnidoc.xbb.scanner.AttributeNameToken;
import org.omnidoc.xbb.scanner.AttributeValueToken;
import org.omnidoc.xbb.scanner.CommandToken;
import org.omnidoc.xbb.scanner.TextToken;
import org.omnidoc.xbb.scanner.Token;

/**
 * 
 */
public class CommandInitState extends SyntaxState {

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
	public SyntaxState recieve(Token token, Consumer<Element> consumer) {
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
			
			command = createCommand(t.getName());

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
			return new StandByState(parent == null ? command : parent).recieve(token, consumer);
		}
	}

	/**
	 * @param name2
	 * @return
	 */
	private Element createCommand(String name) {
		if (name.equalsIgnoreCase("url")){
			return new UrlElement();
		} else 	if (name.equalsIgnoreCase("source")){
			return new SourceCodeElement();	
	    } else if (name.equalsIgnoreCase("ref")){
			return new ReferenceCodeElement();
		} else if (name.equalsIgnoreCase("references")){
			return new ReferencesCodeElement();
		} else if (name.equalsIgnoreCase("b") || name.equalsIgnoreCase("strong")){
			return new StrongTextElement();
		} else if (name.equalsIgnoreCase("i") || name.equalsIgnoreCase("emph")){
			return new EmphasisTextElement();
		} else if (name.equalsIgnoreCase("e") || name.equalsIgnoreCase("estrang")){
			return new OtherLanguageTextElement();
		}else if (name.equalsIgnoreCase("w") || name.equalsIgnoreCase("wiki")){
			return new WikiWordElement();
		}else if (name.equalsIgnoreCase("k") || name.equalsIgnoreCase("keyworkd")){
			return new WikiWordElement();
		} else if (name.equalsIgnoreCase("fig")){
			return new FigureElement();
		} else if (name.equalsIgnoreCase("img")){
			return new ImageElement();
		} 
		return new GenericCommandElement(name);
	}

}
