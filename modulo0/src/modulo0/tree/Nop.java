package modulo0.tree;

public class Nop extends Stat {

	public Nop() {
		super("Nop", new M0Node[] {});

	}

	@Override
	protected Stat copy() {
		return new Nop();
	}
}
