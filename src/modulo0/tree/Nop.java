package modulo0.tree;

import modulo0.tree.Nop;
import modulo0.tree.Stat;
import modulo0.tree.M0Node;

public class Nop extends Stat {

	public Nop() {
		super("Nop", new M0Node[] {});

	}


	@Override
	protected Stat copy() {
		return new Nop();
	}
}
