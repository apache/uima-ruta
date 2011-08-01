package org.apache.uima.tm.dltk.textmarker.launching;

import java.io.IOException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.dltk.core.environment.IDeployment;
import org.eclipse.dltk.core.environment.IExecutionEnvironment;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.osgi.framework.BundleContext;

public class TextMarkerLaunchingPlugin extends Plugin {

  public static final String PLUGIN_ID = "org.apache.uima.tm.dltk.launching";

  private static TextMarkerLaunchingPlugin plugin;

  public TextMarkerLaunchingPlugin() {
    plugin = this;
  }

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  public static TextMarkerLaunchingPlugin getDefault() {
    return plugin;
  }

  public static String getUniqueIdentifier() {
    return PLUGIN_ID;
  }

  public IFileHandle getConsoleProxy(IExecutionEnvironment exeEnv) throws IOException {
    IDeployment deployment = exeEnv.createDeployment();
    IPath path = deployment.add(this.getBundle(), "console");
    path.append("ConsoleProxy.js");
    return deployment.getFile(path);

  }

}