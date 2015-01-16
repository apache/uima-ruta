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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.tools.stylemap.StyleMapEntry;
import org.apache.uima.util.FileUtils;

/**
 * The Modifier Analysis Engine can be used to create an additional view, which contains all textual
 * modifications and HTML highlightings that were specified by the executed rules. This Analysis
 * Engine can be applied, e.g., for anonymization where all annotations of persons are replaced by
 * the string <code>Person</code>. Furthermore, the content of the new view can optionally be stored
 * in a new HTML file. A descriptor file for this Analysis Engine is located in the folder
 * <code>descriptor/utils</code> of a UIMA Ruta project.
 * 
 */
public class RutaModifier extends JCasAnnotator_ImplBase {

  public static final String DEFAULT_MODIFIED_VIEW = "modified";

  /**
   * This optional string parameter specifies the absolute path of the resulting file named
   * <code>output.modified.html</code>. However, if an annotation of the type
   * <code>org.apache.uima.examples.SourceDocumentInformation</code> is given, then the value of
   * this parameter is interpreted to be relative to the URI stored in the annotation and the name
   * of the file will be adapted to the name of the source file. If this functionality is activated
   * in the preferences, then the UIMA Ruta Workbench adds the SourceDocumentInformation annotation
   * when the user launches a script file. The default value of this parameter is empty. In this
   * case no additional html file will be created.
   */
  public static final String PARAM_OUTPUT_LOCATION = "outputLocation";

  @ConfigurationParameter(name = PARAM_OUTPUT_LOCATION, mandatory = false)
  private String outputLocation;

  /**
   * This string parameter specifies the name of the style map file created by the Style Map Creator
   * Analysis Engine, which stores the colors for additional highlightings in the modified view.
   */
  public static final String PARAM_STYLE_MAP = StyleMapCreator.PARAM_STYLE_MAP;

  @ConfigurationParameter(name = PARAM_STYLE_MAP, mandatory = false)
  private String styleMapLocation;

  /**
   * This string parameter specifies the name of the view, which will contain the modified document.
   * A view of this name must not yet exist. The default value of this parameter is
   * <code>modified</code>.
   */
  public static final String PARAM_OUTPUT_VIEW = "outputView";

  @ConfigurationParameter(name = PARAM_OUTPUT_VIEW, mandatory = false, defaultValue = DEFAULT_MODIFIED_VIEW)
  private String modifiedViewName;

  /**
   * This parameter can contain multiple string values and specifies the absolute paths where the
   * style map file can be found.
   */
  public static final String PARAM_DESCRIPTOR_PATHS = RutaEngine.PARAM_DESCRIPTOR_PATHS;

  @ConfigurationParameter(name = PARAM_DESCRIPTOR_PATHS, mandatory = false)
  private String[] descriptorPaths;

  private StyleMapFactory styleMapFactory;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    styleMapLocation = (String) aContext.getConfigParameterValue(PARAM_STYLE_MAP);
    descriptorPaths = (String[]) aContext
            .getConfigParameterValue(RutaEngine.PARAM_DESCRIPTOR_PATHS);
    outputLocation = (String) aContext.getConfigParameterValue(RutaModifier.PARAM_OUTPUT_LOCATION);
    modifiedViewName = (String) aContext.getConfigParameterValue(RutaModifier.PARAM_OUTPUT_VIEW);
    modifiedViewName = StringUtils.isBlank(modifiedViewName) ? DEFAULT_MODIFIED_VIEW
            : modifiedViewName;
    styleMapFactory = new StyleMapFactory();
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
        if (each.getViewName().equals(modifiedViewName)) {
          modifiedView = each;
          break;
        }
      }

      if (modifiedView == null) {
        try {
          modifiedView = cas.createView(modifiedViewName);
        } catch (Exception e) {
          modifiedView = cas.getView(modifiedViewName);
        }
      } else {
        modifiedView = cas.getView(modifiedViewName);
      }
      String locate = RutaEngine.locate(styleMapLocation, descriptorPaths, ".xml", true);
      try {
        String modifiedDocument = getModifiedDocument(cas, locate);
        modifiedView.setDocumentText(modifiedDocument);
      } catch (Exception e) {
        throw new AnalysisEngineProcessException(e);
      }

      String documentText = modifiedView.getDocumentText();
      if (documentText != null) {
        File outputFile = getOutputFile(cas.getCas());
        if (outputFile != null) {
          try {
            FileUtils.saveString2File(documentText, outputFile);
          } catch (IOException e) {
            throw new AnalysisEngineProcessException(e);
          }
        }
      }

    } catch (CASException e) {
      throw new AnalysisEngineProcessException(e);
    }

  }

  private File getOutputFile(CAS cas) {
    if (StringUtils.isBlank(outputLocation)) {
      return null;
    }

    Type sdiType = cas.getTypeSystem().getType(RutaEngine.SOURCE_DOCUMENT_INFORMATION);
    String filename = "output.modified.html";
    File file = new File(outputLocation, filename);
    if (sdiType != null) {
      FSIterator<AnnotationFS> sdiit = cas.getAnnotationIndex(sdiType).iterator();
      if (sdiit.isValid()) {
        AnnotationFS annotationFS = sdiit.get();
        Feature uriFeature = sdiType.getFeatureByBaseName("uri");
        String stringValue = annotationFS.getStringValue(uriFeature);
        File f = new File(stringValue);
        String name = f.getName();
        if (!name.endsWith(".modified.html")) {
          name = name + ".modified.html";
        }
        file = new File(outputLocation, name);
      }

    }
    return file;
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
            RutaBasic.type).iterator();
    while (iterator.isValid()) {
      RutaBasic each = (RutaBasic) iterator.get();
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

  private Type getColorType(RutaBasic basic, List<Type> coloredTypes, JCas cas) {
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
