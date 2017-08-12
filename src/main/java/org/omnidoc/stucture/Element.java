/**
 * 
 */
package org.omnidoc.stucture;

import java.util.Map;
import java.util.Set;


/**
 * 
 */
public interface Element {

	public String getName();
	public Iterable<Element> getElements ();
	public Element getParent();
	public void add(Element element);
	public boolean isContainer();
	public boolean isTerminationMandatory();
	/**
	 * @param string
	 */
	public void setDefaultAttribute(String value);
	public void setAttribute(String name, String value);

	public String getAttribute(String name);
	/**
	 * @param abstractElement
	 */
	public void setParent(Element element);
	/**
	 * @return
	 */
	public Set<Map.Entry<String,String>> getAttributes();
	/**
	 * @return
	 */
	public boolean isPlainText();
	/**
	 * @return
	 */
	public boolean hasElements();
	/**
	 * @return
	 */
	public boolean isRootable();
}
