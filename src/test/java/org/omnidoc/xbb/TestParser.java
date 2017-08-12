/**
 * 
 */
package org.omnidoc.xbb;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.omnidoc.Document;
import org.omnidoc.html.HtmlDocumentModel;
import org.omnidoc.inputsources.FileSource;

/**
 * 
 */
public class TestParser {

	@Test
	public void test() throws IOException {

		
		File f = new File("./src/test/resources/org/omnidoc/xbb/test.xbb").getAbsoluteFile();
		File h = new File("./src/test/resources/org/omnidoc/xbb/test.html").getAbsoluteFile();
		
		XbbParser parser = new XbbParser();
		
		Document doc = parser.parse(new FileSource(f));
		
		assertNotNull(doc);
		
		HtmlDocumentModel model = new HtmlDocumentModel();
		
		// add costum command rendered
		model.AddWriter("art" , (m, element, writer, tab) -> {
			writer.append("<i> art n.º").append(element.getAttribute("n")).append(" do ").append (element.getAttribute("livro")).append("</i>");
		});
		
		model.writeDocument(doc, new FileSource(h));

	}

	@Test
	public void testArticle() throws IOException {

		
		File f = new File("./src/test/resources/org/omnidoc/xbb/velocidadeDaLuz.xbb").getAbsoluteFile();
		File h = new File("./src/test/resources/org/omnidoc/xbb/velocidadeDaLuz.html").getAbsoluteFile();
		
		XbbParser parser = new XbbParser();
		
		Document doc = parser.parse(new FileSource(f));
		
		assertNotNull(doc);
		
		HtmlDocumentModel model = new HtmlDocumentModel();
		
		// add costum command rendered
//		model.AddWriter("art" , (m, element, writer, tab) -> {
//			writer.append("<i> art n.º").append(element.getAttribute("n")).append(" do ").append (element.getAttribute("livro")).append("</i>");
//		});
		
		model.writeDocument(doc, new FileSource(h));

	}

}
