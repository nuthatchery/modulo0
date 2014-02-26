package modulo0;

import java.util.Collection;
import java.util.Collections;

import modulo0.resources.EclipseModulo0Package;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.magnolialang.magnolia.resources.EclipseMagnoliaPackage;
import org.nuthatchery.pica.EclipsePicaInfra;
import org.nuthatchery.pica.resources.ILanguage;
import org.nuthatchery.pica.resources.IManagedPackage;
import org.nuthatchery.pica.resources.IResourceManager;
import org.nuthatchery.pica.resources.IWorkspaceConfig;
import org.nuthatchery.pica.resources.storage.IStorage;
import org.osgi.framework.BundleContext;
import org.rascalmpl.interpreter.Evaluator;
import org.eclipse.core.resources.IFile;
/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "modulo0"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
		EclipsePicaInfra.setInfra(new IWorkspaceConfig() {
			private Collection<String> natures = Collections.unmodifiableCollection(Collections.singletonList(Modulo0Nature.NATURE_ID));

			@Override
			public Collection<String> getActiveNatures() {
				return natures;
			}

			@Override
			public ClassLoader getParserClassLoader() {
				return getClass().getClassLoader();
			}

			@Override
			public void initCompiler() {
			}

			@Override
			public IManagedPackage makePackage(IResourceManager manager,
					IFile resource, IStorage storage, IConstructor id,
					ILanguage lang) {
				return new EclipseModulo0Package(manager, resource, storage, id, lang);
			}

			@Override
			public void addRascalSearchPaths(Evaluator evaluator) {
				// no rascal paths to add
			}});
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
