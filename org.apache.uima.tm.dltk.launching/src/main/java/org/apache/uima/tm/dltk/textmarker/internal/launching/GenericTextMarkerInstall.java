package org.apache.uima.tm.dltk.textmarker.internal.launching;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.uima.tm.dltk.core.TextMarkerNature;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.dltk.launching.AbstractInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.IInterpreterRunner;


public class GenericTextMarkerInstall extends AbstractInterpreterInstall {

  @Override
  public String getBuiltinModuleContent(String name) {
    InputStream stream = GenericTextMarkerInstall.class.getResourceAsStream("builtins.tm");
    DataInputStream st = new DataInputStream(stream);
    StringBuffer buf = new StringBuffer();
    try {
      while (st.available() >= 0) {
        String line = st.readLine();
        if (line == null)
          break;
        buf.append(line);
        buf.append('\n');
      }

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return buf.toString();
  }

  @Override
  public String[] getBuiltinModules() {
    return new String[] { "builtins.tm" };
  }

  public GenericTextMarkerInstall(IInterpreterInstallType type, String id) {
    super(type, id);
  }

  @Override
  public IInterpreterRunner getInterpreterRunner(String mode) {
    IInterpreterRunner runner = super.getInterpreterRunner(mode);
    if (runner != null) {
      return runner;
    }

    if (mode.equals(ILaunchManager.RUN_MODE)) {
      return new TextMarkerInterpreterRunner(this);
    }
    return null;
  }

  public String getNatureId() {
    return TextMarkerNature.NATURE_ID;
  }
}