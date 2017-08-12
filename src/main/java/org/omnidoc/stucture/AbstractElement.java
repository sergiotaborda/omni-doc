/**
 * 
 */
package org.omnidoc.stucture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



/**
 * 
 */
public abstract class AbstractElement implements Element {

	private List<Element> elements = new ArrayList<>();
	protected Map<String, String> attributes = new HashMap<>();
	private Element parent;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isRootable() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasElements() {
		return !elements.isEmpty();
	}

	public void removeTextAtEnd(String text){
		String[] split = text.split(" ");
		outter:for (int i = split.length - 1; i >=0 ; i--)
		{
			for (int j =elements.size() - 1; j >=0 ; j-- ){
				Element el = elements.get(j);
				if (!el.isPlainText()){
					return;
				}
				if (el.isPlainText() && el.getAttribute("text").equals(split[i])){
					elements.remove(j);
					continue outter;
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Entry<String, String>> getAttributes() {
		return attributes.entrySet();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<Element> getElements() {
		return elements;
	}


	public void add(Element element){
		element.setParent(this);
		elements.add(element);
	}


	public void setParent(Element element){
		this.parent= element;
	}

	public Element getParent(){
		return parent;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDefaultAttribute(String value) {
		setAttribute("default", value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAttribute(String name, String value) {
		attributes.put(name,  value);
	}


	/**
	 * @param string
	 * @return
	 */
	public String getAttribute(String name) {
		return attributes.get(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPlainText() {
		return false;
	}

}
