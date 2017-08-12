/**
 * 
 */
package org.omnidoc.stucture;

/**
 * 
 */
public class SectionElement extends AbstractElement{
	
	private int level;
	private ParagraphElement paragraphElement;
	
	public SectionElement(int level){
		this.level = level;
	}

	public int getLevel(){
		return level;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return "section";
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
		return "[Section]";
	}

	public void add(Element element){
		if (element instanceof ParagraphElement) {
			paragraphElement = (ParagraphElement)element;
		}
		super.add(element);
	}
	
	public ParagraphElement getLastParagraf(){
		return paragraphElement;
	}



}
