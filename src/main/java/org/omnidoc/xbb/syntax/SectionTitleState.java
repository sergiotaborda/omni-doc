package org.omnidoc.xbb.syntax;

import java.util.function.Consumer;

import org.omnidoc.stucture.Element;
import org.omnidoc.stucture.SectionElement;
import org.omnidoc.xbb.scanner.Token;

public class SectionTitleState extends ParseState {

	private SectionElement section;
	private int eolCount = 0;
	private StringBuilder title = new StringBuilder();
	private Element parent;
	
	public SectionTitleState(Element parent, SectionElement section) {
		this.section = section;
		this.parent = parent;
	}

	@Override
	public ParseState recieve(ParseContext context, Token token, Consumer<Element> consumer) {
		 if (token.isEOL()){
			 eolCount++;
			 if (eolCount >= 2){
				 section.setAttribute("title", title.toString());
				 return new SectionInitState(parent, section);
			 }
			 return this;
		 } else {
			 if (title.length() !=0){
				 title.append(" ");
			 }
			 title.append(token.toString());
			 return this;
		 }
	}

}
