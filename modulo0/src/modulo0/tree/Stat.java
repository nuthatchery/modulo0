package modulo0.tree;

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
