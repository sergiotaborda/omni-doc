/**
 * 
 */
package org.omnidoc.stucture;

/**
 * 
 */
public class ImageElement extends AbstractCommandElement{

	/**
	 * Constructor.
	 * @param name
	 */
	public ImageElement() {
		this("image");
	}
	
	protected ImageElement(String name) {
		super(name);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDefaultAttribute(String value) {
		setAttribute("source", value);
	}
	
	public String getSource(){
		return getAttribute("source");
	}

	public void setSource(String value){
	    setAttribute("source", value);
	}

}
