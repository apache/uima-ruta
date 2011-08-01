package org.apache.uima.tm.dltk.internal.testing;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class TextMarkerTestingPlugin extends AbstractUIPlugin {

  public static final String PLUGIN_ID = "org.apache.uima.tm.dltk.testing";

  private static TextMarkerTestingPlugin plugin;

  public TextMarkerTestingPlugin() {
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.uniwue.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.uniwue.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  public static TextMarkerTestingPlugin getDefault() {
    return plugin;
  }

}
