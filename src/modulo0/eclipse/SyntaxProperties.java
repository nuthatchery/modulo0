/**************************************************************************
 * Copyright (c) 2010-2013 Anya Helene Bagge
 * Copyright (c) 2010-2013 University of Bergen
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. See http://www.gnu.org/licenses/
 * 
 * 
 * See the file COPYRIGHT for more information.
 * 
 * Contributors:
 * * Anya Helene Bagge
 * 
 *************************************************************************/
package modulo0.eclipse;

import io.usethesource.impulse.parser.IParseController;
import io.usethesource.impulse.services.ILanguageSyntaxProperties;
import org.eclipse.jface.text.IRegion;

public class SyntaxProperties implements ILanguageSyntaxProperties {

	@Override
	public String getBlockCommentContinuation() {
		return " * ";
	}


	@Override
	public String getBlockCommentEnd() {
		return "*/";
	}


	@Override
	public String getBlockCommentStart() {
		return "/*";
	}


	@Override
	public IRegion getDoubleClickRegion(int offset, IParseController pc) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String[][] getFences() {
		return new String[][] { new String[] { "(", ")" }, new String[] { "{", "}" }, new String[] { "<", ">" }, new String[] { "[", "]" } };
	}


	@Override
	public int[] getIdentifierComponents(final String ident) {
		return new int[0];
	}


	@Override
	public String getIdentifierConstituentChars() {
		return null;
	}


	@Override
	public String getSingleLineCommentPrefix() {
		return "//";
	}


	@Override
	public boolean isIdentifierPart(char ch) {
		return Character.isJavaIdentifierPart(ch);
	}


	@Override
	public boolean isIdentifierStart(char ch) {
		return Character.isJavaIdentifierStart(ch);
	}


	@Override
	public boolean isWhitespace(char ch) {
		return ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r';
	}
}
