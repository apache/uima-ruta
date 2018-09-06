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

package org.apache.uima.ruta.query.ui;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.resource.impl.ResourceManager_impl;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.extensions.IRutaActionExtension;
import org.apache.uima.ruta.extensions.IRutaBlockExtension;
import org.apache.uima.ruta.extensions.IRutaBooleanFunctionExtension;
import org.apache.uima.ruta.extensions.IRutaConditionExtension;
import org.apache.uima.ruta.extensions.IRutaNumberFunctionExtension;
import org.apache.uima.ruta.extensions.IRutaStringFunctionExtension;
import org.apache.uima.ruta.extensions.IRutaTypeFunctionExtension;
import org.apache.uima.ruta.ide.core.RutaExtensionManager;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.CasIOUtils;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.ui.handlers.HandlerUtil;

public class QueryActionHandler implements IHandler {

  private class QueryJobChangeAdapter extends JobChangeAdapter {

    private QueryComposite composite;

    QueryJobChangeAdapter(QueryComposite composite) {
      super();
      this.composite = composite;
    }

    @Override
    public void done(IJobChangeEvent event) {
      if (event.getResult().isOK()) {
        composite.getDisplay().asyncExec(new Runnable() {
          public void run() {
            composite.update();
          }
        });
      }
    }
  }

  private class QueryHandlerJob extends Job {
    ExecutionEvent event;

    private boolean recursive;

    private String rules;

    private String typeSystemLocation;

    private String dataLocation;

    private final String fileNameFilter;

    QueryHandlerJob(ExecutionEvent event, String dir, String fileNameFilter, String typeSystem,
            String rules, boolean recursive) {
      super(String.format("Query in \"%s\" [filter: \"%s\"] ...", dir, fileNameFilter));
      this.event = event;
      this.dataLocation = dir;
      this.fileNameFilter = fileNameFilter;
      this.typeSystemLocation = typeSystem;
      this.rules = rules;
      this.recursive = recursive;
      setUser(true);
    }

    private String getText(File each) {
      try {
        return FileUtils.file2String(each, "UTF-8");
      } catch (IOException e) {
        DLTKCore.error(e.getMessage(), e);
      }
      return "";
    }

