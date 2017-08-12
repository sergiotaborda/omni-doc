/**
 * 
 */
package org.omnidoc.stucture;

/**
 * 
 */
public class PlainTextElement extends AbstractElement {

	
	public PlainTextElement(String text){
		this.setAttribute("text", text);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return "plaintext";
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
		return false;
	}

	public String toString(){
		return getAttribute("text");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPlainText() {
		return true;
	}
}
