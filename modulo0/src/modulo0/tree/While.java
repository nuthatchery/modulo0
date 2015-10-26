package modulo0.tree;

public class While extends Stat {

	public While(Expr c, Stat ss) {
		super("While", new M0Node[] { c, ss });

	}

	@Override
	protected Stat copy() {
		return new While((Expr) getChild(0), (Stat) getChild(1));
	}

}
