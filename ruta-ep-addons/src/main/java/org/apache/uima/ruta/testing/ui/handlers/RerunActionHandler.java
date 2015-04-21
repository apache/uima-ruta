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

package org.apache.uima.ruta.testing.ui.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.impl.XmiCasDeserializer;
import org.apache.uima.cas.impl.XmiCasSerializer;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.resource.metadata.FsIndexDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.apache.uima.ruta.ide.launching.RutaLaunchConfigurationConstants;
import org.apache.uima.ruta.ide.launching.RutaLaunchConstants;
import org.apache.uima.ruta.testing.evaluator.ICasEvaluator;
import org.apache.uima.ruta.testing.preferences.TestingPreferenceConstants;
import org.apache.uima.ruta.testing.ui.views.TestCasData;
import org.apache.uima.ruta.testing.ui.views.TestPageBookView;
import org.apache.uima.ruta.testing.ui.views.TestViewPage;
import org.apache.uima.ruta.testing.ui.views.evalDataTable.TypeEvalData;
import org.apache.uima.ruta.testing.ui.views.util.EvalDataProcessor;
import org.apache.uima.ruta.type.EvalAnnotation;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.Launch;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.xml.sax.SAXException;

public class RerunActionHandler implements IHandler {

  private class DebugJobChangeAdapter extends JobChangeAdapter {

    private TestViewPage page;

    DebugJobChangeAdapter(TestViewPage page) {
      super();
      this.page = page;
    }

    @Override
    public void done(IJobChangeEvent event) {
      if (event.getResult().isOK()) {
        page.getControl().getDisplay().asyncExec(new Runnable() {
          public void run() {
            page.updateInfoPanel();
          }
        });
      }
    }
  }

  /**
   * Handler that runs the script to be tested and updates the GUI.
   */
  private class RerunHandlerJob extends Job {
    ExecutionEvent event;

    private final String viewCasName;

    private List<String> excludedTypes;

    private List<String> includedTypes;

    private boolean debug;

    RerunHandlerJob(ExecutionEvent event, String scriptName, String viewCasName,
            List<String> excludedTypes, List<String> includedTypes, boolean debug) {
      super("Testing " + scriptName + "...");
      this.event = event;
      this.viewCasName = viewCasName;
      this.excludedTypes = excludedTypes;
      this.includedTypes = includedTypes;
      this.debug = debug;
      setUser(true);
    }

    @Override
    public IStatus run(IProgressMonitor monitor) {

      // handle GUI
      final TestPageBookView testPageView = (TestPageBookView) HandlerUtil.getActivePart(event);
      final TestViewPage debugPage = (TestViewPage) testPageView.getCurrentPage();
      testPageView.showBusy(true);

      // init variables
      IResource r = debugPage.getResource();
      final IFile fScript = (IFile) r;
      final IProject project = r.getProject();
      IPath engineDescriptorPath = null;
      IPath typeSystemDescriptorPath = null;
      try {
        engineDescriptorPath = RutaProjectUtils.getEngineDescriptorPath(r.getLocation(), project);
        typeSystemDescriptorPath = RutaProjectUtils.getTypeSystemDescriptorPath(
                fScript.getLocation(), project);
      } catch (CoreException e) {
        RutaAddonsPlugin.error(e);
      }
      // show message
      @SuppressWarnings({ "unchecked", "rawtypes" })
      ArrayList<TestCasData> testCasData = (ArrayList) debugPage.getViewer().getInput();
      int numFiles = testCasData.size();
      monitor.beginTask("Running evaluation, please wait", numFiles);

      // switch usage mode: old (only Ruta) vs. new (supports java/uimaFIT AEs)
      IPreferenceStore store = RutaAddonsPlugin.getDefault().getPreferenceStore();
      boolean javaSupportMode = store.getBoolean(TestingPreferenceConstants.EXTEND_CLASSPATH);
      if (!javaSupportMode) {
        // only Ruta mode (classpath NOT expanded)
        IStatus status = evalRutaOnlyScript(monitor, testPageView, debugPage, fScript, project,
                engineDescriptorPath, testCasData);
        if (status.getSeverity() == IStatus.ERROR) {
          Shell shell = Display.getCurrent().getActiveShell();
          MessageDialog.openWarning(shell, "Error", status.getMessage());
        }
        return status;
      } else {
        // * write clean run files into a temp directory, remember file names
        // TODO this approach (may) causes problems, when Java/uimaFIT engines have their own
        // typesystems...
        IStatus status = evalRutaWithClassPathScript(monitor, testPageView, debugPage, fScript,
                project, typeSystemDescriptorPath, testCasData);
        return status;
      }
    }

