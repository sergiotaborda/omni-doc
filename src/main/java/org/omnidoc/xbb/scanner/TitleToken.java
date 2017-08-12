/**
 * 
 */
package org.omnidoc.xbb.scanner;


/**
 * 
 */
public class TitleToken extends Token {

	
	private int level;

	public TitleToken(int level){
		this.level = level;
	}
	
	
	public int getLevel(){
		return level;
	}
	
	/**
	 * @return
	 */
	public boolean isTitle() {
		return true;
	}
}