    @Override
    public IStatus run(IProgressMonitor monitor) {
      final QueryView queryView = (QueryView) HandlerUtil.getActivePart(event);
      final QueryComposite queryComposite = queryView.getComposite();
      // queryView.saveState();
      queryView.showBusy(true);
      monitor.beginTask("Initializing analysis engine...", 1);
      queryComposite.getDisplay().asyncExec(new Runnable() {
        public void run() {
          queryComposite.setResult(null);
        }
      });

      int files = 0;
      int found = 0;

      if (monitor.isCanceled())
        return Status.CANCEL_STATUS;

      final List<QueryResult> result = new ArrayList<QueryResult>();
      String script = "PACKAGE query;\n\n";
      // script += "TYPESYSTEM " + typeSystemFileText.getText();
      script += rules;
      try {
        URL aedesc = RutaEngine.class.getResource("BasicEngine.xml");
        XMLInputSource inae = new XMLInputSource(aedesc);
        IFile iFile = QueryComposite.getIFile(typeSystemLocation);
        IProject project = iFile.getProject();
        ClassLoader classLoader = RutaProjectUtils.getClassLoader(project);
        ResourceManager resMgr = new ResourceManager_impl(classLoader);
        ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(inae);
        AnalysisEngineDescription aed = (AnalysisEngineDescription) specifier;
        TypeSystemDescription basicTypeSystem = aed.getAnalysisEngineMetaData().getTypeSystem();

        if (!StringUtils.isEmpty(typeSystemLocation)) {
          // TODO check on valid input and extend for scr
          String tsLocation = typeSystemLocation;
          Collection<TypeSystemDescription> tsds = new ArrayList<TypeSystemDescription>();
          tsds.add(basicTypeSystem);
          if (typeSystemLocation.endsWith(RutaEngine.SCRIPT_FILE_EXTENSION)) {
            IPath scriptPath = iFile.getLocation();
            IPath descriptorRootPath = RutaProjectUtils.getDescriptorRootPath(project);
            resMgr.setDataPath(descriptorRootPath.toPortableString());
            IPath path = RutaProjectUtils.getTypeSystemDescriptorPath(scriptPath, project, classLoader);
            tsLocation = path.toPortableString();
          }
          File tsFile = new File(tsLocation);
          XMLInputSource ints = new XMLInputSource(tsFile);
          TypeSystemDescription importTSD = UIMAFramework.getXMLParser()
                  .parseTypeSystemDescription(ints);
          importTSD.resolveImports(resMgr);
          tsds.add(importTSD);
          TypeSystemDescription mergeTypeSystems = CasCreationUtils.mergeTypeSystems(tsds);
          aed.getAnalysisEngineMetaData().setTypeSystem(mergeTypeSystems);
        }
        aed.resolveImports(resMgr);
        AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(aed, resMgr, null);
        File tempFile = File.createTempFile("RutaQuery", RutaEngine.SCRIPT_FILE_EXTENSION);
        tempFile.deleteOnExit();
        FileUtils.saveString2File(script, tempFile, "UTF-8");
        String portableString = Path.fromOSString(tempFile.getParentFile().getPath())
                .toPortableString();
        ae.setConfigParameterValue(RutaEngine.PARAM_SCRIPT_PATHS, new String[] { portableString });
        String name = tempFile.getName().substring(0, tempFile.getName().length() - 5);
        ae.setConfigParameterValue(RutaEngine.PARAM_MAIN_SCRIPT, name);

        ae.setConfigParameterValue(RutaEngine.PARAM_DEBUG, true);
        ae.setConfigParameterValue(RutaEngine.PARAM_DEBUG_WITH_MATCHES, true);
        ae.setConfigParameterValue(RutaEngine.PARAM_PROFILE, false);
        ae.setConfigParameterValue(RutaEngine.PARAM_STATISTICS, false);

        IRutaConditionExtension[] conditionExtensions = RutaExtensionManager.getDefault()
                .getRutaConditionExtensions();
        IRutaActionExtension[] actionExtensions = RutaExtensionManager.getDefault()
                .getRutaActionExtensions();
        IRutaBooleanFunctionExtension[] booleanFunctionExtensions = RutaExtensionManager
                .getDefault().getRutaBooleanFunctionExtensions();
        IRutaNumberFunctionExtension[] numberFunctionExtensions = RutaExtensionManager.getDefault()
                .getRutaNumberFunctionExtensions();
        IRutaStringFunctionExtension[] stringFunctionExtensions = RutaExtensionManager.getDefault()
                .getRutaStringFunctionExtensions();
        IRutaTypeFunctionExtension[] typeFunctionExtensions = RutaExtensionManager.getDefault()
                .getRutaTypeFunctionExtensions();
        IRutaBlockExtension[] blockExtensions = RutaExtensionManager.getDefault()
                .getRutaBlockExtensions();

        List<String> languageExtensions = new ArrayList<String>();

        for (IRutaConditionExtension each : conditionExtensions) {
          languageExtensions.add(each.getClass().getName());
        }
        for (IRutaActionExtension each : actionExtensions) {
          languageExtensions.add(each.getClass().getName());
        }
        for (IRutaBooleanFunctionExtension each : booleanFunctionExtensions) {
          languageExtensions.add(each.getClass().getName());
        }
        for (IRutaNumberFunctionExtension each : numberFunctionExtensions) {
          languageExtensions.add(each.getClass().getName());
        }
        for (IRutaStringFunctionExtension each : stringFunctionExtensions) {
          languageExtensions.add(each.getClass().getName());
        }
        for (IRutaTypeFunctionExtension each : typeFunctionExtensions) {
          languageExtensions.add(each.getClass().getName());
        }
        for (IRutaBlockExtension each : blockExtensions) {
          languageExtensions.add(each.getClass().getName());
        }
        ae.setConfigParameterValue(RutaEngine.PARAM_ADDITIONAL_EXTENSIONS,
                languageExtensions.toArray(new String[0]));

        ae.reconfigure();
        CAS cas = ae.newCAS();

        monitor.worked(1);

        if (monitor.isCanceled()) {
          if (ae != null) {
            ae.destroy();
          }
          if (cas != null) {
            cas.release();
          }
          return Status.CANCEL_STATUS;
        }

        File dir = new File(dataLocation);
        List<File> inputFiles = getFiles(dir, recursive);
        monitor.beginTask("Query in " + dir.getName() + "...", inputFiles.size());

        for (File each : inputFiles) {

          monitor.setTaskName("Query in " + each.getName() + "...");

          if (monitor.isCanceled()) {
            if (ae != null) {
              ae.destroy();
            }
            if (cas != null) {
              cas.release();
            }
            return Status.CANCEL_STATUS;
          }

          cas.reset();
          if (FilenameUtils.getExtension(each.getName()).equalsIgnoreCase("xmi") ||
                  FilenameUtils.getExtension(each.getName()).equalsIgnoreCase("bcas") ||
                  FilenameUtils.getExtension(each.getName()).equalsIgnoreCase("scas") ||
                  FilenameUtils.getExtension(each.getName()).equalsIgnoreCase("xcas")) {
            CasIOUtils.load(each.toURI().toURL(), cas);
          } else {
            cas.setDocumentText(getText(each));
          }

          Type matchedType = cas.getTypeSystem().getType(
                  "org.apache.uima.ruta.type.DebugMatchedRuleMatch");
          Type ruleApplyType = cas.getTypeSystem().getType(
                  "org.apache.uima.ruta.type.DebugRuleApply");
          Type blockApplyType = cas.getTypeSystem().getType(
                  "org.apache.uima.ruta.type.DebugBlockApply");

          removeDebugAnnotations(cas, matchedType, ruleApplyType, blockApplyType);

          ae.process(cas);

          Feature innerApplyFeature = blockApplyType.getFeatureByBaseName("innerApply");
          Feature ruleApplyFeature = blockApplyType.getFeatureByBaseName("rules");
          FSIterator<AnnotationFS> iterator = cas.getAnnotationIndex(blockApplyType).iterator();
          boolean foundOne = false;
          while (iterator.isValid()) {
            if (monitor.isCanceled()) {
              if (ae != null) {
                ae.destroy();
              }
              if (cas != null) {
                cas.release();
              }
              return Status.CANCEL_STATUS;
            }
            AnnotationFS fs = iterator.get();
            int find = findRuleMatches(result, fs, each, queryComposite, matchedType,
                    ruleApplyType, blockApplyType, innerApplyFeature, ruleApplyFeature);
            iterator.moveToNext();
            found += find;
            if (!foundOne && find > 0) {
              foundOne = true;
              files++;
            }

            final int constFound = found;
            final int constFiles = files;
            queryComposite.getDisplay().syncExec(new Runnable() {
              public void run() {
                queryComposite.setResult(result);
                queryComposite.setResultInfo(constFound, constFiles);
              }
            });

          }

          monitor.worked(1);
        }
        cas.release();
        ae.destroy();
        monitor.done();
      } catch (Exception e) {
        // report error in query view:
        queryComposite.getDisplay().syncExec(new Runnable() {
          public void run() {
            queryComposite.setRutaQuerySyntaxError(true);
          }
        });
        RutaAddonsPlugin.error(e);
      }

      return Status.OK_STATUS;

    }