    private IStatus evalRutaWithClassPathScript(IProgressMonitor monitor,
            final TestPageBookView testPageView, final TestViewPage debugPage, final IFile fScript,
            final IProject project, IPath typeSystemDescriptorPath,
            ArrayList<TestCasData> testCasData) {
      final IPath cleanInputPath = project.getLocation()
              .append(RutaProjectUtils.getDefaultTestLocation())
              .append(RutaProjectUtils.getDefaultCleanTestLocation());
      clearFolder(project, cleanInputPath);
      writeCleanInputFiles(testCasData, project, typeSystemDescriptorPath, cleanInputPath, monitor);
      if (monitor.isCanceled()) {
        // TODO call monitor.done()
        return Status.CANCEL_STATUS;
      }

      // * apply script to the clean run files
      final IPath runTestPath = project.getLocation()
              .append(RutaProjectUtils.getDefaultTestLocation())
              .append(RutaProjectUtils.getDefaultTempTestLocation());
      clearFolder(project, runTestPath);
      runWithJVM(monitor, fScript, cleanInputPath, runTestPath);

      try {
        // * for each (goldFile, runFile)-pair:
        XMLInputSource in = new XMLInputSource(typeSystemDescriptorPath.toPortableString());
        TypeSystemDescription tsd = UIMAFramework.getXMLParser().parseTypeSystemDescription(in);
        CAS runCas = CasCreationUtils.createCas(tsd, null, null);
        CAS goldCas = CasCreationUtils.createCas(tsd, null, null);
        for (TestCasData td : testCasData) {
          // init etc
          runCas.reset();
          goldCas.reset();
          // deserialize CASes
          IPath path2RunFile = runTestPath.append(td.getPath().toFile().getName());
          String runFilePath = path2RunFile.toPortableString();
          File runFile = new File(runFilePath);
          deserializeCASs(goldCas, td, runCas, runFile);
          runCas = runCas.getView(viewCasName);
          goldCas = goldCas.getView(viewCasName);
          // ** create TP, FP, FN annotations
          // ** collect results and gather eval data
          evalLogicAndUpdateGUI(monitor, testPageView, debugPage, fScript, project, runCas,
                  goldCas, td);
          if (monitor.isCanceled()) {
            return Status.CANCEL_STATUS;
          }
        }
      } catch (Exception e) {
        RutaAddonsPlugin.error(e);
        monitor.done();
        testPageView.showBusy(false);
        return new Status(Status.ERROR, RutaAddonsPlugin.PLUGIN_ID,
                "Error during testing. See Error View for details.");
      }
      monitor.done();
      return Status.OK_STATUS;
    }

