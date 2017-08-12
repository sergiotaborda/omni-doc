/**
 * 
 */
package org.omnidoc.stucture;

import java.util.Map;

/**
 * 
 */
public abstract class AbstractCommandElement extends AbstractElement {


	private String name;

	public AbstractCommandElement (String name){
		this.name = name;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
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
		StringBuilder builder = new StringBuilder(name);
	
		for (Map.Entry<String, String> it : attributes.entrySet()){
			builder.append(" ").append(it.getKey()).append("=").append(it.getValue());
		}
		return builder.toString();
	}

}
