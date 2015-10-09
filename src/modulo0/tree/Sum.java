package modulo0.tree;

import modulo0.tree.Expr;
import modulo0.tree.Sum;
import modulo0.tree.Type;

public class Sum extends Expr {

	public Sum(Expr... es) {
		super("Sum", Type.EXPR, es);

	}


	@Override
	protected Expr copy() {
		return new Sum((Expr[]) children.clone());
	}

}
