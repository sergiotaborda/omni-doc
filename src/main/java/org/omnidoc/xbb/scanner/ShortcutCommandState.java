package org.omnidoc.xbb.scanner;

import java.util.function.Consumer;

public class ShortcutCommandState extends ParseState{

	
	private char pattern;
	StringBuilder builder = new StringBuilder();

	public ShortcutCommandState(char pattern){
		this.pattern = pattern;
	}
	
	


	@Override
	public ParseState recieve(ScanPosition pos, char c, Consumer<Token> consumer) {
		if ( c == pattern){

				consumer.accept(new CommandToken(String.valueOf(pattern), false));
				if (builder.toString().trim().length() > 0){
					consumer.accept(new TextToken(builder.toString()));
				}
				consumer.accept(new CommandToken(String.valueOf(pattern), true));
				return new TextBuilderState();
			
		
			
		} else {
			builder.append(c);
			return this;
		}
	}

}
