/**
 * 
 */
package org.omnidoc.xbb.scanner;

/**
 * 
 */
public class ScanPosition {

	private int column = 0;
	private int line = 0;
	/**
	 * 
	 */
	public void incrementColumn() {
		column++;
	}
	/**
	 * 
	 */
	public void incrementLine() {
		line++;
	}

}
