/**
 * 
 */
package org.omnidoc.stucture;

/**
 * 
 */
public class SourceCodeElement extends AbstractCommandElement {

	/**
	 * Constructor.
	 * @param name
	 */
	public SourceCodeElement() {
		super("source");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDefaultAttribute(String value) {
		setAttribute("language", value);
	}
	
	public String getLanguage(){
		return getAttribute("language");
	}

	public void setLanguage(String value){
	    setAttribute("language", value);
	}
	
	public String getLegend(){
		return getAttribute("legend");
	}

	public void setLegend(String value){
	    setAttribute("legend", value);
	}
}
