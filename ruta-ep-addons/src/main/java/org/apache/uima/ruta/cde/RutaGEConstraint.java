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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

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
import org.apache.uima.ruta.cde.utils.EvaluationMeasures;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.runtime.Path;

public class RutaGEConstraint implements IRutaConstraint {

  private String constraintText;

  private String description;

  public RutaGEConstraint(String constraintText, String description) {
    this.constraintText = constraintText;
    this.description = description;
  }

  public Double processConstraint(CAS cas) throws Exception {
    // Constraint Format: "Peter":Author 0.44, Editor 0.50
    // counts for sysouts, remove later
    int runCount = 0;
    int printCount = 0;

    HashMap<String, Double> rulesMap = createRuleSet();
    ArrayList<Double[]> results = new ArrayList<Double[]>();

    Iterator<Entry<String, Double>> rulesIterator = rulesMap.entrySet().iterator();
    StringBuilder sb = new StringBuilder();
    sb.append("PACKAGE org.apache.uima.ruta;\n\n");
    int counter = 0;
    while (rulesIterator.hasNext()) {

      Entry<String, Double> entry = rulesIterator.next();
      String rule = entry.getKey();
      // Double ratioInConstraint = (Double) entry.getValue();

      // if (!rule.endsWith(";")) {
      // rule = rule + ";";
      // }
      sb.append(rule);
      sb.append("\n");
      counter++;
      if (counter % 100 == 0) {
        sb.append("Document{-> LOG(\"" + counter + "/" + rulesMap.size() + "\")};");
      }

    }
    URL aedesc = RutaEngine.class.getResource("BasicEngine.xml");
    XMLInputSource inae = new XMLInputSource(aedesc);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(inae);
    ResourceManager resMgr = UIMAFramework.newDefaultResourceManager();
    AnalysisEngineDescription aed = (AnalysisEngineDescription) specifier;

    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(aed, resMgr, null);
    File tempFile = File.createTempFile("RutaCDE", RutaEngine.SCRIPT_FILE_EXTENSION);
    tempFile.deleteOnExit();
    FileUtils.saveString2File(sb.toString(), tempFile, "UTF-8");
    String portableString = Path.fromOSString(tempFile.getParentFile().getPath())
            .toPortableString();
    ae.setConfigParameterValue(RutaEngine.SCRIPT_PATHS, new String[] { portableString });
    String name = tempFile.getName().substring(0, tempFile.getName().length() - 5);
    ae.setConfigParameterValue(RutaEngine.MAIN_SCRIPT, name);

    ae.setConfigParameterValue(RutaEngine.CREATE_DEBUG_INFO, true);
    ae.setConfigParameterValue(RutaEngine.CREATE_MATCH_DEBUG_INFO, true);
    ae.setConfigParameterValue(RutaEngine.CREATE_PROFILING_INFO, false);
    ae.setConfigParameterValue(RutaEngine.CREATE_STATISTIC_INFO, false);
    ae.reconfigure();

    Type matchedType = cas.getTypeSystem().getType(
            "org.apache.uima.ruta.type.DebugMatchedRuleMatch");
    Type ruleApplyType = cas.getTypeSystem().getType("org.apache.uima.ruta.type.DebugRuleApply");
    Type blockApplyType = cas.getTypeSystem().getType("org.apache.uima.ruta.type.DebugBlockApply");

    removeDebugAnnotations(cas, matchedType, ruleApplyType, blockApplyType);
    double applyAmount = 0;
    double triedAmount = 0;
    ae.process(cas);
    Feature innerApplyFeature = blockApplyType.getFeatureByBaseName("innerApply");
    Feature appliedFeature = ruleApplyType.getFeatureByBaseName("applied");
    Feature triedFeature = ruleApplyType.getFeatureByBaseName("tried");
    Feature elementFeature = ruleApplyType.getFeatureByBaseName("element");
    FSIterator<AnnotationFS> iterator = cas.getAnnotationIndex(blockApplyType).iterator();
    if (iterator.isValid()) {
      AnnotationFS fs = iterator.get();
      if (fs.getType().equals(blockApplyType)) {
        FeatureStructure featureValue = fs.getFeatureValue(innerApplyFeature);
        FSArray array = (FSArray) featureValue;

        FeatureStructure[] fsArray = array.toArray();
        for (FeatureStructure featureStructure : fsArray) {
          AnnotationFS ruleApply = (AnnotationFS) featureStructure;
          if (ruleApply.getType().equals(ruleApplyType)) {
            applyAmount = ruleApply.getIntValue(appliedFeature);
            triedAmount = ruleApply.getIntValue(triedFeature);
            String ruleString = ruleApply.getStringValue(elementFeature);

            if (triedAmount == 0) {
            } else {
              double ratioInDocument = applyAmount / triedAmount;
              String key = ruleString.trim() + ";";
              Double ratioInConstraint = rulesMap.get(key);
              if (ratioInConstraint != null) {
                results.add(new Double[] { ratioInConstraint, ratioInDocument });
              } else {
                System.out.println("rule not found!!!: " + key);
              }
            }
          }

        }

      }
    }

    removeDebugAnnotations(cas, matchedType, ruleApplyType, blockApplyType);
    ae.destroy();

    runCount++;
    printCount++;
    if (printCount == 10) {
      System.out.println(runCount);
      System.out.println("time: " + System.currentTimeMillis());
      printCount = 0;
    }

    // calculate cosinus similarity for result values:
    return EvaluationMeasures.cosine(results);
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
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public HashMap<String, Double> createRuleSet() {
    HashMap<String, Double> rulesMap = new HashMap<String, Double>();
    try {
      String content = FileUtils.file2String(new File(constraintText));

      String[] constraintTextArray = content.split("\n");
      for (String constraintLine : constraintTextArray) {

        String[] patternAndEstimates = constraintLine.split(":");
        String pattern = patternAndEstimates[0];
        pattern = pattern.trim();
        String estimates = patternAndEstimates[1];
        String[] singleEstimates = estimates.split(",");

        for (String singleEstimate : singleEstimates) {
          singleEstimate = singleEstimate.trim();
          String[] typeAndRatio = singleEstimate.split("\\s+");
          String typeName = typeAndRatio[0];
          String ratio = typeAndRatio[1];
          ratio.trim();
          String rule = "";

          if (pattern.startsWith("\"")) {
            rule = pattern + "{PARTOF(" + typeName + ")};";
          } else {
            rule = pattern + "{PARTOF(" + typeName + ")};";
          }
          rulesMap.put(rule, Double.valueOf(ratio));
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return rulesMap;
  }

  public String getData() {
    return constraintText;
  }

  public void setData(String data) {
    this.constraintText = data;
  }
}
