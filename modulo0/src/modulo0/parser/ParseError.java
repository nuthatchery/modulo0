package modulo0.parser;

import java.net.URI;

public class ParseError extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7365439746450133604L;
	private final int offset;
	private final int length;
	private final String msg;
	private final URI uri;

	public ParseError(String msg, URI uri, int offset, int length) {
		super();
		this.offset = offset;
		this.length = length;
		this.msg = msg;
		this.uri = uri;
	}

	public String getErrorMessage() {
		return msg;
	}

	public int getLength() {
		return length;
	}

	public int getOffset() {
		return offset;
	}

	public URI getUri() {
		return uri;
	}

}
