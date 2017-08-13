/**
 * 
 */
package org.omnidoc.xbb.syntax;

import java.util.function.Consumer;

import org.omnidoc.stucture.Element;
import org.omnidoc.stucture.ParagraphElement;
import org.omnidoc.stucture.PlainTextElement;
import org.omnidoc.stucture.SectionElement;
import org.omnidoc.xbb.scanner.TextToken;
import org.omnidoc.xbb.scanner.TitleToken;
import org.omnidoc.xbb.scanner.Token;

/**
 * 
 */
public class SectionInitState extends ParseState {

	private Element parent;
	private StringBuilder builder = new StringBuilder();
	private int eol = 0;
	private SectionElement section;

	/**
	 * Constructor.
	 * @param doc
	 */
	public SectionInitState(Element parent) {
		this.parent = parent;
		if (parent instanceof SectionElement){
			this.section = (SectionElement)parent;
		} else if (parent instanceof ParagraphElement){
			this.section = (SectionElement)((ParagraphElement)parent).getParent();
		} 
	}
	
	public SectionInitState(Element parent, SectionElement section) {
		this.parent = parent;
		this.section = section;

	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParseState recieve(ParseContext context, Token token, Consumer<Element> consumer) {
		if (token.isCommand()){
//			if (parent != null && paragraph == null){
//				paragraph = new ParagraphElement();
//				paragraph.setAttribute("text", builder.toString());
//				builder.delete(0,  builder.length()); // reset text
//				parent.add(paragraph);
//				
//			} else if(section != null) {
//				paragraph.setAttribute("text", builder.toString());
//				builder.delete(0,  builder.length()); // reset text
//				section.add(paragraph);
//			}
			Element superElement = parent;
			if (section != null){
				superElement = section;
				if (section.getLastParagraf() !=  null){
					superElement = section.getLastParagraf();
				}
			}
			return new CommandInitState(superElement).recieve(context, token, consumer);
		} else if (token.isText()){
			String text = token.as(TextToken.class).map(t -> t.getText()).orElse("");
			if (section != null){
				section.getLastParagraf().add(new PlainTextElement(text));
			} 
			
			builder.append(text).append(" ");
			
			return this;
		} else if (token.isEOL()){
			eol++;
			if (eol == 2){
				
				if (section != null){
					// end paragraf
					if (builder.length() > 0){
						section.getLastParagraf().setAttribute("text", builder.toString());
						builder.delete(0,  builder.length()); // reset text
					} 
					section.add(new ParagraphElement());
				} else {
					eol = 0;
				}
			
			} 
			
			return this;
		} else if (token.isTitle()){
			eol = 0;
			int level = token.as(TitleToken.class).get().getLevel();
			if (level == 1){
				if (section == null){
					// top section
					Element doc = this.resolveDocument(parent);
					
					section = new SectionElement(level);
					doc.add(section);
					parent = doc;

					return new SectionTitleState(parent, section);
				
				} else {
					// new section
					// terminate paragraf TODO

					// add section to parent
					SectionElement newSection  = new SectionElement(level);
					
					Element doc = this.resolveDocument(section);
					
					doc.add(newSection);
					
					return new SectionTitleState(doc, newSection);
				}
			} else {

				SectionElement subSection  = new SectionElement(level);
				if (section == null){
					Element it = parent;
					while (it != null && !(it instanceof SectionElement)){
						it = it.getParent();
					}
					section = (SectionElement)it;
				}
				section.add(subSection);
				
				return new SectionTitleState(section, subSection);

			}


		}
		return new StandByState(parent);




	}



}
