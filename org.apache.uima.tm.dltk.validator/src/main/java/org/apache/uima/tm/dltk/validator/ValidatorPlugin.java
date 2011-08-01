package org.apache.uima.tm.dltk.validator;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class ValidatorPlugin extends Plugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "org.apache.uima.tm.dltk.validator";

  // The shared instance
  private static ValidatorPlugin plugin;

  /**
   * The constructor
   */
  public ValidatorPlugin() {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
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
  public static ValidatorPlugin getDefault() {
    return plugin;
  }

}
