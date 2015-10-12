package modulo0.tree;

import modulo0.tree.Expr;
import modulo0.tree.Mul;
import modulo0.tree.Type;

public class Mul extends Expr {

	public Mul(Expr e1, Expr e2) {
		super("Mul", Type.EXPR, new Expr[] { e1, e2 });

	}


	@Override
	protected Expr copy() {
		return new Mul(getChild(0), getChild(1));
	}

}
