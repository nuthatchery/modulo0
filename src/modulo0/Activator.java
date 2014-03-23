package modulo0;

import java.util.Collection;
import java.util.Collections;

import modulo0.resources.EclipseModulo0Package;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.nuthatchery.pica.eclipse.EclipsePicaInfra;
import org.nuthatchery.pica.resources.ILanguage;
import org.nuthatchery.pica.resources.IManagedPackage;
import org.nuthatchery.pica.resources.IResourceManager;
import org.nuthatchery.pica.resources.IWorkspaceConfig;
import org.nuthatchery.pica.resources.storage.IStorage;
import org.osgi.framework.BundleContext;
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
			@NonNull
			public Collection<String> getActiveNatures() {
				return natures;
			}

				@Override
			public void initCompiler() {
			}

			@Override
			@NonNullByDefault
			public IManagedPackage makePackage(IResourceManager manager,
					IFile resource, IStorage storage, IConstructor id,
					ILanguage lang) {
				return new EclipseModulo0Package(manager, resource, storage, id, lang);
			}

		});
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
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
