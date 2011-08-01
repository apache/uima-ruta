package org.apache.uima.tm.dltk.internal.debug;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

public class TextMarkerDebugPreferences {
  private static final String DEBUGGING_ENGINE_PATH = "debugging_engine";

  private static final String DEBUGGING_ENGINE_PATH_DEFAULT = "";

  private static Preferences getNode() {
    String id = TextMarkerDebugPlugin.getDefault().getBundle().getSymbolicName();
    return Platform.getPreferencesService().getRootNode().node(InstanceScope.SCOPE).node(id);
  }

  public static void save() {
    try {
      getNode().flush();
    } catch (BackingStoreException e) {
      // TODO: add logging
    }
  }

  public static String getDebuggingEnginePath() {
    return getNode().get(DEBUGGING_ENGINE_PATH, DEBUGGING_ENGINE_PATH_DEFAULT);
  }

  public static void setDebuggingEnginePath(String path) {
    getNode().put(DEBUGGING_ENGINE_PATH, path);
  }
}
