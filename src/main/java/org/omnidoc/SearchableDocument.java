package org.omnidoc;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.omnidoc.xml.XMLDOMDocument;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SearchableDocument extends XMLDOMDocument  {

	Node rootNode;
	Document other;
	XPath xpath;

	public SearchableDocument(XMLDOMDocument other){
		super(other.asXMLDocument(), other.getName());
		
		this.other = other;
		this.rootNode = other.asXMLDocument().getFirstChild();
		XPathFactory factory = XPathFactory.newInstance();
		xpath = factory.newXPath();
	}


	public String find(String expression){

		try {

			XPathExpression xPathexpression = xpath.compile(expression);

			return xPathexpression.evaluate(rootNode);
		} catch (XPathExpressionException e) {
			throw new RuntimeException(e);
		}

	}

	public Node findNode(String expression){

		try {

			XPathExpression xPathexpression = xpath.compile(expression);

			return (Node)xPathexpression.evaluate(rootNode, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			throw new RuntimeException(e);
		}

	}

	public NodeList findNodeList(String expression){

		try {
			XPathExpression xPathexpression = xpath.compile(expression);
			return (NodeList)xPathexpression.evaluate(rootNode, XPathConstants.NODESET);

		} catch (XPathExpressionException e) {
			throw new RuntimeException(e);
		}

	}


	public Iterable<Node> findNodeChildren(String expression){

		try {
			XPathExpression xPathexpression = xpath.compile(expression);
			NodeList obj = (NodeList)xPathexpression.evaluate(rootNode, XPathConstants.NODESET);

			if ( obj == null){
				return new NodeListIteable(null);
			} else {
				return new NodeListIteable((NodeList)obj);
			}
				
			
		} catch (XPathExpressionException e) {
			throw new RuntimeException(e);
		}

	}

	private static Iterable<Node> drill (String [] path , int index , Node parent ){

		NodeList list = parent.getChildNodes();

		if (list==null || index == path.length){
			return new NodeListIteable(list);
		}

		for (int i=0; i < list.getLength() ; i++){
			if (list.item(i).getNodeName().equals(path[index])){
				return drill(path, index+1, list.item(i));
			}
		}
		return new NodeListIteable(null);
	}

	
	public Document duplicate(){
		return new SearchableDocument((XMLDOMDocument)other.duplicate());	
	}
}
