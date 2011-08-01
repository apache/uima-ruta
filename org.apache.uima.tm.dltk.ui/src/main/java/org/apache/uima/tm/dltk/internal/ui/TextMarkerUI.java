package org.apache.uima.tm.dltk.internal.ui;

import org.apache.uima.tm.dltk.internal.ui.text.TextMarkerTextTools;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * The activator class controls the plug-in life cycle
 */
public class TextMarkerUI extends AbstractUIPlugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "org.apache.uima.tm.dltk.ui";

  // The shared instance
  private static TextMarkerUI plugin;

  private TextMarkerTextTools textTools;

  /**
   * The constructor
   */
  public TextMarkerUI() {
    plugin = this;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
  }

  /*
   * (non-Javadoc)
   * 
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
  public static TextMarkerUI getDefault() {
    return plugin;
  }

  public synchronized TextMarkerTextTools getTextTools() {
    if (textTools == null)
      textTools = new TextMarkerTextTools(true);
    return textTools;
  }

  public static void error(String message, Throwable t) {
    plugin.getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, message, t));
  }

}
