package modulo0.tree;

import modulo0.parser.Token;

public class Var extends Expr {

	private final Token varName;

	public Var(Token s) {
		super("Var", Type.VAR, new Expr[] {});
		varName = s;
	}

	@Override
	protected Expr copy() {
		return new Var(varName);
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
		Var other = (Var) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	public Token getToken() {
		return varName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public String toRepr() {
		return "Var(\"" + varName.getData() + "\")";
	}

	@Override
	public String toString() {
		return varName.getData();
	}

}
