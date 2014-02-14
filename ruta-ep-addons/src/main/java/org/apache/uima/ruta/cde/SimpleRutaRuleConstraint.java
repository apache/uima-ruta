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

package org.apache.uima.ruta.cde;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

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
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

public class SimpleRutaRuleConstraint implements IRutaRuleConstraint {

  private String rule;

  private String description;

  private String typeSystemLocation;

  private AnalysisEngine ae;

  private boolean initalized = false;

  public SimpleRutaRuleConstraint(String rule, String description) {
    this.rule = rule;
    this.description = description;
  }

  public void initialize() throws Exception {

    String script = "PACKAGE org.apache.uima.ruta;\n\n";
    if (!rule.endsWith(";")) {
      rule = rule + ";";
    }
    script += rule;
    URL aedesc = RutaEngine.class.getResource("BasicEngine.xml");
    XMLInputSource inae = new XMLInputSource(aedesc);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(inae);
    ResourceManager resMgr = UIMAFramework.newDefaultResourceManager();
    AnalysisEngineDescription aed = (AnalysisEngineDescription) specifier;
    TypeSystemDescription basicTypeSystem = aed.getAnalysisEngineMetaData().getTypeSystem();
    if (!typeSystemLocation.equals("")) {
      String tsLocation = typeSystemLocation;
      Collection<TypeSystemDescription> tsds = new ArrayList<TypeSystemDescription>();
      tsds.add(basicTypeSystem);
      if (typeSystemLocation.endsWith(".ruta")) {
        IFile iFile = RutaAddonsPlugin.getIFile(typeSystemLocation);
        IPath scriptPath = iFile.getLocation();
        IProject project = iFile.getProject();
        IPath descriptorRootPath = RutaProjectUtils.getDescriptorRootPath(project);
        resMgr.setDataPath(descriptorRootPath.toPortableString());
        IPath path = RutaProjectUtils.getTypeSystemDescriptorPath(scriptPath, project);
        tsLocation = path.toPortableString();
      }
      File tsFile = new File(tsLocation);
      XMLInputSource ints = new XMLInputSource(tsFile);
      TypeSystemDescription importTSD = UIMAFramework.getXMLParser().parseTypeSystemDescription(
              ints);
      importTSD.resolveImports(resMgr);
      tsds.add(importTSD);
      TypeSystemDescription mergeTypeSystems = CasCreationUtils.mergeTypeSystems(tsds);
      aed.getAnalysisEngineMetaData().setTypeSystem(mergeTypeSystems);
    }
    aed.resolveImports(resMgr);
    ae = UIMAFramework.produceAnalysisEngine(aed, resMgr, null);
    File tempFile = File.createTempFile("RutaRuta", RutaEngine.SCRIPT_FILE_EXTENSION);
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
    ae.reconfigure();

  }

  public Double processConstraint(CAS cas) throws Exception {

    if (!initalized) {
      initialize();
    }

    Type matchedType = cas.getTypeSystem().getType(
            "org.apache.uima.ruta.type.DebugMatchedRuleMatch");
    Type ruleApplyType = cas.getTypeSystem().getType("org.apache.uima.ruta.type.DebugRuleApply");
    Type blockApplyType = cas.getTypeSystem().getType("org.apache.uima.ruta.type.DebugBlockApply");
    removeDebugAnnotations(cas, matchedType, ruleApplyType, blockApplyType);
    double applyAmount = 0;
    double triedAmount = 0;
    ae.process(cas);
    Feature innerApplyFeature = blockApplyType.getFeatureByBaseName("innerApply");
    Feature appliedFeature = blockApplyType.getFeatureByBaseName("applied");
    Feature triedFeature = blockApplyType.getFeatureByBaseName("tried");
    FSIterator<AnnotationFS> iterator = cas.getAnnotationIndex(blockApplyType).iterator();
    if (iterator.isValid()) {
      AnnotationFS fs = iterator.get();
      if (fs.getType().equals(blockApplyType)) {
        FeatureStructure featureValue = fs.getFeatureValue(innerApplyFeature);
        FSArray array = (FSArray) featureValue;
        AnnotationFS ruleApply = (AnnotationFS) array.get(0);

        if (ruleApply.getType().equals(ruleApplyType)) {
          applyAmount = ruleApply.getIntValue(appliedFeature);
          triedAmount = ruleApply.getIntValue(triedFeature);
        }
      }
    }

    removeDebugAnnotations(cas, matchedType, ruleApplyType, blockApplyType);
    ae.destroy();

    if (triedAmount == 0) {
      return null;
    }
    return applyAmount / triedAmount;
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

  public String getDescription() {
    return StringUtils.isBlank(description) ? rule : description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setTypeSystemLocation(String location) {
    typeSystemLocation = location;
  }

  public String getTypeSystemLocation() {
    return typeSystemLocation;
  }

  public String getData() {
    return rule;
  }

  public void setData(String data) {
    this.rule = data;
  }

}
