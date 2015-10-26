package modulo0.tree;

public class Declare extends Stat {

	public Declare(Var x, Expr e, Stat s) {
		super("Declare", new M0Node[] { x, e, s });

	}

	@Override
	protected Stat copy() {
		return new Declare((Var) getChild(0), (Expr) getChild(1), (Stat) getChild(2));
	}

}
