package modulo0.tree;

import modulo0.tree.Assign;
import modulo0.tree.Expr;
import modulo0.tree.Stat;
import modulo0.tree.Var;
import modulo0.tree.M0Node;

public class Assign extends Stat {

	public Assign(Var v, Expr e) {
		super("Assign", new M0Node[] { v, e });

	}


	@Override
	protected Stat copy() {
		return new Assign((Var) getChild(0), (Expr) getChild(1));
	}
}
