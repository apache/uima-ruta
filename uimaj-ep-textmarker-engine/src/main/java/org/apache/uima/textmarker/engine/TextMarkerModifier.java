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

package org.apache.uima.textmarker.engine;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.textmarker.type.TextMarkerBasic;
import org.apache.uima.tools.stylemap.StyleMapEntry;

public class TextMarkerModifier extends JCasAnnotator_ImplBase {

  private StyleMapFactory styleMapFactory;

  private String styleMapLocation;

  private UimaContext context;

  private String[] enginePaths;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    if (aContext == null && context != null) {
      aContext = context;
    }
    styleMapLocation = (String) aContext.getConfigParameterValue(TextMarkerEngine.STYLE_MAP);
    enginePaths = (String[]) aContext.getConfigParameterValue(TextMarkerEngine.DESCRIPTOR_PATHS);
    styleMapFactory = new StyleMapFactory();
    this.context = aContext;
  }

  private String locate(String name, String[] paths, String suffix, boolean mustExist) {
    if (name == null) {
      return null;
    }
    name = name.replaceAll("[.]", "/");
    for (String each : paths) {
      File file = new File(each, name + suffix);
      if (!mustExist || file.exists()) {
        return file.getAbsolutePath();
      }
    }
    return null;
  }

  @Override
  public void process(JCas cas) throws AnalysisEngineProcessException {
    try {
      String viewName = cas.getViewName();
      if (viewName == null) {
        cas = cas.getView(CAS.NAME_DEFAULT_SOFA);
      }
      JCas modifiedView = null;
      Iterator<?> viewIterator = cas.getViewIterator();
      while (viewIterator.hasNext()) {
        JCas each = (JCas) viewIterator.next();
        if (each.getViewName().equals(TextMarkerEngine.MODIFIED_SOFA)) {
          modifiedView = each;
          break;
        }
      }

      if (modifiedView == null) {
        try {
          modifiedView = cas.createView(TextMarkerEngine.MODIFIED_SOFA);
        } catch (Exception e) {
          modifiedView = cas.getView(TextMarkerEngine.MODIFIED_SOFA);
        }
      } else {
        modifiedView = cas.getView(TextMarkerEngine.MODIFIED_SOFA);
      }
      String locate = locate(styleMapLocation, enginePaths, ".xml", true);
      // System.out.println(locate + ": " + styleMapLocation + " in " + Arrays.asList(enginePaths));
      try {
        String modifiedDocument = getModifiedDocument(cas, locate);
        modifiedView.setDocumentText(modifiedDocument);
      } catch (Exception e) {
        throw new AnalysisEngineProcessException(e);
      }
    } catch (CASException e) {
      throw new AnalysisEngineProcessException(e);
    }
  }

  private String getModifiedDocument(JCas cas, String styleMapLocation) {
    String startTag = "<span style='background:&bgcolor'>";
    String endTag = "</span>";
    Map<String, StyleMapEntry> styleMap = styleMapFactory.parseStyleMapDOM(styleMapLocation);
    List<Type> coloredTypes = new ArrayList<Type>();
    if (styleMap != null) {
      coloredTypes = getColoredTypes(cas, styleMap);
    }
    List<String> tokens = new ArrayList<String>();
    FSIterator<org.apache.uima.jcas.tcas.Annotation> iterator = cas.getAnnotationIndex(
            TextMarkerBasic.type).iterator();
    while (iterator.isValid()) {
      TextMarkerBasic each = (TextMarkerBasic) iterator.get();
      String replace = each.getReplacement() == null ? each.getCoveredText() : each
              .getReplacement();
      Type type = getColorType(each, coloredTypes, cas);
      if (type != null && !"".equals(replace)) {
        StyleMapEntry entry = styleMap.get(type.getName());
        String backgroundColor = "#"
                + Integer.toHexString(entry.getBackground().getRGB()).substring(2);
        String newStartTag = startTag.replaceAll("&bgcolor", backgroundColor);
        tokens.add(newStartTag);
        tokens.add(replace);
        tokens.add(endTag);
      } else {
        tokens.add(replace);
      }
      iterator.moveToNext();
    }
    StringBuilder sb = new StringBuilder();
    for (String string : tokens) {
      sb.append(string);
    }
    return sb.toString();
  }

  private Type getColorType(TextMarkerBasic basic, List<Type> coloredTypes, JCas cas) {
    if (coloredTypes.isEmpty()) {
      return null;
    }
    List<Type> colorTypes = new ArrayList<Type>();
    for (Type each : coloredTypes) {
      if (cas.getTypeSystem().subsumes(each, basic.getType()) || basic.isPartOf(each)) {
        colorTypes.add(each);
      }
    }
    Type best = null;
    if (colorTypes.isEmpty()) {
    } else if (colorTypes.size() == 1) {
      best = colorTypes.get(0);
    } else {
      // best = colorTypes.get(0);
    }
    return best;
  }

  private List<Type> getColoredTypes(JCas cas, Map<String, StyleMapEntry> styleMap) {
    List<Type> result = new ArrayList<Type>();
    for (String string : styleMap.keySet()) {
      if (string == null)
        continue;
      Type type = cas.getTypeSystem().getType(string);
      if (type != null) {
        result.add(type);
      }
    }
    Collections.sort(result, new Comparator<Type>() {
      public int compare(Type o1, Type o2) {
        return o1.getShortName().compareTo(o2.getShortName());
      }
    });
    return result;
  }

}
