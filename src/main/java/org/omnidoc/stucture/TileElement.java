/**
 * 
 */
package org.omnidoc.stucture;


/**
 * 
 */
public class TileElement extends AbstractElement {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return "title";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isContainer() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isTerminationMandatory() {
		return false;
	}

	public int getLevel(){
		return Integer.parseInt(getAttribute("level"));
	}
	
	public String getText(){
		return getAttribute("text");
	}
	
	public String toString(){
		return getAttribute("text");
	}




}