    public List<File> getFiles(File dir, boolean recusive) {
      List<File> result = new ArrayList<File>();
      if (fileNameFilter == null) {
        return result;
      }
      FileFilter fileFilter = new FileFilter() {

        public boolean accept(File pathname) {
          if (Pattern.matches(fileNameFilter, pathname.getName())) {
            return true;
          }
          return false;
        }
      };
      for (File each : dir.listFiles(fileFilter)) {
        result.add(each);
      }
      if (recusive) {
        for (File subdir : dir.listFiles(new FileFilter() {

          public boolean accept(File pathname) {
            return pathname.isDirectory();
          }
        })) {
          result.addAll(getFiles(subdir, recusive));
        }
      }
      return result;
    }

    private void removeDebugAnnotations(CAS cas, Type matchedType, Type ruleApplyType,
            Type blockApplyType) {
      Collection<AnnotationFS> toRemove = new ArrayList<AnnotationFS>();
      AnnotationIndex<AnnotationFS> annotationIndex = cas.getAnnotationIndex(blockApplyType);
      for (AnnotationFS annotationFS : annotationIndex) {
        toRemove.add(annotationFS);
      }
      annotationIndex = cas.getAnnotationIndex(ruleApplyType);
      for (AnnotationFS annotationFS : annotationIndex) {
        toRemove.add(annotationFS);
      }
      annotationIndex = cas.getAnnotationIndex(matchedType);
      for (AnnotationFS annotationFS : annotationIndex) {
        toRemove.add(annotationFS);
      }
      for (AnnotationFS annotationFS : toRemove) {
        cas.removeFsFromIndexes(annotationFS);
      }
    }

