/**
 * 
 */
package org.omnidoc.xbb.scanner;


/**
 * 
 */
public class AttributeNameToken extends Token {

	private String name;

	/**
	 * Constructor.
	 * @param string
	 */
	public AttributeNameToken(String name) {
		this.name = name;
	}

	public String getName(){
		return name;
	}
	
	public String toString(){
		return name;
	}
}
