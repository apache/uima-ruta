package org.apache.uima.tm.dltk.debugger;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class TextMarkerDebuggerPlugin extends AbstractUIPlugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "org.apache.uima.tm.dltk.debugger";

  // The shared instance
  private static TextMarkerDebuggerPlugin plugin;

  /**
   * The constructor
   */
  public TextMarkerDebuggerPlugin() {
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
  public static TextMarkerDebuggerPlugin getDefault() {
    return plugin;
  }

}