    public int findRuleMatches(final List<QueryResult> result, AnnotationFS fs, File file,
            final QueryComposite queryComposite, Type matchedType, Type ruleApplyType,
            Type blockApplyType, Feature innerApplyFeature, Feature ruleApplyFeature) {
      int ret = 0;
      if (fs.getType().equals(blockApplyType)) {
        FeatureStructure featureValue = fs.getFeatureValue(innerApplyFeature);
        FSArray array = (FSArray) featureValue;
        for (int i = 0; i < array.size(); i++) {
          AnnotationFS eachApply = (AnnotationFS) array.get(i);
          ret += findRuleMatches(result, eachApply, file, queryComposite, matchedType,
                  ruleApplyType, blockApplyType, innerApplyFeature, ruleApplyFeature);
        }
      } else if (fs.getType().equals(ruleApplyType)) {
        FeatureStructure featureValue = fs.getFeatureValue(ruleApplyFeature);
        FSArray array = (FSArray) featureValue;
        for (int i = 0; i < array.size(); i++) {
          AnnotationFS eachApply = (AnnotationFS) array.get(i);
          ret += findRuleMatches(result, eachApply, file, queryComposite, matchedType,
                  ruleApplyType, blockApplyType, innerApplyFeature, ruleApplyFeature);
        }
      } else if (fs.getType().equals(matchedType)) {
        result.add(new QueryResult(fs.getBegin(), fs.getEnd(), fs.getCoveredText(), file));
        ret += 1;

      }
      return ret;
    }
  }

  public void addHandlerListener(IHandlerListener handlerListener) {

  }

  public void dispose() {

  }

  public Object execute(ExecutionEvent event) throws ExecutionException {

    QueryView queryView = (QueryView) HandlerUtil.getActivePart(event);
    QueryComposite queryComposite = queryView.getComposite();

    String dir = queryComposite.getDataDirectory();
    String fileNameFilter = queryComposite.getFileFilter();
    String typesystem = queryComposite.getTypeSystem();
    String script = queryComposite.getScript();
    boolean recurive = queryComposite.isRecursive();
    QueryHandlerJob job = new QueryHandlerJob(event, dir, fileNameFilter, typesystem, script,
            recurive);

    job.addJobChangeListener(new QueryJobChangeAdapter(queryComposite) {
    });

    job.schedule();

    return null;
  }

  public boolean isEnabled() {
    return true;
  }

  public boolean isHandled() {
    return true;
  }

  public void removeHandlerListener(IHandlerListener handlerListener) {

  }
}
