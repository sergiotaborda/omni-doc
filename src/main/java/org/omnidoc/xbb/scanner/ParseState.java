/**
 * 
 */
package org.omnidoc.xbb.scanner;

import java.util.function.Consumer;

/**
 * 
 */
public abstract class ParseState {

	/**
	 * @param pos
	 * @param consumer
	 */
	public void clear(ScanPosition pos, Consumer<Token> consumer) {}

	/**
	 * @param pos
	 * @param c
	 * @param consumer
	 * @return
	 */
	public abstract ParseState recieve(ScanPosition pos, char c, Consumer<Token> consumer);

}
