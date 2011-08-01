package org.apache.uima.tm.dltk.internal.debug.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class TextMarkerDebugUIPlugin extends AbstractUIPlugin {

  public TextMarkerDebugUIPlugin() {
    plugin = this;
  }

  // The shared instance.
  private static TextMarkerDebugUIPlugin plugin;

  /**
   * This method is called upon plug-in activation
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
  }

  /**
   * This method is called when the plug-in is stopped
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    super.stop(context);
    plugin = null;
  }

  /**
   * Returns the shared instance.
   */
  public static TextMarkerDebugUIPlugin getDefault() {
    return plugin;
  }
}
