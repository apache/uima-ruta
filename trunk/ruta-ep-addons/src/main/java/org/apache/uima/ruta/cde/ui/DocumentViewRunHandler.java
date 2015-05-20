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

package org.apache.uima.ruta.cde.ui;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.UIMAFramework;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.impl.XmiCasDeserializer;
import org.apache.uima.resource.metadata.FsIndexDescription;
import org.apache.uima.resource.metadata.TypePriorities;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.apache.uima.ruta.cde.IRutaConstraint;
import org.apache.uima.ruta.cde.IRutaRuleConstraint;
import org.apache.uima.ruta.cde.utils.ConstraintData;
import org.apache.uima.ruta.cde.utils.DocumentData;
import org.apache.uima.ruta.cde.utils.EvaluationMeasures;
import org.apache.uima.ruta.testing.evaluator.ICasEvaluator;
import org.apache.uima.ruta.testing.preferences.TestingPreferenceConstants;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

public class DocumentViewRunHandler implements IHandler {

  private class DocumentViewRunJobChangeAdapter extends JobChangeAdapter {

    // Composite of the ConstraintSelectView
    private DocumentSelectComposite composite;

    DocumentViewRunJobChangeAdapter(DocumentSelectComposite composite) {
      super();
      this.composite = composite;
    }

    @Override
    public void done(IJobChangeEvent event) {
      if (event.getResult().isOK()) {
        updateUI(composite);
      }
    }

  }

  private void updateUI(final DocumentSelectComposite composite) {
    composite.getDisplay().asyncExec(new Runnable() {
      public void run() {
        try {
          composite.getViewer().refresh();
          ArrayList<Double[]> results = new ArrayList<Double[]>();
          if (composite.getTestDataPath() != null) {
            for (DocumentData data : composite.getDocumentList()) {
              double result = data.getAugmentedResult();
              double score = data.getFMeasure();
              results.add(new Double[] { result, score });
            }
          }
          composite.updateMeasureReport("" + EvaluationMeasures.getMeasureReport(results));
        } catch (Exception e) {
          RutaAddonsPlugin.error(e);
        }
        composite.getViewer().refresh();
        composite.update();
      }
    });
  }

  private class DocumentViewRunHandlerJob extends Job {

    private List<ConstraintData> constraints;

    private List<DocumentData> documents;

    private File typeSystem;

    private String testDataPath;

    private final DocumentSelectComposite composite;

    DocumentViewRunHandlerJob(ExecutionEvent event, List<ConstraintData> constraints,
            ArrayList<DocumentData> documents, File typeSystem, String testDataPath,
            DocumentSelectComposite composite) {
      super("UIMA Ruta CDE");
      this.constraints = constraints;
      this.documents = documents;
      this.typeSystem = typeSystem;
      this.testDataPath = testDataPath;
      this.composite = composite;
    }

    public IStatus run(IProgressMonitor monitor) {

      monitor.beginTask("UIMA Ruta CDE", constraints.size() * documents.size());
      CAS inputCAS = null;
      CAS testCas = null;
      try {
        URL tpUrl = this.getClass().getResource("/org/apache/uima/ruta/engine/TypePriorities.xml");
        TypePriorities typePriorities = UIMAFramework.getXMLParser().parseTypePriorities(
                new XMLInputSource(tpUrl));
        TypeSystemDescription descriptor = (TypeSystemDescription) UIMAFramework.getXMLParser()
                .parse(new XMLInputSource(typeSystem));
        inputCAS = CasCreationUtils
                .createCas(descriptor, typePriorities, new FsIndexDescription[0]);
        testCas = CasCreationUtils.createCas(descriptor, null, new FsIndexDescription[0]);
      } catch (Exception e) {
        RutaAddonsPlugin.error(e);
      }
      for (DocumentData document : documents) {
        if (monitor.isCanceled()) {
          return Status.CANCEL_STATUS;
        }
        double count = 0;
        double augResult = 0;
        document.setResults(new ArrayList<String[]>());
        if (constraints.size() > 0) {
          monitor.setTaskName("UIMA Ruta CDE: " + document.getDocument().getName());
          try {
            inputCAS.reset();
            XmiCasDeserializer.deserialize(new FileInputStream(document.getDocument()), inputCAS,
                    true);
            for (ConstraintData constraintData : constraints) {
              if (monitor.isCanceled()) {
                return Status.CANCEL_STATUS;
              }
              String[] partialResult = new String[2];
              IRutaConstraint constraint = constraintData.getConstraint();
              if (constraint instanceof IRutaRuleConstraint) {
                ((IRutaRuleConstraint) constraint).setTypeSystemLocation(typeSystem
                        .getAbsolutePath());
              }
              // Calculating and adding results to the
              // documentData object
              Double partResult = constraint.processConstraint(inputCAS);
              if (partResult != null) {
                partialResult[0] = constraint.getDescription();
                partialResult[1] = String.valueOf(partResult);
                int weight = constraintData.getWeight();
                count += weight;
                double weightedResult = partResult * weight;
                augResult += weightedResult;
                // inputCAS.release();
                document.getResults().add(partialResult);
              }

              monitor.worked(1);
            }

            if (testDataPath != null) {
              // Hashmap to save all the FileNames without
              // fileExtension and FullPath, both as
              // strings
              HashMap<String, String> testFilesMap = createFileMap(testDataPath);
              String documentFileName = getFileNameWithoutExtensions(document.getDocument()
                      .getAbsolutePath());
              if (testFilesMap.get(documentFileName) != null) {
                File testFile = new File(testFilesMap.get(documentFileName));
                testCas.reset();
                XmiCasDeserializer.deserialize(new FileInputStream(testFile), testCas, true);
                IPreferenceStore store = RutaAddonsPlugin.getDefault().getPreferenceStore();
                String factoryName = store.getString(TestingPreferenceConstants.EVALUATOR_FACTORY);
                boolean includeSubtypes = store.getBoolean(TestingPreferenceConstants.INCLUDE_SUBTYPES);
                boolean useAllTypes = store.getBoolean(TestingPreferenceConstants.ALL_TYPES);
                
                ICasEvaluator evaluator = RutaAddonsPlugin.getCasEvaluatorFactoryById(factoryName)
                        .createEvaluator();
                ArrayList<String> excludedTypes = new ArrayList<String>();
                CAS resultCas = evaluator.evaluate(testCas, inputCAS, excludedTypes, includeSubtypes, useAllTypes);
                document.setFMeasure(calculateF1(resultCas));
              }
            }
          } catch (Exception e) {
            RutaAddonsPlugin.error(e);
          }
        }
        if (count != 0) {
          augResult = augResult / count;
        }
        document.setAugmentedResult(augResult);
        updateUI(composite);
      }
      inputCAS.release();
      testCas.release();
      monitor.done();
      return Status.OK_STATUS;
    }
  }

