package org.apache.uima.tm.dltk.core;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

public class TextMarkerPlugin extends Plugin {

  public static final String PLUGIN_ID = "org.apache.uima.tm.dltk.core";

  private static TextMarkerPlugin plugin;

  public TextMarkerPlugin() {
    plugin = this;
  }

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    super.stop(context);
    plugin = null;
  }

  public static TextMarkerPlugin getDefault() {
    return plugin;
  }

  public static void error(Throwable t) {
    plugin.getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, t.getMessage(), t));
  }

}
