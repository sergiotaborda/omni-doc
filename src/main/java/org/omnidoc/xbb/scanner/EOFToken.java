/**
 * 
 */
package org.omnidoc.xbb.scanner;


/**
 * 
 */
public class EOFToken extends Token {

	public boolean isEOF(){
		return true;
	}
	
	public String toString(){
		return "EOF";
	}
}
