package org.apache.uima.tm.dltk.internal.ui.preferences;

import org.eclipse.osgi.util.NLS;

/**
 * @author Martin
 * 
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.apache.uima.tm.dltk.ui.formatter.messages";

  public static String TextMarkerFormatterPreferencePage_description;
  static {
    // init resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
