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

package org.apache.uima.ruta.action;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaModule;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.ScriptApply;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class CallAction extends AbstractRutaAction {

  protected String namespace;

  public CallAction(String namespace) {
    super();
    this.namespace = namespace;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, RutaStream stream, InferenceCrowd crowd) {
    RutaModule thisScript = element.getParent().getScript();
    AnalysisEngine targetEngine = thisScript.getEngine(namespace);
    if (targetEngine != null) {
      try {
        callEngine(match, crowd, targetEngine, element, stream);
      } catch (AnalysisEngineProcessException e) {
        e.printStackTrace();
      } catch (ResourceInitializationException e) {
        e.printStackTrace();
      }
    } else {
      RutaBlock block = thisScript.getBlock(namespace);
      if (block != null) {
        callScript(block, match, element, stream, crowd);
      } else {
        System.out.println("Found no script/block: " + namespace);
      }
    }

  }

  protected void callScript(RutaBlock block, RuleMatch match, RuleElement element,
          RutaStream stream, InferenceCrowd crowd) {
    List<AnnotationFS> matchedAnnotationsOf = match.getMatchedAnnotationsOf(element);
    for (AnnotationFS annotationFS : matchedAnnotationsOf) {
      RutaStream windowStream = stream.getWindowStream(annotationFS,
              stream.getDocumentAnnotationType());
      ScriptApply apply = block.apply(windowStream, crowd);
      match.addDelegateApply(this, apply);
    }

  }

  protected void callEngine(RuleMatch match, InferenceCrowd crowd, AnalysisEngine targetEngine,
          RuleElement element, RutaStream stream) throws ResourceInitializationException,
          AnalysisEngineProcessException {

    List<AnnotationFS> matchedAnnotations = match.getMatchedAnnotations(null,
            element.getContainer());
    for (AnnotationFS matchedAnnotation : matchedAnnotations) {

      StringBuilder newDocument = new StringBuilder();
      RutaStream windowStream = stream.getWindowStream(matchedAnnotation,
              stream.getDocumentAnnotationType());
      windowStream.moveToFirst();

      CAS newCAS = targetEngine.newCAS();
      List<Type> types = newCAS.getTypeSystem()
              .getProperlySubsumedTypes(newCAS.getAnnotationType());

      Collection<AnnotationFS> fsToAdd = new HashSet<AnnotationFS>();

      Map<Integer, Integer> new2oldBegin = new TreeMap<Integer, Integer>();
      Map<Integer, Integer> new2oldEnd = new TreeMap<Integer, Integer>();
      Map<Integer, Integer> old2newBegin = new TreeMap<Integer, Integer>();
      Map<Integer, Integer> old2newEnd = new TreeMap<Integer, Integer>();

      int localBegin = 0;
      int localEnd = 0;
      while (windowStream.isValid()) {
        FeatureStructure fs = windowStream.get();
        if (fs instanceof RutaBasic) {
          RutaBasic basic = (RutaBasic) fs;
          for (Type type : types) {
            Collection<AnnotationFS> beginAnchors = basic.getBeginAnchors(type);
            for (AnnotationFS a : beginAnchors) {
              if (a != null && !a.getType().getName().equals("uima.tcas.DocumentAnnotation")
                      && !(a instanceof RutaBasic)) {
                fsToAdd.add(a);
              }
            }
          }
          int length = basic.getEnd() - basic.getBegin();
          localEnd = localBegin + length;

          new2oldBegin.put(localBegin, basic.getBegin());
          old2newBegin.put(basic.getBegin(), localBegin);
          new2oldEnd.put(localEnd, basic.getEnd());
          old2newEnd.put(basic.getEnd(), localEnd);

          newDocument.append(basic.getCoveredText());

          localBegin += length;
        }
        windowStream.moveToNext();
      }

      String string = newDocument.toString();
      newCAS.setDocumentText(string);
      for (AnnotationFS each : fsToAdd) {
        int beginOld = each.getBegin();
        int endOld = each.getEnd();

        Integer beginNew = old2newBegin.get(beginOld);
        Integer endNew = old2newEnd.get(endOld);
        if (endNew == null && beginNew != null) {
          int delta = endOld - beginOld;
          endNew = beginNew + delta;
        } else if (endNew != null && beginNew == null) {
          int delta = endOld - beginOld;
          beginNew = endNew - delta;
        } else if (endNew == null && beginNew == null) {
          int index;
          int deltaBefore = 0;
          int deltaAfter = 0;
          Integer valueBegin = null;
          index = beginOld;
          while (valueBegin == null) {
            valueBegin = new2oldBegin.get(++index);
            deltaBefore++;
          }
          Integer valueEnd = null;
          index = endOld;
          while (valueEnd == null) {
            valueEnd = new2oldEnd.get(--index);
            deltaAfter++;
          }
          beginNew = valueBegin - deltaBefore;
          endNew = valueEnd + deltaAfter;
        }

        String typeName = each.getType().getName();
        Type type = newCAS.getTypeSystem().getType(typeName);
        FeatureStructure newAnnotation = newCAS.createAnnotation(type, beginNew, endNew);
        newCAS.addFsToIndexes(newAnnotation);
      }

      targetEngine.process(newCAS);

      for (Type type : types) {
        FSIterator<AnnotationFS> iterator = newCAS.getAnnotationIndex(type).iterator();
        while (iterator.isValid()) {
          AnnotationFS each = iterator.get();
          transform(each, new2oldBegin, new2oldEnd, fsToAdd, stream, match);
          iterator.moveToNext();
        }
      }
    }
  }

  private void transform(FeatureStructure each, Map<Integer, Integer> new2oldBegin,
          Map<Integer, Integer> new2oldEnd, Collection<AnnotationFS> fsToAdd, RutaStream stream,
          RuleMatch match) {
    CAS cas = stream.getCas();
    Type newType = cas.getTypeSystem().getType(each.getType().getName());
    if (newType != null && !fsToAdd.contains(each)
            && !newType.getName().equals("uima.tcas.DocumentAnnotation")
            && !(each instanceof RutaBasic)) {

      FeatureStructure newFS = null;
      if (each instanceof AnnotationFS) {
        newFS = transformAnnotation((AnnotationFS) each, newType, new2oldBegin, new2oldEnd, stream,
                match);
      } else {
        newFS = cas.createFS(newType);
        fillFeatures(each, newFS, newFS.getType(), new2oldBegin, new2oldEnd, stream, match);
      }
      cas.addFsToIndexes(newFS);
    }
  }

  private FeatureStructure transformAnnotation(AnnotationFS annotation, Type newType,
          Map<Integer, Integer> new2oldBegin, Map<Integer, Integer> new2oldEnd, RutaStream stream,
          RuleMatch match) {
    CAS cas = stream.getCas();
    Integer beginOld = annotation.getBegin();
    Integer endOld = annotation.getEnd();
    FeatureStructure newFS = cas.createFS(newType);
    fillFeatures(annotation, newFS, newType, new2oldBegin, new2oldEnd, stream, match);

    Integer beginNew = new2oldBegin.get(beginOld);
    Integer endNew = new2oldEnd.get(endOld);
    if (endNew == null && beginNew != null) {
      int delta = endOld - beginOld;
      endNew = beginNew + delta;
    } else if (endNew != null && beginNew == null) {
      int delta = endOld - beginOld;
      beginNew = endNew - delta;
    } else if (endNew == null && beginNew == null) {
      int index;
      int deltaBefore = 0;
      int deltaAfter = 0;
      Integer valueBegin = null;
      index = beginOld;
      while (valueBegin == null) {
        valueBegin = new2oldBegin.get(++index);
        deltaBefore++;
      }
      Integer valueEnd = null;
      index = endOld;
      while (valueEnd == null) {
        valueEnd = new2oldEnd.get(--index);
        deltaAfter++;
      }
      beginNew = valueBegin - deltaBefore;
      endNew = valueEnd + deltaAfter;
    }

    if (newFS instanceof Annotation) {
      Annotation newA = (Annotation) newFS;
      newA.setBegin(beginNew);
      newA.setEnd(endNew);
      stream.addAnnotation(newA, match);
    }
    return newFS;
  }

  private void fillFeatures(FeatureStructure oldFS, FeatureStructure newFS, Type newType,
          Map<Integer, Integer> new2oldBegin, Map<Integer, Integer> new2oldEnd, RutaStream stream,
          RuleMatch match) {
    for (Object obj : newType.getFeatures()) {
      Feature feature = (Feature) obj;
      String sn = feature.getShortName();
      if (!"sofa".equals(sn) && !"begin".equals(sn) && !"end".equals(sn)) {
        Feature oldFeature = oldFS.getType().getFeatureByBaseName(sn);
        fillFeature(oldFS, oldFeature, newFS, feature, new2oldBegin, new2oldEnd, stream, match);
      }
    }
  }

  private void fillFeature(FeatureStructure oldFS, Feature oldFeature, FeatureStructure newFS,
          Feature feature, Map<Integer, Integer> new2oldBegin, Map<Integer, Integer> new2oldEnd,
          RutaStream stream, RuleMatch match) {
    CAS cas = stream.getCas();
    Type oldRange = oldFeature.getRange();
    if (oldRange.isPrimitive()) {
      if (oldRange.getShortName().equals("String")) {
        String stringValue = oldFS.getStringValue(oldFeature);
        newFS.setStringValue(feature, stringValue);
      } else if (oldRange.getShortName().equals("Integer")) {
        Integer intValue = oldFS.getIntValue(oldFeature);
        newFS.setIntValue(feature, intValue);
      } else if (oldRange.getShortName().equals("Float")) {
        Float floatValue = oldFS.getFloatValue(oldFeature);
        newFS.setFloatValue(feature, floatValue);
      } else if (oldRange.getShortName().equals("Boolean")) {
        Boolean booleanValue = oldFS.getBooleanValue(oldFeature);
        newFS.setBooleanValue(feature, booleanValue);
      }
    } else {
      FeatureStructure oldFeatureFS = oldFS.getFeatureValue(oldFeature);
      FeatureStructure newFeatureFS = null;
      if (oldRange.isArray()) {
        newFeatureFS = cas.createArrayFS(0);
      } else {
        newFeatureFS = cas.createFS(feature.getRange());
      }
      if (newFeatureFS instanceof AnnotationFS) {
        transformAnnotation((AnnotationFS) newFeatureFS, newFeatureFS.getType(), new2oldBegin,
                new2oldEnd, stream, match);
      } else {
        fillFeatures(oldFeatureFS, newFeatureFS, newFeatureFS.getType(), new2oldBegin, new2oldEnd,
                stream, match);
      }
    }
  }

  public String getNamespace() {
    return namespace;
  }

}
