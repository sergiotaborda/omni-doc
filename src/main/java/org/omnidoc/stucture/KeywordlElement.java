/**
 * 
 */
package org.omnidoc.stucture;


/**
 * 
 */
public class KeywordlElement extends AbstractCommandElement  {

	/**
	 * Constructor.
	 * @param name
	 */
	public KeywordlElement() {
		super("keyword");
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
		setAttribute("keyword", value);
	}
	
	public String getKeyword(){
		String k = getAttribute("keyword");
		if (k == null){
			return ""; // TODO read inner words
		}
		return k;
	}

	public void setKeyword(String value){
	    setAttribute("keyword", value);
	}

	
}