    /**
     * This method assumes that gold annotations have already been removed from the files. It just
     * applies the script to the files.
     * 
     * @param monitor
     * @param scriptFile
     * @param cleanInputPath
     */
    private void runWithJVM(IProgressMonitor monitor, IFile scriptFile, IPath cleanInputPath,
            IPath runOutputPath) {
      monitor.setTaskName(String.format("Processing script \"%s\" [w classpatch ext.].",
              scriptFile.getName()));

      IProject project = scriptFile.getProject();

      // init args
      String inputDirPath = null;
      String outputDirPath = null;
      if (cleanInputPath != null) {
        inputDirPath = cleanInputPath.toFile().getAbsolutePath();
      } else {
        // TODO throw exception
        return;
      }
      if (runOutputPath != null) {
        outputDirPath = runOutputPath.toFile().getAbsolutePath();
      } else {
        // TODO throw exception
        return;
      }

      try {
        IPath descriptorPath = RutaProjectUtils.getEngineDescriptorPath(scriptFile.getLocation(),
                project);
        String descriptorAbsolutePath = descriptorPath.toFile().getAbsolutePath();
        ILaunchManager mgr = DebugPlugin.getDefault().getLaunchManager();
        ILaunchConfigurationType type = mgr
                .getLaunchConfigurationType(RutaLaunchConfigurationConstants.ID_RUTA_SCRIPT);

        ILaunchConfigurationWorkingCopy copy = type.newInstance(null, scriptFile.getName()
                + ".Testing");
        // do not use RutaLaunchConstants.ARG_INPUT_FOLDER here
        copy.setAttribute(RutaLaunchConstants.INPUT_FOLDER, inputDirPath);
        // do not use RutaLaunchConstants.ARG_OUTPUT_FOLDER here
        copy.setAttribute(RutaLaunchConstants.OUTPUT_FOLDER, outputDirPath);
        copy.setAttribute(RutaLaunchConstants.ARG_DESCRIPTOR, descriptorAbsolutePath);
        copy.setAttribute(RutaLaunchConfigurationConstants.ATTR_PROJECT_NAME, project.getName());
        ILaunchConfiguration lc = copy.doSave();

        String mode = ILaunchManager.RUN_MODE;
        if (debug) {
          mode = ILaunchManager.DEBUG_MODE;
        }
        ILaunch launch = new Launch(lc, mode, null);

        ILaunchConfiguration launchConfiguration = launch.getLaunchConfiguration();

        ILaunch launched = launchConfiguration.launch(ILaunchManager.RUN_MODE, monitor);

        while (!launched.isTerminated()) {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            RutaAddonsPlugin.error(e);
          }
        }

      } catch (CoreException e) {
        RutaAddonsPlugin.error(e);
      }
    }

    private void clearFolder(final IProject project, final IPath folderPath) {
      FileUtils.deleteAllFiles(folderPath.toFile()); // clear folder
      try {
        project.getFolder(folderPath.makeRelativeTo(project.getLocation())).refreshLocal(
                IResource.DEPTH_INFINITE, new NullProgressMonitor());
      } catch (CoreException e1) {
        e1.printStackTrace();
      }
    }

    private void deserializeCASs(CAS tdCas, TestCasData td, CAS casA, File fileA)
            throws FileNotFoundException, SAXException, IOException {
      if (!fileA.exists()) {
        throw new FileNotFoundException(fileA.getAbsolutePath());
      }
      FileInputStream inputStream = null;
      try {
        inputStream = new FileInputStream(new File(td.getPath().toPortableString()));
        XmiCasDeserializer.deserialize(inputStream, tdCas, true);
      } finally {
        if (inputStream != null) {
          inputStream.close();
        }
      }
      try {
        inputStream = new FileInputStream(fileA);
        XmiCasDeserializer.deserialize(inputStream, casA, true);
      } finally {
        if (inputStream != null) {
          inputStream.close();
        }
      }
    }

    private void writeCleanInputFiles(List<TestCasData> testCasData, IProject project,
            IPath tsDescriptorPath, IPath cleanInputPath, IProgressMonitor monitor) {
      try {
        // create CAS:
        XMLInputSource in = new XMLInputSource(tsDescriptorPath.toPortableString());
        TypeSystemDescription tsd = UIMAFramework.getXMLParser().parseTypeSystemDescription(in);

        CAS cleanCas = getEmptyCas(tsd);

        for (TestCasData td : testCasData) {
          // init etc
          cleanCas.reset(); // clean
          // deserialize CASes
          FileInputStream inputStreamRun = null;
          try {
            inputStreamRun = new FileInputStream(new File(td.getPath().toPortableString()));
            XmiCasDeserializer.deserialize(inputStreamRun, cleanCas, true);
          } finally {
            if (inputStreamRun != null) {
              inputStreamRun.close();
            }
          }
          cleanCas = cleanCas.getView(viewCasName);
          prepareCas(cleanCas);
          // store clean CAS
          IPath path2CleanFile = computeCleanPath(cleanInputPath, td);
          String fPath = path2CleanFile.toPortableString();
          File cleanFile = new File(fPath);
          writeXmi(cleanCas, cleanFile);

          td.setResultPath(path2CleanFile);

          if (monitor.isCanceled()) {
            return;
          }
        }

        cleanCas.release();
        project.getFolder(cleanInputPath.makeRelativeTo(project.getLocation())).refreshLocal(
                IResource.DEPTH_INFINITE, new NullProgressMonitor());
      } catch (Exception e) {
        RutaAddonsPlugin.error(e);
      }
    }

    private IPath computeCleanPath(IPath cleanInputPath, TestCasData td) {
      return cleanInputPath.append(td.getPath().removeFileExtension().lastSegment() + ".xmi");
    }

