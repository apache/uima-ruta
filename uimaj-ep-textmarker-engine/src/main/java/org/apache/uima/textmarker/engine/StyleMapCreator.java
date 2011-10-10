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

import java.io.IOException;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

public class StyleMapCreator extends JCasAnnotator_ImplBase {

  private UimaContext context;

  private StyleMapFactory styleMapFactory;

  public static final String STYLE_MAP = "styleMap";

  private String styleMapLocation;

  private String[] descriptorPaths;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    if (aContext == null && context != null) {
      aContext = context;
    }
    styleMapLocation = (String) aContext.getConfigParameterValue(STYLE_MAP);
    descriptorPaths = (String[]) aContext
            .getConfigParameterValue(TextMarkerEngine.DESCRIPTOR_PATHS);
    styleMapFactory = new StyleMapFactory();
  }

  @Override
  public void process(JCas jcas) throws AnalysisEngineProcessException {
    try {
      String locate = TextMarkerEngine.locate(styleMapLocation, descriptorPaths, ".xml", false);
      if (locate != null) {
        styleMapFactory.createStyleMap(locate, jcas);
      }
    } catch (IOException e) {
      throw new AnalysisEngineProcessException(e);
    }
  }

}
