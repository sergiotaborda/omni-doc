/**
 * 
 */
package org.omnidoc.html;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.omnidoc.Document;
import org.omnidoc.inputsources.InputSource;
import org.omnidoc.inputsources.OutputStreamSource;
import org.omnidoc.models.DocumentModel;
import org.omnidoc.stucture.Element;
import org.omnidoc.stucture.StructuredDocument;

/**
 * 
 */
public class HtmlDocumentModel implements DocumentModel {

	private HtmlDocumentStyle style = new HtmlDocumentStyle();
	private Map<String, HtmlElementWriter> writers = new HashMap<>();
	
	public HtmlDocumentModel (){
		writers.put("section", new SectionHtmlWriter());
		writers.put("p", new ParagraphHtmlWriter());
		writers.put("wiki", new WikiHtmlWriter());
		writers.put("url", new UrlHtmlWriter());
		writers.put("image", new ImgHtmlWriter());
		writers.put("figure", new FigureImgHtmlWriter());
		writers.put("emph", new TextFormatHtmlWriter());
		writers.put("strong", new TextFormatHtmlWriter());
		writers.put("estrang", new TextFormatHtmlWriter());
		writers.put("source", new SourceCodeHtmlWriter());
		writers.put("references", new ReferencesHtmlWriter());
		writers.put("ref", new RefHtmlWriter());
	}
	
	/**
	 * @param commandTag
	 * @param writer
	 */
	public void AddWriter(String commandTag, HtmlElementWriter writer) {
		writers.put(commandTag, writer);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canRead() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canWrite() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Document readDocument(InputSource source) throws IOException {
		throw new UnsupportedOperationException("Not implememented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeDocument(Document document, OutputStreamSource source) throws IOException {
	
		StructuredDocument doc = (StructuredDocument)document;
		//PrintWriter(File file, String csn)
		PrintWriter writer = source.getPrintWriter();
		writer.println("<html>");
		writer.println("<head>");
		writer.append("		<meta http-equiv=\"content-type\" content=\"text/html; charset=ISO-8859-1\" />\n");
		writer.append("		<meta http-equiv=\"content-language\" content=\"pt\" />\n");

		String title = doc.getTitle();
		
		if (title != null){
			writer.append("<title>").append(title).append("</title>");
		}
		
		writer.println("</head>");
		writer.println("<body>");
		for (Element el : doc.getElements()){
			writeElement(el, writer,0);
		}
		writer.println("</body>");
		writer.println("</html>");
		writer.close();
	}
	
	public void writeFragment(Document document, OutputStreamSource source) throws IOException {
	

		PrintWriter writer = new PrintWriter(source.getOutputStream());

		writeElement((StructuredDocument)document, writer, 0);
		writer.close();
	}

	/**
	 * @param document
	 * @param writer
	 */
	public void writeElement(Element element, PrintWriter writer, int tab) {
		
		HtmlElementWriter htmlWriter = writers.get(element.getName());
		
		if (htmlWriter == null){
			writeGenericElement(element, writer, tab);
		} else {
			htmlWriter.writeGenericElement(this, element, writer, tab);
		}
		
	}
	
    private void writeGenericElement(Element element, PrintWriter writer, int tab) {
		
		StringBuilder builder = new StringBuilder();
		
		for (int i =0; i < tab; i++){
			builder.append("\t");
		}
		
		writer.append(builder.toString()).append("<").append(element.getName());
		
		for (Map.Entry<String,String> attrib : element.getAttributes()){
			writer.append(" ").append(attrib.getKey()).append("=\"").append(attrib.getValue()).append("\"");
		}
		
		style.Write(element, writer);
		
		writer.append(">\n");
		
		for (Element el : element.getElements()){
			writeElement(el, writer, tab+1);
		}
		
		writer.append(builder.toString()).append("</").append(element.getName()).append(">\n");
		
	}

	/**
	 * @param el
	 */
	public HtmlElementWriter getWriter(Element el) {
		return writers.get(el.getName());
		
	}



}
