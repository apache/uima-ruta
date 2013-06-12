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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
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
import org.apache.uima.ruta.ide.RutaIdePlugin;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.apache.uima.ruta.testing.evaluator.ICasEvaluator;
import org.apache.uima.ruta.testing.preferences.TestingPreferenceConstants;
import org.apache.uima.ruta.testing.ui.views.TestCasData;
import org.apache.uima.ruta.testing.ui.views.TestPageBookView;
import org.apache.uima.ruta.testing.ui.views.TestViewPage;
import org.apache.uima.ruta.testing.ui.views.evalDataTable.TypeEvalData;
import org.apache.uima.ruta.testing.ui.views.util.EvalDataProcessor;
import org.apache.uima.ruta.type.EvalAnnotation;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.apache.uima.util.XMLSerializer;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.preference.IPreferenceStore;
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

  private class RerunHandlerJob extends Job {
    ExecutionEvent event;

    private final String viewCasName;

    private final List<String> excludedTypes;

    RerunHandlerJob(ExecutionEvent event, String scriptName, String viewCasName,
            List<String> excludedTypes) {
      super("Testing " + scriptName + "...");
      this.event = event;
      this.viewCasName = viewCasName;
      this.excludedTypes = excludedTypes;
      setUser(true);
    }

    @Override
    public IStatus run(IProgressMonitor monitor) {
      final TestPageBookView debugView = (TestPageBookView) HandlerUtil.getActivePart(event);
      final TestViewPage debugPage = (TestViewPage) debugView.getCurrentPage();
      debugPage.saveState();
      debugView.showBusy(true);
      IResource r = debugPage.getResource();
      ArrayList<TestCasData> testCasData = (ArrayList) debugPage.getViewer().getInput();
      monitor.beginTask("Running evaluation, please wait", testCasData.size());
      IProject project = r.getProject();
      IPath engineDescriptorPath = RutaProjectUtils.getEngineDescriptorPath(r.getLocation(),
              project);
      try {
        XMLInputSource in = new XMLInputSource(engineDescriptorPath.toPortableString());
        ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
        AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);

        String desc = null;
        desc = engineDescriptorPath.toPortableString();
        XMLInputSource in2 = new XMLInputSource(desc);
        Object descriptor = UIMAFramework.getXMLParser().parse(in2);
        CAS runCas = getEmptyCas(descriptor);
        CAS testCas = getEmptyCas(descriptor);

        for (TestCasData td : testCasData) {
          runCas.reset();
          testCas.reset();
          String elementName = r.getLocation().lastSegment();
          monitor.setTaskName("Evaluating " + td.getPath().lastSegment());
          int lastIndexOf = elementName.lastIndexOf(RutaEngine.SCRIPT_FILE_EXTENSION);
          if (lastIndexOf != -1) {
            elementName = elementName.substring(0, lastIndexOf);
          }

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

          if (excludedTypes != null && !excludedTypes.isEmpty()) {
            List<AnnotationFS> toRemove = new LinkedList<AnnotationFS>();
            for (String eachType : excludedTypes) {
              Type type = runCas.getTypeSystem().getType(eachType);
              if (type != null && runCas.getTypeSystem().subsumes(runCas.getAnnotationType(), type)) {
                AnnotationIndex<AnnotationFS> annotationIndex = runCas.getAnnotationIndex(type);
                for (AnnotationFS annotationFS : annotationIndex) {
                  toRemove.add(annotationFS);
                }
              }
            }
            for (AnnotationFS annotationFS : toRemove) {
              runCas.removeFsFromIndexes(annotationFS);
            }
          }

          IPreferenceStore store = RutaAddonsPlugin.getDefault().getPreferenceStore();
          String factoryName = store.getString(TestingPreferenceConstants.EVALUATOR_FACTORY);
          ICasEvaluator evaluator = RutaAddonsPlugin.getCasEvaluatorFactoryById(factoryName)
                  .createEvaluator();

          ae.process(runCas);
          CAS resultCas = evaluator.evaluate(testCas, runCas, excludedTypes);

          IPath path2Test = td.getPath().removeLastSegments(1);

          IPath estimatedTestPath = project.getFullPath().append(
                  RutaProjectUtils.getDefaultTestLocation());
          IPath path2recource = r.getFullPath();
          IPath projectRelativePath2Script = path2recource.removeFirstSegments(2);
          IPath estimatedTestFolderPath = estimatedTestPath.append(projectRelativePath2Script
                  .removeFileExtension());

          IPath path2Result = path2Test.append(TestCasData.RESULT_FOLDER);
          IPath path2ResultFile = path2Result.append(td.getPath().removeFileExtension()
                  .lastSegment()
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

          // calculateEvaluatData(td, resultCas);

          EvalDataProcessor.calculateEvaluatData(td, resultCas);

          debugView.getDefaultPage().getControl().getDisplay().asyncExec(new Runnable() {
            public void run() {
              debugPage.getViewer().refresh();
            }
          });
          monitor.worked(1);
          r.getProject().getFolder(path2Result)
                  .refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
          runCas.release();
          testCas.release();
          resultCas.release();
          ae.destroy();
          if (monitor.isCanceled())
            return Status.CANCEL_STATUS;
        }
      } catch (Exception e) {
        RutaIdePlugin.error(e);
      }

      monitor.done();
      debugView.showBusy(false);
      return Status.OK_STATUS;

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
    TestViewPage debugPage = (TestViewPage) debugView.getCurrentPage();

    String viewCasName = debugPage.getSelectedViewCasName();
    String scriptName = debugPage.getResource().getLocation().lastSegment();
    RerunHandlerJob job = new RerunHandlerJob(event, scriptName, viewCasName,
            debugPage.getExcludedTypes());

    job.addJobChangeListener(new DebugJobChangeAdapter(debugPage) {
    });

    job.schedule();

    return null;
  }

  private static void writeXmi(CAS aCas, File name) throws IOException, SAXException {
    FileOutputStream out = null;
    try {
      name.getParentFile().mkdirs();
      if (!name.exists()) {
        name.createNewFile();
      }
      out = new FileOutputStream(name);
      XmiCasSerializer ser = new XmiCasSerializer(aCas.getTypeSystem());
      XMLSerializer xmlSer = new XMLSerializer(out, false);
      ser.serialize(aCas, xmlSer.getContentHandler());
    } catch (Exception e) {
      RutaIdePlugin.error(e);
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

    HashMap map = new HashMap();

    AnnotationIndex<AnnotationFS> index = resultCas.getAnnotationIndex(truePositiveType);

    FSIterator iter = index.iterator();

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
