package org.apache.uima.tm.dltk.internal.ui;

import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.ui.PluginImagesHelper;
import org.eclipse.jface.resource.ImageDescriptor;

public class TextMarkerImages {
  private static final PluginImagesHelper helper = new PluginImagesHelper(TextMarkerUI.getDefault()
          .getBundle(), new Path("/icons"));

  public static final ImageDescriptor PROJECT_DECARATOR = helper.createUnManaged(
          PluginImagesHelper.T_OVR, "tm_ovr.gif");

  public static final ImageDescriptor DESC_WIZBAN_PROJECT_CREATION = helper.createUnManaged(
          PluginImagesHelper.T_WIZBAN, "projectcreate_wiz.png");

  public static final ImageDescriptor DESC_WIZBAN_FILE_CREATION = helper.createUnManaged(
          PluginImagesHelper.T_WIZBAN, "filecreate_wiz.png");
}
