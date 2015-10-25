package modulo0;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.jdt.annotation.Nullable;
import org.nuthatchery.pica.resources.ILanguage;

public class Modulo0Language implements ILanguage {
	private static Modulo0Language instance = new Modulo0Language();

	private Modulo0Language() {
		
	}
	
	public static Modulo0Language getInstance() {
		return instance ;
	}
	@Override
	public Collection<String> getExtensions() {
		return Arrays.asList("m0");
	}

	@Override
	public String getFileName(String modName) {
		return modName + ".m0";
	}

	@Override
	public String getId() {
		return "Modulo0";
	}

	@Override
	public String getModuleName(String fileName) {
		return fileName.substring(0, fileName.indexOf('.'));
	}

	@Override
	public String getName() {
		return "Modulo0";
	}

	@Override
	public Object getNameAST(String name) {
		return name;
	}

	@Override
	public String getNameString(Object nameAST) {
		return (String) nameAST;
	}

	@Override
	public String getPreferredExtension() {
		return "m0";
	}

	@Override
	@Nullable
	public String getStoreExtension() {
		return "m0c";
	}

	@Override
	public boolean hasExtension(String ext) {
		return getExtensions().contains(ext);
	}

}
