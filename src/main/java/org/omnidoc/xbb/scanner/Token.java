/**
 * 
 */
package org.omnidoc.xbb.scanner;

import java.util.Optional;

/**
 * 
 */
public abstract class Token {

	
	public boolean isEOF(){
		return false;
	}
	
	
	public boolean isEOL(){
		return false;
	}
	
	public boolean isText(){
		return false;
	}


	/**
	 * @return
	 */
	public boolean isCommand() {
		return false;
	}

	public <T> Optional<T> as(Class<T> type){
		if (type.isInstance(this)){
			return Optional.of(type.cast(this));
		} else {
			return Optional.empty();
		}
	}


	/**
	 * @return
	 */
	public boolean isTitle() {
		return false;
	}

}
