/**
 * 
 */
package org.omnidoc.stucture;

import org.omnidoc.Document;

/**
 * 
 */
public class StructuredDocument extends AbstractCommandElement implements Document , Element {

	

	private String sourceName;

	public StructuredDocument(String sourceName){
		super("doc");
		this.sourceName = sourceName;
	}
	
	public String getTitle(){
		return getAttribute("title");
	}

	public void setTitle(String value){
	    setAttribute("title", value);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return sourceName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Document duplicate() {
		return  this; // TODO
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isRootable() {
		return false;
	}



}
