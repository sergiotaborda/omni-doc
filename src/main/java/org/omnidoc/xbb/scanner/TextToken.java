/**
 * 
 */
package org.omnidoc.xbb.scanner;

/**
 * 
 */
public class TextToken extends Token {

	private String text;

	/**
	 * Constructor.
	 * @param string
	 */
	public TextToken(String text) {
		this.text = text;
	}

	public boolean isText(){
		return true;
	}
	
	public String getText(){
		return text;
	}
	
	public String toString(){
		return text;
	}
}
