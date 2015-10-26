package modulo0.eclipse;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;

import io.usethesource.impulse.language.Language;
import io.usethesource.impulse.language.LanguageRegistry;
import io.usethesource.impulse.model.ISourceProject;
import io.usethesource.impulse.parser.IMessageHandler;
import io.usethesource.impulse.parser.IParseController;
import io.usethesource.impulse.parser.ISourcePositionLocator;
import io.usethesource.impulse.services.IAnnotationTypeInfo;
import io.usethesource.impulse.services.ILanguageSyntaxProperties;
import modulo0.parser.M0Parser;
import modulo0.parser.ParseError;
import modulo0.parser.Token;
import modulo0.tree.Module;

public class ParseController implements IParseController {

	Module parseTree;
	private List<Token> tokens;
	private IDocument document;
	private Language language;
	private IMessageHandler handler;
	private ISourceProject project;
	private IPath path;

	@Override
	public IAnnotationTypeInfo getAnnotationTypeInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getCurrentAst() {
		return parseTree;
	}

	@Override
	public IDocument getDocument() {
		return document;
	}

	@Override
	public Language getLanguage() {
		return language;
	}

	@Override
	public IPath getPath() {
		return path;
	}

	@Override
	public ISourceProject getProject() {
		return project;
	}

	@Override
	public ISourcePositionLocator getSourcePositionLocator() {
		return new NodeLocator();
	}

	@Override
	public ILanguageSyntaxProperties getSyntaxProperties() {
		return new SyntaxProperties();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<Object> getTokenIterator(IRegion region) {
		if (parseTree == null) {
			return null;
		} else {
			return (Iterator<Object>) ((List<?>) tokens).iterator();
		}
	}

	@Override
	public void initialize(IPath filePath, ISourceProject project, IMessageHandler handler) {
		this.handler = handler;
		this.project = project;
		this.path = filePath;

		this.language = LanguageRegistry.findLanguage(filePath, null);

	}

	@Override
	public Object parse(IDocument document, IProgressMonitor monitor) {
		this.document = document;
		String source = document.get();
		return parse(source, monitor);
	}

	@Override
	public Object parse(String source, IProgressMonitor monitor) {
		M0Parser m0Parser = new M0Parser(source);
		try {
			parseTree = m0Parser.parseModule();
		} catch (ParseError e) {
			if (e.getOffset() >= 0) {
				handler.handleSimpleMessage("Editor: " + e.getErrorMessage(), e.getOffset(),
						e.getOffset() + e.getLength(), 0, 0, 0, 0);
			}
		}
		tokens = m0Parser.getTokens();

		return parseTree;
	}
}
