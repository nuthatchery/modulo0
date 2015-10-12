package modulo0.tree;

import modulo0.tree.Expr;
import modulo0.tree.Let;
import modulo0.tree.Type;
import modulo0.tree.Var;

public class Let extends Expr {

	public Let(Var x, Expr e1, Expr e2) {
		super("Let", Type.EXPR, new Expr[] { x, e1, e2 });

	}


	@Override
	protected Expr copy() {
		return new Let((Var) getChild(0), getChild(1), getChild(2));
	}

}
