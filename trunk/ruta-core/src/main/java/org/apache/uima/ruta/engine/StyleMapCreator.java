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

import java.io.IOException;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

/**
 * This Analysis Engine can be utilized to create style map information, which is needed by the
 * Modifier Analysis Engine in order to create highlightings for some annotations. Style map
 * information can be created using the COLOR action. A descriptor file for this Analysis Engine is
 * located in the folder <code>descriptor/utils</code> of a UIMA Ruta project.
 * 
 */
public class StyleMapCreator extends JCasAnnotator_ImplBase {

  /**
   * This parameter can contain multiple string values and specifies the absolute paths where the
   * style map file can be found.
   */
  public static final String PARAM_DESCRIPTOR_PATHS = RutaEngine.PARAM_DESCRIPTOR_PATHS;

  @ConfigurationParameter(name = PARAM_DESCRIPTOR_PATHS, mandatory = false)
  private String[] descriptorPaths;

  /**
   * This string parameter specifies the name of the style map file created by the Style Map Creator
   * Analysis Engine, which stores the colors for additional highlightings in the modified view.
   */
  public static final String PARAM_STYLE_MAP = "styleMap";

  @ConfigurationParameter(name = PARAM_STYLE_MAP, mandatory = false)
  private String styleMapLocation;

  private UimaContext context;

  private StyleMapFactory styleMapFactory;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    if (aContext == null && context != null) {
      aContext = context;
    }
    if (aContext != null) {
      styleMapLocation = (String) aContext.getConfigParameterValue(PARAM_STYLE_MAP);
      descriptorPaths = (String[]) aContext
              .getConfigParameterValue(RutaEngine.PARAM_DESCRIPTOR_PATHS);
      styleMapFactory = new StyleMapFactory();
      this.context = aContext;
    }
  }

  @Override
  public void process(JCas jcas) throws AnalysisEngineProcessException {
    try {
      String locate = RutaEngine.locate(styleMapLocation, descriptorPaths, ".xml", false);
      if (locate != null) {
        styleMapFactory.createStyleMap(locate, jcas);
      }
    } catch (IOException e) {
      throw new AnalysisEngineProcessException(e);
    }
  }

}
