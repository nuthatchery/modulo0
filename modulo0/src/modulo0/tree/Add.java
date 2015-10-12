package modulo0.tree;

import modulo0.tree.Add;
import modulo0.tree.Expr;
import modulo0.tree.Type;

public class Add extends Expr {

	public Add(Expr e1, Expr e2) {
		super("Add", Type.EXPR, new Expr[] { e1, e2 });

	}


	@Override
	protected Expr copy() {
		return new Add(getChild(0), getChild(1));
	}

}
