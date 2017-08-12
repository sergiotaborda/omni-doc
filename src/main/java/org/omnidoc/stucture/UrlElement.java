/**
 * 
 */
package org.omnidoc.stucture;


/**
 * 
 */
public class UrlElement extends AbstractCommandElement  {

	/**
	 * Constructor.
	 * @param name
	 */
	public UrlElement() {
		super("url");
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isContainer() {
		return true;
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
	public void setDefaultAttribute(String value) {
		setAttribute("href", value);
	}
	
	public String getHref(){
		return getAttribute("href");
	}

	public void setHref(String value){
	    setAttribute("href", value);
	}

}
