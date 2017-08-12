/**
 * 
 */
package org.omnidoc.bb;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;
import org.middleheaven.io.repository.ManagedFile;
import org.middleheaven.io.repository.TextFile;
import org.middleheaven.io.repository.machine.MachineFiles;


/**
 * 
 */
public class BBTest {

	
	@Test @Ignore
	public void test (){
		
		File f = new File("./src/test/resources/org/omnidoc/bb/test.bb").getAbsoluteFile();
		
		BulletinBoardCodeFileDocumentReader reader = new BulletinBoardCodeFileDocumentReader(f);
		
		reader.getInputStream();
		
		ManagedFile file =  MachineFiles.resolveFile(f.getAbsolutePath());
		
		TextFile textFile = TextFile.from(file);
		
		String text = textFile.getText().toString();
		
		String bb = BulletinBoardCodeFileDocumentReader.bbcode(text);
		
		System.out.print(bb);
	}
}
