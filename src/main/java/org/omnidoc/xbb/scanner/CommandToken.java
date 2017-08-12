/**
 * 
 */
package org.omnidoc.xbb.scanner;


/**
 * 
 */
public class CommandToken extends Token {

	private String name;
	private boolean terminator;

	/**
	 * Constructor.
	 * @param string
	 * @param terminator 
	 */
	public CommandToken(String name, boolean terminator) {
		this.name = name;
		this.terminator = terminator;
	}

	/**
	 * @return
	 */
	public boolean isCommand() {
		return true;
	}
	
	/**
	 * @return
	 */
	public CommandToken asCommand() {
		return this;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean isTerminator(){
		return terminator;
	}
	
	public String toString(){
		return name;
	}
}
