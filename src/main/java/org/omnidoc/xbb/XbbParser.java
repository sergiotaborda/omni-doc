/**
 * 
 */
package org.omnidoc.xbb;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import org.omnidoc.Document;
import org.omnidoc.inputsources.InputSource;
import org.omnidoc.stucture.Element;
import org.omnidoc.stucture.StructuredDocument;
import org.omnidoc.xbb.scanner.Scanner;
import org.omnidoc.xbb.scanner.Token;
import org.omnidoc.xbb.syntax.CommandFromElementFactory;
import org.omnidoc.xbb.syntax.DocumentInitState;
import org.omnidoc.xbb.syntax.ParseContext;
import org.omnidoc.xbb.syntax.ParseState;

/**
 * 
 */
public class XbbParser {

	CommandFromElementFactory factory = new CommandFromElementFactory();
	
	public XbbParser(){
		
	}
	public Document parse(InputSource source) throws IOException{
		
		Scanner s = new Scanner();
		final LinkedList<Token> tokens = new LinkedList<>();
		s.read(new InputStreamReader(source.getInputStream()), t -> tokens.add(t) );
		
		StructuredDocument doc = new StructuredDocument(source.getSourceName());
		
		parse(tokens, doc);
		
		print(doc);

		return doc;
	}

	/**
	 * @param doc
	 */
	private void print(Element doc) {
		print(doc , 0);
	}

	private void print(Element doc, int t) {
		for (int i = 0 ; i < t; i++){
			System.out.print("\t");
		}
		System.out.println(doc.toString());
		for(Element el : doc.getElements()){
			print(el, t+1);
		}
	}
	/**
	 * @param tokens
	 * @return
	 */
	private void parse(LinkedList<Token> tokens, StructuredDocument doc) {
		ParseState state = new DocumentInitState(doc);
		final LinkedList<Element> elements = new LinkedList<>();
		
		ParseContext ctx = new ParseContext(){

			@Override
			public CommandFromElementFactory getCommandFromElementFactory() {
				return factory;
			}
			
		};
		
		while (!tokens.isEmpty()){
			Token t = tokens.removeFirst();
			state = state.recieve(ctx, t, o -> elements.add(o));
	
		}
	
	}

	
}
