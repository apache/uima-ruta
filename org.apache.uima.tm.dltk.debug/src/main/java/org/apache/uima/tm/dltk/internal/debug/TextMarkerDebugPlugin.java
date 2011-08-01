package org.apache.uima.tm.dltk.internal.debug;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class TextMarkerDebugPlugin extends Plugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "de.uniwue.dltk.textmarker.debug";

  // The shared instance
  private static TextMarkerDebugPlugin plugin;

  public TextMarkerDebugPlugin() {
  }

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    try {
      savePluginPreferences();
    } finally {
      plugin = null;
      super.stop(context);
    }
  }

  public static TextMarkerDebugPlugin getDefault() {
    return plugin;
  }
}
