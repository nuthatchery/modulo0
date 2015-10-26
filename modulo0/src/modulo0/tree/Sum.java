package modulo0.tree;

public class Sum extends Expr {

	public Sum(Expr... es) {
		super("Sum", Type.EXPR, es);

	}

	@Override
	protected Expr copy() {
		return new Sum((Expr[]) children.clone());
	}

}
