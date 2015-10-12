/**************************************************************************
 * Copyright (c) 2010-2014 Anya Helene Bagge
 * Copyright (c) 2010-2014 University of Bergen
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
 * + Andreas Hjortland
 * * Anya Helene Bagge
 *
 *************************************************************************/
package modulo0.eclipse;

import modulo0.parser.Token;

import org.eclipse.core.runtime.IPath;

import io.usethesource.impulse.editor.ModelTreeNode;
import io.usethesource.impulse.parser.ISourcePositionLocator;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.ISourceLocation;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.jdt.annotation.Nullable;
import org.nuthatchery.pica.terms.INodePredicate;
import org.rascalmpl.values.uptr.ITree;
import org.rascalmpl.values.uptr.TreeAdapter;
import org.rascalmpl.values.uptr.RascalValueFactory;

public class NodeLocator implements ISourcePositionLocator {



	@Override
	@Nullable
	public Object findNode(final Object ast, final int offset) {
		
		return null;
	}


	@Override
	@Nullable
	public Object findNode(final Object ast, final int startOffset, final int endOffset) {

		return null;
	}


	@Override
	public int getEndOffset(final Object node) {
		return getStartOffset(node) + getLength(node) - 1;
	}


	@Override
	public int getLength(final Object node) {
		if(node instanceof Token) {
			return ((Token) node).getLength();
		}
		else
			throw new IllegalArgumentException();
	}

	@Override
	@Nullable
	public IPath getPath(final Object node) {
		return null;
	}


	@Override
	public int getStartOffset(final Object node) {
		if(node instanceof Token) {
			return ((Token) node).getOffset();
		}
		else
			throw new IllegalArgumentException();
	}
}
