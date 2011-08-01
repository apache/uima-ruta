package org.apache.uima.tm.dltk.internal.core.packages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.apache.uima.tm.dltk.internal.core.packages.messages"; //$NON-NLS-1$

  public static String TextMarkerCheckBuilder_interpreterNotFound;

  public static String TextMarkerCheckBuilder_processing;

  public static String TextMarkerCheckBuilder_retrievePackages;

  public static String TextMarkerCheckBuilder_unknownPackage;

  public static String TextMarkerCheckBuilder_unresolvedDependencies;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
