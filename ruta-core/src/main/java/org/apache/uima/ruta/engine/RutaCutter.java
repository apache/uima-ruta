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

package org.apache.uima.ruta.engine;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.tcas.DocumentAnnotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.CasCopier;
import org.apache.uima.util.Level;

public class RutaCutter extends JCasAnnotator_ImplBase {

  public static final String OUTPUT_VIEW = "outputView";

  public static final String INPUT_VIEW = "inputView";

  public static final String KEEP = "keep";

  private static final String DEFAULT_OUTPUT_VIEW = "cutted";

  private static final String DEFAULT_INPUT_VIEW = "_InitialView";

  // variables:
  private String inputViewName;

  private String outputViewName;

  private String keep;

  private int[] map;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    inputViewName = (String) aContext.getConfigParameterValue(INPUT_VIEW);
    inputViewName = StringUtils.isBlank(inputViewName) ? DEFAULT_INPUT_VIEW : inputViewName;
    outputViewName = (String) aContext.getConfigParameterValue(OUTPUT_VIEW);
    outputViewName = StringUtils.isBlank(outputViewName) ? DEFAULT_OUTPUT_VIEW : outputViewName;
    keep = (String) aContext.getConfigParameterValue(KEEP);
  }

  @Override
  public void process(JCas jcaz) throws AnalysisEngineProcessException {
    JCas jcas;
    try {
      if (inputViewName != null) {
        jcas = jcaz.getView(inputViewName);
      } else {
        jcas = jcaz;
      }
    } catch (CASException e1) {
      throw new AnalysisEngineProcessException(e1.getCause());
    }
    // init:
    String documentText = jcas.getDocumentText();
    map = new int[documentText.length() + 1];
    JCas modview = null;
    try {
      // check if view already exists:
      Iterator<JCas> viewIterator = jcas.getViewIterator();
      while (viewIterator.hasNext()) {
        JCas jCas2 = (JCas) viewIterator.next();
        if (jCas2.getViewName().equals(outputViewName)) {
          modview = jCas2;
          getContext().getLogger().log(Level.WARNING,
                  "view with name \"" + outputViewName + "\" already exists.");
        }
      }
      if (modview == null) {
        modview = jcas.createView(outputViewName);
      }
    } catch (CASException e) {
      e.printStackTrace();
      return;
    }
    SortedSet<HtmlConverterPSpan> visibleSpansSoFar = new TreeSet<HtmlConverterPSpan>();

    // process

    TypeSystem typeSystem = jcas.getTypeSystem();
    Type keepType = typeSystem.getType(keep);
    if (keepType == null) {
      Iterator<Type> typeIterator = typeSystem.getTypeIterator();
      while (typeIterator.hasNext()) {
        Type type = (Type) typeIterator.next();
        String shortName = type.getShortName();
        if (shortName.equals(keep)) {
          keepType = type;
          break;
        }
      }
      if (keepType == null) {
        getContext().getLogger().log(Level.WARNING, "Type \"" + keep + "\" not defined.");
        return;
      }
    }
    AnnotationIndex<AnnotationFS> annotationIndex = jcas.getCas().getAnnotationIndex(keepType);
    for (AnnotationFS each : annotationIndex) {
      visibleSpansSoFar.add(new HtmlConverterPSpan(each.getBegin(), each.getEnd(), each
              .getCoveredText()));
    }

    // create new doc-text and the map from deletions and visible-text-spans:
    StringBuffer sbu = new StringBuffer(documentText.length());
    int originalOffsetI = 0;
    int outOffset = 0;
    for (HtmlConverterPSpan vis : visibleSpansSoFar) {
      final int begin = vis.getBegin();
      final int end = vis.getEnd();

      // map text before annotation:
      while (originalOffsetI < begin) {
        map[originalOffsetI++] = outOffset;
      }

      // get and map text/replacement:
      String s = "";
      if (vis instanceof HtmlConverterPSpanReplacement) {
        // conversion/replacement:
        s = vis.getTxt();
        // asserts that s is shorter than the original source
        while (originalOffsetI < begin + s.length()) {
          map[originalOffsetI++] = outOffset++;
        }
        while (originalOffsetI < end) {
          map[originalOffsetI++] = outOffset;
        }
      } else {
        // simple annotation:
        s = documentText.substring(begin, end);
        while (originalOffsetI < end) {
          map[originalOffsetI++] = outOffset++;
        }
      }
      sbu.append(s);
    }
    while (originalOffsetI < documentText.length()) {
      map[originalOffsetI++] = outOffset;
    }
    map[documentText.length()] = outOffset + 1; // handle doc end separately
    String modTxt = sbu.toString();
    modview.setDocumentText(modTxt);

    // copy annotations using the 'map':
    try {
      mapAnnotations(jcas, map, outputViewName);
    } catch (CASException e) {
      e.printStackTrace();
    }
  }

  private void mapAnnotations(JCas fromJcas, int[] map, String toView) throws CASException {
    JCas modview = fromJcas.getView(toView);

    Set<Annotation> indexedFs = new HashSet<Annotation>();
    AnnotationIndex<Annotation> annotationIndex = fromJcas.getAnnotationIndex();

    CasCopier casCopier = new CasCopier(fromJcas.getCas(), modview.getCas());
    for (Annotation annotation : annotationIndex) {
      // TODO be careful here, because some people inherit from DocumentAnnotation
      if (annotation instanceof DocumentAnnotation) {
        continue;
      }
      Annotation clone = (Annotation) casCopier.copyFs(annotation);
      // change the view/sofa of the new annotation...
      // see: http://osdir.com/ml/apache.uima.general/2007-09/msg00107.html
      clone.setFeatureValue(modview.getTypeSystem()
              .getFeatureByFullName(CAS.FEATURE_FULL_NAME_SOFA), modview.getSofa());
      final int mappedBegin = map[clone.getBegin()];
      final int mappedEnd = map[clone.getEnd()];
      if (mappedBegin < mappedEnd) {
        if (mappedEnd > fromJcas.getCas().getDocumentAnnotation().getEnd()) {
          getContext().getLogger().log(Level.WARNING, "illegal annotation offset mapping");
        } else {
          int max = modview.getCas().getDocumentAnnotation().getEnd();
          if (mappedBegin < max && mappedEnd <= max && mappedBegin >= 0 && mappedEnd > 0) {
            clone.setBegin(mappedBegin);
            clone.setEnd(mappedEnd);
            // TODO handle nested annotation features
            modview.addFsToIndexes(clone);
            indexedFs.add(clone);
          } else {
            getContext().getLogger().log(Level.WARNING, "illegal annotation offset mapping");
          }
        }
      }
    }
  }

}
