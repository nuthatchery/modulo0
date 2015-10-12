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
package modulo0.parser;

import org.nuthatchery.pica.resources.managed.IManagedFile;

public class Token {
	private final String category;

	private final int offset;
	private final int length;

	private final String data;

	private final IManagedFile file;


	public String getData() {
		return data;
	}


	public IManagedFile getFile() {
		return file;
	}


	public Token(String category, String data, IManagedFile file, int offset, int length) {
		super();

		this.category = category;
		this.data = data;
		this.file = file;
		this.offset = offset;
		this.length = length;
	}
	public Token(String category, String data, IManagedFile file, int offset) {
		super();

		this.category = category;
		this.data = data;
		this.file = file;
		this.offset = offset;
		this.length = data.length();
	}

	public String getCategory() {
		return category;
	}


	public int getLength() {
		return length;
	}


	public int getOffset() {
		return offset;
	}
	
	public String toString() {
		return "\"" + data + "\":" + category + offset + "+" + length;
	}
}
