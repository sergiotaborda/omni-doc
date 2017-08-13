package org.omnidoc.xbb.scanner;

import java.util.function.Consumer;

public class InlineTitleState extends ParseState {

	int levelCount = 1;
	private boolean counting = true;
	StringBuilder buffer = new StringBuilder();
	
	@Override
	public ParseState recieve(ScanPosition pos, char c, Consumer<Token> consumer) {
		switch (c){
		case '#':
			if (counting){
				levelCount++;
			}
			return this;
		case ' ':
			counting = false;
			return this;
		case '\n':
		case '\r':
			consumer.accept(new TitleToken(levelCount));
			return new TextBuilderState();

			
		default:
			consumer.accept(new TitleToken(levelCount));
			return new TextBuilderState().recieve(pos, c, consumer);
		}
	}

}
