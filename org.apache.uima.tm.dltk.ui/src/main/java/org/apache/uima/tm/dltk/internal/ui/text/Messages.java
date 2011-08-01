package org.apache.uima.tm.dltk.internal.ui.text;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.apache.uima.tm.dltk.internal.ui.text.messages"; //$NON-NLS-1$

  public static String TextMarkerRequirePackageMarkerResolution_addPackageToBuildpath;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
