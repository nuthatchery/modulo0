package modulo0.eclipse;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.Platform;
import modulo0.resources.M0Module;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.nuthatchery.pica.Pica;
import org.nuthatchery.pica.eclipse.EclipsePicaInfra;
import org.nuthatchery.pica.resources.ILanguage;
import org.nuthatchery.pica.resources.IProjectManager;
import org.nuthatchery.pica.resources.IWorkspaceConfig;
import org.nuthatchery.pica.resources.LanguageRegistry;
import org.nuthatchery.pica.resources.handles.IFileHandle;
import org.nuthatchery.pica.resources.managed.IManagedCodeUnit;
import org.nuthatchery.pica.resources.storage.IStorage;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import io.usethesource.impulse.runtime.PluginBase;
import modulo0.Modulo0Language;
import modulo0.resources.M0Module;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends PluginBase {

	// The plug-in ID
	public static final String PLUGIN_ID = "modulo0"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
		EclipsePicaInfra.setInfra(new IWorkspaceConfig() {
			private Collection<String> natures = Collections
					.unmodifiableCollection(Collections.singletonList(Modulo0Nature.NATURE_ID));

			@Override
			@NonNull
			public Collection<String> getActiveNatures() {
				return natures;
			}

			@Override
			public void initCompiler() {
			}

			@Override
			public IManagedCodeUnit makePackage(IProjectManager manager, IFileHandle res, @Nullable IStorage storage,
					Object id, ILanguage lang) {
				return new M0Module(manager, res, storage);
			}

		});

		LanguageRegistry.registerLanguage( Modulo0Language.getInstance());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.
	 * BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.
	 * BundleContext)
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

	public static synchronized void getOrStartInstance() {
		if (plugin == null)
			try {
				Platform.getBundle(PLUGIN_ID).start();
			} catch (BundleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public String getID() {
		return PLUGIN_ID;
	}

	@Override
	public String getLanguageID() {
		return "Modulo0";
	}

}
