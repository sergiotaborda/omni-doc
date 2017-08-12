/**
 * 
 */
package org.omnidoc.bb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.omnidoc.process.DocumentProcessor;
import org.omnidoc.xml.XSLTransfomer;

/**
 * 
 */
public class TestXML2BBConverter {

	
	@Test
	public void convert() throws FileNotFoundException, TransformerException{

		File folder = new File("./src/test/resources/org/omnidoc/xml").getAbsoluteFile();
		File folderOut = new File("./src/test/resources/org/omnidoc/bb").getAbsoluteFile();
		
		File templateFile = new File("./src/test/resources/org/omnidoc/xstl/transformationBB.xsl").getAbsoluteFile();
		
		DocumentProcessor processor = DocumentProcessor.createProcessor();


		processor
		.add(XSLTransfomer.forInputStream(new FileInputStream(templateFile)));
		
		for (File f : folder.listFiles()){
			File out = new File(folderOut, f.getName());

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer( new StreamSource( templateFile ) );
			transformer.setOutputProperty(OutputKeys.INDENT, "no");    
			
	
			StreamSource source = new StreamSource(f);
			StreamResult result = new StreamResult(out);
		
			transformer.transform(source, result);
			
			
		}
		
		
		
	}
}
