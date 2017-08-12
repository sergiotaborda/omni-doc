/**
 * 
 */
package org.omnidoc.stucture;

/**
 * 
 */
public class ReferenceCodeElement extends AbstractCommandElement {

	/**
	 * Constructor.
	 * @param name
	 */
	public ReferenceCodeElement() {
		super("ref");
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isTerminationMandatory() {
		return true;
	}
	
	@Override
	public void setDefaultAttribute(String value) {
		setAttribute("id", value);
	}
	
	public String getId(){
		return getAttribute("id");
	}

	public void setId(String value){
	    setAttribute("id", value);
	}

}
