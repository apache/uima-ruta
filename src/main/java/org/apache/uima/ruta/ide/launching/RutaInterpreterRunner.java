/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.ruta.ide.launching;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.impl.XmiCasDeserializer;
import org.apache.uima.cas.impl.XmiCasSerializer;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.ruta.action.LogAction;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.ide.RutaIdePlugin;
import org.apache.uima.ruta.ide.core.RutaCorePreferences;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.apache.uima.util.XMLInputSource;
import org.apache.uima.util.XMLSerializer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.launching.AbstractInterpreterRunner;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.VMRunnerConfiguration;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.xml.sax.SAXException;

public class RutaInterpreterRunner extends AbstractInterpreterRunner implements IConfigurableRunner {

  public static final IRutaInterpreterRunnerConfig DEFAULT_CONFIG = new IRutaInterpreterRunnerConfig() {

    public void adjustRunnerConfiguration(VMRunnerConfiguration vconfig, InterpreterConfig iconfig,
            ILaunch launch, IJavaProject project) {
      System.out.println("adjust runner");
    }

    public String[] computeClassPath(InterpreterConfig config, ILaunch launch, IJavaProject project)
            throws Exception {
      return RutaInterpreterRunner.getClassPath(project);
    }

    public String[] getProgramArguments(InterpreterConfig config, ILaunch launch,
            IJavaProject project) {
      return new String[0];
    }

    public String getRunnerClassName(InterpreterConfig config, ILaunch launch, IJavaProject project) {
      return "RutaRunner";
    }

  };

  private IRutaInterpreterRunnerConfig config = DEFAULT_CONFIG;

  @Override
  public void run(InterpreterConfig config, ILaunch launch, IProgressMonitor monitor)
          throws CoreException {
    doRunImpl(config, launch, this.config, monitor);
  }

  private static Handler initConsoleLink(String module) {
    final MessageConsole console;
    final MessageConsoleStream out;
    console = findConsole(module);
    out = console.newMessageStream();

    Handler handler = new Handler() {

      @Override
      public void publish(LogRecord record) {
        String message = record.getMessage();
        if (message.equals("\\n")) {
          out.println();
        }
        String[] split = message.split("\\\\n");
        for (String string : split) {
          out.println(string);
        }
        console.activate();
      }

      @Override
      public void flush() {

      }

      @Override
      public void close() throws SecurityException {

      }
    };
    Logger.getLogger(LogAction.LOGGER_NAME).addHandler(handler);
    return handler;
  }

  private static void clearConsoleLink(Handler handler) {
    Logger.getLogger(LogAction.LOGGER_NAME).removeHandler(handler);
  }

  private static MessageConsole findConsole(String name) {
    ConsolePlugin plugin = ConsolePlugin.getDefault();
    IConsoleManager conMan = plugin.getConsoleManager();
    IConsole[] existing = conMan.getConsoles();
    for (int i = 0; i < existing.length; i++)
      if (name.equals(existing[i].getName()))
        return (MessageConsole) existing[i];
    MessageConsole myConsole = new MessageConsole(name, null);
    conMan.addConsoles(new IConsole[] { myConsole });
    return myConsole;
  }

  public static List<File> getFiles(File dir, boolean recusive) {
    List<File> result = new ArrayList<File>();
    for (File each : dir.listFiles()) {
      // TODO: find a solution for this hotfix
      if (each.getName().endsWith(".svn")) {
        continue;
      }
      result.add(each);
      if (each.isDirectory() && recusive) {
        result.addAll(getFiles(each, recusive));
      }
    }
    return result;
  }

