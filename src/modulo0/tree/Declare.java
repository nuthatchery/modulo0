package modulo0.tree;

import modulo0.tree.Declare;
import modulo0.tree.Expr;
import modulo0.tree.Stat;
import modulo0.tree.Var;
import modulo0.tree.M0Node;

public class Declare extends Stat {

	public Declare(Var x, Expr e, Stat s) {
		super("Declare", new M0Node[] { x, e, s });

	}


	@Override
	protected Stat copy() {
		return new Declare((Var) getChild(0), (Expr) getChild(1), (Stat) getChild(2));
	}

}
