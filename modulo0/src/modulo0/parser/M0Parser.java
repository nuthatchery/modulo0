package modulo0.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modulo0.tree.Module;

public class M0Parser {
	private final String input;
	private final List<Token> tokens = new ArrayList<>();
	private Pattern space = Pattern.compile("[ \n\t\f]+");
	private Pattern id = Pattern.compile("[a-zA-Z_]+");
	private Pattern moduleKw = Pattern.compile("module\\b");
	private Pattern semi = Pattern.compile(";");
	private Pattern importKw = Pattern.compile("import\\b");
	private Matcher matcher;
	private Pattern declKw = Pattern.compile("(import|data)\\b");

	public static void main(String[] args) {
		Module parse = parse("module foobar;     import fie; import fee; import foo;");

		System.out.println(parse);
	}

	public M0Parser(String input) {
		this.input = input;

		matcher = space.matcher(input);
	}

	public static Module parse(String input) {
		return new M0Parser(input).parseModule();
	}

	public List<Token> getTokens() {
		return tokens;
	}

	public Module parseModule() {
		skipLayout();

		getToken(moduleKw, "Keyword");

		skipLayout();

		Token name = getToken(id, "Identifier");

		skipLayout();
		getToken(semi, "");
		skipLayout();

		List<Token> imports = new ArrayList<>();
		String kw = getKeyword(declKw);
		while (kw != null) {
			skipLayout();

			if (kw.equals("import")) {
				Token importName = getToken(id, "Identifier");
				imports.add(importName);
				skipLayout();

				getToken(semi, "");
			} else if (kw.equals("data")) {
				String rest = input.substring(matcher.regionStart(),
						matcher.regionEnd());
				tokens.add(new Token("Comment", rest, null, matcher.regionEnd()));
				matcher.region(matcher.regionEnd(), matcher.regionEnd());
			}

			skipLayout();
			kw = getKeyword(declKw);
		}

		return new Module(name, imports, null);
	}

	private Token getToken(Pattern pat, String category) {
		matcher.usePattern(pat);
		if (matcher.lookingAt()) {
			String data = matcher.group();
			Token tok = new Token(category, data, null, matcher.start(),
					data.length());
			matcher.region(matcher.end(), input.length());
			tokens.add(tok);
			return tok;
		} else if (matcher.find()) {
			Token errTok = new Token("SyntaxError", input.substring(
					matcher.regionStart(), matcher.start()), null,
					matcher.regionStart());
			tokens.add(errTok);

			String data = matcher.group();
			Token tok = new Token(category, data, null, matcher.start(),
					data.length());
			matcher.region(matcher.end(), input.length());
			tokens.add(tok);
			return tok;
		} else {
			throw new ParseError("Expected " + category + " token matching "
					+ pat, null, matcher.regionStart(), 1);
		}
	}

	private String getKeyword(Pattern pat) {
		matcher.usePattern(pat);
		if (matcher.lookingAt()) {
			String data = matcher.group();
			Token tok = new Token("Keyword", data, null, matcher.start(),
					data.length());
			matcher.region(matcher.end(), input.length());
			tokens.add(tok);
			return data;
		} else if (matcher.find()) {
			Token errTok = new Token("SyntaxError", input.substring(
					matcher.regionStart(), matcher.start()), null,
					matcher.regionStart());
			tokens.add(errTok);

			String data = matcher.group();
			Token tok = new Token("Keyword", data, null, matcher.start(),
					data.length());
			matcher.region(matcher.end(), input.length());
			tokens.add(tok);
			return data;
		} else if (matcher.hitEnd()) {
			return null;
		} else {
			throw new ParseError("Expected keyword matching " + pat, null,
					matcher.regionStart(), 1);
		}
	}

	private boolean lookingAt(Pattern pat) {
		matcher.usePattern(pat);
		return matcher.lookingAt();
	}

	private void skipLayout() {
		matcher.usePattern(space);
		if (matcher.lookingAt()) {
			matcher.region(matcher.end(), input.length());
		}
	}

}
