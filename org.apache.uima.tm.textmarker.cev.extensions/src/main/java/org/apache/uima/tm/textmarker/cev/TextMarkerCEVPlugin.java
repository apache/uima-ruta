package org.apache.uima.tm.textmarker.cev;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class TextMarkerCEVPlugin extends AbstractUIPlugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "org.apache.uima.tm.textmarker.cev.extensions";

  public static final String BASIC_TYPE = "org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic";

  public static final String SCRIPT_APPLY_TYPE = "org.apache.uima.tm.textmarker.kernel.type.DebugScriptApply";

  public static final String BLOCK_APPLY_TYPE = "org.apache.uima.tm.textmarker.kernel.type.DebugBlockApply";

  public static final String RULE_APPLY_TYPE = "org.apache.uima.tm.textmarker.kernel.type.DebugRuleApply";

  public static final String RULE_MATCH_TYPE = "org.apache.uima.tm.textmarker.kernel.type.DebugRuleMatch";

  public static final String MATCHED_RULE_MATCH_TYPE = "org.apache.uima.tm.textmarker.kernel.type.DebugMatchedRuleMatch";

  public static final String FAILED_RULE_MATCH_TYPE = "org.apache.uima.tm.textmarker.kernel.type.DebugFailedRuleMatch";

  public static final String RULE_ELEMENT_MATCH_TYPE = "org.apache.uima.tm.textmarker.kernel.type.DebugRuleElementMatch";

  public static final String RULE_ELEMENT_MATCHES_TYPE = "org.apache.uima.tm.textmarker.kernel.type.DebugRuleElementMatches";

  public static final String EVAL_CONDITION_TYPE = "org.apache.uima.tm.textmarker.kernel.type.DebugEvaluatedCondition";

  // The shared instance
  private static TextMarkerCEVPlugin plugin;

  /**
   * The constructor
   */
  public TextMarkerCEVPlugin() {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext )
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext )
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
  public static TextMarkerCEVPlugin getDefault() {
    return plugin;
  }

  public static ImageDescriptor getImageDescriptor(String path) {
    return imageDescriptorFromPlugin(PLUGIN_ID, path);
  }

}
