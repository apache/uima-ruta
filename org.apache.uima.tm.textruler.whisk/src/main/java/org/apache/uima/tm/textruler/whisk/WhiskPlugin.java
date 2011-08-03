package org.apache.uima.tm.textruler.whisk;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 */
public class WhiskPlugin extends Plugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "org.apache.uima.tm.textruler.whisk";

  // The shared instance
  private static WhiskPlugin plugin;

  /**
   * The constructor
   */
  public WhiskPlugin() {
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
  public static WhiskPlugin getDefault() {
    return plugin;
  }

}