  public void addHandlerListener(IHandlerListener arg0) {

  }

  public void dispose() {

  }

  public Object execute(ExecutionEvent event) throws ExecutionException {
    ConstraintSelectView constraintView;
    try {
      constraintView = (ConstraintSelectView) HandlerUtil.getActiveWorkbenchWindow(event)
              .getWorkbench().getActiveWorkbenchWindow().getActivePage()
              .showView("org.apache.uima.ruta.cde.ui.ConstraintSelectView");
      ConstraintSelectComposite composite = (ConstraintSelectComposite) constraintView
              .getComposite();
      List<ConstraintData> constraintList = composite.getConstraintList();
      DocumentView docView = (DocumentView) HandlerUtil.getActiveWorkbenchWindow(event)
              .getWorkbench().getActiveWorkbenchWindow().getActivePage()
              .showView("org.apache.uima.ruta.cde.ui.DocumentView");
      DocumentSelectComposite docComposite = docView.getDocComposite();
      ArrayList<DocumentData> documents = docComposite.getDocumentList();
      if (documents.isEmpty()) {
        docComposite.setDocumentsByDir();
      }
      File typeSystem = docComposite.getTypeSystem();
      DocumentViewRunHandlerJob job = new DocumentViewRunHandlerJob(event, constraintList,
              documents, typeSystem, docComposite.getTestDataPath(), docComposite);
      job.addJobChangeListener(new DocumentViewRunJobChangeAdapter(docComposite));
      job.schedule();
    } catch (PartInitException e) {
      RutaAddonsPlugin.error(e);
    }

    return null;
  }

  public boolean isEnabled() {
    return true;
  }

  public boolean isHandled() {
    return true;
  }

  public void removeHandlerListener(IHandlerListener arg0) {

  }

  private double calculateF1(CAS resultCas) {
    TypeSystem ts = resultCas.getTypeSystem();

    Type falsePositiveType = ts.getType(ICasEvaluator.FALSE_POSITIVE);
    Type falseNegativeType = ts.getType(ICasEvaluator.FALSE_NEGATIVE);
    Type truePositiveType = ts.getType(ICasEvaluator.TRUE_POSITIVE);

    int falsePositiveCount = resultCas.getAnnotationIndex(falsePositiveType).size();
    int falseNegativeCount = resultCas.getAnnotationIndex(falseNegativeType).size();
    int truePositiveCount = resultCas.getAnnotationIndex(truePositiveType).size();

    double a = falsePositiveCount;
    double b = falseNegativeCount;
    double c = truePositiveCount;

    double precision = c / (c + a);
    double recall = c / (c + b);
    double fMeasure = 2 * (precision * recall) / (precision + recall);

    fMeasure = fMeasure * 10000;
    fMeasure = Math.round(fMeasure);
    fMeasure = fMeasure / 10000;

    return fMeasure;
  }

  public HashMap<String, String> createFileMap(String directoryFolderPath) {
    HashMap<String, String> filesMap = new HashMap<String, String>();
    File folder = new File(directoryFolderPath);
    if (folder.isDirectory()) {
      String[] files = folder.list(null);
      for (String fileName : files) {
        String fullPath = folder + System.getProperty("file.separator") + fileName;
        String fileNameWithoutExtension = getFileNameWithoutExtensions(fullPath);
        if (!StringUtils.isBlank(fileNameWithoutExtension)) {
          filesMap.put(fileNameWithoutExtension, fullPath);
        }
      }
    }
    return filesMap;
  }

  public String getFileNameWithoutExtensions(String path) {
    File f = new File(path);
    String fileNameNoExtension = "";
    // Check if the file for the given Path exists on the systems filesystem
    // and
    // if it is a file
    if (f.exists() && f.isFile()) {
      // get the filename between the last separator and the file
      // extension
      if (path.contains(".")) {
        int beginOfFileName = path.lastIndexOf(System.getProperty("file.separator")) + 1;
        int endOfFileName = path.indexOf(".");

        fileNameNoExtension = path.substring(beginOfFileName, endOfFileName);
      }
      // if there is no file extension
      else {
        int beginOfFileName = path.lastIndexOf(System.getProperty("file.separator"));
        fileNameNoExtension = path.substring(beginOfFileName);
      }
    }
    return fileNameNoExtension;
  }

}