  public static void doRunImpl(InterpreterConfig config, ILaunch launch,
          IRutaInterpreterRunnerConfig iconfig, IProgressMonitor monitor) throws CoreException {
    String launchMode = launch.getLaunchMode();
    IScriptProject proj = AbstractScriptLaunchConfigurationDelegate.getScriptProject(launch
            .getLaunchConfiguration());

    IPath projectPath = proj.getResource().getLocation();
    IPath inputDirPath = projectPath.append(RutaProjectUtils.getDefaultInputLocation());
    IPath outputDirPath = projectPath.append(RutaProjectUtils.getDefaultOutputLocation());
    String engine = RutaProjectUtils.getEngineDescriptorPath(config.getScriptFilePath(),
            proj.getProject()).toPortableString();
    IPath rootPath = RutaProjectUtils.getDescriptorRootPath(proj.getProject());

    File inputDir = inputDirPath.makeAbsolute().toFile();
    File outputDir = outputDirPath.makeAbsolute().toFile();

    if (!inputDir.exists()) {
      inputDir.mkdirs();
      IFolder folder = proj.getProject().getFolder(RutaProjectUtils.getDefaultInputLocation());
      folder.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
    }
    IFolder outputFolder = proj.getProject().getFolder(RutaProjectUtils.getDefaultOutputLocation());
    if (!outputDir.exists()) {
      outputDir.mkdirs();
      outputFolder.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
    }

    IPreferenceStore store = RutaIdePlugin.getDefault().getPreferenceStore();
    boolean clearOutput = store.getBoolean(RutaCorePreferences.PROJECT_CLEAR_OUTPUT);
    if (clearOutput) {
      List<File> outputFiles = getFiles(outputDir, false);
      for (File file : outputFiles) {
        file.delete();
      }
      outputFolder.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
    }

    List<File> inputFiles = getFiles(inputDir, false);

    int ticks = (inputFiles.size() * 2) + 1;
    SubProgressMonitor mon = new SubProgressMonitor(monitor, ticks);

    mon.setTaskName("Initializing");
    Handler handler = initConsoleLink(config.getScriptFilePath().lastSegment());

    CAS cas = null;

    AnalysisEngine ae = null;
    try {
      File specFile = new File(engine);
      XMLInputSource in = new XMLInputSource(specFile);
      ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
      ResourceManager resMgr = UIMAFramework.newDefaultResourceManager();
      resMgr.setDataPath(rootPath.toPortableString());
      ae = UIMAFramework.produceAnalysisEngine(specifier, resMgr, null);
    } catch (Exception e) {
      String message = e.getMessage();
      DLTKCore.error(message, e);
      clearConsoleLink(handler);
      throw new CoreException(new Status(IStatus.ERROR, RutaIdePlugin.PLUGIN_ID,
              ScriptLaunchConfigurationConstants.ERR_INTERNAL_ERROR, message, e));
    }

    try {
      if ("debug".equals(launchMode)) {
        ae.setConfigParameterValue(RutaEngine.CREATE_DEBUG_INFO, true);
        ae.setConfigParameterValue(RutaEngine.CREATE_MATCH_DEBUG_INFO, true);
        ae.setConfigParameterValue(RutaEngine.CREATE_PROFILING_INFO, true);
        ae.setConfigParameterValue(RutaEngine.CREATE_STATISTIC_INFO, true);
        ae.setConfigParameterValue(RutaEngine.CREATE_CREATED_BY_INFO, true);
        ae.reconfigure();
      }
    } catch (Exception e) {
      clearConsoleLink(handler);
      String message = e.getMessage();
      DLTKCore.error(message, e);
      throw new CoreException(new Status(IStatus.ERROR, RutaIdePlugin.PLUGIN_ID,
              ScriptLaunchConfigurationConstants.ERR_INTERNAL_ERROR, message, e));
    }
    mon.worked(1);
    for (File each : inputFiles) {

      mon.setTaskName("Processing " + each.getName());
      if (mon.isCanceled()) {
        break;
      }
      try {
        if (cas == null) {
          cas = ae.newCAS();
        } else {
          cas.reset();
        }
        if (each.getName().endsWith("xmi")) {
          XmiCasDeserializer.deserialize(new FileInputStream(each), cas, true);
        } else {
          cas.setDocumentText(getText(each));
        }

        if (mon.isCanceled()) {
          break;
        }

        RutaEngine.removeSourceDocumentInformation(cas);
        RutaEngine.addSourceDocumentInformation(cas, each);

        ae.process(cas);

        mon.worked(1);

        if (mon.isCanceled()) {
          break;
        }

        File outputFile = new File(outputDir, each.getName() + ".xmi");
        mon.setTaskName("Saving " + outputFile.getName());
        writeXmi(cas, outputFile);
        mon.worked(1);
      } catch (Exception e) {
        if (cas != null) {
          cas.release();
        }
        if (ae != null) {
          ae.destroy();
        }
        clearConsoleLink(handler);
        String message = e.getMessage();
        DLTKCore.error(message, e);
        throw new CoreException(new Status(IStatus.ERROR, RutaIdePlugin.PLUGIN_ID,
                ScriptLaunchConfigurationConstants.ERR_INTERNAL_ERROR, message, e));
      }
    }
    if (cas != null) {
      cas.release();
    }
    if (ae != null) {
      ae.destroy();
    }
    IFolder folder = outputFolder;
    folder.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
    clearConsoleLink(handler);
    mon.done();
  }

  private static String getText(File each) {
    try {
      return org.apache.uima.pear.util.FileUtil.loadTextFile(each, "UTF-8");
    } catch (IOException e) {
      DLTKCore.error(e.getMessage(), e);
    }
    return "";
  }

  private static void writeXmi(CAS aCas, File name) throws IOException, SAXException {
    FileOutputStream out = null;

    try {
      // write XMI
      out = new FileOutputStream(name);
      XmiCasSerializer ser = new XmiCasSerializer(aCas.getTypeSystem());
      XMLSerializer xmlSer = new XMLSerializer(out, false);
      ser.serialize(aCas, xmlSer.getContentHandler());
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }

  public static String[] getClassPath(IJavaProject myJavaProject) {
    String[] newClassPath = new String[0];// classPath.length + 2];
    return newClassPath;
  }

  protected static String[] computeBaseClassPath(IJavaProject myJavaProject) throws CoreException {
    if (!myJavaProject.exists())
      return new String[0];
    return JavaRuntime.computeDefaultRuntimeClassPath(myJavaProject);
  }

  protected String constructProgramString(InterpreterConfig config) {
    return "";
  }

  public RutaInterpreterRunner(IInterpreterInstall install) {
    super(install);
  }

  @Override
  protected String getProcessType() {
    return RutaLaunchConfigurationConstants.ID_RUTA_PROCESS_TYPE;
  }

  public void setRunnerConfig(IRutaInterpreterRunnerConfig config) {
    this.config = config;
  }
}
