/**
 * 
 */
package org.omnidoc.xbb.scanner;

import java.util.function.Consumer;

/**
 * 
 */
public class TitleState extends ParseState {


	private char titleChar;
	public TitleState (char titleChar){
		this.titleChar = titleChar;
	}
	int count = 1;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParseState recieve(ScanPosition pos, char c, Consumer<Token> consumer) {
		if (c == titleChar){
			count++;
			return this;
		} else if (c == '\n' || c == '\r'){
			if (count >=3){
				int level = (titleChar == '-' ? 1 : (titleChar == '=' ? 2 : 3));
				consumer.accept(new TitleToken(level));
				return new TextBuilderState();
			} 
			StringBuilder builder = new StringBuilder();
			for (int i =0; i < count; i++){
				builder.append(titleChar);
			}
			return new TextBuilderState(builder.toString());

		} else {

			StringBuilder builder = new StringBuilder();
			for (int i =0; i < count; i++){
				builder.append(titleChar);
			}
			builder.append(c);
			return new TextBuilderState(builder.toString());

		}

	}

}
