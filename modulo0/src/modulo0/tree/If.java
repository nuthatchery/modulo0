package modulo0.tree;

import modulo0.tree.Expr;
import modulo0.tree.If;
import modulo0.tree.Stat;
import modulo0.tree.M0Node;

public class If extends Stat {

	public If(Expr c, Stat s1, Stat s2) {
		super("If", new M0Node[] { c, s1, s2 });

	}


	@Override
	protected Stat copy() {
		return new If((Expr) getChild(0), (Stat) getChild(1), (Stat) getChild(2));
	}

}
