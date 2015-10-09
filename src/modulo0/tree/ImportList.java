package modulo0.tree;

import java.util.ArrayList;
import java.util.List;

import modulo0.parser.Token;
import modulo0.tree.ImportList;
import modulo0.tree.Expr;
import modulo0.tree.Type;

public class ImportList extends M0Node {
	
	public ImportList(List<Token> imports) {
		super("ImportList", Type.MODULE, makeChildren(imports));
	}
	
	private ImportList(M0Node[] children) {
		super("ImportList", Type.MODULE, children);
	}
	
	private static M0Node[] makeChildren(List<Token> imports) {
		M0Node[] children = new M0Node[imports.size()];
		int i = 0;
		for(Token imp : imports) {
			children[i++] = new Var(imp);
		}
		return children;
	}


	@Override
	protected ImportList copy() {
		return new ImportList(children);
	}
}
