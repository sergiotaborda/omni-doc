/**
 * 
 */
package org.omnidoc.stucture;

/**
 * 
 */
public class ParagraphElement extends AbstractElement{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return "p";
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
		StringBuilder builder = new StringBuilder();
		
		for(Element el : this.getElements()){
			builder.append(el.toString()).append(" ");
		}
		
		return builder.toString();
	}
	
	

}
