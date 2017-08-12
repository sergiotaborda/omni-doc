/**
 * 
 */
package org.omnidoc.xbb.scanner;

import java.io.IOException;
import java.io.Reader;
import java.util.function.Consumer;

/**
 * 
 */
public class Scanner {

	
	public void read (Reader reader, Consumer<Token> consumer) throws IOException{
		int c;
		
		ParseState state = new TextBuilderState();

		ScanPosition pos = new ScanPosition();
		while ( (c = reader.read()) > -1 ){
			pos.incrementColumn();
			state = state.recieve(pos, (char)c,consumer);
		
			if (c == '\n'){
				pos.incrementLine();
			}
		}
		
		state.clear(pos, consumer);
		consumer.accept(new EOFToken());
		
	}
}
