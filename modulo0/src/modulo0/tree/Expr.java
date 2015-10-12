package modulo0.tree;

import modulo0.tree.Expr;
import modulo0.tree.Type;
import modulo0.tree.M0Node;

public abstract class Expr extends M0Node {
	protected Expr(String name, Type type, Expr[] children) {
		super(name, type, children);
	}


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


	@Override
	protected abstract Expr copy();

}
