/**
 * 
 */
package org.omnidoc.stucture;


/**
 * 
 */
public class ReferencesCodeElement extends AbstractCommandElement {

	/**
	 * Constructor.
	 * @param name
	 */
	public ReferencesCodeElement() {
		super("references");
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isTerminationMandatory() {
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isRootable() {
		return true;
	}
	

}