    private IStatus evalRutaOnlyScript(IProgressMonitor monitor,
            final TestPageBookView testPageView, final TestViewPage debugPage, IFile fScript,
            final IProject project, final IPath engineDescriptorPath,
            ArrayList<TestCasData> testCasData) {
      try {
        // create AE:
        XMLInputSource in = new XMLInputSource(engineDescriptorPath.toPortableString());
        ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
        AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);

        if (debug) {
          ae.setConfigParameterValue(RutaEngine.PARAM_DEBUG, true);
          ae.setConfigParameterValue(RutaEngine.PARAM_DEBUG_WITH_MATCHES, true);
          ae.setConfigParameterValue(RutaEngine.PARAM_PROFILE, true);
          ae.setConfigParameterValue(RutaEngine.PARAM_STATISTICS, true);
          ae.setConfigParameterValue(RutaEngine.PARAM_CREATED_BY, true);
        }
        ae.reconfigure();

        // create (empty) CAS objects:
        String desc = null;
        desc = engineDescriptorPath.toPortableString();
        XMLInputSource in2 = new XMLInputSource(desc);
        Object descriptor = UIMAFramework.getXMLParser().parse(in2);
        CAS runCas = getEmptyCas(descriptor);
        CAS testCas = getEmptyCas(descriptor);

        for (TestCasData td : testCasData) {
          // init td etc
          runCas.reset();
          testCas.reset();
          String elementName = fScript.getLocation().lastSegment();
          monitor.setTaskName("Processing [w/o classpatch ext.] " + td.getPath().lastSegment());
          int lastIndexOf = elementName.lastIndexOf(RutaEngine.SCRIPT_FILE_EXTENSION);
          if (lastIndexOf != -1) {
            elementName = elementName.substring(0, lastIndexOf);
          }
          // deserialize CASes
          FileInputStream inputStreamTest = null;
          try {
            inputStreamTest = new FileInputStream(new File(td.getPath().toPortableString()));
            XmiCasDeserializer.deserialize(inputStreamTest, testCas, true);
          } finally {
            if (inputStreamTest != null) {
              inputStreamTest.close();
            }
          }
          FileInputStream inputStreamRun = null;
          try {
            inputStreamRun = new FileInputStream(new File(td.getPath().toPortableString()));
            XmiCasDeserializer.deserialize(inputStreamRun, runCas, true);
          } finally {
            if (inputStreamRun != null) {
              inputStreamRun.close();
            }
          }
          testCas = testCas.getView(viewCasName);
          runCas = runCas.getView(viewCasName);

          // gather uima eval-types
          prepareCas(runCas);

          // process run cas and evaluate it
          ae.process(runCas);
          evalLogicAndUpdateGUI(monitor, testPageView, debugPage, fScript, project, runCas,
                  testCas, td);
          if (monitor.isCanceled()) {
            ae.destroy();
            return Status.CANCEL_STATUS;
          }
        }
        ae.destroy();
      } catch (Exception e) {
        RutaAddonsPlugin.error(e);
        monitor.done();
        testPageView.showBusy(false);
        return new Status(Status.ERROR, RutaAddonsPlugin.PLUGIN_ID,
                "Error during testing. See Error View for details.");
      }

