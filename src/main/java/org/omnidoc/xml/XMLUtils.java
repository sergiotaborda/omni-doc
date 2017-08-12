package org.omnidoc.xml;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.middleheaven.io.xml.XMLAttributemissingException;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLUtils {

	   public static DocumentFragment parseXml(Document doc, String fragment) {
	        // Wrap the fragment in an arbitrary element
	        fragment = "<fragment>"+fragment+"</fragment>";
	        try {
	            // Create a DOM builder and parse the fragment
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            factory.setExpandEntityReferences(true);
	            factory.setNamespaceAware(false);
	            factory.setValidating(false);
	            factory.setXIncludeAware(false);
	            factory.setIgnoringComments(true);
	            Document d = factory.newDocumentBuilder().parse(
	                new InputSource(new StringReader(fragment)));
	    
	            // Import the nodes of the new document into doc so that they
	            // will be compatible with doc
	            Node node = doc.importNode(d.getDocumentElement(), true);
	    
	            // Create the document fragment node to hold the new nodes
	            DocumentFragment docfrag = doc.createDocumentFragment();
	    
	            // Move the nodes into the fragment
	            while (node.hasChildNodes()) {
	                docfrag.appendChild(node.removeChild(node.getFirstChild()));
	            }
	    
	            // Return the fragment
	            return docfrag;
	        } catch (SAXException e) {
	            // A parsing error occurred; the xml input is not valid
	        	throw new RuntimeException(e);
	        } catch (ParserConfigurationException e) {
	         	throw new RuntimeException(e);
	        } catch (IOException e) {
	         	throw new RuntimeException(e);
	        }
	    }


	    public static final boolean booleanAttribute(String attribName , Element el, boolean required, boolean defaultValue)  {
	        String val = el.getAttribute(attribName);
	        if (val==null || val.length()==0){
	            if (required){
	                throw new RuntimeException( attribName + " " + el.getLocalName() );
	            }else{
	                return defaultValue;
	            }
	        }else {
	            return ("yes".equals(val) ||"on".equals(val) || "true".equals(val));
	        }
	    }

	    public static final boolean booleanAttribute(String attribName , Element el, boolean defaultValue)  {
	        return booleanAttribute(attribName,el,false,defaultValue);
	    }
	    
	    public static final boolean booleanAttribute(String attribName , Element el) {
	        return booleanAttribute(attribName,el,true,false);
	    }

	    
	    public static final Boolean getBooleanAttribute(String attribName,Element el){
	        String val = el.getAttribute(attribName);
	        if (val==null || val.length()==0){
	            return null;
	        }else {
	            return new Boolean(booleanAttribute(attribName, el, false));
	        }
	    }


	    /**
	     * Return a string attribute
	     * @param attribName
	     * @param el
	     * @param defaultValue
	     * @return
	     * @throws XMLAttributemissingException
	     */
	    public static final String getStringAttribute(String attribName , Node el, String defaultValue) {
	        Node val = el.getAttributes().getNamedItem(attribName);
	        if (val==null || val.getTextContent().isEmpty()){
	            if (defaultValue == null){
	                throw new RuntimeException(attribName + " " +  el.getLocalName());
	            }else{
	                return defaultValue;
	            }
	        }else {
	            return val.getTextContent();
	        }
	    }

	    public static final String getStringAttribute(String attribName , Node el) {
	        return getStringAttribute(attribName,el,null);
	    }



		public static Node getChildNode(String name, Node node) {
			if (node ==null){
				return null;
			}
			NodeList list = node.getChildNodes();
			for (int i=0; i < list.getLength(); i++){
				Node no = list.item(i);
				if (no.getNodeName().equalsIgnoreCase(name)){
					return no;
				}
			}
			return null;
		}

	   
}
