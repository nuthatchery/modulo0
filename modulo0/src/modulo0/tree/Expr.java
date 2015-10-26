package modulo0.tree;

public abstract class Expr extends M0Node {
	protected Expr(String name, Type type, Expr[] children) {
		super(name, type, children);
	}

	@Override
	protected abstract Expr copy();

	@Override
	public Expr getChild(int i) {
		return (Expr) children[i];
	}

	@Override
	public Expr replace(int i, M0Node child) {
		assert child instanceof Expr;
		Expr e = copy();
		e.children[i] = child;
		return e;
	}

}
