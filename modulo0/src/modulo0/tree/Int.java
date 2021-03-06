package modulo0.tree;

public class Int extends Expr {

	private int value;

	public Int(int i) {
		super("Int", Type.INT, new Expr[] {});
		value = i;
	}

	@Override
	protected Expr copy() {
		return new Int(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Int other = (Int) obj;
		if (value != other.value) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + value;
		return result;
	}

	@Override
	public String toRepr() {
		return "Int(" + String.valueOf(value) + ")";
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

}
