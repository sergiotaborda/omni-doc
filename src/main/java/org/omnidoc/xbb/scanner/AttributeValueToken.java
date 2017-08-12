/**
 * 
 */
package org.omnidoc.xbb.scanner;


/**
 * 
 */
public class AttributeValueToken extends Token {


	private String value;


	/**
	 * Constructor.
	 * @param string
	 */
	public AttributeValueToken(String value) {
	  this.value = value;
	}

	public String getValue(){
		return value;
	}

	public String toString(){
		return value;
	}
}
