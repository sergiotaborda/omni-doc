package org.omnidoc.xbb.syntax;

import org.omnidoc.stucture.Element;
import org.omnidoc.stucture.EmphasisTextElement;
import org.omnidoc.stucture.FigureElement;
import org.omnidoc.stucture.GenericCommandElement;
import org.omnidoc.stucture.ImageElement;
import org.omnidoc.stucture.InlineCodeElement;
import org.omnidoc.stucture.OtherLanguageTextElement;
import org.omnidoc.stucture.ReferenceCodeElement;
import org.omnidoc.stucture.ReferencesCodeElement;
import org.omnidoc.stucture.SourceCodeElement;
import org.omnidoc.stucture.StrongTextElement;
import org.omnidoc.stucture.UrlElement;
import org.omnidoc.stucture.WikiWordElement;

public class CommandFromElementFactory {

	
	/**
	 * @param name2
	 * @return
	 */
	public Element createCommand(String name) {
		if (name.equalsIgnoreCase("url")){
			return new UrlElement();
		} else 	if (name.equalsIgnoreCase("source")){
			return new SourceCodeElement();	
	    } else if (name.equalsIgnoreCase("ref")){
			return new ReferenceCodeElement();
		} else if (name.equalsIgnoreCase("references")){
			return new ReferencesCodeElement();
		} else if (name.equalsIgnoreCase("b") || name.equalsIgnoreCase("*") || name.equalsIgnoreCase("strong")){
			return new StrongTextElement();
		} else if (name.equalsIgnoreCase("i") || name.equalsIgnoreCase("\\") || name.equalsIgnoreCase("emph")){
			return new EmphasisTextElement();
		} else if (name.equalsIgnoreCase("e") || name.equalsIgnoreCase("\'") || name.equalsIgnoreCase("estrang")){
			return new OtherLanguageTextElement();
		} else if (name.equalsIgnoreCase("c") || name.equalsIgnoreCase("`") || name.equalsIgnoreCase("code")){
			return new InlineCodeElement();
		} else if (name.equalsIgnoreCase("w") || name.equalsIgnoreCase("wiki")){
			return new WikiWordElement();
		}else if (name.equalsIgnoreCase("k") || name.equalsIgnoreCase("keyworkd")){
			return new WikiWordElement();
		} else if (name.equalsIgnoreCase("fig")){
			return new FigureElement();
		} else if (name.equalsIgnoreCase("img")){
			return new ImageElement();
		} 
		return new GenericCommandElement(name);
	}
}
