package org.omnidoc;

import java.util.Iterator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodeListIteable implements Iterable<Node> {

	NodeList list;
	
	public NodeListIteable(NodeList list) {
		this.list = list;
	}

	@Override
	public Iterator<Node> iterator() {
		return new NodeIterator();
	}

	private class NodeIterator implements Iterator<Node>{

		int index=-1;
		private NodeIterator(){}
		
		@Override
		public boolean hasNext() {
			return index < list.getLength() - 1;
		}

		@Override
		public Node next() {
			return list.item(++index);
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
}
