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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.usethesource.impulse.parser.IParseController;
import io.usethesource.impulse.services.ITokenColorer;
import modulo0.parser.Token;

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class TokenColorer implements ITokenColorer {
	public static final String TYPE = "Type";
	public static final String IDENTIFIER = "Identifier";
	public static final String VARIABLE = "Var";
	public static final String CONSTANT = "Constant";
	public static final String COMMENT = "Comment";
	public static final String TODO = "Todo";
	public static final String FUNCTION = "FunName";
	public static final String PROCEDURE = "ProcName";
	public static final String KEYWORD = "Keyword";
	public static final String AMBIGUOUS = "AMB";
	public static final String ERROR = "SyntaxError";
	public static final List<String> CATEGORIES = Arrays.asList(TYPE, IDENTIFIER, VARIABLE, CONSTANT, COMMENT, TODO, FUNCTION, PROCEDURE, KEYWORD, AMBIGUOUS, ERROR);
	private final TextAttribute normal;

	private final Map<String, TextAttribute> map = new HashMap<String, TextAttribute>();


	public TokenColorer() {
		super();
		normal = new TextAttribute(Display.getDefault().getSystemColor(SWT.COLOR_BLACK), null, SWT.NONE);

		final Display display = Display.getDefault();
		map.put(KEYWORD, new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_MAGENTA), null, SWT.BOLD));
		map.put(TODO, new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_RED), null, SWT.BOLD));
		map.put(COMMENT, new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_GRAY), null, SWT.ITALIC));
		map.put(CONSTANT, new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_CYAN), null, SWT.NONE));
		map.put(VARIABLE, new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_CYAN), null, SWT.ITALIC));
		map.put(IDENTIFIER, new TextAttribute(display.getSystemColor(SWT.COLOR_BLACK), null, SWT.NONE));
		map.put(TYPE, new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_YELLOW), null, SWT.NONE));
		map.put(FUNCTION, new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_GREEN), null, SWT.NONE));
		map.put(PROCEDURE, new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_BLUE), null, SWT.NONE));
		map.put(AMBIGUOUS, new TextAttribute(null, display.getSystemColor(SWT.COLOR_YELLOW), SWT.NONE));
		map.put(ERROR, new TextAttribute(null, display.getSystemColor(SWT.COLOR_RED), SWT.NONE));

		final Color mxaCol1 = new Color(Display.getDefault(), 95, 35, 20);
		final Color mxaCol2 = new Color(Display.getDefault(), 120, 25, 15);
		final Color mxaCol3 = new Color(Display.getDefault(), 60, 45, 20);
	}


	@Override
	public IRegion calculateDamageExtent(final IRegion seed, final IParseController ctlr) {
		return seed;
	}


	@Override
	public TextAttribute getColoring(final IParseController controller, final Object token) {
		final String category = ((Token) token).getCategory();
		final TextAttribute attr = map.get(category);

		return attr == null ? normal : attr;
	}

}
