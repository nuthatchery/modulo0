package modulo0.tree;

import modulo0.tree.Seq;
import modulo0.tree.Stat;

public class Seq extends Stat {

	public Seq(Stat... ss) {
		super("Seq", ss);

	}


	@Override
	protected Stat copy() {
		return new Seq((Stat[]) children);
	}

}
