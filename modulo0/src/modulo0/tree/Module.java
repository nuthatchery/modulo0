package modulo0.tree;

import java.util.List;

import modulo0.parser.Token;

public class Module extends M0Node {
	private final String rest;

	public Module(M0Node name, M0Node imports, String rest) {
		super("Module", Type.MODULE, name, imports);
		this.rest = rest;
	}

	public Module(Token name, List<Token> imports, String rest) {
		super("Module", Type.MODULE, new Var(name), new ImportList(imports));
		this.rest = rest;
	}

	@Override
	protected Module copy() {
		return new Module(getChild(0), getChild(1), rest);
	}

	public ImportList getImportList() {
		return (ImportList) getChild(1);
	}

	public Token getModuleName() {
		return ((Var) getChild(0)).getToken();

	}

	@Override
	public String toString() {
		String modName = getModuleName().getData();
		ImportList list = (ImportList) getChild(1);
		StringBuilder buf = new StringBuilder();
		buf.append("module ");
		buf.append(modName);
		buf.append("; ");
		for (int i = 0; i < list.arity(); i++) {
			Var imp = (Var) list.getChild(i);
			buf.append("import ");
			buf.append(imp);
			buf.append("; ");
		}

		return buf.toString();
	}
}
