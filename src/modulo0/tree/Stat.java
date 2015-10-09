package modulo0.tree;

import modulo0.tree.Stat;
import modulo0.tree.Type;
import modulo0.tree.M0Node;

public abstract class Stat extends M0Node {

	public Stat(String name, M0Node[] children) {
		super(name, Type.STAT, children);
	}


	public Stat(String name, Type type, M0Node[] children) {
		super(name, type, children);
	}


	@Override
	protected abstract Stat copy();

}
