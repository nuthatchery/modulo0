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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modulo0.parser.Token;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.IValue;
import org.rascalmpl.values.uptr.ProductionAdapter;
import org.rascalmpl.values.uptr.TreeAdapter;
import org.rascalmpl.values.uptr.visitors.TreeVisitor;

public class TokenIterator implements Iterator<Token> {
	protected final List<Token> tokList;
	protected final Iterator<Token> tokIterator;


	public TokenIterator(final Object parseTree) {
		tokList = new ArrayList<Token>(1000);

		if(parseTree != null) {
			//parseTree.accept(new LexicalCollector());
		}
		tokIterator = tokList.iterator();

	}


	@Override
	public boolean hasNext() {
		return tokIterator.hasNext();
	}


	@Override
	public Token next() {
		return tokIterator.next();
	}


	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}


	private class LexicalCollector  {
		private int location;
	}
}
