package org.apache.uima.tm.textruler.lp2;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class LP2Plugin extends Plugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "org.apache.uima.tm.textruler.lp2";

  // The shared instance
  private static LP2Plugin plugin;

  /**
   * The constructor
   */
  public LP2Plugin() {
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
  public static LP2Plugin getDefault() {
    return plugin;
  }

}
