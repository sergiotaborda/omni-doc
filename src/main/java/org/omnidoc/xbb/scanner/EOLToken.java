/**
 * 
 */
package org.omnidoc.xbb.scanner;


/**
 * 
 */
public class EOLToken extends Token {

	public boolean isEOL(){
		return true;
	}
	
	public String toString(){
		return "\\n";
	}
}