      monitor.done();
      testPageView.showBusy(false);
      return Status.OK_STATUS;
    }

    private void prepareCas(CAS cas) {
      if (!includedTypes.isEmpty()) {
        // exclude all other types if there are some included types
        excludedTypes = new ArrayList<String>();
        List<Type> types = cas.getTypeSystem().getProperlySubsumedTypes(cas.getAnnotationType());
        for (Type type : types) {
          if (!includedTypes.contains(type.getName())) {
            excludedTypes.add(type.getName());
          }
        }
      }
      if (includedTypes.isEmpty() && excludedTypes.isEmpty()) {
        // remove all annotation in default settings
        String documentText = cas.getDocumentText();
        cas.reset();
        cas.setDocumentText(documentText);
      } else {
        List<AnnotationFS> toRemove = new LinkedList<AnnotationFS>();
        AnnotationIndex<AnnotationFS> annotationIndex = cas.getAnnotationIndex();
        for (AnnotationFS annotationFS : annotationIndex) {
          Type type = annotationFS.getType();
          String typeName = type.getName();
          if (includedTypes.contains(typeName) || !excludedTypes.contains(typeName)) {
            toRemove.add(annotationFS);
          }
        }
        for (AnnotationFS each : toRemove) {
          if (!cas.getDocumentAnnotation().equals(each)) {
            cas.removeFsFromIndexes(each);
          }
        }
      }
    }

    private void evalLogicAndUpdateGUI(IProgressMonitor monitor,
            final TestPageBookView testPageView, final TestViewPage debugPage, IFile fScript,
            final IProject project, CAS runCas, CAS goldCas, TestCasData td)
            throws AnalysisEngineProcessException, CASException, IOException, SAXException,
            CoreException {
      // memento for prefs
      IPreferenceStore store = RutaAddonsPlugin.getDefault().getPreferenceStore();
      String factoryName = store.getString(TestingPreferenceConstants.EVALUATOR_FACTORY);
      ICasEvaluator evaluator = RutaAddonsPlugin.getCasEvaluatorFactoryById(factoryName)
              .createEvaluator();
      boolean includeSubtypes = store.getBoolean(TestingPreferenceConstants.INCLUDE_SUBTYPES);
      boolean useAllTypes = store.getBoolean(TestingPreferenceConstants.ALL_TYPES);

      CAS resultCas = evaluator.evaluate(goldCas, runCas, excludedTypes, includeSubtypes,
              useAllTypes);

      // store results
      IPath path2Test = td.getPath().removeLastSegments(1);

      monitor.setTaskName("Actually evaluating " + td.getPath().lastSegment());

      IPath estimatedTestPath = project.getFullPath().append(
              RutaProjectUtils.getDefaultTestLocation());
      IPath path2recource = fScript.getFullPath();
      IPath projectRelativePath2Script = path2recource.removeFirstSegments(2);
      IPath estimatedTestFolderPath = estimatedTestPath.append(projectRelativePath2Script
              .removeFileExtension());

      IPath path2Result = path2Test.append(TestCasData.RESULT_FOLDER);
      IPath path2ResultFile = path2Result.append(td.getPath().removeFileExtension().lastSegment()
              + ".result.xmi");

      if (!path2Test.toOSString().contains(estimatedTestFolderPath.toOSString())) {
        path2Result = project.getLocation().append(RutaProjectUtils.getDefaultTestLocation())
                .append(RutaProjectUtils.getDefaultTempTestLocation());
        path2ResultFile = path2Result.append(td.getPath().removeFileExtension().lastSegment()
                + ".result.xmi");
      }

      File resultFile = new File(path2ResultFile.toPortableString());
      writeXmi(resultCas, resultFile);

      td.setResultPath(path2ResultFile);

      // finally, calculate eval data and show it in the GUI
      EvalDataProcessor.calculateEvaluatData(td, resultCas);

      testPageView.getDefaultPage().getControl().getDisplay().asyncExec(new Runnable() {
        public void run() {
          debugPage.getViewer().refresh();
        }
      });
      monitor.worked(1);
      project.getFolder(path2Result.makeRelativeTo(project.getLocation())).refreshLocal(
              IResource.DEPTH_INFINITE, new NullProgressMonitor());
      runCas.release();
      goldCas.release();
      resultCas.release();
    }

    private CAS getEmptyCas(Object descriptor) throws ResourceInitializationException,
            InvalidXMLException {
      CAS testCas = null;
      if (descriptor instanceof AnalysisEngineDescription) {
        testCas = CasCreationUtils.createCas((AnalysisEngineDescription) descriptor);
      } else if (descriptor instanceof TypeSystemDescription) {
        TypeSystemDescription tsDesc = (TypeSystemDescription) descriptor;
        tsDesc.resolveImports();
        testCas = CasCreationUtils.createCas(tsDesc, null, new FsIndexDescription[0]);
      }
      return testCas;
    }
  }

  public void addHandlerListener(IHandlerListener handlerListener) {

  }

  public void dispose() {

  }

  public Object execute(ExecutionEvent event) throws ExecutionException {
    TestPageBookView debugView = (TestPageBookView) HandlerUtil.getActivePart(event);
    if (!(debugView.getCurrentPage() instanceof TestViewPage)) {
      return Status.CANCEL_STATUS;
    }
    TestViewPage debugPage = (TestViewPage) debugView.getCurrentPage();
    // set debug by preference
    boolean debug = true;
    String viewCasName = debugPage.getSelectedViewCasName();
    String scriptName = debugPage.getResource().getLocation().lastSegment();
    RerunHandlerJob job = new RerunHandlerJob(event, scriptName, viewCasName,
            debugPage.getExcludedTypes(), debugPage.getIncludedTypes(), debug);

    job.addJobChangeListener(new DebugJobChangeAdapter(debugPage) {
    });

    job.schedule();

    return null;
  }

  private static void writeXmi(CAS aCas, File file) throws IOException, SAXException {
    FileOutputStream out = null;
    try {
      file.getParentFile().mkdirs();
      out = new FileOutputStream(file);
      XmiCasSerializer.serialize(aCas, out);
    } catch (Exception e) {
      RutaAddonsPlugin.error(e);
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }

  public boolean isEnabled() {
    return true;
  }

  public boolean isHandled() {
    return true;
  }

  public void removeHandlerListener(IHandlerListener handlerListener) {

  }

  /**
   * 
   * @param data
   *          will be filled with results
   * @param resultCas
   *          must contain TP, FP, FN annotations
   */
  public void calculateEvaluatData(TestCasData data, CAS resultCas) {
    data.setEvaluationStatus(true);
    TypeSystem ts = resultCas.getTypeSystem();
    Type falsePositiveType = ts.getType(ICasEvaluator.FALSE_POSITIVE);
    Type falseNegativeType = ts.getType(ICasEvaluator.FALSE_NEGATIVE);
    Type truePositiveType = ts.getType(ICasEvaluator.TRUE_POSITIVE);

    int falsePositiveCount = resultCas.getAnnotationIndex(falsePositiveType).size();
    int falseNegativeCount = resultCas.getAnnotationIndex(falseNegativeType).size();
    int truePositiveCount = resultCas.getAnnotationIndex(truePositiveType).size();

    data.setTruePositiveCount(truePositiveCount);
    data.setFalsePositiveCount(falsePositiveCount);
    data.setFalseNegativeCount(falseNegativeCount);

    HashMap<String, TypeEvalData> map = new HashMap<String, TypeEvalData>();

    AnnotationIndex<AnnotationFS> index = resultCas.getAnnotationIndex(truePositiveType);

    FSIterator<AnnotationFS> iter = index.iterator();

    while (iter.isValid()) {
      EvalAnnotation a = (EvalAnnotation) iter.next();
      Annotation original = a.getOriginal();
      Type originalType = original.getType();

      if (map.containsKey(originalType.getName())) {
        TypeEvalData element = (TypeEvalData) map.get(originalType.getName());
        int oldCount = element.getTruePositives();
        element.setTruePositives(oldCount + 1);
      } else {
        TypeEvalData newData = new TypeEvalData(originalType.getName(), 1, 0, 0);
        map.put(originalType.getName(), newData);
      }
    }

    index = resultCas.getAnnotationIndex(falsePositiveType);
    iter = index.iterator();

    while (iter.isValid()) {
      EvalAnnotation a = (EvalAnnotation) iter.next();
      Annotation original = a.getOriginal();
      Type originalType = original.getType();

      if (map.containsKey(originalType.getName())) {
        TypeEvalData element = (TypeEvalData) map.get(originalType.getName());
        int oldCount = element.getFalsePositives();
        element.setFalsePositives(oldCount + 1);
      } else {
        TypeEvalData newData = new TypeEvalData(originalType.getName(), 0, 1, 0);
        map.put(originalType.getName(), newData);
      }
    }

    index = resultCas.getAnnotationIndex(falseNegativeType);
    iter = index.iterator();

    while (iter.isValid()) {
      EvalAnnotation a = (EvalAnnotation) iter.next();
      Annotation original = a.getOriginal();
      Type originalType = original.getType();

      if (map.containsKey(originalType.getName())) {
        TypeEvalData element = (TypeEvalData) map.get(originalType.getName());
        int oldCount = element.getFalseNegatives();
        element.setFalseNegatives(oldCount + 1);
      } else {
        TypeEvalData newData = new TypeEvalData(originalType.getName(), 0, 0, 1);
        map.put(originalType.getName(), newData);
      }
    }

    data.setTypeEvalData(map);
  }

}
