package modulo0.tree;

import modulo0.tree.Expr;
import modulo0.tree.Stat;
import modulo0.tree.While;
import modulo0.tree.M0Node;

public class While extends Stat {

	public While(Expr c, Stat ss) {
		super("While", new M0Node[] { c, ss });

	}


	@Override
	protected Stat copy() {
		return new While((Expr) getChild(0), (Stat) getChild(1));
	}

}
