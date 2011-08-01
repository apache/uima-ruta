package org.apache.uima.tm.dltk.textmarker.internal.launching;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.uima.tm.dltk.core.TextMarkerNature;
import org.apache.uima.tm.dltk.textmarker.launching.TextMarkerLaunchingPlugin;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.environment.IDeployment;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.eclipse.dltk.internal.launching.AbstractInterpreterInstallType;
import org.eclipse.dltk.launching.EnvironmentVariable;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.LibraryLocation;


public class GenericTextMarkerInstallType extends AbstractInterpreterInstallType {

  public static final String DBGP_FOR_TM_BUNDLE_ID = "org.apache.uima.tm.dltk.interpreter.dbgp";

  public static final String EMBEDDED_TM_BUNDLE_ID = "org.apache.uima.tm.dltk.interpreter";

  public String getNatureId() {
    return TextMarkerNature.NATURE_ID;
  }

  public String getName() {
    return "Generic TextMarker Install";
  }

  @Override
  public LibraryLocation[] getDefaultLibraryLocations(IFileHandle installLocation,
          EnvironmentVariable[] variables, IProgressMonitor monitor) {
    return new LibraryLocation[0];
  }

  private static String[] possibleExes = { "tm" };

  @Override
  protected String getPluginId() {
    return TextMarkerLaunchingPlugin.PLUGIN_ID;
  }

  @Override
  protected String[] getPossibleInterpreterNames() {
    return possibleExes;
  }

  @Override
  protected IInterpreterInstall doCreateInterpreterInstall(String id) {
    return new GenericTextMarkerInstall(this, id);
  }

  @Override
  protected void filterEnvironment(Map environment) {
    environment.remove("TMLIBPATH");
    environment.remove("DISPLAY");
  }

  @Override
  public IStatus validateInstallLocation(IFileHandle installLocation) {
    return Status.OK_STATUS;
  }

  @Override
  protected IPath createPathFile(IDeployment deployment) throws IOException {
    throw new RuntimeException("This method should not be used");
  }

  @Override
  protected String[] parsePaths(String result) {
    ArrayList paths = new ArrayList();
    String subs = null;
    int index = 0;
    while (index < result.length()) {
      // skip whitespaces
      while (index < result.length() && Character.isWhitespace(result.charAt(index)))
        index++;
      if (index == result.length())
        break;

      if (result.charAt(index) == '{') {
        int start = index;
        while (index < result.length() && result.charAt(index) != '}')
          index++;
        if (index == result.length())
          break;
        subs = result.substring(start + 1, index);
      } else {
        int start = index;
        while (index < result.length() && result.charAt(index) != ' ')
          index++;
        subs = result.substring(start, index);
      }

      paths.add(subs);
      index++;
    }

    return (String[]) paths.toArray(new String[paths.size()]);
  }

  @Override
  protected ILog getLog() {
    return TextMarkerLaunchingPlugin.getDefault().getLog();
  }
}
