package org.omnidoc.formula;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.transform.dom.DOMSource;

import net.sourceforge.jeuclid.MathBase;
import net.sourceforge.jeuclid.converter.Converter;
import net.sourceforge.jeuclid.parser.MathBaseFactory;

import org.omnidoc.BinaryDocument;
import org.omnidoc.Document;
import org.omnidoc.DocumentTransformer;
import org.omnidoc.process.DocumentSpace;
import org.omnidoc.xml.XMLDOMDocument;
import org.omnidoc.xml.XMLUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FormulaTransformer implements DocumentTransformer{

	int formulaIndex = 0; 
	
	@Override
	public boolean canTransform(Document doc) {
		return doc instanceof XMLDOMDocument;
	}
	
	@Override
	public void transform(Document doc, DocumentSpace target) {

		try {

		
			XMLDOMDocument xmlDoc = (XMLDOMDocument)doc.duplicate();
			target.addDocument(xmlDoc);
			
			org.w3c.dom.Document xdoc = xmlDoc.asXMLDocument();
			
			NodeList list = xdoc.getElementsByTagName("math");
			MathBaseFactory factory = MathBaseFactory.getMathBaseFactory();
		
			for (int i =0;i < list.getLength(); i++){
				formulaIndex++;
				Node node = list.item(i);
				MathBase base =factory.createMathBase(new DOMSource(node), MathBase.getDefaultParameters());
		
				formulaIndex++;
				BinaryDocument pic = documentFor(base,formulaIndex);
				target.addDocument(pic);
				
				Node imgNode = XMLUtils.parseXml(xdoc, "<math><img src=\"" + pic.getName() + "\" /></math>");
				Node replaceNodeParent = node.getParentNode();
				replaceNodeParent.replaceChild(imgNode, node);
			}
			
		    list = xdoc.getElementsByTagName("formula");
			
			for (int i =0;i < list.getLength(); i++){
				
				Node node = list.item(i);
				
				MathBase base = factory.createMathBase(new DOMSource(node), MathBase.getDefaultParameters());
				
				formulaIndex++;
				BinaryDocument pic = documentFor(base,formulaIndex);
				target.addDocument(pic);
				
				Node imgNode = XMLUtils.parseXml(xdoc, "<formula><img src=\"" + pic.getName() + "\" /></formula>");
				Node replaceNodeParent = node.getParentNode();
				replaceNodeParent.replaceChild(imgNode, node);
			}
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}


	private BinaryDocument documentFor (MathBase base , int formulaIndex) throws IOException{
	
		String name = "eq" + formulaIndex + ".png";
		
		BinaryDocument pic = new BinaryDocument( name, "image/png");
		
		BufferedImage img = Converter.getConverter().render(base);
		ImageIO.write(img, "png", pic.getContent().getOutputStream());
		
		return pic;
	}




}
