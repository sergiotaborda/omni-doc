package org.omnidoc.hightlight;

import java.io.StringWriter;

import org.omnidoc.Document;
import org.omnidoc.DocumentTransformer;
import org.omnidoc.process.DocumentSpace;
import org.omnidoc.xml.XMLDOMDocument;
import org.omnidoc.xml.XMLUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.java2html.converter.JavaSource2HTMLConverter;
import de.java2html.javasource.JavaSource;
import de.java2html.javasource.JavaSourceParser;
import de.java2html.options.JavaSourceConversionOptions;
import de.java2html.options.JavaSourceStyleTable;

public class SourceCodeColouringTransformer implements DocumentTransformer {

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

			NodeList list = xdoc.getElementsByTagName("source");


			JavaSourceConversionOptions options = JavaSourceConversionOptions.getDefault();
			options.setShowLineNumbers(true);
			JavaSourceStyleTable table = JavaSourceStyleTable.getDefaultKawaStyleTable();
	
			options.setStyleTable(table);

			JavaSourceParser parser = new JavaSourceParser( options); 
			JavaSource2HTMLConverter converter = new JavaSource2HTMLConverter();

			for (int i =0;i < list.getLength(); i++){
				Node node = list.item(i);
				
				String code = node.getTextContent();
				JavaSource source = parser.parse(code);
				
				StringWriter writer = new StringWriter();
				converter.convert(source, options, writer); 
				
				writer.close();
				
				
				code = writer.toString()
				.replaceAll("&eacute;", "é")
				.replaceAll("&ccedil;", "ç")
				.replaceAll("&aacute;", "á")
				.replaceAll("&oacute;", "ó")
				.replaceAll("&uacute;", "ú")
				.replaceAll("&agrave;", "à")
				.replaceAll("&nbsp;", "&#160;");
				
				
				Node imgNode = XMLUtils.parseXml(xdoc, "<source>" + code.replaceAll("<code>", "").replaceAll("</code>", "") + "</source>");
				Node replaceNodeParent = node.getParentNode();
				replaceNodeParent.replaceChild(imgNode, node);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}



}
