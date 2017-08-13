/**
 * 
 */
package org.omnidoc.xbb.scanner;

import java.util.function.Consumer;

import org.omnidoc.xbb.syntax.IllegalCharacterException;

/**
 * 
 */
public class TextBuilderState extends ParseState {

	StringBuilder builder = new StringBuilder();
	StringBuilder escaped = new StringBuilder();
	private boolean escapeContent = false;
	private String lookFor = null;


	public TextBuilderState(){}

	public TextBuilderState(String text){
		builder.append(text);
	}
	/**
	 * Constructor.
	 * @param escapeContent
	 */
	public TextBuilderState(boolean escapeContent, String lookFor) {
		this.escapeContent = escapeContent;
		this.lookFor  = lookFor;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParseState recieve(ScanPosition pos, char c, Consumer<Token> consumer) {
		switch (c){
		case '#':
			return new InlineTitleState();
		case '-':
			if (builder.length() == 0){
				return new TitleState('-');
			} else {
				if (escapeContent){
					escaped.append(c);
				} else {
					builder.append(c);
				}
				return this;
			}
		case '=':
			if (builder.length() == 0){
				return new TitleState('=');
			} else {
				if (escapeContent){
					escaped.append(c);
				} else {
					builder.append(c);
				}
				return this;
			}
		case '~':
			if (builder.length() == 0){
				return new TitleState('~');
			} else {
				if (escapeContent){
					escaped.append(c);
				} else {
					builder.append(c);
				}
				return this;
			}
		case '[':
			if (escapeContent){
				escaped.append(c);

				return this;
			} else {
				if (builder.toString().trim().length() > 0){
					consumer.accept(new TextToken(builder.toString()));
					builder.delete(0, builder.length());
				}
				return new CommandState();
			}
		case ']':
			if (escapeContent){
				String end = escaped.substring(Math.max(0,escaped.length()- lookFor.length()));
				if (end.equalsIgnoreCase(lookFor)){
					String text = escaped.substring(0, escaped.lastIndexOf("["));
					consumer.accept(new TextToken(text));
					consumer.accept(new CommandToken(lookFor, true));
					escaped.delete(0, escaped.length());
					escapeContent = false;
					lookFor = null;
				} else {
					escaped.append(c);	
				}
				return this;
			} else {
				throw new IllegalCharacterException(pos);
			}
		case ' ':
			if (escapeContent){
				escaped.append(c);
			} else {
				// end word
				String s = builder.toString();
				if (s.trim().length() > 0){
					consumer.accept(new TextToken(s));
					builder.delete(0, builder.length());
				}
			}
			return this;
		case '\n':
			if (escapeContent){
				escaped.append(c);
			} else {
				// end word
				if (builder.toString().trim().length() > 0){
					consumer.accept(new TextToken(builder.toString()));
				}
				consumer.accept(new EOLToken());
				builder.delete(0, builder.length());
			}
			return this;
		case '\t':
			if (escapeContent){
				escaped.append(c);
			} 
		case '\r':
			return this;
		case '*':
		case '\\':
		case '`':
		case '\'':
			return new ShortcutCommandState(c);
		default:
			if (escapeContent){
				escaped.append(c);
			} else {
				builder.append(c);
			}
			return this;
		}
	}

	public void clear(ScanPosition pos, Consumer<Token> consumer) {
		consumer.accept(new TextToken(builder.toString()));
		builder.delete(0, builder.length());
	}

}
