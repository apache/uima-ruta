package org.apache.uima.tm.dltk.formatter.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.apache.uima.tm.dltk.formatter.internal.messages"; //$NON-NLS-1$

  public static String TextMarkerFormatter_contentCorrupted;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
